package com.charge.chargemod.runData.oreFeature;

import com.charge.chargemod.ChargeModItemRegistry;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;


//生物群系设置
public class ChargeBiomeModifiers {
    // 注册的key
    public static final ResourceKey<BiomeModifier> ADD_LING_SHI_ORE = registerKey("ling_shi_ore.json");
    // BootstapContext 数据生成的上下文
    public static void bootstrap(BootstapContext<BiomeModifier> context) {
        // 通过上下文获得PLACED_FEATURE的注册HolderGetter
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        // 通过上下文获得BIOME的HolderGetter
        var biomes = context.lookup(Registries.BIOME);
        // 生成json文件，第一个参数是key，第二个参数是BiomeModifiers，
        // 我们使用了子类AddFeaturesBiomeModifier，是指添加feature给biome
        // 第一个参数是holderset的biome ，这里是否是主世界的生物群系。即返回了主世界的生物群系
        // 第二个holdlerSet是指所有的feature，我们通过placedFeatures获得
        // 丢三个参数要求给出在世界生成的什么阶段加你的feature，我们这里是地下矿物生成的时候，你可以到该类下面看看，这是个枚举，
        context.register(ADD_LING_SHI_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ChargeOrePlacements.LING_SHI_ORE)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(ChargeModItemRegistry.MODID, name));
    }
}
