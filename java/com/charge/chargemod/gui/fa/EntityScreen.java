package com.charge.chargemod.gui.fa;

import com.charge.chargemod.gui.ChargeBaseScreen;
import net.minecraft.network.chat.Component;

public class EntityScreen extends ChargeBaseScreen {

    private static final Component title = Component.translatable("实体道法");
    public EntityScreen() {
        super(title);
        leftStringLocal = "实体道法以E为开头，下划线为结尾，后面会接着若干个道法所需要的参数。若别的道法需要实体，则接入E\n" +
                "E101_ 施术者 不需要接\n" +
                "E201_ 被命中的生物  不需要接（射箭命中后的道法中可以使用）\n";

        rightStringLocal = "E301_ 视线上的聚焦的实体 需要接 V（眼睛的位置） V（眼睛看的方向） E（是谁在看）\n" +
                "通常视线聚焦 的眼睛位置就是最后参数实体眼睛的位置";
    }

}