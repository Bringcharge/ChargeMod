package com.charge.chargemod.item.talisman;

import com.mojang.blaze3d.shaders.Effect;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
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
        level.playSound(
                null,                     // 无特定来源实体（全局声音）
                BlockPos.containing(player.position()), // 声音位置
                SoundEvents.ANVIL_LAND, // 声音事件（原版或自定义）
                SoundSource.PLAYERS,       // 声音类别（BLOCKS, PLAYERS, AMBIENT 等）
                1.0F, 1.0F                // 音量、音高
        );
        player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 20 * 30, 1));
        return super.use(level,player,hand);
    }
}
