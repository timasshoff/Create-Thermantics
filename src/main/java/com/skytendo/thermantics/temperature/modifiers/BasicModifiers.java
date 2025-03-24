package com.skytendo.thermantics.temperature.modifiers;

import com.skytendo.thermantics.Config;
import com.skytendo.thermantics.temperature.PlayerTemperatureManager;
import com.skytendo.thermantics.util.BlockFinder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import org.checkerframework.checker.units.qual.C;

public class BasicModifiers {

    public static class HeightBiomeModifier implements TemperatureModifier {
        @Override
        public float modifyTemperature(Player player, Biome biome, float temperature) {
            System.out.println(Config.MIN_HEIGHT_BIOME_TEMP.get());
            System.out.println(player.getY());
            System.out.println(Config.MAX_HEIGHT_BIOME_TEMP.get());
            if (Config.MIN_HEIGHT_BIOME_TEMP.get() < player.getY() && player.getY() < Config.MAX_HEIGHT_BIOME_TEMP.get()) {
                temperature = PlayerTemperatureManager.getBiomeTemperature(biome);
            } else if (player.getY() < Config.MIN_HEIGHT_BIOME_TEMP.get()) {
                temperature = (float) (double) Config.BELOW_BIOME_TEMP.get();
            } else if (player.getY() > Config.MAX_HEIGHT_BIOME_TEMP.get()) {
                temperature = (float) (double) Config.ABOVE_BIOME_TEMP.get();
            }
            return temperature;
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
            if (player.level().isNight()) {
                temperature += Config.NIGHT_TEMPERATURE_MODIFIER.get();
            }
            return temperature;
        }
    }

    public static class VanillaBlocksModifier implements TemperatureModifier {
        @Override
        public float modifyTemperature(Player player, Biome biome, float temperature) {
            temperature += BlockFinder.checkAndCalculateTemperatureModifier(player.level(), player.blockPosition(), Blocks.CAMPFIRE, Config.CAMPFIRE_RANGE.get(), Config.CAMPFIRE_BASE_TEMPERATURE_MODIFIER.get(), Config.CAMPFIRE_TEMPERATURE_FALLOFF.get());
            temperature += BlockFinder.checkAndCalculateTemperatureModifier(player.level(), player.blockPosition(), Blocks.FIRE, Config.FIRE_RANGE.get(), Config.FIRE_BASE_TEMPERATURE_MODIFIER.get(), Config.FIRE_TEMPERATURE_FALLOFF.get());
            temperature += BlockFinder.checkAndCalculateTemperatureModifier(player.level(), player.blockPosition(), Blocks.LAVA, Config.LAVA_RANGE.get(), Config.LAVA_BASE_TEMPERATURE_MODIFIER.get(), Config.LAVA_TEMPERATURE_FALLOFF.get());
            temperature += BlockFinder.checkAndCalculateTemperatureModifier(player.level(), player.blockPosition(), Blocks.TORCH, Config.TORCH_RANGE.get(), Config.TORCH_BASE_TEMPERATURE_MODIFIER.get(), Config.TORCH_TEMPERATURE_FALLOFF.get());
            temperature += BlockFinder.checkAndCalculateTemperatureModifier(player.level(), player.blockPosition(), Blocks.REDSTONE_TORCH, Config.REDSTONE_TORCH_RANGE.get(), Config.REDSTONE_TORCH_BASE_TEMPERATURE_MODIFIER.get(), Config.REDSTONE_TORCH_TEMPERATURE_FALLOFF.get());
            return temperature;
        }
    }

}
