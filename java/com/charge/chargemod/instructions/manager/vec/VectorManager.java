package com.charge.chargemod.instructions.manager.vec;


import com.charge.chargemod.instructions.manager.InstructionsModel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class VectorManager {
    //v00系列是处理
    public static Vec3 vecRevert(Vec3 vec1, InstructionsModel model) { //V001_ 取反
        return vec1.reverse();
    }

    public static Vec3 vecScale(Vec3 vec1, double double1, InstructionsModel model) {   //V002_ 缩放
        return vec1.scale(double1);
    }

    public static Vec3 vecNormalize(Vec3 vec1, InstructionsModel model) {     //V003_ 标准向量
        return  vec1.normalize();
    }

    //v01开始的是四则运算
    public static Vec3 vecAdd(Vec3 vec1, Vec3 vec2, InstructionsModel model) {   //V011_ 向量加法
        return vec1.add(vec2);
    }

    public static Vec3 vecDec(Vec3 vec1, Vec3 vec2, InstructionsModel model) {   //V012_ 向量减法 虽然没什么意义的减法，提供一个工具罢了
        return  vec1.add(vec2.reverse());
    }

    //V02是阻挡射线
    public static Vec3 vecRayToBlockPos(Vec3 vec1, Vec3 vec2, InstructionsModel model) {  //V020_ 从vec1发出向量vec2的射线，看到的方块中心，太远了的话，就miss
        Level worldIn = model.user.level();
        if (worldIn != null && model.user != null) {
            if (vec2.length() < 50);
            Vec3 vec3 = vec2.scale(50 / vec2.length());
            Vec3 vec_to = vec3.add(vec1);
            //查看阻塞位置
            ClipContext context = new ClipContext(vec1, vec_to, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, model.user);//前两个参数是起点和终点坐标
            BlockHitResult blockRayTraceResult = worldIn.clip(context);
            if (blockRayTraceResult.getType() == HitResult.Type.BLOCK) {
                return blockRayTraceResult.getBlockPos().getCenter();
            } else {
                return null;
            }
        }
        return null;
    }

    //V1开始是跟玩家相关的操作
    public static Vec3 vecPlayerLook(InstructionsModel model) {    //V101_ 玩家视线
        if (model.user != null) {
            return model.user.getLookAngle().normalize();
        }
        return null;
    }

    public static Vec3 vecPlayerPos(InstructionsModel model) {     //V102_ 玩家位置
        if (model.user != null) {
            return model.user.getPosition(0.5f);
        }
        return null;
    }

    public static Vec3 vecCameraPos(InstructionsModel model) {     //V103_ 摄像机位置
        if (model.user != null) {
            return model.user.getEyePosition();
        }
        return null;
    }

    //V2是一些回调系列的操作，还有其他的操作
    public static Vec3 vecBlockTarget (InstructionsModel model) {  //V201_ 回调函数附带的参数
            return model.targetVec; //可能是空
    }

    // V3是一些跟entity有关的向量
    public static Vec3 vecEntityPos (Entity entity, InstructionsModel model) { //V301_ 实体位置
        if (entity != null) {
            return entity.getPosition(0.5f);
        }
        return null;
    }
}
