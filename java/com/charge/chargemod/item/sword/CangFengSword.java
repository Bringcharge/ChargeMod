package com.charge.chargemod.item.sword;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

//藏锋
public class CangFengSword extends ChargeBaseSword{

    public final int KILL_THRESHOLD = 3;

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        boolean flag = super.hurtEnemy(stack, target, attacker);
        if (attacker instanceof Player && !attacker.level().isClientSide) {
            Player player = (Player) attacker;
            // 获取当前的斩杀数量
            int killCount = getKillCount(stack);
            killCount++;
            setKillCount(stack, killCount);

            // 如果达到阈值，增加伤害
            if (killCount >= KILL_THRESHOLD) {
                player.sendSystemMessage(Component.literal("武器已打够了单位"));
                setState(stack, true);
            }
        }
        return flag;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);
        boolean flagState = getState(stack);
        if (flagState) {
            tooltip.add(Component.literal("杀心起，锋万里").withStyle(ChatFormatting.BLUE));
        } else {
            tooltip.add(Component.literal("杀心不起，藏锋万里").withStyle(ChatFormatting.GRAY));
        }
    }

    @Override
    public Component getName(ItemStack stack) {
        boolean flag = getState(stack);
        if (flag) {
            return Component.literal("锋万里").withStyle(ChatFormatting.GOLD);
        } else {
            return Component.literal("藏锋万里").withStyle(ChatFormatting.WHITE);
        }
    }

    // 从 NBT 中获取斩杀数量
    private int getKillCount(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        return tag.getInt("KillCount");
    }

    // 将斩杀数量存储到 NBT 中
    private void setKillCount(ItemStack stack, int killCount) {
        CompoundTag tag = stack.getOrCreateTag();
        tag.putInt("KillCount", killCount);
        stack.setTag(tag);
    }

    // 将武器状态存储到 NBT 中
    private void setState(ItemStack stack, boolean changed) {
        CompoundTag tag = stack.getOrCreateTag();
        tag.putBoolean("CangFeng", changed);
        stack.setTag(tag);
    }
    //获取武器状态
    private boolean getState(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        return tag.getBoolean("CangFeng");
    }
}
