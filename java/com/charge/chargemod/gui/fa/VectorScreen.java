package com.charge.chargemod.gui.fa;

import com.charge.chargemod.gui.ChargeBaseScreen;
import net.minecraft.network.chat.Component;

public class VectorScreen extends ChargeBaseScreen {

    private static final Component title = Component.translatable("向量道法");
    public VectorScreen() {
        super(title);
        leftStringLocal = "向量道法以V为开头，下划线为结尾，后面会接着若干个道法所需要的参数。为别的道法计算向量。\n" +
                "V001_ 向量取反 需要接 V（被取反的向量）\n" +
                "V002_ 向量缩放 需要接 V（需要缩放的向量） D（缩放的倍率）\n" +
                "V003_ 单位向量 需要接 V（被单位化的向量）\n" +
                "V011_ 向量加法 需要接 V（向量1） V（向量2）\n" +
                "V012_ 向量减法 需要接 V（被减的向量） V（减去的向量）";


        rightStringLocal = "V020_ 向量被方块阻挡的方块坐标 需接收 V（起点） V（方向向量）\n" +
                "V101_ 使用者的视线方向 不需接收\n" +
                "V102_ 使用者位置 不需接收\n" +
                "V103_ 使用者相机位置 不需接收\n" +
                "V201_ 回调位置，例如箭射中的位置 不需接收\n" +
                "V301_ 实体位置 需要接 E（实体）";
    }

}