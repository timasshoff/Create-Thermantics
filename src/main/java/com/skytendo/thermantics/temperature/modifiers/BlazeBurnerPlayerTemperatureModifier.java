package com.skytendo.thermantics.temperature.modifiers;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.processing.burner.BlazeBurnerBlockEntity;
import com.skytendo.thermantics.Config;
import com.skytendo.thermantics.util.BlockFinder;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;

import java.util.List;

public class BlazeBurnerPlayerTemperatureModifier implements LevelTemperatureModifier {

    @Override
    public float modifyTemperature(BlockPos pos, Level level, float temperature) {
        List<BlockPos> blocks = BlockFinder.findBlocks(level, pos, Config.BLAZE_BURNER_RANGE.get(), AllBlocks.BLAZE_BURNER.get());
        if(blocks.isEmpty()) {
            return temperature;
        }

        for (BlockPos exchangerPos : blocks) {
            BlazeBurnerBlockEntity blazeBurner = (BlazeBurnerBlockEntity) level.getBlockEntity(exchangerPos);
            if (blazeBurner == null) {
                continue;
            }

            BlazeBurnerBlockEntity.FuelType fuelType = blazeBurner.getActiveFuel();
            if (fuelType == BlazeBurnerBlockEntity.FuelType.NONE) {
                continue;
            }
            if (fuelType == BlazeBurnerBlockEntity.FuelType.NORMAL) {
                temperature += BlockFinder.getIsolatedRangedTemperatureModifier(level, pos, exchangerPos, Config.BLAZE_BURNER_NORMAL_BASE_TEMPERATURE_MODIFIER.get(), Config.BLAZE_BURNER_NORMAL_TEMPERATURE_FALLOFF.get());
            }
            if (fuelType == BlazeBurnerBlockEntity.FuelType.SPECIAL) {
                temperature += BlockFinder.getIsolatedRangedTemperatureModifier(level, pos, exchangerPos, Config.BLAZE_BURNER_SPECIAL_BASE_TEMPERATURE_MODIFIER.get(), Config.BLAZE_BURNER_SPECIAL_TEMPERATURE_FALLOFF.get());
            }
        }

        return temperature;
    }
}
