package com.charge.chargemod.block.array;

import com.charge.chargemod.ChargeModItemRegistry;
import com.charge.chargemod.gui.TeleportEditScreen;
import com.charge.chargemod.particle.ChargeModParticleType;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.network.chat.Component;
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
import net.minecraft.world.phys.Vec3;

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
                Vec3i vec3i = pedestal.getTargetVec();
                if (vec3i != null) {
                    //检测代码，看背包里的灵石够不够
                    ItemStack maxStack =null;
                    for(int i = 0; i < player.getInventory().getContainerSize(); ++i) {
                        ItemStack itemstack1 = player.getInventory().getItem(i);
                        if (itemstack1.is(ChargeModItemRegistry.chargeLingShi.get())) {    //背包里有物品，增加渲染
                            if (maxStack == null || maxStack.getCount() < itemstack1.getCount()) {
                                maxStack = itemstack1;
                            }
                        }
                    }
                    if ( maxStack == null) {
                        player.sendSystemMessage(Component.literal("未检测到灵石").withStyle(ChatFormatting.AQUA));
                        return InteractionResult.sidedSuccess(level.isClientSide);
                    }
                    double distance = pos.getCenter().distanceTo(new Vec3(vec3i.getX(), vec3i.getY(), vec3i.getZ()));
                    int k = (int) (distance / 10);
                    if (k > 64) {
                        player.sendSystemMessage(Component.literal("超出传送距离").withStyle(ChatFormatting.AQUA));
                        return InteractionResult.sidedSuccess(level.isClientSide);
                    }
                    if (k <= maxStack.getCount()) {
                        maxStack.shrink(k);    //消耗弓箭
                        if (maxStack.isEmpty()) {
                            player.getInventory().removeItem(maxStack);
                        }
                    } else {
                        player.sendSystemMessage(Component.literal("灵石不足，需要至少"+k+"灵石").withStyle(ChatFormatting.AQUA));
                        return InteractionResult.sidedSuccess(level.isClientSide);
                    }
                    player.teleportTo(vec3i.getX(), vec3i.getY(), vec3i.getZ());
                }
            } else {
                if (level.isClientSide) {
                    TeleportEditScreen screen = new TeleportEditScreen(Component.translatable("坐标定制"), pedestal);
                    Minecraft.getInstance().setScreen(screen);  //添加页面
                }
            }
        }

        return InteractionResult.sidedSuccess(level.isClientSide);
    }

}
