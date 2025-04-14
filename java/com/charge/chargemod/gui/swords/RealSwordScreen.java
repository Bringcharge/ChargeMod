package com.charge.chargemod.gui.swords;

import com.charge.chargemod.ChargeModItemRegistry;
import com.charge.chargemod.gui.ChargeBaseScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class RealSwordScreen extends ChargeBaseScreen {

    private static final Component title = Component.translatable("真");
    public RealSwordScreen() {
        super(title);
        leftStringLocal = "真，具有真实力量的剑。修真一词本就是寻找大道真实一面，剑随心动，达到此等境界时候此剑也可以去伪存真。\n" +
                "怪物身上的装备武器大多都是幻化而成，却有着真实的效果。此剑消耗灵力可以凝虚为实，让哪些装备化为真实而为你所用。";
        rightImage = new ResourceLocation(ChargeModItemRegistry.MODID,"textures/block/test_ore_block.png");
        showCrafting = true;
        //
        topLeftItem = new ResourceLocation("textures/block/gold_block.png");
        leftItem = new ResourceLocation("textures/block/gilded_blackstone.png");
        bottomItem = new ResourceLocation("textures/block/netherrack.png");
        rightItem = new ResourceLocation("textures/block/sunflower_front.png");
        bottomRightItem = new ResourceLocation("textures/item/compass_03.png");
        topItem = new ResourceLocation("textures/item/prismarine_crystals.png");;
        topRightItem = new ResourceLocation("textures/block/diamond_block.png");
        bottomLeftItem = new ResourceLocation("textures/item/potion.png");
        rightStringLocal = "使用金块，镶金黑石，地狱岩，向日葵，指南针，海晶石，钻石块，夜视药水合成，真实虽好，可剑求真也就意味着威力受限于真实，对比起同品级的剑而已不善杀伐。";
    }

}