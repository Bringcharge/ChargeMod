package com.charge.chargemod.entity;

import com.charge.chargemod.ChargeModItemRegistry;
import com.charge.chargemod.damage.ChargeDamageTypes;
import com.charge.chargemod.damage.DaoFaDamageSource;
import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Position;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.protocol.game.ClientboundGameEventPacket;
import net.minecraft.network.protocol.game.ClientboundMoveEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.*;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChargeBladeExtendEntity extends Projectile {

    private int discardTick;
    private static final double ARROW_BASE_DAMAGE = 2.0D;
    private static final EntityDataAccessor<Byte> ID_FLAGS = SynchedEntityData.defineId(AbstractArrow.class, EntityDataSerializers.BYTE);
    private static final EntityDataAccessor<Byte> PIERCE_LEVEL = SynchedEntityData.defineId(AbstractArrow.class, EntityDataSerializers.BYTE);

    @Nullable
    private BlockState lastState;
    public AbstractArrow.Pickup pickup = AbstractArrow.Pickup.DISALLOWED;

    private int life;

    private SoundEvent soundEvent = this.getDefaultHitGroundSoundEvent();
    @Nullable
    private IntOpenHashSet piercingIgnoreEntityIds;
    @Nullable
    private final IntOpenHashSet ignoredEntities = new IntOpenHashSet();

    private LongOpenHashSet ignoredBlock = new LongOpenHashSet(); //用来处理相同的block

    public void setSoundEvent(SoundEvent soundEvent) {
        this.soundEvent = soundEvent;
    }

    public boolean shouldRenderAtSqrDistance(double p_36726_) { //该渲染的距离
        double d0 = this.getBoundingBox().getSize() * 10.0D;
        if (Double.isNaN(d0)) {
            d0 = 1.0D;
        }

        d0 *= 64.0D * getViewScale();
        return p_36726_ < d0 * d0;
    }

    protected void defineSynchedData() {    //虽然不知道这些data是什么东西，保留应该就好了
        this.entityData.define(ID_FLAGS, (byte)0);
        this.entityData.define(PIERCE_LEVEL, (byte)0);
    }

    public void shoot(Vec3 movement, float speed) {
        shoot(movement.x, movement.y, movement.z, speed, 0);
    }

    @Override
    public void shoot(double x, double y, double z, float speed, float useless) {
        Vec3 vec3 = (new Vec3(x, y, z)).normalize().scale((double)speed);
        this.setDeltaMovement(vec3);
        double d0 = vec3.horizontalDistance();
        this.setYRot((float)(Mth.atan2(vec3.x, vec3.z) * (double)(180F / (float)Math.PI)));
        this.setXRot((float)(Mth.atan2(vec3.y, d0) * (double)(180F / (float)Math.PI)));
        this.yRotO = this.getYRot();
        this.xRotO = this.getXRot();
    }

    public void lerpTo(double x, double y, double z, float yRot, float xRot, int p_36733_, boolean p_36734_) {
        this.setPos(x, y, z);
        this.setRot(yRot, xRot);
    }

    public void lerpMotion(double x, double y, double z) {
        super.lerpMotion(x, y, z);
        this.discardTick = 0;
    }
    @Override
    protected boolean updateInWaterStateAndDoFluidPushing() {   //防止水里出问题
        return this.isInFluidType();
    }

    public void tick() {
        super.tick();
        tickDespawn();
        boolean flag = this.isNoPhysics();
        Vec3 deltaMovement = this.getDeltaMovement();
        if (this.xRotO == 0.0F && this.yRotO == 0.0F) { //处理没有角度的情况，自动设置角度
            double d0 = deltaMovement.horizontalDistance();
            this.setYRot((float)(Mth.atan2(deltaMovement.x, deltaMovement.z) * (double)(180F / (float)Math.PI)));
            this.setXRot((float)(Mth.atan2(deltaMovement.y, d0) * (double)(180F / (float)Math.PI)));
            this.yRotO = this.getYRot();
            this.xRotO = this.getXRot();
        }

        BlockPos blockpos = this.blockPosition();   //?为什么会有这个东西，大概是中心点的位置
        BlockState blockstate = this.level().getBlockState(blockpos);
        if (!blockstate.isAir() && !flag) { //这个方块如果不是空气
            //可能更适合丢给hitblock去处理
            Level level = this.level();
            if (!level.isClientSide() && !blockstate.is(Blocks.BEDROCK)) {  //直接破坏掉方块
                level.destroyBlock(blockpos, true, getOwner()); //保留掉落物
            }
//            VoxelShape voxelshape = blockstate.getCollisionShape(this.level(), blockpos);
//            if (!voxelshape.isEmpty()) {
//                Vec3 vec31 = this.position();
//
//                for(AABB aabb : voxelshape.toAabbs()) {
//                    if (aabb.move(blockpos).contains(vec31)) {
//                        break;
//                    }
//                }
//            }
        }

    //这里应加个for

        Vec3 nowPos = this.position();
        Vec3 nextTickPos = nowPos.add(deltaMovement);

        float rotateY = this.getYRot();    //视角在水平面上的弧度
        float f1 = -rotateY * ((float)Math.PI / 180F);
        float f2 = Mth.cos(f1);
        float f3 = Mth.sin(f1);
        Vec3 xz_vec = new Vec3(deltaMovement.x, 0, deltaMovement.z).normalize();
        Vec3 oneSide = deltaMovement.cross(xz_vec).normalize();  //其中的一侧，如果视野在水平线上的话是左侧
        if(oneSide.equals(Vec3.ZERO)) {  //平视，导致向量叉乘是0
            oneSide = new Vec3(f2, 0 , -f3).normalize(); //进行逆时针旋转90，变成左侧
        }
        Vec3 forward = deltaMovement.normalize();
        ArrayList<Vec3> checkPoints = new ArrayList<Vec3>();
        Vec3 firstPos = nowPos.add(forward.scale(6.5)).add(oneSide.scale(0.5));   //第一个点，大概是中间左侧的最前面那个点
        checkPoints.add(firstPos);
        checkPoints.add(firstPos.add(oneSide.scale(1.f)).add(forward.scale(-1.f))); //左侧
        checkPoints.add(firstPos.add(oneSide.scale(2.f)).add(forward.scale(-1.f)));
        checkPoints.add(firstPos.add(oneSide.scale(3.f)).add(forward.scale(-2.f)));
        checkPoints.add(firstPos.add(oneSide.scale(4.f)).add(forward.scale(-2.f)));
        checkPoints.add(firstPos.add(oneSide.scale(5.f)).add(forward.scale(-4.f)));
        checkPoints.add(firstPos.add(oneSide.scale(6.f)).add(forward.scale(-4.f)));
        checkPoints.add(firstPos.add(oneSide.scale(7.f)).add(forward.scale(-6.f)));
        checkPoints.add(firstPos.add(oneSide.scale(8.f)).add(forward.scale(-6.f)));
        checkPoints.add(firstPos.add(oneSide.scale(9.f)).add(forward.scale(-8.f)));
        checkPoints.add(firstPos.add(oneSide.scale(10.f)).add(forward.scale(-8.f)));
        checkPoints.add(firstPos.add(oneSide.scale(11.f)).add(forward.scale(-10.f)));
        checkPoints.add(firstPos.add(oneSide.scale(-1.f)));                         //右侧
        checkPoints.add(firstPos.add(oneSide.scale(-2.f)).add(forward.scale(-1.f)));
        checkPoints.add(firstPos.add(oneSide.scale(-3.f)).add(forward.scale(-1.f)));
        checkPoints.add(firstPos.add(oneSide.scale(-4.f)).add(forward.scale(-2.f)));
        checkPoints.add(firstPos.add(oneSide.scale(-5.f)).add(forward.scale(-2.f)));
        checkPoints.add(firstPos.add(oneSide.scale(-6.f)).add(forward.scale(-4.f)));
        checkPoints.add(firstPos.add(oneSide.scale(-7.f)).add(forward.scale(-4.f)));
        checkPoints.add(firstPos.add(oneSide.scale(-8.f)).add(forward.scale(-6.f)));
        checkPoints.add(firstPos.add(oneSide.scale(-9.f)).add(forward.scale(-6.f)));
        checkPoints.add(firstPos.add(oneSide.scale(-10.f)).add(forward.scale(-8.f)));
        checkPoints.add(firstPos.add(oneSide.scale(-11.f)).add(forward.scale(-8.f)));
        checkPoints.add(firstPos.add(oneSide.scale(-12.f)).add(forward.scale(-10.f)));

        if(discardTick % 2 == 0) {
            for (Vec3 checkPosition : checkPoints) {
                //获取第一个block的hit事件
                Vec3 checkEndPoint = checkPosition.add(deltaMovement);

                BlockPos pos = BlockPos.containing(checkEndPoint).below();
                for (int i = 0; i < 3; i++) {
                    if (this.ignoredBlock == null) { //被伤害过的就记录
                        this.ignoredBlock = new LongOpenHashSet();
                    }
                    if (!ignoredBlock.contains(pos.asLong())) { //如果没有
                        BlockState state = level().getBlockState(pos);
                        if (!state.is(Blocks.AIR)) {
                            onHitBlock(pos);
                        }
                    }
                    this.ignoredBlock.add(pos.asLong());
                    pos = pos.above();
                }
            }

            double max_x = firstPos.x;
            double min_x = firstPos.x;
            double max_y = firstPos.y;
            double min_y = firstPos.y;
            double max_z = firstPos.z;
            double min_z = firstPos.z;
            for (Vec3 checkPosition : checkPoints) {
                max_x = Math.max(max_x, checkPosition.x);
                max_y = Math.max(max_y, checkPosition.y);
                max_z = Math.max(max_z, checkPosition.z);
                min_x = Math.min(min_x, checkPosition.x);
                min_y = Math.min(min_y, checkPosition.y);
                min_z = Math.min(min_z, checkPosition.z);
            }

            AABB area = new AABB(max_x + 2, max_y + 2, max_z + 2, min_x - 2, min_y - 2, min_z - 2);
            List<Entity> list = level().getEntities(this, area, (entity -> {
                return  entity instanceof LivingEntity && canHitEntity(entity);
            }));

            for (Entity entity : list) {
                Entity entity1 = this.getOwner();
                if (entity instanceof Player && entity1 instanceof Player && !((Player) entity1).canHarmPlayer((Player) entity)) {  //不是自己，不能伤害队友
                    continue;
                }

                Vec3 linePoint1 = checkPoints.get(4);
                Vec3 linePoint2 = checkPoints.get(15);
                if (PointDistanceToLine(entity.position(), linePoint1, linePoint2) < 3)
                this.onHitEntity(entity);
                ignoredEntities.add(entity.getId());
            }
//                while (!this.isRemoved()) { //这个while是保证在箭没有消失的情况下击穿多个生物？
//
//
//                    if (hitresult != null && hitresult.getType() != HitResult.Type.MISS && !flag) {
//                        switch (net.minecraftforge.event.ForgeEventFactory.onProjectileImpactResult(this, hitresult)) { //支持无伤害的设置，目前没看到调用
//                            case SKIP_ENTITY:
//                                if (hitresult.getType() != HitResult.Type.ENTITY) { // If there is no entity, we just return default behaviour
//                                    this.onHit(hitresult);
//                                    break;
//                                }
//                                ignoredEntities.add(entityhitresult.getEntity().getId());
//                                entityhitresult = null; // Don't process any further
//                                break;
//                            case STOP_AT_CURRENT_NO_DAMAGE:
//                                entityhitresult = null; // Don't process any further
//                                break;
//                            case STOP_AT_CURRENT:
//                                this.setPierceLevel((byte) 0);
//                            case DEFAULT:
//                                this.onHit(hitresult);
//                                break;
//                        }
//                    }
//
//                    if (entityhitresult == null || this.getPierceLevel() <= 0) {
//                        break;
//                    }
//
//                    hitresult = null;
//                }
            }//for在这里结束

        if (this.isRemoved())
            return;

        deltaMovement = this.getDeltaMovement();
        double d5 = deltaMovement.x;
        double d6 = deltaMovement.y;
        double d1 = deltaMovement.z;


        double d7 = this.getX() + d5;
        double d2 = this.getY() + d6;
        double d3 = this.getZ() + d1;
        double d4 = deltaMovement.horizontalDistance();
        if (flag) {
            this.setYRot((float)(Mth.atan2(-d5, -d1) * (double)(180F / (float)Math.PI)));
        } else {
            this.setYRot((float)(Mth.atan2(d5, d1) * (double)(180F / (float)Math.PI)));
        }

        this.setXRot((float)(Mth.atan2(d6, d4) * (double)(180F / (float)Math.PI)));
        this.setXRot(lerpRotation(this.xRotO, this.getXRot()));
        this.setYRot(lerpRotation(this.yRotO, this.getYRot()));
        float f = 0.99F;
        if (this.isInWater()) {//在水里增加粒子
            for(int j = 0; j < 4; ++j) {
                this.level().addParticle(ParticleTypes.BUBBLE, d7 - d5 * 0.25D, d2 - d6 * 0.25D, d3 - d1 * 0.25D, d5, d6, d1);
            }
        }

        this.setDeltaMovement(deltaMovement.scale((double)f));

        this.setPos(d7, d2, d3);
        this.checkInsideBlocks();

    }


//    @Override
//    protected void onHit(HitResult hitResult) { //简化版的onhit，没有增加event
//        HitResult.Type hitresult$type = hitResult.getType();
//        if (hitresult$type == HitResult.Type.ENTITY) {
////            this.onHitEntity((EntityHitResult)hitResult);
//        } else if (hitresult$type == HitResult.Type.BLOCK) {
//            BlockHitResult blockhitresult = (BlockHitResult)hitResult;
////            this.onHitBlock(blockhitresult);
//        }
//    }

    private boolean shouldFall() {
        return false;
    }

    private void startFalling() {
        Vec3 vec3 = this.getDeltaMovement();
        this.setDeltaMovement(vec3.multiply((double)(this.random.nextFloat() * 0.2F), (double)(this.random.nextFloat() * 0.2F), (double)(this.random.nextFloat() * 0.2F)));
        this.life = 0;
    }

    public void move(MoverType p_36749_, Vec3 p_36750_) {
        super.move(p_36749_, p_36750_);
        if (p_36749_ != MoverType.SELF && this.shouldFall()) {
            this.startFalling();
        }

    }

    private void resetPiercedEntities() {
        if (this.piercingIgnoreEntityIds != null) {
            this.piercingIgnoreEntityIds.clear();
        }
    }


    protected void onHitBlock(BlockPos pos) {
        BlockState state = level().getBlockState(pos);
        Level level = this.level();
        if (!level.isClientSide() && !state.is(Blocks.BEDROCK)) {  //直接破坏掉方块
            level.destroyBlock(pos, true, getOwner()); //保留掉落物
        }
    }

//    protected void onHitBlock(BlockHitResult result) {
//        BlockState state = level().getBlockState(result.getBlockPos());
//        Level level = this.level();
//        if (!level.isClientSide() && !state.is(Blocks.BEDROCK)) {  //直接破坏掉方块
//            level.destroyBlock(result.getBlockPos(), true, getOwner()); //保留掉落物
//        }
//    }

    protected SoundEvent getDefaultHitGroundSoundEvent() {
        return SoundEvents.ARROW_HIT;
    }

    protected void doPostHurtEffects(LivingEntity p_36744_) {
    }

//    @Nullable
//    protected EntityHitResult findHitEntity(Vec3 p_36758_, Vec3 p_36759_) {
//        Vec3 deltaMovement = this.getDeltaMovement();
//        float rotateY = this.getYRot();    //视角在水平面上的弧度
//        float f1 = -rotateY * ((float)Math.PI / 180F);
//        float f2 = Mth.cos(f1);
//        float f3 = Mth.sin(f1);
//        Vec3 xz_vec = new Vec3(deltaMovement.x, 0, deltaMovement.z).normalize();
//        Vec3 oneSide = deltaMovement.cross(xz_vec).normalize();  //其中的一侧，如果视野在水平线上的话是左侧
//        if(oneSide.equals(Vec3.ZERO)) {  //平视，导致向量叉乘是0
//            oneSide = new Vec3(f2, 0 , -f3).normalize(); //进行逆时针旋转90，变成左侧
//        }
//        return ProjectileUtil.getEntityHitResult(this.level(), this, p_36758_, p_36759_, this.getBoundingBox().expandTowards(oneSide).inflate(1.0D).expandTowards(oneSide).inflate(1.0D).expandTowards(deltaMovement).inflate(1.0D), this::canHitEntity);
//    }

    protected boolean canHitEntity(Entity p_36743_) {
        return super.canHitEntity(p_36743_) && (this.piercingIgnoreEntityIds == null || !this.piercingIgnoreEntityIds.contains(p_36743_.getId())) && !this.ignoredEntities.contains(p_36743_.getId());
    }

    public void addAdditionalSaveData(CompoundTag p_36772_) {
        super.addAdditionalSaveData(p_36772_);
        p_36772_.putShort("discardTick", (short)this.discardTick);
        if (this.lastState != null) {
            p_36772_.put("inBlockState", NbtUtils.writeBlockState(this.lastState));
        }

        p_36772_.putByte("pickup", (byte)this.pickup.ordinal());
        p_36772_.putBoolean("crit", this.isCritArrow());
        p_36772_.putByte("PierceLevel", this.getPierceLevel());
        p_36772_.putString("SoundEvent", BuiltInRegistries.SOUND_EVENT.getKey(this.soundEvent).toString());
        p_36772_.putBoolean("ShotFromCrossbow", this.shotFromCrossbow());
    }

    public void readAdditionalSaveData(CompoundTag p_36761_) {
        super.readAdditionalSaveData(p_36761_);
        this.discardTick = p_36761_.getShort("discardTick");
        if (p_36761_.contains("inBlockState", 10)) {
            this.lastState = NbtUtils.readBlockState(this.level().holderLookup(Registries.BLOCK), p_36761_.getCompound("inBlockState"));
        }

        this.pickup = AbstractArrow.Pickup.byOrdinal(p_36761_.getByte("pickup"));
        this.setCritArrow(p_36761_.getBoolean("crit"));
        this.setPierceLevel(p_36761_.getByte("PierceLevel"));
        if (p_36761_.contains("SoundEvent", 8)) {
            this.soundEvent = BuiltInRegistries.SOUND_EVENT.getOptional(new ResourceLocation(p_36761_.getString("SoundEvent"))).orElse(this.getDefaultHitGroundSoundEvent());
        }

        this.setShotFromCrossbow(p_36761_.getBoolean("ShotFromCrossbow"));
    }

    public void setOwner(@Nullable Entity p_36770_) {
        super.setOwner(p_36770_);
        if (p_36770_ instanceof Player) {
            this.pickup = ((Player)p_36770_).getAbilities().instabuild ? AbstractArrow.Pickup.CREATIVE_ONLY : AbstractArrow.Pickup.ALLOWED;
        }

    }

    public void playerTouch(Player p_36766_) {
        if (!this.level().isClientSide && (this.isNoPhysics())) {
            if (this.tryPickup(p_36766_)) {
                p_36766_.take(this, 1);
                this.discard();
            }
        }
    }

    protected boolean tryPickup(Player p_150121_) {
        switch (this.pickup) {
            default:
                return false;
        }
    }

    protected Entity.MovementEmission getMovementEmission() {
        return Entity.MovementEmission.NONE;
    }

    public boolean isAttackable() {
        return false;
    }

    protected float getEyeHeight(Pose p_36752_, EntityDimensions p_36753_) {
        return 0.13F;
    }

    public void setCritArrow(boolean p_36763_) {
        this.setFlag(1, p_36763_);
    }

    public void setPierceLevel(byte p_36768_) {
        this.entityData.set(PIERCE_LEVEL, p_36768_);
    }

    private void setFlag(int p_36738_, boolean p_36739_) {
        byte b0 = this.entityData.get(ID_FLAGS);
        if (p_36739_) {
            this.entityData.set(ID_FLAGS, (byte)(b0 | p_36738_));
        } else {
            this.entityData.set(ID_FLAGS, (byte)(b0 & ~p_36738_));
        }

    }

    public boolean isCritArrow() {
        byte b0 = this.entityData.get(ID_FLAGS);
        return (b0 & 1) != 0;
    }

    public boolean shotFromCrossbow() {
        byte b0 = this.entityData.get(ID_FLAGS);
        return (b0 & 4) != 0;
    }

    public byte getPierceLevel() {
        return this.entityData.get(PIERCE_LEVEL);
    }

    public boolean isNoPhysics() {
        if (!this.level().isClientSide) {
            return this.noPhysics;
        } else {
            return (this.entityData.get(ID_FLAGS) & 2) != 0;
        }
    }

    public void setShotFromCrossbow(boolean p_36794_) {
        this.setFlag(4, p_36794_);
    }


//---------------------------------------------------------------------------------------------------------------------------------------------------------------

    //自己的代码
    public ChargeBladeExtendEntity(Level level, double x, double y, double z) {
        super(ChargeModItemRegistry.CHARGE_BLADE_EXTEND_ENTITY_TYPE.get(), level);
        discardTick= 0;
        this.setPos(x, y, z);
        setNoGravity(true);
    }
    public ChargeBladeExtendEntity(Level level, LivingEntity shooter) {
        this(level, shooter.getX(), shooter.getEyeY() - (double)0.1F, shooter.getZ());
    }

    public ChargeBladeExtendEntity(EntityType<ChargeBladeExtendEntity> ChargeBladeExtendEntity, Level level) {
        super(ChargeBladeExtendEntity, level);
        discardTick = 0;
        setNoGravity(true);
    }

    protected ItemStack getPickupItem() {
        return ItemStack.EMPTY;
    }

    protected void onHitEntity(Entity  entity) {
        float f = (float)this.getDeltaMovement().length();
        int i = 7;  //跟直接伤害有关

        Entity entity1 = this.getOwner();
        DamageSource damagesource;
        if (entity1 == null) {
            damagesource = DaoFaDamageSource.source(this, this, ChargeDamageTypes.DAO_REAL);
        } else {
            damagesource = DaoFaDamageSource.source(entity1, this, ChargeDamageTypes.DAO_REAL);
        }


        if (this.piercingIgnoreEntityIds == null) { //被伤害过的就记录
            this.piercingIgnoreEntityIds = new IntOpenHashSet(32);
        }
        this.piercingIgnoreEntityIds.add(entity.getId());


        //不处理末影人
        if (entity.hurt(damagesource, (float)i)) {

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
        }

    }

//    @Override
//    protected void onHitEntity(EntityHitResult p_36757_) {
//        Entity entity = p_36757_.getEntity();
//        float f = (float)this.getDeltaMovement().length();
//        int i = 7;  //跟直接伤害有关
//
//        Entity entity1 = this.getOwner();
//        DamageSource damagesource;
//        if (entity1 == null) {
//            damagesource = DaoFaDamageSource.source(this, this, ChargeDamageTypes.DAO_REAL);
//        } else {
//            damagesource = DaoFaDamageSource.source(entity1, this, ChargeDamageTypes.DAO_REAL);
//        }
//
//
//        if (this.piercingIgnoreEntityIds == null) { //被伤害过的就记录
//            this.piercingIgnoreEntityIds = new IntOpenHashSet(32);
//        }
//        this.piercingIgnoreEntityIds.add(entity.getId());
//
//
//        //不处理末影人
//        if (entity.hurt(damagesource, (float)i)) {
//
//            if (entity instanceof LivingEntity) {
//                LivingEntity livingentity = (LivingEntity)entity;
//                if (!this.level().isClientSide && this.getPierceLevel() <= 0) {
//                    livingentity.setArrowCount(livingentity.getArrowCount() + 1);
//                }
//
//                //去掉反弹
//
//                if (!this.level().isClientSide && entity1 instanceof LivingEntity) {
//                    EnchantmentHelper.doPostHurtEffects(livingentity, entity1);
//                    EnchantmentHelper.doPostDamageEffects((LivingEntity)entity1, livingentity);
//                }
//
//                this.doPostHurtEffects(livingentity);
//                if (entity1 != null && livingentity != entity1 && livingentity instanceof Player && entity1 instanceof ServerPlayer && !this.isSilent()) {
//                    ((ServerPlayer)entity1).connection.send(new ClientboundGameEventPacket(ClientboundGameEventPacket.ARROW_HIT_PLAYER, 0.0F));
//                }
//
//                //十字弩击杀成就相关代码删掉
//            }
//            //音效还是使用原版的弓箭音效吧，没有看到什么更好的
//            this.playSound(getDefaultHitGroundSoundEvent(), 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
//        }
//
//    }

    //数据存储和删除实体
    protected void tickDespawn() {
        ++this.discardTick;
        if (this.discardTick >= 20 * 5) {
            this.discard();
        }

    }

    public double PointDistanceToLine(Vec3 point, Vec3 line1, Vec3 line2) { //点到线段的距离
        Vec3 lineVec = line1.vectorTo(line2);   //正向线大小
        double dot1 = lineVec.dot(point.vectorTo(line2));   //p->line2 和 line1->line2
        double dot2 = lineVec.reverse().dot(point.vectorTo(line1)); //p->line1 和 line2->line1

        if(dot1 > 0 && dot2 > 0) {  //两个锐角，说明点到线段的垂线在线段内
            return dot1 / (line1.length());   //算出高
        } else {    //有一个不是锐角，垂足在线段外，按照线段距离算
            return Math.min(line1.distanceTo(point), line2.distanceTo(point));  //两个到端点的距离取最短
        }

    }

}
