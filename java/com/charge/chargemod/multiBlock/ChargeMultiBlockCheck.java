package com.charge.chargemod.multiBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.world.level.Level;


public interface ChargeMultiBlockCheck {

    BlockPos isCompleted(Level level, BlockPos center);    //完成了多方快结构，如果有返回代表多方快结构出错的地方
    boolean supportRotate();    //是否支持旋转匹配
    BlockPos getAltarWithIndex(int index, BlockPos center);  //获取祭坛的位置

}
