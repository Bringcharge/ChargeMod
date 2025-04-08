package com.charge.chargemod.gui.swords;

import com.charge.chargemod.ChargeModItemRegistry;
import com.charge.chargemod.gui.ChargeBaseScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class XunYinScreen extends ChargeBaseScreen {

    private static final Component title = Component.translatable("寻因");
    public XunYinScreen() {
        super(title);
        leftStringLocal = "寻因，因果剑之一。消耗灵力有1秒的时间，可以对看破伤害的成因，若有人在此期间加害于你，会对其造成伤害，并移动到它所处位置。\n" +
                "因果，无因毅无果。万物皆系于这两字之上，寻因也仅仅是找到了其中一个开头，这段因又何尝不是另一个因的果。";
        rightImage = new ResourceLocation(ChargeModItemRegistry.MODID,"textures/gui/hou_tian_ba_gua_craft.png");
        showCrafting = true;
        // 乾天, 兑泽（金）, 离火, 震雷（木）, 巽风（木）, 坎水, 艮山（土）, 坤地
        topLeftItem = null;
        leftItem = new ResourceLocation("textures/item/gold_ingot.png");
        bottomItem = new ResourceLocation("textures/item/charcoal.png");
        rightItem = new ResourceLocation("textures/item/copper_ingot.png");
        bottomRightItem = null;
        topItem = new ResourceLocation("textures/item/water_bucket.png");
        topRightItem = new ResourceLocation("textures/block/dirt.png");
        bottomLeftItem = new ResourceLocation("textures/block/cobblestone.png");
        rightStringLocal = "使用了金锭，木炭，铜锭，水桶，泥土，圆石。按照上图合成即可。";
    }

}