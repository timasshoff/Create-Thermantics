package com.skytendo.thermantics.fluid;

import com.skytendo.thermantics.Thermantics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.joml.Vector3f;

public class CT_FluidTypes {
    public static final ResourceLocation WATER_STILL = ResourceLocation.parse("minecraft:block/water_still");
    public static final ResourceLocation WATER_FLOWING = ResourceLocation.parse("minecraft:block/water_flow");
    public static final ResourceLocation WATER_OVERLAY = ResourceLocation.parse("minecraft:misc/underwater");

    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, Thermantics.MODID);

    public static final RegistryObject<FluidType> SUPERHEATED_SCORCHING_COMPOUND_FLUID_TYPE = register("superheated_scorching_compound",
            0xFFf7054e,
            new Vector3f(224 / 255f, 56 / 255f, 208 / 255f),
            FluidType.Properties.create().lightLevel(1).density(15).viscosity(5));

    public static final RegistryObject<FluidType> SCORCHING_COMPOUND_FLUID_TYPE = register("scorching_compound",
            0xFFf61000,
            new Vector3f(224 / 255f, 56 / 255f, 208 / 255f),
            FluidType.Properties.create().lightLevel(1).density(15).viscosity(5));

    public static final RegistryObject<FluidType> COLD_SCORCHING_COMPOUND_FLUID_TYPE = register("cold_scorching_compound",
            0xBE717171,
            new Vector3f(224 / 255f, 56 / 255f, 208 / 255f),
            FluidType.Properties.create().density(15).viscosity(5));

    public static final RegistryObject<FluidType> WARM_FRIGID_COMPOUND = register("warm_frigid_compound",
            0xBE94b8d4,
            new Vector3f(224 / 255f, 56 / 255f, 208 / 255f),
            FluidType.Properties.create().density(15).viscosity(5));

    public static final RegistryObject<FluidType> FRIGID_COMPOUND = register("frigid_compound",
            0xFF36a3f7,
            new Vector3f(224 / 255f, 56 / 255f, 208 / 255f),
            FluidType.Properties.create().density(15).viscosity(5));

    private static RegistryObject<FluidType> register(String name, int tintColor, Vector3f fogColor, FluidType.Properties properties) {
        return FLUID_TYPES.register(name, () -> new BaseFluidType(WATER_STILL, WATER_FLOWING, WATER_OVERLAY, tintColor, fogColor, properties));
    }

    public static void register(IEventBus bus) {
        FLUID_TYPES.register(bus);
    }
}
