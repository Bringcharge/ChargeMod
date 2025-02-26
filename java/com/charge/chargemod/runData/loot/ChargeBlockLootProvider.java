package com.charge.chargemod.runData.loot;

import com.charge.chargemod.ChargeModItemRegistry;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.CopyNameFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.Collections;
import java.util.Set;

//掉落物子表，参考VanillaBlockLoot的内容就可以
public class ChargeBlockLootProvider extends BlockLootSubProvider {

    public static final Set<Block> BLOCK = Set.of(
            ChargeModItemRegistry.CHARGE_LING_SHI_ORE.get(),
            ChargeModItemRegistry.CHARGE_ALTAR_BLOCK.get()  //祭坛的东西
    );

    //方法中的第一个参数是一个空的集合，该集合是指免疫爆炸的方块列表，这里传入的是一个空。第二个参数照这样写即可。
    public ChargeBlockLootProvider() {
        super(Collections.emptySet(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.add(ChargeModItemRegistry.CHARGE_ALTAR_BLOCK.get(),(block) ->  LootTable.lootTable().withPool(
                this.applyExplosionCondition(block, LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(block).apply(CopyNameFunction.copyName(CopyNameFunction.NameSource.BLOCK_ENTITY))))));
//        this.dropSelf(ChargeModItemRegistry.CHARGE_ALTAR_BLOCK.get());
        this.add(ChargeModItemRegistry.CHARGE_LING_SHI_ORE.get(),
                createSilkTouchDispatchTable(ChargeModItemRegistry.CHARGE_LING_SHI_ORE.get(),
                        this.applyExplosionDecay(ChargeModItemRegistry.CHARGE_LING_SHI_ORE.get(),   //第一个参数是本体，看是不是防爆
                            LootItem.lootTableItem(ChargeModItemRegistry.chargeLingShi.get()).  //第二个参数是凋落物
                            apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F))). //掉落情况，Uniform是均匀掉落，2~4个
                            apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE)) //支持时运等级的影响
                        )
                )
        );
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return BLOCK;
    }
}