package com.charge.chargemod.gui.array;

import com.charge.chargemod.ChargeModItemRegistry;
import com.charge.chargemod.gui.ChargeBaseScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class ZhenYaoArrayScreen extends ChargeBaseScreen {

    private static final Component title = Component.translatable("镇妖塔");
    public ZhenYaoArrayScreen() {
        super(title);
        leftStringLocal = "镇妖塔是防御类阵法，它构建完成后，会自动索敌周围的mob并发动攻击，被击中的单位会强行移动到镇妖塔的下方。\n" +
                "索敌是mod，但是发射出的降魔叉却不长眼，不小心被射中的话就得跟怪物一同被镇压了。\n" +
                "若有其余世界来的生物，它也照样镇杀，此乃阵法的缺陷。";
        rightImage = new ResourceLocation(ChargeModItemRegistry.MODID,"textures/gui/zhenyao_pic.png");
        rightStringLocal = "镇妖塔阵盘下方是一个金块，金块周围八格是粘土。阵盘东南、西南、东北、西北四角插上阵旗。阵盘的上方是一个木板。\n";
    }

}