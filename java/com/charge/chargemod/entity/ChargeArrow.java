package com.charge.chargemod.entity;

import com.charge.chargemod.instructions.manager.Instruction;
import com.charge.chargemod.instructions.manager.InstructionsManager;
import com.charge.chargemod.instructions.manager.InstructionsModel;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

import java.util.function.BiConsumer;


public class ChargeArrow extends Arrow {
    public ChargeArrow(EntityType<? extends Arrow> type, Level level) {
        super(type, level);
    }
    public ChargeArrow(Level level, double x, double y, double z) {
        super(level,x,y,z);
    }
    public ChargeArrow(Level level, LivingEntity shooter) { super(level,shooter); }

    private BiConsumer<Instruction, InstructionsModel> biConsumer;
    //注：this.remove()可以移除该entity

    public void setBack(BiConsumer<Instruction, InstructionsModel>  biConsumer) {
        this.biConsumer = biConsumer;
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        Entity entity = result.getEntity(); // 获取击中的实体
        Level level = this.level(); // 获取当前世界

        if (!level.isClientSide) {
            //构建搭载的术式
            Instruction instruction = new Instruction();
            InstructionsModel instructionsModel = new InstructionsModel();
            if (this.biConsumer != null) {
                this.biConsumer.accept(instruction,instructionsModel);  //向外寻求参数
                instructionsModel.targetEntity = result.getEntity();    //附带额外参数
                instructionsModel.targetVec = result.getLocation();       //临时新增的参数
                InstructionsManager.functionWithString(instruction, instructionsModel); //调用函数
            }
        }

        super.onHitEntity(result); // 调用父类逻辑
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        Level level = this.level(); // 获取当前世界
        BlockPos pos = result.getBlockPos(); // 获取击中方块的坐标
        BlockState state = level.getBlockState(pos); // 获取方块状态
        Vec3 vec3d = result.getLocation();

        if (!level.isClientSide) {
            //构建搭载的术式
            Instruction instruction = new Instruction();
            InstructionsModel instructionsModel = new InstructionsModel();
            if (this.biConsumer != null) {
                this.biConsumer.accept(instruction,instructionsModel);  //向外寻求参数
                instructionsModel.targetVec = vec3d;    //附带额外参数
                InstructionsManager.functionWithString(instruction, instructionsModel);  //调用函数
            }
        }

        super.onHitBlock(result); // 调用父类逻辑
    }
}
