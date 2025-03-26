package com.charge.chargemod.effect;

import com.charge.chargemod.ChargeModItemRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOD_EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, ChargeModItemRegistry.MODID);
    // 第二个参数是颜色。自己选一个颜色。
    // 应该是用十进制表示的16进制吧，大家自己试试
    public static final Supplier<MobEffect> EXAMPLE_EFFECT = register("example_effect", ()->new ExampleEffect(MobEffectCategory.BENEFICIAL, 16262179));
    public static final Supplier<MobEffect> HOLD_LIFE_EFFECT = register("hold_life_effect", ()->new HoldLifeEffect(MobEffectCategory.BENEFICIAL, 16262179));
    public static final Supplier<MobEffect> FLY_EFFECT = register("fly_effect", ()->new FlyEffect(MobEffectCategory.BENEFICIAL, 16262179));
    public static <T extends MobEffect> RegistryObject<T> register(String name, Supplier<T> effect){
        return MOD_EFFECTS.register(name, effect);
    }

}