package com.skytendo.thermantics.util;

import com.skytendo.thermantics.Thermantics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class CT_ItemTags {
    public static final TagKey<Item> WARM_ARMOR = createTag("warm_armor");
    public static final TagKey<Item> VERY_WARM_ARMOR = createTag("very_warm_armor");
    public static final TagKey<Item> COLD_ARMOR = createTag("cold_armor");
    public static final TagKey<Item> HYPERTHERMIA_ISOLATING_ARMOR = createTag("hyperthermia_isolating_armor");

    private static TagKey<Item> createTag(String name) {
        return ItemTags.create(ResourceLocation.fromNamespaceAndPath(Thermantics.MODID, name));
    }
}
