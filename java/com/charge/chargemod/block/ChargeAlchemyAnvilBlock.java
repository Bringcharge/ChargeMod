package com.charge.chargemod.block;

import com.charge.chargemod.ChargeModItemRegistry;
import com.charge.chargemod.multiBlock.XianTianBaGua;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import java.util.ArrayList;
import java.util.List;

public class ChargeAlchemyAnvilBlock extends Block implements EntityBlock {
    //
    public ChargeAlchemyAnvilBlock(BlockBehaviour.Properties Properties) {
        super(Properties);
    }


    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ChargeAlchemyAnvilBlockEntity(pos, state);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!level.isClientSide) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof ChargeAlchemyAnvilBlockEntity) {
                ChargeAlchemyAnvilBlockEntity pedestal = (ChargeAlchemyAnvilBlockEntity) blockEntity; //java15模块不支持特殊写法，传统写法强制转换变量类型
                ItemStack heldItem = player.getItemInHand(hand);
                ItemStack pedestalItem = pedestal.getItem();    //炉子里面的东西
                if (!pedestal.getItem().isEmpty()) {    //里面有东西
                    player.getInventory().add(pedestalItem);
                    pedestal.setItem(ItemStack.EMPTY);
                } else if (heldItem.is(ChargeModItemRegistry.CHARGE_BASE_TOKEN.get())) {  //空的炉子，检查手中令牌
                    XianTianBaGua check = new XianTianBaGua();  //检测函数，可惜就是每次都要构建一遍，不想做成static
                    BlockPos blockPos = check.isCompleted(level, pos);  //检查方块是否完整

                    if (blockPos == null) { //完整
                        player.sendSystemMessage(Component.literal("多方快结构完整")
                                .withStyle(ChatFormatting.AQUA));

                        List<ItemStack> list = new ArrayList<ItemStack>();
                        for (int i = 0; i< 8; i++) {   //获取8个祭坛的东西
                            BlockPos altarPos = check.getAltarWithIndex(i,pos);
                            ItemStack stack = ((ChargeAltarBlockEntity)(level.getBlockEntity(altarPos))).getItem();
                            list.add(stack);
                        }
//                        ItemStack item = ChargeAlchemyAnvilHelper.outputWithItemList(list); //获取炼丹的结果，因为数量也在里面
//                        if (!item.isEmpty()) {  //输出成功
//                            pedestal.setItem(item); //直接设置在输出口
//                            check.cleanAltar(level, pos);   //清空所有的祭坛
//                            //TODO 增加粒子效果
//                        }

                    } else {  //不完整
                        player.sendSystemMessage(Component.literal("多方快结构破损 x：" + blockPos.getX() + " y：" + blockPos.getY() + " z：" + blockPos.getY())
                                .withStyle(ChatFormatting.AQUA));
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
            if (blockentity instanceof ChargeAlchemyAnvilBlockEntity) {
                if (level instanceof ServerLevel) {
                    Containers.dropItemStack(level, blockPos.getX(), blockPos.getY(), blockPos.getZ(), ((ChargeAlchemyAnvilBlockEntity) blockentity).getItem());//获取内容物并掉落
                }
                //跟红石信号有关，可以不调用
//                level.updateNeighbourForOutputSignal(blockPos, this);
            }

            super.onRemove(state, level, blockPos, state2, flag);
        }
    }
}
