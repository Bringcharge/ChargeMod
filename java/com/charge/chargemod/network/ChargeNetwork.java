package com.charge.chargemod.network;

import com.charge.chargemod.ChargeModItemRegistry;
import com.charge.chargemod.network.packet.PlayerLingQiToClientPacket;
import com.charge.chargemod.network.packet.PlayerLingQiToServerPacket;
import com.charge.chargemod.network.packet.PlayerMovementPacket;
import com.charge.chargemod.network.packet.PlayerTeleportBlockToServerPacket;
import com.charge.chargemod.network.packet.particle.ParticleCreateToClientPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class ChargeNetwork {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(ChargeModItemRegistry.MODID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );


    public static void register() {
        int id = 0;
        CHANNEL.registerMessage(id++, PlayerLingQiToServerPacket.class, PlayerLingQiToServerPacket::encode, PlayerLingQiToServerPacket::new, PlayerLingQiToServerPacket::handle);
        CHANNEL.registerMessage(id++, PlayerLingQiToClientPacket.class, PlayerLingQiToClientPacket::encode, PlayerLingQiToClientPacket::new, PlayerLingQiToClientPacket::handle);
        CHANNEL.registerMessage(id++, PlayerMovementPacket.class, PlayerMovementPacket::encode, PlayerMovementPacket::new, PlayerMovementPacket::handle);
        CHANNEL.registerMessage(id++, PlayerTeleportBlockToServerPacket.class, PlayerTeleportBlockToServerPacket::encode, PlayerTeleportBlockToServerPacket::new, PlayerTeleportBlockToServerPacket::handle);
        CHANNEL.registerMessage(id++, ParticleCreateToClientPacket.class, ParticleCreateToClientPacket::encode, ParticleCreateToClientPacket::new, ParticleCreateToClientPacket::handle);
    }
}
