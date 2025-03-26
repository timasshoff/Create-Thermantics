package com.skytendo.thermantics.temperature.modifiers;

import java.util.ArrayList;
import java.util.List;

public class TemperatureModifierRegistry {

    public static final List<TemperatureModifier> modifiers = new ArrayList<>();

    public static void registerModifier(TemperatureModifier modifier) {
        modifiers.add(modifier);
    }

    public static void registerModifiers() {
        // Register all modifiers
        TemperatureModifierRegistry.registerModifier(new BasicModifiers.HeightBiomeModifier());
        TemperatureModifierRegistry.registerModifier(new BasicModifiers.WaterModifier());
        TemperatureModifierRegistry.registerModifier(new BasicModifiers.FreezingModifier());
        TemperatureModifierRegistry.registerModifier(new BasicModifiers.LavaModifier());
        TemperatureModifierRegistry.registerModifier(new BasicModifiers.FireModifier());
        TemperatureModifierRegistry.registerModifier(new BasicModifiers.NightModifier());
        TemperatureModifierRegistry.registerModifier(new BasicModifiers.RainModifier());
        TemperatureModifierRegistry.registerModifier(new BasicModifiers.VanillaBlocksModifier());
        TemperatureModifierRegistry.registerModifier(new EncasedFanTemperatureModifier());
        TemperatureModifierRegistry.registerModifier(new ThermalExchangerTemperatureModifier());
    }
}
