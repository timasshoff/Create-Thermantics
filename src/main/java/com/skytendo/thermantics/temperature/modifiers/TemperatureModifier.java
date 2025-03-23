package com.skytendo.thermantics.temperature.modifiers;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.biome.Biome;

public interface TemperatureModifier {
    float modifyTemperature(Player player, Biome biome, float temperature);
}
