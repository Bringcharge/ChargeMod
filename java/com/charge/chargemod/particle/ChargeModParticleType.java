package com.charge.chargemod.particle;

import com.charge.chargemod.ChargeModItemRegistry;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.Registries;
import net.minecraftforge.registries.DeferredRegister;
import org.apache.commons.lang3.ObjectUtils;
import org.joml.Vector3d;

import java.util.function.Supplier;

//粒子注册器，跟另一个注册器差不多，只不过专门处理后期的一大堆粒子
public class ChargeModParticleType {
    //与资源文件同名
    public static final String  SWORD_BLADE = "sword_blade_particle_type";
    public static final String  SWORD_MASK = "sword_mask_particle_type";
    public static final String  INVITE_GOD = "invite_god_particle_type";

    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(Registries.PARTICLE_TYPE, ChargeModItemRegistry.MODID);
    public static final Supplier<ChargeBaseParticleType> SWORD_BLADE_PARTICLE_TYPE = PARTICLE_TYPES.register(SWORD_BLADE, () -> new ChargeBaseParticleType(new Vector3d(0,0,0), 1, 1,SWORD_BLADE));
    public static final Supplier<ChargeBaseParticleType> SWORD_MASK_PARTICLE_TYPE = PARTICLE_TYPES.register(SWORD_MASK, () -> new ChargeBaseParticleType(new Vector3d(0,0,0), 1, 1,SWORD_MASK));
    public static final Supplier<ChargeBaseParticleType> INVITE_GOD_PARTICLE_TYPE = PARTICLE_TYPES.register(INVITE_GOD, () -> new ChargeBaseParticleType(new Vector3d(0,0,0), 1, 1,INVITE_GOD));

    public static Supplier<ChargeBaseParticleType> findParticleTypeByString(String str) {   //手动写一个返还吧
        if (str.equals(SWORD_BLADE)) return SWORD_BLADE_PARTICLE_TYPE;
        if (str.equals(SWORD_MASK)) return SWORD_MASK_PARTICLE_TYPE;
        if (str.equals(INVITE_GOD)) return INVITE_GOD_PARTICLE_TYPE;
        return SWORD_BLADE_PARTICLE_TYPE;
    };

}