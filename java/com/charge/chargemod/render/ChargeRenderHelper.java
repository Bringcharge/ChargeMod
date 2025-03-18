package com.charge.chargemod.render;

import com.charge.chargemod.ChargeModItemRegistry;
import com.charge.chargemod.particle.ChargeModParticleType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ChargeModItemRegistry.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ChargeRenderHelper {   //

    @SubscribeEvent
    public static void onRenderLiving(RenderLivingEvent.Pre<LivingEntity, EntityModel<LivingEntity>> event) {
        Player player = Minecraft.getInstance().player;

        for(int i = 0; i < player.getInventory().getContainerSize(); ++i) {
            ItemStack itemstack1 = player.getInventory().getItem(i);
            if (itemstack1.is(ChargeModItemRegistry.WU_XIANG_SWORD.get())) {    //背包里有物品，增加渲染
//          这个是单机的粒子渲染逻辑
                Level level = player.level();
                if (level.isClientSide) {
                    //addParticle(ParticleOptions particleData, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed);
                    Vec3 eyePosition = player.getEyePosition(1.0F);
                    Vec3 targetEyesPosition = event.getEntity().getEyePosition();
                    Vec3 vec_to = targetEyesPosition.vectorTo(eyePosition).normalize();
                    Vec3 finalPos = targetEyesPosition.add(vec_to.scale(1.5));
                    level.addParticle(ChargeModParticleType.SWORD_MASK_PARTICLE_TYPE.get(), finalPos.x, finalPos.y, finalPos.z,  0,0,0);
                }
            }
        }
    }
}
