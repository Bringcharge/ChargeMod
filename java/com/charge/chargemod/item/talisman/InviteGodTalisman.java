package com.charge.chargemod.item.talisman;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.util.UUID;

//请神符
public class InviteGodTalisman extends ChargeBaseTalisman {
    public InviteGodTalisman() {    //只能拿一个
        super(new Properties().stacksTo(1));
    }
    @Override
    public UseAnim getUseAnimation(ItemStack p_41452_) {
        return UseAnim.BOW;
    }
    @Override
    public int getUseDuration(ItemStack p_40680_) {
        return 7200;
    }

    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (!level.isClientSide()) {
            if (player.isShiftKeyDown()) {
                // Shift+右键逻辑
                LivingEntity entity = getPlayerView(player);    //获取前方entity
                if (entity != null) {
                    setGodUUID(stack, entity.getUUID());    //设置uuid
                    player.sendSystemMessage(Component.translatable("已获取uuid:" + entity.getName().toString()));
                    return InteractionResultHolder.success(stack);
                }

                return InteractionResultHolder.fail(stack);
            } else {
                // 普通右键逻辑
               if (level instanceof ServerLevel) {
                   ServerLevel serverLevel = (ServerLevel)level;
                   Entity entity = serverLevel.getEntity(getGodUUID(stack));
                   if (entity != null) {
                       setInvitePos(stack,entity.blockPosition());
                       player.startUsingItem(hand);
                       //TODO: 添加一个粒子效果
                       return InteractionResultHolder.consume(stack);
                   }
               }

            }
        }
        return InteractionResultHolder.fail(stack);
    }

    //松手之后
    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity player, int tick) {
        if (tick > 5 * 20) {
            if (!level.isClientSide && level instanceof ServerLevel) {
                ServerLevel serverLevel = (ServerLevel) level;
                BlockPos pos = getInvitePos(stack);
                Entity entity = serverLevel.getEntity(getGodUUID(stack));
                if (entity.getPosition(1.0f).distanceTo(pos.getCenter()) < 3) { //距离合适
                    entity.setPos(player.getPosition(1.f));
                }
                stack.shrink(1);//消耗一个物品
                if (stack.isEmpty()) {
                    ((Player)player).getInventory().removeItem(stack);
                }

            }
        }

    }

    @Override
    public void onUseTick(Level level, LivingEntity player, ItemStack stack, int tick) {
        super.onUseTick(level, player, stack, tick);
    }

    // 将uuid存储到 NBT 中
    private void setGodUUID(ItemStack stack, UUID uuid) {
        CompoundTag tag = stack.getOrCreateTag();
        tag.putUUID("inviteGod", uuid);
        stack.setTag(tag);
    }
    //获取uuid
    private UUID getGodUUID(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        return tag.getUUID("inviteGod");
    }

    private void setInvitePos(ItemStack stack, BlockPos pos) {
        CompoundTag tag = stack.getOrCreateTag();
        long k = pos.asLong();
        tag.putLong("invitePos", k);
        stack.setTag(tag);
    }

    private BlockPos getInvitePos(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        long k = tag.getLong("invitePos");
        BlockPos pos = BlockPos.of(k);
        return pos;
    }


    //获取玩家视线
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
                return livingEntity;
            }
        }
        return null;
    }
}
