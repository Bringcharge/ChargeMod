package com.charge.chargemod.runData.loot;

import com.charge.chargemod.ChargeModItemRegistry;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.FishingHookPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.data.loot.packs.VanillaChestLoot;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.StructureTags;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.saveddata.maps.MapDecoration;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.EmptyLootItem;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootTableReference;
import net.minecraft.world.level.storage.loot.functions.*;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.Collections;
import java.util.Set;
import java.util.function.BiConsumer;

//掉落物子表，参考VanillaBlockLoot的内容就可以
public class ChargeChestLootProvider extends VanillaChestLoot {

    public static final Set<Block> BLOCK = Set.of(
            ChargeModItemRegistry.CHARGE_LING_SHI_ORE.get(),
            ChargeModItemRegistry.CHARGE_ALTAR_BLOCK.get(),  //祭坛的东西
            ChargeModItemRegistry.CHARGE_ALCHEMY_STOVE_BLOCK.get(), //八卦炉
            ChargeModItemRegistry.CHARGE_ALCHEMY_ANVIL_BLOCK.get(), //砧
            ChargeModItemRegistry.CHARGE_ARRAY_FLAG.get(),  //旗
            ChargeModItemRegistry.CHARGE_BASE_BLOCK.get()   //基石
    );

    @Override
    public void generate(BiConsumer<ResourceLocation, LootTable.Builder> p_250931_) {
//        p_250931_.accept(BuiltInLootTables.ABANDONED_MINESHAFT, chargeNormalLootTable());   //费矿井
//        p_250931_.accept(BuiltInLootTables.BASTION_BRIDGE, chargeNormalLootTable());    //堡垒桥
        p_250931_.accept(BuiltInLootTables.BASTION_HOGLIN_STABLE, chargeNormalLootTable()); //堡垒马厩
        p_250931_.accept(BuiltInLootTables.BASTION_OTHER, chargeNormalLootTable()); //堡垒其他
        p_250931_.accept(BuiltInLootTables.BASTION_TREASURE, chargeHighLevelLootTable());  //堡垒宝藏
        p_250931_.accept(BuiltInLootTables.BURIED_TREASURE, chargeNormalLootTable());   //埋藏宝藏
        p_250931_.accept(BuiltInLootTables.ANCIENT_CITY, chargeNormalLootTable());  //古老城市
        p_250931_.accept(BuiltInLootTables.ANCIENT_CITY_ICE_BOX, chargeNormalLootTable()); //古老城市冰箱子
        p_250931_.accept(BuiltInLootTables.DESERT_PYRAMID, chargeNormalLootTable());    //沙漠金字塔
//        p_250931_.accept(BuiltInLootTables.END_CITY_TREASURE, chargeNormalLootTable()); //末地城宝箱
        p_250931_.accept(BuiltInLootTables.IGLOO_CHEST, chargeNormalLootTable());   //冰屋箱
        p_250931_.accept(BuiltInLootTables.JUNGLE_TEMPLE, chargeNormalLootTable()); //雨林神庙
//        p_250931_.accept(BuiltInLootTables.JUNGLE_TEMPLE_DISPENSER, chargeNormalLootTable());   //雨林神庙分配器
        p_250931_.accept(BuiltInLootTables.NETHER_BRIDGE, chargeNormalLootTable());//下届桥
        p_250931_.accept(BuiltInLootTables.PILLAGER_OUTPOST, chargeNormalLootTable());//掠夺者前哨
        p_250931_.accept(BuiltInLootTables.SHIPWRECK_MAP, chargeNormalLootTable());//沉船地图
//        p_250931_.accept(BuiltInLootTables.SHIPWRECK_SUPPLY, chargeNormalLootTable());//沉船补给
        p_250931_.accept(BuiltInLootTables.SHIPWRECK_TREASURE, chargeNormalLootTable());//沉船宝箱
        p_250931_.accept(BuiltInLootTables.SIMPLE_DUNGEON, chargeNormalLootTable());//简易地牢
//        p_250931_.accept(BuiltInLootTables.SPAWN_BONUS_CHEST, chargeNormalLootTable()); //初始奖励箱
        p_250931_.accept(BuiltInLootTables.STRONGHOLD_CORRIDOR, chargeHighLevelLootTable()); //要塞走廊
        p_250931_.accept(BuiltInLootTables.STRONGHOLD_CROSSING, chargeHighLevelLootTable()); //要塞穿越？
        p_250931_.accept(BuiltInLootTables.STRONGHOLD_LIBRARY, chargeHighLevelLootTable()); //要塞图书馆
        p_250931_.accept(BuiltInLootTables.UNDERWATER_RUIN_BIG, chargeNormalLootTable()); //大水下遗迹
        p_250931_.accept(BuiltInLootTables.UNDERWATER_RUIN_SMALL, chargeNormalLootTable()); //小水下遗迹
        p_250931_.accept(BuiltInLootTables.VILLAGE_WEAPONSMITH, chargeSwordLootTable()); //村庄武器铁匠
        p_250931_.accept(BuiltInLootTables.VILLAGE_TOOLSMITH, chargeSwordLootTable()); //村庄工具铁匠
        p_250931_.accept(BuiltInLootTables.VILLAGE_CARTOGRAPHER, chargeNormalLootTable()); //村庄制图师
//        p_250931_.accept(BuiltInLootTables.VILLAGE_MASON, chargeNormalLootTable()); //村庄石匠
        p_250931_.accept(BuiltInLootTables.VILLAGE_ARMORER, chargeSwordLootTable()); //村庄盔甲匠
        p_250931_.accept(BuiltInLootTables.VILLAGE_SHEPHERD, chargeNormalLootTable()); //村庄牧羊人
//        p_250931_.accept(BuiltInLootTables.VILLAGE_BUTCHER, chargeNormalLootTable()); //村庄屠夫
        p_250931_.accept(BuiltInLootTables.VILLAGE_FLETCHER, chargeNormalLootTable()); //村庄制箭师（弗莱彻）
        p_250931_.accept(BuiltInLootTables.VILLAGE_FISHER, chargeNormalLootTable()); //村庄钓鱼老
        p_250931_.accept(BuiltInLootTables.VILLAGE_TANNERY, chargeNormalLootTable()); //村庄皮革厂
        p_250931_.accept(BuiltInLootTables.VILLAGE_TEMPLE, chargeNormalLootTable()); //村庄庙宇
//        p_250931_.accept(BuiltInLootTables.VILLAGE_PLAINS_HOUSE, chargeNormalLootTable()); //村庄平原房
//        p_250931_.accept(BuiltInLootTables.VILLAGE_TAIGA_HOUSE, chargeNormalLootTable()); //村庄针叶林房
//        p_250931_.accept(BuiltInLootTables.VILLAGE_SAVANNA_HOUSE, chargeNormalLootTable()); //村庄稀树草原房
//        p_250931_.accept(BuiltInLootTables.VILLAGE_SNOWY_HOUSE, chargeNormalLootTable()); //村庄雪屋
//        p_250931_.accept(BuiltInLootTables.VILLAGE_DESERT_HOUSE, chargeNormalLootTable()); //村庄沙漠房
        p_250931_.accept(BuiltInLootTables.WOODLAND_MANSION, chargeHighLevelLootTable()); //森林宅邸
    }

