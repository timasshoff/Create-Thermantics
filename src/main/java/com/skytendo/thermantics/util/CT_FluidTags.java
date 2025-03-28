package com.skytendo.thermantics.util;

import com.skytendo.thermantics.Thermantics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

public class CT_FluidTags {

    public static final TagKey<Fluid> FLUID_TANK_HOT_CONTENT = createTag("fluid_tank_hot_content");
    public static final TagKey<Fluid> FLUID_TANK_COLD_CONTENT = createTag("fluid_tank_cold_content");

    private static TagKey<Fluid> createTag(String name) {
        return FluidTags.create(ResourceLocation.fromNamespaceAndPath(Thermantics.MODID, name));
    }

}
