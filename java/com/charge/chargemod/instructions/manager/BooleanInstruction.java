package com.charge.chargemod.instructions.manager;


import com.charge.chargemod.instructions.manager.bool.BooleanManager;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

public class BooleanInstruction {
    public static boolean parser(Instruction instruction, InstructionsModel owner) {    //输入

        String order = null;
        for (int i = 0; i<instruction.str.length(); i++) {
            if (instruction.str.charAt(i) == '#') {
                order = instruction.str.substring(0, i+1);    //拿出命令
                if (i < instruction.str.length()) {
                    instruction.str = instruction.str.substring(i + 1); //设置新的值
                }
                break;
            }
        }

        if (order != null && order.equals("B001#")) {   //判断一个entity是不是空
            Entity p1 = InstructionsManager.entityWithString(instruction, owner);
            return BooleanManager.entityNotNull(p1, owner);
        }

        if (order != null && order.equals("B002#")) {   //判断一个vector是不是空
            Vec3 p1 = InstructionsManager.vecWithString(instruction, owner);
            return BooleanManager.vectorNotNull(p1, owner);
        }

        if (order != null && order.equals("B010#")) {   //判断两个int是否相等
            int p1 = InstructionsManager.integerWithString(instruction, owner);
            int p2 = InstructionsManager.integerWithString(instruction, owner);
            return BooleanManager.integerEquation(p1, p2, owner);
        }

        if (order != null && order.equals("B011#")) {   //判断两个double是否相等
            double p1 = InstructionsManager.doubleWithString(instruction, owner);
            double p2 = InstructionsManager.doubleWithString(instruction, owner);
            return BooleanManager.doubleEquation(p1, p2, owner);
        }

        return unexpectedInput();
    }

    private static boolean unexpectedInput() {  //��������
        return false;
    }
}
