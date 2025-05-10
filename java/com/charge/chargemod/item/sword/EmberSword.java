package com.charge.chargemod.item.sword;

import com.charge.chargemod.damage.ChargeDamageTypes;
import com.charge.chargemod.damage.DaoFaDamageSource;
import com.charge.chargemod.lingqi.PlayerLingQiHelper;
import com.charge.chargemod.network.ChargePacketSender;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.Random;

public class EmberSword extends ChargeBaseSword{
    @Override
    public boolean skillWithEntity(LivingEntity entity, Player user, InteractionHand hand) { //右键击中了怪物的函数，最高优先级
        Level level = user.level();
        if (entity != null) {
            if (!level.isClientSide) {
                //灵气判断
                boolean canUse = PlayerLingQiHelper.consumeLingQi(user, 7);
                if (!canUse) {
                    user.sendSystemMessage(Component.translatable("describe.charge.need_ling_li"));
                    return false;
                } else {
                    ChargePacketSender.sendLingqiMessageToClient((ServerPlayer) user, PlayerLingQiHelper.getLingQi(user));
                }

                if (entity.getRemainingFireTicks() > 0) {
                    DamageSource damageSource = DaoFaDamageSource.source(user, ChargeDamageTypes.DAO_REAL);
                    //第三个参数是计算伤害的东西
                    level.explode(user, damageSource, null, entity.getEyePosition(), 4.f, false, Level.ExplosionInteraction.NONE);
                    entity.hurt(DaoFaDamageSource.source(user, ChargeDamageTypes.DAO_REAL), 10);
                } else {
                    entity.hurt(user.level().damageSources().lava(), 10);
                    entity.setSecondsOnFire(8);

                }
            } else {
                Random random = new Random();
                for (int i = 0; i < 8; i++) {
                    Vec3 center = entity.getEyePosition();
                    Vec3 to_vec = new Vec3(random.nextFloat(2)-1,random.nextFloat(2)-1,random.nextFloat(2)-1).normalize().scale(0.1);
                    level.addParticle(ParticleTypes.FLAME, center.x, center.y, center.z , to_vec.x, to_vec.y, to_vec.z);
                }
            }
            return true;
        }
        return false;

    }
}
