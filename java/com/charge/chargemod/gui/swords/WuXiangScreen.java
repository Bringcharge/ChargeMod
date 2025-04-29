package com.charge.chargemod.gui.swords;

import com.charge.chargemod.ChargeModItemRegistry;
import com.charge.chargemod.gui.ChargeBaseScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

public class WuXiangScreen extends ChargeBaseScreen {

    private static final Component title = Component.translatable("仙剑 无相");
    public WuXiangScreen() {
        super(title);
        leftStringLocal = "无相，“无我相，无人相，无众生相，无寿者相。当修行者能辨真假，众生表象不过一具皮囊，于你而言无异。\n" +
                "这剑有斩断事物根本的能力，执剑人眼中已然无了众生之相，却依旧向其挥剑，这何尝不是一种着相呢？挥剑之后，生灵也只剩下森森白骨，最终化为尘土。";
        rightImage = new ResourceLocation(ChargeModItemRegistry.MODID,"textures/block/test_ore_block.png");
        showCrafting = true;
        //乾天, 兑泽（金）, 离火, 震雷（木）, 巽风（木）, 坎水, 艮山（土）, 坤地
        topLeftItem = ChargeModItemRegistry.THE_REAL_SWORD.get();
        leftItem = Items.GOLDEN_SWORD;
        bottomItem = Items.IRON_SWORD;
        rightItem = Items.WOODEN_SWORD;
        bottomRightItem = Items.DIAMOND_SWORD;
        topItem = Items.NETHERITE_SWORD;
        topRightItem = Items.STONE_SWORD;
        bottomLeftItem = ChargeModItemRegistry.THE_FAKE_SWORD.get();
        rightStringLocal = "使用真，金剑，铁剑，木剑，钻石剑，下届合金剑，石剑，假合成。剑之万相，合为无相。";
    }

}