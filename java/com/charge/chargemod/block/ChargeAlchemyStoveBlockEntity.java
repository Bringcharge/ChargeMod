package com.charge.chargemod.block;
import com.charge.chargemod.ChargeModItemRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

//炼丹炉
public class ChargeAlchemyStoveBlockEntity extends BlockEntity {
    private ItemStack item = ItemStack.EMPTY;

    public ChargeAlchemyStoveBlockEntity(BlockPos pos, BlockState state) {
//        super(BlockEntityType.BELL,pos,state);
        super(ChargeModItemRegistry.CHARGE_ALCHEMY_STOVE_ENTITY.get(), pos, state);
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

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        item = ItemStack.of(tag.getCompound("charge_alchemy_stove_block_entity_item"));
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.put("charge_alchemy_stove_block_entity_item", item.save(new CompoundTag()));
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
