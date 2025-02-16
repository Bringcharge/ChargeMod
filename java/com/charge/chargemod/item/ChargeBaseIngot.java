package com.charge.chargemod.item;


import com.charge.chargemod.instructions.manager.Instruction;
import com.charge.chargemod.instructions.manager.InstructionsManager;
import com.charge.chargemod.instructions.manager.InstructionsModel;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ChargeBaseIngot extends Item {
    public ChargeBaseIngot() {
        super(new Item.Properties());
    }
    //普通物品单击右键
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        InstructionsModel model = new InstructionsModel();
        model.user = player;
        model.holder = this;
        Instruction ins = new Instruction();
//        ins.str = "F002#V103#V101#DI4#{Fif#B001#E201#{F004#E101#E201#}{F003#E101#V201#}}";    大概是一个射箭然后射中了会触发if判断的东西
        ins.str = "F005#V020#V103#V101#";
        InstructionsManager.functionWithString(ins, model);    //命令字符串

        return InteractionResultHolder.success(stack);
        //如果消耗的话做下面这个
//        if (!level.isClientSide) {
//            // 在服务端执行逻辑
//            player.sendSystemMessage(Component.literal("Item consumed!")); // 发送消息给玩家
//            stack.shrink(1); // 消耗一个物品
//        }
//        return InteractionResultHolder.consume(stack); // 返回消耗结果
    }

}
