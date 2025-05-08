package com.charge.chargemod.gui.swords;

import com.charge.chargemod.ChargeModItemRegistry;
import com.charge.chargemod.gui.ChargeBaseScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

public class LuanYinGuoScreen extends ChargeBaseScreen {

    private static final Component title = Component.translatable("仙剑 乱因果");
    public LuanYinGuoScreen() {
        super(title);
        leftStringLocal = "乱因果，时间因果众多，虽不能全部斩断，但是扰乱一个区域内的因果还是可以的。\n" +
                "使用灵气可在一个范围内，扰乱所有的因果，对内部的生物产生大量无来源无视护甲、抗性伤害。";
        rightImage = new ResourceLocation(ChargeModItemRegistry.MODID,"textures/gui/xian_tian_ba_gua_craft.png");
        showCrafting = true;
        //乾天, 兑泽（金）, 离火, 震雷（木）, 巽风（木）, 坎水, 艮山（土）, 坤地
        bottomItem = Items.GOLD_BLOCK;
        bottomRightItem = Items.END_STONE;
        rightItem = Items.CACTUS;
        topRightItem = Items.END_ROD;
        bottomLeftItem = Items.PHANTOM_MEMBRANE;
        leftItem = Items.AMETHYST_BLOCK;
        topLeftItem = Items.REDSTONE_BLOCK;
        topItem = Items.LODESTONE;
        rightStringLocal = "使用金块，末地石，仙人掌，末地烛，幻翼膜，水晶块，红石块，磁石合成，因果纠纷乱了，经验也自然不会如同平常一般增加，这也许更符合修仙修己之道。";
    }

}