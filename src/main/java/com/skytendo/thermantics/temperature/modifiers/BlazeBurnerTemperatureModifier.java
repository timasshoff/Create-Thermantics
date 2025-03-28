package com.skytendo.thermantics.temperature.modifiers;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.processing.burner.BlazeBurnerBlock;
import com.simibubi.create.content.processing.burner.BlazeBurnerBlockEntity;
import com.skytendo.thermantics.Config;
import com.skytendo.thermantics.block.CT_Blocks;
import com.skytendo.thermantics.block.ThermalExchangerBlockEntity;
import com.skytendo.thermantics.util.BlockFinder;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.biome.Biome;

import java.util.List;

public class BlazeBurnerTemperatureModifier implements TemperatureModifier{
    @Override
    public float modifyTemperature(Player player, Biome biome, float temperature) {
        List<BlockPos> blocks = BlockFinder.findBlocks(player.level(), player.blockPosition(), Config.BLAZE_BURNER_RANGE.get(), AllBlocks.BLAZE_BURNER.get());
        if(blocks.isEmpty()) {
            return temperature;
        }

        for (BlockPos exchangerPos : blocks) {
            BlazeBurnerBlockEntity blazeBurner = (BlazeBurnerBlockEntity) player.level().getBlockEntity(exchangerPos);
            if (blazeBurner == null) {
                continue;
            }

            BlazeBurnerBlockEntity.FuelType fuelType = blazeBurner.getActiveFuel();
            if (fuelType == BlazeBurnerBlockEntity.FuelType.NONE) {
                continue;
            }
            if (fuelType == BlazeBurnerBlockEntity.FuelType.NORMAL) {
                temperature += BlockFinder.getIsolatedRangedTemperatureModifier(player.level(), player.blockPosition(), exchangerPos, Config.BLAZE_BURNER_NORMAL_BASE_TEMPERATURE_MODIFIER.get(), Config.BLAZE_BURNER_NORMAL_TEMPERATURE_FALLOFF.get());
            }
            if (fuelType == BlazeBurnerBlockEntity.FuelType.SPECIAL) {
                temperature += BlockFinder.getIsolatedRangedTemperatureModifier(player.level(), player.blockPosition(), exchangerPos, Config.BLAZE_BURNER_SPECIAL_BASE_TEMPERATURE_MODIFIER.get(), Config.BLAZE_BURNER_SPECIAL_TEMPERATURE_FALLOFF.get());
            }
        }

        return temperature;
    }
}
