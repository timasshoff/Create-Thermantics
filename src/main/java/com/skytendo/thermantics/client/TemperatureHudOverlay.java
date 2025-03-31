package com.skytendo.thermantics.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.skytendo.thermantics.Thermantics;
import com.skytendo.thermantics.temperature.PlayerTemperature;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class TemperatureHudOverlay {

    private static final ResourceLocation THERMOMETER_EMPTY = ResourceLocation.fromNamespaceAndPath(Thermantics.MODID, "textures/temperature/thermometer_empty.png");
    private static final ResourceLocation THERMOMETER_FREEZING = ResourceLocation.fromNamespaceAndPath(Thermantics.MODID, "textures/temperature/thermometer_freezing.png");
    private static final ResourceLocation THERMOMETER_COLD = ResourceLocation.fromNamespaceAndPath(Thermantics.MODID, "textures/temperature/thermometer_cold.png");
    private static final ResourceLocation THERMOMETER_CHILLY = ResourceLocation.fromNamespaceAndPath(Thermantics.MODID, "textures/temperature/thermometer_chilly.png");
    private static final ResourceLocation THERMOMETER_WARM = ResourceLocation.fromNamespaceAndPath(Thermantics.MODID, "textures/temperature/thermometer_warm.png");
    private static final ResourceLocation THERMOMETER_HOT = ResourceLocation.fromNamespaceAndPath(Thermantics.MODID, "textures/temperature/thermometer_hot.png");
    private static final ResourceLocation THERMOMETER_FIERY = ResourceLocation.fromNamespaceAndPath(Thermantics.MODID, "textures/temperature/thermometer_fiery.png");

    public static final IGuiOverlay HUD_THERMOMETER = ((gui, guiGraphics, partialTick, width, height) -> {
        int x = width / 2;
        int y = height;

        if (Minecraft.getInstance().player.isCreative()) {
            return;
        }

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        PlayerTemperature.TemperatureState temperatureState = PlayerTemperature.temperatureToState(ClientTemperatureData.get());
        ResourceLocation texture = THERMOMETER_EMPTY;
        switch (temperatureState) {
            case FREEZING:
                texture = THERMOMETER_FREEZING;
                break;
            case COLD:
                texture = THERMOMETER_COLD;
                break;
            case CHILLY:
                texture = THERMOMETER_CHILLY;
                break;
            case WARM:
                texture = THERMOMETER_WARM;
                break;
            case HOT:
                texture = THERMOMETER_HOT;
                break;
            case FIERY:
                texture = THERMOMETER_FIERY;
                break;
        }

        RenderSystem.setShaderTexture(0, texture);
        int handId = Minecraft.getInstance().options.mainHand().get().getId();
        if (handId == 1) {
            guiGraphics.blit(texture, x + 90, y - 27, 0, 0, 28, 28, 28, 28);
        } else if (handId == 0) {
            guiGraphics.blit(texture, x - 118, y - 27, 0, 0, 28, 28, 28, 28);
        }
    });
}
