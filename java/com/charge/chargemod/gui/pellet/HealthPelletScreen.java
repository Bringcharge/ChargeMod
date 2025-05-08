package com.charge.chargemod.gui.pellet;

import com.charge.chargemod.ChargeModItemRegistry;
import com.charge.chargemod.gui.ChargeBaseScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

public class HealthPelletScreen extends ChargeBaseScreen {

    private static final Component title = Component.translatable("大还丹");
    public HealthPelletScreen() {
        super(title);
        leftStringLocal = "大还丹，吃下去后可以快速恢复大量生命值。\n" +
                "没有灵芝，就用蘑菇将就讲究。没有人参，就用胡萝卜将就将就。至于丹药药效嘛……也将就将就。";
        rightImage = new ResourceLocation(ChargeModItemRegistry.MODID,"textures/gui/hou_tian_ba_gua_craft.png");
        showCrafting = true;
        // 乾天, 兑泽（金）, 离火, 震雷（木）, 巽风（木）, 坎水, 艮山（土）, 坤地
        topLeftItem = null;
        leftItem = Items.RED_MUSHROOM;
        bottomItem = null;
        rightItem = Items.CARROT;
        bottomRightItem = Items.SPIDER_EYE;
        topItem = Items.NETHER_WART;
        topRightItem = null;
        bottomLeftItem = null;
        rightStringLocal = "使用了红蘑菇，胡萝卜，蜘蛛眼，地狱疣。按照上图合成，连这配方都能有效，世界真是离谱。";
    }

}