package com.skytendo.thermantics.effect;

import com.skytendo.thermantics.Config;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

import java.util.Random;

public class HypothermiaEffect extends MobEffect {
    protected HypothermiaEffect(MobEffectCategory p_19451_, int p_19452_) {
        super(p_19451_, p_19452_);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity.level().isClientSide())
            return;

        Random r = new Random();

        double weaknessChance = amplifier < 1 ? Config.HYPOTHERMIA_1_WEAKNESS_CHANCE.get() : Config.HYPOTHERMIA_2_WEAKNESS_CHANCE.get();
        int weaknessMaxLength = amplifier < 1 ? Config.HYPOTHERMIA_1_WEAKNESS_MAX_LENGTH.get() : Config.HYPOTHERMIA_2_WEAKNESS_MAX_LENGTH.get();
        if (Math.random() < weaknessChance) {
            entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, r.nextInt(100, weaknessMaxLength), amplifier));
        }

        double miningFatigueChance = amplifier < 1 ? Config.HYPOTHERMIA_1_MINING_FATIGUE_CHANCE.get() : Config.HYPOTHERMIA_2_MINING_FATIGUE_CHANCE.get();
        int miningFatigueMaxLength = amplifier < 1 ? Config.HYPOTHERMIA_1_MINING_FATIGUE_MAX_LENGTH.get() : Config.HYPOTHERMIA_2_MINING_FATIGUE_MAX_LENGTH.get();
        if (Math.random() < miningFatigueChance) {
            entity.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, r.nextInt(100, miningFatigueMaxLength), amplifier));
        }

        if (amplifier > 0) {
            double blindnessChance = amplifier < 2 ? Config.HYPOTHERMIA_2_BLINDNESS_CHANCE.get() : Config.HYPOTHERMIA_3_BLINDNESS_CHANCE.get();
            int blindnessMaxLength = amplifier < 2 ? Config.HYPOTHERMIA_2_BLINDNESS_MAX_LENGTH.get() : Config.HYPOTHERMIA_3_BLINDNESS_MAX_LENGTH.get();
            if (Math.random() < blindnessChance) {
                entity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, r.nextInt(40, blindnessMaxLength), amplifier));
            }
        }

        if (amplifier > 1) {
            if (Math.random() < Config.HYPOTHERMIA_3_DAMAGE_CHANCE.get()) {
                entity.hurt(entity.damageSources().freeze(), 5.0f);
            }
        } else if (amplifier > 0) {
            if (Math.random() < Config.HYPOTHERMIA_2_DAMAGE_CHANCE.get()) {
                entity.hurt(entity.damageSources().freeze(), 3.0f);
            }
        }

        super.applyEffectTick(entity, amplifier);
    }

    @Override
    public boolean isDurationEffectTick(int p_19455_, int p_19456_) {
        return true;
    }
}
