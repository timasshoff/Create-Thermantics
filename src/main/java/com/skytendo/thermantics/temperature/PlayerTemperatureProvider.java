package com.skytendo.thermantics.temperature;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerTemperatureProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public static Capability<PlayerTemperature> PLAYER_TEMPERATURE = CapabilityManager.get(new CapabilityToken<PlayerTemperature>() { });

    private PlayerTemperature temperature = null;
    private final LazyOptional<PlayerTemperature> optional = LazyOptional.of(this::createPlayerTemperature);

    private PlayerTemperature createPlayerTemperature() {
        if (temperature == null) {
            temperature = new PlayerTemperature();
        }
        return temperature;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction direction) {
        if (capability == PLAYER_TEMPERATURE) {
            return optional.cast();
        }

        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag compound = new CompoundTag();
        createPlayerTemperature().saveNbtData(compound);
        return compound;
    }

    @Override
    public void deserializeNBT(CompoundTag compoundTag) {
        createPlayerTemperature().loadNbtData(compoundTag);
    }
}
