package com.charge.chargemod.gui.fa;

import com.charge.chargemod.gui.ChargeBaseScreen;
import net.minecraft.network.chat.Component;

public class DoubleScreen extends ChargeBaseScreen {

    private static final Component title = Component.translatable("小数道法");
    public DoubleScreen() {
        super(title);
        leftStringLocal = "小数道法以D为开头，下划线为结尾，后面会接着若干个道法所需要的参数。\n" +
                "DI 直接输入数字 如DI1.0_ DI3.14_\n" +
                "D000_ 相加 需要接 D（小数1） D（小数2），小数1+小数2\n" +
                "D001_ 相加 需要接 D（小数1） D（小数2），小数1-小数2\n" +
                "D002_ 相加 需要接 D（小数1） D（小数2），小数1*小数2\n" +
                "D003_ 相加 需要接 D（小数1） D（小数2），小数1/小数2\n";


        rightStringLocal = "D010_ 整数转小数 需要接 I（整数）\n";

    }

}