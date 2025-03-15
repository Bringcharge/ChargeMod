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
public class ChargeBladeExtendModel<T extends Entity> extends EntityModel<T> {
		// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(ChargeModItemRegistry.MODID, "charge_blade_extend"), "main");
	private final ModelPart bone;

	public ChargeBladeExtendModel(ModelPart root) {
		this.bone = root.getChild("bone");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, -7.0F, 2.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(0, 6).addBox(1.0F, -1.0F, -6.0F, 2.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(10, 22).addBox(-11.0F, -1.0F, 1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 22).addBox(-9.0F, -1.0F, -1.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(14, 17).addBox(-7.0F, -1.0F, -3.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(14, 6).addBox(-5.0F, -1.0F, -5.0F, 2.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(14, 0).addBox(-3.0F, -1.0F, -6.0F, 2.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(0, 12).addBox(3.0F, -1.0F, -5.0F, 2.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(14, 12).addBox(5.0F, -1.0F, -3.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(0, 18).addBox(7.0F, -1.0F, -1.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(10, 25).addBox(10.0F, -1.0F, 3.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(16, 25).addBox(-12.0F, -1.0F, 3.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(18, 22).addBox(9.0F, -1.0F, 1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.50F, 0.0F, 0.0F, -1.5708F, 0.0F));

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