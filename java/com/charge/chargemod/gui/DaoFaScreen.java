package com.charge.chargemod.gui;

import com.charge.chargemod.ChargeModItemRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class DaoFaScreen extends ChargeBaseScreen {

    private static final Component title = Component.translatable("道法");
    public DaoFaScreen() {
        super(title);
        leftStringLocal = "道友可能好奇，为何修炼到最后，依旧是借助外力在释放灵力，为什么没有真正的道法传承。\n" +
                "从我创造出求不得那一刻起，我悟了这世界的规律，这条大道的尽头是无穷无尽的迷惘于疯狂。直接使用灵力，大道会渐渐找到你……\n" +
                "我已经见过了祂……";
        rightStringLocal = "道法：言出法随\n" +
        "使用语言的能力可以直接释放法术，灵力消耗很高。为了防止被大道快速找到，我对语言进行了一些简化和加密，删去了些许法术。";
    }

}