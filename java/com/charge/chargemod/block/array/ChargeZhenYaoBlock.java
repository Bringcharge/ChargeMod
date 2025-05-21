package com.charge.chargemod.block.array;

import com.charge.chargemod.ChargeModItemRegistry;
import com.charge.chargemod.damage.ChargeDamageTypes;
import com.charge.chargemod.damage.DaoFaDamageSource;
import com.charge.chargemod.effect.ModEffects;
import com.charge.chargemod.entity.ChargeArrow;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
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
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraft.world.phys.*;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;

public class ChargeZhenYaoBlock extends ChargeTickBaseBlock implements EntityBlock {
    //
    public static final BooleanProperty ACTIVITY = BooleanProperty.create("activity");    //创建基本状态
    public ChargeZhenYaoBlock(Properties Properties) {
        super(Properties);
        this.registerDefaultState(this.defaultBlockState().setValue(ACTIVITY,false));
    }

    public class ZhenYaoTrident extends ThrownTrident {

        public Consumer<Entity> consumer;//回调

        public ZhenYaoTrident(EntityType<? extends ThrownTrident> p_37561_, Level p_37562_) {
            super(p_37561_, p_37562_);//EntityType.TRIDENT
        }
        @Override
        protected boolean tryPickup(Player p_150196_) {
            return false;
        }

        @Override
        protected void onHitEntity(EntityHitResult p_37573_) {
            super.onHitEntity(p_37573_);
            if (consumer!=null) {
                consumer.accept(p_37573_.getEntity());
            }
        }
    }


    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ChargeZhenYaoBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> entityType) {
        return level.isClientSide ? null : createTickerHelper(entityType, ChargeModItemRegistry.CHARGE_ZHEN_YAO_ENTITY.get(), ChargeZhenYaoBlockEntity::serverTick);
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
                for (Entity entity : entityList) {
                    Vec3 eyePosition = entity.getEyePosition(1.0F);
                    Vec3 vec_to = centerVec;
                    Vec3 dirVec = centerVec.vectorTo(eyePosition).normalize();  //方向单位向量
                    if (Math.abs(eyePosition.y - centerVec.y) < 2) {    //在下方的不攻击
                        continue;
                    }
                    //查看阻塞位置
                    ClipContext context = new ClipContext(eyePosition, vec_to.add(dirVec.scale(1.5)), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, null);//前两个参数是起点和终点坐标
                    BlockHitResult blockRayTraceResult = level.clip(context);
                    if (blockRayTraceResult.getType() == HitResult.Type.MISS) { //视线畅通无阻
                        checkPlayer = true; //找到了目标
                        ZhenYaoTrident zhenYaoTrident = new ZhenYaoTrident(EntityType.TRIDENT,level);
                        zhenYaoTrident.setPos(centerVec.add(dirVec));   //初始位置
                        zhenYaoTrident.consumer = ((shootEntity)->{
                            //参数注入
                            shootEntity.teleportTo(centerVec.x, centerVec.y - 2, centerVec.z);
                        });

                        zhenYaoTrident.setDeltaMovement(dirVec.scale(3));
                        level.addFreshEntity(zhenYaoTrident);
                        break;  //退出for循环
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
