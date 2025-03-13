package com.charge.chargemod.item.sword;

import net.minecraft.core.Vec3i;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class TheRealSword extends ChargeBaseSword{
    @Override
    public boolean skillWithEntity(LivingEntity entity, Player user, InteractionHand hand) { //右键击中了怪物的函数，最高优先级
        Level level = user.level();
        if (!level.isClientSide) { //首先是在服务端进行设置
            Vec3 place = user.getPosition(1.0f);
            Vec3i position = new Vec3i((int)Math.floor(place.x),(int)Math.floor(place.y),(int)Math.floor(place.z));
            if (entity != null) {
                if ((entity instanceof Mob)) {  //是怪物的情况下，身上所有物品保证掉落
                    Mob mob = (Mob) entity;
                    mob.setGuaranteedDrop(EquipmentSlot.MAINHAND);
                    mob.setGuaranteedDrop(EquipmentSlot.OFFHAND);
                    mob.setGuaranteedDrop(EquipmentSlot.HEAD);
                    mob.setGuaranteedDrop(EquipmentSlot.CHEST);
                    mob.setGuaranteedDrop(EquipmentSlot.LEGS);
                    mob.setGuaranteedDrop(EquipmentSlot.FEET);
                }
                //接下来打一个伤害，加点粒子差不多了

            }
        }
        return true;
    }
}
