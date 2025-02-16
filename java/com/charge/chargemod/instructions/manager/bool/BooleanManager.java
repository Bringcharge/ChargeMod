package com.charge.chargemod.instructions.manager.bool;

import com.charge.chargemod.instructions.manager.InstructionsModel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

public class BooleanManager {
    //判断一个entity不是null
    public static boolean entityNotNull (Entity entity, InstructionsModel model) {    //B001#
       return entity!=null;
    }

    //判断一个vector不是null
    public static boolean vectorNotNull (Vec3 vec3d, InstructionsModel model) {    //B001#
        return vec3d.normalize() != null;
    }

    //判断int相等
    public static boolean integerEquation (int a1, int a2, InstructionsModel model) {    //B010#
        return a1 == a2;
    }

    public static boolean doubleEquation (double a1, double a2, InstructionsModel model) {    //B011#
        return Math.abs((float) (a1 - a2)) < 0.0001F;
    }
}
