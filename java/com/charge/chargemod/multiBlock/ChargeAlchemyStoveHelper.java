package com.charge.chargemod.multiBlock;

import com.charge.chargemod.ChargeModItemRegistry;
import net.minecraft.core.Vec3i;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;

import java.util.List;
import java.util.Objects;

public class ChargeAlchemyStoveHelper {
    public static ItemStack outputWithItemList(List<ItemStack> list) {   //输入1~8的item，输出产物

        ItemStack s1 = list.get(3);//乾 天
        ItemStack s2 = list.get(2);//兑 泽（金）
        ItemStack s3 = list.get(0);//离 火
        ItemStack s4 = list.get(6);//震 雷
        ItemStack s5 = list.get(7);//巽 风
        ItemStack s6 = list.get(4);//坎 水
        ItemStack s7 = list.get(5);//艮 山
        ItemStack s8 = list.get(1);//坤 地

        if (    //退魔符
                s1.is(ChargeModItemRegistry.TALISMAN_PAPER_ITEM.get()) &&
                        s2.is(Items.IRON_NUGGET) &&
                        s3.is(Items.GUNPOWDER) &&
                        s8.is(Items.NETHERRACK) &&
                        ItemStacksIsEmpty(s4,s5,s6,s7)
        ) {
            return new ItemStack(ChargeModItemRegistry.PUSH_BACK_TALISMAN.get(),3);
        }

        if (    //御劫符
                s1.is(ChargeModItemRegistry.TALISMAN_PAPER_ITEM.get()) &&
                        s2.is(Items.IRON_INGOT) &&
                        s4.is(ItemTags.PLANKS) &&
                        s7.is(Items.COBBLESTONE) &&
                        ItemStacksIsEmpty(s3,s5,s6,s8)
        ) {
            return new ItemStack(ChargeModItemRegistry.SHIELD_TALISMAN.get(),3);
        }

        if (    //换颅符
                s1.is(ChargeModItemRegistry.TALISMAN_PAPER_ITEM.get()) &&
                        s2.is(Items.REDSTONE) &&
                        s4.is(Items.PAPER) &&
                        s5.is(Items.PAPER) &&
                        s6.is(Items.STONE_AXE) &&
                        s8.is(Items.CLAY) &&
                        ItemStacksIsEmpty(s3, s7)
        ) {
            return new ItemStack(ChargeModItemRegistry.SKULL_STEAL_TALISMAN.get(),1);
        }

        if (    //延死符
                s1.is(ChargeModItemRegistry.TALISMAN_PAPER_ITEM.get()) &&
                        s2.is(Items.LAPIS_LAZULI) &&
                        s3.is(Items.SOUL_SAND) &&
                        s4.is(Items.WARPED_STEM) &&
                        s6.is(Items.GHAST_TEAR) &&
                        s8.is(Items.ROTTEN_FLESH) &&
                        ItemStacksIsEmpty(s5, s7)
        ) {
            return new ItemStack(ChargeModItemRegistry.HOLD_LIFE_TALISMAN.get(),1);
        }

        if (    //回生符
                s1.is(ChargeModItemRegistry.TALISMAN_PAPER_ITEM.get()) &&
                        s2.is(Items.GHAST_TEAR) &&
                        s3.is(Items.SOUL_SOIL) &&
                        s4.is(Items.RED_MUSHROOM) &&
                        s7.is(Items.MYCELIUM) &&
                        s8.is(Items.BONE_MEAL) &&
                        ItemStacksIsEmpty(s5, s6)
        ) {
            return new ItemStack(ChargeModItemRegistry.RELIVE_TALISMAN.get(),1);
        }

        if (    //仙游符
                s1.is(ChargeModItemRegistry.TALISMAN_PAPER_ITEM.get()) &&
                        s3.is(Items.PHANTOM_MEMBRANE) &&
                        s5.is(Items.BAMBOO) &&
                        s6.is(Items.FEATHER) &&
                        s8.is(Items.COCOA_BEANS) &&
                        ItemStacksIsEmpty(s2, s4, s7)
        ) {
            return new ItemStack(ChargeModItemRegistry.FLY_TALISMAN.get(),1);
        }

        if (    //祈雨符
                s1.is(ChargeModItemRegistry.TALISMAN_PAPER_ITEM.get()) &&
                        s2.is(Items.BEEF) &&
                        s3.is(Items.MUTTON) &&
                        s4.is(Items.PORKCHOP) &&
                        ItemStacksIsEmpty(s5, s6, s7, s8)
        ) {
            return new ItemStack(ChargeModItemRegistry.RAIN_TALISMAN.get(),1);
        }

        if (    //请神符
                s1.is(ChargeModItemRegistry.TALISMAN_PAPER_ITEM.get()) &&
                        s2.is(Items.ENDER_PEARL) &&
                        s3.is(Items.COMPASS) &&
                        s4.is(Items.ENDER_PEARL) &&
                        s5.is(Items.ENDER_EYE) &&
                        s8.is(Items.CLOCK) &&
                        ItemStacksIsEmpty(s6, s7)
        ) {
            return new ItemStack(ChargeModItemRegistry.INVITE_GOD_TALISMAN.get(),1);
        }

        if (    //求不得符
                s1.is(ChargeModItemRegistry.TALISMAN_PAPER_ITEM.get()) &&
                        s2.is(Items.MAGMA_CREAM) &&
                        s3.is(Items.BLAZE_ROD) &&
                        s4.is(Items.SOUL_CAMPFIRE) &&
                        s7.is(Items.SLIME_BALL) &&
                        ItemStacksIsEmpty(s5, s6, s8)
        ) {
            return new ItemStack(ChargeModItemRegistry.QIU_BU_DE_TALISMAN.get(),1);
        }

        if (    //解不出符
                s1.is(ChargeModItemRegistry.TALISMAN_PAPER_ITEM.get()) &&
                        s2.is(Items.SOUL_SAND) &&
                        s3.is(Items.SLIME_BALL) &&
                        s4.is(Items.ROTTEN_FLESH) &&
                        s5.is(Items.NETHER_WART) &&
                        s6.is(Items.ENDER_PEARL) &&
                        ItemStacksIsEmpty(s7, s8)
        ) {
            return new ItemStack(ChargeModItemRegistry.JIE_BU_CHU_TALISMAN.get(),1);
        }

        if (    //想不通符
                s1.is(ChargeModItemRegistry.TALISMAN_PAPER_ITEM.get()) &&
                        s3.is(Items.ENDER_EYE) &&
                        s4.is(ItemTags.BANNERS) &&
                        s6.is(Items.MAGMA_CREAM) &&
                        s7.is(Items.KELP) &&
                        ItemStacksIsEmpty(s2, s8)
        ) {
            return new ItemStack(ChargeModItemRegistry.XIANG_BU_TONG_TALISMAN.get(),1);
        }
        if (    //算不对符
                s1.is(ChargeModItemRegistry.TALISMAN_PAPER_ITEM.get()) &&
                        s2.is(Items.GOLDEN_APPLE) &&
                        s4.is(Items.GLISTERING_MELON_SLICE) &&
                        s5.is(Items.GOLDEN_CARROT) &&
                        s6.is(Items.PUMPKIN) &&
                        s8.is(Items.HAY_BLOCK) &&
                        ItemStacksIsEmpty(s3, s7)
        ) {
            return new ItemStack(ChargeModItemRegistry.SUAN_BU_DUI_TALISMAN.get(),1);
        }


        //丹药系列
        if (    //辟谷丹
                s1.is(Items.GLISTERING_MELON_SLICE) &&
                        s2.is(Items.SUGAR) &&
                        s3.is(Items.BEETROOT) &&
                        s5.is(Items.GLOW_BERRIES) &&
                        s6.is(Items.POTION) && (PotionUtils.getPotion(s6) == Potions.WATER) &&
                        ItemStacksIsEmpty(s4, s7, s8)
        ) {
            return new ItemStack(ChargeModItemRegistry.CHARGE_BIGU_PELLET.get(),1);
        }

        if (    //鸿运齐天丹
                s1.is(Items.LAPIS_LAZULI) &&
                        s3.is(Items.GUNPOWDER) &&
                        s5.is(Items.POPPY) &&
                        s6.is(Items.PUFFERFISH) &&
                        ItemStacksIsEmpty(s2, s4, s7, s8)
        ) {
            return new ItemStack(ChargeModItemRegistry.CHARGE_HONG_YUN_PELLET.get(),1);
        }

        if (    //大还丹
                s2.is(Items.RED_MUSHROOM) &&
                        s4.is(Items.CARROT) &&
                        s5.is(Items.SPIDER_EYE) &&
                        s6.is(Items.NETHER_WART) &&
                        ItemStacksIsEmpty(s1, s3, s7, s8)
        ) {
            return new ItemStack(ChargeModItemRegistry.CHARGE_HEALTH_PELLET.get(),1);
        }

        if (    //生力丹
                s2.is(Items.GLOWSTONE_DUST) &&
                        s3.is(Items.REDSTONE) &&
                        s4.is(Items.IRON_INGOT) &&
                        s5.is(Items.BLAZE_POWDER) &&
                        s6.is(Items.HONEYCOMB) &&
                        s8.is(Items.MAGMA_CREAM) &&
                        ItemStacksIsEmpty(s1, s7)
        ) {
            return new ItemStack(ChargeModItemRegistry.CHARGE_POWER_PELLET.get(),1);
        }

        if (    //破万法丹
                s2.is(Items.GOLDEN_CARROT) &&
                        s3.is(Items.WOODEN_SWORD) &&
                        s6.is(ChargeModItemRegistry.TALISMAN_PAPER_ITEM.get()) &&
                        s7.is(Items.GLISTERING_MELON_SLICE) &&
                        ItemStacksIsEmpty(s1, s4, s5, s8)
        ) {
            return new ItemStack(ChargeModItemRegistry.CHARGE_PO_WAN_FA_PELLET.get(),1);
        }

        if (    //聚灵丹
                s1.is(ChargeModItemRegistry.chargeLingShi.get()) &&
                        s2.is(ChargeModItemRegistry.chargeLingShi.get()) &&
                        s3.is(ChargeModItemRegistry.chargeLingShi.get()) &&
                        s4.is(Items.POTATO) &&
                        s5.is(Items.POTATO) &&
                        s6.is(ChargeModItemRegistry.chargeLingShi.get()) &&
                        s7.is(ChargeModItemRegistry.chargeLingShi.get()) &&
                        s8.is(ChargeModItemRegistry.chargeLingShi.get())
        ) {
            return new ItemStack(ChargeModItemRegistry.CHARGE_JU_LING_PELLET.get(),1);
        }

        if (    //万物生丹
                s2.is(Items.BONE) &&
                        s3.is(Items.ROTTEN_FLESH) &&
                        s4.is(Items.FERMENTED_SPIDER_EYE) &&
                        s5.is(Items.SLIME_BALL) &&
                        s6.is(Items.LEATHER) &&
                        s7.is(Items.GUNPOWDER) &&
                        ItemStacksIsEmpty(s1, s8)
        ) {
            return new ItemStack(ChargeModItemRegistry.CHARGE_WAN_WU_SHENG_PELLET.get(),1);
        }

        if (    //三尸丹
                s1.is(ChargeModItemRegistry.CHARGE_WAN_WU_SHENG_PELLET.get()) &&
                        s2.is(Items.POISONOUS_POTATO) &&
                        s3.is(ChargeModItemRegistry.CHARGE_BIGU_PELLET.get()) &&
                        s5.is(Items.PUFFERFISH) &&
                        s6.is(Items.HONEY_BOTTLE) &&
                        ItemStacksIsEmpty(s4, s7, s8)
        ) {
            return new ItemStack(ChargeModItemRegistry.CHARGE_SAN_SHI_PELLET.get(),1);
        }

        if (    //登仙丹
                s1.is(ChargeModItemRegistry.CHARGE_PO_WAN_FA_PELLET.get()) &&
                        s2.is(Items.LIGHTNING_ROD) &&
                        s3.is(Items.BELL) &&
                        s4.is(Items.CHAIN) &&
                        s5.is(Items.ANCIENT_DEBRIS) &&
                        s6.is(Items.TRIDENT) &&
                        s7.is(Items.DIAMOND_ORE) &&
                        s8.is(Items.NETHER_STAR)
        ) {
            return new ItemStack(ChargeModItemRegistry.CHARGE_DENG_XIAN_PELLET.get(),1);
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
