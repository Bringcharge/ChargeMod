package com.charge.chargemod.enent;

import com.charge.chargemod.ChargeModItemRegistry;
import com.charge.chargemod.render.ChargeDaggerEntityRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = "charge", bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ChargeDaggerEvent {

    @SubscribeEvent
    public static void chargeDaggerEvent(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ChargeModItemRegistry.CHARGE_DAGGER_ENTITY_TYPE.get(), ChargeDaggerEntityRenderer::new);

    }
}
