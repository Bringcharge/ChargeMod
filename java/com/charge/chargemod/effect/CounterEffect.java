package com.charge.chargemod.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class CounterEffect extends MobEffect {
    protected CounterEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }
    // 这里是具体的效果，这里我们模仿原版的生命恢复的效果，每5秒恢复2点生命值
}
