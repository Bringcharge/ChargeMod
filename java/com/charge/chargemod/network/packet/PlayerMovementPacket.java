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

public class PlayerMovementPacket {
    private final float x;
    private final float y;
    private final float z;

    public PlayerMovementPacket(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;

    }

    public PlayerMovementPacket(FriendlyByteBuf buf) {
        this.x = buf.readFloat();
        this.y = buf.readFloat();
        this.z = buf.readFloat();

    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeFloat(x);
        buf.writeFloat(y);
        buf.writeFloat(z);
    }

    public void handle(Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            // 在客户端处理数据包
            Player player = Minecraft.getInstance().player; //因为是客户端收到的信息，所以直接获取就可以拿到player了
            player.setPos(x,y,z);
        });
        context.get().setPacketHandled(true);
    }

}
