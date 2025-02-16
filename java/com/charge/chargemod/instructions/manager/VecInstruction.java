package com.charge.chargemod.instructions.manager;


import com.charge.chargemod.instructions.manager.vec.VectorManager;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

public class VecInstruction {
    public static Vec3 parser(Instruction instruction, InstructionsModel owner) {    //�����

        String order = null;
        for (int i = 0; i<instruction.str.length(); i++) {
            if (instruction.str.charAt(i) == '#') {
                order = instruction.str.substring(0, i+1);    //�ó�����
                if (i < instruction.str.length()) {
                    instruction.str = instruction.str.substring(i + 1); //�����µ�ֵ
                }
                break;
            }
        }

        if (order != null && order.equals("V001#")) {   //����ȡ��
            Vec3 p1 = InstructionsManager.vecWithString(instruction,owner);
            if (p1 == null) {
                return  null;
            }
            return VectorManager.vecRevert(p1,owner);
        }

        if (order != null && order.equals("V002#")) {   //��������
            Vec3 p1 = InstructionsManager.vecWithString(instruction,owner);
            int p2 = InstructionsManager.integerWithString(instruction,owner);
            if (p1 == null) {
                return  null;
            }
            return VectorManager.vecScale(p1,p2,owner);
        }

        if (order != null && order.equals("V003#")) {   //��������
            Vec3 p1 = InstructionsManager.vecWithString(instruction,owner);
            if (p1 == null) {
                return  null;
            }
            return VectorManager.vecNormalize(p1, owner);
        }

        if (order != null && order.equals("V011#")) {   //�����ӷ�
            Vec3 p1 = InstructionsManager.vecWithString(instruction,owner);
            Vec3 p2 = InstructionsManager.vecWithString(instruction,owner);
            if (p1 == null || p2 == null) {
                return  null;
            }
            return VectorManager.vecAdd(p1,p2,owner);
        }

        if (order != null && order.equals("V012#")) {   //��������
            Vec3 p1 = InstructionsManager.vecWithString(instruction,owner);
            Vec3 p2 = InstructionsManager.vecWithString(instruction,owner);
            if (p1 == null || p2 == null) {
                return  null;
            }
            return VectorManager.vecDec(p1,p2,owner);
        }

        if (order != null && order.equals("V101#")) {   //�������
            return VectorManager.vecPlayerLook(owner);
        }

        if (order != null && order.equals("V102#")) {   //���λ��
            return VectorManager.vecPlayerPos(owner);
        }

        if (order != null && order.equals("V103#")) {   //�����λ��
            return VectorManager.vecCameraPos(owner);
        }

        if (order != null && order.equals("V201#")) {   //block�������λ��
            return VectorManager.vecBlockTarget(owner);
        }

        if (order != null && order.equals("V301#")) {   //ʵ��λ��
            Entity p1 = InstructionsManager.entityWithString(instruction,owner);
            return VectorManager.vecEntityPos(p1, owner);
        }
        return unexpectedInput();
    }

    private static Vec3 unexpectedInput() {  //���еĴ�������
        return null;
    }
}
