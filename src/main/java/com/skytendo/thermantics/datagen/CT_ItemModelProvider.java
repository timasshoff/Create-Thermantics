package com.skytendo.thermantics.datagen;

import com.skytendo.thermantics.item.CT_Items;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class CT_ItemModelProvider extends ItemModelProvider {

    public CT_ItemModelProvider(PackOutput output, String modid, ExistingFileHelper existingFileHelper) {
        super(output, modid, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(CT_Items.SCORCHING_COMPOUND_BUCKET.get());
        basicItem(CT_Items.COLD_SCORCHING_COMPOUND_BUCKET.get());
        basicItem(CT_Items.SUPERHEATED_SCORCHING_COMPOUND_BUCKET.get());
        basicItem(CT_Items.WARM_FRIGID_COMPOUND_BUCKET.get());
        basicItem(CT_Items.FRIGID_COMPOUND_BUCKET.get());
    }
}
