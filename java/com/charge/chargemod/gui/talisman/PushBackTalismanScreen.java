package com.charge.chargemod.gui.talisman;

import com.charge.chargemod.ChargeModItemRegistry;
import com.charge.chargemod.gui.ChargeBaseScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

public class PushBackTalismanScreen extends ChargeBaseScreen {

    private static final Component title = Component.translatable("退魔符");
    public PushBackTalismanScreen() {
        super(title);
        leftStringLocal = "退魔符，释放出符内巨大灵力，将周围的生物弹飞。\n" +
                "弹飞的伤害不高，但是生物落地会有伤害。可惜使用的瞬间没有无敌判定，不能当成凹来使用。";
        rightImage = new ResourceLocation(ChargeModItemRegistry.MODID,"textures/gui/hou_tian_ba_gua_craft.png");
        showCrafting = true;
        // 乾天, 兑泽（金）, 离火, 震雷（木）, 巽风（木）, 坎水, 艮山（土）, 坤地
        topLeftItem = ChargeModItemRegistry.TALISMAN_PAPER_ITEM.get();
        leftItem = Items.IRON_NUGGET;
        bottomItem = Items.GUNPOWDER;
        rightItem = null;
        bottomRightItem = null;
        topItem =null;
        topRightItem = null;
        bottomLeftItem = Items.NETHERRACK;
        rightStringLocal = "使用了符纸，铁粒，火药，地狱岩。按照上图合成，不必担心存储，这符不会因为含有火药而爆炸。";
    }

}