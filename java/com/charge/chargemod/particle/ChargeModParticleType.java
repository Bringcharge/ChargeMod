package com.charge.chargemod.particle;

import com.charge.chargemod.ChargeModItemRegistry;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.Registries;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import org.joml.Vector3d;

import java.util.function.Supplier;

//粒子注册器，跟另一个注册器差不多，只不过专门处理后期的一大堆粒子
public class ChargeModParticleType {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(Registries.PARTICLE_TYPE, ChargeModItemRegistry.MODID);
    public static final Supplier<SwordBladeParticleType> SWORD_BLADE_PARTICLE_TYPE = PARTICLE_TYPES.register("sword_blade_particle_type", () -> new SwordBladeParticleType(new Vector3d(0,0,0), 1, 1));

}