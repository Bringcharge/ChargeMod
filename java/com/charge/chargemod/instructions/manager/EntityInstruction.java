package com.charge.chargemod.instructions.manager;


import com.charge.chargemod.instructions.manager.entity.EntityManager;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

public class EntityInstruction {
    public static Entity parser(Instruction instruction, InstructionsModel owner) {    //输入口

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
        if (order != null && order.equals("E101#")) { //施术者
            return EntityManager.entityOfUser(owner);
        }

        if (order != null && order.equals("E201#")) {   //获取回调函数的entity
            return EntityManager.entityBlockTarget(owner);
        }

        if (order != null && order.equals("E301#")) {   //entity作为判定，从起点到终点的碰撞箱寻找碰到的livingEntity
            Vec3 p1 = InstructionsManager.vecWithString(instruction,owner);
            Vec3 p2 = InstructionsManager.vecWithString(instruction,owner);
            Entity p3 = InstructionsManager.entityWithString(instruction,owner);
            return EntityManager.entityLookTarget(p1, p2, p3, owner);
        }

        return unexpectedInput();
    }

    private static Entity unexpectedInput() {  //错误走这
        return null;
    }
}
