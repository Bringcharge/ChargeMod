package com.charge.chargemod.item.talisman;

import com.charge.chargemod.ChargeModItemRegistry;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;

public class SkullStealTalisman extends ChargeBaseTalisman {

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (!player.level().isClientSide) {
            EntityHitResult result = vecRayToEntity(player);
            if (result != null) {
                ItemStack stack = ItemStack.EMPTY;
                LivingEntity livingEntity = (LivingEntity) result.getEntity();
                if (livingEntity instanceof Zombie) {   //僵尸
                    Zombie e = (Zombie) livingEntity;
                    e.setItemSlot(EquipmentSlot.HEAD, new ItemStack(ChargeModItemRegistry.PAPER_HEAD.get()));
                    stack = new ItemStack(Items.ZOMBIE_HEAD);
                }

                if (livingEntity instanceof Skeleton) {   //骷髅
                    Skeleton e = (Skeleton) livingEntity;
                    e.setItemSlot(EquipmentSlot.HEAD, new ItemStack(ChargeModItemRegistry.PAPER_HEAD.get()));
                    stack = new ItemStack(Items.SKELETON_SKULL);
                }

                if (livingEntity instanceof WitherSkeleton) {   //骷髅
                    WitherSkeleton e = (WitherSkeleton) livingEntity;
                    e.setItemSlot(EquipmentSlot.HEAD, new ItemStack(ChargeModItemRegistry.PAPER_HEAD.get()));
                    stack = new ItemStack(Items.WITHER_SKELETON_SKULL);
                }

                if (livingEntity instanceof Creeper) {   //骷髅
                    Creeper e = (Creeper) livingEntity;
                    e.setItemSlot(EquipmentSlot.HEAD, new ItemStack(ChargeModItemRegistry.PAPER_HEAD.get()));
                    stack = new ItemStack(Items.CREEPER_HEAD);
                }

                if (livingEntity instanceof Player) {   //骷髅
                    Player e = (Player) livingEntity;

                    ItemStack originHead = e.getItemBySlot(EquipmentSlot.HEAD);
                    if (!originHead.isEmpty()) {
                        player.drop(originHead, true);
                    }
                    e.setItemSlot(EquipmentSlot.HEAD, new ItemStack(ChargeModItemRegistry.PAPER_HEAD.get()));
                    stack = new ItemStack(Items.CREEPER_HEAD);
                }

                //如果获取到了怪物的头
                if (!stack.isEmpty()) {
                    player.getInventory().add(stack);
                }

            }
        }
        return super.use(level,player,hand);
    }

    //检测视线末端的entity
    public EntityHitResult vecRayToEntity(Player player) {
        double reachDistance = 6.0; // 视线检测的最大距离
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
