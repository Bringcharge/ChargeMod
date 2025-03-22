package com.charge.chargemod.item.talisman;

import com.charge.chargemod.effect.ModEffects;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class HoldLifeTalisman extends ChargeBaseTalisman {
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        player.addEffect(new MobEffectInstance(ModEffects.HOLD_LIFE_EFFECT.get(), 20 * 10, 1));
        return super.use(level,player,hand);
    }

    public static void easyUse(Level level, Player player, ItemStack stack) {
        player.addEffect(new MobEffectInstance(ModEffects.HOLD_LIFE_EFFECT.get(), 20 * 60, 1));
        stack.shrink(1);//消耗一个物品
        if (stack.isEmpty()) {
            player.getInventory().removeItem(stack);
        }
    }
}
