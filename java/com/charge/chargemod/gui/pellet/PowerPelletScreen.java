package com.charge.chargemod.gui.pellet;

import com.charge.chargemod.ChargeModItemRegistry;
import com.charge.chargemod.gui.ChargeBaseScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

public class PowerPelletScreen extends ChargeBaseScreen {

    private static final Component title = Component.translatable("生力丹");
    public PowerPelletScreen() {
        super(title);
        leftStringLocal = "生力丹，吃下去后可以大幅度提升身体强度。\n" +
                "每次炼丹都会有人问，能不能炼制那种吃了之后就能活力无限，精力旺盛的丹药。生力丹在这方面遥遥领先。如果客人问的是生育方面……还有别的丹药。";
        rightImage = new ResourceLocation(ChargeModItemRegistry.MODID,"textures/gui/xian_tian_ba_gua_craft.png");
        showCrafting = true;
        // 乾天, 兑泽（金）, 离火, 震雷（木）, 巽风（木）, 坎水, 艮山（土）, 坤地
        topLeftItem = null;
        leftItem = Items.GLOWSTONE_DUST;
        bottomItem = Items.REDSTONE;
        rightItem = Items.IRON_INGOT;
        bottomRightItem = Items.BLAZE_POWDER;
        topItem = Items.HONEYCOMB;
        topRightItem = null;
        bottomLeftItem = Items.MAGMA_CREAM;
        rightStringLocal = "使用了萤石粉，红石，铁锭，烈焰粉，蜜脾，岩浆膏。按照上图合成，特别标注，这丹药管的是身强体壮，生育需求请往后翻页。";
    }

}