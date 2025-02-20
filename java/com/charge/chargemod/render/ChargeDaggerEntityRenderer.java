package com.charge.chargemod.render;

import com.charge.chargemod.ChargeModItemRegistry;
import com.charge.chargemod.entity.ChargeDaggerEntity;
import com.charge.chargemod.entityModel.ChargeDaggerEntityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.joml.Vector3f;

@OnlyIn(Dist.CLIENT)
public class ChargeDaggerEntityRenderer extends EntityRenderer<ChargeDaggerEntity> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(ChargeModItemRegistry.MODID, "textures/entity/charge_dagger.png");
    private final ChargeDaggerEntityModel<ChargeDaggerEntity> model;

    public ChargeDaggerEntityRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new ChargeDaggerEntityModel<>(context.bakeLayer(ChargeModItemRegistry.CHARGE_DAGGER_LAYER));
    }

    @Override
    public void render(ChargeDaggerEntity entity, float yaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();
        poseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTicks, entity.yRotO, entity.getYRot()) - 90.0F));
        poseStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(partialTicks, entity.xRotO, entity.getXRot())));
        VertexConsumer vertexConsumer = buffer.getBuffer(this.model.renderType(TEXTURE));
        this.model.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        poseStack.popPose();
        System.out.println("Rendering ChargeDaggerEntity"); // 调试信息
        super.render(entity, yaw, partialTicks, poseStack, buffer, packedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(ChargeDaggerEntity entity) {
        return TEXTURE;
    }

}