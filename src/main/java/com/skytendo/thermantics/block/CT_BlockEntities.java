package com.skytendo.thermantics.block;

import com.simibubi.create.content.kinetics.transmission.SplitShaftRenderer;
import com.simibubi.create.content.kinetics.transmission.SplitShaftVisual;
import com.skytendo.thermantics.Thermantics;
import com.skytendo.thermantics.block.thermal_clutch.ThermalClutchBlockEntity;
import com.skytendo.thermantics.block.thermal_exchanger.ThermalExchangerBlockEntity;
import com.tterrag.registrate.util.entry.BlockEntityEntry;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.skytendo.thermantics.Thermantics.REGISTRATE;

public class CT_BlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Thermantics.MODID);

    public static final RegistryObject<BlockEntityType<ThermalExchangerBlockEntity>> THERMAL_EXCHANGER_BE = BLOCK_ENTITIES.register("thermal_exchanger_be",
            () -> BlockEntityType.Builder.of(ThermalExchangerBlockEntity::new, CT_Blocks.THERMAL_EXCHANGER.get()).build(null));

    public static final BlockEntityEntry<ThermalClutchBlockEntity> THERMAL_CLUTCH = REGISTRATE
            .blockEntity("thermal_clutch", ThermalClutchBlockEntity::new)
            .visual(() -> SplitShaftVisual::new, false)
            .validBlocks(CT_Blocks.THERMAL_CLUTCH)
            .renderer(() -> SplitShaftRenderer::new)
            .register();

    public static void register(IEventBus bus) {
        BLOCK_ENTITIES.register(bus);
    }
}
