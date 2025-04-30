package com.charge.chargemod.gui.pellet;

import com.charge.chargemod.ChargeModItemRegistry;
import com.charge.chargemod.gui.ChargeBaseScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

public class WanWuShengTalismanScreen extends ChargeBaseScreen {

    private static final Component title = Component.translatable("万物生丹");
    public WanWuShengTalismanScreen() {
        super(title);
        leftStringLocal = "万物生丹，蕴含了生育力量的丹药，服用后能给周边的生物传递孕育生命的力量。\n" +
                "由于丹药的选在较为保守，传递能量需要使用者用身体撞击附近的生物才会有效。丹药出于安全考虑，去除了对一些生物的效果，请不要进行危险的尝试。";
        rightImage = new ResourceLocation(ChargeModItemRegistry.MODID,"textures/gui/xian_tian_ba_gua_craft.png");
        showCrafting = true;
        // 乾天, 兑泽（金）, 离火, 震雷（木）, 巽风（木）, 坎水, 艮山（土）, 坤地
        bottomItem = null;
        bottomRightItem = Items.BONE;
        rightItem = Items.ROTTEN_FLESH;
        topRightItem = Items.FERMENTED_SPIDER_EYE;
        bottomLeftItem = Items.SLIME_BALL;
        leftItem = Items.LEATHER;
        topLeftItem = Items.GUNPOWDER;
        topItem = null;
        rightStringLocal = "使用了骨头，腐肉，发酵蜘蛛眼，粘液球，皮革，火药。按照上图合成，用异变血肉精华合成丹药，连道祖看了都忍不住发出桀桀的笑声。";
    }

}