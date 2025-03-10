package com.charge.chargemod;

import com.charge.chargemod.block.*;
import com.charge.chargemod.entity.ChargeDaggerEntity;
import com.charge.chargemod.item.ChargeBaseIngot;
import com.charge.chargemod.item.ChargeBaseToken;
import com.charge.chargemod.item.ChargeBow;
import com.charge.chargemod.item.ChargeLingShi;
import com.charge.chargemod.item.pellet.ChargeBiGuPellet;
import com.charge.chargemod.item.sword.WaterSplitSword;
import com.charge.chargemod.lingqi.PlayerLingQi;
import com.charge.chargemod.lingqi.PlayerLingQiInterface;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
//方块，物品，实体注册器
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
    //列表注册器
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);
    public static final Capability<PlayerLingQi> PLAYER_LING_QI = CapabilityManager.get(new CapabilityToken<>() {});



    //测试用的基本锭
    public static final RegistryObject<Item> chargeBaseIngot = ITEMS.register("charge_base_ingot", () -> new ChargeBaseIngot());
    //测试用弓
    public static final RegistryObject<Item> chargeBow = ITEMS.register("charge_bow", () -> {
        return new ChargeBow((new Item.Properties()).defaultDurability(400));
    });

    //祭坛
    public static final RegistryObject<Block> CHARGE_ALTAR_BLOCK = BLOCKS.register("charge_altar_block", () -> {
        return new ChargeAltarBlock(BlockBehaviour.Properties.of()
                .mapColor(MapColor.COLOR_GREEN) //地图颜色
                .strength(1.0f, 50.0f) //硬度，石头是1.5 & 爆炸抗性，黑曜石是50
                .lightLevel(state -> 10)
                .sound(SoundType.STONE)
                .noOcclusion()
        );}); //光照等级
    //祭坛blockEntityType
    public static final RegistryObject<BlockEntityType<ChargeAltarBlockEntity>> CHARGE_ALTAR_ENTITY = BLOCK_ENTITIES.register("charge_altar_block", () ->
            BlockEntityType.Builder.of(ChargeAltarBlockEntity::new, CHARGE_ALTAR_BLOCK.get()).build(null));
    //祭坛item
    public static final RegistryObject<Item> chargeAltarBlockItem = ITEMS.register("charge_altar_block", () -> new BlockItem(CHARGE_ALTAR_BLOCK.get(), new Item.Properties()));

    //祭坛基石
    public static final RegistryObject<Block> CHARGE_BASE_BLOCK = BLOCKS.register("charge_base_block", () -> {
        return new ChargeBaseBlock(BlockBehaviour.Properties.of()
                .mapColor(MapColor.COLOR_BLACK) //地图颜色
                .strength(1.0f, 50.0f) //硬度，石头是1.5 & 爆炸抗性，黑曜石是50
                .lightLevel(state -> 10)
                .sound(SoundType.STONE)
        );}); //光照等级
    //祭坛基石item
    public static final RegistryObject<Item> chargeBaseBlockItem = ITEMS.register("charge_base_block", () -> new BlockItem(CHARGE_BASE_BLOCK.get(), new Item.Properties()));

    //八卦炉
    public static final RegistryObject<Block> CHARGE_ALCHEMY_STOVE_BLOCK = BLOCKS.register("charge_alchemy_stove_block", () -> {
        return new ChargeAlchemyStoveBlock(BlockBehaviour.Properties.of()
                .mapColor(MapColor.COLOR_BLUE) //地图颜色
                .strength(1.0f, 50.0f) //硬度，石头是1.5 & 爆炸抗性，黑曜石是50
                .lightLevel(state -> 15)
                .sound(SoundType.STONE)
                .noOcclusion()
        );}); //光照等级
    //八卦炉blockEntityType
    public static final RegistryObject<BlockEntityType<ChargeAlchemyStoveBlockEntity>> CHARGE_ALCHEMY_STOVE_ENTITY = BLOCK_ENTITIES.register("charge_alchemy_stove_block", () ->
            BlockEntityType.Builder.of(ChargeAlchemyStoveBlockEntity::new, CHARGE_ALCHEMY_STOVE_BLOCK.get()).build(null));
    //祭坛item
    public static final RegistryObject<Item> chargeAlchemyStoveBlockItem = ITEMS.register("charge_alchemy_stove_block", () -> new BlockItem(CHARGE_ALCHEMY_STOVE_BLOCK.get(), new Item.Properties()));



    //飞刀layer
    public static final ModelLayerLocation CHARGE_DAGGER_LAYER = new ModelLayerLocation(new ResourceLocation(ChargeModItemRegistry.MODID, "charge_dagger"), "main");
    //飞刀自定义entityType
    public static final RegistryObject<EntityType<ChargeDaggerEntity>> CHARGE_DAGGER_ENTITY_TYPE = ENTITY_TYPES.register("charge_dagger", () -> EntityType.Builder.
            <ChargeDaggerEntity>of(ChargeDaggerEntity::new, MobCategory.MISC)
            .sized(0.5F, 0.5F) // 设置实体大小
            .clientTrackingRange(4) // 客户端跟踪范围
            .updateInterval(20) // 更新间隔
            .build("charge_dagger")
    );

    //灵石
    public static final RegistryObject<Item> chargeLingShi = ITEMS.register("charge_ling_shi", () -> new ChargeLingShi());
    //灵石矿
    public static final RegistryObject<Block> CHARGE_LING_SHI_ORE = BLOCKS.register("charge_ling_shi_ore", () -> {
       return new ChargeLingShiOre(BlockBehaviour.Properties.of()
       .destroyTime(2.5f)
       .explosionResistance(20)
       .lightLevel(state -> 5).sound(SoundType.GLASS)
       );
    });
    //灵石矿item
    public static final RegistryObject<Item> CHARGE_LING_SHI_ORE_ITEM = ITEMS.register("charge_ling_shi_ore", () -> new BlockItem(CHARGE_LING_SHI_ORE.get(), new Item.Properties()));

    //基本令牌，不知道会不会有高级令牌
    public static final RegistryObject<Item> CHARGE_BASE_TOKEN = ITEMS.register("charge_base_token", () -> new ChargeBaseToken());

    //辟谷丹的建模
    public static final RegistryObject<Item> CHARGE_BIGU_PELLET = ITEMS.register("charge_bigu_pellet", () -> new ChargeBiGuPellet());

    //水剑——断水
    public static final RegistryObject<Item> WATER_SPLIT_SWORD = ITEMS.register("water_split_sword", () -> new WaterSplitSword());


    //mod物品列表
    public static final RegistryObject<CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS.register("charge_tab", () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> chargeBaseIngot.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(chargeBaseIngot.get()); // Add the example item to the tab. For your own tabs, this method is preferred over the event
                output.accept(chargeBow.get());
                output.accept(chargeAltarBlockItem.get());
                output.accept(chargeLingShi.get());
                output.accept(chargeLingShi.get());
                output.accept(chargeBaseBlockItem.get());
                output.accept(chargeAlchemyStoveBlockItem.get());
                output.accept(CHARGE_BASE_TOKEN.get());
                output.accept(CHARGE_BIGU_PELLET.get());
                output.accept(WATER_SPLIT_SWORD.get());
            }).build());


}