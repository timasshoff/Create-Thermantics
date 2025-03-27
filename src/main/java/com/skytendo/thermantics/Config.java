package com.skytendo.thermantics;


import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;


// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Forge's config APIs
@Mod.EventBusSubscriber(modid = Thermantics.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config
{
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    public static final ForgeConfigSpec.DoubleValue UPDATE_CHANCE_FREEZING = BUILDER
            .comment("The chance of updating the player's temperature when they are freezing")
            .defineInRange("updateChanceFreezing", 0.08, 0.0, 1.0);

    public static final ForgeConfigSpec.DoubleValue UPDATE_CHANCE_FIRE = BUILDER
            .comment("The chance of updating the player's temperature when they are on fire")
            .defineInRange("updateChanceFire", 0.08, 0.0, 1.0);

    public static final ForgeConfigSpec.DoubleValue UPDATE_CHANCE_LAVA = BUILDER
            .comment("The chance of updating the player's temperature when they are in lava")
            .defineInRange("updateChanceLava", 0.15, 0.0, 1.0);

    public static final ForgeConfigSpec.DoubleValue UPDATE_CHANCE_DEFAULT = BUILDER
            .comment("The chance of updating the player's temperature when they are in a normal environment")
            .defineInRange("updateChanceDefault", 0.005, 0.0, 1.0);

    public static final ForgeConfigSpec.DoubleValue TEMP_DIFFERENCE_SNAP = BUILDER
            .comment("Defines how close to the environment temperature the player temp has to be to snap to the target temperature")
            .defineInRange("tempDifferenceSnap", 1.0, 0.0, 60.0);

    public static final ForgeConfigSpec.DoubleValue TEMP_ADJUSTMENT_SPEED = BUILDER
            .comment("Defines how quickly the player temperature adjusts to the environment temperature")
            .defineInRange("tempAdjustmentSpeed", 0.6, 0.0, 2.5);

    public static final ForgeConfigSpec.IntValue MIN_HEIGHT_BIOME_TEMP = BUILDER
            .comment("Defines the minimum height for the biome temperature to be applied")
            .defineInRange("minHeightBiomeTemp", 45, -64, 320);

    public static final ForgeConfigSpec.IntValue MAX_HEIGHT_BIOME_TEMP = BUILDER
            .comment("Defines the maximum height for the biome temperature to be applied")
            .defineInRange("maxHeightBiomeTemp", 155, -64, 320);

    public static final ForgeConfigSpec.DoubleValue BELOW_BIOME_TEMP = BUILDER
            .comment("The temperature below the min biome height")
            .defineInRange("belowBiomeTemp", 25.0, 0, 60);

    public static final ForgeConfigSpec.DoubleValue ABOVE_BIOME_TEMP = BUILDER
            .comment("The temperature above the min biome height")
            .defineInRange("aboveBiomeTemp", 11.0, 0, 60);

    public static final ForgeConfigSpec.DoubleValue IN_WATER_TEMPERATURE_MODIFIER = BUILDER
            .comment("Defines how being in water modifies the environment temperature")
            .defineInRange("waterTemperatureModifier", -9.0, -60, 60);

    public static final ForgeConfigSpec.DoubleValue FREEZING_TEMPERATURE_MODIFIER = BUILDER
            .comment("Defines how freezing modifies the environment temperature")
            .defineInRange("freezingTemperatureModifier", -18.0, -60, 60);

    public static final ForgeConfigSpec.DoubleValue IN_LAVA_TEMPERATURE_MODIFIER = BUILDER
            .comment("Defines how being in lava modifies the environment temperature")
            .defineInRange("lavaTemperatureModifier", 55.0, -60, 60);

    public static final ForgeConfigSpec.DoubleValue ON_FIRE_TEMPERATURE_MODIFIER = BUILDER
            .comment("Defines how being on fire modifies the environment temperature")
            .defineInRange("fireTemperatureModifier", 30.0, -60, 60);

    public static final ForgeConfigSpec.DoubleValue NIGHT_TEMPERATURE_MODIFIER = BUILDER
            .comment("Defines how the night modifies the environment temperature")
            .defineInRange("nightTemperatureModifier", -8.0, -60, 60);

    public static final ForgeConfigSpec.DoubleValue RAIN_MODIFIER = BUILDER
            .comment("Defines how rain modifies the environment temperature")
            .defineInRange("rainTemperatureModifier", -6.0, -60, 60);

    public static final ForgeConfigSpec.DoubleValue LOW_ISOLATION = BUILDER
            .comment("Defines how much heat gets absorbed by low isolating blocks")
            .defineInRange("lowIsolation", 0.5, 0, 10);

    public static final ForgeConfigSpec.DoubleValue DEFAULT_ISOLATION = BUILDER
            .comment("Defines how much heat gets absorbed by default blocks")
            .defineInRange("defaultIsolation", 1.25, 0, 10);

    public static final ForgeConfigSpec.DoubleValue HIGH_ISOLATION = BUILDER
            .comment("Defines how much heat gets absorbed by highly isolating blocks")
            .defineInRange("highIsolation", 2.5, 0, 10);

    public static final ForgeConfigSpec.DoubleValue EXTREME_ISOLATION = BUILDER
            .comment("Defines how much heat gets absorbed by extremely isolating blocks")
            .defineInRange("extremeIsolation", 4.5, 0, 10);

    // Temp Effects

    public static final ForgeConfigSpec.IntValue NEW_TEMP_CLEMENCY_DURATION = BUILDER
            .comment("Defines how many ticks the player is immune to new temperature effects after a temperature change")
            .defineInRange("newTempClemencyDuration", 600, 0, 25000);

    // Thermal Exchanger

    public static final ForgeConfigSpec.IntValue THERMAL_EXCHANGER_CONSUMPTION = BUILDER
            .comment("Defines how the maximal amount fuel the thermal exchanger consumes")
            .defineInRange("thermalExchangerConsumption", 100, 1, 1000);

    public static final ForgeConfigSpec.DoubleValue THERMAL_EXCHANGER_TICKS_PER_MB = BUILDER
            .comment("Defines the ratio between consumed fuel and ticks")
            .defineInRange("thermalExchangerRatio", 2.0, 0.1, 10.0);

    public static final ForgeConfigSpec.IntValue THERMAL_EXCHANGER_RANGE = BUILDER
            .comment("Defines the temperature range")
            .defineInRange("thermalExchangerRange", 15, 0, 50);

    public static final ForgeConfigSpec.IntValue THERMAL_EXCHANGER_HEAT_BASE_TEMPERATURE_MODIFIER = BUILDER
            .comment("Defines the temperature modifier when as close as possible")
            .defineInRange("thermalExchangerHeatBaseTemperatureModifier", 10, -60, 60);

    public static final ForgeConfigSpec.DoubleValue THERMAL_EXCHANGER_HEAT_TEMPERATURE_FALLOFF = BUILDER
            .comment("Defines the temperature falloff")
            .defineInRange("thermalExchangerHeatTemperatureFalloff", 0.0, 0.0, 2.5);

    public static final ForgeConfigSpec.IntValue THERMAL_EXCHANGER_SUPERHEAT_BASE_TEMPERATURE_MODIFIER = BUILDER
            .comment("Defines the temperature modifier when as close as possible")
            .defineInRange("thermalExchangerSuperheatBaseTemperatureModifier", 30, -60, 60);

    public static final ForgeConfigSpec.DoubleValue THERMAL_EXCHANGER_SUPERHEAT_TEMPERATURE_FALLOFF = BUILDER
            .comment("Defines the temperature falloff")
            .defineInRange("thermalExchangerSuperheatTemperatureFalloff", 0.0, 0.0, 2.5);

    public static final ForgeConfigSpec.IntValue THERMAL_EXCHANGER_COOL_BASE_TEMPERATURE_MODIFIER = BUILDER
            .comment("Defines the temperature modifier when as close as possible")
            .defineInRange("thermalExchangerCoolBaseTemperatureModifier", -25, -60, 60);

    public static final ForgeConfigSpec.DoubleValue THERMAL_EXCHANGER_COOL_TEMPERATURE_FALLOFF = BUILDER
            .comment("Defines the temperature falloff")
            .defineInRange("thermalExchangerCoolTemperatureFalloff", 0.0, 0.0, 2.5);

    // Campfire

    public static final ForgeConfigSpec.IntValue CAMPFIRE_RANGE = BUILDER
            .comment("Defines how the heat range")
            .defineInRange("campfireRange", 5, 0, 50);

    public static final ForgeConfigSpec.DoubleValue CAMPFIRE_BASE_TEMPERATURE_MODIFIER = BUILDER
            .comment("Defines the temperature modifier when as close as possible")
            .defineInRange("campfireBaseTemperatureModifier", 22.0, 0, 60);

    public static final ForgeConfigSpec.DoubleValue CAMPFIRE_TEMPERATURE_FALLOFF = BUILDER
            .comment("Defines the temperature falloff")
            .defineInRange("campfireBaseTemperatureFalloff", 0.35, 0, 2.5);

    // Fire

    public static final ForgeConfigSpec.IntValue FIRE_RANGE = BUILDER
            .comment("Defines how the heat range")
            .defineInRange("fireRange", 6, 0, 50);

    public static final ForgeConfigSpec.DoubleValue FIRE_BASE_TEMPERATURE_MODIFIER = BUILDER
            .comment("Defines the temperature modifier when as close as possible")
            .defineInRange("fireBaseTemperatureModifier", 35.0, 0, 60);

    public static final ForgeConfigSpec.DoubleValue FIRE_TEMPERATURE_FALLOFF = BUILDER
            .comment("Defines the temperature falloff")
            .defineInRange("fireBaseTemperatureFalloff", 0.55, 0, 2.5);

    // Lava

    public static final ForgeConfigSpec.IntValue LAVA_RANGE = BUILDER
            .comment("Defines how the heat range")
            .defineInRange("lavaRange", 8, 0, 50);

    public static final ForgeConfigSpec.DoubleValue LAVA_BASE_TEMPERATURE_MODIFIER = BUILDER
            .comment("Defines the temperature modifier when as close as possible")
            .defineInRange("lavaBaseTemperatureModifier", 55.0, 0, 60);

    public static final ForgeConfigSpec.DoubleValue LAVA_TEMPERATURE_FALLOFF = BUILDER
            .comment("Defines the temperature falloff")
            .defineInRange("lavaBaseTemperaturFalloff", 0.55, 0, 2.5);

    // Torch

    public static final ForgeConfigSpec.IntValue TORCH_RANGE = BUILDER
            .comment("Defines how the heat range")
            .defineInRange("torchRange", 2, 0, 50);

    public static final ForgeConfigSpec.DoubleValue TORCH_BASE_TEMPERATURE_MODIFIER = BUILDER
            .comment("Defines the temperature modifier when as close as possible")
            .defineInRange("torchBaseTemperatureModifier", 12.0, 0, 60);

    public static final ForgeConfigSpec.DoubleValue TORCH_TEMPERATURE_FALLOFF = BUILDER
            .comment("Defines the temperature falloff")
            .defineInRange("torchBaseTemperaturFalloff", 0.42, 0, 2.5);

    // Redstone Torch

    public static final ForgeConfigSpec.IntValue REDSTONE_TORCH_RANGE = BUILDER
            .comment("Defines how the heat range")
            .defineInRange("redstoneTorchRange", 2, 0, 50);

    public static final ForgeConfigSpec.DoubleValue REDSTONE_TORCH_BASE_TEMPERATURE_MODIFIER = BUILDER
            .comment("Defines the temperature modifier when as close as possible")
            .defineInRange("redstoneTorchBaseTemperatureModifier", 8.0, 0, 60);

    public static final ForgeConfigSpec.DoubleValue REDSTONE_TORCH_TEMPERATURE_FALLOFF = BUILDER
            .comment("Defines the temperature falloff")
            .defineInRange("redstoneTorchBaseTemperaturFalloff", 0.55, 0, 2.5);

    // Encased Fan

    public static final ForgeConfigSpec.DoubleValue FAN_SMOKING_BASE_TEMPERATURE_MODIFIER = BUILDER
            .comment("Defines the temperature modifier when as close as possible")
            .defineInRange("fanSmokingTemperatureModifier", 3.5, -60, 60);

    public static final ForgeConfigSpec.DoubleValue FAN_SMOKING_TEMPERATURE_FALLOFF = BUILDER
            .comment("Defines the temperature falloff")
            .defineInRange("fanSmokingTemperatureFalloff", 0.2, 0, 2.5);

    public static final ForgeConfigSpec.DoubleValue FAN_BLASTING_BASE_TEMPERATURE_MODIFIER = BUILDER
            .comment("Defines the temperature modifier when as close as possible")
            .defineInRange("fanBlastingTemperatureModifier", 5.5, -60, 60);

    public static final ForgeConfigSpec.DoubleValue FAN_BLASTING_TEMPERATURE_FALLOFF = BUILDER
            .comment("Defines the temperature falloff")
            .defineInRange("fanBlastingTemperatureFalloff", 0.2, 0, 2.5);

    public static final ForgeConfigSpec.DoubleValue FAN_SPLASHING_BASE_TEMPERATURE_MODIFIER = BUILDER
            .comment("Defines the temperature modifier when as close as possible")
            .defineInRange("fanSplashingTemperatureModifier", -3.5, -60, 60);

    public static final ForgeConfigSpec.DoubleValue FAN_SPLASHING_TEMPERATURE_FALLOFF = BUILDER
            .comment("Defines the temperature falloff")
            .defineInRange("fanSplashingTemperatureFalloff", 0.2, 0, 2.5);


    static final ForgeConfigSpec SPEC = BUILDER.build();

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {

    }
}
