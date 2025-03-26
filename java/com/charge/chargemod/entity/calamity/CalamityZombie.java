package com.charge.chargemod.entity.calamity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;

public class CalamityZombie extends Zombie {

    public CalamitySanShi owner;

    public CalamityZombie(EntityType<? extends Zombie> p_34271_, Level p_34272_) {
        super(p_34271_, p_34272_);
    }

    public void setOwner(CalamitySanShi owner) {
        this.owner = owner;
    }

    public void timeEnd() {
        discard();
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.FOLLOW_RANGE, 35.0D).add(Attributes.MOVEMENT_SPEED, (double)0.23F).add(Attributes.ATTACK_DAMAGE, 3.0D).add(Attributes.ARMOR, 2.0D).add(Attributes.SPAWN_REINFORCEMENTS_CHANCE);
    }

    @Override
    public void die(DamageSource p_21014_) {
        if (net.minecraftforge.common.ForgeHooks.onLivingDeath(this, p_21014_)) return;
        super.die(p_21014_);
        owner.killOneMore();
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.level().isClientSide && owner == null) {
            discard();
        }
    }

    @Override
    public void readAdditionalSaveData(CompoundTag p_20052_) {
        super.readAdditionalSaveData(p_20052_);
        owner = (CalamitySanShi) ((ServerLevel)this.level()).getEntity(p_20052_.getUUID("Owner"));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag p_20139_) {
        super.addAdditionalSaveData(p_20139_);
        p_20139_.putUUID("Owner", this.owner.getUUID());
    }

//    public Packet<ClientGamePacketListener> getAddEntityPacket() {
//        Entity entity = this.owner;
//        return new ClientboundAddEntityPacket(this, entity == null ? 0 : entity.getId());
//    }
//
//    public void recreateFromPacket(ClientboundAddEntityPacket p_150170_) {
//        super.recreateFromPacket(p_150170_);
//        Entity entity = this.level().getEntity(p_150170_.getData());
//        if (entity != null) {
//            CalamitySanShi calamitySanShi = (CalamitySanShi)entity;
//            this.setOwner(calamitySanShi);
//            if (calamitySanShi.zombieTop == null) {
//                calamitySanShi.zombieTop = this;
//            } else if (calamitySanShi.zombieCenter == null){
//                calamitySanShi.zombieCenter = this;
//            } else if (calamitySanShi.zombieBottom == null) {
//                calamitySanShi.zombieBottom = this;
//            }
//        }
//
//    }
}

