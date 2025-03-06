package com.charge.chargemod.item.sword;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class ChargeBaseSword extends SwordItem {
    public ChargeBaseSword(){
        super(ModItemTiers.LingShi,3,-2.4f,new Item.Properties());
    }
    public static LivingEntity getPlayerView(Player player) {

        double reachDistance = 10.0; // 视线检测的最大距离

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
                System.out.println("玩家 " + player.getName().getString() + " 正在看 " + livingEntity.getName().getString());
                return livingEntity;
            }
        }
        return null;
    }
}
