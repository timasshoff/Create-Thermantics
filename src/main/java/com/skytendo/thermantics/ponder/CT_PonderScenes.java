package com.skytendo.thermantics.ponder;

import com.skytendo.thermantics.block.CT_Blocks;
import com.tterrag.registrate.util.entry.ItemProviderEntry;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.minecraft.resources.ResourceLocation;

public class CT_PonderScenes {

    public static void register(PonderSceneRegistrationHelper<ResourceLocation> helper) {
        helper.forComponents(CT_Blocks.THERMAL_EXCHANGER.getId())
                .addStoryBoard("thermal_exchanger", ThermalExchangerScenes::thermalExchanger);
    }

}
