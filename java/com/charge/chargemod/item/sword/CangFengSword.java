package com.charge.chargemod.item.sword;

import com.charge.chargemod.entity.ChargeCangFengDaggerEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
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

    public final int KILL_THRESHOLD = 3;

    private void createArrowItem(Vec3 target3d, Level worldI) {
        Random random = new Random();
        Vec3 arrowCreatePosition = target3d.add((random.nextGaussian()-0.5) * 3.f ,6, (random.nextGaussian() -0.5) * 3.f);
        Vec3 vectorToTarget = target3d.add(arrowCreatePosition.reverse());


        ChargeCangFengDaggerEntity arrow = new ChargeCangFengDaggerEntity(worldI ,arrowCreatePosition.x(),arrowCreatePosition.y(),arrowCreatePosition.z() );

        //设置初速度和散布
        arrow.setDeltaMovement(vectorToTarget.normalize().scale(1));
//        abstractarrowentity.shoot(0,-1,0,2.0f,3.f);
//        arrow.setIsCritical(false);    //箭后面是否带有大量暴击粒子
        worldI.addFreshEntity(arrow);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) { //攻击次数统计
        boolean flag = super.hurtEnemy(stack, target, attacker);
        if (attacker instanceof Player && !attacker.level().isClientSide && !getState(stack)) {
            Player player = (Player) attacker;
            // 获取当前的斩杀数量
            int AttackCount = getKillCount(stack);
            AttackCount++;
            setKillCount(stack, AttackCount);

            // 如果达到阈值
            if (AttackCount == KILL_THRESHOLD) {
                player.sendSystemMessage(Component.literal("武器已经磨砺出锋芒"));
                setState(stack, true);
            }
        }
        return flag;
    }

    @Override
    public boolean skillWithEntity(LivingEntity entity, Player user, InteractionHand hand) { //右键击中了怪物的函数，最高优先级

        if (!getState(user.getItemInHand(hand))) {   //如果没成为锋万里
            if (!user.level().isClientSide) {
                createArrowItem(entity.getEyePosition(1.0f), entity.level());
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
        Vec3 lookVector = user.getLookAngle();
        Vec3 endPosition = eyePosition.add(lookVector.scale(15));
        Vec3 toRight = lookVector.cross(new Vec3(0,1,0).normalize());   //视野往右侧的单位向量
        Vec3 toTop = lookVector.cross(toRight.reverse()).normalize();//视野往上的单位向量
        Vec3 leftBottom = eyePosition.add(toRight.scale(-halfWidth)).add(toTop.scale(-halfHeight));
        Vec3 rightTop = endPosition.add(toRight.scale(halfWidth)).add(toTop.scale(halfHeight));
        AABB aabb = new AABB(leftBottom, rightTop); //扫视的范围

        List<Entity> list = user.level().getEntities(user, aabb, entity -> entity instanceof LivingEntity && entity != user);   //找寻范围内的单位，排除自己
        if (list.size() > 0) {  //有单位可以处理，后续增加扣蓝代码和
            if (!user.level().isClientSide) {   //判断服务端
                for (Entity entity : list) {
                    for (int i = 0; i < 3; i++) {
                        createArrowItem(entity.getEyePosition(1.f), user.level());
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
