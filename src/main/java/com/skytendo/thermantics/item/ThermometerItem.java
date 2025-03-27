package com.skytendo.thermantics.item;

import com.simibubi.create.foundation.item.ItemDescription;
import com.skytendo.thermantics.client.ClientTemperatureData;
import com.skytendo.thermantics.temperature.PlayerTemperature;
import net.createmod.catnip.lang.FontHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ThermometerItem extends Item {

    public ThermometerItem(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        MutableComponent ownTemperature = Component.translatable("info.thermantics.own_temperature").withStyle(ChatFormatting.GRAY);
        ownTemperature.append(Component.literal(Integer.toString(Math.round(ClientTemperatureData.get()))).withStyle(ChatFormatting.GOLD));

        switch (PlayerTemperature.temperatureToState(ClientTemperatureData.get())) {
            case FREEZING -> ownTemperature.append(Component.translatable("info.thermantics.freezing").withStyle(ChatFormatting.DARK_BLUE));
            case COLD -> ownTemperature.append(Component.translatable("info.thermantics.cold").withStyle(ChatFormatting.BLUE));
            case CHILLY -> ownTemperature.append(Component.translatable("info.thermantics.chilly").withStyle(ChatFormatting.AQUA));
            case WARM -> ownTemperature.append(Component.translatable("info.thermantics.warm").withStyle(ChatFormatting.YELLOW));
            case HOT -> ownTemperature.append(Component.translatable("info.thermantics.hot").withStyle(ChatFormatting.RED));
            case FIERY -> ownTemperature.append(Component.translatable("info.thermantics.fiery").withStyle(ChatFormatting.DARK_RED));
        }

        player.displayClientMessage(ownTemperature, true);
        return InteractionResultHolder.pass(player.getItemInHand(hand));
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        ItemDescription description = new ItemDescription.Builder(FontHelper.Palette.STANDARD_CREATE)
                .addSummary(Component.translatable("summary.thermantics.thermometer").getString())
                .addBehaviour("When R-Clicked", "Displays your current temperature")
                .build();
        tooltip.addAll(description.getCurrentLines());
    }
}
