package com.skytendo.thermantics.temperature.modifiers;

import com.skytendo.thermantics.Config;
import com.skytendo.thermantics.util.BlockFinder;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class CampfireModifierPlayer implements LevelTemperatureModifier {

    @Override
    public float modifyTemperature(BlockPos pos, Level level, float temperature) {
        List<BlockPos> blocks = BlockFinder.findBlocks(level, pos, Config.CAMPFIRE_RANGE.get(), Blocks.CAMPFIRE);
        if(blocks.isEmpty()) {
            return temperature;
        }

        for (BlockPos campfirePos : blocks) {
            BlockState state = level.getBlockState(campfirePos);
            if (state.getValue(CampfireBlock.LIT)) {
                temperature += BlockFinder.getIsolatedRangedTemperatureModifier(level, pos, campfirePos, Config.CAMPFIRE_BASE_TEMPERATURE_MODIFIER.get(), Config.CAMPFIRE_TEMPERATURE_FALLOFF.get());
            }
        }

        return temperature;
    }
}
