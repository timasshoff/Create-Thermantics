package com.skytendo.thermantics.temperature.modifiers;

import com.skytendo.thermantics.Config;
import com.skytendo.thermantics.block.CT_Blocks;
import com.skytendo.thermantics.block.ThermalExchangerBlockEntity;
import com.skytendo.thermantics.util.BlockFinder;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class AbstractFurnaceTemperatureModifier implements TemperatureModifier{

    @Override
    public float modifyTemperature(Player player, Biome biome, float temperature) {
        List<BlockPos> blocks = BlockFinder.findBlocks(player.level(), player.blockPosition(), Config.FURNACES_RANGE.get(), Blocks.FURNACE);
        blocks.addAll(BlockFinder.findBlocks(player.level(), player.blockPosition(), Config.FURNACES_RANGE.get(), Blocks.BLAST_FURNACE));
        blocks.addAll(BlockFinder.findBlocks(player.level(), player.blockPosition(), Config.FURNACES_RANGE.get(), Blocks.SMOKER));
        if(blocks.isEmpty()) {
            return temperature;
        }

        for (BlockPos pos : blocks) {
            BlockState state = player.level().getBlockState(pos);
            if (state.getValue(AbstractFurnaceBlock.LIT)) {
                temperature += BlockFinder.getIsolatedRangedTemperatureModifier(player.level(), player.blockPosition(), pos, Config.FURNACES_BASE_TEMPERATURE_MODIFIER.get(), Config.FURNACES_TEMPERATURE_FALLOFF.get());
            }
        }

        return temperature;
    }
}
