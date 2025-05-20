package com.charge.chargemod.gui;

import com.charge.chargemod.ChargeModItemRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

public class AlchemyAnvilScreen extends ChargeBaseScreen {

    private static final Component title = Component.translatable("炼器铁砧");
    public AlchemyAnvilScreen() {
        super(title);
        leftStringLocal = "想要炼制武器，自然需要个炼器炉，老夫根据天地特色，把炉子造成了铁砧的模样，哈哈。按照图在工作台便可合成。\n" +
                "炼器需要消耗灵力，灵力不足自然无法炼制，某些高级的武器也许超出当前灵力上限，等突破了再尝试吧。";
        rightImage = new ResourceLocation(ChargeModItemRegistry.MODID,"textures/gui/empty.png");
        topLeftItem = Items.COPPER_INGOT;
        topItem = Items.BAMBOO;
        topRightItem = Items.COPPER_INGOT;
        leftItem = Items.BAMBOO;
        centerItem = ChargeModItemRegistry.CHARGE_ARRAY_DISK_BLOCK_ITEM.get();
        rightItem = Items.BAMBOO;
        bottomLeftItem = Items.COPPER_INGOT;
        bottomItem = Items.BAMBOO;
        bottomRightItem = Items.COPPER_INGOT;

        rightStringLocal = "经过老夫化简，完成此阵后只需放上物品，拿着令牌就能使用，不拿令牌则可以取出炉中物品。\n" +
                "为什么只有剑？这弓兵拿剑有什么奇怪的。";
    }

}