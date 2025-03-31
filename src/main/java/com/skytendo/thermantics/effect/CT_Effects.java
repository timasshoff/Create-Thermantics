package com.skytendo.thermantics.effect;

import com.skytendo.thermantics.Config;
import com.skytendo.thermantics.Thermantics;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CT_Effects {

    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Thermantics.MODID);

    public static final RegistryObject<MobEffect> CLEMENCY = MOB_EFFECTS.register("clemency",
            () -> new ClemencyEffect(MobEffectCategory.BENEFICIAL, 0x42f5b9));

    public static final RegistryObject<MobEffect> HYPERTHERMIA = MOB_EFFECTS.register("hyperthermia",
            () -> new HyperthermiaEffect(MobEffectCategory.HARMFUL, 0xf57242)
                    .addAttributeModifier(Attributes.MOVEMENT_SPEED,"edabd3e8-7b04-4139-8899-f9b392e393af" , -0.15, AttributeModifier.Operation.MULTIPLY_TOTAL)
                    .addAttributeModifier(Attributes.ATTACK_SPEED,"6baafc71-84dc-40f1-8931-5518cef734ec" , -0.4, AttributeModifier.Operation.MULTIPLY_TOTAL));

    public static final RegistryObject<MobEffect> HYPOTHERMIA = MOB_EFFECTS.register("hypothermia",
            () -> new HypothermiaEffect(MobEffectCategory.HARMFUL, 0x42e0f5)
                    .addAttributeModifier(Attributes.MOVEMENT_SPEED,"f6d4b44e-dd15-42a3-a9f5-5dfbb88daad4" , -0.15, AttributeModifier.Operation.MULTIPLY_TOTAL)
                    .addAttributeModifier(Attributes.ATTACK_DAMAGE,"c7e07c9b-6b2f-4e6b-9844-dbb4aa368a1b" , -4.0, AttributeModifier.Operation.MULTIPLY_TOTAL));


    public static void register(IEventBus bus) {
        MOB_EFFECTS.register(bus);
    }
}
