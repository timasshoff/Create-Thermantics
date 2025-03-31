package com.skytendo.thermantics.datagen;

import com.skytendo.thermantics.Thermantics;
import com.skytendo.thermantics.item.CT_Items;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
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
        basicItem(CT_Items.RADIATOR.get());

        ModelFile freezingThermometer = withExistingParent("thermometer_freezing", mcLoc("item/handheld"))
                .texture("layer0", modLoc("item/thermometer_freezing"));
        ModelFile coldThermometer = withExistingParent("thermometer_cold", mcLoc("item/handheld"))
                .texture("layer0", modLoc("item/thermometer_cold"));
        ModelFile chillyThermometer = withExistingParent("thermometer_chilly", mcLoc("item/handheld"))
                .texture("layer0", modLoc("item/thermometer_chilly"));
        ModelFile warmThermometer = withExistingParent("thermometer_warm", mcLoc("item/handheld"))
                .texture("layer0", modLoc("item/thermometer_warm"));
        ModelFile hotThermometer = withExistingParent("thermometer_hot", mcLoc("item/handheld"))
                .texture("layer0", modLoc("item/thermometer_hot"));
        ModelFile fieryThermometer = withExistingParent("thermometer_fiery", mcLoc("item/handheld"))
                .texture("layer0", modLoc("item/thermometer_fiery"));
        basicItem(CT_Items.THERMOMETER.get())
                .override().predicate(ResourceLocation.fromNamespaceAndPath(Thermantics.MODID, "temperature"), 0.0f).model(freezingThermometer).end()
                .override().predicate(ResourceLocation.fromNamespaceAndPath(Thermantics.MODID, "temperature"), 0.1f).model(coldThermometer).end()
                .override().predicate(ResourceLocation.fromNamespaceAndPath(Thermantics.MODID, "temperature"), 0.2f).model(chillyThermometer).end()
                .override().predicate(ResourceLocation.fromNamespaceAndPath(Thermantics.MODID, "temperature"), 0.3f).model(warmThermometer).end()
                .override().predicate(ResourceLocation.fromNamespaceAndPath(Thermantics.MODID, "temperature"), 0.4f).model(hotThermometer).end()
                .override().predicate(ResourceLocation.fromNamespaceAndPath(Thermantics.MODID, "temperature"), 0.51f).model(fieryThermometer).end();

    }
}
