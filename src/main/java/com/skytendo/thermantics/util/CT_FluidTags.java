package com.skytendo.thermantics.util;

import com.skytendo.thermantics.Thermantics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

public class CT_FluidTags {

    public static final TagKey<Fluid> THERMAL_EXCHANGER_FUEL = createTag("thermal_exchanger_fuel");

    private static TagKey<Fluid> createTag(String name) {
        return FluidTags.create(ResourceLocation.fromNamespaceAndPath(Thermantics.MODID, name));
    }

}
