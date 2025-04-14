package com.skytendo.thermantics.block.thermal_clutch;

import com.simibubi.create.content.kinetics.RotationPropagator;
import com.simibubi.create.content.kinetics.base.DirectionalKineticBlock;
import com.simibubi.create.foundation.block.IBE;
import com.skytendo.thermantics.block.CT_BlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.jetbrains.annotations.NotNull;

public class ThermalClutchBlock extends DirectionalKineticBlock implements IBE<ThermalClutchBlockEntity> {

    public static final BooleanProperty UNCOUPLED = BooleanProperty.create("uncoupled");

    public ThermalClutchBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(UNCOUPLED, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(UNCOUPLED);
        super.createBlockStateDefinition(builder);
    }

    @Override
    public Class<ThermalClutchBlockEntity> getBlockEntityClass() {
        return ThermalClutchBlockEntity.class;
    }

    @Override
    public BlockEntityType<? extends ThermalClutchBlockEntity> getBlockEntityType() {
        return CT_BlockEntities.THERMAL_CLUTCH.get();
    }

    @Override
    public Direction.Axis getRotationAxis(BlockState state) {
        return state.getValue(FACING).getAxis();
    }

    @Override
    public boolean hasShaftTowards(LevelReader world, BlockPos pos, BlockState state, Direction face) {
        return face.getAxis() == state.getValue(FACING).getAxis();
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean hasAnalogOutputSignal(@NotNull BlockState state) {
        return true;
    }

    @SuppressWarnings("deprecation")
    @Override
    public int getAnalogOutputSignal(@NotNull BlockState pState, @NotNull Level pLevel, @NotNull BlockPos pPos) {
        return pState.getValue(UNCOUPLED) ? 0 : 15;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void tick(@NotNull BlockState pState, @NotNull ServerLevel pLevel, @NotNull BlockPos pPos, @NotNull RandomSource pRandom) {
        BlockEntity be = pLevel.getBlockEntity(pPos);
        if (!(be instanceof ThermalClutchBlockEntity kte))
            return;

        if (kte.reattachNextTick) {
            RotationPropagator.handleRemoved(pLevel, pPos, kte);
        }
    }
}
