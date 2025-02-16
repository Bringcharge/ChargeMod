package com.charge.chargemod.instructions.manager.entity;


import com.charge.chargemod.instructions.manager.InstructionsModel;
import net.minecraft.world.entity.Entity;

public class EntityManager {
    //user实体
    public static Entity entityOfUser (InstructionsModel model) {    //E101#
        return model.user;
    }
    //从block中取出实体
    public static Entity entityBlockTarget (InstructionsModel model) {    //E201#
        return model.targetEntity;
    }
}
