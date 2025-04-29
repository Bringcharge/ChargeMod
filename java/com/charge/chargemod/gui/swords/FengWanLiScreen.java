package com.charge.chargemod.gui.swords;

import com.charge.chargemod.ChargeModItemRegistry;
import com.charge.chargemod.gui.ChargeBaseScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

public class FengWanLiScreen extends ChargeBaseScreen {

    private static final Component title = Component.translatable("藏锋万里");
    public FengWanLiScreen() {
        super(title);
        leftStringLocal = "藏锋万里，五行属金。杀心不起，藏锋万里。这把剑汇聚了金的杀伐之力，使用者的竭力克制才能勉强压制住。\n" +
                "这把剑渴望着杀戮，如同你渴望力量一般，当它亲自攻击了足够多的次数之后，便不再束手束脚，剑的名字和技能也会发生变化。";
        rightImage = new ResourceLocation(ChargeModItemRegistry.MODID,"textures/gui/hou_tian_ba_gua_craft.png");
        showCrafting = true;
        // 乾天, 兑泽（金）, 离火, 震雷（木）, 巽风（木）, 坎水, 艮山（土）, 坤地
        topLeftItem = Items.IRON_SWORD;// new ResourceLocation("textures/item/iron_sword.png");
        leftItem = Items.GOLDEN_SWORD; //new ResourceLocation("textures/item/golden_sword.png");
        bottomItem = Items.FLINT; //new ResourceLocation("textures/item/flint.png");
        rightItem = Items.NETHER_WART;// new ResourceLocation("textures/item/nether_wart.png");
        bottomRightItem = Items.LILAC; //new ResourceLocation("textures/block/lilac_top.png");;
        topItem = Items.AMETHYST_SHARD;// new ResourceLocation("textures/item/amethyst_shard.png");
        topRightItem = Items.QUARTZ; // new ResourceLocation("textures/item/quartz.png");
        bottomLeftItem = Items.SUNFLOWER;//new ResourceLocation("textures/block/sunflower_front.png");
        rightStringLocal = "使用了铁剑，金剑，燧石，地狱疣，丁香花，紫水晶，石英，向日葵。按照上图合成。使用这把剑毅是在磨砺用剑人的心，让用剑人与剑同意锋利。";
    }

}