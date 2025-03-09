package com.charge.chargemod.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import org.checkerframework.checker.nullness.qual.Nullable;

public class SwordBladeParticleProvider implements ParticleProvider<SwordBladeParticleType> {
    // 存储一系列的纹理图
    private final SpriteSet sprites;

    // 构造方法
    // 传入对应的图片
    public SwordBladeParticleProvider(SpriteSet sprites) {
        this.sprites = sprites;
    }
    // 创建粒子
    @Nullable
    @Override
    public Particle createParticle(SwordBladeParticleType pType, ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed) {
        SwordBladeParticle swordBladeParticle = new SwordBladeParticle(pLevel, pX, pY, pZ, pType.getSpeed());
        // 随机选择一张图片作为纹理图
        swordBladeParticle.pickSprite(this.sprites);
        // 返回创建好的粒子
        return swordBladeParticle;
    }
}