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
        if (
                checkBlockWithPos(x,y-1,z, Blocks.COPPER_BLOCK, level) &&
                        checkBlockWithPos(x-1,y-1,z-1, Blocks.MOSS_BLOCK, level) &&
                        checkBlockWithPos(x,y-1,z-1, Blocks.MOSS_BLOCK, level) &&
                        checkBlockWithPos(x+1,y-1,z-1, Blocks.MOSS_BLOCK, level) &&
                        checkBlockWithPos(x-1,y-1,z, Blocks.MOSS_BLOCK, level) &&
                        checkBlockWithPos(x+1,y-1,z, Blocks.MOSS_BLOCK, level) &&
                        checkBlockWithPos(x-1,y-1,z+1, Blocks.MOSS_BLOCK, level) &&
                        checkBlockWithPos(x,y-1,z+1, Blocks.MOSS_BLOCK, level) &&
                        checkBlockWithPos(x+1,y-1,z+1, Blocks.MOSS_BLOCK, level) &&
                        checkBlockWithPos(x-1,y,z-1, ChargeModItemRegistry.CHARGE_ARRAY_FLAG.get(), level) &&
                        checkBlockWithPos(x-1,y,z+1, ChargeModItemRegistry.CHARGE_ARRAY_FLAG.get(), level) &&
                        checkBlockWithPos(x+1,y,z-1, ChargeModItemRegistry.CHARGE_ARRAY_FLAG.get(), level) &&
                        checkBlockWithPos(x+1,y,z+1, ChargeModItemRegistry.CHARGE_ARRAY_FLAG.get(), level) &&
                        checkBlockWithPos(x,y+1,z, BlockTags.PLANKS, level)
        ) {
            return new ItemStack(ChargeModItemRegistry.CHARGE_TONG_QIAN_BLOCK_ITEM.get(),1);
        }

        if (
                checkBlockWithPos(x,y-1,z, Blocks.REDSTONE_BLOCK, level) &&
                        checkBlockWithPos(x-1,y-1,z-1, Blocks.MAGMA_BLOCK, level) &&
                        checkBlockWithPos(x,y-1,z-1, Blocks.COBWEB, level) &&
                        checkBlockWithPos(x+1,y-1,z-1, Blocks.MAGMA_BLOCK, level) &&
                        checkBlockWithPos(x-1,y-1,z, Blocks.COBWEB, level) &&
                        checkBlockWithPos(x+1,y-1,z, Blocks.COBWEB, level) &&
                        checkBlockWithPos(x-1,y-1,z+1, Blocks.MAGMA_BLOCK, level) &&
                        checkBlockWithPos(x,y-1,z+1, Blocks.COBWEB, level) &&//蜘蛛网
                        checkBlockWithPos(x+1,y-1,z+1, Blocks.MAGMA_BLOCK, level) &&//岩浆块
                        checkBlockWithPos(x-1,y,z-1, ChargeModItemRegistry.CHARGE_ARRAY_FLAG.get(), level) &&
                        checkBlockWithPos(x-1,y,z+1, ChargeModItemRegistry.CHARGE_ARRAY_FLAG.get(), level) &&
                        checkBlockWithPos(x+1,y,z-1, ChargeModItemRegistry.CHARGE_ARRAY_FLAG.get(), level) &&
                        checkBlockWithPos(x+1,y,z+1, ChargeModItemRegistry.CHARGE_ARRAY_FLAG.get(), level) &&
                        checkBlockWithPos(x,y+1,z, Blocks.LAPIS_BLOCK, level)   //青金石
        ) {
            return new ItemStack(ChargeModItemRegistry.CHARGE_JU_LING_BLOCK_ITEM.get(),1);
        }
        if (
                checkBlockWithPos(x,y-1,z, Blocks.ENDER_CHEST, level) &&
                        checkBlockWithPos(x-1,y-1,z-1, Blocks.OBSIDIAN, level) &&
                        checkBlockWithPos(x,y-1,z-1, Blocks.FIRE, level) &&
                        checkBlockWithPos(x+1,y-1,z-1, Blocks.OBSIDIAN, level) &&
                        checkBlockWithPos(x-1,y-1,z, Blocks.FIRE, level) &&
                        checkBlockWithPos(x+1,y-1,z, Blocks.FIRE, level) &&
                        checkBlockWithPos(x-1,y-1,z+1, Blocks.OBSIDIAN, level) &&
                        checkBlockWithPos(x,y-1,z+1, Blocks.FIRE, level) &&//火
                        checkBlockWithPos(x+1,y-1,z+1, Blocks.OBSIDIAN, level) &&//黑曜石
                        checkBlockWithPos(x-1,y,z-1, ChargeModItemRegistry.CHARGE_ARRAY_FLAG.get(), level) &&
                        checkBlockWithPos(x-1,y,z+1, ChargeModItemRegistry.CHARGE_ARRAY_FLAG.get(), level) &&
                        checkBlockWithPos(x+1,y,z-1, ChargeModItemRegistry.CHARGE_ARRAY_FLAG.get(), level) &&
                        checkBlockWithPos(x+1,y,z+1, ChargeModItemRegistry.CHARGE_ARRAY_FLAG.get(), level) &&
                        checkBlockWithPos(x,y+1,z, Blocks.ICE, level)   //青金石
        ) {
            return new ItemStack(ChargeModItemRegistry.CHARGE_TELEPORT_BLOCK_ITEM.get(),1);
        }
        return ItemStack.EMPTY;
    }

    public static void removeInputWithItem(ItemStack stack, BlockPos pos, Level level) {
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        if (!stack.isEmpty()) {
            level.destroyBlock(new BlockPos(x,y-1,z), false);
            level.destroyBlock(new BlockPos(x-1,y-1,z-1), false);
            level.destroyBlock(new BlockPos(x,y-1,z-1),false);
            level.destroyBlock(new BlockPos(x+1,y-1,z-1), false);
            level.destroyBlock(new BlockPos(x-1,y-1,z), false);
            level.destroyBlock(new BlockPos(x+1,y-1,z), false);
            level.destroyBlock(new BlockPos(x-1,y-1,z+1), false);
            level.destroyBlock(new BlockPos(x,y-1,z+1), false);
            level.destroyBlock(new BlockPos(x+1,y-1,z+1), false);
            level.destroyBlock(new BlockPos(x-1, y,z-1), false);
            level.destroyBlock(new BlockPos(x-1, y,z+1), false);
            level.destroyBlock(new BlockPos(x+1, y,z-1), false);
            level.destroyBlock(new BlockPos(x+1, y,z+1), false);
        }
        level.destroyBlock(pos, false);
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
