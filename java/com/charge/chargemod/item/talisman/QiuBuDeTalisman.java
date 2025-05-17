package com.charge.chargemod.item.talisman;

import com.charge.chargemod.damage.ChargeDamageTypes;
import com.charge.chargemod.damage.DaoFaDamageSource;
import com.charge.chargemod.effect.ModEffects;
import com.charge.chargemod.effect.SilentEffect;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.FlyingMob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.monster.warden.Warden;
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
        if (!level.isClientSide) {
            int halfWidth = 20;
            int halfHeight = 7;

            Vec3 eyePosition = player.getEyePosition(1.0F);
            Vec3 bottomWestSouth = eyePosition.add(-halfWidth, -halfHeight, halfWidth);
            Vec3 topEastNorth = eyePosition.add(halfWidth, halfHeight, -halfWidth);
            AABB searchArea = new AABB(bottomWestSouth, topEastNorth);
            List<Entity> entityList = level.getEntities(player, searchArea, entity -> entity instanceof Mob);

            for (Entity entity : entityList) {  //制作混乱
                Mob mob = (Mob) entity;
                if (mob instanceof WitherBoss || mob instanceof EnderDragon) {
                    continue;
                }
                List<WrappedGoal> list = mob.targetSelector.getAvailableGoals().stream().toList();
                for (WrappedGoal wrappedGoal : list) {  //找不到攻击的，直接移除了
                    mob.targetSelector.removeGoal(wrappedGoal.getGoal());
                }
                if (mob instanceof Warden) {
                    Warden warden = (Warden)mob;
                    warden.clearAnger(player);
                }

            }

            level.playSound(
                    player,                     // 无特定来源实体（全局声音）
                    BlockPos.containing(player.position()), // 声音位置
                    SoundEvents.BELL_BLOCK, // 声音事件（原版或自定义）
                    SoundSource.PLAYERS,       // 声音类别（BLOCKS, PLAYERS, AMBIENT 等）
                    1.0F, 1.0F                // 音量、音高
            );
        }
        return super.use(level,player,hand);
    }
}
