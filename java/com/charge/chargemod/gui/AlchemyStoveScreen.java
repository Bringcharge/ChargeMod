package com.charge.chargemod.gui;

import com.charge.chargemod.ChargeModItemRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

public class AlchemyStoveScreen extends ChargeBaseScreen {

    private static final Component title = Component.translatable("八卦炉");
    public AlchemyStoveScreen() {
        super(title);
        leftStringLocal = "想要炼制丹药、符箓，需要一个炼丹炉，并且按照阵法摆放基石，汇聚天地之力，借用自身灵力后可以炼制。按照下图在工作台可以合成。\n" +
                "也正如上文所说，炼丹需要消耗灵力，灵力不足自然无法炼制，某些高级的丹药也许超出当前灵力上限，等突破了再尝试吧。";
        rightImage = new ResourceLocation(ChargeModItemRegistry.MODID,"textures/gui/empty.png");
        topLeftItem = Items.COAL;
        topItem = Items.IRON_INGOT;
        topRightItem = Items.COAL;
        leftItem = Items.IRON_INGOT;
        centerItem = ChargeModItemRegistry.CHARGE_ARRAY_DISK_BLOCK_ITEM.get();
        rightItem = Items.IRON_INGOT;
        bottomLeftItem = Items.COAL;
        bottomItem = Items.IRON_INGOT;
        bottomRightItem = Items.COAL;

        rightStringLocal = "经过老夫化简，完成此阵后只需放上物品，拿着令牌就能使用，不拿令牌则可以取出炉中物品。\n" +
                "炼丹、炼人，还炼天……咳咳，炼吧，一炼一个不吱声。";
    }

}