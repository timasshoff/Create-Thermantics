package com.skytendo.thermantics.item;

import com.skytendo.thermantics.Thermantics;
import com.skytendo.thermantics.fluid.CT_Fluids;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CT_Items {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Thermantics.MODID);

    public static final RegistryObject<Item> SCORCHING_COMPOUND_BUCKET = ITEMS.register("scorching_compound_bucket",
            () -> new BucketItem(CT_Fluids.SOURCE_SCORCHING_COMPOUND, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));

    public static final RegistryObject<Item> COLD_SCORCHING_COMPOUND_BUCKET = ITEMS.register("cold_scorching_compound_bucket",
            () -> new BucketItem(CT_Fluids.SOURCE_COLD_SCORCHING_COMPOUND, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));

    public static final RegistryObject<Item> SUPERHEATED_SCORCHING_COMPOUND_BUCKET = ITEMS.register("superheated_scorching_compound_bucket",
            () -> new BucketItem(CT_Fluids.SOURCE_SUPERHEATED_SCORCHING_COMPOUND, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));

    public static final RegistryObject<Item> WARM_FRIGID_COMPOUND_BUCKET = ITEMS.register("warm_frigid_compound_bucket",
            () -> new BucketItem(CT_Fluids.SOURCE_WARM_FRIGID_COMPOUND, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));

    public static final RegistryObject<Item> FRIGID_COMPOUND_BUCKET = ITEMS.register("frigid_compound_bucket",
            () -> new BucketItem(CT_Fluids.SOURCE_FRIGID_COMPOUND, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));


    public CT_Items() {
    }

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}
