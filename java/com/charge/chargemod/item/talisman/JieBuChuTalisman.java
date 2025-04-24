package com.charge.chargemod.item.talisman;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.WrappedGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

//仙符-解不出
public class JieBuChuTalisman extends ChargeBaseTalisman {


    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        int halfWidth = 20;
        int halfHeight = 7;

        Vec3 eyePosition = player.getEyePosition(1.0F);
        Vec3 bottomWestSouth = eyePosition.add(-halfWidth, -halfHeight, halfWidth);
        Vec3 topEastNorth = eyePosition.add(halfWidth, halfHeight, -halfWidth);
        AABB searchArea = new AABB(bottomWestSouth, topEastNorth);
        List<Entity> entityList = level.getEntities(player, searchArea, entity -> entity instanceof LivingEntity && !(entity instanceof Player) );

        for (Entity entity : entityList) {
            LivingEntity livingEntity = (LivingEntity) entity;
            livingEntity.setPos(player.position().add(player.getLookAngle().scale(5)));
            livingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20 * 15, 7));

        }
        return super.use(level,player,hand);
    }
}
