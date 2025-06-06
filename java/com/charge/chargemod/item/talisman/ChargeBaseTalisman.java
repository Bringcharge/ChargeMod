package com.charge.chargemod.item.talisman;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

//基本符咒
public class ChargeBaseTalisman extends Item {
    public ChargeBaseTalisman(Properties p_41383_) {
        super(p_41383_);
    }

    public ChargeBaseTalisman() {
        this(new Item.Properties());
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        stack.shrink(1);//消耗一个物品
        if (stack.isEmpty()) {
            player.getInventory().removeItem(stack);
        }
        if (!player.level().isClientSide) {
            ParticleOptions particleOptions = ParticleTypes.LAVA;
            if (player.level() instanceof ServerLevel) {
                ((ServerLevel) player.level()).sendParticles(particleOptions, player.getX(), player.getY() + 1, player.getZ(), 7, 0.0D, 0.5D, 0.0D, 1.0D);//类型，xyz，count，speed_xyz,maxSpeed
            }
        }
        return InteractionResultHolder.success(stack);
    }
}
