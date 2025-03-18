package com.charge.chargemod.item.sword;

import com.charge.chargemod.entity.ChargeCangFengDaggerEntity;
import com.charge.chargemod.entity.ChargeCopperCoinEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.Random;

//铜钱剑 孔方
public class KongFangSword extends ChargeBaseSword{
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

            Vec3 toVecLeft = toVec.add(left.scale(0.5));    //向左30度
            Vec3 toVecRight = toVec.add(left.scale(-0.5));    //向左30度

            createArrowEntity(start.add(toVec.scale(0.5)), toVec, user.level(), user);
            createArrowEntity(start.add(toVecLeft.scale(0.5)), toVecLeft, user.level(), user);
            createArrowEntity(start.add(toVecRight.scale(0.5)), toVecRight, user.level(), user);
        }
        return true;
    }

    private void createArrowEntity(Vec3 arrowCreatePosition, Vec3 vectorToTarget, Level worldI, Entity owner) {
        Random random = new Random();

        ChargeCopperCoinEntity arrow = new ChargeCopperCoinEntity(worldI ,arrowCreatePosition.x(),arrowCreatePosition.y(),arrowCreatePosition.z() );
        arrow.setOwner(owner);
        //设置初速度和散布
        arrow.setDeltaMovement(vectorToTarget.normalize().scale(1));
        arrow.setCritArrow(true);    //箭后面是否带有大量暴击粒子
        worldI.addFreshEntity(arrow);
    }
}
