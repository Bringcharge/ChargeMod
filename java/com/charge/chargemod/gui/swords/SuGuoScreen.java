package com.charge.chargemod.gui.swords;

import com.charge.chargemod.ChargeModItemRegistry;
import com.charge.chargemod.gui.ChargeBaseScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

public class SuGuoScreen extends ChargeBaseScreen {

    private static final Component title = Component.translatable("塑果");
    public SuGuoScreen() {
        super(title);
        leftStringLocal = "塑果，因果剑之一，以自身以剑为因，塑造杀戮的果。\n" +
                "使用灵力可以将目标拉到面前，撞上剑身。在一定程度上可以封锁爬行者和末影人的特性，塑造它们被剑杀死的果。因果过于强大，仅敢用于杀戮，用于其它途径很可能会破坏世界的规则。";
        rightImage = new ResourceLocation(ChargeModItemRegistry.MODID,"textures/gui/xian_tian_ba_gua_craft.png");
        showCrafting = true;
        //乾天, 兑泽（金）, 离火, 震雷（木）, 巽风（木）, 坎水, 艮山（土）, 坤地
        topLeftItem = Items.CLOCK;
        leftItem = Items.GRASS;
        bottomItem = Items.SAND;
        rightItem = Items.APPLE;
        bottomRightItem = Items.CACTUS;
        topItem = Items.CLAY;
        topRightItem = Items.GOLD_ORE;
        bottomLeftItem = Items.NETHER_BRICK_SLAB;
        rightStringLocal = "使用时钟，玻璃，沙子，苹果，仙人掌，粘土块，金矿石，地狱砖块合成，因果本身太过强大，以灵力仅能微弱的撬动它，威力并不会过于强大。";
    }

}