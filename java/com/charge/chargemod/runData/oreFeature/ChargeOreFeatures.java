package com.charge.chargemod.runData.oreFeature;

import com.charge.chargemod.ChargeModItemRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

//矿物生成覆盖逻辑
public class ChargeOreFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> LING_SHI = createKey("ling_shi_ore.json");

    //BootstapContext 是我们datagen的上下文，等会我们使用数据生成的时候说。
    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> pContext) {
        //  创建对应的tag，如果有多个就创建多个
        RuleTest stoneOreReplaceRuleTest = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepSlateOreReplaceRuleTest = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        // 创建一个替换方案list
        List<OreConfiguration.TargetBlockState> list = List.of(
                OreConfiguration.target(stoneOreReplaceRuleTest, ChargeModItemRegistry.CHARGE_LING_SHI_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepSlateOreReplaceRuleTest, ChargeModItemRegistry.CHARGE_LING_SHI_ORE.get().defaultBlockState())
        );
        // 注册对应orefeature（矿物生成逻辑），使用listOreConfiguration，9 上文提到的size
        FeatureUtils.register(pContext, LING_SHI, Feature.ORE, new OreConfiguration(list, 9));

    }
    // 创建ResourceKey的方法
    public static ResourceKey<ConfiguredFeature<?, ?>> createKey(String pName) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(ChargeModItemRegistry.MODID,pName));
    }
}
