package com.charge.chargemod.item.pellet;

import com.charge.chargemod.ChargeModItemRegistry;
import com.charge.chargemod.entity.calamity.CalamitySanShi;
import com.charge.chargemod.entity.calamity.CalamityZombie;
import com.charge.chargemod.lingqi.PlayerLingQi;
import com.charge.chargemod.lingqi.PlayerLingQiHelper;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

//三尸丹
public class ChargeSanShiPellet extends Item {

    public ChargeSanShiPellet() {
        super(new Properties().food(
                new FoodProperties.Builder()
                .saturationMod(0)  //这个值越高，食物就越能减少饥饿条下降的速度。在这里，饱和度被设置为 10
                .nutrition(0)  //营养值决定了食物能恢复多少饥饿值
                .effect(()-> new MobEffectInstance(MobEffects.WITHER,3*20,1),1)
                .effect(()-> new MobEffectInstance(MobEffects.DARKNESS,3*20,1),1)
                .effect(()-> new MobEffectInstance(MobEffects.CONFUSION,3*20,1),1)
                .alwaysEat()
                .fast()
                .build()
        ));
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        if (entity instanceof Player) {
            Player player = (Player)entity;
            int calamityNumber = PlayerLingQiHelper.getMaxCalamity(player);
            if (calamityNumber < 2 && !level.isClientSide) {   //条件符合，召唤三尸
                //或许应该有个提示？
                player.sendSystemMessage(Component.literal("三尸已现，斩三尸成仙"));
                CalamitySanShi sanShi = new CalamitySanShi(ChargeModItemRegistry.CALAMITY_SANSHI.get(), level);
                CalamityZombie topZ = new CalamityZombie(ChargeModItemRegistry.CALAMITY_ZOMBIE.get(), level);
                CalamityZombie centerZ = new CalamityZombie(ChargeModItemRegistry.CALAMITY_ZOMBIE.get(), level);
                CalamityZombie bottomZ = new CalamityZombie(ChargeModItemRegistry.CALAMITY_ZOMBIE.get(), level);
                sanShi.zombieTop = topZ;
                sanShi.zombieCenter = centerZ;
                sanShi.zombieBottom = bottomZ;
                sanShi.owner = player;
                sanShi.setPos(player.position());
                topZ.owner = sanShi;
                centerZ.owner = sanShi;
                bottomZ.owner = sanShi;
                sanShi.setPos(player.position());
                topZ.setPos(player.position());
                centerZ.setPos(player.position());
                bottomZ.setPos(player.position());
                level.addFreshEntity(sanShi);
                level.addFreshEntity(topZ);
                level.addFreshEntity(centerZ);
                level.addFreshEntity(bottomZ);
            }
        }
        return super.finishUsingItem(stack, level, entity); //能吃的
    }

//    @Override
//    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
//        ItemStack stack = player.getItemInHand(hand);
//        InstructionsModel model = new InstructionsModel();
//        model.user = player;
//        model.holder = this;
//        releaseUsing(stack, level, player, 10);
//        return InteractionResultHolder.success(stack);
//        //如果消耗的话做下面这个
////        if (!level.isClientSide) {
////            // 在服务端执行逻辑
////            player.sendSystemMessage(Component.literal("Item consumed!")); // 发送消息给玩家
////            stack.shrink(1); // 消耗一个物品
////        }
////        return InteractionResultHolder.consume(stack); // 返回消耗结果
//    }
}
