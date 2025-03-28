package com.skytendo.thermantics.datagen;

import com.skytendo.thermantics.fluid.CT_Fluids;
import com.skytendo.thermantics.util.CT_FluidTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.FluidTagsProvider;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class CT_FluidTagProvider extends FluidTagsProvider {
    public CT_FluidTagProvider(PackOutput p_255941_, CompletableFuture<HolderLookup.Provider> p_256600_, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_255941_, p_256600_, modId, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider p_256366_) {
        tag(CT_FluidTags.FLUID_TANK_HOT_CONTENT)
                .add(CT_Fluids.SOURCE_SCORCHING_COMPOUND.get())
                .add(CT_Fluids.SOURCE_SUPERHEATED_SCORCHING_COMPOUND.get())
                .add(Fluids.LAVA);

        tag(CT_FluidTags.FLUID_TANK_COLD_CONTENT)
                .add(CT_Fluids.SOURCE_FRIGID_COMPOUND.get());
    }
}
