package com.charge.chargemod.gui.array;

import com.charge.chargemod.ChargeModItemRegistry;
import com.charge.chargemod.gui.ChargeBaseScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class JuLingArrayScreen extends ChargeBaseScreen {

    private static final Component title = Component.translatable("聚灵阵");
    public JuLingArrayScreen() {
        super(title);
        leftStringLocal = "聚灵阵是辅助类阵法，进入范围内的道友可以加速灵力回复，比吃灵石效率要高一些，未登仙路者初次进入也可引气入体。\n" +
                "阵法限制了范围，出了范围效果很快就会消失。对于修炼而言……并没有什么作用\n" +
                "若在老夫的世界，聚灵阵应是修炼必备之物，到了这里，全靠吃丹药堆上去，欸……";
        rightImage = new ResourceLocation(ChargeModItemRegistry.MODID,"textures/gui/juling_pic.png");
        rightStringLocal = "聚灵阵阵盘下方是一个红石块，下方的东南西北是蛛网，下方4角为岩浆块，岩浆块上方插阵旗。阵盘的上方是一个青金石块。\n";
    }

}