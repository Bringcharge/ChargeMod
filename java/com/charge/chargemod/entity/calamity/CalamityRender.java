package com.charge.chargemod.entity.calamity;

import com.charge.chargemod.ChargeModItemRegistry;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.joml.Vector3f;

@OnlyIn(Dist.CLIENT)
public class CalamityRender<T extends Entity> extends EntityRenderer<T> {
   public static final ModelLayerLocation CUSTOM_ARROW_LAYER = new ModelLayerLocation(new ResourceLocation(ChargeModItemRegistry.MODID, "calamity_entity"), "main");
   private static final ResourceLocation TEXTURE = new ResourceLocation(ChargeModItemRegistry.MODID, "textures/entity/calamity_entity.png");
   private final EntityModel<Entity> model;

   public CalamityRender(EntityRendererProvider.Context context) {
      super(context);
      this.model = new CalamityEntityModel<>(context.bakeLayer(CUSTOM_ARROW_LAYER));
   }

   @Override
   public void render(Entity entity, float yaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
      poseStack.pushPose();
      poseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTicks, entity.yRotO, entity.getYRot()) - 90.0F));
      poseStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(partialTicks, entity.xRotO, entity.getXRot())));
      VertexConsumer vertexConsumer = buffer.getBuffer(this.model.renderType(TEXTURE));
      this.model.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
      poseStack.popPose();
      super.render((T) entity, yaw, partialTicks, poseStack, buffer, packedLight);
   }

   @Override
   public ResourceLocation getTextureLocation(Entity entity) {
      return TEXTURE;
   }
}