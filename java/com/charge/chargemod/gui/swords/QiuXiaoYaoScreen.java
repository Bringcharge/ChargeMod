package com.charge.chargemod.gui.swords;

import com.charge.chargemod.ChargeModItemRegistry;
import com.charge.chargemod.gui.ChargeBaseScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

public class QiuXiaoYaoScreen extends ChargeBaseScreen {

    private static final Component title = Component.translatable("仙剑 求逍遥");
    public QiuXiaoYaoScreen() {
        super(title);
        leftStringLocal = "仙剑求逍遥，修仙之人大多都在追求无忧无虑逍遥自在的生活。这个世界既有了无线的寿命，逍遥长生也未必遥不可及。\n" +
                "此剑跳脱五行之外，以凌厉的剑气伤害怪物。也可以斩开空间，短距离传送。";
        rightImage = new ResourceLocation(ChargeModItemRegistry.MODID,"textures/gui/xian_tian_ba_gua_craft.png");
        showCrafting = true;
        //乾天, 兑泽（金）, 离火, 震雷（木）, 巽风（木）, 坎水, 艮山（土）, 坤地
        topLeftItem = Items.PLAYER_HEAD;
        leftItem = Items.GRASS;
        bottomItem = Items.CHORUS_FRUIT;
        rightItem = Items.ENDER_PEARL;
        bottomRightItem = Items.BLAZE_POWDER;
        topItem = Items.PUFFERFISH;
        topRightItem = Items.OBSIDIAN;
        bottomLeftItem = Items.WITHER_SKELETON_SKULL;
        rightStringLocal = "使用玩家头颅，草，紫颂果，末影珍珠，烈焰粉，河豚，黑曜石，凋零骷髅头合成，能瞬间移动和快速斩杀怪物，颇有几分逍遥之意，只是这玩家头颅难以获取，要费一番功夫。";
    }

}