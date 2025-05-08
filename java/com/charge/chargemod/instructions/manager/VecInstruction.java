package com.charge.chargemod.instructions.manager;


import com.charge.chargemod.instructions.manager.vec.VectorManager;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

public class VecInstruction {
    public static Vec3 parser(Instruction instruction, InstructionsModel owner) {    //输入口

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

        if (order != null && order.equals("V001_")) {   //向量取反
            Vec3 p1 = InstructionsManager.vecWithString(instruction,owner);
            if (p1 == null) {
                return  null;
            }
            return VectorManager.vecRevert(p1,owner);
        }

        if (order != null && order.equals("V002_")) {   //向量缩放
            Vec3 p1 = InstructionsManager.vecWithString(instruction,owner);
            int p2 = InstructionsManager.integerWithString(instruction,owner);
            if (p1 == null) {
                return  null;
            }
            return VectorManager.vecScale(p1,p2,owner);
        }

        if (order != null && order.equals("V003_")) {   //向量缩放
            Vec3 p1 = InstructionsManager.vecWithString(instruction,owner);
            if (p1 == null) {
                return  null;
            }
            return VectorManager.vecNormalize(p1, owner);
        }

        if (order != null && order.equals("V011_")) {   //向量加法
            Vec3 p1 = InstructionsManager.vecWithString(instruction,owner);
            Vec3 p2 = InstructionsManager.vecWithString(instruction,owner);
            if (p1 == null || p2 == null) {
                return  null;
            }
            return VectorManager.vecAdd(p1,p2,owner);
        }

        if (order != null && order.equals("V012_")) {   //向量减法
            Vec3 p1 = InstructionsManager.vecWithString(instruction,owner);
            Vec3 p2 = InstructionsManager.vecWithString(instruction,owner);
            if (p1 == null || p2 == null) {
                return  null;
            }
            return VectorManager.vecDec(p1,p2,owner);
        }

        if (order != null && order.equals("V020_")) {
            Vec3 p1 = InstructionsManager.vecWithString(instruction,owner);
            Vec3 p2 = InstructionsManager.vecWithString(instruction,owner);
            if (p1 == null || p2 == null) {
                return  null;
            }
            return VectorManager.vecRayToBlockPos(p1,p2,owner);
        }

        if (order != null && order.equals("V101_")) {   //玩家视线
            return VectorManager.vecPlayerLook(owner);
        }

        if (order != null && order.equals("V102_")) {   //玩家位置
            return VectorManager.vecPlayerPos(owner);
        }

        if (order != null && order.equals("V103_")) {   //摄像机位置
            return VectorManager.vecCameraPos(owner);
        }

        if (order != null && order.equals("V201_")) {   //block函数里的位置
            return VectorManager.vecBlockTarget(owner);
        }

        if (order != null && order.equals("V301_")) {   //实体位置
            Entity p1 = InstructionsManager.entityWithString(instruction,owner);
            return VectorManager.vecEntityPos(p1, owner);
        }
        return unexpectedInput();
    }

    private static Vec3 unexpectedInput() {  //所有的错误走这
        return null;
    }
}
