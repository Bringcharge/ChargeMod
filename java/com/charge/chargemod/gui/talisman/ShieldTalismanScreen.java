package com.charge.chargemod.gui.talisman;

import com.charge.chargemod.ChargeModItemRegistry;
import com.charge.chargemod.gui.ChargeBaseScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

public class ShieldTalismanScreen extends ChargeBaseScreen {

    private static final Component title = Component.translatable("御劫符");
    public ShieldTalismanScreen() {
        super(title);
        leftStringLocal = "御劫符，释放符内的灵力短暂成为护盾。\n" +
                "没什么优势，成本便宜，使用迅速，比起金苹果来产量一定程度上更高。出门必备";
        rightImage = new ResourceLocation(ChargeModItemRegistry.MODID,"textures/gui/xian_tian_ba_gua_craft.png");
        showCrafting = true;
        // 乾天, 兑泽（金）, 离火, 震雷（木）, 巽风（木）, 坎水, 艮山（土）, 坤地
        bottomItem = ChargeModItemRegistry.TALISMAN_PAPER_ITEM.get();
        bottomRightItem = Items.IRON_INGOT;
        rightItem = null;
        topRightItem = Items.OAK_PLANKS;
        bottomLeftItem = null;
        leftItem =null;
        topLeftItem = Items.COBBLESTONE;
        topItem = null;
        rightStringLocal = "使用了符纸，铁锭，任意木板，圆石。按照上图合成，这只是暂时的，盾牌依旧是可靠的战友。";
    }

}