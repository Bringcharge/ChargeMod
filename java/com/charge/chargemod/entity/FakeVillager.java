package com.charge.chargemod.entity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.monster.ZombieVillager;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.Random;

public class FakeVillager extends Villager {

    public int changeTick;

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0);
    }

    public static List<VillagerProfession> FakeVillagerProfession = List.of(
     VillagerProfession.NONE,
     VillagerProfession.ARMORER,
     VillagerProfession.BUTCHER,
     VillagerProfession.CARTOGRAPHER,
     VillagerProfession.CLERIC,
     VillagerProfession.FARMER,
     VillagerProfession.FISHERMAN,
     VillagerProfession.FLETCHER,
     VillagerProfession.LEATHERWORKER,
     VillagerProfession.LIBRARIAN,
     VillagerProfession.MASON,
     VillagerProfession.NITWIT,
     VillagerProfession.SHEPHERD,
     VillagerProfession.TOOLSMITH,
     VillagerProfession.WEAPONSMITH
    );

    static public void changeToZombie(Level level, FakeVillager villager) {
        ZombieVillager zombievillager = villager.convertTo(EntityType.ZOMBIE_VILLAGER, false);
        if (zombievillager != null) {
            zombievillager.finalizeSpawn((ServerLevel)level, level.getCurrentDifficultyAt(zombievillager.blockPosition()), MobSpawnType.CONVERSION, new Zombie.ZombieGroupData(false, true), (CompoundTag) null);
            zombievillager.setVillagerData(villager.getVillagerData());
            zombievillager.setGossips(villager.getGossips().store(NbtOps.INSTANCE));
            zombievillager.setTradeOffers(villager.getOffers().createTag());
            zombievillager.setVillagerXp(villager.getVillagerXp());
            net.minecraftforge.event.ForgeEventFactory.onLivingConvert(villager, zombievillager);
//            if (!this.isSilent()) {
//                level.levelEvent((Player) null, 1026, this.blockPosition(), 0);
//            }
        }
    }

    public FakeVillager(EntityType<? extends Villager> p_35381_, Level p_35382_) {
        super(p_35381_, p_35382_);
        Random randomIndex = new Random();
//        int index = randomIndex.nextInt(14);
//        this.setVillagerData(this.getVillagerData().setProfession(FakeVillagerProfession.get(index)));
        changeTick = new Random().nextInt() % (20 * 60 * 4);
//        changeTick =  (20 * 6);
    }

    public void tick() {
        if (!this.level().isClientSide && this.isAlive() && !this.isNoAi()) {
            if (changeTick > 0) {
                changeTick--;
                if (changeTick == 0) {
                    changeToZombie(this.level(), this);
                }
            }
        }
        super.tick();

    }



    @Override
    public void addAdditionalSaveData(CompoundTag p_34319_) {
        super.addAdditionalSaveData(p_34319_);
        p_34319_.putInt("changeTick", this.changeTick);
    }
    @Override
    public void readAdditionalSaveData(CompoundTag p_34305_) {
        super.readAdditionalSaveData(p_34305_);
        this.changeTick = p_34305_.getInt("changeTick");
    }

}
