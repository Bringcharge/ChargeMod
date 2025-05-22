package com.charge.chargemod.gui.fa;

import com.charge.chargemod.gui.ChargeBaseScreen;
import net.minecraft.network.chat.Component;

public class IntegerScreen extends ChargeBaseScreen {

    private static final Component title = Component.translatable("整数道法");
    public IntegerScreen() {
        super(title);
        leftStringLocal = "整数道法以I为开头，下划线为结尾。\n" +
                "II 直接输入数字 如II666_ II114514_\n";


        rightStringLocal = "\n";

    }

}