package com.charge.chargemod.gui.swords;

import com.charge.chargemod.ChargeModItemRegistry;
import com.charge.chargemod.gui.ChargeBaseScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class EarthBeatScreen extends ChargeBaseScreen {

    private static final Component title = Component.translatable("地动");
    public EarthBeatScreen() {
        super(title);
        leftStringLocal = "地动，五行土之精。某日观地龙翻身，落入深谷，听大地深处轰鸣之声有感而创。\n" +
                "大地雄浑，蓬勃有力，其能厚积而薄发。灵力注入后，可以使大地震动，将周围的生物瞬间击飞到空中，在密闭空间内杀伤不大，注意田地。";
        rightImage = new ResourceLocation(ChargeModItemRegistry.MODID,"textures/block/test_ore_block.png");
        showCrafting = true;
        //
        topLeftItem = new ResourceLocation("textures/item/slime_ball.png");
        leftItem = new ResourceLocation("textures/block/redstone_block.png");
        bottomItem = new ResourceLocation("textures/block/magma.png");
        rightItem = new ResourceLocation("textures/block/grass_block_side.png");
        bottomRightItem = new ResourceLocation("textures/block/sand.png");
        topItem = new ResourceLocation("textures/block/nether_wart_block.png");
        topRightItem = new ResourceLocation("textures/block/stone.png");
        bottomLeftItem = new ResourceLocation("textures/item/diamond_pickaxe.png");
        rightStringLocal = "使用黏液球，红石块，岩浆块，草方块，沙子，地狱疣块，石头，钻石镐合成，可以将其他道友也抛至高空，好用，也需慎用。";
    }

}