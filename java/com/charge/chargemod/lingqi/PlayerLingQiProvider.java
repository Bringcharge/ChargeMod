package com.charge.chargemod.lingqi;

import com.charge.chargemod.ChargeModItemRegistry;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.jetbrains.annotations.NotNull;

//能力的提供者capabilityProvider，以及数据的持久化
public class PlayerLingQiProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    // 存储玩家灵气的实例
    private PlayerLingQi LingQi = null;

    // 创建 PlayerThirst 实例的私有方法
    private @NotNull PlayerLingQi createPlayerLingQi() {
        if (this.LingQi == null) {
            this.LingQi = new PlayerLingQi();
        }
        return this.LingQi;
    }

    // 实现 INBTSerializable 接口的 serializeNBT 方法，用于将数据保存到 NBT 数据中
    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createPlayerLingQi().saveNBTData(nbt);
        return nbt;
    }

    // 实现 INBTSerializable 接口的 deserializeNBT 方法，用于从 NBT 数据中加载
    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createPlayerLingQi().loadNBTData(nbt);
    }

    // 实现 ICapabilityProvider 接口的 getCapability 方法，用于获取玩家的口渴值实例
//    @Override
//    public @Nullable PlayerLingQi getCapability(Player player, Void context) {
//        return this.createPlayerLingQi();
//    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @org.jetbrains.annotations.Nullable Direction side) {
        if (cap == ChargeModItemRegistry.PLAYER_LING_QI) {
            return LazyOptional.of(this::createPlayerLingQi).cast();
        }
        return LazyOptional.empty();
    }
}