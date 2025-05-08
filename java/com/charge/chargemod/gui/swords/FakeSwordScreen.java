package com.charge.chargemod.gui.swords;

import com.charge.chargemod.ChargeModItemRegistry;
import com.charge.chargemod.gui.ChargeBaseScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

public class FakeSwordScreen extends ChargeBaseScreen {

    private static final Component title = Component.translatable("假");
    public FakeSwordScreen() {
        super(title);
        leftStringLocal = "假，具有虚假力量的剑。真假互为事物两面，没有虚假也就没有真实。这剑具有化实为虚的功能，也可临时创造出一些虚假的奇迹。\n" +
                "怪物身上的装备武器大多都是幻化而成，消耗灵力，该剑能使怪物手中之物彻底虚假消失。也可使僵尸村民虚假的成为村民。";
        rightImage = new ResourceLocation(ChargeModItemRegistry.MODID,"textures/gui/xian_tian_ba_gua_craft.png");
        showCrafting = true;
        //
        bottomItem = Items.IRON_BLOCK;//new ResourceLocation("textures/block/iron_block.png");
        bottomRightItem = Items.MOSSY_COBBLESTONE;// new ResourceLocation("textures/block/mossy_cobblestone.png");
        rightItem = Items.SOUL_SAND; //new ResourceLocation("textures/block/soul_sand.png");
        topRightItem = Items.LILY_PAD; new ResourceLocation("textures/block/lily_pad.png");
        bottomLeftItem = Items.CLOCK; new ResourceLocation("textures/item/clock_05.png");
        leftItem = Items.KELP; // new ResourceLocation("textures/item/kelp.png");;
        topLeftItem = Items.AMETHYST_BLOCK; // new ResourceLocation("textures/block/amethyst_block.png");
        topItem = Items.POTION;// new ResourceLocation("textures/item/potion.png");
        rightStringLocal = "使用铁块，苔石，灵魂沙，莲叶，时钟，海带，紫水晶块，剧毒药水(中毒II)合成，假的毕竟是假的，只是此剑力量有限，不足以让一些归于虚无。";
    }

}