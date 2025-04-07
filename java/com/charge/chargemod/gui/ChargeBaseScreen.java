package com.charge.chargemod.gui;
import com.charge.chargemod.ChargeModItemRegistry;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.ImageWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;

import java.util.ArrayList;
import java.util.List;

public class ChargeBaseScreen extends Screen {

    public int index;   //所在的页码
    public GuidePageDelegate delegate;
    public Component title;
    public String leftStringLocal;
    public String rightStringLocal;
    public List<FormattedCharSequence> leftTextList;
    public List<FormattedCharSequence> rightTextList;
    public ResourceLocation rightImage;

    public boolean showCrafting = false;
    public ResourceLocation topItem;
    public ResourceLocation topLeftItem;
    public ResourceLocation topRightItem;
    public ResourceLocation leftItem;
    public ResourceLocation rightItem;
    public ResourceLocation bottomLeftItem;
    public ResourceLocation bottomRightItem;
    public ResourceLocation bottomItem;
    public ResourceLocation centerItem;

    private static final ResourceLocation BOOK_TEXTURE = new ResourceLocation(ChargeModItemRegistry.MODID,"textures/gui/book.png");

    public ChargeBaseScreen(Component title) {
        super(title);
        this.title = title;
    }

    @Override
    protected void init() {
        super.init();
        // 添加翻页按钮
        if (leftStringLocal!=null) {
            leftText(leftStringLocal, 90);
        }
        if (rightStringLocal!=null) {
            rightText(rightStringLocal, 90);
        }
        this.addRenderableWidget(Button.builder(Component.literal("<"), btn -> goToPreviousPage()).bounds(width / 2 - 110 - 20, height / 2 - 10, 20, 20).build());
        this.addRenderableWidget(Button.builder(Component.literal(">"), btn -> goToNextPage()).bounds(width / 2 + 110 , height / 2 - 10, 20, 20).build());
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        int leftPageStartPosX = width / 2 - 110;
        int leftPageStartPosY = height / 2 - 80;
        int rightPageStartPosX = width / 2 + 20;
        int rightPageStartPosY = height / 2 - 80;
        int rightPageStartPosY_have_image = rightPageStartPosY + 64 + 5;    //5是额外的间隔
        renderBackground(guiGraphics);
        // 绘制书本背景
        guiGraphics.blit(BOOK_TEXTURE, width / 2 - 128, height / 2 - 128, 0, 0, 256, 256);
        // 绘制文本
        guiGraphics.drawCenteredString(font, title, width / 2, 20, 0xFFFFFF);
//        guiGraphics.drawString(font,Component.literal(" 测试开头位置"),leftPageStartPosX,leftPageStartPosY, 0xFFFFFF);
        // 逐行渲染
        if (leftTextList != null) {
            for (int i = 0; i < leftTextList.size(); i++) {
                guiGraphics.drawString(
                        font,
                        leftTextList.get(i),
                        leftPageStartPosX,
                        leftPageStartPosY + i * 10, // 计算每行Y坐标
                        0x000000, // rgb颜色
                        false     // 是否带阴影
                );
            }
        }
        if (rightImage == null) {   //如果没有右侧图片
            if (rightTextList != null) {
                for (int i = 0; i < rightTextList.size(); i++) {
                    guiGraphics.drawString(
                            font,
                            rightTextList.get(i),
                            rightPageStartPosX,
                            rightPageStartPosY + i * 10, // 计算每行Y坐标
                            0x000000, // rgb颜色
                            false     // 是否带阴影
                    );
                }
            }
        } else {    //如果有右侧图片

            ImageWidget rightImageWidget = new ImageWidget(rightPageStartPosX + 10, rightPageStartPosY,64,64,rightImage);
            if (showCrafting) rightImageWidget.setAlpha(0.3f);
            this.addRenderableWidget(rightImageWidget);
            if (showCrafting) { //展示合成表

                if (topLeftItem != null) {  //左上角
                    this.addRenderableWidget(new ImageWidget(rightPageStartPosX + 10, rightPageStartPosY,16,16,topLeftItem));
                }
                if (topItem != null) {
                    this.addRenderableWidget(new ImageWidget(rightPageStartPosX + 10 + 16 + 8, rightPageStartPosY,16,16,topItem));
                }
                if (topRightItem != null) {
                    this.addRenderableWidget(new ImageWidget(rightPageStartPosX + 10 + 32 + 16, rightPageStartPosY,16,16,topRightItem));
                }
                if (leftItem != null) { //左侧
                    this.addRenderableWidget(new ImageWidget(rightPageStartPosX + 10, rightPageStartPosY + 16 + 8,16,16,leftItem));
                }
                if (rightItem != null) {
                    this.addRenderableWidget(new ImageWidget(rightPageStartPosX + 10 + 32 + 16, rightPageStartPosY + 16 + 8,16,16,rightItem));
                }
                if (bottomLeftItem != null) { //左下角
                    this.addRenderableWidget(new ImageWidget(rightPageStartPosX + 10, rightPageStartPosY + 32 + 16,16,16,bottomLeftItem));
                }
                if (bottomItem != null) { //下
                    this.addRenderableWidget(new ImageWidget(rightPageStartPosX + 10 + 16 + 8, rightPageStartPosY + 32 + 16,16,16,bottomItem));
                }
                if (bottomRightItem != null) { //下右
                    this.addRenderableWidget(new ImageWidget(rightPageStartPosX + 10 + 32 + 16, rightPageStartPosY + 32 + 16,16,16,bottomRightItem));
                }
            }
            if (rightTextList != null) {
                for (int i = 0; i < rightTextList.size(); i++) {
                    guiGraphics.drawString(
                            font,
                            rightTextList.get(i),
                            rightPageStartPosX,
                            rightPageStartPosY_have_image + i * 10, // 计算每行Y坐标
                            0x000000, // rgb颜色
                            false     // 是否带阴影
                    );
                }
            }
        }
        super.render(guiGraphics, mouseX, mouseY, partialTick);
    }

    public void leftText(String str, int wordWidth) {
        leftTextList = font.split(Component.translatable(str), wordWidth);
    }

    public void rightText(String str, int wordWidth) {
        rightTextList = font.split(Component.translatable(str), wordWidth);
    }

    private void goToPreviousPage() {
        delegate.changeToPreviousPage(index);
    }
    private void goToNextPage() {
        delegate.changeToNextPage(index);
    }
}
