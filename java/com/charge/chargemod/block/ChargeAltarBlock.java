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
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ChargeAltarBlock extends Block implements EntityBlock {

    private static VoxelShape ChargeAltarBlock_shape;

    static {
        VoxelShape base = Block.box(0, 0, 0, 16, 1, 16);    //6个参数分别是起始点的XYZ和结束点的XYZ
        VoxelShape column1 = Block.box(2, 1, 2, 3, 9, 3);       //而且第一个的xyz必须每一个都比第二个小
        VoxelShape column2 = Block.box(13, 1, 2, 14, 9, 3);
        VoxelShape column3 = Block.box(2, 1, 13, 3, 9, 14);
        VoxelShape column4 = Block.box(13, 1, 13, 14, 9, 14);
        VoxelShape roof1 = Block.box(0, 9, 0, 16, 10, 16);
        VoxelShape roof2 = Block.box(1, 10, 1, 15, 11, 15);
        VoxelShape roof3 = Block.box(2, 11, 2, 14, 12, 14);
        VoxelShape roof4 = Block.box(3, 12, 3, 13, 13, 13);
        VoxelShape roof5 = Block.box(4, 13, 4, 12, 14, 12);
        VoxelShape roof6 = Block.box(5, 14, 5, 11, 15, 11);
        VoxelShape roof7 = Block.box(6, 15, 6, 10, 16, 10);
//        VoxelShape roof8 = Block.box(7, 16, 7, 9, 17, 9);
//        ChargeAltarBlock_shape = Shapes.or(base, column1, column2, column3, column4, roof1, roof2, roof3, roof4, roof5, roof6, roof7);
        ChargeAltarBlock_shape = Shapes.or(base, column1, column2, column3, column4, roof1, roof2, roof3, roof4, roof5, roof6, roof7);
    }
//
    public ChargeAltarBlock(BlockBehaviour.Properties Properties) {
        super(Properties);
    }
    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return ChargeAltarBlock_shape;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ChargeAltarBlockEntity(pos, state);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!level.isClientSide) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof ChargeAltarBlockEntity) {
                ChargeAltarBlockEntity pedestal = (ChargeAltarBlockEntity) blockEntity; //java15模块不支持特殊写法，传统写法强制转换变量类型
                ItemStack heldItem = player.getItemInHand(hand);
                ItemStack pedestalItem = pedestal.getItem();    //祭坛里面的东西
                if (heldItem.isEmpty()) {
                    // 如果玩家手中没有物品，取出置物台上的物品

                    if (!pedestalItem.isEmpty()) {
                        player.getInventory().add(pedestalItem);
                        pedestal.setItem(ItemStack.EMPTY);
                    }
                } else {
                    if (!pedestal.getItem().isEmpty()) {    //如果里面有东西
                        player.getInventory().add(pedestalItem); //拿出原本的物品
                        pedestal.setItem(heldItem.split(1)); // 只放置一个物品
                    } else {
                        // 如果玩家手中有物品，放置到置物台上
                        pedestal.setItem(heldItem.split(1)); // 只放置一个物品
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
            if (blockentity instanceof ChargeAltarBlockEntity) {
                if (level instanceof ServerLevel) {
                    Containers.dropItemStack(level, blockPos.getX(), blockPos.getY(), blockPos.getZ(), ((ChargeAltarBlockEntity) blockentity).getItem());//获取内容物并掉落
                }
                //跟红石信号有关，可以不调用
//                level.updateNeighbourForOutputSignal(blockPos, this);
            }

            super.onRemove(state, level, blockPos, state2, flag);
        }
    }
}
