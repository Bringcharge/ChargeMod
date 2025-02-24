package com.charge.chargemod.blockRegistry;

import com.charge.chargemod.block.ChargeAltarBlock;
import com.charge.chargemod.block.ChargeAltarBlockEntity;
import com.mojang.datafixers.types.Type;
import net.minecraft.Util;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.datafix.fixes.References;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.FurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

public class ChargeBlockRegistry {

    public static final int charge_int_to_load = 3;

    //祭坛的注册
//    public static final Block chargeAltarBlockStatic = Blocks.register("charge_altar_block",new ChargeAltarBlock(BlockBehaviour.Properties.of()
//            .mapColor(MapColor.COLOR_GREEN) //地图颜色
//            .strength(1.0f,50.0f) //硬度，石头是1.5 & 爆炸抗性，黑曜石是50
//            .lightLevel(state -> 10)
//            .sound(SoundType.STONE)
//            .noOcclusion()
//    ));
//
//    public static final BlockEntityType<ChargeAltarBlockEntity> chargeAltarBlockEntityTypeStatic = register("charge_altar_block",
//            BlockEntityType.Builder.of(ChargeAltarBlockEntity::new, chargeAltarBlockStatic));
//    //注册一些Blocks和BlockEntityType的类
//
//
//
//
//
//    private static <T extends BlockEntity> BlockEntityType<T> register(String p_58957_, BlockEntityType.Builder<T> p_58958_) {
//        Type<?> type = Util.fetchChoiceType(References.BLOCK_ENTITY, p_58957_);
//        return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, p_58957_, p_58958_.build(type));
//    }


}
