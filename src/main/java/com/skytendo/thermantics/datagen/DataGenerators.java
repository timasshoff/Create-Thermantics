package com.skytendo.thermantics.datagen;

import com.skytendo.thermantics.Thermantics;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = Thermantics.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        CT_BlockTagProvider blockTagProvider = generator.addProvider(event.includeServer(), new CT_BlockTagProvider(output, lookupProvider, Thermantics.MODID, existingFileHelper));
        generator.addProvider(event.includeServer(), new CT_ItemTagProvider(output, lookupProvider, blockTagProvider.contentsGetter(), Thermantics.MODID, existingFileHelper));
        generator.addProvider(event.includeServer(), new CT_FluidTagProvider(output, lookupProvider, Thermantics.MODID, existingFileHelper));
        generator.addProvider(event.includeServer(), new CT_BlockStateProvider(output, Thermantics.MODID, existingFileHelper));
        generator.addProvider(event.includeServer(), new CT_ItemModelProvider(output, Thermantics.MODID, existingFileHelper));

        generator.addProvider(event.includeServer(), new CT_GlobalLootModifiersProvider(output));
    }
}
