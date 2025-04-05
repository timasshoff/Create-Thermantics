package com.skytendo.thermantics.loot;

import com.mojang.serialization.Codec;
import com.skytendo.thermantics.Thermantics;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CT_LootModifiers {

    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIERS_SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, Thermantics.MODID);

    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> ADD_ITEM = LOOT_MODIFIERS_SERIALIZERS.register("add_item", AddItemModifier.CODEC);
    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> ADD_POTION = LOOT_MODIFIERS_SERIALIZERS.register("add_potion", AddPotionModifier.CODEC);
    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> ADD_ENCHANTED_BOOK = LOOT_MODIFIERS_SERIALIZERS.register("add_enchanted_book", AddEnchantedBookModifier.CODEC);

    public static void register(IEventBus bus) {
        LOOT_MODIFIERS_SERIALIZERS.register(bus);
    }

}
