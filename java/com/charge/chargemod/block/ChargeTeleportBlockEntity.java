package com.charge.chargemod.block;
import com.charge.chargemod.ChargeModItemRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

//炼器铁砧
public class ChargeTeleportBlockEntity extends BlockEntity {
    private ItemStack item = ItemStack.EMPTY;
    private Vec3i targetVec = null;

    public ChargeTeleportBlockEntity(BlockPos pos, BlockState state) {
//        super(BlockEntityType.BELL,pos,state);
        super(ChargeModItemRegistry.CHARGE_TELEPORT_ENTITY.get(), pos, state);
    }



    public ItemStack getItem() {
        return item;
    }

    public void setItem(ItemStack item) {
        this.item = item;
        setChanged(); // 标记方块实体为已更改
        if (level != null) {
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3); // 更新客户端
        }
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
        CompoundTag tag1 = tag.getCompound("charge_teleport_block_entity_target");
        if (tag1 != null) {
            int x = tag1.getInt("vec_x");
            int y = tag1.getInt("vec_y");
            int z = tag1.getInt("vec_z");
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
//        tag.put("charge_alchemy_anvil_block_entity_item", item.save(new CompoundTag()));
        CompoundTag sub_tag = new CompoundTag();
        if (this.targetVec != null) {
            sub_tag.putInt("vec_x", this.targetVec.getX());
            sub_tag.putInt("vec_y", this.targetVec.getY());
            sub_tag.putInt("vec_z", this.targetVec.getZ());
            tag.put("charge_teleport_block_entity_target", sub_tag);
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
