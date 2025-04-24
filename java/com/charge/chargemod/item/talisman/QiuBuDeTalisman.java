package com.charge.chargemod.item.talisman;

import com.charge.chargemod.damage.ChargeDamageTypes;
import com.charge.chargemod.damage.DaoFaDamageSource;
import com.charge.chargemod.effect.SilentEffect;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.FlyingMob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

//仙符-求不得
public class QiuBuDeTalisman extends ChargeBaseTalisman {


    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        int halfWidth = 20;
        int halfHeight = 7;

        Vec3 eyePosition = player.getEyePosition(1.0F);
        Vec3 bottomWestSouth = eyePosition.add(-halfWidth, -halfHeight, halfWidth);
        Vec3 topEastNorth = eyePosition.add(halfWidth, halfHeight, -halfWidth);
        AABB searchArea = new AABB(bottomWestSouth, topEastNorth);
        List<Entity> entityList = level.getEntities(player, searchArea, entity -> entity instanceof Mob);

        for (Entity entity : entityList) {  //制作混乱
            Mob mob = (Mob) entity;

            List<WrappedGoal> list = mob.targetSelector.getAvailableGoals().stream().toList();
            for (WrappedGoal wrappedGoal : list) {  //找不到攻击的，直接移除了
                mob.targetSelector.removeGoal(wrappedGoal.getGoal());
            }

//            if (entity instanceof Creeper) {    //如果是creeper吃了buff，修改ai
//                Creeper creeper = (Creeper) entity;
//                List<WrappedGoal> list = creeper.goalSelector.getAvailableGoals().stream().toList();
//                for (WrappedGoal wrappedGoal : list) {
//                    if (wrappedGoal.getGoal() instanceof SwellGoal) {
//                        creeper.goalSelector.removeGoal(wrappedGoal.getGoal());
//                        break;
//                    }
//                }
//            }
//            if (entity instanceof Zombie) {
//                Zombie zombie = (Zombie) entity;
//                List<WrappedGoal> list = zombie.goalSelector.getAvailableGoals().stream().toList();
//                for (WrappedGoal wrappedGoal : list) {
//                    if (wrappedGoal.getGoal() instanceof ZombieAttackGoal) {
//                        zombie.goalSelector.removeGoal(wrappedGoal.getGoal());
//                        break;
//                    }
//                }
//            }
//            if (entity instanceof Skeleton) {
//                Skeleton skeleton = (Skeleton) entity;
//                List<WrappedGoal> list = skeleton.goalSelector.getAvailableGoals().stream().toList();
//                for (WrappedGoal wrappedGoal : list) {
//                    if (wrappedGoal.getGoal() instanceof RangedBowAttackGoal || wrappedGoal.getGoal() instanceof MeleeAttackGoal) {
//                        skeleton.goalSelector.removeGoal(wrappedGoal.getGoal());
//                    }
//                }
//            }
//            if (entity instanceof Slime || entity instanceof FlyingMob) {
//                Mob slime = (Mob) entity;
//                List<WrappedGoal> list = slime.goalSelector.getAvailableGoals().stream().toList();
//                for (WrappedGoal wrappedGoal : list) {  //找不到攻击的，直接移除了
//                    slime.goalSelector.removeGoal(wrappedGoal.getGoal());
//                }
//            }

        }
        return super.use(level,player,hand);
    }
}
