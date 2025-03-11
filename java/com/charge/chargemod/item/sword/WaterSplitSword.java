package com.charge.chargemod.item.sword;

import net.minecraft.core.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.commands.DamageCommand;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageEffects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.Vec3;

public class WaterSplitSword  extends ChargeBaseSword {
    @Override
    public boolean skillWithEntity(LivingEntity entity, Player user, InteractionHand hand) { //右键击中了怪物的函数，最高优先级
        if (!user.level().isClientSide) {
            hurtEntity(entity, user);
        }
        return true;
    }

    @Override
    public boolean skillWithNone(Player user, InteractionHand hand) { //右键什么都没有击中，最低的优先级
        if (!user.level().isClientSide) {
            Vec3 place = user.getPosition(1.0f);
            Vec3i position = new Vec3i((int) Math.floor(place.x), (int) Math.floor(place.y), (int) Math.floor(place.z));
            BlockPos pos = new BlockPos(position);
            removeWaterBreadthFirstSearch(user.level(), pos);   //找到玩家位置
        }
        return true;
    }

    private void hurtEntity(LivingEntity entity, Player player) {
        RegistryAccess registryAccess = player.level().registryAccess();

        // 获取 DamageType 注册表
        Registry<DamageType> damageTypeRegistry = registryAccess.registryOrThrow(Registries.DAMAGE_TYPE);

        // 获取 DamageTypes.MAGIC 的 Holder
        Holder<DamageType> magicDamageTypeHolder = damageTypeRegistry.getHolderOrThrow(DamageTypes.MAGIC);

        // 创建 DamageSource
        DamageSource damageSource = new DamageSource(magicDamageTypeHolder,player,player);
        entity.hurt(damageSource,18);
    }

    private boolean removeWaterBreadthFirstSearch(Level level, BlockPos blockPos) {
        BlockState spongeState = level.getBlockState(blockPos);
        //返回了一个广搜，中心是blockPos，第二个函数是搜索树的层数，可以简单理解为曼哈顿距离，第三个函数是处理上限
        //第四个函数是加入队列的条件，也就是搜索树产生子节点规则。最后一个则是筛选条件，复合筛选的总数小于等于第三个参数
        return BlockPos.breadthFirstTraversal(blockPos, 4, 106, (pos, consumer) -> {
            for(Direction direction : Direction.values()) { //上下东南西北
                consumer.accept(pos.relative(direction));
            }
        }, (pos1) -> {
            if (pos1.equals(blockPos)) {
                return true;
            } else {
                BlockState blockstate = level.getBlockState(pos1);
                FluidState fluidstate = level.getFluidState(pos1);
                if (!spongeState.canBeHydrated(level, blockPos, fluidstate, pos1)) {    //如果方块不能包含水
                    return false;
                } else {
                    Block block = blockstate.getBlock();
                    if (block instanceof BucketPickup) {    //如果是水桶能舀水的
                        BucketPickup bucketpickup = (BucketPickup)block;
                        if (!bucketpickup.pickupBlock(level, pos1, blockstate).isEmpty()) {//能舀水成功
                            return true;
                        }
                    }

                    return true;
                }
            }
        }) > 1;
    }
}
