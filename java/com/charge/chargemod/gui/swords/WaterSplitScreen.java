package com.charge.chargemod.gui.swords;

import com.charge.chargemod.ChargeModItemRegistry;
import com.charge.chargemod.gui.ChargeBaseScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class WaterSplitScreen extends ChargeBaseScreen {

    private static final Component title = Component.translatable("断流");
    public WaterSplitScreen() {
        super(title);
        leftStringLocal = "断流，五行之水。当握住这把剑，杀意随水凝在剑锋，当杀意与灵力充足，一剑连水流都能斩断！\n" +
                "剑断得了水流，却断不了似水的愁丝，也断不了因果，断不了迷惘。修仙到最后修的究竟是什么，还需要持剑人亲自找寻。";
        rightImage = new ResourceLocation(ChargeModItemRegistry.MODID,"textures/gui/hou_tian_ba_gua_craft.png");
        showCrafting = true;
        // 乾天, 兑泽（金）, 离火, 震雷（木）, 巽风（木）, 坎水, 艮山（土）, 坤地
        topLeftItem = new ResourceLocation("textures/item/bucket.png");
        leftItem = null;
        bottomItem = new ResourceLocation("textures/item/charcoal.png");
        rightItem = null;
        bottomRightItem = null;
        topItem = new ResourceLocation("textures/item/salmon.png");;
        topRightItem = null;
        bottomLeftItem = new ResourceLocation("textures/item/stick.png");
        rightStringLocal = "使用了橡树树叶，甘蔗，纸，木棍。按照上图合成即可。";
    }

}