package com.charge.chargemod.item.talisman;

import com.charge.chargemod.damage.ChargeDamageTypes;
import com.charge.chargemod.damage.DaoFaDamageSource;
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
import java.util.Random;

//仙符-想不通
public class XiangBuTongTalisman extends ChargeBaseTalisman {


    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        int halfWidth = 20;
        int halfHeight = 7;

        Vec3 eyePosition = player.getEyePosition(1.0F);
        Vec3 bottomWestSouth = eyePosition.add(-halfWidth, -halfHeight, halfWidth);
        Vec3 topEastNorth = eyePosition.add(halfWidth, halfHeight, -halfWidth);
        AABB searchArea = new AABB(bottomWestSouth, topEastNorth);
        List<Entity> entityList = level.getEntities(player, searchArea, entity -> entity instanceof LivingEntity && entity != player);
        int i = 0;
        for (Entity entity : entityList) {  //制作混乱
            i = (i + 1) % entityList.size();
            Entity source = entityList.get(i);

            DamageSource damageSource = DaoFaDamageSource.source(source, ChargeDamageTypes.DAO_HEAVY);
            entity.hurt(damageSource, 5);
        }
        return super.use(level,player,hand);
    }
}
