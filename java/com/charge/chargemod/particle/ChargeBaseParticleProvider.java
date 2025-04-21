package com.charge.chargemod.particle;

import com.charge.chargemod.particle.particleList.SwordBladeParticle;
import com.charge.chargemod.particle.particleList.SwordMaskParticle;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import org.checkerframework.checker.nullness.qual.Nullable;

public class ChargeBaseParticleProvider implements ParticleProvider<ChargeBaseParticleType> {
    // 存储一系列的纹理图
    public final SpriteSet sprites;

    // 构造方法
    // 传入对应的图片
    public ChargeBaseParticleProvider(SpriteSet sprites) {
        this.sprites = sprites;
    }
    // 创建粒子
    @Nullable
    @Override
    //生成粒子函数是被调用的。
    public Particle createParticle(ChargeBaseParticleType pType, ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed) {
        if (pType.getType().equals(ChargeModParticleType.SWORD_MASK)) {//根据传入的type不同，构建不同的粒子效果
            SwordMaskParticle swordMaskParticle = new SwordMaskParticle(pLevel, pX, pY, pZ, pType.getSpeed());
            swordMaskParticle.pickSprite(this.sprites);
            return swordMaskParticle;
        }

        SwordBladeParticle swordBladeParticle = new SwordBladeParticle(pLevel, pX, pY, pZ, pType.getSpeed());
        // 随机选择一张图片作为纹理图
        swordBladeParticle.pickSprite(this.sprites);
        // 返回创建好的粒子
        return swordBladeParticle;
    }
}