package com.charge.chargemod.block;

import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class ChargeLingShiOre extends DropExperienceBlock {

    public ChargeLingShiOre(BlockBehaviour.Properties p_221081_) {
        this(p_221081_, ConstantInt.of(15));
    }

    public ChargeLingShiOre(BlockBehaviour.Properties p_221083_, IntProvider p_221084_) {
        super(p_221083_, p_221084_);
    }

}
