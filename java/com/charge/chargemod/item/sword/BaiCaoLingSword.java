package com.charge.chargemod.item.sword;

import com.charge.chargemod.damage.ChargeDamageTypes;
import com.charge.chargemod.damage.DaoFaDamageSource;
import com.charge.chargemod.lingqi.PlayerLingQiHelper;
import com.charge.chargemod.network.ChargePacketSender;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.Random;

public class BaiCaoLingSword extends ChargeBaseSword {

    @Override
    public InteractionResultHolder<ItemStack> use(Level p_40672_, Player p_40673_, InteractionHand p_40674_) {
        ItemStack itemstack = p_40673_.getItemInHand(p_40674_);

        p_40673_.startUsingItem(p_40674_);
        return InteractionResultHolder.consume(itemstack);
    }

    @Override
    public int getUseDuration(ItemStack p_40680_) {
        return 20 * 10;
    }

    @Override
    public void onUseTick(Level level, LivingEntity user, ItemStack stack, int p_41431_) {
        super.onUseTick(level, user, stack, p_41431_);
        Player player = (Player)user;
        boolean canUse = PlayerLingQiHelper.consumeLingQi(player, 1);
        if (canUse) {
            Random random = new Random();
            for (int i = 0; i < 5; i++) {
                Vec3 center = player.getPosition(1.f);
                Vec3 to_vec = new Vec3(random.nextFloat(2)-1,0,random.nextFloat(2)-1).normalize().scale(1);
                level.addParticle(ParticleTypes.SPIT, true, center.x, center.y + 0.8, center.z , to_vec.x, to_vec.y, to_vec.z);
            }
        }
        if (!user.level().isClientSide()) {
            //灵气消耗
            if (!canUse) {
                player.sendSystemMessage(Component.translatable("describe.charge.need_ling_li"));
                return;
            } else {
                ChargePacketSender.sendLingqiMessageToClient((ServerPlayer) player, PlayerLingQiHelper.getLingQi(player));
                level.playSound(
                        user,                     // 无特定来源实体（全局声音）
                        BlockPos.containing(user.position()), // 声音位置
                        SoundEvents.CHAIN_HIT, // 声音事件（原版或自定义）
                        SoundSource.PLAYERS,       // 声音类别（BLOCKS, PLAYERS, AMBIENT 等）
                        1.0F, 1.0F                // 音量、音高
                );
            }

            int halfWidth = 8;
            int halfHeight = 7;
            int tickCount = getTickCount(stack);
            setTickCount(stack, tickCount + 1);
            if (tickCount % 5 == 0) {
                Vec3 eyePosition = user.getEyePosition(1.0F);
                Vec3 bottomWestSouth = eyePosition.add(-halfWidth, -halfHeight, halfWidth);
                Vec3 topEastNorth = eyePosition.add(halfWidth, halfHeight, -halfWidth);
                AABB searchArea = new AABB(bottomWestSouth, topEastNorth);
                List<Entity> entityList = level.getEntities(user, searchArea, entity -> entity instanceof LivingEntity && entity != user);
                for (Entity entity : entityList) {
                    DamageSource damageSource = DaoFaDamageSource.source(user,ChargeDamageTypes.DAO_REAL);
                    if (!entity.isFullyFrozen()) {
                        entity.hurt(damageSource, 1);
                    } else {
                        entity.hurt(damageSource, 7);
                    }
                    LivingEntity livingEntity = (LivingEntity)entity;
                    livingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 3, 3));
                    int frozen = entity.getTicksFrozen();
                    entity.setTicksFrozen(frozen + 40);
                }
            }
        }
    }

    @Override
    public void releaseUsing(ItemStack p_41412_, Level p_41413_, LivingEntity p_41414_, int p_41415_) {
        super.releaseUsing(p_41412_, p_41413_, p_41414_, p_41415_);
        setTickCount(p_41412_,0);
    }

    // 从 NBT 中获取斩杀数量
    private int getTickCount(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        return tag.getInt("baicaolingTickCount");
    }

    // 将tick数量存储到 NBT 中
    private void setTickCount(ItemStack stack, int tickCount) {
        CompoundTag tag = stack.getOrCreateTag();
        tag.putInt("baicaolingTickCount", tickCount);
        stack.setTag(tag);
    }
}
