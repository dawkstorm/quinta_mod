package net.dawkmeow.quinta.networking.packets;

import net.dawkmeow.quinta.networking.ModNetworking;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

public record TestPayload(String test) implements CustomPayload {
    public static final CustomPayload.Id<TestPayload> ID = new CustomPayload.Id<>(ModNetworking.TEST_ID);
    public static final PacketCodec<RegistryByteBuf, TestPayload> CODEC = PacketCodec.tuple(
            PacketCodecs.STRING, TestPayload::test, TestPayload::new
    );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
