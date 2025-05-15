package com.charge.chargemod.item.sword;

import com.charge.chargemod.damage.ChargeDamageTypes;
import com.charge.chargemod.damage.DaoFaDamageSource;
import com.charge.chargemod.effect.ModEffects;
import com.charge.chargemod.lingqi.PlayerLingQiHelper;
import com.charge.chargemod.network.ChargePacketSender;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class XunYinSword extends ChargeBaseSword {
    @Override
    public boolean skillWithNone(Player user, InteractionHand hand) {
        if (!user.level().isClientSide) {
            boolean canUse = PlayerLingQiHelper.consumeLingQi(user, 3);
            if (!canUse) {
                user.sendSystemMessage(Component.translatable("describe.charge.need_ling_li"));
                return false;
            } else {
                ChargePacketSender.sendLingqiMessageToClient((ServerPlayer) user, PlayerLingQiHelper.getLingQi(user));
            }

            user.addEffect(new MobEffectInstance(ModEffects.COUNTER_EFFECT.get(), 20, 1));
        }
        return true;
    }
}
