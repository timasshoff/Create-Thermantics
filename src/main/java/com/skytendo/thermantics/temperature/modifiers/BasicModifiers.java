package com.skytendo.thermantics.temperature.modifiers;

import com.skytendo.thermantics.Config;
import com.skytendo.thermantics.temperature.EnvironmentTemperatureUtil;
import com.skytendo.thermantics.util.BlockFinder;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;

public class BasicModifiers {

    public static class HeightBiomeModifier implements LevelTemperatureModifier {
        public static boolean isInBiomeHeight(BlockPos pos) {
            return Config.MIN_HEIGHT_BIOME_TEMP.get() < pos.getY() && pos.getY() < Config.MAX_HEIGHT_BIOME_TEMP.get();
        }

        @Override
        public float modifyTemperature(BlockPos pos, Level level, float temperature) {
            if (level.dimension() == Level.NETHER) {
                return EnvironmentTemperatureUtil.getBiomeTemperature(level.getBiome(pos).get()) + 1.5f;
            }
            if (level.dimension() == Level.END) {
                return EnvironmentTemperatureUtil.getBiomeTemperature(level.getBiome(pos).get());
            }
            if (Config.MIN_HEIGHT_BIOME_TEMP.get() < pos.getY() && pos.getY() < Config.MAX_HEIGHT_BIOME_TEMP.get()) {
                temperature = EnvironmentTemperatureUtil.getBiomeTemperature(level.getBiome(pos).get());
            } else if (pos.getY() < Config.MIN_HEIGHT_BIOME_TEMP.get()) {
                temperature = (float) (double) Config.BELOW_BIOME_TEMP.get();
            } else if (pos.getY() > Config.MAX_HEIGHT_BIOME_TEMP.get()) {
                temperature = (float) (double) Config.ABOVE_BIOME_TEMP.get();
            }
            return temperature;
        }
    }

    public static class WaterModifier implements PlayerTemperatureModifier {
        @Override
        public float modifyTemperature(Player player, Biome biome, float temperature) {
            if (player.isInWater()) {
                temperature += Config.IN_WATER_TEMPERATURE_MODIFIER.get();
            }
            return temperature;
        }
    }

    public static class FreezingModifier implements PlayerTemperatureModifier {
        @Override
        public float modifyTemperature(Player player, Biome biome, float temperature) {
            if (player.isFreezing()) {
                temperature += Config.FREEZING_TEMPERATURE_MODIFIER.get();
            }
            return temperature;
        }
    }

    public static class LavaModifier implements PlayerTemperatureModifier {
        @Override
        public float modifyTemperature(Player player, Biome biome, float temperature) {
            if (player.isInLava()) {
                temperature += Config.IN_LAVA_TEMPERATURE_MODIFIER.get();
            }
            return temperature;
        }
    }

    public static class FireModifier implements PlayerTemperatureModifier {
        @Override
        public float modifyTemperature(Player player, Biome biome, float temperature) {
            if (player.isOnFire()) {
                temperature += Config.ON_FIRE_TEMPERATURE_MODIFIER.get();
            }
            return temperature;
        }
    }

    public static class NightModifier implements LevelTemperatureModifier {
        @Override
        public float modifyTemperature(BlockPos pos, Level level, float temperature) {
            if (level.isNight() && HeightBiomeModifier.isInBiomeHeight(pos)) {
                temperature += Config.NIGHT_TEMPERATURE_MODIFIER.get();
            }
            return temperature;
        }
    }

    public static class RainModifier implements LevelTemperatureModifier {
        @Override
        public float modifyTemperature(BlockPos pos, Level level, float temperature) {
            if (level.isRaining() && HeightBiomeModifier.isInBiomeHeight(pos)) {
                temperature += Config.RAIN_MODIFIER.get();
            }
            return temperature;
        }
    }

    public static class VanillaBlocksModifierPlayer implements LevelTemperatureModifier {
        @Override
        public float modifyTemperature(BlockPos pos, Level level, float temperature) {
            temperature += BlockFinder.checkAndCalculateTemperatureModifier(level, pos, Blocks.FIRE, Config.FIRE_RANGE.get(), Config.FIRE_BASE_TEMPERATURE_MODIFIER.get(), Config.FIRE_TEMPERATURE_FALLOFF.get());
            temperature += BlockFinder.checkAndCalculateTemperatureModifier(level, pos, Blocks.LAVA, Config.LAVA_RANGE.get(), Config.LAVA_BASE_TEMPERATURE_MODIFIER.get(), Config.LAVA_TEMPERATURE_FALLOFF.get());
            temperature += BlockFinder.checkAndCalculateTemperatureModifier(level, pos, Blocks.TORCH, Config.TORCH_RANGE.get(), Config.TORCH_BASE_TEMPERATURE_MODIFIER.get(), Config.TORCH_TEMPERATURE_FALLOFF.get());
            temperature += BlockFinder.checkAndCalculateTemperatureModifier(level, pos, Blocks.MAGMA_BLOCK, Config.FIRE_RANGE.get(), Config.FIRE_BASE_TEMPERATURE_MODIFIER.get(), Config.FIRE_TEMPERATURE_FALLOFF.get());
            return temperature;
        }
    }

}
