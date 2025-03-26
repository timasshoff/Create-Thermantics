package com.skytendo.thermantics.item;

import com.simibubi.create.AllItems;
import com.skytendo.thermantics.Thermantics;
import com.skytendo.thermantics.block.CT_Blocks;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class CT_CreativeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Thermantics.MODID);

    public static final RegistryObject<CreativeModeTab> THERMANTICS_TAB = CREATIVE_MODE_TABS.register("thermantics_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(AllItems.ANDESITE_ALLOY.get()))
                    .title(Component.translatable("creativetab.thermantics.default_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(CT_Blocks.THERMAL_EXCHANGER.get());
                        output.accept(CT_Items.COLD_SCORCHING_COMPOUND_BUCKET.get());
                        output.accept(CT_Items.SCORCHING_COMPOUND_BUCKET.get());
                        output.accept(CT_Items.SUPERHEATED_SCORCHING_COMPOUND_BUCKET.get());
                        output.accept(CT_Items.WARM_FRIGID_COMPOUND_BUCKET.get());
                        output.accept(CT_Items.FRIGID_COMPOUND_BUCKET.get());
                    }).build());

    public static void register(IEventBus bus) {
        CREATIVE_MODE_TABS.register(bus);
    }

}
