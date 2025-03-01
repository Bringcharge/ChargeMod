package com.charge.chargemod.item;


import com.charge.chargemod.lingqi.PlayerLingQiHelper;
import com.charge.chargemod.network.ChargePacketSender;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.TorchBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

import java.util.Random;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class ChargeBow  extends BowItem {

    public ChargeBow(Item.Properties builder) {
        super(builder);
    }

    //注：this.remove()可以移除该entity
    public class ObsidianArrowEntity extends Arrow {
        public ObsidianArrowEntity(EntityType<? extends Arrow> type, Level level) {
            super(type, level);
        }
        public ObsidianArrowEntity(Level level, double x, double y, double z) {
            super(level,x,y,z);
        }
        public ObsidianArrowEntity(Level level, LivingEntity shooter) { super(level,shooter); }

        private BiConsumer<Integer, Vec3> biConsumer;
        private Consumer<Vec3> consumer;

        public void setBack(BiConsumer<Integer, Vec3> biConsumer) {
            this.biConsumer = biConsumer;
        }
        public void setConsumer(Consumer<Vec3> consumer) {this.consumer = consumer;}

        @Override
        protected void onHitEntity(EntityHitResult result) {
            Entity entity = result.getEntity(); // 获取击中的实体
            Level level = this.level(); // 获取当前世界

            if (!level.isClientSide) {
                // 在服务端执行逻辑
                if (this.biConsumer != null) {
                    this.biConsumer.accept(20, entity.getPosition(0.5f));  //目前的20是一个延迟后发射的额外箭雨
                }
            }

            super.onHitEntity(result); // 调用父类逻辑
        }

        @Override
        protected void onHitBlock(BlockHitResult result) {
            Level level = this.level(); // 获取当前世界
            BlockPos pos = result.getBlockPos(); // 获取击中方块的坐标
            BlockState state = level.getBlockState(pos); // 获取方块状态
            Vec3 vec3d = result.getLocation();

            if (!level.isClientSide) {
                if (this.biConsumer != null) {
                    this.consumer.accept(vec3d);
                }
            }

            super.onHitBlock(result); // 调用父类逻辑
        }
    }

    private int delayTick;
    private int arrowNumber;
    private Vec3 target3d;

    @Override
    //tick响应函数
    public void inventoryTick(ItemStack stack, Level worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        super.inventoryTick(stack, worldIn, entityIn, itemSlot, isSelected);
        if (this.delayTick > 0) {
            this.delayTick--;
        }
        else if (this.arrowNumber > 0) {
            if (!worldIn.isClientSide()) {
                this.arrowNumber--;
                this.createArrowItem(this.target3d, entityIn.level());
                this.delayTick = 1;
            } else {
                System.out.println("这个会在本地运行");
            }
        }
    }

    //箭雨的造箭方法
    private void createArrowItem(Vec3 target3d, Level worldI) {
        Random random = new Random();
        Vec3 arrowCreatePosition = target3d.add((random.nextGaussian()-0.5) * 8.f ,20, (random.nextGaussian() -0.5) * 8.f);
        Vec3 vectorToTarget = target3d.add(arrowCreatePosition.reverse());

        //创建箭矢
        ArrowItem arrowitem = (ArrowItem)(Items.ARROW); //最原版的箭矢
        Arrow arrow = new Arrow(worldI ,arrowCreatePosition.x(),arrowCreatePosition.y(),arrowCreatePosition.z() );

        //设置初速度和散布
        arrow.shoot(vectorToTarget.x(),vectorToTarget.y(),vectorToTarget.z(),2.0f,3.f);
//        abstractarrowentity.shoot(0,-1,0,2.0f,3.f);
//        arrow.setIsCritical(false);    //箭后面是否带有大量暴击粒子
        worldI.addFreshEntity(arrow);
    }

    private void flash(LivingEntity livingEntity,Vec3  targetPlace) {
        Level world = livingEntity.level();
        if (!world.isClientSide) {
            livingEntity.teleportTo(targetPlace.x(), targetPlace.y(), targetPlace.z());
            livingEntity.fallDistance = 0.0F;
        }
    }

    //射箭方法
    @Override
    public void releaseUsing(ItemStack stack, Level worldIn, LivingEntity entityLiving, int timeLeft) {
        Random random = new Random();
        if (entityLiving instanceof Player) {
            Player player = (Player)entityLiving;
            boolean flag =player.getAbilities().instabuild || EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, stack) > 0;
            ItemStack itemstack = player.getProjectile(stack);//寻找弹药的函数，根据父类的函数修改之后可以匹配相应的弹药

            int i = this.getUseDuration(stack) - timeLeft;
            i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(stack, worldIn, player, i, !itemstack.isEmpty() || flag);
            if (i < 0) return;

            if (!itemstack.isEmpty() || flag) {
                if (itemstack.isEmpty()) {
                    itemstack = new ItemStack(Items.ARROW);
                }

                float f = getPowerForTime(i);   //拉弓时间计算出弓箭初速
                if (!((double)f < 0.1D)) {
                    boolean flag1 = player.getAbilities().instabuild || (itemstack.getItem() instanceof ArrowItem && ((ArrowItem)itemstack.getItem()).isInfinite(itemstack, stack, player));
                    if (!worldIn.isClientSide()) {    //是服务器

                        boolean canUse = PlayerLingQiHelper.consumeLingQi(player,100);
                        if (!canUse) {
                            return;
                        } else {
                            ChargePacketSender.sendLingqiMessageToClient((ServerPlayer) player, PlayerLingQiHelper.getLingQi(player));
                        }


                        ArrowItem arrowitem = (ArrowItem)(itemstack.getItem() instanceof ArrowItem ? itemstack.getItem() : Items.ARROW);
                        ObsidianArrowEntity abstractarrowentity = new ObsidianArrowEntity(worldIn,player);

                        //设置击中怪物后的函数
                        abstractarrowentity.setBack((a,b)->{
                            this.arrowNumber = a;
                            this.target3d = b;
                        });

                        //击中地面时候的函数
                        abstractarrowentity.setConsumer((e)->{
                            this.flash(entityLiving,e);
                        });

                        abstractarrowentity.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, f * 9.0F, 1.0F);
                        if (f == 1.0F) {
//                            abstractarrowentity.setIsCritical(true);    //暴击粒子？
                        }
//                        int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);
//                        if (j > 0) {
//                            abstractarrowentity.setDamage(abstractarrowentity.getDamage() + (double)j * 0.5D + 0.5D);
//                        }

                        //击退
//                        abstractarrowentity.setKnockbackStrength(-5);


//                        if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, stack) > 0) {
//                            abstractarrowentity.setFire(100);
//                        }

//                        stack.damageItem(1, playerentity, (p_220009_1_) -> {
//                            p_220009_1_.sendBreakAnimation(playerentity.getActiveHand());
//                        });
                        if (flag1 || player.getAbilities().instabuild && (itemstack.getItem() == Items.SPECTRAL_ARROW || itemstack.getItem() == Items.TIPPED_ARROW)) {
                            abstractarrowentity.pickup = AbstractArrow.Pickup.DISALLOWED;
                        }

                        worldIn.addFreshEntity(abstractarrowentity);
                    }

                    worldIn.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1.0F, 1.0F / (random.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
                    if (!flag1 && !player.getAbilities().instabuild) {
                        itemstack.shrink(1);    //消耗弓箭
                        if (itemstack.isEmpty()) {
                            player.getInventory().removeItem(itemstack);
                        }
                    }

                    player.awardStat(Stats.ITEM_USED.get(this));
                }
            }
        }
    }
}