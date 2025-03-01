package com.charge.chargemod.event;

import com.charge.chargemod.ChargeModItemRegistry;
import com.charge.chargemod.lingqi.PlayerLingQi;
import com.charge.chargemod.network.ChargeNetwork;
import com.charge.chargemod.network.ChargePacketSender;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;

import java.util.Optional;

@Mod.EventBusSubscriber(modid = ChargeModItemRegistry.MODID,bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ChargeForgeEvent{

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if(event.side == LogicalSide.SERVER) {
            LazyOptional<PlayerLingQi> optional = event.player.getCapability(ChargeModItemRegistry.PLAYER_LING_QI);
            optional.ifPresent(playerLingQi -> {
                //TODO:肯能要加入聚灵阵之类的东西
                playerLingQi.addLingQi(1);
                ChargePacketSender.sendLingqiMessageToClient((ServerPlayer) event.player, playerLingQi.getLingQi());
//                event.player.sendSystemMessage(Component.literal("Subtracted Thirst"));

            });
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

}