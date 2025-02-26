package com.charge.chargemod;


import com.charge.chargemod.runData.loot.ChargeBlockLootProvider;
import com.charge.chargemod.runData.loot.ChargeLootTableProvider;
import com.charge.chargemod.runData.oreFeature.ChargeWorldGen;
import net.minecraft.data.DataProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Collections;
import java.util.List;

//监听runData事件的类，再事件里添加各类注册事件
@Mod.EventBusSubscriber(modid = ChargeModItemRegistry.MODID,bus = Mod.EventBusSubscriber.Bus.MOD)
public class ChargeDataGeneratorHandler {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event){
        ExistingFileHelper efh = event.getExistingFileHelper();
        var lp = event.getLookupProvider();
        //world  gen事件
        event.getGenerator().addProvider(event.includeServer(), (DataProvider.Factory<ChargeWorldGen>) pOutput -> new ChargeWorldGen(pOutput,lp));

        //loot战利品注册
        event.getGenerator().addProvider(
                event.includeServer(),
                (DataProvider.Factory<ChargeLootTableProvider>)pOutput -> new ChargeLootTableProvider(pOutput, Collections.emptySet(),
                        List.of(
                                new LootTableProvider.SubProviderEntry(ChargeBlockLootProvider::new, LootContextParamSets.BLOCK)
                        ))
        );

    }
}