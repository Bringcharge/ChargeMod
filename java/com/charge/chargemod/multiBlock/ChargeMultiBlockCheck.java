package com.charge.chargemod.multiBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;


public interface ChargeMultiBlockCheck {

    boolean isCompleted(Level level, BlockPos center);    //完成了多方快结构
    boolean supportRotate();    //是否支持旋转匹配
    BlockPos getAltarWithIndex(int index, BlockPos center);  //获取祭坛的位置

}
