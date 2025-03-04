package com.charge.chargemod.item.pellet;

import com.charge.chargemod.instructions.manager.InstructionsModel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

//辟谷丹
public class ChargeBiGuPellet extends Item {

    //普通物品单击右键
    private static final FoodProperties FOOD_PROPERTIES = new FoodProperties.Builder()
            .saturationMod(10)  //这个值越高，食物就越能减少饥饿条下降的速度。在这里，饱和度被设置为 10
            .nutrition(20)  //营养值决定了食物能恢复多少饥饿值
//            .effect(()-> new MobEffectInstance(MobEffects.POISON,3*20,1),1)
            .alwaysEat()
            .fast()
            .build();

    public ChargeBiGuPellet() {
        super(new Item.Properties().food(FOOD_PROPERTIES));
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
