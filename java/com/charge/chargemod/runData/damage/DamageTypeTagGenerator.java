package com.charge.chargemod.runData.damage;


import com.charge.chargemod.ChargeModItemRegistry;
import com.charge.chargemod.damage.ChargeDamageTypes;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class DamageTypeTagGenerator extends TagsProvider<DamageType> {
    public DamageTypeTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
        super(output, Registries.DAMAGE_TYPE, lookupProvider, ChargeModItemRegistry.MODID, existingFileHelper);
    }

    private static TagKey<DamageType> create(String name) {
        return TagKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(ChargeModItemRegistry.MODID, name));
    }
    public static final TagKey<DamageType> DAO_REAL = create("dao_real");
    public static final TagKey<DamageType> DAO_HEAVY = create("dao_heavy");
//    public static final TagKey<DamageType> BYPASS_EVASION = create("bypass_evasion");
//    public static final TagKey<DamageType> LONG_CAST_IGNORE = create("long_cast_ignore");
//    public static final TagKey<DamageType> FIRE_MAGIC = create("fire_magic");
//    public static final TagKey<DamageType> ICE_MAGIC = create("ice_magic");
//    public static final TagKey<DamageType> LIGHTNING_MAGIC = create("lightning_magic");
//    public static final TagKey<DamageType> HOLY_MAGIC = create("holy_magic");
//    public static final TagKey<DamageType> ENDER_MAGIC = create("ender_magic");
//    public static final TagKey<DamageType> BLOOD_MAGIC = create("blood_magic");
//    public static final TagKey<DamageType> EVOCATION_MAGIC = create("evocation_magic");
//    public static final TagKey<DamageType> ELDRITCH_MAGIC = create("eldritch_magic");
//    public static final TagKey<DamageType> NATURE_MAGIC = create("nature_magic");

    protected void addTags(@NotNull HolderLookup.Provider provider) {
        tag(DamageTypeTags.BYPASSES_COOLDOWN).add(ChargeDamageTypes.DAO_REAL);
        tag(DAO_REAL).add(ChargeDamageTypes.DAO_REAL);
        tag(DAO_HEAVY).add(ChargeDamageTypes.DAO_HEAVY);

//        tag(FIRE_MAGIC).add(ISSDamageTypes.FIRE_MAGIC);
//        tag(ICE_MAGIC).add(ISSDamageTypes.ICE_MAGIC);
//        tag(LIGHTNING_MAGIC).add(ISSDamageTypes.LIGHTNING_MAGIC);
//        tag(HOLY_MAGIC).add(ISSDamageTypes.HOLY_MAGIC);
//        tag(ENDER_MAGIC).add(ISSDamageTypes.ENDER_MAGIC);
//        tag(BLOOD_MAGIC).add(ISSDamageTypes.BLOOD_MAGIC);
//        tag(EVOCATION_MAGIC).add(ISSDamageTypes.EVOCATION_MAGIC);
//        tag(ELDRITCH_MAGIC).add(ISSDamageTypes.ELDRITCH_MAGIC);
//        tag(NATURE_MAGIC).add(ISSDamageTypes.NATURE_MAGIC);

//        tag(BYPASS_EVASION).add(
//                DamageTypes.ON_FIRE,
//                DamageTypes.WITHER,
//                DamageTypes.FREEZE,
//                DamageTypes.STARVE,
//                DamageTypes.DROWN,
//                DamageTypes.STALAGMITE,
//                DamageTypes.OUTSIDE_BORDER,
//                DamageTypes.FELL_OUT_OF_WORLD,
//                DamageTypes.DRY_OUT,
//                DamageTypes.IN_WALL,
//                ISSDamageTypes.CAULDRON
//        );
//        tag(BYPASS_EVASION).addOptional(
//                new ResourceLocation("thrown")
//        );
//
//        tag(LONG_CAST_IGNORE).add(
//                DamageTypes.FREEZE,
//                DamageTypes.STARVE,
//                DamageTypes.ON_FIRE,
//                DamageTypes.WITHER
//        );
//        tag(LONG_CAST_IGNORE).addOptional(
//                new ResourceLocation("thrown")
//        );
    }

}