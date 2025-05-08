package com.charge.chargemod.gui.talisman;

import com.charge.chargemod.ChargeModItemRegistry;
import com.charge.chargemod.gui.ChargeBaseScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

public class RainTalismanScreen extends ChargeBaseScreen {

    private static final Component title = Component.translatable("祈雨符");
    public RainTalismanScreen() {
        super(title);
        leftStringLocal = "祈雨符，可通天，改变天气，让此地下雨。\n" +
                "是否下雨在这方世界其实意义不大，但祈雨自古有之，也可视为修行之人法力的一个标志。所以创造此符，让这方世界的修行者也能做到唤雨这基本术法。";
        rightImage = new ResourceLocation(ChargeModItemRegistry.MODID,"textures/gui/xian_tian_ba_gua_craft.png");
        showCrafting = true;
        // 乾天, 兑泽（金）, 离火, 震雷（木）, 巽风（木）, 坎水, 艮山（土）, 坤地
        topLeftItem = ChargeModItemRegistry.TALISMAN_PAPER_ITEM.get();
        leftItem = Items.BEEF;
        bottomItem = Items.MUTTON;
        rightItem = Items.PORKCHOP;
        bottomRightItem = null;
        topItem = null;
        topRightItem = null;
        bottomLeftItem = null;
        rightStringLocal = "使用了符纸，生牛肉，生羊肉，生猪肉，按照上图合成。三牲祭祀，祈求下雨，这比起术法更接近于另一个世界的习俗吧。";
    }

}