package com.skytendo.thermantics.datagen;

import com.simibubi.create.Create;
import com.skytendo.thermantics.block.CT_Blocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class CT_BlockStateProvider extends BlockStateProvider {

    public CT_BlockStateProvider(PackOutput output, String modid, ExistingFileHelper exFileHelper) {
        super(output, modid, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        horizontalBlock(CT_Blocks.THERMAL_EXCHANGER.get(), models().cubeBottomTop(
                CT_Blocks.THERMAL_EXCHANGER.getId().getPath(),
                ResourceLocation.fromNamespaceAndPath(Create.ID, "block/copper_casing"),
                ResourceLocation.fromNamespaceAndPath(Create.ID, "block/copper_casing"),
                modLoc("block/thermal_exchanger_top")
        ));
        itemModels().cubeBottomTop(CT_Blocks.THERMAL_EXCHANGER.getId().getPath().toString(),
                ResourceLocation.fromNamespaceAndPath(Create.ID, "block/copper_casing"),
                ResourceLocation.fromNamespaceAndPath(Create.ID, "block/copper_casing"),
                modLoc("block/thermal_exchanger_top"));
    }
}
