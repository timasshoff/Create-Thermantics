package com.skytendo.thermantics.temperature;

import com.skytendo.thermantics.Config;
import com.skytendo.thermantics.effect.CT_Effects;
import com.skytendo.thermantics.enchantment.CT_Enchantments;
import com.skytendo.thermantics.networking.CT_Messages;
import com.skytendo.thermantics.networking.packet.TemperatureDataSyncS2CPacket;
import com.skytendo.thermantics.temperature.modifiers.TemperatureModifier;
import com.skytendo.thermantics.temperature.modifiers.TemperatureModifierRegistry;
import com.skytendo.thermantics.util.CT_ItemTags;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.event.TickEvent;

public class PlayerTemperatureManager {

    public static double getUpdateChance(Player player) {
        if (player.isFreezing()) {
            return Config.UPDATE_CHANCE_FREEZING.get();
        }
        if (player.isOnFire()) {
            return Config.UPDATE_CHANCE_FIRE.get();
        }
        if (player.isInLava()) {
            return Config.UPDATE_CHANCE_LAVA.get();
        }
        return Config.UPDATE_CHANCE_DEFAULT.get(); // Default: Update every 10 seconds on average
    }

    public static void updateTemperature(PlayerTemperature temperature, TickEvent.PlayerTickEvent event) {
        Holder<Biome> biome =  event.player.level().getBiome(event.player.blockPosition());
        float environmentTemperature = calculateEnvironmentTemperature(biome.get(), event.player);
        adjustTemperature(temperature, environmentTemperature);

        if (Config.DEBUG_INFO.get()) {
            event.player.sendSystemMessage(Component.literal("---------"));
            event.player.sendSystemMessage(Component.literal("Temperature: " + temperature.getTemperature()));
            event.player.sendSystemMessage(Component.literal("Base Biome Temperature: " + getBiomeTemperature(biome.get())));
            event.player.sendSystemMessage(Component.literal("Environment Temperature: " + environmentTemperature));
            event.player.sendSystemMessage(Component.literal("Ticks in current temp state: " + temperature.getTicksInCurrentTempState()));
        }

        CT_Messages.sendToPlayer(new TemperatureDataSyncS2CPacket(temperature.getTemperature()), (ServerPlayer) event.player);
    }

    public static void updateTemperatureState(PlayerTemperature temperature, TickEvent.PlayerTickEvent event) {
        if (temperature.getCurrentTempState() == PlayerTemperature.temperatureToState(temperature.getTemperature())) {
            temperature.increaseTicksInCurrentTempState();
        } else {
            temperature.setCurrentTempState(PlayerTemperature.temperatureToState(temperature.getTemperature()));
            temperature.resetTicksInCurrentTempState();
        }
    }

    public static void applyTemperatureEffects(PlayerTemperature temperature, TickEvent.PlayerTickEvent event) {
        if (temperature.getTicksInCurrentTempState() <= Config.NEW_TEMP_CLEMENCY_DURATION.get()) {
            return;
        }
        if (event.player.hasEffect(CT_Effects.CLEMENCY.get())) {
            return;
        }
        if (event.player.isCreative()) {
            return;
        }

        if (temperature.getCurrentTempState() == PlayerTemperature.TemperatureState.FREEZING) {
            if (!event.player.hasEffect(CT_Effects.HYPOTHERMIA.get())) {
                event.player.addEffect(new MobEffectInstance(CT_Effects.HYPOTHERMIA.get(), 180, 2, false, false, true));
            }
        }
        if (temperature.getCurrentTempState() == PlayerTemperature.TemperatureState.COLD) {
            if (!event.player.hasEffect(CT_Effects.HYPOTHERMIA.get())) {
                event.player.addEffect(new MobEffectInstance(CT_Effects.HYPOTHERMIA.get(), 180, 1, false, false, true));
            }
        }
        if (temperature.getCurrentTempState() == PlayerTemperature.TemperatureState.CHILLY) {
            if (!event.player.hasEffect(CT_Effects.HYPOTHERMIA.get())) {
                event.player.addEffect(new MobEffectInstance(CT_Effects.HYPOTHERMIA.get(), 180, 0, false, false, true));
            }
        }
        if (temperature.getCurrentTempState() == PlayerTemperature.TemperatureState.HOT) {
            if (hasHyperthermiaIsolation(event.player)) {
                return;
            }
            if (!event.player.hasEffect(CT_Effects.HYPERTHERMIA.get())) {
                event.player.addEffect(new MobEffectInstance(CT_Effects.HYPERTHERMIA.get(), 180, 0, false, false, true));
            }
        }
        if (temperature.getCurrentTempState() == PlayerTemperature.TemperatureState.FIERY) {
            if (hasHyperthermiaIsolation(event.player)) {
                return;
            }
            if (!event.player.hasEffect(CT_Effects.HYPERTHERMIA.get())) {
                event.player.addEffect(new MobEffectInstance(CT_Effects.HYPERTHERMIA.get(), 180, 1, false, false, true));
            }
        }
    }

    private static boolean hasHyperthermiaIsolation(Player player) {
        if (player.level().dimension() != Level.NETHER) {
            return false;
        }
        for (ItemStack stack : player.getArmorSlots()) {
            if (stack.getEnchantmentLevel(CT_Enchantments.INFERNAL_PLATING.get()) > 0) {
                return true;
            }
        }
        return false;
    }

    private static float calculateEnvironmentTemperature(Biome biome, Player player) {
        float temperature = 0.0f;
        for (TemperatureModifier modifier : TemperatureModifierRegistry.modifiers) {
             temperature = modifier.modifyTemperature(player, biome, temperature);
        }
        return Math.min(Math.max(temperature, 0.0f), 60.0f);
    }

    public static float getBiomeTemperature(Biome biome) {
        return Math.min(Math.max((18.52f * biome.getBaseTemperature() + 12.96f), 0.0f), 50.0f);
    }

    private static void adjustTemperature(PlayerTemperature playerTemperature, float environmentTemperature) {
        float difference = Math.abs(environmentTemperature - playerTemperature.getTemperature());
        if (difference <= Config.TEMP_DIFFERENCE_SNAP.get()) {
            playerTemperature.setTemperature(environmentTemperature);
            return;
        }

        double speed = Config.TEMP_ADJUSTMENT_SPEED.get();
        float amount = (float) (difference * (1 - Math.exp(-speed)));
        if (environmentTemperature < playerTemperature.getTemperature()) {
            playerTemperature.decreaseTemperature(amount);
        } else if (environmentTemperature > playerTemperature.getTemperature()) {
            playerTemperature.increaseTemperature(amount);
        }
    }
}
