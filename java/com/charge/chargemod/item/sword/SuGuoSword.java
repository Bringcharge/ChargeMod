package com.charge.chargemod.item.sword;


import com.charge.chargemod.damage.ChargeDamageTypes;
import com.charge.chargemod.damage.DaoFaDamageSource;
import com.charge.chargemod.effect.ModEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.SwellGoal;
import net.minecraft.world.entity.ai.goal.WrappedGoal;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;
import java.util.List;

public class SuGuoSword extends ChargeBaseSword{



    @Override
    public boolean skillWithEntity(LivingEntity entity, Player player, InteractionHand hand) {
        Level level = player.level();
        if (!level.isClientSide) { //首先是在服务端进行设置
            Vec3 place = player.getPosition(1.0f);
            Vec3i position = new Vec3i((int)Math.floor(place.x),(int)Math.floor(place.y),(int)Math.floor(place.z));

            if (entity != null) {
                entity.setPos(player.getPosition(1.f).add(player.getLookAngle().normalize().scale(1.5)));   //拉到面前来
                DamageSource damageSource = DaoFaDamageSource.source(player, ChargeDamageTypes.DAO_REAL);
                entity.hurt(damageSource,18);
                entity.addEffect(new MobEffectInstance(ModEffects.SILENT_EFFECT.get(), 20 * 5));    //沉默

            }
        }
        return true;
    }
}




