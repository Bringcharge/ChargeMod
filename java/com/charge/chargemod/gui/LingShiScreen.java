package com.charge.chargemod.gui;

import com.charge.chargemod.ChargeModItemRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class LingShiScreen extends ChargeBaseScreen {

    private static final Component title = Component.translatable("炼丹");
    public LingShiScreen() {
        super(title);
        leftStringLocal = "修仙最基本的一个东西，就是灵气。这个世界有灵气，不过没经过修行的人是感知不到灵气的。\n" +
                "世界上也自然有许多天然形成，灵气浓郁的矿物，叫做灵石矿。去地下寻找一些灵石矿，挖掉可以获得灵石。" +
                "使用后可以加速灵气回复，如果没有灵气则可以引气入体。";
        rightImage = new ResourceLocation(ChargeModItemRegistry.MODID,"textures/block/charge_ling_shi_ore.png");
        rightStringLocal = "上图这个就是灵石矿，灵石作为修仙最基础也是最重要的材料，需要大量收集。遇到了还是挖掘留存为好";
    }

}