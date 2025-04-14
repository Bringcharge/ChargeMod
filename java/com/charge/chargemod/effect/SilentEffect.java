package com.charge.chargemod.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.goal.SwellGoal;
import net.minecraft.world.entity.ai.goal.WrappedGoal;
import net.minecraft.world.entity.monster.Creeper;

import java.util.List;

public class SilentEffect extends MobEffect {
    public class OwnSwellGoal extends SwellGoal {
        private final Creeper creeper_temp;
        public OwnSwellGoal(Creeper p_25919_) {
            super(p_25919_);
            creeper_temp = p_25919_;
        }

        @Override
        public void tick() {
            boolean flag = false;
            List<MobEffectInstance> effects = this.creeper_temp.getActiveEffects().stream().toList();
            for (MobEffectInstance effectInstance : effects) {
                MobEffect effect = effectInstance.getEffect();
                if (effect.getDescriptionId().contains("silent_effect")) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                //自定义
                this.creeper_temp.setSwellDir(-1);
            } else {
                super.tick();
            }

        }
    }

    protected SilentEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    @Override
    public void addAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int level) {
        if (entity instanceof Creeper) {    //如果是creeper吃了buff，修改ai
            Creeper creeper = (Creeper) entity;

            boolean flag = false;
            List<WrappedGoal> list = creeper.goalSelector.getAvailableGoals().stream().toList();
            for (WrappedGoal wrappedGoal : list) {
                if (wrappedGoal.getGoal() instanceof SwellGoal) {
                    creeper.goalSelector.removeGoal(wrappedGoal.getGoal());
                    flag = true;
                    break;
                }
            }
            if (flag) { //删对了东西
                creeper.goalSelector.addGoal(2, new OwnSwellGoal(creeper));
            }
        }
        super.addAttributeModifiers(entity, attributeMap, level);
    }
}
