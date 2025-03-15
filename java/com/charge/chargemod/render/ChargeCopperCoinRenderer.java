package com.charge.chargemod.render;

import com.charge.chargemod.ChargeModItemRegistry;
import com.charge.chargemod.entity.ChargeCopperCoinEntity;
import com.charge.chargemod.entityModel.ChargeCopperCoinModel;
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

@OnlyIn(Dist.CLIENT)
public class ChargeCopperCoinRenderer extends EntityRenderer<ChargeCopperCoinEntity> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(ChargeModItemRegistry.MODID, "textures/entity/charge_copper_coin.png");
    private final ChargeCopperCoinModel<ChargeCopperCoinEntity> model;

    public ChargeCopperCoinRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new ChargeCopperCoinModel<>(context.bakeLayer(ChargeModItemRegistry.COPPER_COIN_LAYER));
    }


    @Override
    public void render(ChargeCopperCoinEntity entity, float yaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();
//        poseStack.rotateAround(Axis.YP.rotationDegrees(-45),0f,0f,0f);
//        poseStack.rotateAround(Axis.XP.rotationDegrees(-90),0f,0f,0f);
//        poseStack.translate(0f, -1.0f, 0.0f);
//        poseStack.mulPose(Axis.ZP.rotationDegrees(-90));
//        poseStack.mulPose(Axis.YP.rotationDegrees(-45));

        poseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTicks, entity.yRotO, entity.getYRot()) - 90.0F));
        poseStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(partialTicks, entity.xRotO, entity.getXRot())));
        VertexConsumer vertexConsumer = buffer.getBuffer(this.model.renderType(TEXTURE));
        this.model.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        poseStack.popPose();
        super.render(entity, yaw, partialTicks, poseStack, buffer, packedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(ChargeCopperCoinEntity entity) {
        return TEXTURE;
    }

}