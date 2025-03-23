package com.skytendo.thermantics.client;

import com.skytendo.thermantics.temperature.PlayerTemperature;

public class ClientTemperatureData {
    private static float playerTemperature;

    public static void set(float temperature) {
        playerTemperature = temperature;
    }

    public static float get() {
        return playerTemperature;
    }

    public static PlayerTemperature.TemperatureState getTemperatureState() {
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
}
