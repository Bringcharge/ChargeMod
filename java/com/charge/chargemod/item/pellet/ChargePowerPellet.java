package com.charge.chargemod.item.pellet;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

//生力丹
public class ChargePowerPellet extends Item {

    //构造参数
    public ChargePowerPellet() {
        super(new Properties().food(
                new FoodProperties.Builder()
                .saturationMod(0)  //这个值越高，食物就越能减少饥饿条下降的速度。在这里，饱和度被设置为 10
                .nutrition(0)  //营养值决定了食物能恢复多少饥饿值
                .effect(()-> new MobEffectInstance(MobEffects.ABSORPTION,60 * 20,5),1)   //后面那个是概率
                .effect(()-> new MobEffectInstance(MobEffects.HEALTH_BOOST,60 * 20,5),1)   //后面那个是概率
                .alwaysEat()
                .fast()
                .build()
        ));
    }

}
