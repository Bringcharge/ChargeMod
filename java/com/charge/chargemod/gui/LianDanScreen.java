package com.charge.chargemod.gui;

import com.charge.chargemod.ChargeModItemRegistry;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class LianDanScreen extends ChargeBaseScreen {

    private static final Component title = Component.translatable("炼丹炉阵");
    public LianDanScreen() {
        super(title);
        leftStringLocal = "炼丹是用的后天八卦作为阵法，严格区分东南西北。按照图示摆放，中央是八卦炉，周围8个置物台。黑色的代表基石。\n" +
                "八卦炉和置物台在同一层，而基石应该比八卦炉低一层。";
        rightImage = new ResourceLocation(ChargeModItemRegistry.MODID,"textures/gui/hou_tian_ba_gua.png");
        rightStringLocal = "如果摆放出现错误，持有令牌使用八卦炉的时候会有所提示，无需担心，但需要基石的量巨大，多于两组，还请做好准备。";
    }

}