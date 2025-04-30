package com.charge.chargemod.gui.pellet;

import com.charge.chargemod.ChargeModItemRegistry;
import com.charge.chargemod.gui.ChargeBaseScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

public class DengXianTalismanScreen extends ChargeBaseScreen {

    private static final Component title = Component.translatable("登仙丹");
    public DengXianTalismanScreen() {
        super(title);
        leftStringLocal = "登仙丹，调用体内灵力让仙体力量发挥到极限，到达真仙的高度，还会引动天罚雷劫。\n" +
                "丹方选取了世间稀有之物，凑齐便代表已经游历这方世界，借用世界之力推动体内灵力达到巅峰。引来的雷劫一旦开始，便无法停止，需谨慎使用。";
        rightImage = new ResourceLocation(ChargeModItemRegistry.MODID,"textures/gui/xian_tian_ba_gua_craft.png");
        showCrafting = true;
        // 乾天, 兑泽（金）, 离火, 震雷（木）, 巽风（木）, 坎水, 艮山（土）, 坤地
        bottomItem = ChargeModItemRegistry.CHARGE_PO_WAN_FA_PELLET.get();
        bottomRightItem = Items.LIGHTNING_ROD;
        rightItem = Items.BELL;
        topRightItem = Items.CHAIN;
        bottomLeftItem = Items.ANCIENT_DEBRIS;
        leftItem = Items.TRIDENT;
        topLeftItem = Items.DIAMOND_ORE;
        topItem = Items.NETHER_STAR;
        rightStringLocal = "使用了破万法丹，避雷针，钟，锁链，远古残骸，三叉戟，钻石矿石，下界之星。按照上图合成。雷劫威能强大，需多寻些保命之物\n" +
                "且天道狡诈，有些雷会长久不散。这雷劫……命也，命也。";
    }

}