package com.charge.chargemod.gui.fa;

import com.charge.chargemod.gui.ChargeBaseScreen;
import net.minecraft.network.chat.Component;

public class FunctionScreen extends ChargeBaseScreen {

    private static final Component title = Component.translatable("功能道法");
    public FunctionScreen() {
        super(title);
        leftStringLocal = "功能道法以F为开头，下划线为结尾，后面会接着若干个道法所需要的参数。\n" +
                "F001_ 落雷 需要接 V（落雷位置）\n" +
                "F002_ 射箭 需要接 V（弓箭起点） V（弓箭朝向） D（弓箭速度） {}（射中之后执行的新道法）\n" +
                "F003_ 传送 需要接 E（被传送生物） V（传送地点）\n";

        rightStringLocal = "F004_ 交换位置 需要接 E（被交换生物1） E（被交换生物2）\n" +
                "F005_ 破坏方块 需要接 V（方块坐标）\n" +
                "Fif_ 判断 需要接 B（真假条件） {}（如果是真需要执行的道法） {}（假需要执行的道法）\n" +
                "这个Fif比较晦涩难懂，可以参考样例。{}中可以什么都不填写，即为不实用新的道法。";

    }

}