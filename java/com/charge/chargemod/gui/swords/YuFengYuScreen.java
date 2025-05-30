package com.charge.chargemod.gui.swords;

import com.charge.chargemod.ChargeModItemRegistry;
import com.charge.chargemod.gui.ChargeBaseScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class YuFengYuScreen extends ChargeBaseScreen {

    private static final Component title = Component.translatable("欲风雨");
    public YuFengYuScreen() {
        super(title);
        leftStringLocal = "欲风雨，五行属木。雷响八方，见风知雨，剑蕴含了强大的木属性力量，能唤天雷，驱邪缚魅。\n" +
                "这把剑的雷霆带着天威，不分敌我，范围内都会受到雷电伤害，雷霆虽强但却无法掉落经验，掉落物也既有可能被雷霆摧毁，切记切记。";
        rightImage = new ResourceLocation(ChargeModItemRegistry.MODID,"textures/gui/hou_tian_ba_gua_craft.png");
        showCrafting = true;
        // 乾天, 兑泽（金）, 离火, 震雷（木）, 巽风（木）, 坎水, 艮山（土）, 坤地
        topLeftItem = new ResourceLocation("textures/block/lily_pad.png");
        leftItem = new ResourceLocation("textures/block/lightning_rod.png");
        bottomItem = new ResourceLocation("textures/item/flint_and_steel.png");
        rightItem = new ResourceLocation("textures/item/bamboo.png");
        bottomRightItem = new ResourceLocation("textures/item/cocoa_beans.png");;
        topItem = new ResourceLocation("textures/item/pufferfish.png");
        topRightItem = new ResourceLocation("textures/item/amethyst_shard.png");
        bottomLeftItem = new ResourceLocation("textures/block/nether_wart_block.png");
        rightStringLocal = "使用了莲叶，避雷针，打火石，竹子，可可豆，河豚，紫水晶，地狱疣块。按照上图合成。合成中使用了竹子作为主材，使剑威能浩大，必要时也可以当作雷霆的特殊用途使用。";
    }

}