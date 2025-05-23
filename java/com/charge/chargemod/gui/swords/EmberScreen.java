package com.charge.chargemod.gui.swords;

import com.charge.chargemod.ChargeModItemRegistry;
import com.charge.chargemod.gui.ChargeBaseScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class EmberScreen extends ChargeBaseScreen {

    private static final Component title = Component.translatable("余烬");
    public EmberScreen() {
        super(title);
        leftStringLocal = "余烬，五行属火。可以消耗灵力让怪物燃烧，若是已经燃烧的怪物则可以引发巨大的爆炸。\n" +
                "如同燃烧后的炭火，仅剩微不足道的暗红，却也可能引燃正片森林，烧尽一切。";
        rightImage = new ResourceLocation(ChargeModItemRegistry.MODID,"textures/gui/hou_tian_ba_gua_craft.png");
        showCrafting = true;
        // 乾天, 兑泽（金）, 离火, 震雷（木）, 巽风（木）, 坎水, 艮山（土）, 坤地
        topLeftItem = null;
        leftItem = null;
        bottomItem = new ResourceLocation("textures/block/magma.png");
        rightItem = new ResourceLocation("textures/item/coal.png");
        bottomRightItem = new ResourceLocation("textures/block/oak_log_top.png");;
        topItem = null;
        topRightItem = new ResourceLocation("textures/item/flint.png");
        bottomLeftItem = null;
        rightStringLocal = "使用了岩浆块，煤炭，橡木圆木，燧石。按照上图合成即可。";
    }

}