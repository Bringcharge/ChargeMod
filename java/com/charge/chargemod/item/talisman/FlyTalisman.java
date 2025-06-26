package com.charge.chargemod.item.talisman;

import com.charge.chargemod.ChargeModItemRegistry;
import com.charge.chargemod.effect.ModEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

import static net.minecraft.world.InteractionHand.MAIN_HAND;
import static net.minecraft.world.InteractionHand.OFF_HAND;

//天游符
public class FlyTalisman extends ChargeBaseTalisman {
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {

        if (!level.isClientSide() && !player.getCooldowns().isOnCooldown(this)) {

            player.getCooldowns().addCooldown(this, 20 * 20);
            player.addEffect(new MobEffectInstance(ModEffects.FLY_EFFECT.get(), 20 * 10, 1));
            ItemStack stack = player.getItemInHand(hand);
            level.playSound(
                    null,                     // 无特定来源实体（全局声音）
                    BlockPos.containing(player.position()), // 声音位置
                    SoundEvents.ENDER_EYE_DEATH, // 声音事件（原版或自定义）
                    SoundSource.PLAYERS,       // 声音类别（BLOCKS, PLAYERS, AMBIENT 等）
                    1.0F, 1.0F                // 音量、音高
            );
            stack.shrink(1);//消耗一个物品
            if (stack.isEmpty()) {
                player.getInventory().removeItem(stack);
            }
            level.playSound(
                    null,                     // 无特定来源实体（全局声音）
                    BlockPos.containing(player.position()), // 声音位置
                    SoundEvents.BLAZE_BURN, // 声音事件（原版或自定义）
                    SoundSource.PLAYERS,       // 声音类别（BLOCKS, PLAYERS, AMBIENT 等）
                    1.0F, 1.0F                // 音量、音高
            );
            return InteractionResultHolder.success(stack); // success
        }
        return InteractionResultHolder.fail(player.getItemInHand(hand)); // 冷却中返回fail
    }

}
