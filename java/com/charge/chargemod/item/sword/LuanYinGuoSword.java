package com.charge.chargemod.item.sword;

import com.charge.chargemod.damage.ChargeDamageTypes;
import com.charge.chargemod.damage.DaoFaDamageSource;
import com.charge.chargemod.effect.ModEffects;
import com.charge.chargemod.lingqi.PlayerLingQiHelper;
import com.charge.chargemod.network.ChargePacketSender;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.List;


public class LuanYinGuoSword extends ChargeBaseSword {
    @Override
    public boolean skillWithEntity(LivingEntity entity, Player player, InteractionHand hand) {
        //灵气判断
        boolean canUse = PlayerLingQiHelper.consumeLingQi(player, 50);
        if (!canUse) {
            if (!player.level().isClientSide) {
                player.sendSystemMessage(Component.translatable("describe.charge.need_ling_li"));
            }
            return false;
        } else {
            if (!player.level().isClientSide) {
                ChargePacketSender.sendLingqiMessageToClient((ServerPlayer) player, PlayerLingQiHelper.getLingQi(player));
            }
        }
        Vec3 pos = entity.getPosition(1.f);
        List<Entity> list = player.level().getEntities(player, new AABB(pos.x() - 2.0D, pos.y() - 2.0D, pos.z() - 2.0D, pos.x() + 2.0D, pos.y() + 2.0D, pos.z() + 2.0D), (p_147140_) -> {
            return p_147140_.isAlive();
        }); //获取目标单位附近的单位

        for (Entity entity1:list) {
            if (entity1 instanceof LivingEntity) {
                LivingEntity livingEntity = (LivingEntity) entity1;
                livingEntity.hurt(DaoFaDamageSource.source(player, ChargeDamageTypes.DAO_REAL), 18);
                livingEntity.addEffect(new MobEffectInstance(ModEffects.SILENT_EFFECT.get(), 20 * 10));
            }
        }
        double step = 0.2;
        for (double i = pos.x() - 2.0D; i < pos.x() + 2.0D; i+=step) {
            double j = pos.y() + 0.5D;
            for (double k = pos.z() - 2.0D; k < pos.z() + 2.0D; k+=step) {
                double offset_x = i - pos.x;
                double offset_z = k - pos.z;
                player.level().addParticle(ParticleTypes.WITCH, true,i, j, k , offset_x / 3.f, 0, offset_z / 3.f);
            }
        }
        return true;
    }

    @Override

    public boolean skillWithBlock(BlockPos blockPos, Player player, InteractionHand hand) {
        Vec3 pos = blockPos.getCenter();
        //灵气判断
        boolean canUse = PlayerLingQiHelper.consumeLingQi(player, 50);
        if (!canUse) {
            if (!player.level().isClientSide) {
                player.sendSystemMessage(Component.translatable("describe.charge.need_ling_li"));
            }
            return false;
        } else {
            if (!player.level().isClientSide) {
                ChargePacketSender.sendLingqiMessageToClient((ServerPlayer) player, PlayerLingQiHelper.getLingQi(player));
            }
        }
        List<Entity> list = player.level().getEntities(player, new AABB(pos.x() - 2.0D, pos.y() - 2.0D, pos.z() - 2.0D, pos.x() + 2.0D, pos.y() + 2.0D, pos.z() + 2.0D), (p_147140_) -> {
            return p_147140_.isAlive();
        }); //获取目标单位附近的单位

        for (Entity entity1:list) {
            if (entity1 instanceof LivingEntity) {
                LivingEntity livingEntity = (LivingEntity) entity1;
                livingEntity.hurt(DaoFaDamageSource.source(player, ChargeDamageTypes.DAO_REAL), 15);
                livingEntity.addEffect(new MobEffectInstance(ModEffects.SILENT_EFFECT.get(), 20 * 10));
            }
        }
        double step = 0.5;
        for (double i = pos.x() - 2.0D; i < pos.x() + 2.0D; i+=step) {
            double j = pos.y() + 0.5D;
            for (double k = pos.z() - 2.0D; k < pos.z() + 2.0D; k+=step) {
                double offset_x = i - pos.x;
                double offset_z = k - pos.z;
                player.level().addParticle(ParticleTypes.WITCH, true,i, j, k , offset_x, 0, offset_z);
            }
        }
        return true;
    }

    public static boolean isSafePosition(Level level, BlockPos pos, Entity entity) {
        // 获取实体的碰撞箱（基于其尺寸）
        AABB entityAABB = entity.getDimensions(entity.getPose()).makeBoundingBox(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);

        // 检查碰撞箱与周围方块的碰撞
        for (int y = pos.getY(); y < pos.getY() + entityAABB.getYsize(); y++) {
            BlockPos checkPos = new BlockPos(pos.getX(), y, pos.getZ());
            VoxelShape collisionShape = level.getBlockState(checkPos).getCollisionShape(level, checkPos);
            if (!collisionShape.isEmpty() && collisionShape.bounds().move(checkPos).intersects(entityAABB)) {
                return false; // 碰撞箱重叠，位置不安全
            }
        }
        return true;
    }


}
