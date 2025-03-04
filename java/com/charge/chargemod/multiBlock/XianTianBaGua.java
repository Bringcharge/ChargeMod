package com.charge.chargemod.multiBlock;

import com.charge.chargemod.ChargeModItemRegistry;
import com.charge.chargemod.block.ChargeAltarBlockEntity;
import com.charge.chargemod.block.ChargeBaseBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class XianTianBaGua implements ChargeMultiBlockCheck {

    public List<Vec3i> baseBlock;
    public List<Vec3i> altarBlock;
    public Vec3i centerPos = new Vec3i(12,0,12);

    public XianTianBaGua() {
        List<Vec3i> baseBlocks = List.of(
                new Vec3i(0,-1,22),
                new Vec3i(0,-1,21),
                new Vec3i(0,-1,14),
                new Vec3i(0,-1,13),
                new Vec3i(0,-1,11),
                new Vec3i(0,-1,10),
                new Vec3i(0,-1,3),
                new Vec3i(0,-1,2),  //第零列
                new Vec3i(1,-1,22),
                new Vec3i(1,-1,2),  //第一列
                new Vec3i(2,-1,24),
                new Vec3i(2,-1,23),
                new Vec3i(2,-1,20),
                new Vec3i(2,-1,19),
                new Vec3i(2,-1,24),
                new Vec3i(2,-1,14),
                new Vec3i(2,-1,13),
                new Vec3i(2,-1,12),
                new Vec3i(2,-1,11),
                new Vec3i(2,-1,10),
                new Vec3i(2,-1,5),
                new Vec3i(2,-1,4),
                new Vec3i(2,-1,1),
                new Vec3i(2,-1,0),  //第二列
                new Vec3i(3,-1,24),
                new Vec3i(3,-1,21),
                new Vec3i(3,-1,20),
                new Vec3i(3,-1,4),
                new Vec3i(3,-1,0),  //第三列
                new Vec3i(4,-1,22),
                new Vec3i(4,-1,21),
                new Vec3i(4,-1,18),
                new Vec3i(4,-1,17),
                new Vec3i(4,-1,14),
                new Vec3i(4,-1,13),
                new Vec3i(4,-1,11),
                new Vec3i(4,-1,10),
                new Vec3i(4,-1,7),
                new Vec3i(4,-1,6),
                new Vec3i(4,-1,3),
                new Vec3i(4,-1,2), //第四列
                new Vec3i(5,-1,22),
                new Vec3i(5,-1,19),
                new Vec3i(5,-1,18),
                new Vec3i(5,-1,6),
                new Vec3i(5,-1,5),
                new Vec3i(5,-1,2),  //第五列
                new Vec3i(6,-1,20),
                new Vec3i(6,-1,19),
                new Vec3i(6,-1,5),
                new Vec3i(6,-1,4),  //第六列
                new Vec3i(7,-1,20),
                new Vec3i(7,-1,4),  //第七列
                new Vec3i(10,-1,24),
                new Vec3i(10,-1,22),
                new Vec3i(10,-1,20),
                new Vec3i(10,-1,4),
                new Vec3i(10,-1,2),
                new Vec3i(10,-1,0), //10
                new Vec3i(11,-1,24),
                new Vec3i(11,-1,22),
                new Vec3i(11,-1,20),
                new Vec3i(11,-1,4),
                new Vec3i(11,-1,2),
                new Vec3i(11,-1,0), //11
                new Vec3i(12,-1,24),
                new Vec3i(12,-1,22),
                new Vec3i(12,-1,20), //12
                new Vec3i(13,-1,24),
                new Vec3i(13,-1,22),
                new Vec3i(13,-1,20),
                new Vec3i(13,-1,4),
                new Vec3i(13,-1,2),
                new Vec3i(13,-1,0), //13
                new Vec3i(14,-1,24),
                new Vec3i(14,-1,22),
                new Vec3i(14,-1,20),
                new Vec3i(14,-1,4),
                new Vec3i(14,-1,2),
                new Vec3i(14,-1,0), //14
                new Vec3i(17,-1,20),
                new Vec3i(17,-1,4), //17
                new Vec3i(18,-1,20),
                new Vec3i(18,-1,19),
                new Vec3i(18,-1,5),
                new Vec3i(18,-1,4), //18
                new Vec3i(19,-1,22),
                new Vec3i(19,-1,18),
                new Vec3i(19,-1,6),
                new Vec3i(19,-1,2), //19
                new Vec3i(20,-1,22),
                new Vec3i(20,-1,21),
                new Vec3i(20,-1,18),
                new Vec3i(20,-1,17),
                new Vec3i(20,-1,14),
                new Vec3i(20,-1,13),
                new Vec3i(20,-1,12),
                new Vec3i(20,-1,11),
                new Vec3i(20,-1,10),
                new Vec3i(20,-1,7),
                new Vec3i(20,-1,6),
                new Vec3i(20,-1,3),
                new Vec3i(20,-1,2),//20
                new Vec3i(21,-1,24),
                new Vec3i(21,-1,21),
                new Vec3i(21,-1,20),
                new Vec3i(21,-1,24),
                new Vec3i(21,-1,4),
                new Vec3i(21,-1,0), //21
                new Vec3i(22,-1,24),
                new Vec3i(22,-1,23),
                new Vec3i(22,-1,20),
                new Vec3i(22,-1,19),
                new Vec3i(22,-1,14),
                new Vec3i(22,-1,13),
                new Vec3i(22,-1,11),
                new Vec3i(22,-1,10),
                new Vec3i(22,-1,5),
                new Vec3i(22,-1,4),
                new Vec3i(22,-1,1),
                new Vec3i(22,-1,0), //22
                new Vec3i(23,-1,23),
                new Vec3i(23,-1,22),
                new Vec3i(23,-1,2),
                new Vec3i(23,-1,1), //23
                new Vec3i(24,-1,22),
                new Vec3i(24,-1,21),
                new Vec3i(24,-1,14),
                new Vec3i(24,-1,13),
                new Vec3i(24,-1,12),
                new Vec3i(24,-1,11),
                new Vec3i(24,-1,10),
                new Vec3i(24,-1,3),
                new Vec3i(24,-1,2)
        );
        List<Vec3i> altarBlocks = List.of(
                new Vec3i(12,0,17), //乾 天
                new Vec3i(8,0,16),  //巽 风
                new Vec3i(7,0,12),  //坎 水
                new Vec3i(8,0,8),   //艮 山
                new Vec3i(12,0,7),  //坤 地
                new Vec3i(16,0,8),  //震 雷
                new Vec3i(17,0,12), //离 火
                new Vec3i(16,0,16)  //兑 泽（金）
        );

        this.baseBlock = baseBlocks;
        this.altarBlock = altarBlocks;
    }

    @Override
    public BlockPos isCompleted(Level level, BlockPos center) {
        boolean flag = true;
        for (Vec3i vec : this.baseBlock) {
            int offset_x = vec.getX() - this.centerPos.getX();
            int offset_y = vec.getY() - this.centerPos.getY();
            int offset_z = vec.getZ() - this.centerPos.getZ();
            Vec3i targetBLock = center.offset(offset_x, offset_y, offset_z);
            BlockState state = level.getBlockState(new BlockPos(targetBLock));
            if (!state.is(ChargeModItemRegistry.CHARGE_BASE_BLOCK.get())) {  //如果不是基石
                return new BlockPos(targetBLock);
            }
        }

        for (Vec3i vec : this.altarBlock) {
            int offset_x = vec.getX() - this.centerPos.getX();
            int offset_y = vec.getY() - this.centerPos.getY();
            int offset_z = vec.getZ() - this.centerPos.getZ();
            Vec3i targetBLock = center.offset(offset_x, offset_y, offset_z);
            BlockState state = level.getBlockState(new BlockPos(targetBLock));
            if (!state.is(ChargeModItemRegistry.CHARGE_ALTAR_BLOCK.get())) {  //如果不是祭坛
                return new BlockPos(targetBLock);
            }
        }
        //理论上不需要检测中间方块，因为这个事件应该就是需要中间的方块右键触发的
        return null;
    }
    @Override
    public BlockPos getAltarWithIndex(int index, BlockPos center) {
        Vec3i vec = this.altarBlock.get(index);
        int offset_x = vec.getX() - this.centerPos.getX();
        int offset_y = vec.getY() - this.centerPos.getY();
        int offset_z = vec.getZ() - this.centerPos.getZ();
        Vec3i targetBLock = center.offset(offset_x, offset_y, offset_z);
        return new BlockPos(targetBLock);
    }

    @Override
    public void cleanAltar(Level level, BlockPos center) {  //清空所有祭坛
        for (int index = 0; index< this.altarBlock.size(); index++) {
            Vec3i vec = this.altarBlock.get(index);
            int offset_x = vec.getX() - this.centerPos.getX();
            int offset_y = vec.getY() - this.centerPos.getY();
            int offset_z = vec.getZ() - this.centerPos.getZ();
            Vec3i targetBLock = center.offset(offset_x, offset_y, offset_z);
            BlockEntity entity = level.getBlockEntity(new BlockPos(targetBLock));
            if (entity instanceof ChargeAltarBlockEntity) {
                ChargeAltarBlockEntity e = (ChargeAltarBlockEntity)entity;
                e.setItem(ItemStack.EMPTY);
            }
        }
    }

    @Override
    public boolean supportRotate() {
        return false;
    }
}
