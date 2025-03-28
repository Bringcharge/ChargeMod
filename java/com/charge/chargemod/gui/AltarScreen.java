package com.charge.chargemod.gui;

import com.charge.chargemod.ChargeModItemRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class AltarScreen extends ChargeBaseScreen {

    private static final Component title = Component.translatable("法台地基");
    public AltarScreen() {
        super(title);
        leftStringLocal = "首先说的是法坛的地基。法坛想要运作，就需要以阴阳相辅，这阴的部分自然是黑色的石头。厚重承载万物的基石无疑是最好的法台地基。";
        rightImage = new ResourceLocation(ChargeModItemRegistry.MODID,"textures/block/test_ore_block.png");
        rightStringLocal = "上图这个就是灵石矿，灵石作为修仙最基础也是最重要的材料，需要大量收集。遇到了还是挖掘留存为好";
    }

}