package com.charge.chargemod.item.sword;

import com.charge.chargemod.damage.ChargeDamageTypes;
import com.charge.chargemod.damage.DaoFaDamageSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class EmberSword extends ChargeBaseSword{
    @Override
    public boolean skillWithEntity(LivingEntity entity, Player user, InteractionHand hand) { //右键击中了怪物的函数，最高优先级
        Level level = user.level();
        if (entity != null) {
            if (!level.isClientSide) {
                if (entity.getRemainingFireTicks() > 0) {
                    DamageSource damageSource = DaoFaDamageSource.source(user, ChargeDamageTypes.DAO_REAL);
                    //第三个参数是计算伤害的东西
                    level.explode(user,damageSource,null,entity.getEyePosition(),4.f,false,Level.ExplosionInteraction.NONE);
                    entity.hurt(DaoFaDamageSource.source(user,ChargeDamageTypes.DAO_REAL), 10);
                } else {
                    entity.hurt(user.level().damageSources().lava(), 10);
                    entity.setSecondsOnFire(8);
                }
                return true;
            }

        }
        return false;

    }
}
