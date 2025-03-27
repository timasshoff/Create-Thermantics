package com.skytendo.thermantics.ponder;

import com.skytendo.thermantics.Thermantics;
import net.createmod.ponder.api.registration.PonderPlugin;
import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.minecraft.resources.ResourceLocation;

public class ThermanticsPonder implements PonderPlugin {
    @Override
    public String getModId() {
        return Thermantics.MODID;
    }

    @Override
    public void registerScenes(PonderSceneRegistrationHelper<ResourceLocation> helper) {
        CT_PonderScenes.register(helper);
    }
}
