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
            .defineInRange("minHeightBiomeTemp", 155, -64, 320);

    public static final ForgeConfigSpec.DoubleValue BELOW_BIOME_TEMP = BUILDER
            .comment("The temperature below the min biome height")
            .defineInRange("belowBiomeTemp", 25.0, 0, 60);

    public static final ForgeConfigSpec.DoubleValue ABOVE_BIOME_TEMP = BUILDER
            .comment("The temperature above the min biome height")
            .defineInRange("belowBiomeTemp", 11.0, 0, 60);

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

    // Campfire

    public static final ForgeConfigSpec.IntValue CAMPFIRE_RANGE = BUILDER
            .comment("Defines how the heat range")
            .defineInRange("campfireRange", 5, 0, 50);

    public static final ForgeConfigSpec.DoubleValue CAMPFIRE_BASE_TEMPERATURE_MODIFIER = BUILDER
            .comment("Defines the temperature modifier when as close as possible")
            .defineInRange("campfireBaseTemperatureModifier", 22.0, 0, 60);

    public static final ForgeConfigSpec.DoubleValue CAMPFIRE_TEMPERATURE_FALLOFF = BUILDER
            .comment("Defines the temperature falloff")
            .defineInRange("campfireBaseTemperatureModifier", 0.35, 0, 2.5);

    // Fire

    public static final ForgeConfigSpec.IntValue FIRE_RANGE = BUILDER
            .comment("Defines how the heat range")
            .defineInRange("fireRange", 6, 0, 50);

    public static final ForgeConfigSpec.DoubleValue FIRE_BASE_TEMPERATURE_MODIFIER = BUILDER
            .comment("Defines the temperature modifier when as close as possible")
            .defineInRange("fireBaseTemperatureModifier", 35.0, 0, 60);

    public static final ForgeConfigSpec.DoubleValue FIRE_TEMPERATURE_FALLOFF = BUILDER
            .comment("Defines the temperature falloff")
            .defineInRange("fireBaseTemperatureModifier", 0.55, 0, 2.5);

    // Lava

    public static final ForgeConfigSpec.IntValue LAVA_RANGE = BUILDER
            .comment("Defines how the heat range")
            .defineInRange("lavaRange", 8, 0, 50);

    public static final ForgeConfigSpec.DoubleValue LAVA_BASE_TEMPERATURE_MODIFIER = BUILDER
            .comment("Defines the temperature modifier when as close as possible")
            .defineInRange("lavaBaseTemperatureModifier", 55.0, 0, 60);

    public static final ForgeConfigSpec.DoubleValue LAVA_TEMPERATURE_FALLOFF = BUILDER
            .comment("Defines the temperature falloff")
            .defineInRange("lavaBaseTemperatureModifier", 0.55, 0, 2.5);


    static final ForgeConfigSpec SPEC = BUILDER.build();

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {

    }
}
