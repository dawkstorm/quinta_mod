package net.dawkmeow.quinta.entity.client;

import net.dawkmeow.quinta.Quinta;
import net.dawkmeow.quinta.entity.custom.AirScooterEntity;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.util.Identifier;

public class AirScooterRenderer extends LivingEntityRenderer<AirScooterEntity, AirScooterModel<AirScooterEntity>> {
    private static final Identifier TEXTURE =
            Identifier.of(Quinta.MOD_ID, "textures/entity/air_scooter_texture.png");

    public AirScooterRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new AirScooterModel<>(ctx.getPart(ModModelLayers.AIR_SCOOTER)), 0.6f);
    }

    @Override
    public Identifier getTexture(AirScooterEntity entity) {
        return TEXTURE;
    }

    
}
