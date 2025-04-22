package com.charge.chargemod.runData.runDatarecipe;

import com.charge.chargemod.ChargeModItemRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
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
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ChargeModItemRegistry.CHARGE_BASE_TOKEN.get())
                .pattern("cac")
                .pattern("cbc")
                .pattern("cbc")
                .define('a', Items.IRON_INGOT)
                .define('b', Items.REDSTONE)
                .define('c', Items.GOLD_INGOT)
                .unlockedBy("has_charge_base_token", has(Items.GOLD_INGOT))
                .save(consumer);

        // 无序合成
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