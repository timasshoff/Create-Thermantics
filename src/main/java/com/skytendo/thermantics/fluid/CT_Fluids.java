package com.skytendo.thermantics.fluid;

import com.skytendo.thermantics.Thermantics;
import com.skytendo.thermantics.block.CT_Blocks;
import com.skytendo.thermantics.item.CT_Items;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CT_Fluids {

    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, Thermantics.MODID);

    // Superheated Scorcing Compound

    public static final RegistryObject<FlowingFluid> SOURCE_SUPERHEATED_SCORCHING_COMPOUND = FLUIDS.register("superheated_scorching_compound_fluid",
            () -> new ForgeFlowingFluid.Source(CT_Fluids.SUPERHEATED_SCORCHING_COMPOUND_FLUID_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_SUPERHEATED_SCORCHING_COMPOUND = FLUIDS.register("superheated_flowing_scorching_compound",
            () -> new ForgeFlowingFluid.Flowing(CT_Fluids.SUPERHEATED_SCORCHING_COMPOUND_FLUID_PROPERTIES));

    public static final ForgeFlowingFluid.Properties SUPERHEATED_SCORCHING_COMPOUND_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            CT_FluidTypes.SUPERHEATED_SCORCHING_COMPOUND_FLUID_TYPE, SOURCE_SUPERHEATED_SCORCHING_COMPOUND, FLOWING_SUPERHEATED_SCORCHING_COMPOUND)
            .slopeFindDistance(2).levelDecreasePerBlock(2).block(CT_Blocks.SUPERHEATED_SCROCHING_COMPOUND_BLOCK)
            .bucket(CT_Items.SUPERHEATED_SCORCHING_COMPOUND_BUCKET);

    // Scorcing Compound

    public static final RegistryObject<FlowingFluid> SOURCE_SCORCHING_COMPOUND = FLUIDS.register("scorching_compound_fluid",
            () -> new ForgeFlowingFluid.Source(CT_Fluids.SCORCHING_COMPOUND_FLUID_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_SCORCHING_COMPOUND = FLUIDS.register("flowing_scorching_compound",
            () -> new ForgeFlowingFluid.Flowing(CT_Fluids.SCORCHING_COMPOUND_FLUID_PROPERTIES));

    public static final ForgeFlowingFluid.Properties SCORCHING_COMPOUND_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            CT_FluidTypes.SCORCHING_COMPOUND_FLUID_TYPE, SOURCE_SCORCHING_COMPOUND, FLOWING_SCORCHING_COMPOUND)
            .slopeFindDistance(2).levelDecreasePerBlock(2).block(CT_Blocks.SCROCHING_COMPOUND_BLOCK)
            .bucket(CT_Items.SCORCHING_COMPOUND_BUCKET);

    // Cold Scorching Compound

    public static final RegistryObject<FlowingFluid> SOURCE_COLD_SCORCHING_COMPOUND = FLUIDS.register("cold_scorching_compound_fluid",
            () -> new ForgeFlowingFluid.Source(CT_Fluids.COLD_SCORCHING_COMPOUND_FLUID_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_COLD_SCORCHING_COMPOUND = FLUIDS.register("flowing_cold_scorching_compound",
            () -> new ForgeFlowingFluid.Flowing(CT_Fluids.COLD_SCORCHING_COMPOUND_FLUID_PROPERTIES));

    public static final ForgeFlowingFluid.Properties COLD_SCORCHING_COMPOUND_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            CT_FluidTypes.COLD_SCORCHING_COMPOUND_FLUID_TYPE, SOURCE_COLD_SCORCHING_COMPOUND, FLOWING_COLD_SCORCHING_COMPOUND)
            .slopeFindDistance(2).levelDecreasePerBlock(2).block(CT_Blocks.COLD_SCROCHING_COMPOUND_BLOCK)
            .bucket(CT_Items.COLD_SCORCHING_COMPOUND_BUCKET);

    // Warm Frigid Compound

    public static final RegistryObject<FlowingFluid> SOURCE_WARM_FRIGID_COMPOUND = FLUIDS.register("warm_frigid_compound_fluid",
            () -> new ForgeFlowingFluid.Source(CT_Fluids.WARM_FRIGID_COMPOUND_FLUID_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_WARM_FRIGID_COMPOUND = FLUIDS.register("flowing_warm_frigid_compound",
            () -> new ForgeFlowingFluid.Flowing(CT_Fluids.WARM_FRIGID_COMPOUND_FLUID_PROPERTIES));

    public static final ForgeFlowingFluid.Properties WARM_FRIGID_COMPOUND_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            CT_FluidTypes.WARM_FRIGID_COMPOUND, SOURCE_WARM_FRIGID_COMPOUND, FLOWING_WARM_FRIGID_COMPOUND)
            .slopeFindDistance(2).levelDecreasePerBlock(2).block(CT_Blocks.WARM_FRIGID_COMPOUND_BLOCK)
            .bucket(CT_Items.WARM_FRIGID_COMPOUND_BUCKET);

    // Frigid Compound

    public static final RegistryObject<FlowingFluid> SOURCE_FRIGID_COMPOUND = FLUIDS.register("frigid_compound_fluid",
            () -> new ForgeFlowingFluid.Source(CT_Fluids.FRIGID_COMPOUND_FLUID_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_FRIGID_COMPOUND = FLUIDS.register("flowing_frigid_compound",
            () -> new ForgeFlowingFluid.Flowing(CT_Fluids.FRIGID_COMPOUND_FLUID_PROPERTIES));

    public static final ForgeFlowingFluid.Properties FRIGID_COMPOUND_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            CT_FluidTypes.FRIGID_COMPOUND, SOURCE_FRIGID_COMPOUND, FLOWING_FRIGID_COMPOUND)
            .slopeFindDistance(2).levelDecreasePerBlock(2).block(CT_Blocks.FRIGID_COMPOUND_BLOCK)
            .bucket(CT_Items.FRIGID_COMPOUND_BUCKET);

    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }
}
