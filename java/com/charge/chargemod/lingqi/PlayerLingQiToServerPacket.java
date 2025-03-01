package com.charge.chargemod.lingqi;

import com.charge.chargemod.ChargeModItemRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.PacketDistributor;

import java.util.Optional;
import java.util.function.Supplier;

public class PlayerLingQiToServerPacket {
    private final String message;
    private final int add;

    public PlayerLingQiToServerPacket(String message, int consumer) {
        this.message = message;
        this.add = consumer;
    }

    public PlayerLingQiToServerPacket(FriendlyByteBuf buf) {
        this.message = buf.readUtf();
        this.add = buf.readInt();
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeUtf(message);
        buf.writeInt(add);
    }

    public void handle(Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            // 在服务器端处理数据包
            System.out.println("Received packet: message=" + message + ", add=" + add);
            ServerPlayer player = context.get().getSender();
            if (player != null) {
                // 服务端的level
                ServerLevel level = (ServerLevel) player.level();
                // 检测是消耗指令还是增加指令
                if(message.equals("dec") || message.equals("sub") || message.equals("consume")){
                    //消耗指令则减少
                    PlayerLingQiHelper.consumeLingQi(player,add);
                } else if (message.equals("add")){
                    //增加指令
                    PlayerLingQiHelper.addLingQi(player,add);
                }
            }
        });
        context.get().setPacketHandled(true);
    }

    // 判断玩家附近是否有水方块
    private boolean hasWaterAroundThem(Player player, ServerLevel level, int size) {
        return level.getBlockStates(player.getBoundingBox().inflate(size))
                .filter(state -> state.is(Blocks.WATER)).toArray().length > 0;
    }

    public static ResourceLocation getId() {
        return new ResourceLocation(ChargeModItemRegistry.MODID, "player_lingqi_custompacket");
    }
}
