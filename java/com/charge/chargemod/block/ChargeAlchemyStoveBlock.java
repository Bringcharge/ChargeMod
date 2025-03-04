package com.charge.chargemod.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

//炼丹炉
public class ChargeAlchemyStoveBlock extends Block implements EntityBlock {

    //
    public ChargeAlchemyStoveBlock(BlockBehaviour.Properties Properties) {
        super(Properties);
    }


    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ChargeAlchemyStoveBlockEntity(pos, state);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!level.isClientSide) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof ChargeAltarBlockEntity) {
                ChargeAltarBlockEntity pedestal = (ChargeAltarBlockEntity) blockEntity; //java15模块不支持特殊写法，传统写法强制转换变量类型
                ItemStack heldItem = player.getItemInHand(hand);
                ItemStack pedestalItem = pedestal.getItem();    //祭坛里面的东西
                if (heldItem.isEmpty()) {   //TODO:改成使用令牌
                    //TODO： 加入判断多方快结构是否完整的代码

                } else {
                    if (!pedestal.getItem().isEmpty()) {    //如果里面有东西，拿出来
                        player.getInventory().add(pedestalItem);
                        pedestal.setItem(ItemStack.EMPTY);
                    }
                }
            }
        }
        return InteractionResult.sidedSuccess(level.isClientSide);
    }


    //被破坏时掉落物的情况
    public void onRemove(BlockState state, Level level, BlockPos blockPos, BlockState state2, boolean flag) {
        if (!state.is(state2.getBlock())) {
            BlockEntity blockentity = level.getBlockEntity(blockPos);
            if (blockentity instanceof ChargeAlchemyStoveBlockEntity) {
                if (level instanceof ServerLevel) {
                    Containers.dropItemStack(level, blockPos.getX(), blockPos.getY(), blockPos.getZ(), ((ChargeAlchemyStoveBlockEntity) blockentity).getItem());//获取内容物并掉落
                }
                //跟红石信号有关，可以不调用
//                level.updateNeighbourForOutputSignal(blockPos, this);
            }

            super.onRemove(state, level, blockPos, state2, flag);
        }
    }
}
