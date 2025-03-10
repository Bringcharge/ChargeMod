package com.charge.chargemod.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.TextureSheetParticle;
import org.joml.Vector3d;
import org.joml.Vector4i;

public class SwordBladeParticle extends TextureSheetParticle {
    // 除了父类要求的参数,这里我们还增加了几个参数,例如速度，颜色，大小等
    // 方便我们自定义我们自己的粒子效果,
    // 我们使用vector4i表示颜色
    public SwordBladeParticle(ClientLevel pLevel, double pX, double pY, double pZ, Vector3d speed) {
        super(pLevel, pX, pY, pZ, speed.x,speed.y,speed.z);
        this.lifetime = 100; // 这是粒子的存在的时间
        this.xd = speed.x; // 这里的是x,y,z方向的速度
        this.yd = speed.y; // 虽然父类中也有设置,但是父类会加上一个随机的速度
        this.zd = speed.z; // 我们并不希望这样做,就重新赋值

        this.setAlpha(1); // opengl中的颜色标准值是0-1,所以这里标准化
        final float PARTICLE_SCALE_FOR_ONE_METRE = 0.5F; // 这是我们自定义的粒子大小,单位是米
        this.quadSize = PARTICLE_SCALE_FOR_ONE_METRE;// 放大的倍数
        this.hasPhysics = true; // 粒子是否可以被碰撞
        this.lifetime = 3;  //设置粒子生命周期
    }
    // 这里指明渲染是半透明
    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }
}