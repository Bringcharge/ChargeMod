package com.charge.chargemod.render;

import com.charge.chargemod.block.ChargeAlchemyAnvilBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;


public class ChargeAlchemyAnvilRender implements BlockEntityRenderer<ChargeAlchemyAnvilBlockEntity> {
    private final ItemRenderer itemRenderer;

    public ChargeAlchemyAnvilRender(BlockEntityRendererProvider.Context context) {
        this.itemRenderer = context.getItemRenderer();
    }

    @Override
    public void render(ChargeAlchemyAnvilBlockEntity blockEntity, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
        ItemStack item = blockEntity.getItem();
        Level level = blockEntity.getLevel();
        if (!item.isEmpty()) {
            poseStack.pushPose();
            poseStack.translate(0.5, 0.8, 0.5); // 调整物品的位置
            poseStack.scale(0.6F, 0.6F, 0.6F); // 调整物品的大小
            itemRenderer.renderStatic(item, ItemDisplayContext.FIXED, combinedLight, combinedOverlay, poseStack, buffer, level, 0); //第二个是变换方式，或者叫展示的方式
            poseStack.popPose();
        }
    }
}
