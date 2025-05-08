package com.charge.chargemod.gui.talisman;

import com.charge.chargemod.ChargeModItemRegistry;
import com.charge.chargemod.gui.ChargeBaseScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

public class QiuBuDeTalismanScreen extends ChargeBaseScreen {

    private static final Component title = Component.translatable("仙符 求不得");
    public QiuBuDeTalismanScreen() {
        super(title);
        leftStringLocal = "求不得，四大仙符之一，怪物伤害人类是怪物的本能，使用此符大部分怪物会丧失攻击的能力。\n" +
                "一日修炼，天问我：羊吃草，人吃谷于肉，你修仙所求灵气长生，与生物求食何异？思索数日创作此符，众生皆有所求，皆求而不得。";
        rightImage = new ResourceLocation(ChargeModItemRegistry.MODID,"textures/gui/xian_tian_ba_gua_craft.png");
        showCrafting = true;
        // 乾天, 兑泽（金）, 离火, 震雷（木）, 巽风（木）, 坎水, 艮山（土）, 坤地
        topLeftItem = ChargeModItemRegistry.TALISMAN_PAPER_ITEM.get();
        leftItem = Items.MAGMA_CREAM;
        bottomItem = Items.BLAZE_ROD;
        rightItem = Items.SOUL_CAMPFIRE;
        bottomRightItem = null;
        topItem = null;
        topRightItem = Items.SLIME_BALL;
        bottomLeftItem = null;
        rightStringLocal = "使用了符纸，岩浆膏，烈焰棒，灵魂营火，粘液球。按照上图合成，此符蕴含着大道碎片。";
    }

}