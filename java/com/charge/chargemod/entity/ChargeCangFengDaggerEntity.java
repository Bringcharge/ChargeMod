package com.charge.chargemod.entity;

import com.charge.chargemod.ChargeModItemRegistry;
import com.charge.chargemod.instructions.manager.Instruction;
import com.charge.chargemod.instructions.manager.InstructionsManager;
import com.charge.chargemod.instructions.manager.InstructionsModel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

import java.util.function.BiConsumer;

public class ChargeCangFengDaggerEntity extends AbstractArrow  {
    public ChargeCangFengDaggerEntity(Level level, double x, double y, double z) {
        super(ChargeModItemRegistry.CHARGE_CANG_FENG_DAGGER_ENTITY_TYPE.get(), x, y, z, level);
    }
    public ChargeCangFengDaggerEntity(Level level, LivingEntity shooter) { super(ChargeModItemRegistry.CHARGE_CANG_FENG_DAGGER_ENTITY_TYPE.get(),shooter,level); }

    public ChargeCangFengDaggerEntity(EntityType<ChargeCangFengDaggerEntity> chargeCangFengDaggerEntity, Level level) {
        super(chargeCangFengDaggerEntity, level);
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        Entity entity = result.getEntity(); // 获取击中的实体
        Level level = this.level(); // 获取当前世界

        if (!level.isClientSide) {
            //构建搭载的术式

        }

        super.onHitEntity(result); // 调用父类逻辑
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(ChargeModItemRegistry.chargeBaseIngot.get());
    }

//    @Override
//    protected void onHitBlock(BlockHitResult result) {
//        Level level = this.level(); // 获取当前世界
//        BlockPos pos = result.getBlockPos(); // 获取击中方块的坐标
//        BlockState state = level.getBlockState(pos); // 获取方块状态
//        Vec3 vec3d = result.getLocation();
//
//        if (!level.isClientSide) {
//            //构建搭载的术式
//            Instruction instruction = new Instruction();
//            InstructionsModel instructionsModel = new InstructionsModel();
//            if (this.biConsumer != null) {
//                this.biConsumer.accept(instruction,instructionsModel);  //向外寻求参数
//                instructionsModel.targetVec = vec3d;    //附带额外参数
//                InstructionsManager.functionWithString(instruction, instructionsModel);  //调用函数
//            }
//        }
//
//        super.onHitBlock(result); // 调用父类逻辑
//    }
}
