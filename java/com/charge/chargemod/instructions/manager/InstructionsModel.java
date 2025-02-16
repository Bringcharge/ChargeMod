package com.charge.chargemod.instructions.manager;


import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.phys.Vec3;

public class InstructionsModel {    //一个Model,用于存放各种需要传入的数据结构
    public Entity user;
    public Item holder;
    //����ֵ
    public Entity targetEntity;
    public Vec3 targetVec;
    //

    public void copy(InstructionsModel oldModel) { //仅仅返回一个属性相同的实例，但是实例不属于同一个指针
        this.holder = oldModel.holder;
        this.user = oldModel.user;
        this.targetEntity = oldModel.targetEntity;
        this.targetVec = oldModel.targetVec;
    }
}
