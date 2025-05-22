package com.charge.chargemod.gui.fa;

import com.charge.chargemod.gui.ChargeBaseScreen;
import net.minecraft.network.chat.Component;

public class BooleanScreen extends ChargeBaseScreen {

    private static final Component title = Component.translatable("判断道法");
    public BooleanScreen() {
        super(title);
        leftStringLocal = "判断道法以B为开头，下划线为结尾，后面会接着若干个道法所需要的参数。返还判断结论的真假。\n" +
                "B001_ 实体不为空 需要接 E（实体存在）\n" +
                "B002_ 向量不为空 需要接 V（是否为空，0向量不算空）\n" +
                "B010_ 两个整数相等 需要接 I（整数1） I（整数2）\n" +
                "B011_ 两个小数相等 需要接 D（小数1） D（小数2）\n";


        rightStringLocal = "\n";

    }

}