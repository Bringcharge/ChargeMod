package com.charge.chargemod.item.sword;

import com.charge.chargemod.damage.ChargeDamageTypes;
import com.charge.chargemod.damage.DaoFaDamageSource;
import com.charge.chargemod.lingqi.PlayerLingQiHelper;
import com.charge.chargemod.network.ChargePacketSender;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Position;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.List;


public class QiuXiaoYaoSword extends ChargeBaseSword {
    @Override
    public boolean skillWithEntity(LivingEntity entity, Player player, InteractionHand hand) {
        //灵气判断

        boolean canUse = PlayerLingQiHelper.consumeLingQi(player, 30);
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
                livingEntity.hurt(DaoFaDamageSource.source(player, ChargeDamageTypes.DAO_REAL), 15);
            }
        }
        return true;
    }

    @Override

    public boolean skillWithBlock(BlockPos blockPos, Player player, InteractionHand hand) {
        //灵气判断
        boolean canUse = PlayerLingQiHelper.consumeLingQi(player, 30);
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
        Level level = player.level();
        Vec3 lookVec = player.getLookAngle();
        Vec3 vec32 = player.getEyePosition();
        Vec3 vec33 = blockPos.getCenter();
        for (int count = 0; count < 20; count ++) {
            Vec3 target = vec33.add(lookVec.scale(-count));
            BlockPos pos1 = BlockPos.containing(target);
            BlockPos pos2 = pos1.below();
            if (isSafePosition(player.level(), pos1, player)) { //焦点
                player.teleportTo(target.x, target.y, target.z);
                break;
            } else if (isSafePosition(player.level(), pos2, player)) {//往下一格，方便通道计算
                player.teleportTo(target.x, target.y - 1, target.z);
                break;
            }
            player.level().playSound(
                    null,                     // 无特定来源实体（全局声音）
                    BlockPos.containing(player.position()), // 声音位置
                    SoundEvents.ENDERMAN_TELEPORT, // 声音事件（原版或自定义）
                    SoundSource.PLAYERS,       // 声音类别（BLOCKS, PLAYERS, AMBIENT 等）
                    1.0F, 1.0F                // 音量、音高
            );
        }
        return true;
    }


    @Override
    public boolean skillWithNone(Player player, InteractionHand hand) {
        Level level = player.level();
        Vec3 lookVec = player.getLookAngle();
        Vec3 vec32 = player.getEyePosition();
        Vec3 vec33 = vec32.add(lookVec.scale(20));
        boolean canUse = PlayerLingQiHelper.consumeLingQi(player, 30);
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
        BlockHitResult result = level.clip(new ClipContext(vec32, vec33, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, player));
        if (result.getType() != HitResult.Type.MISS) {
            BlockPos pos = result.getBlockPos();
            vec33 = pos.getCenter();
        }
        for (int count = 0; count < 20; count ++) {
            Vec3 target = vec33.add(lookVec.scale(-count));
            BlockPos pos1 = BlockPos.containing(target);
            BlockPos pos2 = pos1.below();
            if (isSafePosition(player.level(), pos1, player)) { //焦点
                player.teleportTo(target.x, target.y, target.z);
                return true;
            } else if (isSafePosition(player.level(), pos2, player)) {//往下一格，方便通道计算
                player.teleportTo(target.x, target.y - 1, target.z);
                return true;
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
