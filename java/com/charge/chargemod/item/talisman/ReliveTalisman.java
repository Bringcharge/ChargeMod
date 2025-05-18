package com.charge.chargemod.item.talisman;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.FlyingMob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

import static net.minecraft.world.InteractionHand.MAIN_HAND;
import static net.minecraft.world.InteractionHand.OFF_HAND;

//活死人符
public class ReliveTalisman extends ChargeBaseTalisman {
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {

        if (!level.isClientSide() && hand == MAIN_HAND) {
            ItemStack stack = player.getItemInHand(OFF_HAND);   //获取副手物品
            boolean costFlag = false;
            if (stack.is(Items.BEEF)) {//牛
                creatEntity(level, new Cow(EntityType.COW, level), player);
                costFlag = true;
            }
            if (stack.is(Items.PORKCHOP)) {//猪
                creatEntity(level, new Pig(EntityType.PIG, level), player);
                costFlag = true;
            }
            if (stack.is(Items.CHICKEN)) {//鸡
                creatEntity(level, new Chicken(EntityType.CHICKEN, level), player);
                costFlag = true;
            }
            if (stack.is(Items.MUTTON)) {//羊
                creatEntity(level, new Sheep(EntityType.SHEEP, level), player);
                costFlag = true;
            }
            if (stack.is(Items.RABBIT)) {//兔子
                creatEntity(level, new Rabbit(EntityType.RABBIT, level), player);
                costFlag = true;
            }
            if (stack.is(Items.PUFFERFISH)) {//河豚
                creatEntity(level, new Pufferfish(EntityType.PUFFERFISH, level), player);
                costFlag = true;
            }
            if (stack.is(Items.SALMON)) {//鲑鱼
                creatEntity(level, new Salmon(EntityType.SALMON, level), player);
                costFlag = true;
            }
            if (stack.is(Items.COD)) {//鳕鱼
                creatEntity(level, new Cod(EntityType.COD, level), player);
                costFlag = true;
            }
            if (stack.is(Items.COD)) {//鳕鱼
                creatEntity(level, new Cod(EntityType.COD, level), player);
                costFlag = true;
            }
            if (stack.is(Items.TROPICAL_FISH)) { //蜜蜂
                creatEntity(level, new TropicalFish(EntityType.TROPICAL_FISH, level), player);
                costFlag = true;
            }
            if (costFlag) {
                stack.shrink(1);//消耗一个物品
                if (stack.isEmpty()) {
                    player.getInventory().removeItem(stack);
                }
                level.playSound(
                        null,                     // 无特定来源实体（全局声音）
                        BlockPos.containing(player.position()), // 声音位置
                        SoundEvents.EVOKER_PREPARE_SUMMON, // 声音事件（原版或自定义）
                        SoundSource.PLAYERS,       // 声音类别（BLOCKS, PLAYERS, AMBIENT 等）
                        1.0F, 1.0F                // 音量、音高
                );
            }


        }
        return super.use(level,player,hand);
    }

    public void creatEntity(Level level, Entity entity, Player player) {
        if (!level.isClientSide()) {
            entity.setPos(player.getPosition(1.0f));
            level.addFreshEntity(entity);

        }
    }
}
