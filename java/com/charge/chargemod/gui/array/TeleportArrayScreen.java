package com.charge.chargemod.gui.array;

import com.charge.chargemod.ChargeModItemRegistry;
import com.charge.chargemod.gui.ChargeBaseScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class TeleportArrayScreen extends ChargeBaseScreen {

    private static final Component title = Component.translatable("传送阵");
    public TeleportArrayScreen() {
        super(title);
        leftStringLocal = "传送阵可以说是最实用的阵法，它构建完成后，空手使用输入坐标，便能确定传送目的地。手持令牌使用便可传送。\n" +
                "传送阵需要消耗身上存储的灵石，1个灵石可以传送10格，每次最多消耗一组灵石（一组堆叠满是64个），注意别把自己传送到石头里。";
        //TODO:需要加图
        rightImage = new ResourceLocation(ChargeModItemRegistry.MODID,"textures/block/test_ore_block.png");
        rightStringLocal = "传送阵阵盘下方是一个末影箱，下方的东南西北是火焰，下方4角为黑曜石。黑曜石上方插着阵旗。阵盘的上方是冰。\n" +
                "冰火共存，末影开路。传送是单向的，只在一世界有用，若是找不到回家的路那可得不偿失。";
    }

}