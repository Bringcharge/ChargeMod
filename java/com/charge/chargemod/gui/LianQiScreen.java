package com.charge.chargemod.gui;

import com.charge.chargemod.ChargeModItemRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class LianQiScreen extends ChargeBaseScreen {

    private static final Component title = Component.translatable("炼器阵");
    public LianQiScreen() {
        super(title);
        leftStringLocal = "炼器，是聚万物之精，融为器物。使用的是先天八卦作为大阵，本想辅以灵火，神器……但这世界不需要锻打都能将铁锭打造成装甲，天地伟力就足以。\n" +
                "目前是以锻剑为主，分为三类，有五行，真假，因果。每把剑都有不用的特性，若要我推荐……又有谁规定只能配一把剑呢？各来一把，仗剑天涯，岂不更为潇洒。";
        rightImage = new ResourceLocation(ChargeModItemRegistry.MODID,"textures/gui/xian_tian_ba_gua.png");
        rightStringLocal = "炼器铁砧和置物台在同一层，而基石应该比炼器铁砧低一层。其余用法参考八卦炉即可";
    }

}