package com.charge.chargemod.item.sword;

import com.charge.chargemod.entity.ChargeCangFengDaggerEntity;
import com.charge.chargemod.lingqi.PlayerLingQiHelper;
import com.charge.chargemod.network.ChargePacketSender;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

//藏锋
public class CangFengSword extends ChargeBaseSword{

    public final int KILL_THRESHOLD = 99;

    private void createArrowEntity(Vec3 target3d, Level worldI,Entity owner) {
        Random random = new Random();
        Vec3 arrowCreatePosition = target3d.add((random.nextGaussian()-0.5) * 3.f ,6, (random.nextGaussian() -0.5) * 3.f);
        Vec3 vectorToTarget = target3d.add(arrowCreatePosition.reverse());


        ChargeCangFengDaggerEntity arrow = new ChargeCangFengDaggerEntity(worldI ,arrowCreatePosition.x(),arrowCreatePosition.y(),arrowCreatePosition.z() );
        arrow.setOwner(owner);
        //设置初速度和散布
        arrow.setDeltaMovement(vectorToTarget.normalize().scale(1));
        arrow.setCritArrow(true);    //箭后面是否带有大量暴击粒子
        worldI.addFreshEntity(arrow);
        worldI.playSound(
                null,                     // 无特定来源实体（全局声音）
                BlockPos.containing(target3d), // 声音位置
                SoundEvents.CHAIN_HIT, // 声音事件（原版或自定义）
                SoundSource.PLAYERS,       // 声音类别（BLOCKS, PLAYERS, AMBIENT 等）
                1.0F, 1.0F                // 音量、音高
        );
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) { //攻击次数统计
        boolean flag = super.hurtEnemy(stack, target, attacker);
        if (attacker instanceof Player && !attacker.level().isClientSide && !getState(stack)) {
            Player player = (Player) attacker;
            // 获取当前的攻击次数
            int AttackCount = getKillCount(stack);
            AttackCount++;
            setKillCount(stack, AttackCount);

            // 如果达到阈值
            if (AttackCount == KILL_THRESHOLD) {
                player.sendSystemMessage(Component.translatable("describe.charge.cang_feng_count_enough"));
                setState(stack, true);
            }
        }
        return flag;
    }

    @Override
    public boolean skillWithEntity(LivingEntity entity, Player user, InteractionHand hand) { //右键击中了怪物的函数，最高优先级

        if (!getState(user.getItemInHand(hand))) {   //如果没成为锋万里
            if (!user.level().isClientSide) {
                Player player = user;
                boolean canUse = PlayerLingQiHelper.consumeLingQi(player, 10);
                if (!canUse) {
                    player.sendSystemMessage(Component.translatable("describe.charge.need_ling_li"));
                    return false;
                } else {
                    ChargePacketSender.sendLingqiMessageToClient((ServerPlayer) player, PlayerLingQiHelper.getLingQi(player));
                }
                createArrowEntity(entity.getEyePosition(1.0f), entity.level(), user);

            }
            return true;
        }
        return false;
    }



    @Override
    public boolean skillWithNone(Player user, InteractionHand hand) { //右键什么都没有击中，最低的优先级
        if (!getState(user.getItemInHand(hand))) {   //如果没成为锋万里
            return false;
        }
        //构建扫视范围
        float halfWidth = 6;
        float halfHeight = 2;
        Vec3 eyePosition = user.getEyePosition(1.0F);
        Vec3 topNorthEast = user.getEyePosition().add(halfWidth, halfHeight, halfWidth);
        Vec3 bottomNorthWest = user.getEyePosition().add(-halfWidth, -halfHeight, -halfWidth);

        AABB aabb = new AABB(topNorthEast, bottomNorthWest); //扫视的范围

        List<Entity> list = user.level().getEntities(user, aabb, entity -> entity instanceof LivingEntity && entity != user);   //找寻范围内的单位，排除自己
        if (list.size() > 0) {  //有单位可以处理，后续增加扣蓝代码和
            if (!user.level().isClientSide) {   //判断服务端
                //灵气判断
                boolean canUse = PlayerLingQiHelper.consumeLingQi(user, 20);
                if (!canUse) {
                    user.sendSystemMessage(Component.translatable("describe.charge.need_ling_li"));
                    return false;
                } else {
                    ChargePacketSender.sendLingqiMessageToClient((ServerPlayer) user, PlayerLingQiHelper.getLingQi(user));
                }

                for (Entity entity : list) {
                    for (int i = 0; i < 3; i++) {
                        createArrowEntity(entity.getEyePosition(1.f), user.level(), user);
                    }
                }
            }
            return true;
        }
        return false;
    }




    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);
        boolean flagState = getState(stack);
        if (flagState) {
            tooltip.add(Component.translatable("describe.charge.feng_wan_li_describe").withStyle(ChatFormatting.BLUE));
        } else {
            tooltip.add(Component.translatable("describe.charge.cang_feng_describe").withStyle(ChatFormatting.GRAY));
        }
    }

    @Override
    public Component getName(ItemStack stack) {
        boolean flag = getState(stack);
        if (flag) {
            return Component.translatable("item.charge.cang_feng_sword").withStyle(ChatFormatting.GOLD);
        } else {
            return Component.translatable("item.charge.no_cang_feng_sword").withStyle(ChatFormatting.WHITE);
        }
    }

    // 从 NBT 中获取斩杀数量
    private int getKillCount(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        return tag.getInt("AttackCount");
    }

    // 将斩杀数量存储到 NBT 中
    private void setKillCount(ItemStack stack, int killCount) {
        CompoundTag tag = stack.getOrCreateTag();
        tag.putInt("AttackCount", killCount);
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
