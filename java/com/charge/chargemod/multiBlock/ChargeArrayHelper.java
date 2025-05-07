package com.charge.chargemod.multiBlock;

import com.charge.chargemod.ChargeModItemRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class ChargeArrayHelper {
    public static ItemStack outputWithItemList(BlockPos pos, Level level) {   //输入1~8的item，输出产物
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        if (
                checkBlockWithPos(x,y-1,z, Blocks.GOLD_BLOCK, level) &&
                        checkBlockWithPos(x-1,y-1,z-1, Blocks.CLAY, level) &&
                        checkBlockWithPos(x,y-1,z-1, Blocks.CLAY, level) &&
                        checkBlockWithPos(x+1,y-1,z-1, Blocks.CLAY, level) &&
                        checkBlockWithPos(x-1,y-1,z, Blocks.CLAY, level) &&
                        checkBlockWithPos(x+1,y-1,z, Blocks.CLAY, level) &&
                        checkBlockWithPos(x-1,y-1,z+1, Blocks.CLAY, level) &&
                        checkBlockWithPos(x,y-1,z+1, Blocks.CLAY, level) &&
                        checkBlockWithPos(x+1,y-1,z+1, Blocks.CLAY, level) &&
                        checkBlockWithPos(x-1,y,z-1, ChargeModItemRegistry.CHARGE_ARRAY_FLAG.get(), level) &&
                        checkBlockWithPos(x-1,y,z+1, ChargeModItemRegistry.CHARGE_ARRAY_FLAG.get(), level) &&
                        checkBlockWithPos(x+1,y,z-1, ChargeModItemRegistry.CHARGE_ARRAY_FLAG.get(), level) &&
                        checkBlockWithPos(x+1,y,z+1, ChargeModItemRegistry.CHARGE_ARRAY_FLAG.get(), level) &&
                        checkBlockWithPos(x,y+1,z, BlockTags.PLANKS, level)

        ) {
            return new ItemStack(ChargeModItemRegistry.CHARGE_ZHEN_YAO_BLOCK_ITEM.get(),1);
        }
        return ItemStack.EMPTY;
    }

    public static boolean checkBlockWithPos(int x, int y, int z, TagKey<Block> tag, Level level) {
        BlockState state = level.getBlockState(new BlockPos(x,y,z));
        return state.is(tag);
    }

    public static boolean checkBlockWithPos(int x, int y, int z, Block block, Level level) {
        BlockState state = level.getBlockState(new BlockPos(x,y,z));
        return state.is(block);
    }
}
