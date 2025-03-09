package com.charge.chargemod.item.sword;

import com.charge.chargemod.instructions.manager.Instruction;
import com.charge.chargemod.instructions.manager.InstructionsManager;
import com.charge.chargemod.instructions.manager.InstructionsModel;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.*;

//仙剑基类，这item不会注册，不会展示。
public class ChargeBaseSword extends SwordItem {
    public ChargeBaseSword(){
        super(ModItemTiers.LingShi,3,-2.4f,new Item.Properties());
    }

    public boolean skillWithEntity(LivingEntity entity, Player user) { //右键击中了怪物的函数，最高优先级
        return false;
    }

    public boolean skillWithBlock(BlockPos blockPos, Player user) { //右键击中了方块的函数，第二优先级
        return false;
    }

    public boolean skillWithNone(Player user) { //右键什么都没有击中，最低的优先级
        return false;
    }


    //右键使用物品
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        BlockHitResult viewPosResult = vecRayToBlockPos(player); //获取玩家视线尽头的方块
        EntityHitResult viewEntityResult = vecRayToEntity(player);   //获取玩家视野的entity

        boolean consumeFlag = false;    //使用技能是否已经被处理了

        if (viewEntityResult != null &&
                (viewPosResult == null ||
                viewEntityResult.getLocation().distanceTo(player.getEyePosition()) < viewPosResult.getLocation().distanceTo(player.getEyePosition()))) { //距离判断，entity比block近
            //优先攻击entity，触发entity特效
            consumeFlag = skillWithEntity((LivingEntity) viewEntityResult.getEntity(), player);
        }

        if (viewPosResult != null && !consumeFlag) {    //技能视野针对的方块
            consumeFlag = skillWithBlock(viewPosResult.getBlockPos(),player);
        }

        if (!consumeFlag) {
            consumeFlag = skillWithNone(player);
        }

        if (consumeFlag) {
            return InteractionResultHolder.success(stack);
        }
        return InteractionResultHolder.pass(stack);
    }



    public static BlockHitResult vecRayToBlockPos(Player player) {  //V020# 从vec1发出向量vec2的射线，看到的方块中心，太远了的话，就miss
        Level worldIn = player.level();

        Vec3 eyePosition = player.getEyePosition(1.0F);
        Vec3 lookVector = player.getLookAngle();
        Vec3 vec3 = lookVector.scale(50 / lookVector.length());
        Vec3 vec_to = vec3.add(eyePosition);
        //查看阻塞位置
        ClipContext context = new ClipContext(eyePosition, vec_to, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, player);//前两个参数是起点和终点坐标
        BlockHitResult blockRayTraceResult = worldIn.clip(context);
        if (blockRayTraceResult.getType() == HitResult.Type.BLOCK) {
            return blockRayTraceResult;
        } else {
            return null;
        }
    }

    //检测视线末端的entity
    public static EntityHitResult vecRayToEntity(Player player) {
        double reachDistance = 50.0; // 视线检测的最大距离
        // 获取玩家的视线起点和终点
        Vec3 eyePosition = player.getEyePosition(1.0F);
        Vec3 lookVector = player.getLookAngle();
        Vec3 endPosition = eyePosition.add(lookVector.scale(reachDistance));

        // 定义检测范围
        AABB searchArea = player.getBoundingBox().expandTowards(lookVector.scale(reachDistance)).inflate(1.0);

        // 检测视线聚焦的实体
        EntityHitResult entityHitResult = ProjectileUtil.getEntityHitResult(
                player.level(),
                player,
                eyePosition,
                endPosition,
                searchArea,
                entity -> entity instanceof LivingEntity && entity != player // 过滤条件
        );

        if (entityHitResult != null && entityHitResult.getType() == HitResult.Type.ENTITY) {
            if (entityHitResult.getEntity() instanceof LivingEntity ) {
                LivingEntity livingEntity = (LivingEntity)entityHitResult.getEntity();
                // 如果视线聚焦的是 LivingEntity
                return entityHitResult;
            }
        }
        return null;
    }
}
