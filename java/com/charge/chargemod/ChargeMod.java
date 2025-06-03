package com.charge.chargemod;

import com.charge.chargemod.effect.ModEffects;
import com.charge.chargemod.entity.ChargeDaggerEntity;
import com.charge.chargemod.entity.FakeVillager;
import com.charge.chargemod.entity.calamity.CalamityZombie;
import com.charge.chargemod.entityModel.ChargeBladeExtendModel;
import com.charge.chargemod.entityModel.ChargeCangFengDaggerModel;
import com.charge.chargemod.entityModel.ChargeCopperCoinModel;
import com.charge.chargemod.entityModel.ChargeDaggerEntityModel;
import com.charge.chargemod.lingqi.PlayerLingQiInterface;
import com.charge.chargemod.network.ChargeNetwork;
import com.charge.chargemod.particle.ChargeModParticleType;
import com.charge.chargemod.render.*;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.NoopRenderer;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ChargeModItemRegistry.MODID)
@Mod.EventBusSubscriber(modid = ChargeModItemRegistry.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ChargeMod
{
    // Define mod id in a common place for everything to reference

    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();


    // Creates a creative tab with the id "examplemod:example_tab" for the example item, that is placed after the combat tab


    public ChargeMod()
    {

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        FMLJavaModLoadingContext.get().getModEventBus().register(this); //注册事件体
        modEventBus.addListener(this::commonSetup); //mod event 注册监听

        //注册blocks
        ChargeModItemRegistry.BLOCKS.register(modEventBus);
        //注册item
        ChargeModItemRegistry.ITEMS.register(modEventBus);
        //注册物品列表
        ChargeModItemRegistry.CREATIVE_MODE_TABS.register(modEventBus);
        //注册entity
        ChargeModItemRegistry.ENTITY_TYPES.register(modEventBus);
        //注册block entity列表
        ChargeModItemRegistry.BLOCK_ENTITIES.register(modEventBus);
        ChargeModParticleType.PARTICLE_TYPES.register(modEventBus);
        //特殊效果注册器
        ModEffects.MOD_EFFECTS.register(modEventBus);
        // Register ourselves for server and other game events we are interested in
//        MinecraftForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
//        modEventBus.addListener(this::addCreative);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    //注册layer
    @SubscribeEvent
    public static void onRegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {//实体layer关联
//        System.out.println("Registering ChargeDaggerEntity...test1");
        event.registerLayerDefinition(ChargeModItemRegistry.CHARGE_DAGGER_LAYER, ChargeDaggerEntityModel::createBodyLayer);
        event.registerLayerDefinition(ChargeModItemRegistry.CHARGE_CANG_FENG_DAGGER_LAYER, ChargeCangFengDaggerModel::createBodyLayer);
        event.registerLayerDefinition(ChargeModItemRegistry.COPPER_COIN_LAYER, ChargeCopperCoinModel::createBodyLayer);
        event.registerLayerDefinition(ChargeModItemRegistry.CHARGE_BLADE_EXTEND_LAYER, ChargeBladeExtendModel::createBodyLayer);
    }

    //注册render
    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {  //渲染关联
//        System.out.println("Registering ChargeDaggerEntity...test2");
        event.registerEntityRenderer(ChargeModItemRegistry.CHARGE_CANG_FENG_DAGGER_ENTITY_TYPE.get(), ChargeCangFengDaggerEntityRenderer::new);
        event.registerEntityRenderer(ChargeModItemRegistry.CHARGE_DAGGER_ENTITY_TYPE.get(), ChargeDaggerEntityRenderer::new);
        event.registerEntityRenderer(ChargeModItemRegistry.COPPER_COIN_ENTITY_TYPE.get(), ChargeCopperCoinRenderer::new);
        event.registerEntityRenderer(ChargeModItemRegistry.CHARGE_BLADE_EXTEND_ENTITY_TYPE.get(), NoopRenderer::new);
        event.registerBlockEntityRenderer(ChargeModItemRegistry.CHARGE_ALTAR_ENTITY.get(), ChargeAltarRender::new);
        event.registerBlockEntityRenderer(ChargeModItemRegistry.CHARGE_ALCHEMY_STOVE_ENTITY.get(), ChargeAlchemyStoveRender::new);
        event.registerBlockEntityRenderer(ChargeModItemRegistry.CHARGE_ALCHEMY_ANVIL_ENTITY.get(), ChargeAlchemyAnvilRender::new);
    }

    //注册能力
    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(PlayerLingQiInterface.class);
    }

    // 注册entity
    @SubscribeEvent
    public static void onEntityRegister(RegisterEvent event) {
        if (event.getRegistryKey().equals(ForgeRegistries.ENTITY_TYPES.getRegistryKey())) { //普通entity type注册事件
//            System.out.println("Registering Entity Type...");
            event.register(ForgeRegistries.ENTITY_TYPES.getRegistryKey(), helper -> {
                helper.register("charge_dagger", ChargeModItemRegistry.CHARGE_DAGGER_ENTITY_TYPE.get());
                helper.register("charge_cang_feng_dagger",ChargeModItemRegistry.CHARGE_CANG_FENG_DAGGER_ENTITY_TYPE.get());
                helper.register("fake_villager",ChargeModItemRegistry.FAKE_VILLAGER.get());
                helper.register("calamity_zombie", ChargeModItemRegistry.CALAMITY_ZOMBIE.get());
            });
        }
        if (event.getRegistryKey().equals(ForgeRegistries.BLOCK_ENTITY_TYPES.getRegistryKey())) {   //注册block entity
            event.register(ForgeRegistries.BLOCK_ENTITY_TYPES.getRegistryKey(), helper -> {
                System.out.println("Registering Block Entity Type...");
                helper.register("charge_altar_block", ChargeModItemRegistry.CHARGE_ALTAR_ENTITY.get());
            });

        }
    }
    @SubscribeEvent
    public static void setupAttributes(EntityAttributeCreationEvent event) {
        // 第一个参数是你的entityType，第二个参数是你的AttributeSupplier，通过我们写的Builder获得。
        event.put(ChargeModItemRegistry.FAKE_VILLAGER.get(), FakeVillager.createAttributes().build());
        event.put(ChargeModItemRegistry.CALAMITY_ZOMBIE.get(), CalamityZombie.createAttributes().build());
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");

        if (Config.logDirtBlock)
            LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));

        LOGGER.info(Config.magicNumberIntroduction + Config.magicNumber);

        Config.items.forEach((item) -> LOGGER.info("ITEM >> {}", item.toString()));

        ChargeNetwork.register();

        event.enqueueWork(() -> {
            // 在这里执行需要在主线程中运行的任务

        });
    }

    // Add the example block item to the building blocks tab
//    private void addCreative(BuildCreativeModeTabContentsEvent event)
//    {
//        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS)
//            event.accept(EXAMPLE_BLOCK_ITEM);
//    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
//    @SubscribeEvent
//    public void onServerStarting(ServerStartingEvent event)
//    {
//        // Do something when the server starts
//        LOGGER.info("HELLO from server starting");
//    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
//    @Mod.EventBusSubscriber(modid = ChargeModItemRegistry.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
//    public static class ClientModEvents
//    {
//        @SubscribeEvent
//        public static void onClientSetup(FMLClientSetupEvent event)
//        {
//            // Some client setup code
//            LOGGER.info("HELLO FROM CLIENT SETUP");
//            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
////            EntityRenderers.register(ChargeModItemRegistry.CHARGE_DAGGER_ENTITY_ENTITY_TYPE, ChargeDaggerEntityRender::new);    //为什么放这里，不懂
//
//        }
//    }
}
