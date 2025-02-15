package com.charge.chargemod;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ChargeModItemRegistry {
    public static final String MODID = "charge";
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
//    public static RegistryObject<Item> obsidianIngot = ITEMS.register("obsidian_ingot",() -> {
//        return new ObsidianIngot();
//    });
//    public static RegistryObject<Item> obsidianApple = ITEMS.register("obsidian_apple",() -> {
//        return new ObsidianApple();
//    });
//    public static RegistryObject<Item> obsidianSword = ITEMS.register("obsidian_sword",() -> {
//        return new ObsidianSword();
//    });
//    public static RegistryObject<Item> bigBrid = ITEMS.register("big_brid",() -> {
//        return new BigBrid((new Item.Properties()).maxDamage(384).group(ModGroup.itemGroup));
//    });
//    public static RegistryObject<Item> iceSword = ITEMS.register("ice_sword",() -> {
//        return new IceSword();
//    });


    //以下是block函数
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    //这个是什么？总不能是group吧
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final RegistryObject<Item> EXAMPLE_ITEM = ITEMS.register("charge_item", () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
            .alwaysEat().nutrition(1).saturationMod(2f).build())));

    public static final RegistryObject<CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS.register("example_tab", () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> EXAMPLE_ITEM.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(EXAMPLE_ITEM.get()); // Add the example item to the tab. For your own tabs, this method is preferred over the event
            }).build());


//    public static RegistryObject<Block> obsidianBlock = BLOCK.register("obsidian_block",()->{
//        return new ObsidianBlock();
//    });
//
//    public static RegistryObject<Item> obsidianBlockItem = ITEMS.register("obsidian_block",()->{    //����һ��item����ʵû��itemҲ��block
//        return new BlockItem(ChargeModItemRegistry.obsidianBlock.get(),new Item.Properties().group(ModGroup.itemGroup));
//    });
//
//    //一个新的方块模型
//    public static RegistryObject<Block> obsidianRubikCube = BLOCK.register("obsidian_rubik_cube",()->{
//        return new ObsidianRubikCube();
//    });
//
//    public static RegistryObject<Item> obsidianRubikCubeItem = ITEMS.register("obsidian_rubik_cube",()->{
//       return new BlockItem(ChargeModItemRegistry.obsidianRubikCube.get(),new Item.Properties().group(ModGroup.itemGroup));
//    });
//
//    //
//    public static RegistryObject<Block> obsidianFrame = BLOCK.register("obsidian_frame", () -> {
//        return new ObsidianFrame();
//    });
//
//    public static RegistryObject<Item> obssidianFrame = ITEMS.register("obsidian_frame", () -> {
//        return new BlockItem(ChargeModItemRegistry.obsidianFrame.get(), new Item.Properties().group(ModGroup.itemGroup));
//    });
//
//    //���Կ��ܲ�����һ��tileʵ������
//    public static RegistryObject<Block> obsidianCounterBlock = BLOCK.register("obsidian_counter_block", () -> {
//        return new ObsidianCounter();
//    });
//    public static RegistryObject<Item> obsidianCounterBlockItem = ITEMS.register("obsidian_counter_block", () -> {
//        return new BlockItem(ChargeModItemRegistry.obsidianCounterBlock.get(), new Item.Properties().group(ModGroup.itemGroup));
//    });
}
