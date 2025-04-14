package com.charge.chargemod.gui.swords;

import com.charge.chargemod.ChargeModItemRegistry;
import com.charge.chargemod.gui.ChargeBaseScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class FakeSwordScreen extends ChargeBaseScreen {

    private static final Component title = Component.translatable("假");
    public FakeSwordScreen() {
        super(title);
        leftStringLocal = "假，具有虚假力量的剑。真假互为事物两面，没有虚假也就没有真实。这剑具有化实为虚的功能，也可临时创造出一些虚假的奇迹。\n" +
                "怪物身上的装备武器大多都是幻化而成，消耗灵力，该剑能使怪物手中之物彻底虚假消失。也可使僵尸村民虚假的成为村民。";
        rightImage = new ResourceLocation(ChargeModItemRegistry.MODID,"textures/block/test_ore_block.png");
        showCrafting = true;
        //
        topLeftItem = new ResourceLocation("textures/block/iron_block.png");
        leftItem = new ResourceLocation("textures/block/mossy_cobblestone.png");
        bottomItem = new ResourceLocation("textures/block/soul_sand.png");
        rightItem = new ResourceLocation("textures/block/lily_pad.png");
        bottomRightItem = new ResourceLocation("textures/item/clock_05.png");
        topItem = new ResourceLocation("textures/item/kelp.png");;
        topRightItem = new ResourceLocation("textures/block/amethyst_block.png");
        bottomLeftItem = new ResourceLocation("textures/item/potion.png");
        rightStringLocal = "使用铁块，苔石，灵魂沙，莲叶，时钟，海带，紫水晶块，剧毒药水合成，假的毕竟是假的，只是此剑力量有限，不足以让一些归于虚无。";
    }

}