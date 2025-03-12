package com.charge.chargemod.damage;


import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class DaoFalDamageSource extends DamageSource {

    public DaoFalDamageSource(@NotNull Entity directEntity, @NotNull Entity causingEntity, ResourceKey<DamageType> ResourceKey , @Nullable Vec3 damageSourcePosition) {
        super(getHolderFromResource(directEntity, ResourceKey), directEntity, causingEntity, damageSourcePosition);   //临时设置的
    }
//    public SpellDamageSource(@NotNull Entity directEntity, @NotNull Entity causingEntity, @Nullable Vec3 damageSourcePosition) {
//        super(getHolderFromResource(directEntity, ChargeDamageTypes.DAO_REAL), directEntity, causingEntity, damageSourcePosition);   //临时设置的
//    }

    @Override
    public @NotNull Component getLocalizedDeathMessage(@NotNull LivingEntity pLivingEntity) {
        String s = "death.attack.";
        return Component.translatable(s, pLivingEntity.getDisplayName());
    }

    //TODO: need a better way to get the registry access without going through the level each time
    private static Holder<DamageType> getHolderFromResource(Entity entity, ResourceKey<DamageType> damageTypeResourceKey) {
        var option = entity.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolder(damageTypeResourceKey);
        if (option.isPresent()) {
            return option.get();
        } else {
            return entity.level().damageSources().genericKill().typeHolder();
        }
    }

    public static DaoFalDamageSource source(@NotNull Entity entity, ResourceKey<DamageType> resourceKey) {
        return source(entity, entity , resourceKey);
    }

    public static DaoFalDamageSource source(@NotNull Entity directEntity, @NotNull Entity causingEntity, ResourceKey<DamageType> resourceKey) {
        return new DaoFalDamageSource(directEntity, causingEntity, resourceKey, null);
    }

    public DamageSource get() {
        return this;
    }

}
