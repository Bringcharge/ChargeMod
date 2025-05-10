package com.charge.chargemod.block.array;

import com.charge.chargemod.ChargeModItemRegistry;
import com.charge.chargemod.effect.ModEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ChargeJuLingBlock extends ChargeTickBaseBlock implements EntityBlock {
    //
    public static final BooleanProperty ACTIVITY = BooleanProperty.create("activity");    //创建基本状态
    public ChargeJuLingBlock(Properties Properties) {
        super(Properties);
        this.registerDefaultState(this.defaultBlockState().setValue(ACTIVITY,false));
    }


    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ChargeJuLingBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> entityType) {
        return level.isClientSide ? null : createTickerHelper(entityType, ChargeModItemRegistry.CHARGE_JU_LING_BLOCK_ENTITY.get(), ChargeJuLingBlockEntity::serverTick);
    }

    @Override
    public void everyTick(BlockState state, Level level, BlockPos pos) {
        BlockEntity blockentity = level.getBlockEntity(pos);
        if (blockentity instanceof ChargeTickBlockEntity && level instanceof ServerLevel) {
            ChargeTickBlockEntity tickBlockEntity = (ChargeTickBlockEntity)blockentity;
            int tick = tickBlockEntity.getTick();
            if ((tick % 20 == 0 && state.getValue(ACTIVITY) )|| tick % (20 * 3) == 0) {   //tick数据符合条件
                boolean checkPlayer = false; //假设检测成功
                List<ServerPlayer> playerList = ((ServerLevel)level).getPlayers((serverPlayer -> true));   //用户list
                for (ServerPlayer player : playerList) {
                    Vec3 playerPosition = player.getPosition(1.f);
                    double distance = playerPosition.distanceTo(pos.getCenter());
                    if (distance < 10) {
                        player.addEffect(new MobEffectInstance(ModEffects.LING_QI_INCREASE.get(), 20 * 3, 2));  //增加恢复速度，2级
                        checkPlayer = true;
                    }
                }
                level.setBlock(pos, state.setValue(ACTIVITY,checkPlayer), 3); //最后一个是flag
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
