package com.skytendo.thermantics.temperature.modifiers;

import com.skytendo.thermantics.Config;
import com.skytendo.thermantics.util.CT_ItemTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.biome.Biome;

public class ArmorTemperatureModifier implements TemperatureModifier{
    @Override
    public float modifyTemperature(Player player, Biome biome, float temperature) {
        for (ItemStack stack : player.getArmorSlots()) {
            if (stack.is(CT_ItemTags.COLD_ARMOR)) {
                temperature += Config.COLD_ARMOR.get();
            }
            if (stack.is(CT_ItemTags.WARM_ARMOR)) {
                temperature += Config.WARM_ARMOR.get();
            }
            if (stack.is(CT_ItemTags.VERY_WARM_ARMOR)) {
                temperature += Config.VERY_WARM_ARMOR.get();
            }
        }
        return temperature;
    }
}
