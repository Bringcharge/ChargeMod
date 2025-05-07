package com.charge.chargemod.block.array;

import com.charge.chargemod.ChargeModItemRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

//tick响应的blockEntity，用于计数tick和修改state
public class ChargeTickBlockEntity extends BlockEntity {
    private Vec3i targetVec = null;
    private int tick;
    private int state;

    public ChargeTickBlockEntity(BlockPos pos, BlockState state) {
//        super(BlockEntityType.BELL,pos,state);
        super(ChargeModItemRegistry.CHARGE_TICK_ENTITY.get(), pos, state);
    }



    public int getState() {
        return state;
    }

    public void setState(int state1) {
        this.state = state1;
        setChanged(); // 标记方块实体为已更改
        if (level != null) {
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3); // 更新客户端
        }
    }

    public int getTick() {
        return tick;
    }

    public void setTick(int tick) {
        this.tick = tick;
    }

    public Vec3i getTargetVec() {
        return targetVec;
    }
    public void setTargetVec(Vec3i vec) {
        this.targetVec = vec;
        setChanged(); // 标记方块实体为已更改
        if (level != null) {
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3); // 更新客户端
        }
    }
    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        CompoundTag tag1 = tag.getCompound("charge_tick_block_entity_target");
        if (tag1 != null) {
            tick = tag1.getInt("tick");
            state = tag1.getInt("state");
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
//        tag.put("charge_alchemy_anvil_block_entity_item", item.save(new CompoundTag()));
        CompoundTag sub_tag = new CompoundTag();
        if (this.targetVec != null) {
            sub_tag.putInt("tick", this.targetVec.getX());
            sub_tag.putInt("state", this.targetVec.getY());
            tag.put("charge_tick_block_entity_target", sub_tag);
        }
    }

    @Override
    public CompoundTag getUpdateTag() {
        return saveWithoutMetadata();
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        load(pkt.getTag());
    }
}
