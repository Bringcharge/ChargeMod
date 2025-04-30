package com.charge.chargemod.gui.talisman;

import com.charge.chargemod.ChargeModItemRegistry;
import com.charge.chargemod.gui.ChargeBaseScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

public class InviteGodTalismanScreen extends ChargeBaseScreen {

    private static final Component title = Component.translatable("请神符");
    public InviteGodTalismanScreen() {
        super(title);
        leftStringLocal = "请神符，蹲下使用该符可以标记一个生物，可在异地使用符，产生请神仪式，若仪式结束后，生物仍站在召唤仪式附近，则会传送到使用符的道友附近\n" +
                "说是请神，实际上更接近于请人，这就是大道相通。目前仅能用于同一维度。";
        rightImage = new ResourceLocation(ChargeModItemRegistry.MODID,"textures/gui/xian_tian_ba_gua_craft.png");
        showCrafting = true;
        // 乾天, 兑泽（金）, 离火, 震雷（木）, 巽风（木）, 坎水, 艮山（土）, 坤地
        bottomItem = ChargeModItemRegistry.TALISMAN_PAPER_ITEM.get();
        bottomRightItem = Items.ENDER_PEARL;
        rightItem = Items.COMPASS;
        topRightItem = Items.ENDER_PEARL;
        bottomLeftItem = Items.ENDER_EYE;
        leftItem = null;
        topLeftItem = null;
        topItem = Items.CLOCK;
        rightStringLocal = "使用了符纸，末影珍珠，指南针，末影珍珠，末影之眼，时钟，按照上图合成。请神嘛，最好还是礼貌一些。";
    }

}