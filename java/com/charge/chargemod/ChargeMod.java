package com.charge.chargemod;

import com.charge.chargemod.entity.ChargeDaggerEntity;
import com.charge.chargemod.entityModel.ChargeDaggerEntityModel;
import com.charge.chargemod.render.ChargeDaggerEntityRenderer;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderers;
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
//    public static final EntityType<ChargeDaggerEntity> CHARGE_DAGGER =  Registry.register(BuiltInRegistries.ENTITY_TYPE, "charge_dagger", EntityType.Builder.<ChargeDaggerEntity>of(
//            ChargeDaggerEntity::new, MobCategory.MISC)
//            .sized(0.5F, 0.5F)
//            .clientTrackingRange(4)
//            .updateInterval(20)
//            .build("charge_dagger")
//    );
    // Define mod id in a common place for everything to reference

    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();


    // Creates a creative tab with the id "examplemod:example_tab" for the example item, that is placed after the combat tab


    public ChargeMod()
    {

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        FMLJavaModLoadingContext.get().getModEventBus().register(this);
//        ChargeModItemRegistry.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
//        ChargeModItemRegistry.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);
        // Register the Deferred Register to the mod event bus so blocks get registered
        ChargeModItemRegistry.BLOCKS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so items get registered
        ChargeModItemRegistry.ITEMS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so tabs get registered
        ChargeModItemRegistry.CREATIVE_MODE_TABS.register(modEventBus);
        //注册entity
        ChargeModItemRegistry.ENTITY_TYPES.register(modEventBus);
        // Register ourselves for server and other game events we are interested in
//        MinecraftForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
//        modEventBus.addListener(this::addCreative);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    @SubscribeEvent
    public static void onRegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {//实体layer关联
        System.out.println("Registering ChargeDaggerEntity...test1");
        event.registerLayerDefinition(ChargeModItemRegistry.CHARGE_DAGGER_LAYER, ChargeDaggerEntityModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {  //渲染关联
        System.out.println("Registering ChargeDaggerEntity...test2");
        event.registerEntityRenderer(ChargeModItemRegistry.CHARGE_DAGGER_ENTITY_TYPE.get(), ChargeDaggerEntityRenderer::new);
    }

    // 在 Mod 初始化时注册
    @SubscribeEvent
    public static void onEntityRegister(RegisterEvent event) {
        if (event.getRegistryKey().equals(ForgeRegistries.ENTITY_TYPES.getRegistryKey())) {
            System.out.println("Registering ChargeDaggerEntity...");
            event.register(ForgeRegistries.ENTITY_TYPES.getRegistryKey(), helper -> {
                helper.register("charge_dagger", ChargeModItemRegistry.CHARGE_DAGGER_ENTITY_TYPE.get());
            });
        }
    }


    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");

        if (Config.logDirtBlock)
            LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));

        LOGGER.info(Config.magicNumberIntroduction + Config.magicNumber);

        Config.items.forEach((item) -> LOGGER.info("ITEM >> {}", item.toString()));

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
