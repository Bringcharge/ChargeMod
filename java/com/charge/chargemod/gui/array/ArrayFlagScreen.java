package com.charge.chargemod.gui.array;

import com.charge.chargemod.ChargeModItemRegistry;
import com.charge.chargemod.gui.ChargeBaseScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

public class ArrayFlagScreen extends ChargeBaseScreen {

    private static final Component title = Component.translatable("阵旗");
    public ArrayFlagScreen() {
        super(title);
        leftStringLocal = "阵旗是用于阵法的道具，每个阵法都需要4个阵旗，经过老夫的化简基本上阵法都是对称的，无需仔细分辨东西南北。\n" +
                "当然也化简了所用材料，任意羊毛下面是木棍，木棍下面是灵石，在工作台即可合成。";
        rightImage = new ResourceLocation(ChargeModItemRegistry.MODID,"textures/gui/empty.png");
        showCrafting = true;
        topLeftItem = Items.WHITE_WOOL;
        leftItem = Items.STICK;
        bottomLeftItem = ChargeModItemRegistry.chargeLingShi.get();
        rightStringLocal = "阵旗同样具有良好的抗爆性，只不过方块不完整，不适合用于制作房屋。";
    }

}