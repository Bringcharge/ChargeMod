package com.charge.chargemod.lingqi;

public interface PlayerLingQiInterface {
    //三劫
    void gotLingQiInside();
    void SlaySanShi();
    void LightningCalamity();

    int getMaxLingQi(); //获取最大灵气
    int getLingQi(); // 获取灵气值
    void setLingQi(int value); // 设置灵气值
    void addLingQi(int amount); // 增加灵气值
    void consumeLingQi(int amount); // 消耗灵气值
}
