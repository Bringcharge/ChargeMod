package com.charge.chargemod.gui.swords;

import com.charge.chargemod.ChargeModItemRegistry;
import com.charge.chargemod.gui.ChargeBaseScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class BaiCaoLingScreen extends ChargeBaseScreen {

    private static final Component title = Component.translatable("百草零");
    public BaiCaoLingScreen() {
        super(title);
        leftStringLocal = "百草零，五行属水。这剑明晃晃夺人眼眸，冷森森叫人胆寒……也确实是一把寒冰为主的剑。\n" +
                "消耗灵力，周遭的一切将快速冰冻，如果生物已经被冰冻则造成大量伤害。这极寒仅对生物有效，周遭的水面不会因此而冻结，这也是精心改良的结果。";
        rightImage = new ResourceLocation(ChargeModItemRegistry.MODID,"textures/gui/hou_tian_ba_gua_craft.png");
        showCrafting = true;
        // 乾天, 兑泽（金）, 离火, 震雷（木）, 巽风（木）, 坎水, 艮山（土）, 坤地
        topLeftItem = new ResourceLocation("textures/block/ice.png");
        leftItem = new ResourceLocation("textures/item/iron_sword.png");
        bottomItem = new ResourceLocation("textures/block/soul_sand.png");
        rightItem = new ResourceLocation("textures/block/snow.png");
        bottomRightItem = new ResourceLocation("textures/item/powder_snow_bucket.png");;
        topItem = new ResourceLocation("textures/item/potion.png");
        topRightItem = new ResourceLocation("textures/item/gunpowder.png");
        bottomLeftItem = new ResourceLocation("textures/item/sweet_berries.png");
        rightStringLocal = "使用了冰，铁剑，灵魂沙，雪，细雪桶，虚弱药水，火药，甜浆果。按照上图合成，这剑曾经是能冰冻水面的，直到一日发现灵田灌溉都被冻结了才痛下决心改良的。";
    }

}