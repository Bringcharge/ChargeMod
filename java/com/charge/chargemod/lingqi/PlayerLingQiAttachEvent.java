package com.charge.chargemod.lingqi;

import com.charge.chargemod.ChargeModItemRegistry;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

//使用 AttachCapabilitiesEvent 将 PlayerLingQi 能力附加到玩家实体。
@Mod.EventBusSubscriber(modid = ChargeModItemRegistry.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class PlayerLingQiAttachEvent {
    private static final ResourceLocation PLAYER_LING_QI_ID = new ResourceLocation(ChargeModItemRegistry.MODID, "player_ling_qi");

    @SubscribeEvent
    public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            event.addCapability(PLAYER_LING_QI_ID, new PlayerLingQiProvider());
//            event.addCapability(PLAYER_LING_QI_ID, new PlayerLingQiProvider() {     //这玩意似乎多余，可以不用重载
//                private final PlayerLingQi playerLingQi = new PlayerLingQi();
//                private final LazyOptional<PlayerLingQi> optional = LazyOptional.of(() -> playerLingQi);
//
//                @Nonnull
//                @Override
//                public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
//                    if (cap == ChargeModItemRegistry.PLAYER_LING_QI) {
//                        return optional.cast();
//                    }
//                    return LazyOptional.empty();
//                }
//            });
        }
    }
}