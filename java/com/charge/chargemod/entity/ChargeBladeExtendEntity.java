package com.charge.chargemod.entity;

import com.charge.chargemod.ChargeModItemRegistry;
import com.charge.chargemod.damage.ChargeDamageTypes;
import com.charge.chargemod.damage.DaoFaDamageSource;
import net.minecraft.network.protocol.game.ClientboundGameEventPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

public class ChargeBladeExtendEntity extends AbstractArrow  {
    public ChargeBladeExtendEntity(Level level, double x, double y, double z) {
        super(ChargeModItemRegistry.CHARGE_BLADE_EXTEND_ENTITY_TYPE.get(), x, y, z, level);
        this.pickup = Pickup.DISALLOWED;

        setNoGravity(true);
    }
    public ChargeBladeExtendEntity(Level level, LivingEntity shooter) {
        super(ChargeModItemRegistry.CHARGE_BLADE_EXTEND_ENTITY_TYPE.get(),shooter,level);
        this.pickup = Pickup.DISALLOWED;
        setNoGravity(true);
    }

    public ChargeBladeExtendEntity(EntityType<ChargeBladeExtendEntity> ChargeBladeExtendEntity, Level level) {
        super(ChargeBladeExtendEntity, level);
        this.pickup = Pickup.DISALLOWED;
        setNoGravity(true);
    }

    @Override
    protected ItemStack getPickupItem() {
        return ItemStack.EMPTY;
    }

    @Override
    protected void onHitEntity(EntityHitResult p_36757_) {
        Entity entity = p_36757_.getEntity();
        float f = (float)this.getDeltaMovement().length();
        int i = 7;  //跟直接伤害有关

        Entity entity1 = this.getOwner();
        DamageSource damagesource;
        if (entity1 == null) {
            damagesource = DaoFaDamageSource.source(this, this, ChargeDamageTypes.DAO_REAL);
        } else {
            damagesource = DaoFaDamageSource.source(entity1, this, ChargeDamageTypes.DAO_REAL);
        }

        //好像是处理着火的单位，和末影人的
        boolean flag = entity.getType() == EntityType.ENDERMAN;
        int k = entity.getRemainingFireTicks();
        if (this.isOnFire() && !flag) {
            entity.setSecondsOnFire(5);
        }


        if (entity.hurt(damagesource, (float)i)) {
            if (flag) {
                return;
            }

            if (entity instanceof LivingEntity) {
                LivingEntity livingentity = (LivingEntity)entity;
                if (!this.level().isClientSide && this.getPierceLevel() <= 0) {
                    livingentity.setArrowCount(livingentity.getArrowCount() + 1);
                }

                //去掉反弹

                if (!this.level().isClientSide && entity1 instanceof LivingEntity) {
                    EnchantmentHelper.doPostHurtEffects(livingentity, entity1);
                    EnchantmentHelper.doPostDamageEffects((LivingEntity)entity1, livingentity);
                }

                this.doPostHurtEffects(livingentity);
                if (entity1 != null && livingentity != entity1 && livingentity instanceof Player && entity1 instanceof ServerPlayer && !this.isSilent()) {
                    ((ServerPlayer)entity1).connection.send(new ClientboundGameEventPacket(ClientboundGameEventPacket.ARROW_HIT_PLAYER, 0.0F));
                }

                //十字弩击杀成就相关代码删掉
            }
            //音效还是使用原版的弓箭音效吧，没有看到什么更好的
            this.playSound(getDefaultHitGroundSoundEvent(), 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
            if (this.getPierceLevel() <= 0) {
                this.discard();
            }
        } else {
            entity.setRemainingFireTicks(k);
            this.setDeltaMovement(this.getDeltaMovement().scale(-0.1D));
            this.setYRot(this.getYRot() + 180.0F);
            this.yRotO += 180.0F;
            if (!this.level().isClientSide && this.getDeltaMovement().lengthSqr() < 1.0E-7D) {
                if (this.pickup == Pickup.ALLOWED) {  //这玩意是捡不起来的
                    this.spawnAtLocation(this.getPickupItem(), 0.1F);
                }

                this.discard();
            }
        }

    }
}
