package com.charge.chargemod.gui.fa;

import com.charge.chargemod.gui.ChargeBaseScreen;
import net.minecraft.network.chat.Component;

public class DaoStructureScreen extends ChargeBaseScreen {

    private static final Component title = Component.translatable("道法结构");
    public DaoStructureScreen() {
        super(title);
        leftStringLocal = "关于道法的格式，需要以/dao作为开头，后面跟着的是咒语，咒语分为多种部分。\n" +
                "包含功能（F），向量（V），整数（I），小数（D），实体（E），是非（B），新的咒语用{}表示";
        rightStringLocal = "老夫也不多说了，来一个实用样例吧 /dao F004_E101_E301_V103_V101_E101_ 这个道法的作用是交换视野中央的生物与自身的位置。\n" +
                "而 /dao F002_V103_V101_DI4_{Fif_B001_E201_{F004_E101_E201_}{F003_E101_V201_}}则是射出一支箭，若命中地面则传送自己过去，若射中生物则交换位置。";
    }

}