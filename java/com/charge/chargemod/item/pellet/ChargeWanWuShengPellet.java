package com.charge.chargemod.item.pellet;

import com.charge.chargemod.effect.ModEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

//万物生丹
public class ChargeWanWuShengPellet extends Item {

    //构造参数
    public ChargeWanWuShengPellet() {
        super(new Properties().food(
                new FoodProperties.Builder()
                .saturationMod(0)  //这个值越高，食物就越能减少饥饿条下降的速度。在这里，饱和度被设置为 10
                .nutrition(0)  //营养值决定了食物能恢复多少饥饿值
                .effect(()-> new MobEffectInstance(ModEffects.WAN_WU_SHENG_EFFECT.get(),600 * 20),1)   //后面那个是概率
                .alwaysEat()
                .fast()
                .build()
        ));
    }

}
