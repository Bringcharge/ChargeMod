package com.charge.chargemod.gui.pellet;

import com.charge.chargemod.ChargeModItemRegistry;
import com.charge.chargemod.gui.ChargeBaseScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

public class PoWanFaTalismanScreen extends ChargeBaseScreen {

    private static final Component title = Component.translatable("破万法丹");
    public PoWanFaTalismanScreen() {
        super(title);
        leftStringLocal = "破万法丹，吃下去后可以消除自身不良状态，保留增益。\n" +
                "万法皆由道，以道破万法。牛奶会让所有的状态消失，此丹药则可避免此尴尬，而且收纳方便，随身携带量大，属实是斗法不二之选。";
        rightImage = new ResourceLocation(ChargeModItemRegistry.MODID,"textures/gui/xian_tian_ba_gua_craft.png");
        showCrafting = true;
        // 乾天, 兑泽（金）, 离火, 震雷（木）, 巽风（木）, 坎水, 艮山（土）, 坤地
        bottomItem = null;
        bottomRightItem = Items.GOLDEN_CARROT;
        rightItem = Items.WOODEN_SWORD;
        topRightItem = null;
        bottomLeftItem = null;
        leftItem = ChargeModItemRegistry.TALISMAN_PAPER_ITEM.get();
        topLeftItem = Items.GLISTERING_MELON_SLICE;
        topItem = null;
        rightStringLocal = "使用了金萝卜，木剑，符纸，金西瓜。按照上图合成，破万法是给自己用的丹药，给敌人并不能破除它的增益。";
    }

}