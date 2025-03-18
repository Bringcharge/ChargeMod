package com.charge.chargemod.item.sword;

import com.charge.chargemod.entity.ChargeBladeExtendEntity;
import com.charge.chargemod.entity.ChargeCopperCoinEntity;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.Random;

public class WuXiangSword extends ChargeBaseSword{

    @Override
    public boolean skillWithNone(Player user, InteractionHand hand) { //右键什么都没有击中，最低的优先级
        if (!user.level().isClientSide) {
            Vec3 start = user.getEyePosition(1.0f);
            Vec3 toVec = user.getLookAngle();
            float rotateY = user.getYRot();    //视角在水平面上的弧度
            float f1 = -rotateY * ((float)Math.PI / 180F);
            float f2 = Mth.cos(f1);
            float f3 = Mth.sin(f1);
            Vec3 xz_vec = new Vec3(f3, 0, f2).normalize();
            Vec3 left = toVec.cross(xz_vec).normalize();//其实不能算真正的左，可能是右侧，取决于look的位置

            createArrowEntity(start.add(toVec.scale(0.5)), toVec, user.level(), user);  //发射剑气
        }
        return true;
    }

    private void createArrowEntity(Vec3 arrowCreatePosition, Vec3 vectorToTarget, Level worldIn, Entity owner) {
        Random random = new Random();

        ChargeBladeExtendEntity abstractarrowentity = new ChargeBladeExtendEntity(worldIn,(LivingEntity) owner);    //设置剑气
        abstractarrowentity.shoot(owner.getLookAngle(), 0.5f);
        abstractarrowentity.setOwner(owner);
        worldIn.addFreshEntity(abstractarrowentity);
    }

}
