package com.charge.chargemod.item;


import com.charge.chargemod.ChargeModItemRegistry;
import com.charge.chargemod.entity.ChargeBladeExtendEntity;
import com.charge.chargemod.entity.ChargeDaggerEntity;
import com.charge.chargemod.entity.calamity.CalamityLightning;
import com.charge.chargemod.entity.calamity.CalamitySanShi;
import com.charge.chargemod.entity.calamity.CalamityZombie;
import com.charge.chargemod.instructions.manager.InstructionsModel;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.Random;

public class ChargeBaseIngot extends Item {
    public ChargeBaseIngot() {
        super(new Item.Properties());
    }
    //普通物品单击右键
//    @Override
//    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
//        ItemStack stack = player.getItemInHand(hand);
//        InstructionsModel model = new InstructionsModel();
//        model.user = player;
//        model.holder = stack;
//
//        releaseUsing(stack, level, player, 10);   //临时射箭代码
////        Instruction ins = new Instruction();
////        ins.str = "F002#V103#V101#DI4#{Fif#B001#E201#{F004#E101#E201#}{F003#E101#V201#}}";    大概是一个射箭然后射中了会触发if判断的东西
////        ins.str = "F004#E101#E301#V103#V101#E101#"; //视野看到的单位，交换位置
////        InstructionsManager.functionWithString(ins, model);    //命令字符串
//
////          这个是单机的粒子渲染逻辑
////        if (level.isClientSide) {
////            //addParticle(ParticleOptions particleData, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed);
////            Vec3 eyePosition = player.getEyePosition(1.0F);
////            Vec3 lookVector = player.getLookAngle();
////            Vec3 vec_to = lookVector.add(eyePosition);
////            level.addParticle(ChargeModParticleType.SWORD_BLADE_PARTICLE_TYPE.get(), vec_to.x, vec_to.y, vec_to.z,  0,0,0);
////        }
//
//        return InteractionResultHolder.success(stack);
//        //如果消耗的话做下面这个
////        if (!level.isClientSide) {
////            // 在服务端执行逻辑
////            player.sendSystemMessage(Component.literal("Item consumed!")); // 发送消息给玩家
////            stack.shrink(1); // 消耗一个物品
////        }
////        return InteractionResultHolder.consume(stack); // 返回消耗结果
//    }
    //以下是临时测试

    private int delayTick;
    private int arrowNumber;
    private Vec3 target3d;

    private void createArrowItem(Vec3 target3d, Level worldI) {
        Random random = new Random();
        Vec3 arrowCreatePosition = target3d.add((random.nextGaussian()-0.5) * 8.f ,20, (random.nextGaussian() -0.5) * 8.f);
        Vec3 vectorToTarget = target3d.add(arrowCreatePosition.reverse());

        //创建箭矢
        ArrowItem arrowitem = (ArrowItem)(Items.ARROW); //最原版的箭矢
        ChargeDaggerEntity arrow = new ChargeDaggerEntity(worldI ,arrowCreatePosition.x(),arrowCreatePosition.y(),arrowCreatePosition.z() );

        //设置初速度和散布
        arrow.shoot(vectorToTarget.x(),vectorToTarget.y(),vectorToTarget.z(),2.0f,3.f);
//        abstractarrowentity.shoot(0,-1,0,2.0f,3.f);
//        arrow.setIsCritical(false);    //箭后面是否带有大量暴击粒子
        worldI.addFreshEntity(arrow);
    }

    @Override
    //tick响应函数
    public void inventoryTick(ItemStack stack, Level worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        super.inventoryTick(stack, worldIn, entityIn, itemSlot, isSelected);
        if (this.delayTick > 0) {
            this.delayTick--;
        }
        else if (this.arrowNumber > 0) {
            this.arrowNumber--;
            this.createArrowItem(this.target3d,entityIn.level());
            this.delayTick = 1;
        }
    }

    public void releaseUsing(ItemStack stack, Level worldIn, LivingEntity entityLiving, int timeLeft) {
        Random random = new Random();
        if (entityLiving instanceof Player) {
            Player player = (Player)entityLiving;
            boolean flag =player.getAbilities().instabuild || EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, stack) > 0;
            ItemStack itemstack = player.getProjectile(stack);//寻找弹药的函数，根据父类的函数修改之后可以匹配相应的弹药

            int i = 20;
            i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(stack, worldIn, player, i, !itemstack.isEmpty() || flag);
            if (i < 0) return;

            if (!itemstack.isEmpty() || flag) {
                if (itemstack.isEmpty()) {
                    itemstack = new ItemStack(Items.ARROW);
                }

                float f = 4;   //拉弓时间计算出弓箭初速
                if (!((double)f < 0.1D)) {
                    boolean flag1 = player.getAbilities().instabuild || (itemstack.getItem() instanceof ArrowItem && ((ArrowItem)itemstack.getItem()).isInfinite(itemstack, stack, player));
                    if (!worldIn.isClientSide) {
                        ArrowItem arrowitem = (ArrowItem)(itemstack.getItem() instanceof ArrowItem ? itemstack.getItem() : Items.ARROW);
                        ChargeBladeExtendEntity abstractarrowentity = new ChargeBladeExtendEntity(worldIn,player);

                        //击中地面时候的函数
//                        abstractarrowentity.setConsumer((e)->{
//                            this.flash(entityLiving,e);
//                        });

                        abstractarrowentity.shoot(player.getLookAngle(), 0.5f);
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

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {   //渡劫demo

//        player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 30, 1));

//        CalamitySanShi sanShi = new CalamitySanShi(ChargeModItemRegistry.CALAMITY_SANSHI.get(), level);
//        CalamityZombie topZ = new CalamityZombie(ChargeModItemRegistry.CALAMITY_ZOMBIE.get(), level);
//        CalamityZombie centerZ = new CalamityZombie(ChargeModItemRegistry.CALAMITY_ZOMBIE.get(), level);
//        CalamityZombie bottomZ = new CalamityZombie(ChargeModItemRegistry.CALAMITY_ZOMBIE.get(), level);
//        sanShi.zombieTop = topZ;
//        sanShi.zombieCenter = centerZ;
//        sanShi.zombieBottom = bottomZ;
//        sanShi.owner = player;
//        topZ.owner = sanShi;
//        centerZ.owner = sanShi;
//        bottomZ.owner = sanShi;
//        sanShi.setPos(player.position());
//        topZ.setPos(player.position());
//        centerZ.setPos(player.position());
//        bottomZ.setPos(player.position());
//        level.addFreshEntity(sanShi);
//        level.addFreshEntity(topZ);
//        level.addFreshEntity(centerZ);
//        level.addFreshEntity(bottomZ);
        if (!level.isClientSide) {
            CalamityLightning sanShi = new CalamityLightning(ChargeModItemRegistry.CALAMITY_LIGHTNING.get(), level);
            sanShi.start();
            sanShi.owner = player;
            player.sendSystemMessage(Component.literal("劫云正在凝聚"));
            level.addFreshEntity(sanShi);
        }
        return super.use(level,player,hand);
    }
}
