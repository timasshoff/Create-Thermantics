package com.skytendo.thermantics.block;

import com.simibubi.create.api.stress.BlockStressValues;
import com.simibubi.create.foundation.data.SharedProperties;
import com.simibubi.create.infrastructure.config.CStress;
import com.skytendo.thermantics.Thermantics;
import com.skytendo.thermantics.block.thermal_clutch.ThermalClutchBlock;
import com.skytendo.thermantics.block.thermal_exchanger.ThermalExchangerBlock;
import com.skytendo.thermantics.fluid.CT_Fluids;
import com.skytendo.thermantics.item.CT_Items;
import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateBlockstateProvider;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import static com.simibubi.create.foundation.data.AssetLookup.partialBaseModel;

import java.util.function.Function;
import java.util.function.Supplier;

import static com.simibubi.create.foundation.data.ModelGen.customItemModel;
import static com.simibubi.create.foundation.data.TagGen.axeOrPickaxe;
import static com.skytendo.thermantics.Thermantics.REGISTRATE;

public class CT_Blocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Thermantics.MODID);

    public static final RegistryObject<Block> THERMAL_EXCHANGER = registerBlock("thermal_exchanger",
            () -> new ThermalExchangerBlock(BlockBehaviour.Properties.of()
                    .strength(2.0F, 2.0F).sound(SoundType.WOOD)));

    public static final RegistryObject<LiquidBlock> SCROCHING_COMPOUND_BLOCK = BLOCKS.register("scorching_compound_block",
            () -> new LiquidBlock(CT_Fluids.SOURCE_SCORCHING_COMPOUND, BlockBehaviour.Properties.copy(Blocks.LAVA)));

    public static final RegistryObject<LiquidBlock> COLD_SCROCHING_COMPOUND_BLOCK = BLOCKS.register("cold_scorching_compound_block",
            () -> new LiquidBlock(CT_Fluids.SOURCE_COLD_SCORCHING_COMPOUND, BlockBehaviour.Properties.copy(Blocks.LAVA)));

    public static final RegistryObject<LiquidBlock> SUPERHEATED_SCROCHING_COMPOUND_BLOCK = BLOCKS.register("superheated_scorching_compound_block",
            () -> new LiquidBlock(CT_Fluids.SOURCE_SUPERHEATED_SCORCHING_COMPOUND, BlockBehaviour.Properties.copy(Blocks.LAVA)));

    public static final RegistryObject<LiquidBlock> WARM_FRIGID_COMPOUND_BLOCK = BLOCKS.register("warm_frigid_compound_block",
            () -> new LiquidBlock(CT_Fluids.SOURCE_WARM_FRIGID_COMPOUND, BlockBehaviour.Properties.copy(Blocks.WATER)));

    public static final RegistryObject<LiquidBlock> FRIGID_COMPOUND_BLOCK = BLOCKS.register("frigid_compound_block",
            () -> new LiquidBlock(CT_Fluids.SOURCE_FRIGID_COMPOUND, BlockBehaviour.Properties.copy(Blocks.WATER)));

    public static final BlockEntry<ThermalClutchBlock> THERMAL_CLUTCH = REGISTRATE.block("thermal_clutch", ThermalClutchBlock::new)
            .initialProperties(SharedProperties::stone)
            .properties(p -> p.noOcclusion().mapColor(MapColor.PODZOL))
            .addLayer(() -> RenderType::cutoutMipped)
            .transform(CStress.setNoImpact())
            .transform(axeOrPickaxe())
            .blockstate((c, p) -> p.directionalBlock(c.get(), forBoolean(c, state -> state.getValue(ThermalClutchBlock.UNCOUPLED), "uncoupled", p)))
            .item()
            .transform(customItemModel())
            .register();

    public CT_Blocks() {
    }

    public static void register(IEventBus bus) {
        BLOCKS.register(bus);
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        CT_Items.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    private static Function<BlockState, ModelFile> forBoolean(DataGenContext<?, ?> ctx, Function<BlockState, Boolean> condition, String key, RegistrateBlockstateProvider prov) {
        return state -> condition.apply(state) ? partialBaseModel(ctx, prov, key) : partialBaseModel(ctx, prov);
    }

}
