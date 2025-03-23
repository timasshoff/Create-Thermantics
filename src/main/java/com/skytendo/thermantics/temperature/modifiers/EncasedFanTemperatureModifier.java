package com.skytendo.thermantics.temperature.modifiers;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.kinetics.fan.AirCurrent;
import com.simibubi.create.content.kinetics.fan.EncasedFanBlockEntity;
import com.simibubi.create.content.kinetics.fan.processing.AllFanProcessingTypes;
import com.simibubi.create.content.kinetics.fan.processing.FanProcessingType;
import com.simibubi.create.infrastructure.config.AllConfigs;
import com.skytendo.thermantics.util.BlockFinder;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.biome.Biome;

import java.util.List;

public class EncasedFanTemperatureModifier implements TemperatureModifier{
    @Override
    public float modifyTemperature(Player player, Biome biome, float temperature) {

        List<BlockPos> blocks = BlockFinder.findBlocks(player.level(), player.blockPosition(), AllConfigs.server().kinetics.fanPushDistance.get() + 1, AllBlocks.ENCASED_FAN.get());
        if(blocks.isEmpty()) {
            return temperature;
        }

        for (BlockPos fanPos : blocks) {
            EncasedFanBlockEntity fan = (EncasedFanBlockEntity) player.level().getBlockEntity(fanPos);
            if (fan == null || fan.getSpeed() > 0) {
                continue;
            }

            AirCurrent current = fan.getAirCurrent();
            if (current == null) {
                continue;
            }

            for (int i = 0; i < current.maxDistance; i++) {
                FanProcessingType type = current.getTypeAt(i);
                if (type == null) {
                    continue;
                }

                if (type.equals(AllFanProcessingTypes.SMOKING)) {
                    temperature += BlockFinder.getIsolatedRangedTemperatureModifier(player.level(), player.blockPosition(), fan.getAirCurrentPos().relative(current.direction, i), 3.5, 0.2);
                }
                if (type.equals(AllFanProcessingTypes.BLASTING)) {
                    temperature += BlockFinder.getIsolatedRangedTemperatureModifier(player.level(), player.blockPosition(), fan.getAirCurrentPos().relative(current.direction, i), 5.5, 0.2);
                }
                if (type.equals(AllFanProcessingTypes.SPLASHING)) {
                    temperature += BlockFinder.getIsolatedRangedTemperatureModifier(player.level(), player.blockPosition(), fan.getAirCurrentPos().relative(current.direction, i), -3.5, 0.4);
                }
            }
        }
        return temperature;
    }
}
