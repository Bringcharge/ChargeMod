package com.charge.chargemod.block.array;

import com.charge.chargemod.ChargeModItemRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

//tick响应的blockEntity，用于计数tick和修改state
public class ChargeJuLingBlockEntity extends ChargeTickBlockEntity {

    public ChargeJuLingBlockEntity(BlockPos pos, BlockState state) {
        super(ChargeModItemRegistry.CHARGE_JU_LING_BLOCK_ENTITY.get(), pos, state);
    }

}
