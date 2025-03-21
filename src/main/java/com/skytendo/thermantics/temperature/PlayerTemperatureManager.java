package com.skytendo.thermantics.temperature;

import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.event.TickEvent;

public class PlayerTemperatureManager {

    public static void updateTemperature(PlayerTemperature temperature, TickEvent.PlayerTickEvent event) {
        Holder<Biome> biome =  event.player.level().getBiome(event.player.blockPosition());
        float environmentTemperature = calculateEnvironmentTemperature(biome.get(), event.player);
        adjustTemperature(temperature, environmentTemperature);
        event.player.sendSystemMessage(Component.literal("Temperature: " + temperature.getTemperature()));
        event.player.sendSystemMessage(Component.literal("Environment Temperature: " + environmentTemperature));
    }

    private static float calculateEnvironmentTemperature(Biome biome, Player player) {
        float temperature;

        if (player.isInWater()) {
            temperature = getWaterTemperature(getBiomeTemperature(biome));
            return temperature;
        }

        temperature = getBiomeTemperature(biome);
        if (player.level().isNight()) {
            temperature += getNightAdjustment(temperature);
        }
        return temperature;
    }

    private static float getWaterTemperature(float biomeTemperature) {
        if (biomeTemperature < 0) {
            return 3.0f;
        }
        if (biomeTemperature < 8) {
            return 13.0f;
        }
        if (biomeTemperature < 16) {
            return 18.0f;
        }
        if (biomeTemperature > 28) {
            return 21.0f;
        }
        return 12.0f;
    }

    private static float getBiomeTemperature(Biome biome) {
        return (13.6484805403f * biome.getBaseTemperature()) + 7.0879687222f;
    }

    private static float getNightAdjustment(float dayTemperature) {
        if (dayTemperature < 12) {
            return -6.0f;
        }
        if (dayTemperature < 16) {
            return -8.0f;
        }
        if (dayTemperature < 20) {
            return -9.0f;
        }
        if (dayTemperature < 24) {
            return -14.0f;
        }
        if (dayTemperature < 30) {
            return -21.0f;
        }
        if (dayTemperature > 30) {
            return -28.0f;
        }
        return 0.0f;
    }

    private static void adjustTemperature(PlayerTemperature playerTemperature, float outsideTemperature) {
        float freezingFactor = 0.06f;
        float coolingFactor = 0.05f;
        float temperateFactor = 0.03f;
        float heatingFactor = 0.05f;

        float target = 19 < outsideTemperature && outsideTemperature < 25 ? PlayerTemperature.DEFAULT_TEMPERATURE : outsideTemperature;
        if (Math.abs((PlayerTemperature.DEFAULT_TEMPERATURE - target)) < 0.5f) {
            playerTemperature.setTemperature(target);
            return;
        }

        float difference = Math.abs(playerTemperature.getTemperature() - target);

        if (outsideTemperature < 0) {
            playerTemperature.decreaseTemperature((float) (Math.exp(difference * 0.05f) * freezingFactor));
            return;
        }
        if (outsideTemperature < 12) {
            playerTemperature.decreaseTemperature((float) (Math.exp(difference * 0.05f) * coolingFactor));
            return;
        }
        if (outsideTemperature > 28) {
            playerTemperature.decreaseTemperature((float) (Math.exp(difference * 0.05f) * heatingFactor));
            return;
        }
        playerTemperature.increaseTemperature((float) (Math.exp(difference * 0.05f) * temperateFactor));
    }
}
