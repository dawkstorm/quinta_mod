package net.dawkmeow.quinta.networking.packets;

import net.dawkmeow.quinta.networking.ModNetworking;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

public record ManaCollectPacket(String test) implements CustomPayload {
    public static final Id<ManaCollectPacket> ID = new Id<>(ModNetworking.TEST_ID);
    public static final PacketCodec<RegistryByteBuf, ManaCollectPacket> CODEC = PacketCodec.tuple(
            PacketCodecs.STRING, ManaCollectPacket::test, ManaCollectPacket::new
    );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
