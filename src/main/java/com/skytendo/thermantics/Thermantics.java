package com.skytendo.thermantics;

import com.mojang.logging.LogUtils;
import com.simibubi.create.AllCreativeModeTabs;
import com.simibubi.create.AllItems;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.skytendo.thermantics.block.CT_BlockEntities;
import com.skytendo.thermantics.block.CT_Blocks;
import com.skytendo.thermantics.client.ClientTemperatureData;
import com.skytendo.thermantics.effect.CT_Effects;
import com.skytendo.thermantics.effect.CT_Potions;
import com.skytendo.thermantics.enchantment.CT_Enchantments;
import com.skytendo.thermantics.fluid.CT_FluidTypes;
import com.skytendo.thermantics.fluid.CT_Fluids;
import com.skytendo.thermantics.item.CT_CreativeTabs;
import com.skytendo.thermantics.item.CT_Items;
import com.skytendo.thermantics.loot.CT_LootModifiers;
import com.skytendo.thermantics.networking.CT_Messages;
import com.skytendo.thermantics.ponder.ThermanticsPonder;
import com.skytendo.thermantics.temperature.modifiers.TemperatureModifierRegistry;
import com.skytendo.thermantics.util.CT_BrewingRecipe;
import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import net.createmod.ponder.foundation.PonderIndex;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Thermantics.MODID)
public class Thermantics
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "thermantics";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public static final NonNullSupplier<Registrate> REGISTRATE = NonNullSupplier.lazy(() -> Registrate.create(MODID));

    public Thermantics(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();

        context.registerConfig(ModConfig.Type.SERVER, Config.SPEC);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        CT_CreativeTabs.register(modEventBus);
        CT_Items.register(modEventBus);
        CT_Blocks.register(modEventBus);
        CT_BlockEntities.register(modEventBus);
        CT_FluidTypes.register(modEventBus);
        CT_Fluids.register(modEventBus);
        CT_Effects.register(modEventBus);
        CT_Potions.register(modEventBus);
        CT_LootModifiers.register(modEventBus);
        CT_Enchantments.register(modEventBus);

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");

        CT_Messages.register();
        TemperatureModifierRegistry.registerModifiers();

        BrewingRecipeRegistry.addRecipe(new CT_BrewingRecipe(
                Potions.AWKWARD,
                AllItems.POWDERED_OBSIDIAN.get(),
                CT_Potions.CLEMENCY.get()
        ));
    }


    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            ItemBlockRenderTypes.setRenderLayer(CT_Fluids.SOURCE_SCORCHING_COMPOUND.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(CT_Fluids.FLOWING_SCORCHING_COMPOUND.get(), RenderType.translucent());

            ItemBlockRenderTypes.setRenderLayer(CT_Fluids.SOURCE_SUPERHEATED_SCORCHING_COMPOUND.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(CT_Fluids.FLOWING_SUPERHEATED_SCORCHING_COMPOUND.get(), RenderType.translucent());

            ItemBlockRenderTypes.setRenderLayer(CT_Fluids.SOURCE_COLD_SCORCHING_COMPOUND.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(CT_Fluids.FLOWING_COLD_SCORCHING_COMPOUND.get(), RenderType.translucent());

            ItemBlockRenderTypes.setRenderLayer(CT_Fluids.SOURCE_FRIGID_COMPOUND.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(CT_Fluids.FLOWING_FRIGID_COMPOUND.get(), RenderType.translucent());

            ItemBlockRenderTypes.setRenderLayer(CT_Fluids.SOURCE_WARM_FRIGID_COMPOUND.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(CT_Fluids.FLOWING_WARM_FRIGID_COMPOUND.get(), RenderType.translucent());

            PonderIndex.addPlugin(new ThermanticsPonder());

            event.enqueueWork(() -> {
                ItemProperties.register(CT_Items.THERMOMETER.get(), ResourceLocation.fromNamespaceAndPath(Thermantics.MODID, "temperature"), (stack, world, entity, id) -> {
                    float temperature = ClientTemperatureData.get();
                    return temperature / 100f;
                });
            });
        }
    }
}
