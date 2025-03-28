package com.charge.chargemod.gui;

import com.example.examplemod.ExampleMod;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class ManualScreen extends ChargeBaseScreen {

    private static final Component title = Component.translatable("前言");
    public ManualScreen() {
        super(title);
        leftStringLocal = "天地玄黄，宇宙洪荒。此方天地气象万千，广袤无垠。正是天圆地方，无比契合修行之道。\n  老夫观天地星辰，在这世间寻找到了一丝求仙问道之法。特此著书立传，传与后人，望得书者妥善用之。";
        rightStringLocal = "前言难免有些严肃，不过这修仙说起来也并非难事。首先是获得灵气，接着用灵气炼制：器、符、丹、阵四类宝物。\n特殊的丹药能祝你突破获得更多的灵气，自然能炼制更多的宝物。此书内有老夫推断的宝物的炼制之法，道友准备好珍惜材料自行炼制便可";
    }

}