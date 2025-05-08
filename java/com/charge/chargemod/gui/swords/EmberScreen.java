package com.charge.chargemod.gui.swords;

import com.charge.chargemod.ChargeModItemRegistry;
import com.charge.chargemod.gui.ChargeBaseScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

public class EmberScreen extends ChargeBaseScreen {

    private static final Component title = Component.translatable("余烬");
    public EmberScreen() {
        super(title);
        leftStringLocal = "余烬，五行属火。可以消耗灵力让怪物燃烧，若是已经燃烧的怪物则可以引发巨大的爆炸。\n" +
                "如同燃烧后的炭火，仅剩微不足道的暗红，却也可能引燃正片森林，烧尽一切。";
        rightImage = new ResourceLocation(ChargeModItemRegistry.MODID,"textures/gui/xian_tian_ba_gua_craft.png");
        showCrafting = true;
        // 乾天, 兑泽（金）, 离火, 震雷（木）, 巽风（木）, 坎水, 艮山（土）, 坤地
        bottomItem = null;
        bottomRightItem = null;
        rightItem = Items.MAGMA_BLOCK; //new ResourceLocation("textures/block/magma.png");
        topRightItem = Items.COAL; // new ResourceLocation("textures/item/coal.png");
        bottomLeftItem = Items.ACACIA_LOG; // new ResourceLocation("textures/block/oak_log_top.png");;
        leftItem = null;
        topLeftItem = Items.FLINT; //new ResourceLocation("textures/item/flint.png");
        topItem = null;
        rightStringLocal = "使用了岩浆块，煤炭，原木，燧石。按照上图合成即可。";
    }

}