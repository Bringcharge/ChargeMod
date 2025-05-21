package com.charge.chargemod.gui.array;

import com.charge.chargemod.ChargeModItemRegistry;
import com.charge.chargemod.gui.ChargeBaseScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

public class ArrayDiskScreen extends ChargeBaseScreen {

    private static final Component title = Component.translatable("阵盘");
    public ArrayDiskScreen() {
        super(title);
        leftStringLocal = "阵盘是阵法的核心，放置于阵法中央。最初的阵法是布置于天地之间，而后老夫加入阵盘，将阵法汇聚保留在阵盘上，形成各类阵，放下便可使用。\n" +
                "只是这类阵法一旦放置便不可拆，放置时还请谨慎。";
        rightImage = new ResourceLocation(ChargeModItemRegistry.MODID,"textures/gui/empty.png");
        topLeftItem = Items.BONE_MEAL;
        topRightItem = ChargeModItemRegistry.chargeBaseBlockItem.get();
        centerItem =  Items.GOLD_INGOT;
        bottomLeftItem = ChargeModItemRegistry.chargeBaseBlockItem.get();
        bottomRightItem =Items.BONE_MEAL;
        rightStringLocal = "需要用金锭，基石，骨粉合成。阵法完成后，手持令牌使用阵盘，即可成阵，成阵也需要消耗灵力，需要斩三尸之后才可使用。";
    }

}