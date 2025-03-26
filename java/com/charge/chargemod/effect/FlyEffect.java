package com.charge.chargemod.effect;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.player.Abilities;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameType;

public class FlyEffect extends MobEffect{
    // 第一个是分类，表示药水效果是好的还是坏的，第二个是药水粒子效果的颜色是否正常
    protected FlyEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int level) {
        super.removeAttributeModifiers(entity, attributeMap, level);
        //可以加个判断
        if (entity instanceof ServerPlayer) {
            ServerPlayer player = (ServerPlayer)entity;
            Abilities abilities = player.getAbilities();
            // 创造模式下允许飞行
            abilities.mayfly = player.gameMode.isCreative();       // 创造模式下允许飞行
            // 更新玩家的能力状态
            player.onUpdateAbilities();
        }
    }
    //增加效果
    public void addAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int level) {
        super.addAttributeModifiers(entity, attributeMap, level);
        //可以加个判断
        if (entity instanceof Player) {
            Player player = (Player)entity;
            Abilities abilities = player.getAbilities();
            abilities.mayfly = true;
            // 更新玩家的能力状态
            player.onUpdateAbilities();
        }
    }
}
