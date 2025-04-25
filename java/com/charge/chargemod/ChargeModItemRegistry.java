package com.charge.chargemod;

import com.charge.chargemod.block.*;
import com.charge.chargemod.entity.*;
import com.charge.chargemod.entity.calamity.CalamityLightning;
import com.charge.chargemod.entity.calamity.CalamitySanShi;
import com.charge.chargemod.entity.calamity.CalamityZombie;
import com.charge.chargemod.item.*;
import com.charge.chargemod.item.Armor.ModArmorMaterials;
import com.charge.chargemod.item.Armor.PaperHead;
import com.charge.chargemod.item.pellet.*;
import com.charge.chargemod.item.sword.*;
import com.charge.chargemod.item.talisman.*;
import com.charge.chargemod.lingqi.PlayerLingQi;
import com.charge.chargemod.lingqi.PlayerLingQiInterface;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.*;
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
    //八卦炉item
    public static final RegistryObject<Item> chargeAlchemyStoveBlockItem = ITEMS.register("charge_alchemy_stove_block", () -> new BlockItem(CHARGE_ALCHEMY_STOVE_BLOCK.get(), new Item.Properties()));
    //炼器铁砧
    public static final RegistryObject<Block> CHARGE_ALCHEMY_ANVIL_BLOCK = BLOCKS.register("charge_alchemy_anvil_block", () -> {
        return new ChargeAlchemyAnvilBlock(BlockBehaviour.Properties.of()
                .mapColor(MapColor.COLOR_BLUE) //地图颜色
                .strength(1.0f, 50.0f) //硬度，石头是1.5 & 爆炸抗性，黑曜石是50
                .lightLevel(state -> 15)
                .sound(SoundType.STONE)
                .noOcclusion()
        );}); //光照等级
    //炼器铁砧blockEntityType
    public static final RegistryObject<BlockEntityType<ChargeAlchemyAnvilBlockEntity>> CHARGE_ALCHEMY_ANVIL_ENTITY = BLOCK_ENTITIES.register("charge_alchemy_anvil_block", () ->
            BlockEntityType.Builder.of(ChargeAlchemyAnvilBlockEntity::new, CHARGE_ALCHEMY_ANVIL_BLOCK.get()).build(null));
    //炼器铁砧item
    public static final RegistryObject<Item> chargeAlchemyAnvilBlockItem = ITEMS.register("charge_alchemy_anvil_block", () -> new BlockItem(CHARGE_ALCHEMY_ANVIL_BLOCK.get(), new Item.Properties()));

    //传送阵
    public static final RegistryObject<Block> CHARGE_TELEPORT_BLOCK = BLOCKS.register("charge_teleport_block", () -> {
        return new ChargeTeleportBlock(BlockBehaviour.Properties.of()
                .mapColor(MapColor.COLOR_BLUE) //地图颜色
                .strength(1.0f, 50.0f) //硬度，石头是1.5 & 爆炸抗性，黑曜石是50
                .lightLevel(state -> 15)
                .sound(SoundType.STONE)
                .noOcclusion()
        );}); //光照等级
    //传送阵blockEntityType
    public static final RegistryObject<BlockEntityType<ChargeTeleportBlockEntity>> CHARGE_TELEPORT_ENTITY = BLOCK_ENTITIES.register("charge_teleport_block", () ->
            BlockEntityType.Builder.of(ChargeTeleportBlockEntity::new, CHARGE_TELEPORT_BLOCK.get()).build(null));
    //传送阵item
    public static final RegistryObject<Item> chargeTeleportBlockItem = ITEMS.register("charge_teleport_block", () -> new BlockItem(CHARGE_TELEPORT_BLOCK.get(), new Item.Properties()));

