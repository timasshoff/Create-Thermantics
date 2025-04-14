package com.skytendo.thermantics.block.thermal_sensor;

import com.simibubi.create.content.redstone.diodes.BrassDiodeScrollSlot;
import com.simibubi.create.content.redstone.diodes.BrassDiodeScrollValueBehaviour;
import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import com.simibubi.create.foundation.blockEntity.behaviour.scrollValue.ScrollValueBehaviour;
import com.simibubi.create.foundation.utility.CreateLang;
import com.skytendo.thermantics.Config;
import com.skytendo.thermantics.block.CT_BlockEntities;
import com.skytendo.thermantics.temperature.EnvironmentTemperatureUtil;
import com.skytendo.thermantics.util.ClutchValueBox;
import com.skytendo.thermantics.util.SensorScrollSlot;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class ThermalSensorBlockEntity extends SmartBlockEntity {

    private ScrollValueBehaviour threshold;

    public ThermalSensorBlockEntity(BlockPos pos, BlockState state) {
        super(CT_BlockEntities.THERMAL_SENSOR_BE.get(), pos, state);
    }

    @Override
    public void addBehaviours(List<BlockEntityBehaviour> behaviours) {
        threshold = new ScrollValueBehaviour(
                Component.translatable("thermantics.label.thermal_sensor.temperature_threshold"),
                this,
                new SensorScrollSlot());
        threshold.between(0, 60);
        threshold.withCallback((value) -> update());
        behaviours.add(threshold);
    }

    @Override
    public void tick() {
        if (Math.random() < Config.UPDATE_CHANCE_DEFAULT.get()) {
            update();
        }
    }

    private void update() {
        float environemntTemperature = EnvironmentTemperatureUtil.calculateLevelEnvironmentTemperature(getBlockPos(),getLevel());
        if (environemntTemperature > threshold.getValue()) {
            setChanged();
            getLevel().setBlockAndUpdate(getBlockPos(), getBlockState().setValue(ThermalSensorBlock.ACTIVE, true));
        } else {
            setChanged();
            getLevel().setBlockAndUpdate(getBlockPos(), getBlockState().setValue(ThermalSensorBlock.ACTIVE, false));
        }
    }
}
