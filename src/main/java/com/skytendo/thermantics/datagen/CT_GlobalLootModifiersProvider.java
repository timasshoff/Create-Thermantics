package com.skytendo.thermantics.datagen;

import com.skytendo.thermantics.Thermantics;
import com.skytendo.thermantics.effect.CT_Potions;
import com.skytendo.thermantics.enchantment.CT_Enchantments;
import com.skytendo.thermantics.loot.AddEnchantedBookModifier;
import com.skytendo.thermantics.loot.AddPotionModifier;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;

public class CT_GlobalLootModifiersProvider extends GlobalLootModifierProvider {

    public CT_GlobalLootModifiersProvider(PackOutput output) {
        super(output, Thermantics.MODID);
    }

    @Override
    protected void start() {
        add("clemency_potion_from_ruined_portal", new AddPotionModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.parse("minecraft:chests/ruined_portal")).build(),
                LootItemRandomChanceCondition.randomChance(1.0f).build()
        }, CT_Potions.CLEMENCY.get()));

        add("infernal_plating_from_bastion_treasure", new AddEnchantedBookModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(ResourceLocation.parse("minecraft:chests/bastion_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(0.2f).build()
        }, CT_Enchantments.INFERNAL_PLATING.get()));
    }
}
