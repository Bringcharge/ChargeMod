package com.charge.chargemod.gui;

import com.charge.chargemod.ChargeModItemRegistry;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class LianDanScreen extends ChargeBaseScreen {

    private static final Component title = Component.translatable("灵石");
    public LianDanScreen() {
        super(title);
        leftStringLocal = "引气入体后，我们。\n";
        rightImage = new ResourceLocation(ChargeModItemRegistry.MODID,"textures/block/test_ore_block.png");
        rightStringLocal = "上图这个就是灵石矿，灵石作为修仙最基础也是最重要的材料，需要大量收集。遇到了还是挖掘留存为好";
    }

}