package com.charge.chargemod.gui;

import com.charge.chargemod.ChargeModItemRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

public class AltarScreen extends ChargeBaseScreen {

    private static final Component title = Component.translatable("置物坛");
    public AltarScreen() {
        super(title);
        leftStringLocal = "置物坛是与八卦炉、炼器铁砧配套使用的方块。可以往里面放置材料、取出材料。制作起来需要竹子，相对麻烦。\n" +
                "合成需要竹子，木棍，石头，按照如图所示的方式合成即可。";
        rightImage = new ResourceLocation(ChargeModItemRegistry.MODID,"textures/gui/empty.png");
        topLeftItem = topItem = topRightItem = Items.BAMBOO;
        leftItem = rightItem = Items.STICK;
        bottomLeftItem = bottomItem = bottomRightItem = Items.STONE;
        rightStringLocal = "置物台的作用仅仅是存放东西，一次存放一个。单纯摆在家里做展示柜也未尝不可。";
    }

}