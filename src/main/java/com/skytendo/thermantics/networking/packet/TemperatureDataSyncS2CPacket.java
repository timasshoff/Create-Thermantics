package com.skytendo.thermantics.networking.packet;

import com.skytendo.thermantics.client.ClientTemperatureData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class TemperatureDataSyncS2CPacket {

    private final float temperature;

    public TemperatureDataSyncS2CPacket(float temperature) {
        this.temperature = temperature;
    }

    public TemperatureDataSyncS2CPacket(FriendlyByteBuf buf) {
        this.temperature = buf.readFloat();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeFloat(this.temperature);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ClientTemperatureData.set(this.temperature);
        });
        return true;
    }
}
