package com.charge.chargemod.item.talisman;

import com.charge.chargemod.damage.ChargeDamageTypes;
import com.charge.chargemod.damage.DaoFaDamageSource;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.FlyingMob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

//退魔符
public class PushBackTalisman extends ChargeBaseTalisman {


    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {

        if (!level.isClientSide()) {
            int halfWidth = 10;
            int halfHeight = 7;

            Vec3 eyePosition = player.getEyePosition(1.0F);
            Vec3 bottomWestSouth = eyePosition.add(-halfWidth, -halfHeight, halfWidth);
            Vec3 topEastNorth = eyePosition.add(halfWidth, halfHeight, -halfWidth);
            AABB searchArea = new AABB(bottomWestSouth, topEastNorth);
            List<Entity> entityList = level.getEntities(player, searchArea, entity -> entity instanceof LivingEntity && entity != player);
            for (Entity entity : entityList) {
                DamageSource damageSource = DaoFaDamageSource.source(player, ChargeDamageTypes.DAO_HEAVY);
                entity.hurt(damageSource, 1); //增加伤害源头判定
                Vec3 forward = player.position().vectorTo(entity.getEyePosition()).normalize(); //计算向量
                if (!(entity instanceof FlyingMob)) {
                    entity.push(forward.x * 6,forward.y * 6,forward.z * 6); //击飞单位
                }
            }
            ParticleOptions particleOptions = ParticleTypes.FLASH;
            if (player.level() instanceof ServerLevel) {
                ((ServerLevel) player.level()).sendParticles(particleOptions, player.getX(), player.getY() + 1, player.getZ(), 7, 0.0D, 0.5D, 0.0D, 1.0D);//类型，xyz，count，speed_xyz,maxSpeed
            }
        }
        return super.use(level,player,hand);
    }
}
