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
    private long ticksInCurrentTempState = 0;
    private int currentTempState = TemperatureState.WARM.ordinal();

    public float getTemperature() {
        return temperature;
    }

    public static PlayerTemperature.TemperatureState temperatureToState(float playerTemperature) {
        if (playerTemperature < 10.0f) {
            return PlayerTemperature.TemperatureState.FREEZING;
        } else if (playerTemperature < 20.0f) {
            return PlayerTemperature.TemperatureState.COLD;
        } else if (playerTemperature < 30.0f) {
            return PlayerTemperature.TemperatureState.CHILLY;
        } else if (playerTemperature < 40.0f) {
            return PlayerTemperature.TemperatureState.WARM;
        } else if (playerTemperature < 50.0f) {
            return PlayerTemperature.TemperatureState.HOT;
        } else {
            return PlayerTemperature.TemperatureState.FIERY;
        }
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

    public long getTicksInCurrentTempState() {
        return ticksInCurrentTempState;
    }

    public void increaseTicksInCurrentTempState() {
        this.ticksInCurrentTempState++;
    }

    public void resetTicksInCurrentTempState() {
        this.ticksInCurrentTempState = 0;
    }

    public TemperatureState getCurrentTempState() {
        return TemperatureState.values()[currentTempState];
    }

    public void setCurrentTempState(TemperatureState state) {
        this.currentTempState = state.ordinal();
    }

    public void copyFrom(PlayerTemperature source) {
        this.temperature = source.temperature;
        this.ticksInCurrentTempState = source.ticksInCurrentTempState;
    }

    public void saveNbtData(CompoundTag compound) {
        compound.putFloat("temperature", temperature);
        compound.putLong("ticksInCurrentTempState", ticksInCurrentTempState);
        compound.putInt("currentTempState", currentTempState);
    }

    public void loadNbtData(CompoundTag compound) {
        temperature = compound.getFloat("temperature");
        ticksInCurrentTempState = compound.getLong("ticksInCurrentTempState");
        currentTempState = compound.getInt("currentTempState");
    }
}
