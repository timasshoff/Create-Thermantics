package com.skytendo.thermantics.temperature.effects;

import com.skytendo.thermantics.temperature.PlayerTemperature;
import net.minecraft.world.entity.player.Player;

public abstract class TemperatureEffect {

    protected Player player;
    protected PlayerTemperature temperature;

    public TemperatureEffect(Player player, PlayerTemperature temperature) {
        this.player = player;
        this.temperature = temperature;
    }

    public abstract void apply();
}
