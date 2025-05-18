package com.charge.chargemod.event;

import com.charge.chargemod.hud.LingqiHud;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

//专门用来处理client端事件的类
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD,value = Dist.CLIENT)
public class ClientEventHandler {
    //// 在 RegisterGuiOverlaysEvent 事件中注册自定义的 HUD 覆盖
    @SubscribeEvent
    public static void registerGuiOverlays(RegisterGuiOverlaysEvent event) {
        // 使用 event.registerAboveAll 方法将 ExampleHud 的实例注册为最高优先级的 HUD 覆盖
        // 第一个参数是一个 ResourceLocation 对象，用于指定 HUD 的唯一标识符
        // 第二个参数是 ExampleHud 的单例对象，通过 ExampleHud.getInstance() 获取
        event.registerAboveAll("lingqi_hud", LingqiHud.getInstance());
    }

}