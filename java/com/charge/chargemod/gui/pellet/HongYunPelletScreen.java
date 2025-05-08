package com.charge.chargemod.gui.pellet;

import com.charge.chargemod.ChargeModItemRegistry;
import com.charge.chargemod.gui.ChargeBaseScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

public class HongYunPelletScreen extends ChargeBaseScreen {

    private static final Component title = Component.translatable("鸿运齐天丹");
    public HongYunPelletScreen() {
        super(title);
        leftStringLocal = "鸿运齐天丹，吃下去后可以短时间内大幅增加气运。\n" +
                "一开始我惊讶于这方世界气运如此好获得，后来才发现这世界所谓的幸运只影响钓鱼，而不是真正的气运。";
        rightImage = new ResourceLocation(ChargeModItemRegistry.MODID,"textures/gui/xian_tian_ba_gua_craft.png");
        showCrafting = true;
        // 乾天, 兑泽（金）, 离火, 震雷（木）, 巽风（木）, 坎水, 艮山（土）, 坤地
        topLeftItem = Items.LAPIS_LAZULI;
        leftItem = null;
        bottomItem = Items.GUNPOWDER;
        rightItem = null;
        bottomRightItem = Items.POPPY;
        topItem = Items.PUFFERFISH;
        topRightItem = null;
        bottomLeftItem = null;
        rightStringLocal = "使用了青金石，火药，虞美人，河豚。按照上图合成，请牢记这不是蛊虫，吃完只有短暂的运气。";
    }

}