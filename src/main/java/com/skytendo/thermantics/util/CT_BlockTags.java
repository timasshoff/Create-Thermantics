package com.skytendo.thermantics.util;

import com.skytendo.thermantics.Thermantics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class CT_BlockTags {

    public static final TagKey<Block> LOW_ISOLATING = createTag("low_isolating");
    public static final TagKey<Block> HIGH_ISOLATING = createTag("high_isolating");
    public static final TagKey<Block> EXTREME_ISOLATING = createTag("extreme_isolating");

    private static TagKey<Block> createTag(String name) {
        return BlockTags.create(ResourceLocation.fromNamespaceAndPath(Thermantics.MODID, name));
    }

}
