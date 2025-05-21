package com.charge.chargemod.gui;

import com.charge.chargemod.ChargeModItemRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

public class BaseStoneScreen extends ChargeBaseScreen {

    private static final Component title = Component.translatable("法台地基");
    public BaseStoneScreen() {
        super(title);
        leftStringLocal = "首先说的是法坛的地基。法坛想要运作，需要借助天地之力。地基是灵力于石头结合，可以吸收自然灵气。按照八卦的方式摆放能够汇聚灵气。\n" +
                "地基也是阵盘的原料之一，在构建炼丹炼器阵时大量需要，光一个阵法就需约莫百十来个。";
        rightImage = new ResourceLocation(ChargeModItemRegistry.MODID,"textures/gui/empty.png");
        topItem = Items.STONE;
        topLeftItem = ChargeModItemRegistry.chargeLingShi.get();
        rightStringLocal = "这基石汇聚灵气，硬度虽然不高，却能抵抗爆炸，修建的建筑在爆炸面前也不易损坏，甚至能作为工事对抗凋零。";
    }

}