package com.charge.chargemod.event;

import com.charge.chargemod.ChargeModItemRegistry;
import com.charge.chargemod.item.talisman.HoldLifeTalisman;
import com.charge.chargemod.lingqi.PlayerLingQi;
import com.charge.chargemod.network.ChargeNetwork;
import com.charge.chargemod.network.ChargePacketSender;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;
import org.checkerframework.common.returnsreceiver.qual.This;

import java.util.List;
import java.util.Optional;

@Mod.EventBusSubscriber(modid = ChargeModItemRegistry.MODID,bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ChargeForgeEvent{
    public static long  tickCount = 0;

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if(event.side == LogicalSide.SERVER) {
            tickCount++;
            if (tickCount % 10 == 0) {
                LazyOptional<PlayerLingQi> optional = event.player.getCapability(ChargeModItemRegistry.PLAYER_LING_QI);
                optional.ifPresent(playerLingQi -> {
                    //TODO:肯能要加入聚灵阵之类的东西
                    playerLingQi.addLingQi(1);
                    ChargePacketSender.sendLingqiMessageToClient((ServerPlayer) event.player, playerLingQi.getLingQi());
//                event.player.sendSystemMessage(Component.literal("Subtracted Thirst"));

                });
            }
        }
    }
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

    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent event) {
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