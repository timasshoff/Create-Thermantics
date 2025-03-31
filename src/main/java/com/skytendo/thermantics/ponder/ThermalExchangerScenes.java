package com.skytendo.thermantics.ponder;

import com.simibubi.create.content.fluids.tank.FluidTankBlockEntity;
import com.simibubi.create.foundation.ponder.CreateSceneBuilder;
import com.skytendo.thermantics.fluid.CT_Fluids;
import net.createmod.ponder.api.ParticleEmitter;
import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.api.scene.SceneBuildingUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;

public class ThermalExchangerScenes {
    public static void thermalExchanger(SceneBuilder builder, SceneBuildingUtil util) {
        CreateSceneBuilder scene = new CreateSceneBuilder(builder);
        scene.title("thermal_exchanger", "thermantics.ponder.thermal_exchanger.header");
        scene.configureBasePlate(0, 0, 5);
        scene.world().showSection(util.select().layer(0), Direction.UP);
        scene.world().showSection(util.select().position(2, 1, 2), Direction.DOWN);

        scene.overlay().showText(50)
                .placeNearTarget()
                .text("thermantics.ponder.thermal_exchanger.text_1")
                .pointAt(util.vector().of(2.5, 1.5, 2.5));
        scene.idle(60);

        // L
        scene.world().showSection(util.select().position(4, 1, 2), Direction.DOWN);
        scene.world().showSection(util.select().position(4, 2, 2), Direction.DOWN);
        scene.world().showSection(util.select().position(3, 1, 2), Direction.DOWN);
        scene.world().showSection(util.select().position(2, 1, 3), Direction.DOWN);
        scene.world().showSection(util.select().position(3, 1, 3), Direction.DOWN);
        scene.world().showSection(util.select().position(4, 1, 3), Direction.DOWN);
        scene.idle(5);

        scene.overlay().showText(50)
                .placeNearTarget()
                .text("thermantics.ponder.thermal_exchanger.text_2")
                .pointAt(util.vector().of(3.5, 1.5, 2.5));
        scene.idle(60);

        // R
        scene.world().showSection(util.select().position(0, 1, 2), Direction.DOWN);
        scene.world().showSection(util.select().position(0, 2, 2), Direction.DOWN);
        scene.world().showSection(util.select().position(1, 1, 2), Direction.DOWN);
        scene.world().showSection(util.select().position(1, 1, 3), Direction.DOWN);
        scene.idle(5);

        scene.overlay().showText(50)
                .placeNearTarget()
                .text("thermantics.ponder.thermal_exchanger.text_3")
                .pointAt(util.vector().of(1.5, 1.5, 2.5));
        scene.idle(60);

        BlockPos inputPos = util.grid().at(4, 1, 2);
        FluidStack inputContent = new FluidStack(CT_Fluids.SOURCE_SCORCHING_COMPOUND.get()
                .getSource(), 16000);
        scene.world().modifyBlockEntity(inputPos, FluidTankBlockEntity.class, be -> be.getTankInventory()
                .fill(inputContent, IFluidHandler.FluidAction.EXECUTE));

        scene.overlay().showText(90)
                .placeNearTarget()
                .text("thermantics.ponder.thermal_exchanger.text_4")
                .pointAt(util.vector().of(2.5, 1.5, 2.5));

        scene.effects().indicateSuccess(new BlockPos(2, 1, 2));
        ParticleEmitter emitter = scene.effects().simpleParticleEmitter(ParticleTypes.SMOKE, util.vector().of(0, 0.5, 0));
        scene.effects().emitParticles(util.vector().topOf(new BlockPos(2, 1, 2)), emitter, 2, 80);

        scene.idle(100);

        scene.world().modifyBlockEntity(inputPos, FluidTankBlockEntity.class, be -> be.getTankInventory()
                .drain(16000, IFluidHandler.FluidAction.EXECUTE));

        BlockPos outputPos = util.grid().at(0, 1, 2);
        FluidStack outputContent = new FluidStack(CT_Fluids.SOURCE_COLD_SCORCHING_COMPOUND.get()
                .getSource(), 16000);
        scene.world().modifyBlockEntity(outputPos, FluidTankBlockEntity.class, be -> be.getTankInventory()
                .fill(outputContent, IFluidHandler.FluidAction.EXECUTE));

        scene.overlay().showText(90)
                .placeNearTarget()
                .text("thermantics.ponder.thermal_exchanger.text_5")
                .pointAt(util.vector().of(0.5, 1.5, 2.5));

        scene.idle(90);
        scene.markAsFinished();
    }
}
