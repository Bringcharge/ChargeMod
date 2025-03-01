package com.charge.chargemod.network;

import com.charge.chargemod.lingqi.PlayerLingQiToClientPacket;
import com.charge.chargemod.lingqi.PlayerLingQiToServerPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.PacketDistributor;

public class ChargePacketSender {
    public static void sendLingqiMessageToServer(String message, int add) {
        PlayerLingQiToServerPacket packet = new PlayerLingQiToServerPacket(message, add);
        ChargeNetwork.CHANNEL.sendToServer(packet);
    }

    public static void sendLingqiMessageToClient(ServerPlayer player, int lingqi) {
        PlayerLingQiToClientPacket packet = new PlayerLingQiToClientPacket(lingqi);
        ChargeNetwork.CHANNEL.send(PacketDistributor.PLAYER.with(() -> player), packet);;
    }

}
