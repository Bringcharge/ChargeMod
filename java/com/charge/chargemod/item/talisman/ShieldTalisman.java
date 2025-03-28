package com.charge.chargemod.item.talisman;

import com.mojang.blaze3d.shaders.Effect;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

//御劫符
public class ShieldTalisman extends ChargeBaseTalisman {


    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {

        player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 20 * 30, 1));
        return super.use(level,player,hand);
    }
}
