package com.charge.chargemod.item.talisman;

import com.charge.chargemod.effect.ModEffects;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

//祈雨符
public class RainTalisman extends ChargeBaseTalisman {
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {

        if (!level.isClientSide() && !player.getCooldowns().isOnCooldown(this)) {

            player.getCooldowns().addCooldown(this, 20 * 20);

            ServerLevel serverLevel = (ServerLevel)level;
            serverLevel.setWeatherParameters(0, 20 * 60 * 5, true,false);    //晴时间，雷雨时间，是否下雨，是否打雷


            ItemStack stack = player.getItemInHand(hand);
            stack.shrink(1);//消耗一个物品
            if (stack.isEmpty()) {
                player.getInventory().removeItem(stack);
            }
            return InteractionResultHolder.success(stack); // success
        }
        return InteractionResultHolder.fail(player.getItemInHand(hand)); // 冷却中返回fail
    }

}
