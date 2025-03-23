package com.skytendo.thermantics.event;

import com.skytendo.thermantics.Thermantics;
import com.skytendo.thermantics.client.TemperatureHudOverlay;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Thermantics.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CT_ClientModBusEvents {
    @SubscribeEvent
    public static void registerGuiOverlays(RegisterGuiOverlaysEvent event) {
        event.registerAboveAll("thermometer", TemperatureHudOverlay.HUD_THERMOMETER);
    }
}
