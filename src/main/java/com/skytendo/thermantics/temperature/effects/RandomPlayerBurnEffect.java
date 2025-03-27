package com.skytendo.thermantics.temperature.effects;

import com.skytendo.thermantics.temperature.PlayerTemperature;
import net.minecraft.world.entity.player.Player;

import java.util.Random;

public class RandomPlayerBurnEffect extends TemperatureEffect{

    private float chance;
    private int minSecOnFire;
    private int maxSecOnFire;

    public RandomPlayerBurnEffect(Player player, PlayerTemperature temperature, float chance, int minSecOnFire, int maxSecOnFire) {
        super(player, temperature);
        this.chance = chance;
        this.minSecOnFire = minSecOnFire;
        this.maxSecOnFire = maxSecOnFire;
    }

    @Override
    public void apply() {
        if (Math.random() < chance) {
            player.setSecondsOnFire(new Random().nextInt(minSecOnFire, maxSecOnFire));
        }
    }
}
