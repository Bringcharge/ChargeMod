package com.charge.chargemod.render;

import com.charge.chargemod.block.ChargeAltarBlock;
import com.charge.chargemod.block.ChargeAltarBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;


public class ChargeAltarRender implements BlockEntityRenderer<ChargeAltarBlockEntity> {
    private final ItemRenderer itemRenderer;

    public ChargeAltarRender(BlockEntityRendererProvider.Context context) {
        this.itemRenderer = context.getItemRenderer();
    }

    @Override
    public void render(ChargeAltarBlockEntity blockEntity, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
        ItemStack item = blockEntity.getItem();
        Level level = blockEntity.getLevel();
        if (!item.isEmpty()) {
            poseStack.pushPose();
            poseStack.translate(0.5, 1 / 16., 0.5); // 调整物品的位置
            poseStack.scale(0.6F, 0.6F, 0.6F); // 调整物品的大小
            itemRenderer.renderStatic(item, ItemDisplayContext.GROUND, combinedLight, combinedOverlay, poseStack, buffer, level, 0); //第二个是变换方式，或者叫展示的方式
            poseStack.popPose();
        }
    }
}
