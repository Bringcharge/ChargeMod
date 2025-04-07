package com.charge.chargemod.gui;

import com.charge.chargemod.ChargeModItemRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class LianQiScreen extends ChargeBaseScreen {

    private static final Component title = Component.translatable("炼器");
    public LianQiScreen() {
        super(title);
        leftStringLocal = "炼器，是聚万物之精，融为器物。使用的是后天八卦作为大阵，本想辅以灵火，神器……但这世界不需要锻打都能将铁锭打造成装甲，天地伟力就足以。\n" +
                "目前是以锻剑为主，分为三类，有五行，真假，因果。每把剑都有不用的特性，若要我推荐……又有谁规定只能配一把剑呢？各来一把，仗剑天涯，岂不更为潇洒。";
        rightImage = new ResourceLocation(ChargeModItemRegistry.MODID,"textures/gui/hou_tian_ba_gua.png");
        rightStringLocal = "上图这个就是灵石矿，灵石作为修仙最基础也是最重要的材料，需要大量收集。遇到了还是挖掘留存为好";
    }

}