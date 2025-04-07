package com.charge.chargemod.gui.swords;

import com.charge.chargemod.ChargeModItemRegistry;
import com.charge.chargemod.gui.ChargeBaseScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class ZhongZhongGuScreen extends ChargeBaseScreen {

    private static final Component title = Component.translatable("冢中骨");
    public ZhongZhongGuScreen() {
        super(title);
        leftStringLocal = "冢中骨，一把以五行土为主的武器，动用灵力可以开山碎石，这份力量也能作用在生物身上。\n" +
                "伤害虽然平平无奇，不过黑曜石这种坚硬的石头也能瞬间破坏，短时间内打通通道也许能在危险时找到一线生机。";
        rightImage = new ResourceLocation(ChargeModItemRegistry.MODID,"textures/gui/hou_tian_ba_gua_craft.png");
        showCrafting = true;
        // 乾天, 兑泽（金）, 离火, 震雷（木）, 巽风（木）, 坎水, 艮山（土）, 坤地
        topLeftItem = new ResourceLocation("textures/item/bone.png");
        leftItem = new ResourceLocation("textures/item/copper_ingot.png");
        bottomItem = null;
        rightItem = null;
        bottomRightItem = null;
        topItem = null;
        topRightItem = new ResourceLocation("textures/block/stone.png");
        bottomLeftItem = new ResourceLocation("textures/block/iron_ore.png");
        rightStringLocal = "使用了骨头，铜锭，石头，铁矿石。按照上图合成即可。";
    }

}