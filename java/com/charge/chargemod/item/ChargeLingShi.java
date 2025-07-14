package com.charge.chargemod.item;

import com.charge.chargemod.effect.ModEffects;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class ChargeLingShi extends Item {
    public ChargeLingShi() {
        super(new Item.Properties());
    }
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

//        如果消耗的话做下面这个
        if (!level.isClientSide) {
            // 在服务端执行逻辑
            player.addEffect(new MobEffectInstance(ModEffects.LING_QI_INCREASE.get(), 20 * 10, 1));
            stack.shrink(1); // 消耗一个物品
        }
        return InteractionResultHolder.consume(stack); // 返回消耗结果
    }

    @Override
    public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
        return 1600;
    }
}