    public static LootTable.Builder chargeNormalLootTable() {
        return LootTable.lootTable().withPool(LootPool.lootPool()
                .setRolls(UniformGenerator.between(2.0F, 3.0F))
                .add(LootItem.lootTableItem(ChargeModItemRegistry.chargeLingShi.get()).setWeight(4).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 6.0F))))
                .add(LootItem.lootTableItem(ChargeModItemRegistry.TALISMAN_PAPER_ITEM.get()).setWeight(4).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                .add(LootItem.lootTableItem(ChargeModItemRegistry.CHARGE_ARRAY_DISK_BLOCK_ITEM.get()).setWeight(4))
                //普通的
                .add(LootItem.lootTableItem(ChargeModItemRegistry.PUSH_BACK_TALISMAN.get()).setWeight(1).setQuality(1).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                .add(LootItem.lootTableItem(ChargeModItemRegistry.SHIELD_TALISMAN.get()).setWeight(1).setQuality(1).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                .add(LootItem.lootTableItem(ChargeModItemRegistry.CHARGE_BIGU_PELLET.get()).setWeight(1).setQuality(1))
                .add(LootItem.lootTableItem(ChargeModItemRegistry.CHARGE_HONG_YUN_PELLET.get()).setWeight(1).setQuality(1))

                .add(EmptyLootItem.emptyItem().setWeight(20))
                .add(LootItem.lootTableItem(Items.COAST_ARMOR_TRIM_SMITHING_TEMPLATE).setWeight(1).apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F)))));
    }

    public static LootTable.Builder chargeSwordLootTable() {
        return LootTable.lootTable().withPool(LootPool.lootPool()
                .setRolls(UniformGenerator.between(2.0F, 3.0F))
                .add(LootItem.lootTableItem(ChargeModItemRegistry.chargeLingShi.get()).setWeight(4).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 6.0F))))
                .add(LootItem.lootTableItem(ChargeModItemRegistry.TALISMAN_PAPER_ITEM.get()).setWeight(4).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                .add(LootItem.lootTableItem(ChargeModItemRegistry.CHARGE_ARRAY_DISK_BLOCK_ITEM.get()).setWeight(4))
                //武器的
                .add(LootItem.lootTableItem(ChargeModItemRegistry.KONG_FANG_SWORD.get()).setWeight(1).setQuality(1))
                .add(LootItem.lootTableItem(ChargeModItemRegistry.FU_YAO_SWORD.get()).setWeight(1).setQuality(1))
                .add(LootItem.lootTableItem(ChargeModItemRegistry.WATER_SPLIT_SWORD.get()).setWeight(1).setQuality(1))
                .add(LootItem.lootTableItem(ChargeModItemRegistry.EMBER_SWORD.get()).setWeight(1).setQuality(1))
                .add(LootItem.lootTableItem(ChargeModItemRegistry.ZHONG_ZHONG_GU_SWORD.get()).setWeight(1).setQuality(1))

                .add(EmptyLootItem.emptyItem().setWeight(25))
                .add(LootItem.lootTableItem(Items.COAST_ARMOR_TRIM_SMITHING_TEMPLATE).setWeight(1).apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F)))));
    }

    public static LootTable.Builder chargeHighLevelLootTable() {
        return LootTable.lootTable().withPool(LootPool.lootPool()
                .setRolls(UniformGenerator.between(2.0F, 3.0F))
                .add(LootItem.lootTableItem(ChargeModItemRegistry.chargeLingShi.get()).setWeight(4).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 6.0F))))
                //高级的
                .add(LootItem.lootTableItem(ChargeModItemRegistry.CHARGE_HEALTH_PELLET.get()).setWeight(1).setQuality(2) )
                .add(LootItem.lootTableItem(ChargeModItemRegistry.CHARGE_POWER_PELLET.get()).setWeight(1).setQuality(2))
                .add(LootItem.lootTableItem(ChargeModItemRegistry.CHARGE_WAN_WU_SHENG_PELLET.get()).setWeight(1).setQuality(2))
                .add(LootItem.lootTableItem(ChargeModItemRegistry.CHARGE_SAN_SHI_PELLET.get()).setWeight(1).setQuality(2))
                .add(LootItem.lootTableItem(ChargeModItemRegistry.CHARGE_JU_LING_PELLET.get()).setWeight(1).setQuality(2))
                .add(LootItem.lootTableItem(ChargeModItemRegistry.FLY_TALISMAN.get()).setWeight(1).setQuality(2))
                .add(LootItem.lootTableItem(ChargeModItemRegistry.RAIN_TALISMAN.get()).setWeight(1).setQuality(2))
                .add(LootItem.lootTableItem(ChargeModItemRegistry.HOLD_LIFE_TALISMAN.get()).setWeight(1).setQuality(2))
                .add(EmptyLootItem.emptyItem().setWeight(25))
                .add(LootItem.lootTableItem(Items.COAST_ARMOR_TRIM_SMITHING_TEMPLATE).setWeight(1).apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F)))));
    }

