package com.skytendo.thermantics.datagen;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllTags;
import com.skytendo.thermantics.util.CT_BlockTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeBlockTagsProvider;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class CT_BlockTagProvider extends BlockTagsProvider {

    public CT_BlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, modId, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(CT_BlockTags.LOW_ISOLATING)
                .addTag(Tags.Blocks.GLASS)
                .addTag(Tags.Blocks.FENCES)
                .addTag(Tags.Blocks.FENCE_GATES)
                .addTag(BlockTags.WALLS)
                .addTag(Tags.Blocks.CHESTS)
                .addTag(BlockTags.BEDS)
                .addTag(AllTags.AllBlockTags.WINDMILL_SAILS.tag)
                .addTag(AllTags.AllBlockTags.FAN_TRANSPARENT.tag)
                .add(AllBlocks.COPYCAT_PANEL.get())
                .add(AllBlocks.COPYCAT_BARS.get())
                .add(AllBlocks.COPYCAT_STEP.get())
                .add(AllBlocks.COPYCAT_BASE.get());

        tag(CT_BlockTags.HIGH_ISOLATING)
                .addTag(Tags.Blocks.END_STONES)
                .addTag(BlockTags.WOOL)
                .addTag(BlockTags.BEACON_BASE_BLOCKS)
                .addTag(AllTags.AllBlockTags.CASING.tag);

        tag(CT_BlockTags.EXTREME_ISOLATING)
                .addTag(Tags.Blocks.OBSIDIAN);
    }
}
