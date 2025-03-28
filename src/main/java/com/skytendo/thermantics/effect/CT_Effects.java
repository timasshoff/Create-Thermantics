package com.skytendo.thermantics.effect;

import com.skytendo.thermantics.Thermantics;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CT_Effects {

    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Thermantics.MODID);

    public static final RegistryObject<MobEffect> CLEMENCY = MOB_EFFECTS.register("clemency",
            () -> new ClemencyEffect(MobEffectCategory.BENEFICIAL, 0x42f5b9));

    public static void register(IEventBus bus) {
        MOB_EFFECTS.register(bus);
    }
}
