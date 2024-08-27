package net.dawkmeow.quinta.networking.packets;

import net.dawkmeow.quinta.networking.ModNetworking;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

public record Manipulate1KeyPacket(String test) implements CustomPayload {
    public static final Id<Manipulate1KeyPacket> ID = new Id<>(ModNetworking.MANIPULATE_1_ID);
    public static final PacketCodec<RegistryByteBuf, Manipulate1KeyPacket> CODEC = PacketCodec.tuple(
            PacketCodecs.STRING, Manipulate1KeyPacket::test, Manipulate1KeyPacket::new
    );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