//    public void generate2(BiConsumer<ResourceLocation, LootTable.Builder> p_249559_) {
//        p_249559_.accept(BuiltInLootTables.FISHING, LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
//                .add(LootTableReference.lootTableReference(BuiltInLootTables.FISHING_JUNK).setWeight(10).setQuality(-2))
//                .add(LootTableReference.lootTableReference(BuiltInLootTables.FISHING_TREASURE).setWeight(5).setQuality(2).when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().subPredicate(FishingHookPredicate.inOpenWater(true)))))
//                .add(LootTableReference.lootTableReference(BuiltInLootTables.FISHING_FISH).setWeight(85).setQuality(-1))));
//        p_249559_.accept(BuiltInLootTables.FISHING_FISH, fishingFishLootTable());
//        p_249559_.accept(BuiltInLootTables.FISHING_JUNK, LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(Blocks.LILY_PAD).setWeight(17)).add(LootItem.lootTableItem(Items.LEATHER_BOOTS).setWeight(10).apply(SetItemDamageFunction.setDamage(UniformGenerator.between(0.0F, 0.9F)))).add(LootItem.lootTableItem(Items.LEATHER).setWeight(10)).add(LootItem.lootTableItem(Items.BONE).setWeight(10)).add(LootItem.lootTableItem(Items.POTION).setWeight(10).apply(SetPotionFunction.setPotion(Potions.WATER))).add(LootItem.lootTableItem(Items.STRING).setWeight(5)).add(LootItem.lootTableItem(Items.FISHING_ROD).setWeight(2).apply(SetItemDamageFunction.setDamage(UniformGenerator.between(0.0F, 0.9F)))).add(LootItem.lootTableItem(Items.BOWL).setWeight(10)).add(LootItem.lootTableItem(Items.STICK).setWeight(5)).add(LootItem.lootTableItem(Items.INK_SAC).setWeight(1).apply(SetItemCountFunction.setCount(ConstantValue.exactly(10.0F)))).add(LootItem.lootTableItem(Blocks.TRIPWIRE_HOOK).setWeight(10)).add(LootItem.lootTableItem(Items.ROTTEN_FLESH).setWeight(10)).add(LootItem.lootTableItem(Blocks.BAMBOO).when(IN_JUNGLE.or(IN_SPARSE_JUNGLE).or(IN_BAMBOO_JUNGLE)).setWeight(10))));
//        p_249559_.accept(BuiltInLootTables.FISHING_TREASURE, LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(Items.NAME_TAG)).add(LootItem.lootTableItem(Items.SADDLE)).add(LootItem.lootTableItem(Items.BOW).apply(SetItemDamageFunction.setDamage(UniformGenerator.between(0.0F, 0.25F))).apply(EnchantWithLevelsFunction.enchantWithLevels(ConstantValue.exactly(30.0F)).allowTreasure())).add(LootItem.lootTableItem(Items.FISHING_ROD).apply(SetItemDamageFunction.setDamage(UniformGenerator.between(0.0F, 0.25F))).apply(EnchantWithLevelsFunction.enchantWithLevels(ConstantValue.exactly(30.0F)).allowTreasure())).add(LootItem.lootTableItem(Items.BOOK).apply(EnchantWithLevelsFunction.enchantWithLevels(ConstantValue.exactly(30.0F)).allowTreasure())).add(LootItem.lootTableItem(Items.NAUTILUS_SHELL))));
//    }
}