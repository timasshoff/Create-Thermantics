package com.skytendo.thermantics.ponder;

import com.simibubi.create.foundation.ponder.CreateSceneBuilder;
import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.api.scene.SceneBuildingUtil;
import net.createmod.ponder.api.scene.Selection;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;

public class ThermalExchangerScenes {
    public static void thermalExchanger(SceneBuilder builder, SceneBuildingUtil util) {
        CreateSceneBuilder scene = new CreateSceneBuilder(builder);
        scene.title("thermal_exchanger", "Using the Thermal Exchanger");
        scene.configureBasePlate(0, 0, 5);
        scene.world().showSection(util.select().layer(0), Direction.UP);

        BlockPos gaugePos = util.grid().at(0, 1, 2);
        Selection gauge = util.select().position(gaugePos);
        scene.world().showSection(gauge, Direction.UP);
        scene.world().setKineticSpeed(gauge, 0);

        scene.idle(5);
        scene.world().showSection(util.select().position(5, 1, 2), Direction.DOWN);
        scene.idle(10);

        for (int i = 4; i >= 1; i--) {
            if (i == 2)
                scene.rotateCameraY(70);
            scene.idle(5);
            scene.world().showSection(util.select().position(i, 1, 2), Direction.DOWN);
        }

        scene.world().setKineticSpeed(gauge, 64);
        scene.effects().indicateSuccess(gaugePos);
        scene.idle(10);
        scene.overlay().showText(1000)
                .placeNearTarget()
                .text("Shafts will relay rotation in a straight line.")
                .pointAt(util.vector().of(3, 1.5, 2.5));

        scene.idle(20);
        scene.markAsFinished();
    }
}
