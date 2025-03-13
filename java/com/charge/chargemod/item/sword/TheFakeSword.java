package com.charge.chargemod.item.sword;

import com.charge.chargemod.ChargeModItemRegistry;
import com.charge.chargemod.entity.FakeVillager;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.village.ReputationEventType;
import net.minecraft.world.entity.monster.ZombieVillager;
import net.minecraft.world.entity.npc.VillagerData;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class TheFakeSword extends ChargeBaseSword{
    @Override
    public boolean skillWithEntity(LivingEntity entity, Player user, InteractionHand hand) { //右键击中了怪物的函数，最高优先级
        Vec3 place = user.getPosition(1.0f);
        Vec3i position = new Vec3i((int)Math.floor(place.x),(int)Math.floor(place.y),(int)Math.floor(place.z));
        Level level = user.level();
        if (entity != null) {
            if (!(entity instanceof Player)) {  //不是玩家的时候，爆掉它武器
                entity.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
                entity.setItemSlot(EquipmentSlot.OFFHAND, ItemStack.EMPTY);
                if (entity instanceof ZombieVillager && level instanceof ServerLevel) {
                    conversionZombieVillager((ServerLevel)level, (ZombieVillager)entity, user);
                }
            }
        }
        return false;
    }


    private void conversionZombieVillager(ServerLevel p_34399_, ZombieVillager zombieVillager, Player player) {
        FakeVillager villager = zombieVillager.convertTo(ChargeModItemRegistry.FAKE_VILLAGER.get(), false);
        RandomSource random = RandomSource.create();
        if (villager != null) {

            BuiltInRegistries.VILLAGER_PROFESSION.getRandom(random).ifPresent((p_255550_) -> {
                villager.setVillagerData(zombieVillager.getVillagerData().setProfession(p_255550_.value()));
            });

            //拿不到原始数据，就不设置gossips了
            //tradeOffers也不设置了，反正是假货

            villager.setVillagerXp(1);
            villager.finalizeSpawn(p_34399_, p_34399_.getCurrentDifficultyAt(villager.blockPosition()), MobSpawnType.CONVERSION, (SpawnGroupData) null, (CompoundTag) null);

            villager.refreshBrain(p_34399_);

            if (player instanceof ServerPlayer) {
                CriteriaTriggers.CURED_ZOMBIE_VILLAGER.trigger((ServerPlayer) player, zombieVillager, villager);
                p_34399_.onReputationEvent(ReputationEventType.ZOMBIE_VILLAGER_CURED, player, villager);

            }

            villager.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 200, 0));
            if (!zombieVillager.isSilent()) {
                p_34399_.levelEvent((Player) null, 1027, zombieVillager.blockPosition(), 0);
            }
            net.minecraftforge.event.ForgeEventFactory.onLivingConvert(zombieVillager, villager);
        }
    }
}
