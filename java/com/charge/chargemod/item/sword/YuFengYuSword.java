package com.charge.chargemod.item.sword;

import com.charge.chargemod.lingqi.PlayerLingQiHelper;
import com.charge.chargemod.network.ChargePacketSender;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Position;
import net.minecraft.core.Vec3i;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.Random;

//欲风雨
public class YuFengYuSword extends ChargeBaseSword{

    public boolean skillWithEntity(LivingEntity entity, Player user, InteractionHand hand) { //右键击中了怪物的函数，最高优先级
        Level level = entity.level();
        if (!level.isClientSide) { //首先是在服务端进行设置
            if (entity != null) {

                boolean canUse = PlayerLingQiHelper.consumeLingQi(user, 20);
                if (!canUse) {
                    user.sendSystemMessage(Component.translatable("describe.charge.need_ling_li"));
                    return false;
                } else {
                    ChargePacketSender.sendLingqiMessageToClient((ServerPlayer) user, PlayerLingQiHelper.getLingQi(user));
                }

                Random random = new Random();
                int number = random.nextInt(3,6);
                for (int i = 0; i <number; i++) {
                    int x = random.nextInt(-8,8);
                    int z = random.nextInt(-8,8);
                    LightningBolt lightningBoltEntity = lightningCreat(level);
                    lightningBoltEntity.setPos(entity.position().x + x, entity.position().y, entity.position().z + z);
                    level.addFreshEntity(lightningBoltEntity);
                }
                LightningBolt lightningBoltEntity = lightningCreat(level);
                lightningBoltEntity.setPos(entity.position());
                level.addFreshEntity(lightningBoltEntity);
            }
        }
        return true;
    }

    public boolean skillWithBlock(BlockPos blockPos, Player user, InteractionHand hand) { //右键击中了方块的函数，第二优先级
        Level level = user.level();
        if (!level.isClientSide) { //首先是在服务端进行设置
            Vec3 position = blockPos.getCenter();

            boolean canUse = PlayerLingQiHelper.consumeLingQi(user, 20);
            if (!canUse) {
                user.sendSystemMessage(Component.translatable("describe.charge.need_ling_li"));
                return false;
            } else {
                ChargePacketSender.sendLingqiMessageToClient((ServerPlayer) user, PlayerLingQiHelper.getLingQi(user));
            }

            Random random = new Random();
            int number = random.nextInt(3,6);
            for (int i = 0; i <number; i++) {
                int x = random.nextInt(-8,8);
                int z = random.nextInt(-8,8);
                LightningBolt lightningBoltEntity = lightningCreat(level);
                lightningBoltEntity.setPos(position.x + x, position.y, position.z + z);
                level.addFreshEntity(lightningBoltEntity);
            }
            LightningBolt lightningBoltEntity = lightningCreat(level);
            lightningBoltEntity.setPos(position);
            level.addFreshEntity(lightningBoltEntity);

        }
        return true;
    }


    public LightningBolt lightningCreat(Level level) {
        LightningBolt lightningBolt = new LightningBolt(EntityType.LIGHTNING_BOLT, level);
        lightningBolt.setDamage(10);
        return lightningBolt;
    }
}
