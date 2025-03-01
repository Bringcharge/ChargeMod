package com.charge.chargemod.lingqi;

import net.minecraft.nbt.CompoundTag;


//没写能力的接口，直接写了实现类。
public class PlayerLingQi implements PlayerLingQiInterface {
    // 玩家当前的口渴值
    private int lingqi;
    // 最小和最大口渴值的常量
    public int MIN_LINGQI = 0;
    public int MAX_LINGQI = 200;
    // 获取当前的口渴值
    @Override
    public int getLingQi() {
        return lingqi;
    }

    @Override
    public void setLingQi(int value) {
        lingqi = value;
    }

    // 增加灵气值，但不能超过最大值
    @Override
    public void addLingQi(int add) {
        this.lingqi = Math.min(lingqi + add, MAX_LINGQI);
    }

    // 减少灵气值，但不能低于最小值
    @Override
    public void consumeLingQi(int consume) {
        this.lingqi = Math.max(lingqi - consume, MIN_LINGQI);
    }

    // 从另一个 PlayerLingQi 对象复制灵气
    public void copyFrom(PlayerLingQi source) {
        this.lingqi = source.lingqi;
    }

    // 将灵气值保存到 NBT 数据中
    public void saveNBTData(CompoundTag nbt) {
        nbt.putInt("lingqi", lingqi);
    }

    // 从 NBT 数据中加载灵气
    public void loadNBTData(CompoundTag nbt) {
        lingqi = nbt.getInt("lingqi");
    }
}

