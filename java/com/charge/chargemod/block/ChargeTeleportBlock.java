package com.charge.chargemod.block;

import com.charge.chargemod.ChargeModItemRegistry;
import com.charge.chargemod.gui.TeleportEditScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
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
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class ChargeTeleportBlock extends Block implements EntityBlock {
    //
    public ChargeTeleportBlock(Properties Properties) {
        super(Properties);
    }


    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ChargeTeleportBlockEntity(pos, state);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {

        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof ChargeTeleportBlockEntity) {
            ChargeTeleportBlockEntity pedestal = (ChargeTeleportBlockEntity) blockEntity; //java15模块不支持特殊写法，传统写法强制转换变量类型
            ItemStack heldItem = player.getItemInHand(hand);
            if (heldItem.is(ChargeModItemRegistry.CHARGE_BASE_TOKEN.get())) {  //item正确，执行操作
                //TODO: 结构检查
                Vec3i vec3i = pedestal.getTargetVec();
                if (vec3i != null) {
                    player.teleportTo(vec3i.getX(), vec3i.getY(), vec3i.getZ());
                }
            } else {
                TeleportEditScreen screen = new TeleportEditScreen(Component.translatable("坐标定制"), pedestal);
                Minecraft.getInstance().setScreen(screen);  //添加页面
            }
        }

        return InteractionResult.sidedSuccess(level.isClientSide);
    }

}
