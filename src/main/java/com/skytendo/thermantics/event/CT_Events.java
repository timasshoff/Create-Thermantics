package com.skytendo.thermantics.event;

import com.skytendo.thermantics.Thermantics;
import com.skytendo.thermantics.temperature.PlayerTemperature;
import com.skytendo.thermantics.temperature.PlayerTemperatureManager;
import com.skytendo.thermantics.temperature.PlayerTemperatureProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Thermantics.MODID)
public class CT_Events {

    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if(event.getObject() instanceof Player) {
            if(!event.getObject().getCapability(PlayerTemperatureProvider.PLAYER_TEMPERATURE).isPresent()) {
                event.addCapability(new ResourceLocation(Thermantics.MODID, "properties"), new PlayerTemperatureProvider());
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if(event.isWasDeath()) {
            event.getOriginal().getCapability(PlayerTemperatureProvider.PLAYER_TEMPERATURE).ifPresent(oldStore -> {
                event.getOriginal().getCapability(PlayerTemperatureProvider.PLAYER_TEMPERATURE).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                });
            });
        }
    }

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(PlayerTemperature.class);
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if(event.side == LogicalSide.SERVER) {
            event.player.getCapability(PlayerTemperatureProvider.PLAYER_TEMPERATURE).ifPresent(temperature -> {
                if(event.player.getRandom().nextFloat() < 0.005f) { // Once Every 10 Seconds on Avg
                    PlayerTemperatureManager.updateTemperature(temperature, event);
                }
            });
        }
    }
}
