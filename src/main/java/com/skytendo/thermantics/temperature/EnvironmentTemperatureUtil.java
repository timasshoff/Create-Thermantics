package com.skytendo.thermantics.temperature;

import com.skytendo.thermantics.temperature.modifiers.LevelTemperatureModifier;
import com.skytendo.thermantics.temperature.modifiers.PlayerTemperatureModifier;
import com.skytendo.thermantics.temperature.modifiers.TemperatureModifierRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;

public class EnvironmentTemperatureUtil {

    public static float calculateLevelEnvironmentTemperature(BlockPos pos, Level level) {
        float temperature = 0.0f;
        for (LevelTemperatureModifier modifier : TemperatureModifierRegistry.levelModifiers) {
            temperature = modifier.modifyTemperature(pos, level, temperature);
        }
        return Math.min(Math.max(temperature, 0.0f), 60.0f);
    }

    public static float calculatePlayerLevelEnvironmentTemperature(Biome biome, Player player) {
        float temperature = 0.0f;
        for (LevelTemperatureModifier modifier : TemperatureModifierRegistry.levelModifiers) {
            temperature = modifier.modifyTemperature(player.blockPosition(), player.level(), temperature);
        }
        for (PlayerTemperatureModifier modifier : TemperatureModifierRegistry.playerModifiers) {
            temperature = modifier.modifyTemperature(player, biome, temperature);
        }
        return Math.min(Math.max(temperature, 0.0f), 60.0f);
    }

    public static float getBiomeTemperature(Biome biome) {
        return Math.min(Math.max((18.52f * biome.getBaseTemperature() + 12.96f), 0.0f), 50.0f);
    }
}
