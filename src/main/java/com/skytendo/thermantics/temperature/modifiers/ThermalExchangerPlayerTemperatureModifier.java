package com.skytendo.thermantics.temperature.modifiers;

import com.skytendo.thermantics.Config;
import com.skytendo.thermantics.block.CT_Blocks;
import com.skytendo.thermantics.block.thermal_exchanger.ThermalExchangerBlockEntity;
import com.skytendo.thermantics.util.BlockFinder;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;

import java.util.List;

public class ThermalExchangerPlayerTemperatureModifier implements LevelTemperatureModifier {

    @Override
    public float modifyTemperature(BlockPos pos, Level level, float temperature) {
        List<BlockPos> blocks = BlockFinder.findBlocks(level, pos, Config.THERMAL_EXCHANGER_RANGE.get(), CT_Blocks.THERMAL_EXCHANGER.get());
        if(blocks.isEmpty()) {
            return temperature;
        }

        for (BlockPos exchangerPos : blocks) {
            ThermalExchangerBlockEntity exchanger = (ThermalExchangerBlockEntity) level.getBlockEntity(exchangerPos);
            if (exchanger == null) {
                continue;
            }

            if (exchanger.getMode() == ThermalExchangerBlockEntity.Mode.HEAT) {
                temperature += BlockFinder.getIsolatedRangedTemperatureModifier(level, pos, exchangerPos, Config.THERMAL_EXCHANGER_HEAT_BASE_TEMPERATURE_MODIFIER.get(), Config.THERMAL_EXCHANGER_HEAT_TEMPERATURE_FALLOFF.get());
            }

            if (exchanger.getMode() == ThermalExchangerBlockEntity.Mode.SUPERHEAT) {
                temperature += BlockFinder.getIsolatedRangedTemperatureModifier(level, pos, exchangerPos, Config.THERMAL_EXCHANGER_SUPERHEAT_BASE_TEMPERATURE_MODIFIER.get(), Config.THERMAL_EXCHANGER_SUPERHEAT_TEMPERATURE_FALLOFF.get());
            }

            if (exchanger.getMode() == ThermalExchangerBlockEntity.Mode.COOl) {
                temperature += BlockFinder.getIsolatedRangedTemperatureModifier(level, pos, exchangerPos, -Config.THERMAL_EXCHANGER_COOL_BASE_TEMPERATURE_MODIFIER.get(), Config.THERMAL_EXCHANGER_COOL_TEMPERATURE_FALLOFF.get());
            }
        }

        return temperature;
    }
}
