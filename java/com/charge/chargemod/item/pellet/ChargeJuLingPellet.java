package com.charge.chargemod.item.pellet;

import com.charge.chargemod.effect.ModEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

//辟谷丹
public class ChargeJuLingPellet extends Item {

    //构造参数
    private static final FoodProperties FOOD_PROPERTIES = new FoodProperties.Builder()
            .saturationMod(10)
            .nutrition(20)
            .effect(()-> new MobEffectInstance(ModEffects.LING_QI_INCREASE.get(),30*20,4),1)
            .alwaysEat()
            .fast()
            .build();

    public ChargeJuLingPellet() {
        super(new Properties().food(FOOD_PROPERTIES));
    }

}
