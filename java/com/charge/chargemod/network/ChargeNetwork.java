package com.charge.chargemod.network;

import com.charge.chargemod.ChargeModItemRegistry;
import com.charge.chargemod.lingqi.PlayerLingQiToClientPacket;
import com.charge.chargemod.lingqi.PlayerLingQiToServerPacket;
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
        CHANNEL.registerMessage(0, PlayerLingQiToServerPacket.class, PlayerLingQiToServerPacket::encode, PlayerLingQiToServerPacket::new, PlayerLingQiToServerPacket::handle);
        CHANNEL.registerMessage(0, PlayerLingQiToClientPacket.class, PlayerLingQiToClientPacket::encode, PlayerLingQiToClientPacket::new, PlayerLingQiToClientPacket::handle);
    }
}
