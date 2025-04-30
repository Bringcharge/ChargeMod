package com.charge.chargemod.gui.talisman;

import com.charge.chargemod.ChargeModItemRegistry;
import com.charge.chargemod.gui.ChargeBaseScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

public class MazeTalismanScreen extends ChargeBaseScreen {

    private static final Component title = Component.translatable("道符 ?");
    public MazeTalismanScreen() {
        super(title);
        leftStringLocal = "道符，也许大道的碎片叠加起来，能形成最终的符。\n" +
                "一日修炼，我问天，天不语。天问我，我无言。定坐良久，宛如进入天地之间，将大道碎片合而为一。清浊相交，阴阳不分，身处其中浑浑噩噩。";
        rightImage = new ResourceLocation(ChargeModItemRegistry.MODID,"textures/gui/xia_tian_ba_gua_craft.png");
        showCrafting = true;
        // 乾天, 兑泽（金）, 离火, 震雷（木）, 巽风（木）, 坎水, 艮山（土）, 坤地
        rightStringLocal = "万幸最终走出，却已然不知自己身处何方，所行何事，无意间提笔写下此页。";
    }

}