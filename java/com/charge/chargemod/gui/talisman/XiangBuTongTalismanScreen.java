package com.charge.chargemod.gui.talisman;

import com.charge.chargemod.ChargeModItemRegistry;
import com.charge.chargemod.gui.ChargeBaseScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

public class XiangBuTongTalismanScreen extends ChargeBaseScreen {

    private static final Component title = Component.translatable("仙符 想不通");
    public XiangBuTongTalismanScreen() {
        super(title);
        leftStringLocal = "想不通，四大仙符之一，让周围生物相互攻击、逃跑、仇恨彼此。\n" +
                "一日修炼，我问天：生命相互争斗，永无止境，此为天意？天不语，大地纷争依旧。数日后创造此符，此符也只是借用些许天道罢了。";
        rightImage = new ResourceLocation(ChargeModItemRegistry.MODID,"textures/gui/xian_tian_ba_gua_craft.png");
        showCrafting = true;
        // 乾天, 兑泽（金）, 离火, 震雷（木）, 巽风（木）, 坎水, 艮山（土）, 坤地
        topLeftItem = ChargeModItemRegistry.TALISMAN_PAPER_ITEM.get();
        leftItem = null;
        bottomItem = Items.ENDER_EYE;
        rightItem = Items.WHITE_BANNER;
        bottomRightItem = null;
        topItem = Items.MAGMA_CREAM;
        topRightItem = Items.KELP;
        bottomLeftItem = null;
        rightStringLocal = "使用了符纸，末影之眼，旗帜，岩浆膏，海带。按照上图合成，此符蕴含着大道碎片。";
    }

}