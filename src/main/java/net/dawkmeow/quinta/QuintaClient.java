package net.dawkmeow.quinta;

import net.dawkmeow.quinta.entity.ModEntities;
import net.dawkmeow.quinta.entity.client.AirScooterModel;
import net.dawkmeow.quinta.entity.client.AirScooterRenderer;
import net.dawkmeow.quinta.entity.client.ModModelLayers;
import net.dawkmeow.quinta.event.KeyInputHandler;
import net.dawkmeow.quinta.networking.ModNetworking;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class QuintaClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        KeyInputHandler.register();
        ModNetworking.registerS2CPackets();

        EntityRendererRegistry.register(ModEntities.AIR_SCOOTER, AirScooterRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.AIR_SCOOTER, AirScooterModel::getTexturedModelData);
    }
}
