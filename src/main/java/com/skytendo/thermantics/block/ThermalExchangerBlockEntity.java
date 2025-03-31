package com.skytendo.thermantics.block;

import com.simibubi.create.AllParticleTypes;
import com.simibubi.create.api.equipment.goggles.IHaveGoggleInformation;
import com.simibubi.create.content.fluids.particle.FluidParticleData;
import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import com.simibubi.create.foundation.blockEntity.behaviour.fluid.SmartFluidTankBehaviour;
import com.simibubi.create.foundation.fluid.CombinedTankWrapper;
import com.simibubi.create.foundation.particle.AirParticleData;
import com.simibubi.create.foundation.utility.CreateLang;
import com.skytendo.thermantics.Config;
import com.skytendo.thermantics.Thermantics;
import com.skytendo.thermantics.fluid.CT_Fluids;
import com.skytendo.thermantics.util.CT_FluidTags;
import net.createmod.catnip.lang.LangBuilder;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.*;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import org.checkerframework.checker.units.qual.C;
import org.joml.Vector3f;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ThermalExchangerBlockEntity extends SmartBlockEntity implements IHaveGoggleInformation {

    public enum Mode {
        SUPERHEAT,
        HEAT,
        IDLE,
        COOl
    }

    private SmartFluidTankBehaviour inputTank;
    private SmartFluidTankBehaviour outputTank;
    protected LazyOptional<IFluidHandler> fluidCapability;

    private Mode mode = Mode.IDLE;
    private int ticksLeft = 0;

    public ThermalExchangerBlockEntity(BlockPos pos, BlockState state) {
        super(CT_BlockEntities.THERMAL_EXCHANGER_BE.get(), pos, state);
    }

    @Override
    public void addBehaviours(List<BlockEntityBehaviour> behaviours) {
        inputTank = new SmartFluidTankBehaviour(SmartFluidTankBehaviour.INPUT, this, 1, 1000, true)
                .forbidExtraction();
        outputTank = new SmartFluidTankBehaviour(SmartFluidTankBehaviour.OUTPUT, this, 1, 1000, true)
                .forbidInsertion();

        behaviours.add(inputTank);
        behaviours.add(outputTank);

        fluidCapability = LazyOptional.of(() -> {
            LazyOptional<? extends IFluidHandler> inputCap = inputTank.getCapability();
            LazyOptional<? extends IFluidHandler> outputCap = outputTank.getCapability();
            return new CombinedTankWrapper(outputCap.orElse(null), inputCap.orElse(null));
        });
    }

    @Override
    public void tick() {
        super.tick();

        if (level.isClientSide) { // Render particles
            Random r = new Random();
            if (r.nextFloat() < 0.3f) {
                List<Vector3f> positions = new ArrayList<>();
                for (int i = 0; i < r.nextInt(3, 8); i++) {
                    positions.add(new Vector3f(
                            worldPosition.getX() + r.nextFloat(0.1f, 0.9f),
                            worldPosition.getY() + 1.1f,
                            worldPosition.getZ() + r.nextFloat(0.1f, 0.9f)));
                }

                ParticleOptions particle = null;
                if (mode == Mode.HEAT) {
                    particle = ParticleTypes.SMOKE;
                }
                if (mode == Mode.SUPERHEAT) {
                    particle = ParticleTypes.LARGE_SMOKE;
                }
                if (mode == Mode.COOl) {
                    particle = null;
                }
                if (particle == null) {
                    return;
                }
                for (Vector3f pos : positions) {
                    level.addParticle(particle,
                            pos.x, pos.y, pos.z,
                            0, 0, 0);
                }
            }
            return;
        }

        if (ticksLeft <= 0) { // Go idle
            ticksLeft = 0;
            mode = Mode.IDLE;
            handleIdle();
        } else {
            ticksLeft--;
        }
        notifyUpdate();
    }

    private void handleIdle() {
        FluidStack input = inputTank.getPrimaryHandler().getFluid();
        if (input.isEmpty()) {
            return;
        }

        if (input.getFluid() == CT_Fluids.SOURCE_SCORCHING_COMPOUND.get()) {
            simulateAndTransferFuel(CT_Fluids.SOURCE_COLD_SCORCHING_COMPOUND.get(), Mode.HEAT);
        }
        if (input.getFluid() == CT_Fluids.SOURCE_SUPERHEATED_SCORCHING_COMPOUND.get()) {
            simulateAndTransferFuel(CT_Fluids.SOURCE_COLD_SCORCHING_COMPOUND.get(), Mode.SUPERHEAT);
        }
        if (input.getFluid() == CT_Fluids.SOURCE_FRIGID_COMPOUND.get()) {
            simulateAndTransferFuel(CT_Fluids.SOURCE_WARM_FRIGID_COMPOUND.get(), Mode.COOl);
        }
    }

    private void simulateAndTransferFuel(Fluid output, Mode mode) {
        inputTank.allowExtraction();
        outputTank.allowInsertion();
        if (simulateTransferFuel(output)) {
            transferFuel(output);
            this.mode = mode;
        }
        inputTank.forbidExtraction();
        outputTank.forbidInsertion();
    }

    private boolean simulateTransferFuel(Fluid output) {
        int simulatedAmountDrained = inputTank.getPrimaryHandler().drain(Config.THERMAL_EXCHANGER_CONSUMPTION.get(), IFluidHandler.FluidAction.SIMULATE).getAmount();
        int simulatedAmountFilled = outputTank.getPrimaryHandler().fill(new FluidStack(output, simulatedAmountDrained), IFluidHandler.FluidAction.SIMULATE);
        if (simulatedAmountFilled != simulatedAmountDrained && simulatedAmountDrained != 0) {
            return false;
        }
        return true;
    }

    private void transferFuel(Fluid output) {
        int amountDrained = inputTank.getPrimaryHandler().drain(Config.THERMAL_EXCHANGER_CONSUMPTION.get(), IFluidHandler.FluidAction.EXECUTE).getAmount();
        outputTank.getPrimaryHandler().fill(new FluidStack(output, amountDrained), IFluidHandler.FluidAction.EXECUTE);
        ticksLeft = (int) (amountDrained * Config.THERMAL_EXCHANGER_TICKS_PER_MB.get());
    }

    public Mode getMode() {
        return mode;
    }

    public SmartFluidTankBehaviour getInputTank() {
        return inputTank;
    }

    public SmartFluidTankBehaviour getOutputTank() {
        return outputTank;
    }

    @Override
    protected void read(CompoundTag tag, boolean clientPacket) {
        super.read(tag, clientPacket);
        ticksLeft = tag.getInt("ticksLeft");
        mode = Mode.values()[tag.getInt("mode")];
    }

    @Override
    protected void write(CompoundTag tag, boolean clientPacket) {
        super.write(tag, clientPacket);
        tag.putInt("ticksLeft", ticksLeft);
        tag.putInt("mode", mode.ordinal());
    }

    @Override
    public void invalidate() {
        super.invalidate();
        fluidCapability.invalidate();
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, Direction side) {
        if (cap == ForgeCapabilities.FLUID_HANDLER)
            return fluidCapability.cast();
        return super.getCapability(cap, side);
    }

    @Override
    public boolean addToGoggleTooltip(List<Component> tooltip, boolean isPlayerSneaking) {
        LangBuilder mb = CreateLang.translate("generic.unit.millibuckets");

        new LangBuilder(Thermantics.MODID).translate("googles.thermal_exchanger.title").forGoggles(tooltip);

        new LangBuilder(Thermantics.MODID).translate("googles.thermal_exchanger.mode").style(ChatFormatting.GRAY).forGoggles(tooltip);
        ChatFormatting modeFormatting = ChatFormatting.GRAY;
        if (mode == Mode.COOl)
            modeFormatting = ChatFormatting.AQUA;
        if (mode == Mode.HEAT)
            modeFormatting = ChatFormatting.RED;
        if (mode == Mode.SUPERHEAT)
            modeFormatting = ChatFormatting.DARK_RED;
        new LangBuilder(Thermantics.MODID).add(Component.literal(mode.name())).style(modeFormatting).forGoggles(tooltip, 1);

        new LangBuilder(Thermantics.MODID).translate("googles.thermal_exchanger.input").style(ChatFormatting.GRAY).forGoggles(tooltip);
        new LangBuilder(Thermantics.MODID).add(CreateLang.fluidName(inputTank.getPrimaryHandler().getFluid()).style(ChatFormatting.GRAY)).forGoggles(tooltip, 1);
        new LangBuilder(Thermantics.MODID)
                .add(CreateLang.number(inputTank.getPrimaryHandler().getFluid().getAmount())
                        .add(mb)
                        .style(ChatFormatting.GOLD))
                .text(ChatFormatting.GRAY, " / ")
                .add(CreateLang.number(inputTank.getPrimaryHandler().getCapacity())
                        .add(mb)
                        .style(ChatFormatting.DARK_GRAY))
                .forGoggles(tooltip, 1);

        new LangBuilder(Thermantics.MODID).translate("googles.thermal_exchanger.output").style(ChatFormatting.GRAY).forGoggles(tooltip);
        new LangBuilder(Thermantics.MODID).add(CreateLang.fluidName(outputTank.getPrimaryHandler().getFluid()).style(ChatFormatting.GRAY)).forGoggles(tooltip, 1);
        new LangBuilder(Thermantics.MODID)
                .add(CreateLang.number(outputTank.getPrimaryHandler().getFluid().getAmount())
                        .add(mb)
                        .style(ChatFormatting.GOLD))
                .text(ChatFormatting.GRAY, " / ")
                .add(CreateLang.number(outputTank.getPrimaryHandler().getCapacity())
                        .add(mb)
                        .style(ChatFormatting.DARK_GRAY))
                .forGoggles(tooltip, 1);
        return true;
    }
}
