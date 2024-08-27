// Made with Blockbench 4.10.4
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports

package net.dawkmeow.quinta.entity.client;

import net.dawkmeow.quinta.entity.custom.AirScooterEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;

public class AirScooterModel<T extends AirScooterEntity> extends SinglePartEntityModel<T> {
	private final ModelPart air_scooter;
	public AirScooterModel(ModelPart root) {
		this.air_scooter = root.getChild("air_scooter");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData air_scooter = modelPartData.addChild("air_scooter", ModelPartBuilder.create().uv(72, 27).cuboid(-3.0F, -1.0F, -3.0F, 6.0F, 1.0F, 6.0F, new Dilation(0.0F))
		.uv(72, 0).cuboid(-4.0F, -2.0F, -4.0F, 8.0F, 1.0F, 8.0F, new Dilation(0.0F))
		.uv(30, 66).cuboid(-5.0F, -3.0F, -5.0F, 10.0F, 1.0F, 10.0F, new Dilation(0.0F))
		.uv(36, 53).cuboid(-6.0F, -4.0F, -6.0F, 12.0F, 1.0F, 12.0F, new Dilation(0.0F))
		.uv(0, 52).cuboid(-6.0F, -5.0F, -6.0F, 12.0F, 1.0F, 12.0F, new Dilation(0.0F))
		.uv(36, 40).cuboid(-6.0F, -6.0F, -6.0F, 12.0F, 1.0F, 12.0F, new Dilation(0.0F))
		.uv(0, 39).cuboid(-6.0F, -7.0F, -6.0F, 12.0F, 1.0F, 12.0F, new Dilation(0.0F))
		.uv(36, 27).cuboid(-6.0F, -8.0F, -6.0F, 12.0F, 1.0F, 12.0F, new Dilation(0.0F))
		.uv(36, 14).cuboid(-6.0F, -9.0F, -6.0F, 12.0F, 1.0F, 12.0F, new Dilation(0.0F))
		.uv(36, 1).cuboid(-6.0F, -10.0F, -6.0F, 12.0F, 1.0F, 12.0F, new Dilation(0.0F))
		.uv(0, 26).cuboid(-6.0F, -10.0F, -6.0F, 12.0F, 1.0F, 12.0F, new Dilation(0.0F))
		.uv(0, 13).cuboid(-6.0F, -11.0F, -6.0F, 12.0F, 1.0F, 12.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-6.0F, -12.0F, -6.0F, 12.0F, 1.0F, 12.0F, new Dilation(0.0F))
		.uv(0, 65).cuboid(-5.0F, -13.0F, -5.0F, 10.0F, 1.0F, 10.0F, new Dilation(0.0F))
		.uv(60, 66).cuboid(-4.0F, -14.0F, -4.0F, 8.0F, 1.0F, 8.0F, new Dilation(0.0F))
		.uv(72, 14).cuboid(-3.0F, -15.0F, -3.0F, 6.0F, 1.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
		return TexturedModelData.of(modelData, 128, 128);
	}

	@Override
	public ModelPart getPart() {
		return air_scooter;
	}

	@Override
	public void setAngles(AirScooterEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {

	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
		air_scooter.render(matrices, vertices, light, overlay, color);
	}
}