package com.charge.chargemod.gui.talisman;

import com.charge.chargemod.ChargeModItemRegistry;
import com.charge.chargemod.gui.ChargeBaseScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public class SkullStealTalismanScreen extends ChargeBaseScreen {

    private static final Component title = Component.translatable("换颅符");
    public SkullStealTalismanScreen() {
        super(title);
        leftStringLocal = "换颅符，偷偷改换生物的头颅，将对方的头颅换成纸人头颅，并盗取对面原本头颅。\n" +
                "取敌方首级如探囊取物，只是取了对面不会死亡，还有反击的余地让这符略显尴尬。";
        rightImage = new ResourceLocation(ChargeModItemRegistry.MODID,"textures/gui/xian_tian_ba_gua_craft.png");
        showCrafting = true;
        // 乾天, 兑泽（金）, 离火, 震雷（木）, 巽风（木）, 坎水, 艮山（土）, 坤地
        bottomItem = ChargeModItemRegistry.TALISMAN_PAPER_ITEM.get();
        bottomRightItem = Items.REDSTONE;
        rightItem = null;
        topRightItem = Items.PAPER;
        bottomLeftItem = Items.PAPER;
        leftItem =Items.STONE_AXE;
        topLeftItem = null;
        topItem = Items.CLAY;
        rightStringLocal = "使用了符纸，红石，纸，纸，石斧，粘土。按照上图合成，此符属于邪门歪道，但让雷电爬行者把脑袋炸下来似乎更感邪门。";
    }

}