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

        ItemStack s1 = list.get(3);//乾 天
        ItemStack s2 = list.get(2);//兑 泽（金）
        ItemStack s3 = list.get(0);//离 火
        ItemStack s4 = list.get(6);//震 雷
        ItemStack s5 = list.get(7);//巽 风
        ItemStack s6 = list.get(4);//坎 水
        ItemStack s7 = list.get(5);//艮 山
        ItemStack s8 = list.get(1);//坤 地


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
