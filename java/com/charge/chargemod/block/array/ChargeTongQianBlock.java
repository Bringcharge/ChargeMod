package com.charge.chargemod.block.array;

import com.charge.chargemod.ChargeModItemRegistry;
import com.charge.chargemod.entity.ChargeCopperCoinEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.*;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;

public class ChargeTongQianBlock extends ChargeTickBaseBlock implements EntityBlock {
    //
    public static final BooleanProperty ACTIVITY = BooleanProperty.create("activity");    //创建基本状态
    public ChargeTongQianBlock(Properties Properties) {
        super(Properties);
        this.registerDefaultState(this.defaultBlockState().setValue(ACTIVITY,false));
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ChargeTongQianBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> entityType) {
        return level.isClientSide ? null : createTickerHelper(entityType, ChargeModItemRegistry.CHARGE_TONG_QIAN_BLOCK_ENTITY.get(), ChargeTongQianBlockEntity::serverTick);
    }

    @Override
    public void everyTick(BlockState state, Level level, BlockPos pos) {

        BlockEntity blockentity = level.getBlockEntity(pos);
        if (blockentity instanceof ChargeTickBlockEntity) {
            ChargeTickBlockEntity tickBlockEntity = (ChargeTickBlockEntity)blockentity;
            int tick = tickBlockEntity.getTick();

            if ((tick % (20 * 2) == 0 && state.getValue(ACTIVITY) )|| tick % (20 * 6) == 0) {   //tick数据符合条件
                boolean checkPlayer = false; //检测成功标志
                int halfWidth = 15;
                int halfHeight = 7;

                Vec3 centerVec = pos.getCenter();
                Vec3 bottomWestSouth = centerVec.add(-halfWidth, -halfHeight, halfWidth);
                Vec3 topEastNorth = centerVec.add(halfWidth, halfHeight, -halfWidth);
                AABB searchArea = new AABB(bottomWestSouth, topEastNorth);
                List<Entity> entityList = level.getEntities((Entity)null, searchArea, entity -> entity instanceof Mob);
                int targetCount = 0;
                for (Entity entity : entityList) {
                    Vec3 eyePosition = entity.getEyePosition(1.0F);
                    Vec3 vec_to = centerVec;
                    Vec3 dirVec = centerVec.vectorTo(eyePosition).normalize();  //方向单位向量
                    //查看阻塞位置
                    ClipContext context = new ClipContext(eyePosition, vec_to.add(dirVec.scale(1.5)), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, null);//前两个参数是起点和终点坐标
                    BlockHitResult blockRayTraceResult = level.clip(context);
                    if (blockRayTraceResult.getType() == HitResult.Type.MISS) { //视线畅通无阻
                        checkPlayer = true; //找到了目标
                        ChargeCopperCoinEntity zhenYaoTrident = new ChargeCopperCoinEntity(ChargeModItemRegistry.COPPER_COIN_ENTITY_TYPE.get(),level);
                        zhenYaoTrident.setPos(centerVec.add(dirVec));   //初始位置
                        zhenYaoTrident.setDeltaMovement(dirVec.scale(3));
                        level.addFreshEntity(zhenYaoTrident);
                        targetCount++;
                        if (targetCount == 3) {
                            break;  //退出for循环
                        }
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
