package com.charge.chargemod.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class ExampleEffect extends MobEffect {
    // 第一个是分类，表示药水效果是好的还是坏的，第二个是药水粒子效果的颜色是否正常
    protected ExampleEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }
    // 这里是具体的效果，这里我们模仿原版的生命恢复的效果，每5秒恢复2点生命值
    @Override
    public void applyEffectTick(LivingEntity pEntity, int pAmplifier) {
        super.applyEffectTick(pEntity, pAmplifier);
        if (pEntity.getHealth() < pEntity.getMaxHealth()) {
            pEntity.heal(2.0F);
        }
    }
    // 这里是判断是否应该应用效果的，这里我们每5秒应用一次效果
    @Override
    public boolean isDurationEffectTick(int tick, int amp) {
        if (tick % 5 == 0) {
            return true;
        }else{
            return false;
        }
    }
}
