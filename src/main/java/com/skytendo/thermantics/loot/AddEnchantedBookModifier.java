package com.skytendo.thermantics.loot;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class AddEnchantedBookModifier extends LootModifier {

    public static final Supplier<Codec<AddEnchantedBookModifier>> CODEC = Suppliers.memoize(() ->
            RecordCodecBuilder.create(inst -> codecStart(inst).and(ForgeRegistries.ENCHANTMENTS.getCodec()
                    .fieldOf("enchantment").forGetter(m -> m.enchantment)).apply(inst, AddEnchantedBookModifier::new)));

    private final Enchantment enchantment;

    public AddEnchantedBookModifier(LootItemCondition[] conditionsIn, Enchantment enchantment) {
        super(conditionsIn);
        this.enchantment = enchantment;
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        for (LootItemCondition condition : this.conditions) {
            if (!condition.test(context)) {
                return generatedLoot;
            }
        }
        ItemStack stack = new ItemStack(Items.ENCHANTED_BOOK);
        stack.enchant(enchantment, 1);
        generatedLoot.add(stack);
        return generatedLoot;
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }
}
