package com.charge.chargemod.gui.talisman;

import com.charge.chargemod.ChargeModItemRegistry;
import com.charge.chargemod.gui.ChargeBaseScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

public class HoldLifeTalismanScreen extends ChargeBaseScreen {

    private static final Component title = Component.translatable("延死符");
    public HoldLifeTalismanScreen() {
        super(title);
        leftStringLocal = "延死符，此符在使用者受到致命伤时，会从背包中自动触发，暂时延使用者的死亡。\n" +
                "延死，只能拖延死亡的时间，当符的持续时间结束后，施术者就会不可避免的死亡。这短暂的时间可以保护身上的物资或是拼死一搏，渡劫时也许能有奇效。";
        rightImage = new ResourceLocation(ChargeModItemRegistry.MODID,"textures/gui/hou_tian_ba_gua_craft.png");
        showCrafting = true;
        // 乾天, 兑泽（金）, 离火, 震雷（木）, 巽风（木）, 坎水, 艮山（土）, 坤地
        topLeftItem = ChargeModItemRegistry.TALISMAN_PAPER_ITEM.get();
        leftItem = Items.LAPIS_LAZULI;
        bottomItem = Items.SOUL_SAND;
        rightItem = Items.WARPED_STEM;
        bottomRightItem = null;
        topItem = Items.GHAST_TEAR;
        topRightItem = null;
        bottomLeftItem = Items.ROTTEN_FLESH;
        rightStringLocal = "使用了符纸，青金石，灵魂沙，诡异菌柄，恶魂泪，腐肉。按照上图合成，这符也可以主动使用，这无异于自杀。";
    }

}