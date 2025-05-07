package com.charge.chargemod.block.array;

import com.charge.chargemod.damage.ChargeDamageTypes;
import com.charge.chargemod.damage.DaoFaDamageSource;
import com.charge.chargemod.effect.ModEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.FlyingMob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class ChargeZhenYaoBlock extends ChargeTickBaseBlock implements EntityBlock {
    //
    public static final BooleanProperty ACTIVITY = BooleanProperty.create("activity");    //创建基本状态
    public ChargeZhenYaoBlock(Properties Properties) {
        super(Properties);
        this.registerDefaultState(this.defaultBlockState().setValue(ACTIVITY,false));
    }

    public class ZhenYaoTrident extends ThrownTrident {
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
            //TODO：增加回调
        }
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

            if ((tick % 20 == 0 && state.getValue(ACTIVITY) )|| tick % (20 * 3) == 0) {   //tick数据符合条件
                boolean checkPlayer = false; //检测成功标志
                int halfWidth = 8;
                int halfHeight = 7;

                Vec3 eyePosition = pos.getCenter();
                Vec3 bottomWestSouth = eyePosition.add(-halfWidth, -halfHeight, halfWidth);
                Vec3 topEastNorth = eyePosition.add(halfWidth, halfHeight, -halfWidth);
                AABB searchArea = new AABB(bottomWestSouth, topEastNorth);
                List<Entity> entityList = level.getEntities((Entity)null, searchArea, entity -> entity instanceof LivingEntity);
                for (Entity entity : entityList) {

                }
                        checkPlayer = true;

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
