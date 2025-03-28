package com.charge.chargemod.gui;

import com.example.examplemod.ExampleMod;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class LevelScreen extends ChargeBaseScreen {

    private static final Component title = Component.translatable("修为");
    public LevelScreen() {
        super(title);
        leftStringLocal = "在其他世界，修为是一个修士能力的体现。可以延长寿元，增强体魄。\n" +
                "不过这个世界生物寿元无穷，人人力能抗鼎，大金块随心所欲往身上搬，修为也显得无关紧要了。所以修行的方向从修为转变为灵力的提升，分为引气入体，斩三尸成仙，度过天罚雷劫三部分。" +
                "每度过一关，灵力都能大幅提升，炼制物品也需要巨大的法力去维持和驱动，当灵力不足时很多宝物无法炼制。同样，哪怕别的道友炼制出来赠与你，灵力也不一定能驱使。";
        rightStringLocal = "大部分的灵力提升，还是得依靠丹药完成，接下来会详细讲述如何建造法坛，如何炼丹，以及炼器、符箓、阵法等内容";
    }

}