package com.charge.chargemod.gui.swords;

import com.charge.chargemod.ChargeModItemRegistry;
import com.charge.chargemod.gui.ChargeBaseScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class YuanTianHanScreen extends ChargeBaseScreen {

    private static final Component title = Component.translatable("愿天寒");
    public YuanTianHanScreen() {
        super(title);
        leftStringLocal = "愿天寒，五行火之精。凝聚了大量火焰元素的剑，使用灵力可以向前方释放三昧真火，灼烧生灵。\n" +
                "剑上的火焰太过强大且纯粹，剑势如同滔天火海倾泻而下，也只有在冰天雪地之中挥舞此剑，才勉强能感到一丝凉意。";
        rightImage = new ResourceLocation(ChargeModItemRegistry.MODID,"textures/block/test_ore_block.png");
        showCrafting = true;
        //
        topLeftItem = new ResourceLocation("textures/item/magma_cream.png");
        leftItem = new ResourceLocation("textures/block/gold_block.png");
        bottomItem = new ResourceLocation("textures/item/lava_bucket.png");
        rightItem = new ResourceLocation("textures/item/gunpowder.png");
        bottomRightItem = new ResourceLocation("textures/item/blaze_rod.png");
        topItem = new ResourceLocation("textures/block/magma.png");;
        topRightItem = new ResourceLocation("textures/block/netherrack.png");
        bottomLeftItem = new ResourceLocation("textures/block/soul_sand.png");
        rightStringLocal = "使用岩浆膏，金块，岩浆桶，火药，烈焰棒，岩浆块，地狱岩，灵魂沙合成，三昧真火原本也可以冶炼矿物和木材，后因将房屋炼成精碳，遂改良。";
    }

}