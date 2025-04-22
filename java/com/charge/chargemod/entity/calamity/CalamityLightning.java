package com.charge.chargemod.entity.calamity;

import com.charge.chargemod.lingqi.PlayerLingQiHelper;
import com.charge.chargemod.network.ChargePacketSender;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.Random;

public class CalamityLightning extends Entity {
    private static final EntityDataAccessor<Byte> ID_FLAGS = SynchedEntityData.defineId(CalamityLightning.class, EntityDataSerializers.BYTE);
    private static final EntityDataAccessor<Byte> PIERCE_LEVEL = SynchedEntityData.defineId(CalamityLightning.class, EntityDataSerializers.BYTE);


    private int lifeTime;
    private final int maxLifeTime = 20  * 225 + 20 * 5; //持续225s + 5s演出
    public Player owner;

    public CalamityLightning(EntityType<?> p_19870_, Level p_19871_) {
        super(p_19870_, p_19871_);
        lifeTime = 0;
    }

    private int stateCheck(int lifeTime) {  //判断当前进度
        for (int i = 1; i <= 9; i++) {
            lifeTime = lifeTime - i * 5 * 20;
            if (lifeTime <= 0 ) return i;
        }
        return 10;
    }

    public void start() {
        if (!this.level().isClientSide) {
            ServerLevel serverLevel = (ServerLevel)this.level();
            serverLevel.setWeatherParameters(0, maxLifeTime, false,true);    //晴时间，雷雨时间，是否下雨，是否打雷
        }
    }

    public int triggerTick(int tick) {   //触发时机
        int count = 0;
        for (int i = 1; i <=9 ;i++) {
            count += i * 5 * 20;
            if (tick == count) {
                return i;
            }
        }
        return 0;
    }

    public boolean nearTriggerTick(int tick) {   //触发时机
        int count = 0;
        for (int i = 1; i <=9 ;i++) {
            count += i * 5 * 20;
            if (Math.abs(tick - count) < 20 * 3) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void tick() {
        super.tick();
        lifeTime++;
        if (!this.level().isClientSide) {
            if (owner == null) {
                discard();
                return;
            }
            if (owner.isDeadOrDying()) {    //不知道触发图腾会不会计入
                owner.sendSystemMessage(Component.literal("渡劫失败"));
                discard();
                return;
            }

            if (lifeTime > maxLifeTime) {   //结束了
                owner.sendSystemMessage(Component.literal("渡劫成功"));
                if (!level().isClientSide) {
                    if (owner instanceof ServerPlayer) {
                        PlayerLingQiHelper.crossingCalamity(owner, 3);
                        ChargePacketSender.sendCrossCalamityMessageToClient((ServerPlayer)owner, 3);
                    }
                }
                discard();
                return;
            }

            int state = stateCheck(lifeTime);
            randomThunder(tickCount, state);    //去构建背景
            int trigger = triggerTick(tickCount);
            if (trigger != 0) {
                LightningBolt lightningBolt = new LightningBolt(EntityType.LIGHTNING_BOLT, level());
                lightningBolt.setDamage(trigger * 5);
                lightningBolt.setPos(owner.position().x, owner.position().y, owner.position().z);
                level().addFreshEntity(lightningBolt);
                owner.sendSystemMessage(Component.literal("第" + trigger +"道"));
            }

        }
    }

    private void randomThunder(int tick,int state) {    //随机雷电特效
        if (nearTriggerTick(tick)) {
            return;
        }
        Random random = new Random();
        if ( tick % (80 / state) == 0) {
            Level level = level();
            int number = random.nextInt(1,2);
            for (int i = 0; i <number; i++) {
                int x = random.nextInt(-32,32);
                int z = random.nextInt(-32,32);
                if (x <= 3 && x >= -3 && z <= 3 && z >= -3) {
                    continue;
                }
                LightningBolt lightningBoltEntity = lightningCreat(level);
                lightningBoltEntity.setPos(owner.position().x + x, owner.position().y, owner.position().z + z);
                level.addFreshEntity(lightningBoltEntity);
            }
        }
    }

    public LightningBolt lightningCreat(Level level) {  //构建雷电实体
        LightningBolt lightningBolt = new LightningBolt(EntityType.LIGHTNING_BOLT, level);
        lightningBolt.setDamage(1);
        return lightningBolt;
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(ID_FLAGS, (byte)0);
        this.entityData.define(PIERCE_LEVEL, (byte)0);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag p_20052_) {
        lifeTime = p_20052_.getInt("lifeTime");
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag p_20139_) {
        p_20139_.putInt("lifeTime", this.lifeTime);
    }

}
