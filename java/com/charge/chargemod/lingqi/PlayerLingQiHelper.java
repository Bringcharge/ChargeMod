package com.charge.chargemod.lingqi;

import com.charge.chargemod.ChargeModItemRegistry;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.util.LazyOptional;

public class PlayerLingQiHelper {
    public static void addLingQi(Player player, int lingqi) {
        LazyOptional<PlayerLingQi> optional = player.getCapability(ChargeModItemRegistry.PLAYER_LING_QI);
        optional.ifPresent(playerLingQi -> playerLingQi.addLingQi(lingqi));
    }

    public static boolean consumeLingQi(Player player, int lingqi) {    //消耗灵气，如果不够则返回失败
        LazyOptional<PlayerLingQi> optional = player.getCapability(ChargeModItemRegistry.PLAYER_LING_QI);
        if (optional.map(PlayerLingQi::getLingQi).orElse(0) > lingqi) {
            optional.ifPresent(playerLingQi -> playerLingQi.consumeLingQi(lingqi));
            return true;
        }
        return false;
    }

    public static int getLingQi(Player player) {
        LazyOptional<PlayerLingQi> optional = player.getCapability(ChargeModItemRegistry.PLAYER_LING_QI);
        return optional.map(PlayerLingQi::getLingQi).orElse(0);
    }

    public static void setLingQi(Player player, int lingqi) {
        LazyOptional<PlayerLingQi> optional = player.getCapability(ChargeModItemRegistry.PLAYER_LING_QI);
        optional.ifPresent(playerLingQi -> playerLingQi.setLingQi(lingqi));
    }
}
