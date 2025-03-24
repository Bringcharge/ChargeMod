package com.charge.chargemod.multiBlock;

import com.charge.chargemod.ChargeModItemRegistry;
import net.minecraft.core.Vec3i;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import java.util.List;
import java.util.Objects;

public class ChargeAlchemyStoveHelper {
    public static ItemStack outputWithItemList(List<ItemStack> list) {   //输入1~8的item，输出产物

        ItemStack s1 = list.get(0);//乾 天
        ItemStack s2 = list.get(1);//巽 风
        ItemStack s3 = list.get(2);//坎 水
        ItemStack s4 = list.get(3);//艮 山
        ItemStack s5 = list.get(4);//坤 地
        ItemStack s6 = list.get(5);//震 雷
        ItemStack s7 = list.get(6);//离 火
        ItemStack s8 = list.get(7);//兑 泽（金）

        if (    //测试物品
                s1.is(Items.EGG) &&
                s5.is(Items.BREAD) &&
                ItemStacksIsEmpty(s2,s3,s4,s6,s7,s8)
        ) {
            return new ItemStack(ChargeModItemRegistry.CHARGE_BIGU_PELLET.get(),1);
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
