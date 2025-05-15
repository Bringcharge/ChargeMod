package com.charge.chargemod.item.sword;

import com.charge.chargemod.damage.ChargeDamageTypes;
import com.charge.chargemod.damage.DaoFaDamageSource;
import com.charge.chargemod.lingqi.PlayerLingQiHelper;
import com.charge.chargemod.network.ChargePacketSender;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.ZombieVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import java.util.Random;

public class ZhongZhongGuSword  extends ChargeBaseSword {

    @Override
    public boolean skillWithEntity(LivingEntity entity, Player user, InteractionHand hand) { //右键击中了怪物的函数，最高优先级
        Vec3 place = user.getPosition(1.0f);
        Level level = user.level();
        if (!user.level().isClientSide) {
            boolean canUse = PlayerLingQiHelper.consumeLingQi(user, 5);
            if (!canUse) {
                user.sendSystemMessage(Component.translatable("describe.charge.need_ling_li"));
                return false;
            } else {
                ChargePacketSender.sendLingqiMessageToClient((ServerPlayer) user, PlayerLingQiHelper.getLingQi(user));
            }
        }
        if (entity != null) {
            if (!(entity instanceof Player)) {  //不是玩家的时候，爆掉它武器
                Vec3 toPlayer = entity.getPosition(1.0f).vectorTo(user.getEyePosition()).normalize().scale(2.f);
                entity.push(toPlayer.x, toPlayer.y, toPlayer.z);
                entity.hurt(DaoFaDamageSource.source(user, ChargeDamageTypes.DAO_HEAVY), 10);
            }
        }
        return false;
    }

    @Override
    public boolean skillWithBlock(BlockPos blockPos, Player user, InteractionHand hand) { //右键击中了方块的函数，第二优先级
        Level level = user.level();
        if (!level.isClientSide) { //首先是在服务端进行设置

            boolean canUse = PlayerLingQiHelper.consumeLingQi(user, 2);
            if (!canUse) {
                user.sendSystemMessage(Component.translatable("describe.charge.need_ling_li"));
                return false;
            } else {
                ChargePacketSender.sendLingqiMessageToClient((ServerPlayer) user, PlayerLingQiHelper.getLingQi(user));
            }


            Vec3 position = blockPos.getCenter();

            Random random = new Random();
            BlockState state = level.getBlockState(blockPos);
            if (!state.is(Blocks.BEDROCK)) {    //不是基岩
                level.destroyBlock(blockPos, true, user);
            }

        }
        return true;
    }
}
