package net.dawkmeow.quinta.networking.packets;

import net.dawkmeow.quinta.networking.ModNetworking;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

public record AttackKeyPacket(String test) implements CustomPayload {
    public static final CustomPayload.Id<AttackKeyPacket> ID = new CustomPayload.Id<>(ModNetworking.ATTACK_KEY_ID);
    public static final PacketCodec<RegistryByteBuf, AttackKeyPacket> CODEC = PacketCodec.tuple(
            PacketCodecs.STRING, AttackKeyPacket::test, AttackKeyPacket::new
    );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
