package com.charge.chargemod.block;

import com.charge.chargemod.effect.ModEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class ChargeJuLingBlock extends Block implements EntityBlock {
    //
    public static final BooleanProperty ACTIVITY = BooleanProperty.create("activity");    //创建基本状态
    public ChargeJuLingBlock(Properties Properties) {
        super(Properties);
        this.registerDefaultState(this.defaultBlockState().setValue(ACTIVITY,false));
    }


    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ChargeTickBlockEntity(pos, state);
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource randomSource) {

        super.tick(state, level, pos, randomSource);
        BlockEntity blockentity = level.getBlockEntity(pos);
        if (blockentity instanceof ChargeTickBlockEntity) {
            ChargeTickBlockEntity tickBlockEntity = (ChargeTickBlockEntity)blockentity;
            int tick = tickBlockEntity.getTick();
            if (tick % 20 == 0) {   //tick数据符合条件
                // TODO: 结构检查，并且修改状态
                boolean checkStruck = true; //假设检测成功
                if (checkStruck) {
                    level.setBlock(pos, state.setValue(ACTIVITY,true), 3); //最后一个是flag
                } else {
                    level.setBlock(pos, state.setValue(ACTIVITY,false), 3); //最后一个是flag
                }
                List<ServerPlayer> playerList = level.getPlayers((serverPlayer -> true));   //用户list
                for (ServerPlayer player : playerList) {
                    Vec3 playerPosition = player.getPosition(1.f);
                    double distance = playerPosition.distanceTo(pos.getCenter());
                    if (distance < 10) {
                        player.addEffect(new MobEffectInstance(ModEffects.LING_QI_INCREASE.get(), 20 * 3, 2));  //增加恢复速度，2级
                    }
                }
            }
            tick ++;
            tickBlockEntity.setTick(tick);
        }

    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(ACTIVITY);
    }

}
