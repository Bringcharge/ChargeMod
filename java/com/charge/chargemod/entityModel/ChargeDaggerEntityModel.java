package com.charge.chargemod.entityModel;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


@OnlyIn(Dist.CLIENT)
public class ChargeDaggerEntityModel<T extends Entity> extends EntityModel<T> {
    private final ModelPart dagger;

    public ChargeDaggerEntityModel(ModelPart root) {
        this.dagger = root.getChild("charge_dagger");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition parts = mesh.getRoot();

        // 定义飞刀的模型
        parts.addOrReplaceChild("charge_dagger", CubeListBuilder.create()
                        .texOffs(0, 0) // 纹理偏移
                        .addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, new CubeDeformation(0.0F)), // 创建一个 1x1x1 的立方体
                PartPose.offset(0.0F, 0.0F, 0.0F));

        return LayerDefinition.create(mesh, 16, 16);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        // 不需要动画逻辑
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        dagger.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}