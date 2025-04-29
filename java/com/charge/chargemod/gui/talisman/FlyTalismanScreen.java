package com.charge.chargemod.gui.talisman;

import com.charge.chargemod.ChargeModItemRegistry;
import com.charge.chargemod.gui.ChargeBaseScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public class FlyTalismanScreen extends ChargeBaseScreen {

    private static final Component title = Component.translatable("仙游符");
    public FlyTalismanScreen() {
        super(title);
        leftStringLocal = "仙游符，可以短暂的如同创世神一样，于空中随意飞行。\n" +
                "这方世界法力压制太过严重，飞行的时间有限，它无法连续使用，且没有抵御摔落的额外能力，使用符时请牢记剩余时间。";
        rightImage = new ResourceLocation(ChargeModItemRegistry.MODID,"textures/gui/xian_tian_ba_gua_craft.png");
        showCrafting = true;
        // 乾天, 兑泽（金）, 离火, 震雷（木）, 巽风（木）, 坎水, 艮山（土）, 坤地
        bottomItem = ChargeModItemRegistry.TALISMAN_PAPER_ITEM.get();
        bottomRightItem = null;
        rightItem = Items.PHANTOM_MEMBRANE;
        topRightItem = null;
        bottomLeftItem = Items.BAMBOO;
        leftItem = Items.FEATHER;
        topLeftItem = null;
        topItem = Items.COCOA_BEANS;
        rightStringLocal = "使用了符纸，幻翼膜，竹子，羽毛，可可豆。按照上图合成，短暂一瞬获得神的力量，这也是修仙的一大浪漫。";
    }

}