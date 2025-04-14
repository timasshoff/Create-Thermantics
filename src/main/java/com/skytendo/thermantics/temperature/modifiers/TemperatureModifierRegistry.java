package com.skytendo.thermantics.temperature.modifiers;

import java.util.ArrayList;
import java.util.List;

public class TemperatureModifierRegistry {

    public static final List<PlayerTemperatureModifier> playerModifiers = new ArrayList<>();
    public static final List<LevelTemperatureModifier> levelModifiers = new ArrayList<>();

    public static void registerPlayerModifier(PlayerTemperatureModifier modifier) {
        playerModifiers.add(modifier);
    }

    public static void registerLevelModifier(LevelTemperatureModifier modifier) {
        levelModifiers.add(modifier);
    }

    public static void registerModifiers() {
        // Register all modifiers
        TemperatureModifierRegistry.registerLevelModifier(new BasicModifiers.HeightBiomeModifier());
        TemperatureModifierRegistry.registerPlayerModifier(new BasicModifiers.WaterModifier());
        TemperatureModifierRegistry.registerPlayerModifier(new BasicModifiers.FreezingModifier());
        TemperatureModifierRegistry.registerPlayerModifier(new BasicModifiers.LavaModifier());
        TemperatureModifierRegistry.registerPlayerModifier(new BasicModifiers.FireModifier());
        TemperatureModifierRegistry.registerLevelModifier(new BasicModifiers.NightModifier());
        TemperatureModifierRegistry.registerLevelModifier(new BasicModifiers.RainModifier());
        TemperatureModifierRegistry.registerLevelModifier(new BasicModifiers.VanillaBlocksModifierPlayer());
        TemperatureModifierRegistry.registerLevelModifier(new CampfireModifierPlayer());
        TemperatureModifierRegistry.registerPlayerModifier(new ArmorPlayerTemperatureModifier());
        TemperatureModifierRegistry.registerLevelModifier(new AbstractFurnacePlayerTemperatureModifier());
        TemperatureModifierRegistry.registerLevelModifier(new EncasedFanPlayerTemperatureModifier());
        TemperatureModifierRegistry.registerLevelModifier(new BlazeBurnerPlayerTemperatureModifier());
        TemperatureModifierRegistry.registerLevelModifier(new FluidTankPlayerTemperatureModifier());
        TemperatureModifierRegistry.registerLevelModifier(new ThermalExchangerPlayerTemperatureModifier());
    }
}
