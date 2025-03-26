package com.skytendo.thermantics.temperature.effects;

import com.skytendo.thermantics.temperature.PlayerTemperature;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;

public class PlayerFreezeEffect extends TemperatureEffect{

    public PlayerFreezeEffect(Player player, PlayerTemperature temperature) {
        super(player, temperature);
    }

    @Override
    public void apply() {
        player.setIsInPowderSnow(true);
    }
}
