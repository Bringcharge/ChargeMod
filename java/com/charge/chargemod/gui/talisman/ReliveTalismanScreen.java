package com.charge.chargemod.gui.talisman;

import com.charge.chargemod.ChargeModItemRegistry;
import com.charge.chargemod.gui.ChargeBaseScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

public class ReliveTalismanScreen extends ChargeBaseScreen {

    private static final Component title = Component.translatable("回生符");
    public ReliveTalismanScreen() {
        super(title);
        leftStringLocal = "回生符，从这个世界的无限轮回之中领悟出的符，在副手持有生物的肉或一些特殊战利品，可以再次生成生物。\n" +
                "世界上并不存在真正的起死回生，如果你断掉的手重新长出了身体，你觉得那身体还是你吗？";
        rightImage = new ResourceLocation(ChargeModItemRegistry.MODID,"textures/gui/xian_tian_ba_gua_craft.png");
        showCrafting = true;
        // 乾天, 兑泽（金）, 离火, 震雷（木）, 巽风（木）, 坎水, 艮山（土）, 坤地
        topLeftItem = ChargeModItemRegistry.TALISMAN_PAPER_ITEM.get();
        leftItem = Items.GHAST_TEAR;
        bottomItem = Items.SOUL_SOIL;
        rightItem = Items.RED_MUSHROOM;
        bottomRightItem = null;
        topItem = null;
        topRightItem = Items.MYCELIUM;
        bottomLeftItem = Items.BONE_MEAL;
        rightStringLocal = "使用了符纸，恶魂之泪，灵魂土，红蘑菇，菌丝，骨粉。按照上图合成，至于为什么蜂蜜瓶能产生蜂蜜，老朽也不清楚。";
    }

}