package com.charge.chargemod.gui.swords;

import com.charge.chargemod.ChargeModItemRegistry;
import com.charge.chargemod.gui.ChargeBaseScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

public class FuYaoScreen extends ChargeBaseScreen {

    private static final Component title = Component.translatable("扶摇");
    public FuYaoScreen() {
        super(title);
        leftStringLocal = "扶摇，五行属木。大鹏一日同风起、扶摇直上九万里，你我非大鹏，却可化为扶摇上云霄。\n" +
                "木属本就不为了杀生，此剑借了大鹏些许逍遥之意，使用灵力乘风而行，行动上往往快人一步。";
        rightImage = new ResourceLocation(ChargeModItemRegistry.MODID,"textures/gui/hou_tian_ba_gua_craft.png");
        showCrafting = true;
        // 乾天, 兑泽（金）, 离火, 震雷（木）, 巽风（木）, 坎水, 艮山（土）, 坤地
        topLeftItem = Items.ACACIA_LEAVES;// new ResourceLocation("textures/block/azalea_leaves.png");
        leftItem = null;
        bottomItem = null;
        rightItem = Items.SUGAR_CANE;// new ResourceLocation("textures/item/sugar_cane.png");
        bottomRightItem = Items.PAPER; // new ResourceLocation("textures/item/paper.png");
        topItem = null;
        topRightItem = Items.STICK;// new ResourceLocation("textures/item/stick.png");
        bottomLeftItem = null;
        rightStringLocal = "使用了橡树树叶，甘蔗，纸，木棍。按照上图合成即可。";
    }

}