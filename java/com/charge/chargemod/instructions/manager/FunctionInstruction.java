package com.charge.chargemod.instructions.manager;


import com.charge.chargemod.instructions.manager.fucntion.FunctionsManager;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

public class FunctionInstruction {
    public static void parser(Instruction instruction, InstructionsModel owner) {    //输入口

        String order = null;
        for (int i = 0; i<instruction.str.length(); i++) {
            if (instruction.str.charAt(i) == '_') {
                order = instruction.str.substring(0, i+1);    //拿出命令
                if (i < instruction.str.length()) {
                    instruction.str = instruction.str.substring(i + 1); //设置新的值
                }
                break;
            }
        }

        if (order != null && order.equals("F001_")) {   //落雷
            Vec3 p1 = InstructionsManager.vecWithString(instruction,owner);
            if (p1 == null) {
                return;
            }
            FunctionsManager.lightningDown(p1,owner);
            return;
        }

        if (order != null && order.equals("F002_")) {   //射箭
            Vec3 p1 = InstructionsManager.vecWithString(instruction,owner);
            Vec3 p2 = InstructionsManager.vecWithString(instruction,owner);
            double p3 = InstructionsManager.doubleWithString(instruction,owner);
            String p4 = InstructionsManager.instructionsBlock(instruction,owner);
            if (p1 == null || p2 == null) {
                return;
            }
            FunctionsManager.ShotArrow(p1,p2,p3,p4,owner);
        }

        if (order != null && order.equals("F003_")) {   //瞬移
            Entity p1 = InstructionsManager.entityWithString(instruction, owner);
            Vec3 p2 = InstructionsManager.vecWithString(instruction, owner);
            if (p1 instanceof LivingEntity) {
                FunctionsManager.deliver((LivingEntity) p1, p2, owner);
            }
        }

        if (order != null && order.equals("F004_")) {   //交换
            Entity p1 = InstructionsManager.entityWithString(instruction, owner);
            Entity p2 = InstructionsManager.entityWithString(instruction, owner);
            if (p1 instanceof LivingEntity) {
                FunctionsManager.switchEntity((LivingEntity) p1,(LivingEntity) p2, owner);
            }
        }

        if (order != null && order.equals("F005_")) {   //破坏方块
            Vec3 p1 = InstructionsManager.vecWithString(instruction,owner);
            if (p1 == null) {
                return;
            }
            FunctionsManager.breakBlock(p1, owner);
        }

        if (order != null && order.equals("Fif_")) {
            boolean p1 = InstructionsManager.booleanWithString(instruction, owner);
            String p2 = InstructionsManager.instructionsBlock(instruction, owner);
            String p3 = InstructionsManager.instructionsBlock(instruction, owner);
            FunctionsManager.logicIf(p1,p2,p3,owner);
        }
        /*
        ...更多的函数
         */
        if (order!=null && instruction.str.length() > 0) {  //如果解析成功，就继续执行函数。否则可能进入死循环
            InstructionsManager.functionWithString(instruction,owner);
        }
//        unexpectedInput();
    }

    private static void unexpectedInput() {  //所有的错误走这

    }
}
