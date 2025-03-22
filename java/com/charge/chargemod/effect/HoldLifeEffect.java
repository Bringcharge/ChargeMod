package com.charge.chargemod.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;

public class HoldLifeEffect extends MobEffect{
    // 第一个是分类，表示药水效果是好的还是坏的，第二个是药水粒子效果的颜色是否正常
    protected HoldLifeEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int level) {
        super.removeAttributeModifiers(entity, attributeMap, level);
        //可以加个判断
        entity.kill();  //吊命结束后完蛋
    }
    //增加效果
    public void addAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int level) {
        super.addAttributeModifiers(entity, attributeMap, level);
    }
}
