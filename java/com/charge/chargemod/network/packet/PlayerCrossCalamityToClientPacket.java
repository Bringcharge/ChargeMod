package com.charge.chargemod.network.packet;

import com.charge.chargemod.ChargeModItemRegistry;
import com.charge.chargemod.lingqi.PlayerLingQiHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PlayerCrossCalamityToClientPacket {
    private final int type;

    public PlayerCrossCalamityToClientPacket(int type) {
        this.type = type;
    }

    public PlayerCrossCalamityToClientPacket(FriendlyByteBuf buf) {
        this.type = buf.readInt();
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeInt(type);
    }

    public void handle(Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            // 在客户端处理数据包
            Player player = Minecraft.getInstance().player; //因为是客户端收到的信息，所以直接获取就可以拿到player了
            PlayerLingQiHelper.crossingCalamity(player, type);   //覆盖当前的值

        });
        context.get().setPacketHandled(true);
    }
}
