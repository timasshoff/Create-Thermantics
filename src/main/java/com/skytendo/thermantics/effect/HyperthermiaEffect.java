package com.skytendo.thermantics.effect;

import com.skytendo.thermantics.Config;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

import java.util.Random;

public class HyperthermiaEffect extends MobEffect {
    protected HyperthermiaEffect(MobEffectCategory p_19451_, int p_19452_) {
        super(p_19451_, p_19452_);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity.level().isClientSide())
            return;

        Random r = new Random();

        double confusionChance = amplifier < 1 ? Config.HYPERTHERMIA_1_NAUSEA_CHANCE.get() : Config.HYPERTHERMIA_2_NAUSEA_CHANCE.get();
        int confusionMaxLength = amplifier < 1 ? Config.HYPERTHERMIA_1_NAUSEA_MAX_LENGTH.get() : Config.HYPERTHERMIA_2_NAUSEA_MAX_LENGTH.get();
        if (Math.random() < confusionChance) {
            entity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, r.nextInt(100, confusionMaxLength), amplifier));
        }

        double miningFatigueChance = amplifier < 1 ? Config.HYPERTHERMIA_1_MINING_FATIGUE_CHANCE.get() : Config.HYPERTHERMIA_2_MINING_FATIGUE_CHANCE.get();
        int miningFatigueMaxLength = amplifier < 1 ? Config.HYPERTHERMIA_1_MINING_FATIGUE_MAX_LENGTH.get() : Config.HYPERTHERMIA_2_MINING_FATIGUE_MAX_LENGTH.get();
        if (Math.random() < miningFatigueChance) {
            entity.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, r.nextInt(60, miningFatigueMaxLength), amplifier));
        }

        double weaknessChance = amplifier < 1 ? Config.HYPERTHERMIA_1_WEAKNESS_CHANCE.get() : Config.HYPERTHERMIA_2_WEAKNESS_CHANCE.get();
        int weaknessMaxLength = amplifier < 1 ? Config.HYPERTHERMIA_1_WEAKNESS_MAX_LENGTH.get() : Config.HYPERTHERMIA_2_WEAKNESS_MAX_LENGTH.get();
        if (Math.random() < weaknessChance) {
            entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, r.nextInt(60, weaknessMaxLength), amplifier));
        }

        if (amplifier > 0) {
            if (Math.random() < Config.HYPERTHERMIA_FIRE_CHANCE.get()) {
                entity.setSecondsOnFire(r.nextInt(3, 5));
            }
        }

        super.applyEffectTick(entity, amplifier);
    }

    @Override
    public boolean isDurationEffectTick(int p_19455_, int p_19456_) {
        return true;
    }
}
