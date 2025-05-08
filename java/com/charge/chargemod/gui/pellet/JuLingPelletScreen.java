package com.charge.chargemod.gui.pellet;

import com.charge.chargemod.ChargeModItemRegistry;
import com.charge.chargemod.gui.ChargeBaseScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

public class JuLingPelletScreen extends ChargeBaseScreen {

    private static final Component title = Component.translatable("聚灵丹");
    public JuLingPelletScreen() {
        super(title);
        leftStringLocal = "聚灵丹，吃下去后可以短时间内增加灵力的回复速度。\n" +
                "这里的聚灵指的是将灵石内部的灵力聚集，快速给使用者提供力量。出于人性化考量，加入土豆以调和味道和口感，让它吃起来不像是硬邦邦的压缩灵石。";
        rightImage = new ResourceLocation(ChargeModItemRegistry.MODID,"textures/gui/xian_tian_ba_gua_craft.png");
        showCrafting = true;
        // 乾天, 兑泽（金）, 离火, 震雷（木）, 巽风（木）, 坎水, 艮山（土）, 坤地
        bottomItem = ChargeModItemRegistry.chargeLingShi.get();
        bottomRightItem = ChargeModItemRegistry.chargeLingShi.get();
        rightItem = ChargeModItemRegistry.chargeLingShi.get();
        topRightItem = Items.POTATO;
        bottomLeftItem = Items.POTATO;
        leftItem = ChargeModItemRegistry.chargeLingShi.get();
        topLeftItem = ChargeModItemRegistry.chargeLingShi.get();
        topItem = ChargeModItemRegistry.chargeLingShi.get();
        rightStringLocal = "使用了4个灵石和2个土豆。按照上图合成，这可不是土豆炖灵石，至少功能上要更强大一些。";
    }

}