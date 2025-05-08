package com.charge.chargemod.multiBlock;

import com.charge.chargemod.ChargeModItemRegistry;
import net.minecraft.core.Vec3i;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;

import java.util.List;

public class ChargeAlchemyAnvilHelper {
    public static ItemStack outputWithItemList(List<ItemStack> list) {   //输入1~8的item，输出产物

        ItemStack s1 = list.get(0);//乾 天
        ItemStack s2 = list.get(7);//兑 泽（金）
        ItemStack s3 = list.get(6);//离 火
        ItemStack s4 = list.get(5);//震 雷
        ItemStack s5 = list.get(1);//巽 风
        ItemStack s6 = list.get(2);//坎 水
        ItemStack s7 = list.get(3);//艮 山
        ItemStack s8 = list.get(4);//坤 地



        if (    //孔方
                s1.is(Items.COPPER_INGOT) &&
                        s2.is(Items.COPPER_INGOT) &&
                        s3.is(Items.RED_DYE) &&
                        s5.is(Items.STRING) &&
            ItemStacksIsEmpty(s4, s6, s7, s8)
        ) {
            return new ItemStack(ChargeModItemRegistry.KONG_FANG_SWORD.get(),1);
        }
        if (    //扶摇
                s1.is(ItemTags.LEAVES) &&
                        s4.is(Items.SUGAR_CANE) &&
                        s5.is(Items.PAPER) &&
                        s7.is(Items.STICK) &&
                        ItemStacksIsEmpty(s2, s3, s6, s8)
        ) {
            return new ItemStack(ChargeModItemRegistry.FU_YAO_SWORD.get(),1);
        }

        if (    //断流
                s1.is(Items.BUCKET) &&
                        s3.is(Items.CHARCOAL) &&
                        s6.is(Items.SALMON) &&
                        s8.is(Items.STICK) &&
                        ItemStacksIsEmpty(s2, s4, s5, s7)
        ) {
            return new ItemStack(ChargeModItemRegistry.EMBER_SWORD.get(),1);
        }

        if (    //余烬
                s3.is(Items.MAGMA_BLOCK ) &&
                        s4.is(Items.COAL) &&
                        s5.is(ItemTags.LOGS) && //原木
                        s7.is(Items.FLINT) &&
                        ItemStacksIsEmpty(s1, s2, s6, s8)
        ) {
            return new ItemStack(ChargeModItemRegistry.WATER_SPLIT_SWORD.get(),1);
        }

        if (    //冢中骨
                s1.is(Items.BONE ) &&
                        s2.is(Items.COPPER_INGOT) &&
                        s7.is(Items.STONE) && //原木
                        s8.is(Items.IRON_ORE) &&
                        ItemStacksIsEmpty(s3, s4, s5, s6)
        ) {
            return new ItemStack(ChargeModItemRegistry.ZHONG_ZHONG_GU_SWORD.get(),1);
        }

        if (    //寻因
                s2.is(Items.GOLD_INGOT ) &&
                        s3.is(Items.CHARCOAL) &&
                        s4.is(Items.COPPER_INGOT) && //原木
                        s6.is(Items.WATER_BUCKET) &&
                        s7.is(Items.DIRT) &&
                        s8.is(Items.COBBLESTONE) &&
                        ItemStacksIsEmpty(s1, s5)
        ) {
            return new ItemStack(ChargeModItemRegistry.WATER_SPLIT_SWORD.get(),1);
        }

        if (    //锋万里
                s1.is(Items.IRON_SWORD) &&
                        s2.is(Items.GOLDEN_SWORD) &&
                        s3.is(Items.FLINT) && //原木
                        s4.is(Items.NETHER_WART) &&
                        s5.is(Items.LILAC) &&
                        s6.is(Items.AMETHYST_SHARD) &&
                        s7.is(Items.QUARTZ) &&
                        s8.is(Items.SUNFLOWER)
        ) {
            return new ItemStack(ChargeModItemRegistry.CANG_FENG.get(),1);
        }

        if (    //欲风雨
                s1.is(Items.LILY_PAD) &&
                        s2.is(Items.LIGHTNING_ROD) &&
                        s3.is(Items.FLINT_AND_STEEL) && //原木
                        s4.is(Items.BAMBOO) &&
                        s5.is(Items.COCOA_BEANS) &&
                        s6.is(Items.PUFFERFISH) &&
                        s7.is(Items.AMETHYST_SHARD) &&
                        s8.is(Items.NETHER_WART_BLOCK)
        ) {
            return new ItemStack(ChargeModItemRegistry.YU_FENG_YU.get(),1);
        }
        if (    //百草零
                s1.is(Items.ICE) &&
                        s2.is(Items.IRON_SWORD) &&
                        s3.is(Items.SOUL_SAND) && //原木
                        s4.is(Items.SNOW_BLOCK) &&
                        s5.is(Items.POWDER_SNOW_BUCKET) &&
                        s6.is(Items.POTION) && (PotionUtils.getPotion(s6) == Potions.WEAKNESS) &&
                        s7.is(Items.GUNPOWDER) &&
                        s8.is(Items.SWEET_BERRIES)
        ) {
            return new ItemStack(ChargeModItemRegistry.BAI_CAO_LING.get(),1);
        }

        if (    //愿天寒
                s1.is(Items.MAGMA_CREAM) &&
                        s2.is(Items.GOLD_BLOCK) &&
                        s3.is(Items.LAVA_BUCKET) && //原木
                        s4.is(Items.GUNPOWDER) &&
                        s5.is(Items.BLAZE_ROD) &&
                        s6.is(Items.MAGMA_BLOCK) &&
                        s7.is(Items.NETHERRACK) &&
                        s8.is(Items.SOUL_SAND)
        ) {
            return new ItemStack(ChargeModItemRegistry.MAY_COLD.get(),1);
        }

        if (    //地动
                s1.is(Items.SLIME_BALL) &&
                        s2.is(Items.REDSTONE_BLOCK) &&
                        s3.is(Items.MAGMA_CREAM) &&
                        s4.is(Items.GRASS_BLOCK) &&
                        s5.is(Items.SAND) &&
                        s6.is(Items.NETHER_WART_BLOCK) &&
                        s7.is(Items.STONE) &&
                        s8.is(Items.DIAMOND_PICKAXE)
        ) {
            return new ItemStack(ChargeModItemRegistry.EARTH_BEAT.get(),1);
        }

        if (    //真
                s1.is(Items.GOLD_BLOCK) &&
                        s2.is(Items.GILDED_BLACKSTONE) &&
                        s3.is(Items.NETHERRACK) &&
                        s4.is(Items.SUNFLOWER) &&
                        s5.is(Items.COMPASS) &&
                        s6.is(Items.PRISMARINE_SHARD) &&
                        s7.is(Items.DIAMOND_BLOCK) &&
                        s8.is(Items.POTION) && (PotionUtils.getPotion(s8) == Potions.NIGHT_VISION)
        ) {
            return new ItemStack(ChargeModItemRegistry.THE_REAL_SWORD.get(),1);
        }

        if (    //假
                s1.is(Items.IRON_BLOCK) &&
                        s2.is(Items.MOSSY_COBBLESTONE) &&
                        s3.is(Items.SOUL_SAND) &&
                        s4.is(Items.LILY_PAD) &&
                        s5.is(Items.CLOCK) &&
                        s6.is(Items.KELP) &&
                        s7.is(Items.AMETHYST_BLOCK) &&
                        s8.is(Items.POTION) && (PotionUtils.getPotion(s8) == Potions.STRONG_POISON)
        ) {
            return new ItemStack(ChargeModItemRegistry.THE_FAKE_SWORD.get(),1);
        }

        if (    //塑果
                s1.is(Items.CLOCK) &&
                        s2.is(ItemTags.SMELTS_TO_GLASS) &&
                        s3.is(Items.SAND) &&
                        s4.is(Items.APPLE) &&
                        s5.is(Items.CACTUS) &&
                        s6.is(Items.CLAY) &&
                        s7.is(Items.GOLD_ORE) &&
                        s8.is(Items.NETHER_BRICK_SLAB)
        ) {
            return new ItemStack(ChargeModItemRegistry.SU_GUO_SWORD.get(),1);
        }

        if (    //求逍遥
                s1.is(Items.PLAYER_HEAD) &&
                        s2.is(Items.GRASS) &&
                        s3.is(Items.CHORUS_FRUIT) &&
                        s4.is(Items.ENDER_PEARL) &&
                        s5.is(Items.BLAZE_POWDER) &&
                        s6.is(Items.PUFFERFISH) &&
                        s7.is(Items.OBSIDIAN) &&
                        s8.is(Items.WITHER_SKELETON_SKULL)
        ) {
            return new ItemStack(ChargeModItemRegistry.QIU_XIAO_YAO_SWORD.get(),1);
        }

        if (    //无相
                s1.is(ChargeModItemRegistry.THE_REAL_SWORD.get()) &&
                        s2.is(Items.GOLDEN_SWORD) &&
                        s3.is(Items.IRON_SWORD) &&
                        s4.is(Items.WOODEN_SWORD) &&
                        s5.is(Items.DIAMOND_SWORD) &&
                        s6.is(Items.NETHERITE_SWORD) &&
                        s7.is(Items.STONE_SWORD) &&
                        s8.is(ChargeModItemRegistry.THE_FAKE_SWORD.get())
        ) {
            return new ItemStack(ChargeModItemRegistry.WU_XIANG_SWORD.get(),1);
        }

        if (    //乱因果
                s1.is(Items.GOLD_BLOCK) &&
                        s2.is(Items.END_STONE) &&
                        s3.is(Items.CACTUS) &&
                        s4.is(Items.END_ROD) &&
                        s5.is(Items.PHANTOM_MEMBRANE) &&
                        s6.is(Items.AMETHYST_BLOCK) &&
                        s7.is(Items.REDSTONE_BLOCK) &&
                        s8.is(Items.LODESTONE)
        ) {
            return new ItemStack(ChargeModItemRegistry.LUAN_YIN_GUO_SWORD.get(),1);
        }

        return ItemStack.EMPTY;
    }


    static boolean ItemStacksIsEmpty(ItemStack... input) {
        for (ItemStack o : input) { // implicit null check of 'input' array
            if (!o.isEmpty()) {
                return false;
            }
        }
        return true;
    }
}
