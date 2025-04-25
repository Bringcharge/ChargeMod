package com.charge.chargemod.event;

import com.charge.chargemod.ChargeModItemRegistry;
import com.charge.chargemod.damage.ChargeDamageTypes;
import com.charge.chargemod.damage.DaoFaDamageSource;
import com.charge.chargemod.item.talisman.HoldLifeTalisman;
import com.charge.chargemod.lingqi.PlayerLingQi;
import com.charge.chargemod.network.ChargeNetwork;
import com.charge.chargemod.network.ChargePacketSender;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.EntityTeleportEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;
import org.checkerframework.common.returnsreceiver.qual.This;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Mod.EventBusSubscriber(modid = ChargeModItemRegistry.MODID,bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ChargeForgeEvent{
    public static long  tickCount = 0;

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if(event.side == LogicalSide.SERVER) {  //灵气恢复
            tickCount++;
            if (tickCount % 10 == 0) {
                LazyOptional<PlayerLingQi> optional = event.player.getCapability(ChargeModItemRegistry.PLAYER_LING_QI);
                optional.ifPresent(playerLingQi -> {

                    List<MobEffectInstance> effects = event.player.getActiveEffects().stream().toList();    //确认是否有灵气buff
                    for (MobEffectInstance mobEffectInstance : effects) {       //判断是否有buff
                        MobEffect effect = mobEffectInstance.getEffect();
                        if (effect.getDescriptionId().contains("ling_qi_increase")) {
                            int amp = mobEffectInstance.getAmplifier();    //buff等级
                            playerLingQi.addLingQi(amp * 2);
                            break;
                        }
                    }
                    playerLingQi.addLingQi(1);
                    ChargePacketSender.sendLingqiMessageToClient((ServerPlayer) event.player, playerLingQi.getLingQi());
//                event.player.sendSystemMessage(Component.literal("Subtracted Thirst"));

                });
            }
        }

        if (event.side == LogicalSide.SERVER) { // 只在 tick 开始时检查
            if (tickCount % 10 == 0) { // 每 10 tick 检测一次
                Player player = event.player;
                boolean flag = false;

                List<MobEffectInstance> effects = player.getActiveEffects().stream().toList();
                for (MobEffectInstance mobEffectInstance : effects) {       //判断是否有buff
                    MobEffect effect = mobEffectInstance.getEffect();
                    if (effect.getDescriptionId().contains("wan_wu_sheng_effect")) { //万物生
                        flag = true;
                    }
                }

                if (flag) { //有buff的情况下
                    AABB playerBoundingBox = player.getBoundingBox(); // 获取玩家的碰撞箱
                    // 获取玩家周围的所有实体
                    List<Entity> entities = player.level().getEntities(player, playerBoundingBox.inflate(1.0));

                    for (Entity entity : entities) {
                        if (entity instanceof Animal && !(entity instanceof Player)) {
                            Animal animal = (Animal) entity;
                            // 检查碰撞箱是否重叠
                            if (animal.canFallInLove() && playerBoundingBox.intersects(entity.getBoundingBox())) {    //玩家与生物发生碰撞

                                animal.setInLove(player);
                                animal.spawnChildFromBreeding((ServerLevel) player.level(), animal);
                            }
                        }
                    }
                }
            }
        }

    }

//    @SubscribeEvent
//    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
//        if (event.phase == TickEvent.Phase.START) { // 只在 tick 开始时检查
//            tickCounter++;
//            if (tickCounter % 10 == 0) { // 每 10 tick 检测一次
//                Player player = event.player;
//                AABB playerBoundingBox = player.getBoundingBox(); // 获取玩家的碰撞箱
//
//                // 获取玩家周围的所有实体
//                List<Entity> entities = player.level().getEntities(player, playerBoundingBox.inflate(1.0));
//
//                for (Entity entity : entities) {
//                    if (entity instanceof LivingEntity && !(entity instanceof Player)) {
//                        LivingEntity livingEntity = (LivingEntity) entity;
//                        // 检查碰撞箱是否重叠
//                        if (playerBoundingBox.intersects(entity.getBoundingBox())) {
//                            System.out.println("玩家 " + player.getName().getString() + " 与 " + livingEntity.getName().getString() + " 发生碰撞！");
//                        }
//                    }
//                }
//            }
//        }
//    }

    // 当玩家刚刚加入世界时候同步数据
    @SubscribeEvent
    public static void onPlayerJoinWorld(EntityJoinLevelEvent event) {
        if(!event.getLevel().isClientSide()) {
            if(event.getEntity() instanceof ServerPlayer) {
                ServerPlayer player = (ServerPlayer) event.getEntity();
                LazyOptional<PlayerLingQi> optional = player.getCapability(ChargeModItemRegistry.PLAYER_LING_QI);
                optional.ifPresent(playerLingQi -> {
                    ChargePacketSender.sendLingqiMessageToClient((ServerPlayer) player, playerLingQi.getLingQi());
                });
            }
        }
    }

    //末影人传送事件
    @SubscribeEvent
    public static void onEnderManPortEvent(EntityTeleportEvent event) {
        if (event.getEntity() instanceof EnderMan) {
            EnderMan enderMan = (EnderMan)event.getEntity();
            List<MobEffectInstance> effects = enderMan.getActiveEffects().stream().toList();
            for (MobEffectInstance mobEffectInstance : effects) {
                MobEffect effect = mobEffectInstance.getEffect();
                if (effect.getDescriptionId().contains("silent_effect")) {
                    event.setCanceled(true);
                }
            }
        }
    }

    //受伤事件
    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent event) {

        //反击
        if (event.getEntity() instanceof Player) {
            if (event.getSource().is(DamageTypes.GENERIC_KILL)) {   //如果是即死命令，那就不处理了
                return;
            }
            Player player = (Player) event.getEntity();
            List<MobEffectInstance> effects = player.getActiveEffects().stream().toList();

            for (MobEffectInstance mobEffectInstance : effects) {       //判断是否有buff
                MobEffect effect = mobEffectInstance.getEffect();
                if (effect.getDescriptionId().contains("counter_effect")) { //反击

                    Entity entity = event.getSource().getEntity();  //获取伤害源头
                    if (entity instanceof LivingEntity) {
                        LivingEntity livingEntity = (LivingEntity) entity;
                        ChargePacketSender.sendMovementMessageToClient((ServerPlayer) player, (float) livingEntity.position().x,(float)livingEntity.position().y,(float)livingEntity.position().z);
                        livingEntity.hurt(DaoFaDamageSource.source(player, ChargeDamageTypes.DAO_HEAVY), 10);
                    }
                    player.removeEffect(effect);    //移除反击buff
                    event.setCanceled(true);
                    return;
                }
                if (effect.getDescriptionId().contains("suan_bu_dui_effect")) { //算不准
                    float amount = event.getAmount();
                    Random random = new Random();
                    float discount = random.nextFloat(amount);
                    event.setAmount(amount - discount); //其实减法和直接用随机数区别不大，先保留一个减法，说不定后期有改动
                }
            }
        }

        //吊命
        if (event.getEntity() instanceof Player && event.getEntity().getHealth() <= event.getAmount()) {

            if (event.getSource().is(DamageTypes.GENERIC_KILL)) {   //如果是即死命令，那就不处理了
                return;
            }
            Player player = (Player) event.getEntity();
            List<MobEffectInstance> effects = player.getActiveEffects().stream().toList();
            boolean flag = false;
            for (MobEffectInstance mobEffectInstance : effects) {       //判断是否有buff
                MobEffect effect = mobEffectInstance.getEffect();
                if (effect.getDescriptionId().contains("hold_life_effect")) {
                    flag = true;
                }
            }
            if (!flag) {
                Inventory inventory = player.getInventory();
                for (ItemStack stack : inventory.items) {
                    if (stack.is(ChargeModItemRegistry.HOLD_LIFE_TALISMAN.get())) {
                        HoldLifeTalisman.easyUse(player.level(), player, stack);
                        flag = true;
                        break;
                    }
                }
            }
            if (!flag) {
                Inventory inventory = player.getInventory();
                for (ItemStack stack : inventory.offhand) {
                    if (stack.is(ChargeModItemRegistry.HOLD_LIFE_TALISMAN.get())) {
                        HoldLifeTalisman.easyUse(player.level(), player, stack);
                        flag = true;
                        break;
                    }
                }
            }
            if (flag) {
                event.setCanceled(true);
            }
        }
    }

}