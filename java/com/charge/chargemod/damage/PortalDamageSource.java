package com.charge.chargemod.damage;


import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.jetbrains.annotations.NotNull;

public class PortalDamageSource extends DamageSource {
    public PortalDamageSource(Holder<DamageType> pType, @Nullable Entity pEntity) {
        super(pType, pEntity);
    }

    @Override
    public @NotNull Component getLocalizedDeathMessage(@NotNull LivingEntity pLivingEntity) {
        return Component.translatable("death.attack.unstable_portal_owner", pLivingEntity.getDisplayName());
    }


}
