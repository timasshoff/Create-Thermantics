package com.skytendo.thermantics.block.thermal_clutch;

import com.simibubi.create.content.kinetics.RotationPropagator;
import com.simibubi.create.content.kinetics.transmission.SplitShaftBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import com.simibubi.create.foundation.blockEntity.behaviour.scrollValue.ScrollValueBehaviour;
import com.skytendo.thermantics.Config;
import com.skytendo.thermantics.block.CT_Blocks;
import com.skytendo.thermantics.temperature.PlayerTemperatureManager;
import com.skytendo.thermantics.util.ClutchValueBox;
import net.createmod.catnip.lang.Lang;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.ticks.TickPriority;

import java.util.List;

import static com.skytendo.thermantics.block.thermal_clutch.ThermalClutchBlock.UNCOUPLED;
import static net.minecraft.world.level.block.state.properties.BlockStateProperties.FACING;

public class ThermalClutchBlockEntity extends SplitShaftBlockEntity {

    public static final int DEFAULT_TEMP = 40;
    public static final int MAX_TEMP = 60;

    public ScrollValueBehaviour temperatureThreshold;

    public boolean reattachNextTick = false;

    public ThermalClutchBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public void addBehaviours(List<BlockEntityBehaviour> behaviours) {
        super.addBehaviours(behaviours);
        temperatureThreshold = new ScrollValueBehaviour(
                Component.literal("hallo"),
                this,
                new ClutchValueBox()
        );
        temperatureThreshold.between(0, MAX_TEMP);
        temperatureThreshold.value = DEFAULT_TEMP;
        temperatureThreshold.withCallback(i -> this.update());
        behaviours.add(temperatureThreshold);
    }

    @Override
    public void initialize() {
        update();
        super.initialize();
    }

    private void update() {
        boolean coupled = !getBlockState().getValue(UNCOUPLED);

        if (coupled != thresholdReached() && !isOverStressed()) {
            if (level != null) {
                level.setBlockAndUpdate(getBlockPos(), getBlockState().cycle(UNCOUPLED));
                level.scheduleTick(getBlockPos(), CT_Blocks.THERMAL_CLUTCH.get(), 0, TickPriority.EXTREMELY_HIGH);
                reattachNextTick = true;
            }
        }
    }

    private boolean thresholdReached() {
        return true;
    }

    @Override
    public void tick() {
        super.tick();
        if (Math.random() < Config.UPDATE_CHANCE_DEFAULT.get()) {
            update();
        }
        if (reattachNextTick && level != null) {
            reattachNextTick = false;
            RotationPropagator.handleAdded(level, getBlockPos(), this);
        }
    }

    @Override
    public float getRotationSpeedModifier(Direction face) {
        if (face == getBlockState().getValue(FACING) && getBlockState().getValue(UNCOUPLED))
            return 0;
        return 1;
    }
}
