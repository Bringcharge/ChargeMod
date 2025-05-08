package com.charge.chargemod.gui.swords;

import com.charge.chargemod.ChargeModItemRegistry;
import com.charge.chargemod.gui.ChargeBaseScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

public class KongFangScreen extends ChargeBaseScreen {

    private static final Component title = Component.translatable("孔方");
    public KongFangScreen() {
        super(title);
        leftStringLocal = "孔方，五行属金的铜钱剑。修行本应离不开钱财，这方世界钱财居然也失去了作用，这把剑用灵力可以发出铜钱攻击敌人\n" +
                "铜钱不算锋利，一次能发射3枚，对付一些远程怪物算是勉强够用。用铜钱打人，也算得上是挥金如土，潇洒半生了吧。";
        rightImage = new ResourceLocation(ChargeModItemRegistry.MODID,"textures/gui/xian_tian_ba_gua_craft.png");
        showCrafting = true;
        // 乾天, 兑泽（金）, 离火, 震雷（木）, 巽风（木）, 坎水, 艮山（土）, 坤地
        bottomItem = Items.COPPER_INGOT;// new ResourceLocation("textures/item/copper_ingot.png");
        bottomRightItem = Items.COPPER_INGOT;// new ResourceLocation("textures/item/copper_ingot.png");
        rightItem = Items.RED_DYE;// new ResourceLocation("textures/item/red_dye.png");
        topRightItem = null;
        bottomLeftItem = Items.STRING; //new ResourceLocation("textures/item/string.png");
        leftItem = null;
        topLeftItem = null;
        topItem = null;
        rightStringLocal = "使用了铜锭，铜锭，红色染料，线。按照上图合成即可。";
    }

}