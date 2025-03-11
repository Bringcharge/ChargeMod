package com.charge.chargemod.event;

import com.charge.chargemod.ChargeModItemRegistry;
import com.charge.chargemod.particle.ChargeModParticleType;
import com.charge.chargemod.particle.SwordBladeParticleProvider;
import com.charge.chargemod.render.ChargeDaggerEntityRenderer;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = "charge", bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ChargeModClientEvent {

    @SubscribeEvent
    public static void chargeDaggerEvent(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ChargeModItemRegistry.CHARGE_DAGGER_ENTITY_TYPE.get(), ChargeDaggerEntityRenderer::new);

    }


    @SubscribeEvent
    public static void onParticleFactoryRegistration(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ChargeModParticleType.SWORD_BLADE_PARTICLE_TYPE.get(), SwordBladeParticleProvider::new);
    }


    @SubscribeEvent
    public static void propertyOverrideRegistry(FMLClientSetupEvent event){
        event.enqueueWork(()->{
            ItemProperties.register(ChargeModItemRegistry.CANG_FENG.get(),new ResourceLocation(ChargeModItemRegistry.MODID,"cangfeng_state"),(itemStack, level, livingEntity, num)->{
                CompoundTag tag = itemStack.getOrCreateTag();
                return tag.getBoolean("CangFeng") ? 1 : 0;
            });
        });
    }
}
