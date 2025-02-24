package com.charge.chargemod;

import com.charge.chargemod.block.ChargeAltarBlock;
import com.charge.chargemod.block.ChargeAltarBlockEntity;
import com.charge.chargemod.blockRegistry.ChargeBlockRegistry;
import com.charge.chargemod.entity.ChargeDaggerEntity;
import com.charge.chargemod.item.ChargeBaseIngot;
import com.charge.chargemod.item.ChargeBow;
import com.mojang.datafixers.types.Type;
import net.minecraft.Util;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.datafix.fixes.References;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import net.minecraftforge.registries.RegistryObject;

//@Mod.EventBusSubscriber(modid = "charge", bus = Mod.EventBusSubscriber.Bus.MOD)
public class ChargeModItemRegistry {
    public static final String MODID = "charge";
    //item的注册
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    //block的注册
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    //entity注册器
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MODID);
    //block entity注册器
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MODID);
    //这个是什么？
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

//    public static final RegistryObject<Item> EXAMPLE_ITEM = ITEMS.register("charge_item", () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
//            .alwaysEat().nutrition(1).saturationMod(2f).build())));


    //实际注册内容
    public static final RegistryObject<Item> chargeBaseIngot = ITEMS.register("charge_base_ingot", () -> new ChargeBaseIngot());
    public static final RegistryObject<Item> chargeBow = ITEMS.register("charge_bow", () -> {
        return new ChargeBow((new Item.Properties()).defaultDurability(400));
    });
    public static final RegistryObject<Block> CHARGE_ALTAR_BLOCK = BLOCKS.register("charge_altar_block", () -> {
        return new ChargeAltarBlock(BlockBehaviour.Properties.of()
                .mapColor(MapColor.COLOR_GREEN) //地图颜色
                .strength(1.0f, 50.0f) //硬度，石头是1.5 & 爆炸抗性，黑曜石是50
                .lightLevel(state -> 10)
                .sound(SoundType.STONE)
                .noOcclusion()
        ); //光照等级
    });

//    public static final RegistryObject<BlockEntityType<ChargeAltarBlockEntity>> CHARGE_ALTAR_ENTITY = BLOCK_ENTITIES.register("charge_altar_block", () ->
//            BlockEntityType.Builder.of(ChargeAltarBlockEntity::new, CHARGE_ALTAR_BLOCK.get()).build(null));

    //尝试直接创建block
//    public static final Block chargeAltarBlockStatic = Blocks.register("charge_altar_block", new ChargeAltarBlock(BlockBehaviour.Properties.of()
//            .mapColor(MapColor.COLOR_GREEN) //地图颜色
//            .strength(1.0f, 50.0f) //硬度，石头是1.5 & 爆炸抗性，黑曜石是50
//            .lightLevel(state -> 10)
//            .sound(SoundType.STONE)
//            .noOcclusion()
//    ));

//    public static final BlockEntityType<ChargeAltarBlockEntity> chargeAltarBlockEntityTypeStatic = register("charge_altar_block",
//            BlockEntityType.Builder.of(ChargeAltarBlockEntity::new, CHARGE_ALTAR_BLOCK.get()));
    //注册一些Blocks和BlockEntityType的类

    public static final RegistryObject<BlockEntityType<ChargeAltarBlockEntity>> CHARGE_ALTAR_ENTITY = BLOCK_ENTITIES.register("charge_altar_block", () ->
            BlockEntityType.Builder.of(ChargeAltarBlockEntity::new, CHARGE_ALTAR_BLOCK.get()).build(null));

    public static final RegistryObject<Item> chargeAltarBlockItem = ITEMS.register("charge_altar_block", () -> new BlockItem(CHARGE_ALTAR_BLOCK.get(), new Item.Properties()));

    public static final ModelLayerLocation CHARGE_DAGGER_LAYER = new ModelLayerLocation(new ResourceLocation(ChargeModItemRegistry.MODID, "charge_dagger"), "main");


    public static final RegistryObject<EntityType<ChargeDaggerEntity>> CHARGE_DAGGER_ENTITY_TYPE = ENTITY_TYPES.register("charge_dagger", () -> EntityType.Builder.
            <ChargeDaggerEntity>of(ChargeDaggerEntity::new, MobCategory.MISC)
            .sized(0.5F, 0.5F) // 设置实体大小
            .clientTrackingRange(4) // 客户端跟踪范围
            .updateInterval(20) // 更新间隔
            .build("charge_dagger")
    );

//    public static final EntityType<ChargeDaggerEntity> CHARGE_DAGGER = EntityType.Builder.<ChargeDaggerEntity>of(ChargeDaggerEntity::new, MobCategory.MONSTER)
//            .sized(1F, 1F) // 设置实体大小
//            .clientTrackingRange(4) // 客户端跟踪范围
//            .updateInterval(20) // 更新间隔
//            .build("charge_dagger");    //目前这个build函数出错了，好像是没找到字段对应的匹配


//

    //列表
    public static final RegistryObject<CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS.register("charge_tab", () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> chargeBaseIngot.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(chargeBaseIngot.get()); // Add the example item to the tab. For your own tabs, this method is preferred over the event
                output.accept(chargeBow.get());
                output.accept(chargeAltarBlockItem.get());
            }).build());

//    @SubscribeEvent
//    public static void onEntityRegister(RegisterEvent event) {
//        if (event.getRegistryKey().equals(ForgeRegistries.ENTITY_TYPES.getRegistryKey())) {
//            System.out.println("Registering ChargeDaggerEntity...Sub");
//        }
//    }

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
//,
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

    private static <T extends BlockEntity> BlockEntityType<T> register(String p_58957_, BlockEntityType.Builder<T> p_58958_) {
        Type<?> type = Util.fetchChoiceType(References.BLOCK_ENTITY, p_58957_);
        return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, p_58957_, p_58958_.build(type));
    }
}