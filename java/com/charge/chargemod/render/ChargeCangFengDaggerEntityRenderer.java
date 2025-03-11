package com.charge.chargemod.render;

import com.charge.chargemod.ChargeModItemRegistry;
import com.charge.chargemod.entity.ChargeCangFengDaggerEntity;
import com.charge.chargemod.entity.ChargeDaggerEntity;
import com.charge.chargemod.entityModel.ChargeCangFengDaggerModel;
import com.charge.chargemod.entityModel.ChargeDaggerEntityModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.joml.Vector3f;

@OnlyIn(Dist.CLIENT)
public class ChargeCangFengDaggerEntityRenderer extends EntityRenderer<ChargeCangFengDaggerEntity> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(ChargeModItemRegistry.MODID, "textures/entity/charge_cang_feng_dagger.png");
    private final ChargeCangFengDaggerModel<ChargeCangFengDaggerEntity> model;

    public ChargeCangFengDaggerEntityRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new ChargeCangFengDaggerModel<>(context.bakeLayer(ChargeModItemRegistry.CHARGE_CANG_FENG_DAGGER_LAYER));
    }

    @Override
    public void render(ChargeCangFengDaggerEntity entity, float yaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();
//        poseStack.rotateAround(Axis.YP.rotationDegrees(-45),0f,0f,0f);
//        poseStack.rotateAround(Axis.XP.rotationDegrees(-90),0f,0f,0f);
//        poseStack.translate(0f, -1.0f, 0.0f);
//        poseStack.mulPose(Axis.ZP.rotationDegrees(-90));
//        poseStack.mulPose(Axis.YP.rotationDegrees(-45));

        poseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTicks, entity.yRotO, entity.getYRot()) - 90.0F));
        poseStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(partialTicks, entity.xRotO, entity.getXRot()) + 90.f));
        VertexConsumer vertexConsumer = buffer.getBuffer(this.model.renderType(TEXTURE));
        this.model.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        poseStack.popPose();
        super.render(entity, yaw, partialTicks, poseStack, buffer, packedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(ChargeCangFengDaggerEntity entity) {
        return TEXTURE;
    }

}