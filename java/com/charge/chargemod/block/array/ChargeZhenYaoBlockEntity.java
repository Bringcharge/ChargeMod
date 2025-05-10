package com.charge.chargemod.block.array;

import com.charge.chargemod.ChargeModItemRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

//tick响应的blockEntity，用于计数tick和修改state
public class ChargeZhenYaoBlockEntity extends ChargeTickBlockEntity {

    public ChargeZhenYaoBlockEntity(BlockPos pos, BlockState state) {
        super(ChargeModItemRegistry.CHARGE_ZHEN_YAO_ENTITY.get(), pos, state);
    }

}
