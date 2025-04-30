package com.charge.chargemod.gui.pellet;

import com.charge.chargemod.ChargeModItemRegistry;
import com.charge.chargemod.gui.ChargeBaseScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

public class SanShiTalismanScreen extends ChargeBaseScreen {

    private static final Component title = Component.translatable("三尸丹");
    public SanShiTalismanScreen() {
        super(title);
        leftStringLocal = "服用后，会经历短暂的不适，并且唤出自身的三尸。\n" +
                "成仙的法子有很多，尸解成仙，五气朝元，功德圆满……这些在这方世界太过困难，不若选择斩三尸成仙。";
        rightImage = new ResourceLocation(ChargeModItemRegistry.MODID,"textures/gui/xian_tian_ba_gua_craft.png");
        showCrafting = true;
        // 乾天, 兑泽（金）, 离火, 震雷（木）, 巽风（木）, 坎水, 艮山（土）, 坤地
        bottomItem = ChargeModItemRegistry.CHARGE_WAN_WU_SHENG_PELLET.get();
        bottomRightItem = Items.POISONOUS_POTATO;
        rightItem = ChargeModItemRegistry.CHARGE_BIGU_PELLET.get();
        topRightItem = null;
        bottomLeftItem = Items.PUFFERFISH;
        leftItem = Items.HONEY_BOTTLE;
        topLeftItem = null;
        topItem = null;
        rightStringLocal = "使用了万物生丹，毒马铃薯，辟谷丹，河豚，蜂蜜。按照上图合成。成仙不易，大道无情。";
    }

}