package com.charge.chargemod.item.sword;

import com.charge.chargemod.damage.ChargeDamageTypes;
import com.charge.chargemod.damage.DaoFaDamageSource;
import com.charge.chargemod.lingqi.PlayerLingQiHelper;
import com.charge.chargemod.network.ChargePacketSender;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.FlyingMob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class EarthBeatSword extends ChargeBaseSword {
    @Override
    public boolean skillWithNone(Player user, InteractionHand hand) { //右键什么都没有击中，最低的优先级
        Level level = user.level();
        if (!level.isClientSide()) {

            //灵气判断
            boolean canUse = PlayerLingQiHelper.consumeLingQi(user, 15);
            if (!canUse) {
                user.sendSystemMessage(Component.translatable("describe.charge.need_ling_li"));
                return false;
            } else {
                ChargePacketSender.sendLingqiMessageToClient((ServerPlayer) user, PlayerLingQiHelper.getLingQi(user));
            }

            int halfWidth = 8;
            int halfHeight = 7;

            Vec3 eyePosition = user.getEyePosition(1.0F);
            Vec3 bottomWestSouth = eyePosition.add(-halfWidth, -halfHeight, halfWidth);
            Vec3 topEastNorth = eyePosition.add(halfWidth, halfHeight, -halfWidth);
            AABB searchArea = new AABB(bottomWestSouth, topEastNorth);
            List<Entity> entityList = level.getEntities(user, searchArea, entity -> entity instanceof LivingEntity && entity != user);
            for (Entity entity : entityList) {
                DamageSource damageSource = DaoFaDamageSource.source(user, ChargeDamageTypes.DAO_HEAVY);
                entity.hurt(damageSource, 6);
                if (!(entity instanceof FlyingMob)) {
                    entity.push(0, 8, 0);
                }
            }

        }
        return true;
    }
}
