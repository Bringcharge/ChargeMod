package com.charge.chargemod.item.sword;

import com.charge.chargemod.lingqi.PlayerLingQiHelper;
import com.charge.chargemod.network.ChargePacketSender;
import net.minecraft.core.Vec3i;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class FuYaoSword extends ChargeBaseSword {
    @Override
    public boolean skillWithNone(Player player, InteractionHand hand) { //右键什么都没有击中，最低的优先级
        //这玩意不能限制服务端，玩家的移动一定要在客户端做
    //灵气判断
        boolean canUse = PlayerLingQiHelper.consumeLingQi(player, 8);
        if (!canUse) {
            if (!player.level().isClientSide) {
                player.sendSystemMessage(Component.translatable("describe.charge.need_ling_li"));
            }
            return false;
        } else {
            if (!player.level().isClientSide) {
                ChargePacketSender.sendLingqiMessageToClient((ServerPlayer) player, PlayerLingQiHelper.getLingQi(player));
            }
        }
        player.awardStat(Stats.ITEM_USED.get(this));
        int j = 4;
        float f7 = player.getYRot();
        float f = player.getXRot();
        float f1 = -Mth.sin(f7 * ((float)Math.PI / 180F)) * Mth.cos(f * ((float)Math.PI / 180F)) ;
        float f2 = -Mth.sin(f * ((float)Math.PI / 180F)) ;
        float f3 = Mth.cos(f7 * ((float)Math.PI / 180F)) * Mth.cos(f * ((float)Math.PI / 180F)) ;
        float f4 = Mth.sqrt(f1 * f1 + f2 * f2 + f3 * f3);
        float f5 = 6;
        f1 *= f5 / f4;
        f2 *= f5 / f4;
        f3 *= f5 / f4;
        player.push((double)f1, (double)f2, (double)f3);
        player.startAutoSpinAttack(50);
        SoundEvent soundevent;
        soundevent = SoundEvents.TRIDENT_RIPTIDE_3;
        player.level().playSound((Player)null, player, soundevent, SoundSource.PLAYERS, 1.0F, 1.0F);
        return true;
    }
}
