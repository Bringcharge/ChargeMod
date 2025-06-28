package com.charge.chargemod.gui.array;

import com.charge.chargemod.ChargeModItemRegistry;
import com.charge.chargemod.gui.ChargeBaseScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class TongQianArrayScreen extends ChargeBaseScreen {

    private static final Component title = Component.translatable("铜钱阵");
    public TongQianArrayScreen() {
        super(title);
        leftStringLocal = "铜钱阵是防御类阵法，它构建完成后，会自动索敌周围的mob并发动攻击，一次攻击最多3个敌人。\n" +
                "索敌是mod，无论哪个世界的mod都照打不误。对付多个敌人时好用。\n" +
                "用铜钱攻击在其他世界是相当奢侈，而在这方世界铜往往是多出来没用的方块，真令人唏嘘。";
        rightImage = new ResourceLocation(ChargeModItemRegistry.MODID,"textures/gui/tongqian_pic.png");
        rightStringLocal = "铜钱阵阵盘下方是一个铜块，铜块周围八格是苔藓块。阵盘东南、西南、东北、西北四角插上阵旗。阵盘的上方是一个木板。\n";
    }

}