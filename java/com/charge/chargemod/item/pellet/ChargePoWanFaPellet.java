package com.charge.chargemod.item.pellet;

import com.charge.chargemod.ChargeModItemRegistry;
import com.charge.chargemod.damage.ChargeDamageTypes;
import com.charge.chargemod.damage.DaoFaDamageSource;
import com.charge.chargemod.entity.calamity.CalamityLightning;
import com.charge.chargemod.lingqi.PlayerLingQiHelper;
import com.charge.chargemod.network.ChargePacketSender;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.Random;

//破万法丹
public class ChargePoWanFaPellet extends Item {

    public ChargePoWanFaPellet() {
        super(new Properties().food(
                new FoodProperties.Builder()
                .saturationMod(0)  //这个值越高，食物就越能减少饥饿条下降的速度。在这里，饱和度被设置为 10
                .nutrition(0)  //营养值决定了食物能恢复多少饥饿值
                .alwaysEat()
                .fast()
                .build()
        ));
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        if (entity instanceof Player) {
            Player player = (Player)entity;
            List<MobEffectInstance> effects = player.getActiveEffects().stream().toList();
            for (MobEffectInstance mobEffectInstance : effects) {       //判断buff是否有害
                MobEffect effect = mobEffectInstance.getEffect();
                if (effect.getCategory() == MobEffectCategory.HARMFUL) {
                    player.removeEffect(effect);
                }
            }
        }
        return super.finishUsingItem(stack, level, entity); //能吃的
    }
}
