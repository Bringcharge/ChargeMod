package com.charge.chargemod.runData;


import com.charge.chargemod.ChargeModItemRegistry;
import com.charge.chargemod.runData.damage.DamageTypeTagGenerator;
import com.charge.chargemod.runData.loot.ChargeBlockLootProvider;
import com.charge.chargemod.runData.loot.ChargeLootTableProvider;
import com.charge.chargemod.runData.ChargeWorldGen;
import com.charge.chargemod.runData.runDatarecipe.ModRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

//监听runData事件的类，再事件里添加各类注册事件
@Mod.EventBusSubscriber(modid = ChargeModItemRegistry.MODID,bus = Mod.EventBusSubscriber.Bus.MOD)
public class ChargeDataGeneratorHandler {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event){
        DataGenerator generator = event.getGenerator();
        PackOutput output = event.getGenerator().getPackOutput();
        ExistingFileHelper efh = event.getExistingFileHelper();
        var lp = event.getLookupProvider();
        DatapackBuiltinEntriesProvider dataPackProvider = new ChargeWorldGen(output, lp);
        CompletableFuture<HolderLookup.Provider> lookupProvider = dataPackProvider.getRegistryProvider();

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
        //添加伤害类型
        event.getGenerator().addProvider(event.includeServer(), new DamageTypeTagGenerator(output, lookupProvider, efh));
        event.getGenerator().addProvider(event.includeServer(), (DataProvider.Factory<ModRecipeProvider>) pOutput -> new ModRecipeProvider(pOutput));

    }
}