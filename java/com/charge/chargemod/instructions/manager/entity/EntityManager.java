package com.charge.chargemod.instructions.manager.entity;


import com.charge.chargemod.instructions.manager.InstructionsModel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class EntityManager {
    //user实体
    public static Entity entityOfUser (InstructionsModel model) {    //E101_
        return model.user;
    }
    //从回调函数中取出实体
    public static Entity entityBlockTarget (InstructionsModel model) {    //E201_
        return model.targetEntity;
    }
    //Entity入参视野上的entity
    public static Entity entityLookTarget(Vec3 eyePosition, Vec3 lookVector, Entity entity, InstructionsModel model) {    //E301_

        double dis = lookVector.length();
//        double reachDistance = Math.min(dis, 50); // 视线检测的最大距离
        double reachDistance = 50; // 视线检测的最大距离
        double scale = reachDistance / dis; //缩放倍数
        // 获取玩家的视线起点和终点
        Vec3 endPosition = eyePosition.add(lookVector.scale(scale));

        // 定义检测范围
        AABB searchArea = entity.getBoundingBox().expandTowards(lookVector.scale(reachDistance)).inflate(1.0);

        // 检测视线聚焦的实体
        EntityHitResult entityHitResult = ProjectileUtil.getEntityHitResult(
                entity.level(),
                entity,
                eyePosition,
                endPosition,
                searchArea,
                targetEntity -> targetEntity instanceof LivingEntity && targetEntity != entity // 过滤条件
        );

        if (entityHitResult != null && entityHitResult.getType() == HitResult.Type.ENTITY) {
            if (entityHitResult.getEntity() instanceof LivingEntity ) {// 如果视线聚焦的是 LivingEntity
                return entityHitResult.getEntity();
            }
        }
        return null;
    }
}
