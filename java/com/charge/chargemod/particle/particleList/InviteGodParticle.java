package com.charge.chargemod.particle.particleList;

import com.charge.chargemod.particle.ChargeBaseParticle;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.ParticleRenderType;
import org.joml.Vector3d;

import java.util.Random;

public class InviteGodParticle extends ChargeBaseParticle {    //继承夫类
    // 除了父类要求的参数,这里我们还增加了几个参数,例如速度，颜色，大小等
    // 方便我们自定义我们自己的粒子效果,
    // 我们使用vector4i表示颜色
    public InviteGodParticle(ClientLevel pLevel, double pX, double pY, double pZ, Vector3d speed) {
        super(pLevel, pX, pY, pZ, speed,10,0.5f);
        Random random = new Random();
    }
    // 这里指明渲染是半透明
    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }
}