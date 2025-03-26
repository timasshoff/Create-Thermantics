package com.skytendo.thermantics.temperature.effects;

import com.skytendo.thermantics.temperature.PlayerTemperature;
import net.minecraft.world.entity.player.Player;

public class RandomDamageEffect extends TemperatureEffect {

    private float chance;

    public RandomDamageEffect(Player player, PlayerTemperature temperature, float chance) {
        super(player, temperature);
        this.chance = chance;
    }

    @Override
    public void apply() {

    }
}
