package com.skytendo.thermantics.temperature.modifiers;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.infrastructure.config.AllConfigs;
import com.skytendo.thermantics.block.CT_Blocks;
import com.skytendo.thermantics.block.ThermalExchangerBlockEntity;
import com.skytendo.thermantics.util.BlockFinder;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.biome.Biome;

import java.util.List;

public class ThermalExchangerTemperatureModifier implements TemperatureModifier {
    @Override
    public float modifyTemperature(Player player, Biome biome, float temperature) {
        List<BlockPos> blocks = BlockFinder.findBlocks(player.level(), player.blockPosition(), 15, CT_Blocks.THERMAL_EXCHANGER.get());
        if(blocks.isEmpty()) {
            return temperature;
        }

        for (BlockPos exchangerPos : blocks) {
            ThermalExchangerBlockEntity exchanger = (ThermalExchangerBlockEntity) player.level().getBlockEntity(exchangerPos);
            if (exchanger == null) {
                continue;
            }

            if (exchanger.getMode() == ThermalExchangerBlockEntity.Mode.HEAT) {
                temperature += BlockFinder.getIsolatedRangedTemperatureModifier(player.level(), player.blockPosition(), exchangerPos, 10, 0.0f);
            }

            if (exchanger.getMode() == ThermalExchangerBlockEntity.Mode.SUPERHEAT) {
                temperature += BlockFinder.getIsolatedRangedTemperatureModifier(player.level(), player.blockPosition(), exchangerPos, 30, 0.0f);
            }

            if (exchanger.getMode() == ThermalExchangerBlockEntity.Mode.COOl) {
                temperature += BlockFinder.getIsolatedRangedTemperatureModifier(player.level(), player.blockPosition(), exchangerPos, -25, 0.0f);
            }
        }

        return temperature;
    }
}
