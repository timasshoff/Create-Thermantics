package com.skytendo.thermantics.temperature;

import net.minecraft.nbt.CompoundTag;

public class PlayerTemperature {

    public static float DEFAULT_TEMPERATURE = 35.0f;
    public enum TemperatureState {
        FREEZING,
        COLD,
        CHILLY,
        WARM,
        HOT,
        FIERY
    }

    private float temperature = DEFAULT_TEMPERATURE;

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public void increaseTemperature(float amount) {
        this.temperature += amount;
    }

    public void decreaseTemperature(float amount) {
        this.temperature -= amount;
    }

    public void copyFrom(PlayerTemperature source) {
        this.temperature = source.temperature;
    }

    public void saveNbtData(CompoundTag compound) {
        compound.putFloat("temperature", temperature);
    }

    public void loadNbtData(CompoundTag compound) {
        temperature = compound.getFloat("temperature");
    }
}
