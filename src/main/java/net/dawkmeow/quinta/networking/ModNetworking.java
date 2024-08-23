package net.dawkmeow.quinta.networking;

import net.dawkmeow.quinta.Quinta;
import net.dawkmeow.quinta.networking.packets.TestPayload;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.util.Identifier;

public class ModNetworking {
    public static final Identifier COLLECT_MANA_ID = Identifier.of(Quinta.MOD_ID, "collect_mana");
    public static final Identifier MANA_SYNC_ID = Identifier.of(Quinta.MOD_ID, "mana");
    public static final Identifier TEST_ID = Identifier.of(Quinta.MOD_ID, "collect_mana");


    public static void registerC2SPackets(){
        PayloadTypeRegistry.playC2S().register(TestPayload.ID, TestPayload.CODEC);
        ServerPlayNetworking.registerGlobalReceiver(TestPayload.ID, ((payload, context) -> {
            EntityType.ALLAY.spawn(context.player().getServerWorld(), context.player().getBlockPos(), SpawnReason.TRIGGERED);
        }));
    }

    public static void registerS2CPackets(){

    }
}
