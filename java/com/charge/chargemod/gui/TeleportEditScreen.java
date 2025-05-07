package com.charge.chargemod.gui;
import com.charge.chargemod.block.array.ChargeTeleportBlockEntity;
import com.charge.chargemod.network.ChargePacketSender;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import com.charge.chargemod.ChargeModItemRegistry;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class TeleportEditScreen extends Screen {

    public Component title;
    public ChargeTeleportBlockEntity entity;
    @Nullable public Vec3i target;

    private EditBox editBox_x;
    private EditBox editBox_y;
    private EditBox editBox_z;
    private static final ResourceLocation BOOK_TEXTURE = new ResourceLocation(ChargeModItemRegistry.MODID,"textures/gui/book.png");

    public TeleportEditScreen(Component title) {
        this(title, null);
    }

    public TeleportEditScreen(Component title, ChargeTeleportBlockEntity entity) {
        super(title);
        this.title = title;
        this.entity = entity;
        this.target = entity.getTargetVec();
    }

    public boolean isNumeric(String str) {
        return str.matches("[-+]?\\d*");
    }

    @Override
    protected void init() {
        super.init();
        // 添加确定和取消按钮
        this.editBox_x = new EditBox(this.font, this.width / 2 - 100, 48, 50, 20, Component.translatable("gui.teleport_x"));
        this.editBox_y = new EditBox(this.font, this.width / 2 - 25, 48, 50, 20, Component.translatable("gui.teleport_y"));
        this.editBox_z = new EditBox(this.font, this.width / 2 + 50, 48, 50, 20, Component.translatable("gui.teleport_z"));
        this.editBox_x.setFilter((s -> {
            return isNumeric(s);
        }));
        this.editBox_y.setFilter((s -> {
            return isNumeric(s);
        }));
        this.editBox_z.setFilter((s -> {
            return isNumeric(s);
        }));
        if (target!= null) {    //如果有数据设置初始页面
            editBox_x.setValue(String.valueOf(target.getX()));
            editBox_y.setValue(String.valueOf(target.getY()));
            editBox_z.setValue(String.valueOf(target.getZ()));
        }

        this.addRenderableWidget(editBox_x);
        this.addRenderableWidget(editBox_y);
        this.addRenderableWidget(editBox_z);
        this.addRenderableWidget(Button.builder(Component.literal("确定"), btn -> clickConfirmButton()).bounds(width / 2 - 60 - 20, 78, 60, 20).build());
        this.addRenderableWidget(Button.builder(Component.literal("取消"), btn -> clickCancelButton()).bounds(width / 2 + 20 , 78, 60, 20).build());
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {

        renderBackground(guiGraphics);
        //标题渲染
        guiGraphics.drawCenteredString(font, Component.translatable("gui.teleport_screen"), width / 2, 18, 0xFFFFFF);

        // 逐行渲染
        super.render(guiGraphics, mouseX, mouseY, partialTick);
    }

    private void clickConfirmButton() {
        this.saveChanges();
        this.minecraft.setScreen((Screen)null);
    }
    private void clickCancelButton() {
        this.minecraft.setScreen((Screen)null);
    }
    private void saveChanges() {    //存储ui附带设置的数据
        if (this.entity!=null) {
            int x = Integer.parseInt(editBox_x.getValue());
            int y = Integer.parseInt(editBox_y.getValue());
            int z = Integer.parseInt(editBox_z.getValue());
            BlockPos pos = entity.getBlockPos();
            ChargePacketSender.sendTeleportBlockMessageToServer(pos.getX(), pos.getY(), pos.getZ(), x, y, z);
        }
    }
}