//    //tick响应block
//    public static final RegistryObject<Block> CHARGE_TICK_BLOCK = BLOCKS.register("charge_tick_block", () -> {
//        return new ChargeTickBlock(BlockBehaviour.Properties.of()
//                .mapColor(MapColor.COLOR_PINK) //地图颜色
//                .strength(1.0f, 50.0f) //硬度，石头是1.5 & 爆炸抗性，黑曜石是50
//                .sound(SoundType.STONE)
//        );}); //光照等级
    //tick的entityType
    public static final RegistryObject<BlockEntityType<ChargeTickBlockEntity>> CHARGE_TICK_ENTITY = BLOCK_ENTITIES.register("charge_tick_block", () ->
            BlockEntityType.Builder.of(ChargeTickBlockEntity::new, CHARGE_TELEPORT_BLOCK.get()).build(null));


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
    //藏锋飞剑
    public static final ModelLayerLocation CHARGE_CANG_FENG_DAGGER_LAYER = new ModelLayerLocation(new ResourceLocation(ChargeModItemRegistry.MODID, "charge_cang_feng_dagger"), "main");
    //藏锋飞剑自定义entityType
    public static final RegistryObject<EntityType<ChargeCangFengDaggerEntity>> CHARGE_CANG_FENG_DAGGER_ENTITY_TYPE = ENTITY_TYPES.register("charge_cang_feng_dagger", () -> EntityType.Builder.
            <ChargeCangFengDaggerEntity>of(ChargeCangFengDaggerEntity::new, MobCategory.MISC)
            .sized(0.5F, 0.5F) // 设置实体大小
            .clientTrackingRange(4) // 客户端跟踪范围
            .updateInterval(20) // 更新间隔
            .build("charge_cang_feng_dagger")
    );

    //铜钱
    public static final ModelLayerLocation COPPER_COIN_LAYER = new ModelLayerLocation(new ResourceLocation(ChargeModItemRegistry.MODID, "charge_copper_coin"), "main");
    //铜钱entityType
    public static final RegistryObject<EntityType<ChargeCopperCoinEntity>> COPPER_COIN_ENTITY_TYPE = ENTITY_TYPES.register("charge_copper_coin", () -> EntityType.Builder.
            <ChargeCopperCoinEntity>of(ChargeCopperCoinEntity::new, MobCategory.MISC)
            .sized(0.5F, 0.5F) // 设置实体大小
            .clientTrackingRange(4) // 客户端跟踪范围
            .updateInterval(20) // 更新间隔
            .build("charge_copper_coin")
    );

    //剑气
    public static final ModelLayerLocation CHARGE_BLADE_EXTEND_LAYER = new ModelLayerLocation(new ResourceLocation(ChargeModItemRegistry.MODID, "charge_blade_extend"), "main");
    //剑气entityType
    public static final RegistryObject<EntityType<ChargeBladeExtendEntity>> CHARGE_BLADE_EXTEND_ENTITY_TYPE = ENTITY_TYPES.register("charge_blade_extend", () -> EntityType.Builder.
            <ChargeBladeExtendEntity>of(ChargeBladeExtendEntity::new, MobCategory.MISC)
            .sized(16.F, 8.F) // 设置实体大小，要改
            .clientTrackingRange(4) // 客户端跟踪范围
            .updateInterval(20) // 更新间隔
            .build("charge_blade_extend")
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

    //纸头，不可获取
    public static final RegistryObject<Item> PAPER_HEAD = ITEMS.register("paper_head", () -> new PaperHead(ModArmorMaterials.PAPER, ArmorItem.Type.HELMET, (new Item.Properties())));
    //说明书，天书
    public static final RegistryObject<Item>  CHARGE_GUIDE_BOOK = ITEMS.register("charge_guide_book_item", () -> new ChargeGuideBookItem());
    //基本令牌，不知道会不会有高级令牌
    public static final RegistryObject<Item> CHARGE_BASE_TOKEN = ITEMS.register("charge_base_token", () -> new ChargeBaseToken());


    //辟谷丹
    public static final RegistryObject<Item> CHARGE_BIGU_PELLET = ITEMS.register("charge_bigu_pellet", () -> new ChargeBiGuPellet());
    //鸿运齐天丹
    public static final RegistryObject<Item> CHARGE_HONG_YUN_PELLET = ITEMS.register("charge_hong_yun_pellet", () -> new ChargeHongYunPellet());
    //大还丹
    public static final RegistryObject<Item> CHARGE_HEALTH_PELLET = ITEMS.register("charge_health_pellet", () -> new ChargeHealthPellet());
    //生力丹
    public static final RegistryObject<Item> CHARGE_POWER_PELLET = ITEMS.register("charge_power_pellet", () -> new ChargePowerPellet());
    //破万法丹
    public static final RegistryObject<Item> CHARGE_PO_WAN_FA_PELLET = ITEMS.register("charge_po_wan_fa_pellet", () -> new ChargePoWanFaPellet());
    //万物生丹
    public static final RegistryObject<Item> CHARGE_WAN_WU_SHENG_PELLET = ITEMS.register("charge_wan_wu_sheng_pellet", () -> new ChargeWanWuShengPellet());
    //三尸丹
    public static final RegistryObject<Item> CHARGE_SAN_SHI_PELLET = ITEMS.register("charge_san_shi_pellet", () -> new ChargeSanShiPellet());
    //登仙丹
    public static final RegistryObject<Item> CHARGE_DENG_XIAN_PELLET = ITEMS.register("charge_deng_xian_pellet", () -> new ChargeLightningPellet());


    //金剑——孔方
    public static final RegistryObject<Item> KONG_FANG_SWORD = ITEMS.register("kong_fang_sword", () -> new KongFangSword());
    //木剑——扶摇
    public static final RegistryObject<Item> FU_YAO_SWORD = ITEMS.register("fu_yao_sword", () -> new FuYaoSword());
    //水剑——断水
    public static final RegistryObject<Item> WATER_SPLIT_SWORD = ITEMS.register("water_split_sword", () -> new WaterSplitSword());
    //火剑——余烬
    public static final RegistryObject<Item> EMBER_SWORD = ITEMS.register("ember_sword", () -> new EmberSword());
    //土剑——冢中骨
    public static final RegistryObject<Item> ZHONG_ZHONG_GU_SWORD = ITEMS.register("zhong_zhong_gu_sword", () -> new ZhongZhongGuSword());
    //普通剑——寻因
    public static final RegistryObject<Item> XUN_YIN_SWORD = ITEMS.register("xun_yin_sword", () -> new XunYinSword());

    //金剑高级——藏锋万里
    public static final RegistryObject<Item> CANG_FENG = ITEMS.register("cang_feng_sword", () -> new CangFengSword());
    //高级木剑——欲风雨
    public static final RegistryObject<Item> YU_FENG_YU = ITEMS.register("yu_feng_yu_sword", () -> new YuFengYuSword());
    //水剑高级——百草零
    public static final RegistryObject<Item> BAI_CAO_LING = ITEMS.register("bai_cao_ling_sword", () -> new BaiCaoLingSword());
    //火剑高级——愿天寒
    public static final RegistryObject<Item> MAY_COLD = ITEMS.register("may_cold_sword", () -> new MayColdSword());
    //土剑—高级—地动
    public static final RegistryObject<Item> EARTH_BEAT = ITEMS.register("earth_beat_sword", () -> new EarthBeatSword());
    //高级剑——真
    public static final RegistryObject<Item> THE_REAL_SWORD = ITEMS.register("the_real_sword", () -> new TheRealSword());
    //高级剑——假
    public static final RegistryObject<Item> THE_FAKE_SWORD = ITEMS.register("the_fake_sword", () -> new TheFakeSword());
    //高级剑——塑果
    public static final RegistryObject<Item> SU_GUO_SWORD = ITEMS.register("su_guo_sword", () -> new SuGuoSword());
    //仙剑——无相
    public static final RegistryObject<Item> WU_XIANG_SWORD = ITEMS.register("wu_xiang_sword", () -> new WuXiangSword());
    //仙剑——求逍遥
    public static final RegistryObject<Item> QIU_XIAO_YAO_SWORD = ITEMS.register("qiu_xiao_yao_sword", () -> new QiuXiaoYaoSword());
    //仙剑——乱因果

    //符咒
    //退魔符
    public static final RegistryObject<Item> PUSH_BACK_TALISMAN = ITEMS.register("push_back_talisman", () -> new PushBackTalisman());
    //御劫符
    public static final RegistryObject<Item> SHIELD_TALISMAN = ITEMS.register("shield_talisman", () -> new ShieldTalisman());
    //换颅符
    public static final RegistryObject<Item> SKULL_STEAL_TALISMAN = ITEMS.register("skull_steal_talisman", () -> new SkullStealTalisman());
    //延死符
    public static final RegistryObject<Item> HOLD_LIFE_TALISMAN = ITEMS.register("hold_life_talisman", () -> new HoldLifeTalisman());
    //回生符
    public static final RegistryObject<Item> RELIVE_TALISMAN = ITEMS.register("relive_talisman", () -> new ReliveTalisman());
    //仙游符
    public static final RegistryObject<Item> FLY_TALISMAN = ITEMS.register("fly_talisman", () -> new FlyTalisman());
    //祈雨符
    public static final RegistryObject<Item> RAIN_TALISMAN = ITEMS.register("rain_talisman", () -> new RainTalisman());
    //请神符
    public static final RegistryObject<Item> INVITE_GOD_TALISMAN = ITEMS.register("invite_god_talisman", () -> new InviteGodTalisman());
    //仙符 求不得
    public static final RegistryObject<Item> QIU_BU_DE_TALISMAN = ITEMS.register("qiu_bu_de_talisman", () -> new QiuBuDeTalisman());
    //仙符 解不出
    public static final RegistryObject<Item> JIE_BU_CHU_TALISMAN = ITEMS.register("jie_bu_chu_talisman", () -> new JieBuChuTalisman());
    //仙符 想不通
    public static final RegistryObject<Item> XIANG_BU_TONG_TALISMAN = ITEMS.register("xiang_bu_tong_talisman", () -> new XiangBuTongTalisman());
    //仙符 算不对
    public static final RegistryObject<Item> SUAN_BU_DUI_TALISMAN = ITEMS.register("suan_bu_dui_talisman", () -> new SuanBuDuiTalisman());
    //仙符 迷惘
    public static final RegistryObject<Item> MAZE_TALISMAN = ITEMS.register("maze_talisman", () -> new MazeTalisman());

    //mod物品列表
    public static final RegistryObject<CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS.register("charge_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup." + MODID + ".charge_tab"))
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> chargeBaseIngot.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(chargeBaseIngot.get()); // Add the example item to the tab. For your own tabs, this method is preferred over the event\
                output.accept(CHARGE_GUIDE_BOOK.get());
                output.accept(chargeBow.get());
                output.accept(chargeAltarBlockItem.get());
                output.accept(chargeLingShi.get());
                output.accept(chargeLingShi.get());
                output.accept(chargeBaseBlockItem.get());
                output.accept(chargeAlchemyAnvilBlockItem.get());
                output.accept(chargeAlchemyStoveBlockItem.get());
                output.accept(CHARGE_BASE_TOKEN.get());

                //丹药
                output.accept(CHARGE_BIGU_PELLET.get());
                output.accept(CHARGE_HONG_YUN_PELLET.get());
                output.accept(CHARGE_HEALTH_PELLET.get());
                output.accept(CHARGE_POWER_PELLET.get());
                output.accept(CHARGE_PO_WAN_FA_PELLET.get());
                output.accept(CHARGE_WAN_WU_SHENG_PELLET.get());
                output.accept(CHARGE_SAN_SHI_PELLET.get());
                output.accept(CHARGE_DENG_XIAN_PELLET.get());

