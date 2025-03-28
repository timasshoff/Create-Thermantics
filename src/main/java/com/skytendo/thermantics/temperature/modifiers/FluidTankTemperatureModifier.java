package com.skytendo.thermantics.temperature.modifiers;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllTags;
import com.simibubi.create.content.fluids.tank.FluidTankBlockEntity;
import com.skytendo.thermantics.Config;
import com.skytendo.thermantics.block.CT_Blocks;
import com.skytendo.thermantics.block.ThermalExchangerBlockEntity;
import com.skytendo.thermantics.util.BlockFinder;
import com.skytendo.thermantics.util.CT_FluidTags;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.material.Fluid;

import java.util.List;

public class FluidTankTemperatureModifier implements TemperatureModifier{
    @Override
    public float modifyTemperature(Player player, Biome biome, float temperature) {
        List<BlockPos> blocks = BlockFinder.findBlocks(player.level(), player.blockPosition(), Config.FLUID_TANK_RANGE.get(), AllBlocks.FLUID_TANK.get());
        if(blocks.isEmpty()) {
            return temperature;
        }

        for (BlockPos exchangerPos : blocks) {
            FluidTankBlockEntity tank = (FluidTankBlockEntity) player.level().getBlockEntity(exchangerPos);
            if (tank == null) {
                continue;
            }

            Fluid fluid = tank.getTankInventory().getFluid().getFluid();
            double modifier = (tank.getTankInventory().getFluidAmount() / 1000f) * Config.FLUID_TANK_FLUID_BUCKET_AMOUNT_TEMPERATURE_MODIFIER.get();

            if (fluid.is(CT_FluidTags.FLUID_TANK_HOT_CONTENT)) {
                temperature += BlockFinder.getIsolatedRangedTemperatureModifier(player.level(), player.blockPosition(), exchangerPos, Config.FLUID_TANK_HOT_BASE_TEMPERATURE_MODIFIER.get() + modifier, Config.FLUID_TANK_HOT_TEMPERATURE_FALLOFF.get());
            }
            if (fluid.is(CT_FluidTags.FLUID_TANK_COLD_CONTENT)) {
                temperature += BlockFinder.getIsolatedRangedTemperatureModifier(player.level(), player.blockPosition(), exchangerPos, Config.FLUID_TANK_COLD_BASE_TEMPERATURE_MODIFIER.get() - modifier, Config.FLUID_TANK_COLD_TEMPERATURE_FALLOFF.get());
            }
        }

        return temperature;
    }
}
