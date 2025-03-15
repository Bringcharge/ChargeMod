package com.charge.chargemod.entity;

import com.charge.chargemod.ChargeModItemRegistry;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.animal.allay.Allay;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import net.minecraft.world.entity.animal.camel.Camel;
import net.minecraft.world.entity.animal.frog.Frog;
import net.minecraft.world.entity.animal.frog.Tadpole;
import net.minecraft.world.entity.animal.goat.Goat;
import net.minecraft.world.entity.animal.horse.*;
import net.minecraft.world.entity.animal.sniffer.Sniffer;
import net.minecraft.world.entity.boss.enderdragon.EndCrystal;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.decoration.*;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.monster.hoglin.Hoglin;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.monster.piglin.PiglinBrute;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.WanderingTrader;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.*;
import net.minecraft.world.entity.vehicle.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;

@Mod.EventBusSubscriber(modid = ChargeModItemRegistry.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class EntityRenderChangeHelper {



//    @SubscribeEvent
//    public static void onRenderWorldLast(RenderLevelStageEvent event) {
//        if (event.getStage() == RenderLevelStageEvent.Stage.AFTER_ENTITIES) {
//            Player player = Minecraft.getInstance().player;
//            if (player != null) {
//                // 渲染虚拟实体
//                renderVirtualEntity(event.getPoseStack(), event.getCamera(), event.getPartialTick());
//            }
//        }
//    }
//
//    private static void renderVirtualEntity(PoseStack poseStack, Camera camera, float partialTicks) {
//        Minecraft minecraft = Minecraft.getInstance();
//        EntityRenderDispatcher renderDispatcher = minecraft.getEntityRenderDispatcher();
//        MultiBufferSource.BufferSource bufferSource = minecraft.renderBuffers().bufferSource();
//        if (!visualEntities.isEmpty()) {
//            VisualZombie VIRTUAL_ENTITY = (VisualZombie)visualEntities.get(0);
//            // 设置虚拟实体的位置
//            VIRTUAL_ENTITY.setPos(camera.getPosition().x, camera.getPosition().y, camera.getPosition().z);
//
//            // 渲染虚拟实体
//            poseStack.pushPose();
//            poseStack.translate(-camera.getPosition().x, -camera.getPosition().y, -camera.getPosition().z);
//            renderDispatcher.render(VIRTUAL_ENTITY, 0, 0, 0, 0, partialTicks, poseStack, bufferSource, 15728880);
//            poseStack.popPose();
//
//            // 提交渲染
//            bufferSource.endBatch();
//        }
//    }

//    @SubscribeEvent
//    public static void onRenderLiving(RenderLivingEvent.Pre<?, ?> event) {
//        Level level = event.getEntity().level();
//        if (level.isClientSide) {
//            ClientLevel clientLevel = (ClientLevel) level;
//            Player player = Minecraft.getInstance().player;
//
//            // 玩家持有自定义物品，触发渲染逻辑
//            Inventory inventory = player.getInventory();
//            boolean temp = false;
//            for (int i = 0; i < inventory.getContainerSize(); ++i) {
//                ItemStack itemstack1 = inventory.getItem(i);
//                if (itemstack1.is(ChargeModItemRegistry.WU_XIANG_SWORD.get()) && !temp) {
//                    temp = true;
////                    setRandomEntityModel();
//                    break;
//                }
//            }
//        }
//    }

//    public static void onRenderLiving(RenderLivingEvent.Pre<?, ?> event) {
//        Player player = Minecraft.getInstance().player;
//        if (player != null && player.hasEffect(YourModEffects.TRANSMUTATION_EFFECT.get())) {
//            // 如果玩家拥有 TransmutationEffect，应用渲染变化
//            if (event.getEntity() instanceof Skeleton) {
//                // 将骷髅渲染为僵尸
//                event.setCanceled(true);
//                Minecraft.getInstance().getEntityRenderDispatcher().render(event.getEntity(), event.getPartialTick(), event.getMatrixStack(), event.getBuffers(), event.getLight());
//            } else if (event.getEntity() instanceof Zombie) {
//                // 将僵尸渲染为爬行者
//                event.setCanceled(true);
//                Minecraft.getInstance().getEntityRenderDispatcher().render(event.getEntity(), event.getPartialTick(), event.getMatrixStack(), event.getBuffers(), event.getLight());
//            }
//        }
//    }




//    private static EntityRenderer<?> getRandomRenderer(LivingEntity entity) {
//        EntityType<?>[] randomEntityTypes = {
//                EntityType.ZOMBIE, EntityType.SKELETON, EntityType.CREEPER, EntityType.ENDERMAN
//                // 添加更多怪物类型
//        };
//
//        // 随机选择一个实体类型
//        EntityType<?> randomType = randomEntityTypes[entity.level.random.nextInt(randomEntityTypes.length)];
//
//        // 获取渲染器
//        return Minecraft.getInstance().getEntityRenderDispatcher().getRenderer(randomType.create(entity.level));
//    }

    private static void setRandomEntityModel() {
        Minecraft minecraft = Minecraft.getInstance();
        Level level = minecraft.level;

        EntityType<?>[] randomEntityTypes = {
                EntityType.ZOMBIE, EntityType.SKELETON, EntityType.CREEPER, EntityType.PLAYER
                // 添加更多怪物类型
        };
        EntityType<?>[] targetType = {
            EntityType.ALLAY,//愉灵
            EntityType.ARMOR_STAND, //盔甲架
            EntityType.AXOLOTL,//美西钝口螈
            EntityType.BAT,//蝙蝠
            EntityType.BEE,//蜜蜂
            EntityType.BLAZE,//烈焰人
            EntityType.CAMEL,//骆驼
            EntityType.CAT,//猫
            EntityType.CAVE_SPIDER,//洞蜘蛛
            EntityType.CHICKEN,//鸡
            EntityType.COD,//鳕鱼
            EntityType.COW,//牛
            EntityType.CREEPER,//爬行者，鸡鸡怪
            EntityType.DOLPHIN,//海豚
            EntityType.DONKEY,//驴子
            EntityType.DROWNED,//溺尸
            EntityType.ELDER_GUARDIAN,//远古守护者
            EntityType.ENDERMAN,//末影人
            EntityType.ENDERMITE,//末影螨
            EntityType.EVOKER,//召唤师，塑能师
            EntityType.FOX,//狐狸
            EntityType.FROG,//青蛙
            EntityType.GHAST,//恶魂
            EntityType.GLOW_SQUID,//发光鱿鱼
            EntityType.GOAT,//山羊
            EntityType.GUARDIAN,//守护者
            EntityType.HOGLIN,//疣猪兽
            EntityType.HORSE,//马
            EntityType.HUSK,//尸壳
            EntityType.ILLUSIONER,//幻术师？？
            EntityType.IRON_GOLEM,//铁傀儡
            EntityType.LLAMA,//羊驼
            EntityType.MAGMA_CUBE,//岩浆怪
            EntityType.MULE,//骡子
            EntityType.OCELOT,//山猫
            EntityType.PANDA,//熊猫
            EntityType.PARROT,//鹦鹉
            EntityType.PHANTOM,//幻翼
            EntityType.PIG,//猪
            EntityType.PIGLIN,//猪灵
            EntityType.PIGLIN_BRUTE,//狂暴猪灵
            EntityType.PILLAGER,//掠夺者
            EntityType.POLAR_BEAR,//北极熊
            EntityType.PUFFERFISH,//河豚
            EntityType.RABBIT,//兔子
            EntityType.RAVAGER,//劫掠兽
            EntityType.SALMON,//鲑鱼？三文鱼？
            EntityType.SHEEP,//绵羊
            EntityType.SHULKER,//潜影贝
            EntityType.SILVERFISH,//蠹虫
            EntityType.SKELETON,//骷髅
            EntityType.SKELETON_HORSE,//骷髅马
            EntityType.SLIME,//史莱姆
            EntityType.SNIFFER,//嗅探兽
            EntityType.SNOW_GOLEM,//雪傀儡
            EntityType.SPIDER,//蜘蛛
            EntityType.SQUID,//鱿鱼
            EntityType.STRAY,//流髑
            EntityType.STRIDER,//炽足兽
            EntityType.TADPOLE,//蝌蚪
            EntityType.TRADER_LLAMA,//行商羊驼
            EntityType.TROPICAL_FISH,//热带鱼
            EntityType.TURTLE,//海龟
            EntityType.VEX,//恼鬼
            EntityType.VILLAGER,//村民
            EntityType.VINDICATOR,//卫道士
            EntityType.WANDERING_TRADER,//流浪商人
            EntityType.WARDEN,//坚守者
            EntityType.WITCH,//女巫
            EntityType.WITHER_SKELETON,//凋零骷髅
            EntityType.WOLF,//狼
            EntityType.ZOGLIN,//僵尸疣猪兽
            EntityType.ZOMBIE,//僵尸
            EntityType.ZOMBIE_HORSE,//僵尸马
            EntityType.ZOMBIE_VILLAGER,//僵尸村民
            EntityType.ZOMBIFIED_PIGLIN,//僵尸猪灵
            EntityType.PLAYER//玩家
        };


        // 随机选择一个实体类型
        EntityType<?> randomType = randomEntityTypes[level.random.nextInt(randomEntityTypes.length)];


        for (EntityType type : targetType) {
            // 获取该实体类型的渲染器
            EntityRenderer<?> renderer = Minecraft.getInstance().getEntityRenderDispatcher().getRenderer(randomType.create(level));
            // 替换当前实体的渲染器
            if (renderer != null) {
                Minecraft.getInstance().getEntityRenderDispatcher().renderers.put(type, renderer);
            }
        }
    }
}
