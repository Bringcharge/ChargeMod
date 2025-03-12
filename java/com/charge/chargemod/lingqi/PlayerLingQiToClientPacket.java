package com.charge.chargemod.lingqi;

import com.charge.chargemod.ChargeModItemRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
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

import java.util.function.Supplier;

public class PlayerLingQiToClientPacket {
    private final int lingqi;

    public PlayerLingQiToClientPacket(int lingqi) {
        this.lingqi = lingqi;
    }

    public PlayerLingQiToClientPacket(FriendlyByteBuf buf) {
        this.lingqi = buf.readInt();
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeInt(lingqi);
    }

    public void handle(Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            // 在客户端处理数据包
//            System.out.println("Received message from server: " + lingqi);
            Player player = Minecraft.getInstance().player; //因为是客户端收到的信息，所以直接获取就可以拿到player了
            PlayerLingQiHelper.setLingQi(player, lingqi);   //覆盖当前的值

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
