package com.charge.chargemod.gui.talisman;

import com.charge.chargemod.ChargeModItemRegistry;
import com.charge.chargemod.gui.ChargeBaseScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

public class SuanBuDuiTalismanScreen extends ChargeBaseScreen {

    private static final Component title = Component.translatable("仙符 算不对");
    public SuanBuDuiTalismanScreen() {
        super(title);
        leftStringLocal = "算不对，四大仙符之一，蒙蔽天道的力量，让所有作用在自己身上的伤害随机减少。\n" +
                "一日修炼，我问天：天可算尽世事？天不语。思索数日，若天知万物，又何来天罚渡劫，在成事之前早可杜绝。遂造出此符，以求蒙蔽天机。";
        rightImage = new ResourceLocation(ChargeModItemRegistry.MODID,"textures/gui/hou_tian_ba_gua_craft.png");
        showCrafting = true;
        // 乾天, 兑泽（金）, 离火, 震雷（木）, 巽风（木）, 坎水, 艮山（土）, 坤地
        topLeftItem = ChargeModItemRegistry.TALISMAN_PAPER_ITEM.get();
        leftItem = Items.GOLDEN_APPLE;
        bottomItem = null;
        rightItem = Items.GLISTERING_MELON_SLICE;
        bottomRightItem = Items.GOLDEN_CARROT;
        topItem = Items.PUMPKIN;
        topRightItem = null;
        bottomLeftItem = Items.HAY_BLOCK;
        rightStringLocal = "使用了符纸，金苹果，闪耀西瓜，金胡萝卜，南瓜，干草块。按照上图合成，此符蕴含着大道碎片。";
    }

}