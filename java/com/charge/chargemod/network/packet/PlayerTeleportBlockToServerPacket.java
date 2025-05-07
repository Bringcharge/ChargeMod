package com.charge.chargemod.network.packet;

import com.charge.chargemod.block.array.ChargeTeleportBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PlayerTeleportBlockToServerPacket {
    private final int pos_x;
    private final int pos_y;
    private final int pos_z;
    private final int target_x;
    private final int target_y;
    private final int target_z;

    public PlayerTeleportBlockToServerPacket(int pos_x, int pos_y, int pos_z, int target_x, int target_y, int target_z) {
        this.pos_x = pos_x;
        this.pos_y = pos_y;
        this.pos_z = pos_z;
        this.target_x = target_x;
        this.target_y = target_y;
        this.target_z = target_z;
    }

    public PlayerTeleportBlockToServerPacket(FriendlyByteBuf buf) {
        this.pos_x = buf.readInt();
        this.pos_y = buf.readInt();
        this.pos_z = buf.readInt();
        this.target_x = buf.readInt();
        this.target_y = buf.readInt();
        this.target_z = buf.readInt();
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeInt(pos_x);
        buf.writeInt(pos_y);
        buf.writeInt(pos_z);
        buf.writeInt(target_x);
        buf.writeInt(target_y);
        buf.writeInt(target_z);
    }

    public void handle(Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            // 在服务器端处理数据包
            ServerPlayer player = context.get().getSender();
            if (player != null) {
                // 服务端的level
                ServerLevel level = (ServerLevel) player.level();
                BlockPos blockPos = new BlockPos(pos_x, pos_y, pos_z);
                BlockEntity blockEntity = level.getBlockEntity(blockPos);
                if (blockEntity instanceof ChargeTeleportBlockEntity) {
                    ChargeTeleportBlockEntity entity = (ChargeTeleportBlockEntity)blockEntity;
                    entity.setTargetVec(new Vec3i(target_x, target_y, target_z));
                }
            }
        });
        context.get().setPacketHandled(true);
    }
}
