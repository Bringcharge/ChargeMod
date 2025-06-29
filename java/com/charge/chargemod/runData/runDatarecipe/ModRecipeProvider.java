package com.charge.chargemod.runData.runDatarecipe;

import com.charge.chargemod.ChargeModItemRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider {

    //https://docs.neoforged.net/docs/datagen/server/recipes
    public ModRecipeProvider(PackOutput pPackOutput) {
        super(pPackOutput);
    }


    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        // 有序合成
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ChargeModItemRegistry.CHARGE_BASE_TOKEN.get())//基本令牌
                .pattern("cac")
                .pattern("cbc")
                .pattern("cbc")
                .define('a', Items.IRON_INGOT)
                .define('b', Items.REDSTONE)
                .define('c', Items.GOLD_INGOT)
                .unlockedBy("has_charge_gold_ingot", has(Items.GOLD_INGOT))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ChargeModItemRegistry.CHARGE_ARRAY_FLAG_ITEM.get())//阵旗
                .pattern("c  ")
                .pattern("b  ")
                .pattern("a  ")
                .define('c', ItemTags.WOOL)
                .define('b', Items.STICK)
                .define('a', ChargeModItemRegistry.chargeLingShi.get())
                .unlockedBy("has_charge_ling_shi", has(ChargeModItemRegistry.chargeLingShi.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ChargeModItemRegistry.CHARGE_ARRAY_DISK_BLOCK_ITEM.get())//阵盘
                .pattern("c b")
                .pattern(" a ")
                .pattern("b c")
                .define('a', Items.GOLD_INGOT)
                .define('b', ChargeModItemRegistry.chargeBaseBlockItem.get())
                .define('c', Items.BONE_MEAL)
                .unlockedBy("has_charge_base_block", has(ChargeModItemRegistry.chargeBaseBlockItem.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ChargeModItemRegistry.chargeAltarBlockItem.get())//祭坛
                .pattern("aaa")
                .pattern("b b")
                .pattern("ccc")
                .define('a', Items.BAMBOO)
                .define('b', Items.STICK)
                .define('c', Items.STONE)
                .unlockedBy("has_bamboo", has(Items.BAMBOO))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ChargeModItemRegistry.chargeAlchemyStoveBlockItem.get())//八卦炉
                .pattern("cbc")
                .pattern("bab")
                .pattern("cbc")
                .define('a', ChargeModItemRegistry.CHARGE_ARRAY_DISK_BLOCK_ITEM.get())
                .define('b', Items.BAMBOO)
                .define('c', Items.COPPER_INGOT)
                .unlockedBy("has_charge_base_block", has(ChargeModItemRegistry.chargeBaseBlockItem.get()))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ChargeModItemRegistry.chargeAlchemyAnvilBlockItem.get())//铁砧
                .pattern("cbc")
                .pattern("bab")
                .pattern("cbc")
                .define('a', ChargeModItemRegistry.CHARGE_ARRAY_DISK_BLOCK_ITEM.get())
                .define('b', Items.IRON_INGOT)
                .define('c', Items.COAL)
                .unlockedBy("has_charge_base_block", has(ChargeModItemRegistry.chargeBaseBlockItem.get()))
                .save(consumer);

        // 无序合成
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ChargeModItemRegistry.CHARGE_GUIDE_BOOK.get())  //天书
                .requires(ChargeModItemRegistry.chargeLingShi.get(), 1)
                .requires(Items.BOOK, 1)
                .unlockedBy("has_charge_ling_shi", has(ChargeModItemRegistry.chargeLingShi.get()))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ChargeModItemRegistry.chargeBaseBlockItem.get())  //祭坛基石
                .requires(ChargeModItemRegistry.chargeLingShi.get(), 1)
                .requires(Items.STONE, 1)
                .unlockedBy("has_charge_ling_shi", has(ChargeModItemRegistry.chargeLingShi.get()))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ChargeModItemRegistry.TALISMAN_PAPER_ITEM.get())  //符纸
                .requires(Items.PAPER, 1)
                .requires(Items.YELLOW_DYE, 1)
                .unlockedBy("has_paper", has(Items.PAPER))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ChargeModItemRegistry.MAZE_TALISMAN.get(), 8)//迷惘
                .requires(ChargeModItemRegistry.XIANG_BU_TONG_TALISMAN.get(), 1)
                .requires(ChargeModItemRegistry.JIE_BU_CHU_TALISMAN.get(), 1)
                .requires(ChargeModItemRegistry.QIU_BU_DE_TALISMAN.get(), 1)
                .requires(ChargeModItemRegistry.SUAN_BU_DUI_TALISMAN.get(), 1)
                .unlockedBy("has_xiang_bu_tong", has(ChargeModItemRegistry.XIANG_BU_TONG_TALISMAN.get()))
                .save(consumer);

//        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.MAGIC_INGOT.get())
//                .requires(Items.IRON_INGOT, 3)
//                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
//                .save(consumer);

        // 冶炼
//        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.RUBY_ORE.get()),
//                RecipeCategory.MISC,
//                ModItems.RUBY.get(),
//                0.3f,
//                100)
//                .unlockedBy("has_ruby_ore", has(ModBlocks.RUBY_ORE.get()))
//                .save(consumer);

        // 切割石头 (示例)
//        SingleItemRecipeBuilder.stonecutting(
//                Ingredient.of(Items.STONE),
//                RecipeCategory.BUILDING_BLOCKS,
//                Items.STONE_BRICKS)
//                .unlockedBy("has_stone", has(Items.STONE))
//                .save(consumer, "stone_to_stone_bricks");
    }
}