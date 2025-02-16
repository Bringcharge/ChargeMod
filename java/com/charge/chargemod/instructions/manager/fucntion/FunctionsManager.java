package com.charge.chargemod.instructions.manager.fucntion;


import com.charge.chargemod.entity.ChargeArrow;
import com.charge.chargemod.instructions.manager.Instruction;
import com.charge.chargemod.instructions.manager.InstructionsManager;
import com.charge.chargemod.instructions.manager.InstructionsModel;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;


public class FunctionsManager {
    //落雷
    public static void lightningDown (Vec3 targetPlace, InstructionsModel model) {    //F001#
        Level worldIn = model.user.level();
        if (worldIn != null && worldIn instanceof ServerLevel) {
            LightningBolt lightning = new LightningBolt(EntityType.LIGHTNING_BOLT, worldIn);
            lightning.moveTo(targetPlace);
            ((ServerLevel) worldIn).addFreshEntity(lightning);
        }
    }

    //射箭
    public static void ShotArrow(Vec3 start, Vec3 direction, double speed, String blockInstructions, InstructionsModel model) { //F002#
        Level worldIn = model.user.level();
        if (worldIn != null && worldIn instanceof ServerLevel) {
            ChargeArrow chargeArrow = new ChargeArrow(worldIn, start.x(), start.y(), start.z());
            chargeArrow.setBack((instruction,instructionsModel)->{
                //参数注入
                instruction.str = blockInstructions;
                instructionsModel.copy(model);
            });
            chargeArrow.shoot(direction.x(),direction.y(),direction.z(), (float) speed,0.f);
            worldIn.addFreshEntity(chargeArrow);
        }
    }

    //传送，把entity传送到targetPlace
    public static void deliver(LivingEntity livingEntity, Vec3 targetPlace, InstructionsModel model) { //#F003
        if (livingEntity==null) {
            return;
        }
        Level world = livingEntity.level();
        if (!world.isClientSide()) {
            livingEntity.teleportTo(targetPlace.x(), targetPlace.y(), targetPlace.z());
            livingEntity.fallDistance = 0.0F;
        }
    }

    //交换两个entity的位置
    public static void switchEntity(LivingEntity livingEntity1, LivingEntity livingEntity2, InstructionsModel model) { //#F004
        if (livingEntity1 == null || livingEntity2 == null) {
            return;
        }
        Level world = livingEntity1.level();

        if (!world.isClientSide()) {
            Vec3 place1 = livingEntity1.getPosition(0.5f);
            Vec3 place2 = livingEntity2.getPosition(0.5f);  //先获取目标位置，两个实体进行交换
            livingEntity1.teleportTo(place2.x(), place2.y(), place2.z());
            livingEntity1.fallDistance = 0.0F;
            livingEntity2.teleportTo(place1.x(), place1.y(), place1.z());
            livingEntity2.fallDistance = 0.0F;
        }
    }

    //if函数
    public static void logicIf(boolean condition, String thenCondition, String elseCondition,InstructionsModel model) { //Fif#
        InstructionsModel newModel = new InstructionsModel();
        newModel.copy(model);
        Instruction instruction = new Instruction();

        if (condition) {    //if 后面的条件是true
            instruction.str = thenCondition;
            InstructionsManager.functionWithString(instruction,newModel);
        }else {
            instruction.str = elseCondition;
            InstructionsManager.functionWithString(instruction,newModel);
        }
    }
}


