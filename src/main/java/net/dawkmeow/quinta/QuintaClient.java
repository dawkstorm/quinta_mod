package net.dawkmeow.quinta;

import net.dawkmeow.quinta.event.KeyInputHandler;
import net.dawkmeow.quinta.networking.ModNetworking;
import net.fabricmc.api.ClientModInitializer;

public class QuintaClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        KeyInputHandler.register();
        ModNetworking.registerS2CPackets();
    }
}