//                output.accept(PAPER_HEAD.get());
                //剑
                output.accept(KONG_FANG_SWORD.get());
                output.accept(FU_YAO_SWORD.get());
                output.accept(WATER_SPLIT_SWORD.get());
                output.accept(EMBER_SWORD.get());
                output.accept(ZHONG_ZHONG_GU_SWORD.get());
                output.accept(XUN_YIN_SWORD.get());
                //高级剑
                output.accept(CANG_FENG.get());
                output.accept(YU_FENG_YU.get());
                output.accept(BAI_CAO_LING.get());
                output.accept(MAY_COLD.get());
                output.accept(EARTH_BEAT.get());
                output.accept(THE_REAL_SWORD.get());
                output.accept(THE_FAKE_SWORD.get());
                output.accept(SU_GUO_SWORD.get());
                output.accept(WU_XIANG_SWORD.get());
                output.accept(QIU_XIAO_YAO_SWORD.get());

                //符
                output.accept(PUSH_BACK_TALISMAN.get());
                output.accept(SHIELD_TALISMAN.get());
                output.accept(SKULL_STEAL_TALISMAN.get());
                output.accept(HOLD_LIFE_TALISMAN.get());
                output.accept(RELIVE_TALISMAN.get());
                output.accept(FLY_TALISMAN.get());
                output.accept(RAIN_TALISMAN.get());
                output.accept(INVITE_GOD_TALISMAN.get());
                output.accept(QIU_BU_DE_TALISMAN.get());
                output.accept(JIE_BU_CHU_TALISMAN.get());
                output.accept(XIANG_BU_TONG_TALISMAN.get());
                output.accept(SUAN_BU_DUI_TALISMAN.get());
                output.accept(MAZE_TALISMAN.get());

                //阵
                output.accept(chargeTeleportBlockItem.get());   //传送阵
            }).build());

    public static final RegistryObject<EntityType<FakeVillager>> FAKE_VILLAGER = ENTITY_TYPES.register("fake_villager",
            () -> EntityType.Builder.of(FakeVillager::new, MobCategory.CREATURE)
                    .sized(0.6F, 1.95F) // 设置实体大小
                    .build("fake_villager"));
    public static final RegistryObject<EntityType<CalamityZombie>> CALAMITY_ZOMBIE = ENTITY_TYPES.register("calamity_zombie",
            () -> EntityType.Builder.of(CalamityZombie::new, MobCategory.CREATURE)
                    .sized(0.6F, 1.95F) // 设置实体大小
                    .build("calamity_zombie"));
    public static final RegistryObject<EntityType<CalamitySanShi>> CALAMITY_SANSHI = ENTITY_TYPES.register("calamity_sanshi",
            () -> EntityType.Builder.of(CalamitySanShi::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F) // 设置实体大小
                    .build("calamity_sanshi"));
    public static final RegistryObject<EntityType<CalamityLightning>> CALAMITY_LIGHTNING = ENTITY_TYPES.register("calamity_lightning",
            () -> EntityType.Builder.of(CalamityLightning::new, MobCategory.MISC)
                    .sized(0.6F, 1.95F) // 设置实体大小
                    .build("calamity_lightning"));


}