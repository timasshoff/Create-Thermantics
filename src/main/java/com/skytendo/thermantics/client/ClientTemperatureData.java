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
}
