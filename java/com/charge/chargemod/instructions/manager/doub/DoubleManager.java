package com.charge.chargemod.instructions.manager.doub;


import com.charge.chargemod.instructions.manager.InstructionsModel;
import net.minecraft.world.phys.Vec3;

public class DoubleManager {

    //D00x 四则运算
    public static double add (double a1, double a2, InstructionsModel model) { //D001#
        return a1 + a2;
    }

    public static double dec (double a1, double a2, InstructionsModel model) { //D002#
        return a1 - a2;
    }

    public static double mult (double a1, double a2, InstructionsModel model) { //D003#
        return a1 * a2;
    }

    public static double div (double a1, double a2, InstructionsModel model) { //D004#
        return a1 / a2;
    }
    //D01x类型转换
    public static double intToDouble (int a1, InstructionsModel model) { //D100#
        return (double) a1;
    }

    public static double getX (Vec3 a1, InstructionsModel model) { //D101#
        return a1.x();
    }

    public static double getY (Vec3 a1, InstructionsModel model) { //D102#
        return a1.y();
    }

    public static double getZ (Vec3 a1, InstructionsModel model) { //D103#
        return a1.z();
    }

}
