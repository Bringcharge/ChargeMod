package com.charge.chargemod.network;

import com.charge.chargemod.network.packet.*;
import com.charge.chargemod.network.packet.particle.ParticleCreateToClientPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.PacketDistributor;

import java.util.List;

public class ChargePacketSender {
    public static void sendLingqiMessageToServer(String message, int add) {
        PlayerLingQiToServerPacket packet = new PlayerLingQiToServerPacket(message, add);
        ChargeNetwork.CHANNEL.sendToServer(packet);
    }

    public static void sendLingqiMessageToClient(ServerPlayer player, int lingqi) {
        PlayerLingQiToClientPacket packet = new PlayerLingQiToClientPacket(lingqi);
        ChargeNetwork.CHANNEL.send(PacketDistributor.PLAYER.with(() -> player), packet);
    }
    public static void sendCrossCalamityMessageToClient(ServerPlayer player, int type) {
        PlayerCrossCalamityToClientPacket packet = new PlayerCrossCalamityToClientPacket(type);
        ChargeNetwork.CHANNEL.send(PacketDistributor.PLAYER.with(() -> player), packet);
    }

    public static void sendMovementMessageToClient(ServerPlayer player, float x, float y, float z) {
        PlayerMovementPacket packet = new PlayerMovementPacket(x, y, z);
        ChargeNetwork.CHANNEL.send(PacketDistributor.PLAYER.with(() -> player), packet);
    }

    public static void sendTeleportBlockMessageToServer(int pos_x, int pos_y, int pos_z, int target_x, int target_y, int target_z) {
        PlayerTeleportBlockToServerPacket packet = new PlayerTeleportBlockToServerPacket(pos_x, pos_y, pos_z, target_x, target_y, target_z);
        ChargeNetwork.CHANNEL.sendToServer(packet);
    }

    public static void sendParticleTypeToClient(ServerPlayer player, double x, double y, double z, double speed_x, double speed_y, double speed_z, String type) {
        ParticleCreateToClientPacket packet = new ParticleCreateToClientPacket(x, y, z, speed_x, speed_y, speed_z, type);
        ChargeNetwork.CHANNEL.send(PacketDistributor.PLAYER.with(() -> player), packet);
    }
    public static void sendParticleTypeToAllPlayer(ServerLevel level, double x, double y, double z, double speed_x, double speed_y, double speed_z, String type) {
        List<ServerPlayer> playerList = level.getPlayers((test)->true);
        for (ServerPlayer player : playerList) {
            sendParticleTypeToClient(player, x, y, z, speed_x, speed_y, speed_z, type);
        }
    }
}
