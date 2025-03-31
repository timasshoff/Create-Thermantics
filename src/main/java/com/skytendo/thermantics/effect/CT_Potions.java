package com.skytendo.thermantics.effect;

import com.skytendo.thermantics.Thermantics;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CT_Potions {

    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, Thermantics.MODID);

    public static final RegistryObject<Potion> CLEMENCY = POTIONS.register("clemency_potion",
            () -> new Potion(new MobEffectInstance(CT_Effects.CLEMENCY.get(), 19200)));

    public static void register(IEventBus bus) {
        POTIONS.register(bus);
    }
}
