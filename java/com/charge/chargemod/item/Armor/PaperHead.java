package com.charge.chargemod.item.Armor;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;

import java.util.Collection;

public class PaperHead extends ArmorItem {

    //用来存放从玩家身上获取到附魔效果
    private Collection<MobEffectInstance> activeEffects;

    public PaperHead(ArmorMaterial pMaterial, Type pType, Properties pProperties) {
        super(pMaterial, pType, pProperties);
        activeEffects=null;
    }

    //每个tick都会执行
//    @Override
//    public void onArmorTick(ItemStack stack, Level level, Player player) {
//        this.activeEffects=player.getActiveEffects();
//        //服务端执行逻辑,客户端会被直接拒绝执行移除效果代码
//        if(!level.isClientSide()){
//            Inventory inventory = player.getInventory();//获取玩家的库存(即背包状态)
//            if(isAllEquiped(inventory) && isCorrectEquipment(inventory)){
//                //如果玩家身上的效果消失了就再加一次，实现全套一直都有能力的效果
//                if(isEffect(player)){
//                    //给玩家新增一个能力，需要创建一个能力的实例，第一个参数是能力的类型，第二个是持续时间(tick)，第三个就是能力等级(从0开始)
//                    player.addEffect(new MobEffectInstance(MobEffects.JUMP,400,2));
//                    player.addEffect(new MobEffectInstance(MobEffects.REGENERATION,400,2));
//                    player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION,400,2));
//                }
//            }
//        }
//    }
//    //判断是不是穿上了四件衣服
//    private boolean isAllEquiped(Inventory inventory){
//        //获得玩家装备槽的所有装备
//        ItemStack armor = inventory.getArmor(0);
//        ItemStack armor1 = inventory.getArmor(1);
//        ItemStack armor2 = inventory.getArmor(2);
//        ItemStack armor3= inventory.getArmor(3);
//        //判断是不是穿了全套
//        return !armor.isEmpty() &&
//                !armor1.isEmpty()&&
//                !armor2.isEmpty()&&
//                !armor3.isEmpty();
//    }
//    //判断穿的一身衣服是不是我们定义的完整一套
//    private boolean isCorrectEquipment(Inventory inventory){
//        ArmorItem armor = (ArmorItem) inventory.getArmor(0).getItem();
//        ArmorItem armor1 = (ArmorItem)inventory.getArmor(1).getItem();
//        ArmorItem armor2 = (ArmorItem)inventory.getArmor(2).getItem();
//        ArmorItem armor3= (ArmorItem)inventory.getArmor(3).getItem();
//        //判断穿的一套是不是我们自己新加的装备
//        return armor.getMaterial()== ModArmorMaterials.RED&&
//                armor1.getMaterial()== ModArmorMaterials.RED&&
//                armor2.getMaterial()== ModArmorMaterials.RED&&
//                armor3.getMaterial()== ModArmorMaterials.RED;
//    }
//    //判断玩家身上有没有效果
//    private boolean isEffect(Player player){
//        Map<MobEffect, MobEffectInstance> activeEffectsMap = player.getActiveEffectsMap();
//        return activeEffectsMap.isEmpty();
//    }
}