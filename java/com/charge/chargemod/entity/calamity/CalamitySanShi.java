package com.charge.chargemod.entity.calamity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.level.Level;

import java.util.Optional;

public class CalamitySanShi extends Entity {
    private static final EntityDataAccessor<Byte> ID_FLAGS = SynchedEntityData.defineId(CalamitySanShi.class, EntityDataSerializers.BYTE);
    private static final EntityDataAccessor<Byte> PIERCE_LEVEL = SynchedEntityData.defineId(CalamitySanShi.class, EntityDataSerializers.BYTE);

    public CalamityZombie zombieTop;
    public CalamityZombie zombieCenter;
    public CalamityZombie zombieBottom;
    private int lifeTime;
    public Player owner;
    private int killCount;

    public CalamitySanShi(EntityType<?> p_19870_, Level p_19871_) {
        super(p_19870_, p_19871_);
        killCount = 0;
        lifeTime = 5 * 20 * 60; //渡劫最多5分钟
    }

    public void killOneMore() {
        killCount++;
        if (killCount == 3) {   //三尸已斩
            //TODO:添加渡劫成功的提示并且增加player蓝条
            owner.sendSystemMessage(Component.literal("渡劫完成"));
            discard();
        }
    }

    @Override
    public void tick() {
        super.tick();
        lifeTime--;
        if (!this.level().isClientSide) {

            if (lifeTime <= 0) {    //时间到了就消失
                //TODO：发送渡劫失败的提示
                zombieTop.timeEnd();
                zombieCenter.timeEnd();
                zombieBottom.timeEnd();
                owner.sendSystemMessage(Component.literal("渡劫失败"));
                discard();
            }
            if (owner == null) {
                discard();
            }
        }
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(ID_FLAGS, (byte)0);
        this.entityData.define(PIERCE_LEVEL, (byte)0);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag p_20052_) {
        lifeTime = p_20052_.getInt("lifeTime");
        killCount = p_20052_.getInt("killCount");
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag p_20139_) {
        p_20139_.putInt("killCount", this.killCount);
        p_20139_.putInt("lifeTime", this.lifeTime);
    }
//
//    public Packet<ClientGamePacketListener> getAddEntityPacket() {
//        Entity entity = this.owner;
//        return new ClientboundAddEntityPacket(this, entity == null ? 0 : entity.getId());
//    }
//
//    public void recreateFromPacket(ClientboundAddEntityPacket p_150170_) {
//        super.recreateFromPacket(p_150170_);
//        Entity entity = this.level().getEntity(p_150170_.getData());
//        if (entity != null) {
//            Player player = (Player) entity;
//            this.owner = player;
//
//        }
//
//    }
}
