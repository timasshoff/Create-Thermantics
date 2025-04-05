package com.skytendo.thermantics.event;

import com.skytendo.thermantics.Config;
import com.skytendo.thermantics.Thermantics;
import com.skytendo.thermantics.effect.CT_Effects;
import com.skytendo.thermantics.networking.CT_Messages;
import com.skytendo.thermantics.networking.packet.TemperatureDataSyncS2CPacket;
import com.skytendo.thermantics.temperature.PlayerTemperature;
import com.skytendo.thermantics.temperature.PlayerTemperatureManager;
import com.skytendo.thermantics.temperature.PlayerTemperatureProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
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
                event.addCapability(ResourceLocation.fromNamespaceAndPath(Thermantics.MODID, "properties"), new PlayerTemperatureProvider());
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
                if(event.player.getRandom().nextFloat() < PlayerTemperatureManager.getUpdateChance(event.player)) {
                    PlayerTemperatureManager.updateTemperature(temperature, event);
                }
                PlayerTemperatureManager.updateTemperatureState(temperature, event);
                PlayerTemperatureManager.applyTemperatureEffects(temperature, event);
            });
        }
    }

    @SubscribeEvent
    public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
       if (Config.DEATH_CLEMENCY.get()) {
           event.getEntity().addEffect(new MobEffectInstance(CT_Effects.CLEMENCY.get(), Config.DEATH_CLEMENCY_DURATION.get(), 0, false, false, true));
       }
    }

    @SubscribeEvent
    public static void onPlayerJoinWorld(EntityJoinLevelEvent event) {
        if (!event.getLevel().isClientSide()) {
            if (event.getEntity() instanceof ServerPlayer serverPlayer) {
                event.getEntity().getCapability(PlayerTemperatureProvider.PLAYER_TEMPERATURE).ifPresent(temperature -> {
                    CT_Messages.sendToPlayer(new TemperatureDataSyncS2CPacket(temperature.getTemperature()), serverPlayer);
                });
            }
        }
    }
}
