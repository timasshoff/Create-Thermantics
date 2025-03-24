package com.skytendo.thermantics.util;

import com.skytendo.thermantics.Config;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.List;

public class BlockFinder {
    public static float checkAndCalculateTemperatureModifier(Level world, BlockPos player, Block block, int range, double blockBaseTemperatureModifier, double rangeFalloff) {
        if (world == null || player == null || block == null) {
            return 0.0f;
        }

        float result = 0.0f;
        List<BlockPos> blocks = findBlocks(world, player, range, block);
        for (BlockPos pos : blocks) {
            result += getIsolatedRangedTemperatureModifier(world, player, pos, blockBaseTemperatureModifier, rangeFalloff);
        }
        return Math.min(Math.max(result, -60.0f), 60.0f);
    }

    public static List<BlockPos> findBlocks(Level world, BlockPos center, int radius, Block targetBlock) {
        List<BlockPos> foundBlocks = new ArrayList<>();

        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    BlockPos pos = center.offset(x, y, z);
                    if (world.getBlockState(pos).getBlock().equals(targetBlock)) {
                        foundBlocks.add(pos);
                    }
                }
            }
        }

        return foundBlocks;
    }

    public static float getIsolatedRangedTemperatureModifier(Level world, BlockPos player, BlockPos block, double baseTemperature, double rangeFalloff) {
        float distance = (float) Math.sqrt(player.distSqr(block));
        float rangedTemperature = (float) (baseTemperature * Math.exp(-rangeFalloff * distance));

        float isolation = 0.0f;
        List<BlockPos> blocks = getBlocksBetween(world, player, block);
        if (blocks.size() > 1) {
            for (BlockPos pos : blocks) {
                BlockState state = world.getBlockState(pos);
                if (state.is(CT_BlockTags.LOW_ISOLATING)) {
                    isolation += Config.LOW_ISOLATION.get();
                } else if (state.is(CT_BlockTags.HIGH_ISOLATING)) {
                    isolation += Config.HIGH_ISOLATION.get();
                } else if (state.is(CT_BlockTags.EXTREME_ISOLATING)) {
                    isolation += Config.EXTREME_ISOLATION.get();
                } else {
                    isolation += Config.DEFAULT_ISOLATION.get();
                }
            }
            if (baseTemperature < 0) {
                isolation *= -1;
            }
        }

        if (baseTemperature < 0) {
            return Math.min(rangedTemperature - isolation, 0.0f);
        } else {
            return Math.max(rangedTemperature - isolation, 0.0f);
        }
    }

    public static List<BlockPos> getBlocksBetween(Level world, BlockPos start, BlockPos end) {
        List<BlockPos> blocks = new ArrayList<>();

        int dx = Math.abs(end.getX() - start.getX());
        int dy = Math.abs(end.getY() - start.getY());
        int dz = Math.abs(end.getZ() - start.getZ());

        int sx = start.getX() < end.getX() ? 1 : -1;
        int sy = start.getY() < end.getY() ? 1 : -1;
        int sz = start.getZ() < end.getZ() ? 1 : -1;

        int x = start.getX();
        int y = start.getY();
        int z = start.getZ();

        if (dx >= dy && dx >= dz) {
            int yd = 2 * dy - dx;
            int zd = 2 * dz - dx;
            while (x != end.getX()) {
                BlockPos pos = new BlockPos(x, y, z);
                if (isSolid(world, pos)) {
                    blocks.add(pos);
                }
                if (yd > 0) {
                    y += sy;
                    yd -= 2 * dx;
                }
                if (zd > 0) {
                    z += sz;
                    zd -= 2 * dx;
                }
                yd += 2 * dy;
                zd += 2 * dz;
                x += sx;
            }
        } else if (dy >= dx && dy >= dz) {
            int xd = 2 * dx - dy;
            int zd = 2 * dz - dy;
            while (y != end.getY()) {
                BlockPos pos = new BlockPos(x, y, z);
                if (isSolid(world, pos)) {
                    blocks.add(pos);
                }
                if (xd > 0) {
                    x += sx;
                    xd -= 2 * dy;
                }
                if (zd > 0) {
                    z += sz;
                    zd -= 2 * dy;
                }
                xd += 2 * dx;
                zd += 2 * dz;
                y += sy;
            }
        } else {
            int xd = 2 * dx - dz;
            int yd = 2 * dy - dz;
            while (z != end.getZ()) {
                BlockPos pos = new BlockPos(x, y, z);
                if (isSolid(world, pos)) {
                    blocks.add(pos);
                }
                if (xd > 0) {
                    x += sx;
                    xd -= 2 * dz;
                }
                if (yd > 0) {
                    y += sy;
                    yd -= 2 * dz;
                }
                xd += 2 * dx;
                yd += 2 * dy;
                z += sz;
            }
        }
        if (isSolid(world, end)) {
            blocks.add(end);
        }

        return blocks;
    }

    private static boolean isSolid(Level world, BlockPos pos) {
        BlockState state = world.getBlockState(pos);
        return !state.isAir() && state.isSolid();
    }
}
