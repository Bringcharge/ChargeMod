package com.charge.chargemod.particle.particleList;

import com.charge.chargemod.particle.ChargeBaseParticle;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.TextureSheetParticle;
import org.joml.Vector3d;

public class SwordMaskParticle extends ChargeBaseParticle { //继承父类

    public SwordMaskParticle(ClientLevel pLevel, double pX, double pY, double pZ, Vector3d speed) {

        super(pLevel, pX, pY, pZ, speed,1,2.5f);
    }
    // 这里指明渲染是半透明
    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }
}