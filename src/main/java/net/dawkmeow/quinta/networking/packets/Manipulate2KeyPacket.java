package net.dawkmeow.quinta.networking.packets;

import net.dawkmeow.quinta.networking.ModNetworking;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

public record Manipulate2KeyPacket(String test) implements CustomPayload {
    public static final Id<Manipulate2KeyPacket> ID = new Id<>(ModNetworking.MANIPULATE_2_ID);
    public static final PacketCodec<RegistryByteBuf, Manipulate2KeyPacket> CODEC = PacketCodec.tuple(
            PacketCodecs.STRING, Manipulate2KeyPacket::test, Manipulate2KeyPacket::new
    );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
