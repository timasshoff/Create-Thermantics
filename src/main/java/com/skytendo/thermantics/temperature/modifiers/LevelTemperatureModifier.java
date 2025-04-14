package com.skytendo.thermantics.temperature.modifiers;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;

public interface LevelTemperatureModifier {
    float modifyTemperature(BlockPos pos, Level level, float temperature);
}
