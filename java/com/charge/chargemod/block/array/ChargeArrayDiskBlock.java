package com.charge.chargemod.block.array;

import com.charge.chargemod.ChargeModItemRegistry;
import com.charge.chargemod.block.ChargeAlchemyAnvilBlockEntity;
import com.charge.chargemod.block.ChargeAlchemyStoveBlockEntity;
import com.charge.chargemod.block.ChargeAltarBlockEntity;
import com.charge.chargemod.lingqi.PlayerLingQiHelper;
import com.charge.chargemod.multiBlock.ChargeArrayHelper;
import com.charge.chargemod.multiBlock.XianTianBaGua;
import com.charge.chargemod.network.ChargePacketSender;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import java.util.ArrayList;
import java.util.List;

//阵盘
public class ChargeArrayDiskBlock extends Block implements EntityBlock {
    //
    public ChargeArrayDiskBlock(Properties Properties) {
        super(Properties);
    }


    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ChargeAlchemyAnvilBlockEntity(pos, state);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!level.isClientSide) {
            ItemStack heldItem = player.getItemInHand(hand);
            if (heldItem.is(ChargeModItemRegistry.CHARGE_BASE_TOKEN.get())) {  //阵盘，检查手中令牌
                ItemStack stack = ChargeArrayHelper.outputWithItemList(pos, level); //直接获取检测
                if (!stack.isEmpty()) {
                    boolean canUse = PlayerLingQiHelper.consumeLingQi(player, 60);
                    if (!canUse) {
                        player.sendSystemMessage(Component.translatable("describe.charge.need_ling_li"));
                        return InteractionResult.sidedSuccess(level.isClientSide);
                    } else {
                        ChargePacketSender.sendLingqiMessageToClient((ServerPlayer) player, PlayerLingQiHelper.getLingQi(player));
                    }
                    //这里需一些粒子效果
                    ChargeArrayHelper.removeInputWithItem(stack, pos, level);   //破坏多的方块，一定要先执行，不然stack是会判空的
                    Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), stack); //生成凋落物
                    player.sendSystemMessage(Component.literal("阵法完成") .withStyle(ChatFormatting.AQUA));
                }
            }
        }
        return InteractionResult.sidedSuccess(level.isClientSide);
    }

}
