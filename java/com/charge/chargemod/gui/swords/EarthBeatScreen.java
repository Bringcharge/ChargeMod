package com.charge.chargemod.gui.swords;

import com.charge.chargemod.ChargeModItemRegistry;
import com.charge.chargemod.gui.ChargeBaseScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

public class EarthBeatScreen extends ChargeBaseScreen {

    private static final Component title = Component.translatable("地动");
    public EarthBeatScreen() {
        super(title);
        leftStringLocal = "地动，五行土之精。某日观地龙翻身，落入深谷，听大地深处轰鸣之声有感而创。\n" +
                "大地雄浑，蓬勃有力，其能厚积而薄发。灵力注入后，可以使大地震动，将周围的生物瞬间击飞到空中，在密闭空间内杀伤不大，注意田地。";
        rightImage = new ResourceLocation(ChargeModItemRegistry.MODID,"textures/gui/xian_tian_ba_gua_craft.png");
        showCrafting = true;
        //
        bottomItem = Items.SLIME_BALL; // new ResourceLocation("textures/item/slime_ball.png");
        bottomRightItem = Items.REDSTONE_BLOCK;// new ResourceLocation("textures/block/redstone_block.png");
        rightItem = Items.MAGMA_BLOCK; // new ResourceLocation("textures/block/magma.png");
        topRightItem = Items.GRASS_BLOCK;// new ResourceLocation("textures/block/grass_block_side.png");
        bottomLeftItem = Items.SAND; // new ResourceLocation("textures/block/sand.png");
        leftItem = Items.NETHER_WART_BLOCK; // new ResourceLocation("textures/block/nether_wart_block.png");
        topLeftItem = Items.STONE; // new ResourceLocation("textures/block/stone.png");
        topItem = Items.DIAMOND_PICKAXE; // new ResourceLocation("textures/item/diamond_pickaxe.png");
        rightStringLocal = "使用黏液球，红石块，岩浆块，草方块，沙子，地狱疣块，石头，钻石镐合成，可以将其他道友也抛至高空，好用，也需慎用。";
    }

}