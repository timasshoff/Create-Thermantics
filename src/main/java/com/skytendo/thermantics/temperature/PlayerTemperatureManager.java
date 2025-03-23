package com.skytendo.thermantics.temperature;

import com.skytendo.thermantics.networking.CT_Messages;
import com.skytendo.thermantics.networking.packet.TemperatureDataSyncS2CPacket;
import com.skytendo.thermantics.util.BlockFinder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.TickEvent;

import java.util.List;

public class PlayerTemperatureManager {

    public static float getUpdateChance(Player player) {
        if (player.isFreezing()) {
            return 0.08f;
        }
        if (player.isOnFire()) {
            return 0.08f;
        }
        if (player.isInLava()) {
            return 0.15f;
        }
        return 0.005f; // Default: Update every 10 seconds on average
    }

    public static void updateTemperature(PlayerTemperature temperature, TickEvent.PlayerTickEvent event) {
        Holder<Biome> biome =  event.player.level().getBiome(event.player.blockPosition());
        float environmentTemperature = calculateEnvironmentTemperature(biome.get(), event.player);
        adjustTemperature(temperature, environmentTemperature);
        event.player.sendSystemMessage(Component.literal("---------"));
        event.player.sendSystemMessage(Component.literal("Temperature: " + temperature.getTemperature()));
        event.player.sendSystemMessage(Component.literal("Base Biome Temperature: " + getBiomeTemperature(biome.get())));
        event.player.sendSystemMessage(Component.literal("Environment Temperature: " + environmentTemperature));

        CT_Messages.sendToPlayer(new TemperatureDataSyncS2CPacket(temperature.getTemperature()), (ServerPlayer) event.player);
    }

    private static float calculateEnvironmentTemperature(Biome biome, Player player) {
        float temperature = 0.0f;

        if (45 < player.getY() && player.getY() < 155) {
            temperature = getBiomeTemperature(biome);
        } else if (player.getY() < 45) {
            temperature = 25.0f;
        } else if (player.getY() > 155) {
            temperature = 11.0f;
        }

        if (player.isInWater()) {
            temperature -= 9.0f;
        }

        if (player.isFreezing()) {
            temperature -= 18.0f;
        }

        if (player.isInLava()) {
            temperature += 55.0f;
        }

        if (player.isOnFire()) {
            temperature += 30.0f;
        }

        if (player.level().isNight()) {
            temperature -= 8.0f;
        }

        temperature += BlockFinder.checkAndCalculateTemperatureModifier(player.level(), player.blockPosition(), Blocks.CAMPFIRE, 5, 22.0f, 0.35f);
        temperature += BlockFinder.checkAndCalculateTemperatureModifier(player.level(), player.blockPosition(), Blocks.FIRE, 6, 35.0f, 0.55f);
        temperature += BlockFinder.checkAndCalculateTemperatureModifier(player.level(), player.blockPosition(), Blocks.LAVA, 8, 55.0f, 0.55f);

        return Math.min(Math.max(temperature, 0.0f), 60.0f);
    }

    private static float getBiomeTemperature(Biome biome) {
        return Math.min(Math.max((18.52f * biome.getBaseTemperature() + 12.96f), 0.0f), 50.0f);
    }

    private static void adjustTemperature(PlayerTemperature playerTemperature, float environmentTemperature) {
        float difference = Math.abs(environmentTemperature - playerTemperature.getTemperature());
        if (difference <= 1.0f) {
            playerTemperature.setTemperature(environmentTemperature);
            return;
        }

        float speed = 0.6f;
        float amount = (float) (difference * (1 - Math.exp(-speed)));
        if (environmentTemperature < playerTemperature.getTemperature()) {
            playerTemperature.decreaseTemperature(amount);
        } else if (environmentTemperature > playerTemperature.getTemperature()) {
            playerTemperature.increaseTemperature(amount);
        }
    }
}
