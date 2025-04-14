package com.skytendo.thermantics.block.thermal_sensor;

import com.simibubi.create.foundation.item.ItemDescription;
import net.createmod.catnip.lang.FontHelper;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ThermalSensorBlockItem extends BlockItem {

    public ThermalSensorBlockItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        ItemDescription description = new ItemDescription.Builder(FontHelper.Palette.STANDARD_CREATE)
                .addSummary(Component.translatable("summary.thermantics.thermal_sensor").getString())
                .build();
        tooltip.addAll(description.getCurrentLines());
    }
}
