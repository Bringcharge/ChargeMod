package com.charge.chargemod.gui.pellet;

import com.charge.chargemod.ChargeModItemRegistry;
import com.charge.chargemod.gui.ChargeBaseScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

public class BiGuPelletScreen extends ChargeBaseScreen {

    private static final Component title = Component.translatable("辟谷丹");
    public BiGuPelletScreen() {
        super(title);
        leftStringLocal = "辟谷丹，可以提供大量的饥饿值，又持续时间不错的饱腹效果。\n" +
                "其实就是用灵气把各种食材压缩在一起，变成可以快速吃下的丹药罢了。比起辟谷丹，有条件的仙人还是更喜欢吃现做的食物。";
        rightImage = new ResourceLocation(ChargeModItemRegistry.MODID,"textures/gui/xian_tian_ba_gua_craft.png");
        showCrafting = true;
        // 乾天, 兑泽（金）, 离火, 震雷（木）, 巽风（木）, 坎水, 艮山（土）, 坤地
        topLeftItem = Items.GLISTERING_MELON_SLICE;
        leftItem = Items.SUGAR;
        bottomItem = Items.BEETROOT;
        rightItem = null;
        bottomRightItem = Items.GLOW_BERRIES;
        topItem = Items.POTION;
        topRightItem = null;
        bottomLeftItem = null;
        rightStringLocal = "使用了金西瓜，糖，甜菜根，发光浆果，水瓶。按照上图合成，炼制麻烦，费时费力，若非出远门一般不考虑。";
    }

}