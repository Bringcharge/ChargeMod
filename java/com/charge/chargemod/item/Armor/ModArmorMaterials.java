package com.charge.chargemod.item.Armor;

import net.minecraft.Util;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.EnumMap;
import java.util.function.Supplier;

public enum  ModArmorMaterials implements StringRepresentable, ArmorMaterial {
    //创建一个我们自己的装备材质
    PAPER ("paper", 40,Util.make(new EnumMap<>(ArmorItem.Type.class), (pConsumer) -> {
        pConsumer.put(ArmorItem.Type.BOOTS, 1);
        pConsumer.put(ArmorItem.Type.LEGGINGS, 1);
        pConsumer.put(ArmorItem.Type.CHESTPLATE, 1);
        pConsumer.put(ArmorItem.Type.HELMET,1);
    }), 15, SoundEvents.ARMOR_EQUIP_LEATHER, 1.0F, 0.0F,
            () -> Ingredient.of(Items.PAPER)
    );

    private static final EnumMap<ArmorItem.Type, Integer> HEALTH_FUNCTION_FOR_TYPE = Util.make(new EnumMap<>(ArmorItem.Type.class), (p_266653_) -> {
        p_266653_.put(ArmorItem.Type.BOOTS, 1);
        p_266653_.put(ArmorItem.Type.LEGGINGS, 1);
        p_266653_.put(ArmorItem.Type.CHESTPLATE, 1);
        p_266653_.put(ArmorItem.Type.HELMET, 1);
    });
    private final String name;
    private final int durabilityMultiplier;
    private final EnumMap<ArmorItem.Type, Integer> protectionFunctionForType;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
    private final LazyLoadedValue<Ingredient> repairIngredient;

    ModArmorMaterials(String pName, int pDurabilityMultiplier, EnumMap<ArmorItem.Type, Integer> pProtectionFunctionForType, int pEnchantmentValue, SoundEvent pSound, float pToughness, float pKnockbackResistance, Supplier<Ingredient> pRepairIngredient) {
        this.name = pName;
        this.durabilityMultiplier = pDurabilityMultiplier;//耐久乘数
        this.protectionFunctionForType = pProtectionFunctionForType;// 护甲值
        this.enchantmentValue = pEnchantmentValue;//附魔时可获得稀有附魔的能力
        this.sound = pSound;//穿戴时声音
        this.toughness = pToughness;//韧性
        this.knockbackResistance = pKnockbackResistance;//击退抗性
        this.repairIngredient = new LazyLoadedValue<>(pRepairIngredient);//修复所用材料
    }

    public int getDurabilityForType(ArmorItem.Type pType) {
        return HEALTH_FUNCTION_FOR_TYPE.get(pType) * this.durabilityMultiplier;
    }

    public int getDefenseForType(ArmorItem.Type pType) {
        return this.protectionFunctionForType.get(pType);
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    public SoundEvent getEquipSound() {
        return this.sound;
    }

    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    public String getName() {
        return this.name;
    }

    public float getToughness() {
        return this.toughness;
    }

    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }

    public String getSerializedName() {
        return this.name;
    }
}
