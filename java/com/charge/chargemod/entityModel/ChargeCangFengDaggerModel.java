package com.charge.chargemod.entityModel;// Made with Blockbench 4.12.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.charge.chargemod.ChargeModItemRegistry;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class ChargeCangFengDaggerModel<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(ChargeModItemRegistry.MODID, "charge_cang_feng_dagger"), "main");
	private final ModelPart bone;

	public ChargeCangFengDaggerModel(ModelPart root) {
		this.bone = root.getChild("bone");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(0, 0).addBox(-7.3F, -0.5F, -7.12F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(8, 6).addBox(-6.3F, -0.5F, -5.12F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(6, 14).addBox(-4.3F, -0.5F, -5.12F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(8, 8).addBox(-5.3F, -0.5F, -4.12F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(14, 6).addBox(-3.3F, -0.5F, -4.12F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 9).addBox(-4.3F, -0.5F, -3.12F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(14, 8).addBox(-2.3F, -0.5F, -3.12F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(6, 10).addBox(-3.3F, -0.5F, -2.12F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(10, 14).addBox(-1.3F, -0.5F, -2.12F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 11).addBox(-2.3F, -0.5F, -1.12F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(14, 14).addBox(-0.3F, -0.5F, -1.12F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(6, 12).addBox(-1.3F, -0.5F, -0.12F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 15).addBox(0.7F, -0.5F, -0.12F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(12, 10).addBox(-0.3F, -0.5F, 0.88F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(4, 16).addBox(1.7F, -0.5F, 0.88F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(12, 12).addBox(0.7F, -0.5F, 1.88F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(8, 16).addBox(2.7F, -0.5F, 1.88F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 13).addBox(1.7F, -0.5F, 2.88F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(14, 4).addBox(-5.3F, -0.5F, -6.12F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(14, 0).addBox(1.7F, -0.5F, 3.88F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(14, 2).addBox(0.7F, -0.5F, 4.88F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(8, 0).addBox(3.7F, -0.5F, 1.88F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(8, 3).addBox(4.7F, -0.5F, 0.88F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 3).addBox(3.7F, -0.5F, 3.88F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 6).addBox(5.7F, -0.5F, 5.88F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.9764F, 0.6036F, -0.4436F, -1.5708F, 0.0F, 0.7854F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		bone.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}