package com.skytendo.thermantics.temperature.modifiers;

import com.skytendo.thermantics.Config;
import com.skytendo.thermantics.temperature.PlayerTemperatureManager;
import com.skytendo.thermantics.util.BlockFinder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;

public class BasicModifiers {

    public static class HeightBiomeModifier implements TemperatureModifier {
        @Override
        public float modifyTemperature(Player player, Biome biome, float temperature) {
            if (player.level().dimension() == Level.NETHER) {
                return PlayerTemperatureManager.getBiomeTemperature(biome) + 1.5f;
            }
            if (player.level().dimension() == Level.END) {
                return PlayerTemperatureManager.getBiomeTemperature(biome);
            }
            if (Config.MIN_HEIGHT_BIOME_TEMP.get() < player.getY() && player.getY() < Config.MAX_HEIGHT_BIOME_TEMP.get()) {
                temperature = PlayerTemperatureManager.getBiomeTemperature(biome);
            } else if (player.getY() < Config.MIN_HEIGHT_BIOME_TEMP.get()) {
                temperature = (float) (double) Config.BELOW_BIOME_TEMP.get();
            } else if (player.getY() > Config.MAX_HEIGHT_BIOME_TEMP.get()) {
                temperature = (float) (double) Config.ABOVE_BIOME_TEMP.get();
            }
            return temperature;
        }

        public static boolean isPlayerInBiomeHeight(Player player) {
            return Config.MIN_HEIGHT_BIOME_TEMP.get() < player.getY() && player.getY() < Config.MAX_HEIGHT_BIOME_TEMP.get();
        }
    }

    public static class WaterModifier implements TemperatureModifier {
        @Override
        public float modifyTemperature(Player player, Biome biome, float temperature) {
            if (player.isInWater()) {
                temperature += Config.IN_WATER_TEMPERATURE_MODIFIER.get();
            }
            return temperature;
        }
    }

    public static class FreezingModifier implements TemperatureModifier {
        @Override
        public float modifyTemperature(Player player, Biome biome, float temperature) {
            if (player.isFreezing()) {
                temperature += Config.FREEZING_TEMPERATURE_MODIFIER.get();
            }
            return temperature;
        }
    }

    public static class LavaModifier implements TemperatureModifier {
        @Override
        public float modifyTemperature(Player player, Biome biome, float temperature) {
            if (player.isInLava()) {
                temperature += Config.IN_LAVA_TEMPERATURE_MODIFIER.get();
            }
            return temperature;
        }
    }

    public static class FireModifier implements TemperatureModifier {
        @Override
        public float modifyTemperature(Player player, Biome biome, float temperature) {
            if (player.isOnFire()) {
                temperature += Config.ON_FIRE_TEMPERATURE_MODIFIER.get();
            }
            return temperature;
        }
    }

    public static class NightModifier implements TemperatureModifier {
        @Override
        public float modifyTemperature(Player player, Biome biome, float temperature) {
            if (player.level().isNight() && HeightBiomeModifier.isPlayerInBiomeHeight(player)) {
                temperature += Config.NIGHT_TEMPERATURE_MODIFIER.get();
            }
            return temperature;
        }
    }

    public static class RainModifier implements TemperatureModifier {
        @Override
        public float modifyTemperature(Player player, Biome biome, float temperature) {
            if (player.level().isRaining() && HeightBiomeModifier.isPlayerInBiomeHeight(player)) {
                temperature += Config.RAIN_MODIFIER.get();
            }
            return temperature;
        }
    }

    public static class VanillaBlocksModifier implements TemperatureModifier {
        @Override
        public float modifyTemperature(Player player, Biome biome, float temperature) {
            temperature += BlockFinder.checkAndCalculateTemperatureModifier(player.level(), player.blockPosition(), Blocks.FIRE, Config.FIRE_RANGE.get(), Config.FIRE_BASE_TEMPERATURE_MODIFIER.get(), Config.FIRE_TEMPERATURE_FALLOFF.get());
            temperature += BlockFinder.checkAndCalculateTemperatureModifier(player.level(), player.blockPosition(), Blocks.LAVA, Config.LAVA_RANGE.get(), Config.LAVA_BASE_TEMPERATURE_MODIFIER.get(), Config.LAVA_TEMPERATURE_FALLOFF.get());
            temperature += BlockFinder.checkAndCalculateTemperatureModifier(player.level(), player.blockPosition(), Blocks.TORCH, Config.TORCH_RANGE.get(), Config.TORCH_BASE_TEMPERATURE_MODIFIER.get(), Config.TORCH_TEMPERATURE_FALLOFF.get());
            temperature += BlockFinder.checkAndCalculateTemperatureModifier(player.level(), player.blockPosition(), Blocks.MAGMA_BLOCK, Config.FIRE_RANGE.get(), Config.FIRE_BASE_TEMPERATURE_MODIFIER.get(), Config.FIRE_TEMPERATURE_FALLOFF.get());
            return temperature;
        }
    }

}
