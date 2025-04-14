package com.skytendo.thermantics.temperature.modifiers;

import com.skytendo.thermantics.Config;
import com.skytendo.thermantics.util.BlockFinder;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class AbstractFurnacePlayerTemperatureModifier implements LevelTemperatureModifier {

    @Override
    public float modifyTemperature(BlockPos pos, Level level, float temperature) {
        List<BlockPos> blocks = BlockFinder.findBlocks(level, pos, Config.FURNACES_RANGE.get(), Blocks.FURNACE);
        blocks.addAll(BlockFinder.findBlocks(level, pos, Config.FURNACES_RANGE.get(), Blocks.BLAST_FURNACE));
        blocks.addAll(BlockFinder.findBlocks(level, pos, Config.FURNACES_RANGE.get(), Blocks.SMOKER));
        if(blocks.isEmpty()) {
            return temperature;
        }

        for (BlockPos foundPos : blocks) {
            BlockState state = level.getBlockState(foundPos);
            if (state.getValue(AbstractFurnaceBlock.LIT)) {
                temperature += BlockFinder.getIsolatedRangedTemperatureModifier(level, pos, foundPos, Config.FURNACES_BASE_TEMPERATURE_MODIFIER.get(), Config.FURNACES_TEMPERATURE_FALLOFF.get());
            }
        }

        return temperature;
    }
}
