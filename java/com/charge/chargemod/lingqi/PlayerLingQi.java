package com.charge.chargemod.lingqi;

import net.minecraft.nbt.CompoundTag;

//灵气管理类
public class PlayerLingQi implements PlayerLingQiInterface {
    // 玩家当前的灵气
    private int lingqi;
    //30,100,300
    private boolean got_ling_qi;//引入给30
    private boolean slay_san_shi;//斩三尸给100
    private boolean lightning;//雷劫后给300
    // 最小常量
    public int MIN_LINGQI = 0;
    // 获取当前的灵气
    @Override
    public int getLingQi() {
        return lingqi;
    }

    @Override
    public void setLingQi(int value) {
        this.lingqi = Math.min(value, getMaxLingQi());
    }

    // 增加灵气值，但不能超过最大值
    @Override
    public void addLingQi(int add) {
        this.lingqi = Math.min(lingqi + add, getMaxLingQi());
    }

    // 减少灵气值，但不能低于最小值
    @Override
    public void consumeLingQi(int consume) {
        this.lingqi = Math.max(lingqi - consume, MIN_LINGQI);
    }

    //当前阶段
    @Override
    public int getCalamityNumber() {
        if (lightning) {
            return 3;
        }
        if (slay_san_shi) {
            return 2;
        }
        if (got_ling_qi) {
            return 1;
        };
        return 0;
    }

    //灵气上限
    @Override
    public int getMaxLingQi() {
        if (lightning) {
            return 300;
        }
        if (slay_san_shi) {
            return 100;
        }
        if (got_ling_qi) {
            return 30;
        };
        return 0;
    }

    @Override
    public void gotLingQiInside() {
        got_ling_qi = true;
    }

    @Override
    public void SlaySanShi() {
        slay_san_shi = true;
    }

    @Override
    public void LightningCalamity() {
        lightning = true;
    }

    // 从另一个 PlayerLingQi 对象复制灵气
    public void copyFrom(PlayerLingQi source) {
        this.lingqi = source.lingqi;
    }

    // 将灵气值保存到 NBT 数据中
    public void saveNBTData(CompoundTag nbt) {
        nbt.putBoolean("lightning", lightning);
        nbt.putBoolean("slay_san_shi", slay_san_shi);
        nbt.putBoolean("got_ling_qi", got_ling_qi);
        nbt.putInt("lingqi", lingqi);
    }

    // 从 NBT 数据中加载灵气
    public void loadNBTData(CompoundTag nbt) {
        lightning = nbt.getBoolean("lightning");
        slay_san_shi = nbt.getBoolean("slay_san_shi");
        got_ling_qi = nbt.getBoolean("got_ling_qi");
        lingqi = nbt.getInt("lingqi");
    }
}

