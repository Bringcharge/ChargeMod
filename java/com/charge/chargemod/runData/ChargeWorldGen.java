package com.charge.chargemod.runData;

import com.charge.chargemod.ChargeModItemRegistry;
import com.charge.chargemod.damage.ChargeDamageTypes;
import com.charge.chargemod.runData.oreFeature.ChargeBiomeModifiers;
import com.charge.chargemod.runData.oreFeature.ChargeOreFeatures;
import com.charge.chargemod.runData.oreFeature.ChargeOrePlacements;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;


//世界生成json生成
public class ChargeWorldGen extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, ChargeOreFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, ChargeOrePlacements::bootstrap)

            .add(ForgeRegistries.Keys.BIOME_MODIFIERS, ChargeBiomeModifiers::bootstrap)
            .add(Registries.DAMAGE_TYPE, ChargeDamageTypes::bootstrap)
            ;//ModBiomeModifiers



    public ChargeWorldGen(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(ChargeModItemRegistry.MODID));
    }
}