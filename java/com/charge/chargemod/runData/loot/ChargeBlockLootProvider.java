package com.charge.chargemod.runData.loot;

import com.charge.chargemod.ChargeModItemRegistry;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;

import java.util.Collections;
import java.util.Set;

public class ChargeBlockLootProvider extends BlockLootSubProvider {

    public static final Set<Block> BLOCK = Set.of(
            ChargeModItemRegistry.CHARGE_LING_SHI_ORE.get()
    );

    public ChargeBlockLootProvider() {
        super(Collections.emptySet(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropOther(ChargeModItemRegistry.CHARGE_LING_SHI_ORE.get(), ChargeModItemRegistry.chargeLingShi.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return BLOCK;
    }
}