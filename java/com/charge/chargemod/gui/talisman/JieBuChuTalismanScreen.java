package com.charge.chargemod.gui.talisman;

import com.charge.chargemod.ChargeModItemRegistry;
import com.charge.chargemod.gui.ChargeBaseScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public class JieBuChuTalismanScreen extends ChargeBaseScreen {

    private static final Component title = Component.translatable("仙符 解不出");
    public JieBuChuTalismanScreen() {
        super(title);
        leftStringLocal = "解不出，四大仙符之一，将生物聚集在自己的前方，并施法困住的符。蕴含着大道的力量。\n" +
                "一日修炼，天问我：什么是道？我无法回答。求解多日，无法言道，终做出以此符，展示大道的千万分之一。";
        rightImage = new ResourceLocation(ChargeModItemRegistry.MODID,"textures/gui/xian_tian_ba_gua_craft.png");
        showCrafting = true;
        // 乾天, 兑泽（金）, 离火, 震雷（木）, 巽风（木）, 坎水, 艮山（土）, 坤地
        bottomItem = ChargeModItemRegistry.TALISMAN_PAPER_ITEM.get();
        bottomRightItem = Items.SOUL_SAND;
        rightItem = Items.SLIME_BALL;
        topRightItem = Items.ROTTEN_FLESH;
        bottomLeftItem = Items.NETHER_WART;
        leftItem = Items.ENDER_PEARL;
        topLeftItem = null;
        topItem = null;
        rightStringLocal = "使用了符纸，灵魂沙，粘液球，腐肉，地狱疣，末影珍珠。按照上图合成，此符蕴含着大道碎片。";
    }

}