package com.skytendo.thermantics.temperature.effects;

import com.skytendo.thermantics.temperature.PlayerTemperature;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;

import java.util.Random;

public class RandomStatusEffect extends TemperatureEffect {

    private float chance;
    private MobEffect effect;
    private int minDuration;
    private int maxDuration;
    private int level;

    public RandomStatusEffect(Player player, PlayerTemperature temperature, float chance, MobEffect effect, int minSecDuration, int maxSecDuration, int level) {
        super(player, temperature);
        this.chance = chance;
        this.effect = effect;
        this.minDuration = minSecDuration * 20;
        this.maxDuration = maxSecDuration * 20;
        this.level = level;
    }

    @Override
    public void apply() {
        if (Math.random() < chance) {
            player.addEffect(new MobEffectInstance(effect, new Random().nextInt(minDuration, maxDuration), level));
        }
    }
}
