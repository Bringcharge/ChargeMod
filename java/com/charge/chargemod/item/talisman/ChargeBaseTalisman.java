package com.charge.chargemod.item.talisman;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

//基本符咒
public class ChargeBaseTalisman extends Item {
    public ChargeBaseTalisman(Properties p_41383_) {
        super(p_41383_);
    }

    public ChargeBaseTalisman() {
        this(new Item.Properties());
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        stack.shrink(1);//消耗一个物品
        if (stack.isEmpty()) {
            player.getInventory().removeItem(stack);
        }
        return InteractionResultHolder.success(stack);
    }
}
