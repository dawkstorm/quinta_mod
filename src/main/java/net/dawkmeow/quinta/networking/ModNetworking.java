package net.dawkmeow.quinta.networking;

import net.dawkmeow.quinta.Quinta;
import net.dawkmeow.quinta.item.custom.AirWand;
import net.dawkmeow.quinta.networking.packets.AttackKeyPacket;
import net.dawkmeow.quinta.networking.packets.Manipulate1KeyPacket;
import net.dawkmeow.quinta.networking.packets.Manipulate2KeyPacket;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.item.Item;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;

public class ModNetworking {
    public static final Identifier MANIPULATE_1_ID = Identifier.of(Quinta.MOD_ID, "manipulate_1");
    public static final Identifier MANIPULATE_2_ID = Identifier.of(Quinta.MOD_ID, "manipulate_2");
    public static final Identifier ATTACK_KEY_ID = Identifier.of(Quinta.MOD_ID, "attack_key");


    public static void registerC2SPackets(){
        PayloadTypeRegistry.playC2S().register(AttackKeyPacket.ID, AttackKeyPacket.CODEC);
        PayloadTypeRegistry.playC2S().register(Manipulate1KeyPacket.ID, Manipulate1KeyPacket.CODEC);
        PayloadTypeRegistry.playC2S().register(Manipulate2KeyPacket.ID, Manipulate2KeyPacket.CODEC);

        ServerPlayNetworking.registerGlobalReceiver(AttackKeyPacket.ID, ((payload, context) -> {
            Item item = context.player().getStackInHand(Hand.MAIN_HAND).getItem();
            if(item instanceof AirWand airWand){
                if(airWand.capturedEntity != null){
                    airWand.isEnabled = false;
                    airWand.capturedEntity.setNoGravity(false);
                    Vec3d launchVector = new Vec3d(
                            context.player().getRotationVector().getX() * 3,
                            context.player().getRotationVector().getY() * 3,
                            context.player().getRotationVector().getZ() * 3
                    );
                    airWand.capturedEntity.setVelocity(launchVector);
                    airWand.capturedEntity = context.player();
                }
            }
        }));

        ServerPlayNetworking.registerGlobalReceiver(Manipulate1KeyPacket.ID, ((payload, context) -> {
            Item item = context.player().getStackInHand(Hand.MAIN_HAND).getItem();
            if(item instanceof AirWand airWand){
                if(airWand.capturedEntity != null){
                    if(airWand.capturedEntity != null){
                        if(airWand.distance < 10){
                            airWand.distance += 0.2d;
                        }
                        else{
                            airWand.distance = 10;
                        }
                    }
                }
            }
        }));

        ServerPlayNetworking.registerGlobalReceiver(Manipulate2KeyPacket.ID, ((payload, context) -> {
            Item item = context.player().getStackInHand(Hand.MAIN_HAND).getItem();
            if(item instanceof AirWand airWand){
                if(airWand.capturedEntity != null){
                    if(airWand.distance > 1){
                        airWand.distance -= 0.2d;
                    }
                    else{
                        airWand.distance = 1;
                    }
                }
            }
        }));
    }

    public static void registerS2CPackets(){

    }
}
