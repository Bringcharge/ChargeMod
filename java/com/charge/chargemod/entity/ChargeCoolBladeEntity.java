package com.charge.chargemod.entity;

import com.charge.chargemod.lingqi.PlayerLingQiHelper;
import com.charge.chargemod.network.ChargePacketSender;
import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import java.util.Random;
import java.util.UUID;

//这个更像是处理事件的
public class ChargeCoolBladeEntity extends Entity {
    private static final EntityDataAccessor<Byte> ID_FLAGS_BLADE = SynchedEntityData.defineId(ChargeCoolBladeEntity.class, EntityDataSerializers.BYTE);
    private static final EntityDataAccessor<Byte> PIERCE_LEVEL_BLADE = SynchedEntityData.defineId(ChargeCoolBladeEntity.class, EntityDataSerializers.BYTE);


    private int lifeTime;
    private final int maxLifeTime = 20; //持续225s + 5s演出
    private Player owner;
    public Vec3 leftSide;//左单位向量
    public Vec3 upSide;//上单位向量
    public Vec3 toSide; //前单位向量
    public Vec3 startPos;   //释放位置
    private LongOpenHashSet ignoredBlock = new LongOpenHashSet(); //用来处理相同的block
    public ChargeCoolBladeEntity(EntityType<?> p_19870_, Level p_19871_) {
        super(p_19870_, p_19871_);
    }
    public void setLevelAndPlayer(Level p_19871_, Player player) {
        lifeTime = 0;
        Vec3 nowPos = player.getEyePosition();
        Vec3 vec3 = player.getLookAngle().normalize();
        rebuildData(vec3, nowPos);
    }

    public void rebuildData(Vec3 vec3, Vec3 nowPos) {//视线单位向量，当前位置

        Vec3 nextTickPos = vec3;

        double d0 = vec3.horizontalDistance();
        this.setYRot((float)(Mth.atan2(vec3.x, vec3.z) * (double)(180F / (float)Math.PI)));
        this.setXRot((float)(Mth.atan2(vec3.y, d0) * (double)(180F / (float)Math.PI)));
        float rotateY = this.getYRot();    //视角在水平面上的弧度
        float f1 = -rotateY * ((float)Math.PI / 180F);
        float f2 = Mth.cos(f1);
        float f3 = Mth.sin(f1);
        Vec3 xz_vec = new Vec3(vec3.x, 0, vec3.z).normalize();
        Vec3 oneSide = vec3.cross(xz_vec).normalize();  //其中的一侧，如果视野在水平线上的话是左侧
        if(oneSide.equals(Vec3.ZERO)) {  //平视，导致向量叉乘是0
            oneSide = new Vec3(f2, 0 , -f3).normalize(); //进行逆时针旋转90，变成左侧
        }
        setPos(nowPos);

        leftSide = oneSide;
        toSide = vec3;
        upSide = vec3.cross(oneSide);
        startPos = nowPos;
        ignoredBlock = new LongOpenHashSet();
    }

    public void breakBLock(int tick) {
        if (startPos == null) {
            return;
        }
        float width = 20.f; //宽度范围
        float heightRate = 6.f; //高度除以的倍率
        float tickStep = width / maxLifeTime;//每tick处理方案

        for (float i = -(width / 2) + tick * tickStep ; i < (tick + 1) * tickStep; i+=0.5 ) {    //横向
            for (float j = 0.5f; j < width; j+=0.5) { //前后
                float height = width / 2.f / heightRate - Math.abs(i) / heightRate;  //计算当前坐标需要高度
                for (float k = -height; k < height; k+=0.5 ) {    //上下
                    BlockPos pos = BlockPos.containing(startPos.add(leftSide.scale(i)).add(toSide.scale(j)).add(upSide.scale(k)));
                    double dis = startPos.distanceTo(pos.getCenter());
                    if (!ignoredBlock.contains(pos.asLong()) && dis < width) { //检测是否处理过
                        BlockState state = level().getBlockState(pos);
                        if (!state.is(Blocks.AIR)) {
                            onHitBlock(pos);
                        }
                    } else {
                        this.ignoredBlock.add(pos.asLong());
                    }
                }
            }
        }
    }

    protected void onHitBlock(BlockPos pos) {   //破坏方块函数
        BlockState state = level().getBlockState(pos);
        Level level = this.level();
        if (!level.isClientSide() && !state.is(Blocks.BEDROCK)) {  //直接破坏掉方块
            level.destroyBlock(pos, true, owner); //保留掉落物
        }
    }

    @Override
    public void tick() {    //假设持续10tick
        super.tick();
        if (lifeTime >= maxLifeTime) {
            //销毁
            discard();
        } else {
            breakBLock(lifeTime);
        }
        lifeTime++;
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(ID_FLAGS_BLADE, (byte)0);
        this.entityData.define(PIERCE_LEVEL_BLADE, (byte)0);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag p_20052_) {
        lifeTime = p_20052_.getInt("lifeTime_blade");
        UUID uuid = p_20052_.getUUID("Owner");
        if (uuid != null && this.level() instanceof ServerLevel) {
            this.owner = (Player) ((ServerLevel) this.level()).getEntity(uuid);
        }
        double side_x = p_20052_.getDouble("look_x");
        double side_y = p_20052_.getDouble("look_y");
        double side_z = p_20052_.getDouble("look_z");
        double start_x = p_20052_.getDouble("pos_x");
        double start_y = p_20052_.getDouble("pos_y");
        double start_z = p_20052_.getDouble("pos_z");
        rebuildData(new Vec3(side_x,side_y,side_z), new Vec3(start_x, start_y, start_z));
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag p_20139_) {
        p_20139_.putInt("lifeTime_blade", this.lifeTime);
        if (owner != null) {
            p_20139_.putUUID("Owner", this.owner.getUUID());
        }
        p_20139_.putDouble("look_x",toSide.x);
        p_20139_.putDouble("look_y",toSide.y);
        p_20139_.putDouble("look_z",toSide.z);
        p_20139_.putDouble("pos_x",startPos.x);
        p_20139_.putDouble("pos_y",startPos.y);
        p_20139_.putDouble("pos_z",startPos.z);
    }

}
