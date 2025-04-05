package com.skytendo.thermantics.enchantment;

import com.skytendo.thermantics.Thermantics;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class CT_Enchantments {

    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, Thermantics.MODID);

    public static RegistryObject<Enchantment> INFERNAL_PLATING = ENCHANTMENTS.register("infernal_plating",
            () -> new InfernalPlatingEnchantment(Enchantment.Rarity.RARE,
                    EnchantmentCategory.ARMOR,
                    new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET}));

    public static void register(IEventBus bus) {
        ENCHANTMENTS.register(bus);
    }
}
