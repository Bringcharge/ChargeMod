package com.charge.chargemod.network.packet.particle;

import com.charge.chargemod.ChargeModItemRegistry;
import com.charge.chargemod.lingqi.PlayerLingQiHelper;
import com.charge.chargemod.particle.ChargeModParticleType;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ParticleCreateToClientPacket {
    private final double x;
    private final double y;
    private final double z;
    private final double speed_x;
    private final double speed_y;
    private final double speed_z;
    private final String type;

    public ParticleCreateToClientPacket(double x, double y, double z, double speed_x, double speed_y, double speed_z, String type) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.speed_x = speed_x;
        this.speed_y = speed_y;
        this.speed_z = speed_z;
        this.type = type;
    }

    public ParticleCreateToClientPacket(FriendlyByteBuf buf) {
        this.x = buf.readDouble();
        this.y = buf.readDouble();
        this.z = buf.readDouble();
        this.speed_x = buf.readDouble();
        this.speed_y = buf.readDouble();
        this.speed_z = buf.readDouble();
        this.type = buf.readUtf();
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeDouble(x);
        buf.writeDouble(y);
        buf.writeDouble(z);
        buf.writeDouble(speed_x);
        buf.writeDouble(speed_y);
        buf.writeDouble(speed_z);
        buf.writeUtf(type);
    }

    public void handle(Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            // 在客户端处理数据包
            Player player = Minecraft.getInstance().player; //因为是客户端收到的信息，所以直接获取就可以拿到player了
            Level level = player.level();
            level.addParticle(ChargeModParticleType.findParticleTypeByString(type).get(),x,y,z,speed_x,speed_y,speed_z);
        });
        context.get().setPacketHandled(true);
    }
}
