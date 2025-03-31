package com.skytendo.thermantics;


import net.minecraftforge.common.ForgeConfig;
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

    public static final ForgeConfigSpec.BooleanValue DEBUG_INFO = BUILDER
            .define("debugInfo", false);

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
            .defineInRange("updateChanceDefault", 0.0037, 0.0, 1.0);

    public static final ForgeConfigSpec.DoubleValue TEMP_DIFFERENCE_SNAP = BUILDER
            .comment("Defines how close to the environment temperature the player temp has to be to snap to the target temperature")
            .defineInRange("tempDifferenceSnap", 1.0, 0.0, 60.0);

    public static final ForgeConfigSpec.DoubleValue TEMP_ADJUSTMENT_SPEED = BUILDER
            .comment("Defines how quickly the player temperature adjusts to the environment temperature")
            .defineInRange("tempAdjustmentSpeed", 0.5, 0.0, 2.5);

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
            .defineInRange("waterTemperatureModifier", -12.0, -60, 60);

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

    public static final ForgeConfigSpec.BooleanValue DEATH_CLEMENCY = BUILDER
            .comment("Defines if the player is immune to temperature effects after death")
            .define("deathClemency", true);

    public static final ForgeConfigSpec.IntValue DEATH_CLEMENCY_DURATION = BUILDER
            .comment("Defines how long in ticks the player is immune to temperature effects after death")
            .defineInRange("deathClemencyDuration", 1200, 0, 25000);

    public static final ForgeConfigSpec.IntValue NEW_TEMP_CLEMENCY_DURATION = BUILDER
            .comment("Defines how many ticks the player is immune to new temperature effects after a temperature change")
            .defineInRange("newTempClemencyDuration", 600, 0, 25000);

    // Hyperthermia

    public static final ForgeConfigSpec.DoubleValue HYPERTHERMIA_FIRE_CHANCE = BUILDER
            .comment("Defines how likely it is to set the player on fire when having hyperthermia")
            .defineInRange("hyperthermiaFireChance", 0.005, 0, 1);

    public static final ForgeConfigSpec.DoubleValue HYPERTHERMIA_1_NAUSEA_CHANCE = BUILDER
            .comment("Defines how likely it is to get nausea when having hyperthermia level 1")
            .defineInRange("hyperthermia1NauseaChance", 0.005, 0, 10);

    public static final ForgeConfigSpec.IntValue HYPERTHERMIA_1_NAUSEA_MAX_LENGTH = BUILDER
            .comment("Defines how likely it is to get nausea when having hyperthermia level 1")
            .defineInRange("hyperthermia1NauseaMaxLength", 200, 0, 25000);

    public static final ForgeConfigSpec.DoubleValue HYPERTHERMIA_2_NAUSEA_CHANCE = BUILDER
            .comment("Defines how likely it is to get nausea when having hyperthermia level 2")
            .defineInRange("hyperthermia2NauseaChance", 0.0065, 0, 10);

    public static final ForgeConfigSpec.IntValue HYPERTHERMIA_2_NAUSEA_MAX_LENGTH = BUILDER
            .comment("Defines how likely it is to get nausea when having hyperthermia level 1")
            .defineInRange("hyperthermia2NauseaMaxLength", 300, 0, 25000);

    public static final ForgeConfigSpec.DoubleValue HYPERTHERMIA_1_MINING_FATIGUE_CHANCE = BUILDER
            .comment("Defines how likely it is to get mining fatigue when having hyperthermia level 1")
            .defineInRange("hyperthermia1MiningFatigueChance", 0.0065, 0, 10);

    public static final ForgeConfigSpec.IntValue HYPERTHERMIA_1_MINING_FATIGUE_MAX_LENGTH = BUILDER
            .comment("Defines how likely it is to get mining fatigue when having hyperthermia level 1")
            .defineInRange("hyperthermia1MiningFatigueMaxLength", 160, 0, 25000);

    public static final ForgeConfigSpec.DoubleValue HYPERTHERMIA_2_MINING_FATIGUE_CHANCE = BUILDER
            .comment("Defines how likely it is to get mining fatigue when having hyperthermia level 2")
            .defineInRange("hyperthermia2MiningFatigueChance", 0.0075, 0, 10);

    public static final ForgeConfigSpec.IntValue HYPERTHERMIA_2_MINING_FATIGUE_MAX_LENGTH = BUILDER
            .comment("Defines how likely it is to get mining fatigue when having hyperthermia level 1")
            .defineInRange("hyperthermia2MiningFatigueMaxLength", 220, 0, 25000);

    public static final ForgeConfigSpec.DoubleValue HYPERTHERMIA_1_WEAKNESS_CHANCE = BUILDER
            .comment("Defines how likely it is to get weakness when having hyperthermia level 1")
            .defineInRange("hyperthermia1WeaknessChance", 0.003, 0, 10);

    public static final ForgeConfigSpec.IntValue HYPERTHERMIA_1_WEAKNESS_MAX_LENGTH = BUILDER
            .comment("Defines how likely it is to get weakness when having hyperthermia level 1")
            .defineInRange("hyperthermia1WeaknessMaxLength", 160, 0, 25000);

    public static final ForgeConfigSpec.DoubleValue HYPERTHERMIA_2_WEAKNESS_CHANCE = BUILDER
            .comment("Defines how likely it is to get weakness when having hyperthermia level 2")
            .defineInRange("hyperthermia2WeaknessChance", 0.005, 0, 10);

    public static final ForgeConfigSpec.IntValue HYPERTHERMIA_2_WEAKNESS_MAX_LENGTH = BUILDER
            .comment("Defines how likely it is to get weakness when having hyperthermia level 1")
            .defineInRange("hyperthermia2WeaknessMaxLength", 220, 0, 25000);

    // Hypothermia

    public static final ForgeConfigSpec.DoubleValue HYPOTHERMIA_2_DAMAGE_CHANCE = BUILDER
            .comment("Defines how likely it is to take damage when having hypothermia level 2")
            .defineInRange("hypothermia2DamageChance", 0.005, 0, 1);

    public static final ForgeConfigSpec.DoubleValue HYPOTHERMIA_3_DAMAGE_CHANCE = BUILDER
            .comment("Defines how likely it is to take damage when having hypothermia level 3")
            .defineInRange("hypothermia3DamageChance", 0.008, 0, 1);

    public static final ForgeConfigSpec.DoubleValue HYPOTHERMIA_1_WEAKNESS_CHANCE = BUILDER
            .comment("Defines how likely it is to get weakness when having hypothermia level 1")
            .defineInRange("hypothermia1WeaknessChance", 0.005, 0, 10);

    public static final ForgeConfigSpec.IntValue HYPOTHERMIA_1_WEAKNESS_MAX_LENGTH = BUILDER
            .comment("Defines how likely it is to get weakness when having hypothermia level 1")
            .defineInRange("hypothermia1WeaknessMaxLength", 200, 0, 25000);

    public static final ForgeConfigSpec.DoubleValue HYPOTHERMIA_2_WEAKNESS_CHANCE = BUILDER
            .comment("Defines how likely it is to get weakness when having hypothermia level 2")
            .defineInRange("hypothermia2WeaknessChance", 0.006, 0, 10);

    public static final ForgeConfigSpec.IntValue HYPOTHERMIA_2_WEAKNESS_MAX_LENGTH = BUILDER
            .comment("Defines how likely it is to get weakness when having hypothermia level 1")
            .defineInRange("hypothermia2WeaknessMaxLength", 260, 0, 25000);

    public static final ForgeConfigSpec.DoubleValue HYPOTHERMIA_1_MINING_FATIGUE_CHANCE = BUILDER
            .comment("Defines how likely it is to get mining fatigue when having hypothermia level 1")
            .defineInRange("hypothermia1MiningFatigueChance", 0.0045, 0, 10);

    public static final ForgeConfigSpec.IntValue HYPOTHERMIA_1_MINING_FATIGUE_MAX_LENGTH = BUILDER
            .comment("Defines how likely it is to get mining fatigue when having hypothermia level 1")
            .defineInRange("hypothermia1MiningFatigueMaxLength", 200, 0, 25000);

    public static final ForgeConfigSpec.DoubleValue HYPOTHERMIA_2_MINING_FATIGUE_CHANCE = BUILDER
            .comment("Defines how likely it is to get mining fatigue when having hypothermia level 2")
            .defineInRange("hypothermia2MiningFatigueChance", 0.005, 0, 10);

    public static final ForgeConfigSpec.IntValue HYPOTHERMIA_2_MINING_FATIGUE_MAX_LENGTH = BUILDER
            .comment("Defines how likely it is to get mining fatigue when having hypothermia level 1")
            .defineInRange("hypothermia2MiningFatigueMaxLength", 260, 0, 25000);

    public static final ForgeConfigSpec.DoubleValue HYPOTHERMIA_2_BLINDNESS_CHANCE = BUILDER
            .comment("Defines how likely it is to get blindness when having hypothermia level 2")
            .defineInRange("hypothermia1BlindnessChance", 0.0037, 0, 10);

    public static final ForgeConfigSpec.IntValue HYPOTHERMIA_2_BLINDNESS_MAX_LENGTH = BUILDER
            .comment("Defines how likely it is to get blindness when having hypothermia level 2")
            .defineInRange("hypothermia1BlindnessMaxLength", 120, 0, 25000);

    public static final ForgeConfigSpec.DoubleValue HYPOTHERMIA_3_BLINDNESS_CHANCE = BUILDER
            .comment("Defines how likely it is to get blindness when having hypothermia level 3")
            .defineInRange("hypothermia2BlindnessChance", 0.005, 0, 10);

    public static final ForgeConfigSpec.IntValue HYPOTHERMIA_3_BLINDNESS_MAX_LENGTH = BUILDER
            .comment("Defines how likely it is to get blindness when having hypothermia level 3")
            .defineInRange("hypothermia2BlindnessMaxLength", 200, 0, 25000);

    // Armor

    public static final ForgeConfigSpec.DoubleValue WARM_ARMOR = BUILDER
            .comment("Defines how warm warm armor is")
            .defineInRange("warmArmor", 0.75, -10, 10);

    public static final ForgeConfigSpec.DoubleValue VERY_WARM_ARMOR = BUILDER
            .comment("Defines how warm very warm armor is")
            .defineInRange("veryWarmArmor", 2.25, -10, 10);

    public static final ForgeConfigSpec.DoubleValue COLD_ARMOR = BUILDER
            .comment("Defines how much cold cold armor is")
            .defineInRange("coldArmor", -1.5, -10, 10);

    // Thermal Exchanger

    public static final ForgeConfigSpec.IntValue THERMAL_EXCHANGER_CONSUMPTION = BUILDER
            .comment("Defines how the maximal amount fuel the thermal exchanger consumes")
            .defineInRange("thermalExchangerConsumption", 500, 1, 1000);

    public static final ForgeConfigSpec.DoubleValue THERMAL_EXCHANGER_TICKS_PER_MB = BUILDER
            .comment("Defines the ratio between consumed fuel and ticks")
            .defineInRange("thermalExchangerRatio", 1.0, 0.1, 10.0);

    public static final ForgeConfigSpec.IntValue THERMAL_EXCHANGER_RANGE = BUILDER
            .comment("Defines the temperature range")
            .defineInRange("thermalExchangerRange", 13, 0, 50);

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

    // Furnaces

    public static final ForgeConfigSpec.IntValue FURNACES_RANGE = BUILDER
            .comment("Defines the heat range")
            .defineInRange("furnacesRange", 3, 0, 50);

    public static final ForgeConfigSpec.DoubleValue FURNACES_BASE_TEMPERATURE_MODIFIER = BUILDER
            .comment("Defines the temperature modifier when as close as possible")
            .defineInRange("furnacesBaseTemperatureModifier", 15.0, 0, 60);

    public static final ForgeConfigSpec.DoubleValue FURNACES_TEMPERATURE_FALLOFF = BUILDER
            .comment("Defines the temperature falloff")
            .defineInRange("furnacesBaseTemperatureFalloff", 0.45, 0, 2.5);

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

    // Blaze Burner

    public static final ForgeConfigSpec.IntValue BLAZE_BURNER_RANGE = BUILDER
            .comment("Defines the heat range")
            .defineInRange("blazeBurnerRange", 5, 0, 50);

    public static final ForgeConfigSpec.DoubleValue BLAZE_BURNER_NORMAL_BASE_TEMPERATURE_MODIFIER = BUILDER
            .comment("Defines the temperature modifier when as close as possible")
            .defineInRange("blazeBurnerNormalBaseTemperatureModifier", 30.0, -60, 60);

    public static final ForgeConfigSpec.DoubleValue BLAZE_BURNER_NORMAL_TEMPERATURE_FALLOFF = BUILDER
            .comment("Defines the temperature falloff")
            .defineInRange("blazeBurnerNormalTemperatureFalloff", 0.55, 0, 2.5);

    public static final ForgeConfigSpec.DoubleValue BLAZE_BURNER_SPECIAL_BASE_TEMPERATURE_MODIFIER = BUILDER
            .comment("Defines the temperature modifier when as close as possible")
            .defineInRange("blazeBurnerSpecialBaseTemperatureModifier", 47.0, -60, 60);

    public static final ForgeConfigSpec.DoubleValue BLAZE_BURNER_SPECIAL_TEMPERATURE_FALLOFF = BUILDER
            .comment("Defines the temperature falloff")
            .defineInRange("blazeBurnerSpecialTemperatureFalloff", 0.45, 0, 2.5);

    // Fluid Tank

    public static final ForgeConfigSpec.IntValue FLUID_TANK_RANGE = BUILDER
            .comment("Defines the heat range")
            .defineInRange("fluidTankRange", 3, 0, 50);

    public static final ForgeConfigSpec.DoubleValue FLUID_TANK_FLUID_BUCKET_AMOUNT_TEMPERATURE_MODIFIER = BUILDER
            .comment("Defines the how much temperature will be added / deducted per bucket of fluid")
            .defineInRange("fluidTankRange", 0.2, 0, 50);

    public static final ForgeConfigSpec.DoubleValue FLUID_TANK_HOT_BASE_TEMPERATURE_MODIFIER = BUILDER
            .comment("Defines the temperature modifier when as close as possible")
            .defineInRange("fluidTankHotBaseTemperatureModifier", 8.0, -60, 60);

    public static final ForgeConfigSpec.DoubleValue FLUID_TANK_HOT_TEMPERATURE_FALLOFF = BUILDER
            .comment("Defines the temperature falloff")
            .defineInRange("fluidTankHotTemperatureFalloff", 0.45, 0, 2.5);

    public static final ForgeConfigSpec.DoubleValue FLUID_TANK_COLD_BASE_TEMPERATURE_MODIFIER = BUILDER
            .comment("Defines the temperature modifier when as close as possible")
            .defineInRange("fluidTankColdBaseTemperatureModifier", -8.0, -60, 60);

    public static final ForgeConfigSpec.DoubleValue FLUID_TANK_COLD_TEMPERATURE_FALLOFF = BUILDER
            .comment("Defines the temperature falloff")
            .defineInRange("fluidTankColdTemperatureFalloff", 0.45, 0, 2.5);

    static final ForgeConfigSpec SPEC = BUILDER.build();

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {

    }
}
