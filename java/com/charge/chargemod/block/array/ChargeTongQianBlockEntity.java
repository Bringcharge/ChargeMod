package com.charge.chargemod.block.array;

import com.charge.chargemod.ChargeModItemRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

//tick响应的blockEntity，用于计数tick和修改state
public class ChargeTongQianBlockEntity extends ChargeTickBlockEntity {

    public ChargeTongQianBlockEntity(BlockPos pos, BlockState state) {
        super(ChargeModItemRegistry.CHARGE_TONG_QIAN_BLOCK_ENTITY.get(), pos, state);
    }

}
