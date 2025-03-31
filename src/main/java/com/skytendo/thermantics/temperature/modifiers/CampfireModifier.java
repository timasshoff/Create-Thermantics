package com.skytendo.thermantics.temperature.modifiers;

import com.skytendo.thermantics.Config;
import com.skytendo.thermantics.util.BlockFinder;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class CampfireModifier implements TemperatureModifier {
    @Override
    public float modifyTemperature(Player player, Biome biome, float temperature) {
        List<BlockPos> blocks = BlockFinder.findBlocks(player.level(), player.blockPosition(), Config.CAMPFIRE_RANGE.get(), Blocks.CAMPFIRE);
        if(blocks.isEmpty()) {
            return temperature;
        }

        for (BlockPos pos : blocks) {
            BlockState state = player.level().getBlockState(pos);
            if (state.getValue(CampfireBlock.LIT)) {
                temperature += BlockFinder.getIsolatedRangedTemperatureModifier(player.level(), player.blockPosition(), pos, Config.CAMPFIRE_BASE_TEMPERATURE_MODIFIER.get(), Config.CAMPFIRE_TEMPERATURE_FALLOFF.get());
            }
        }

        return temperature;
    }
}
