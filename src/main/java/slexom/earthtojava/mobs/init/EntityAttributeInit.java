package slexom.earthtojava.mobs.init;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.SquidEntity;
import slexom.earthtojava.mobs.entity.base.*;
import slexom.earthtojava.mobs.entity.merchant.villager.E2JWanderingTraderEntity;
import slexom.earthtojava.mobs.entity.monster.BoneSpiderEntity;
import slexom.earthtojava.mobs.entity.monster.SkeletonWolfEntity;
import slexom.earthtojava.mobs.entity.passive.HornedSheepEntity;
import slexom.earthtojava.mobs.entity.passive.MelonGolemEntity;

public class EntityAttributeInit {

    public static void init() {

        registerBaseChickenAttributes(EntityTypesInit.AMBER_CHICKEN_REGISTRY_OBJECT);
        registerBaseChickenAttributes(EntityTypesInit.MIDNIGHT_CHICKEN_REGISTRY_OBJECT);
        registerBaseChickenAttributes(EntityTypesInit.STORMY_CHICKEN_REGISTRY_OBJECT);
        registerBaseChickenAttributes(EntityTypesInit.CLUCKSHROOM_REGISTRY_OBJECT);
        registerBaseChickenAttributes(EntityTypesInit.BRONZED_CHICKEN_REGISTRY_OBJECT);


        registerBaseCowAttributes(EntityTypesInit.ALBINO_COW_REGISTRY_OBJECT);
        registerBaseCowAttributes(EntityTypesInit.ASHEN_COW_REGISTRY_OBJECT);
        registerBaseCowAttributes(EntityTypesInit.SUNSET_COW_REGISTRY_OBJECT);
        registerBaseCowAttributes(EntityTypesInit.WOOLY_COW_REGISTRY_OBJECT);
        registerBaseCowAttributes(EntityTypesInit.MOOBLOOM_REGISTRY_OBJECT);


        registerBasePigAttributes(EntityTypesInit.PIEBALD_PIG_REGISTRY_OBJECT);
        registerBasePigAttributes(EntityTypesInit.PALE_PIG_REGISTRY_OBJECT);
        registerBasePigAttributes(EntityTypesInit.MUDDY_PIG_REGISTRY_OBJECT);
        registerBasePigAttributes(EntityTypesInit.SPOTTED_PIG_REGISTRY_OBJECT);
        registerBasePigAttributes(EntityTypesInit.PINK_FOOTED_PIG_REGISTRY_OBJECT);

        registerBaseRabbitAttributes(EntityTypesInit.JUMBO_RABBIT_REGISTRY_OBJECT);
        registerBaseRabbitAttributes(EntityTypesInit.HARELEQUIN_RABBIT_REGISTRY_OBJECT);
        registerBaseRabbitAttributes(EntityTypesInit.MUDDY_FOOT_RABBIT_REGISTRY_OBJECT);
        registerBaseRabbitAttributes(EntityTypesInit.VESTED_RABBIT_REGISTRY_OBJECT);

        registerBaseOneColorSheepAttributes(EntityTypesInit.INKY_SHEEP_REGISTRY_OBJECT);
        registerBaseOneColorSheepAttributes(EntityTypesInit.FLECKED_SHEEP_REGISTRY_OBJECT);
        registerBaseOneColorSheepAttributes(EntityTypesInit.ROCKY_SHEEP_REGISTRY_OBJECT);
        registerBaseOneColorSheepAttributes(EntityTypesInit.RAINBOW_SHEEP_REGISTRY_OBJECT);
        RegisterHelper.registerEntityAttributes(EntityTypesInit.HORNED_SHEEP_REGISTRY_OBJECT, HornedSheepEntity.createHornedSheepAttributes());

        RegisterHelper.registerEntityAttributes(EntityTypesInit.FURNACE_GOLEM_REGISTRY_OBJECT, IronGolemEntity.createIronGolemAttributes());
        RegisterHelper.registerEntityAttributes(EntityTypesInit.MELON_GOLEM_REGISTRY_OBJECT, MelonGolemEntity.createMelonGolemAttributes());
        RegisterHelper.registerEntityAttributes(EntityTypesInit.TROPICAL_SLIME_REGISTRY_OBJECT, HostileEntity.createHostileAttributes());
        RegisterHelper.registerEntityAttributes(EntityTypesInit.GLOW_SQUID_REGISTRY_OBJECT, SquidEntity.createSquidAttributes());

        RegisterHelper.registerEntityAttributes(EntityTypesInit.BONE_SPIDER_REGISTRY_OBJECT, BoneSpiderEntity.createBoneSpiderAttributes());

        RegisterHelper.registerEntityAttributes(EntityTypesInit.SKELETON_WOLF_REGISTRY_OBJECT, SkeletonWolfEntity.createSkeletonWolfAttributes());

        RegisterHelper.registerEntityAttributes(EntityTypesInit.JOLLY_LLAMA_REGISTRY_OBJECT, E2JBaseLlamaEntity.createJollyLlamaAttributes());

        RegisterHelper.registerEntityAttributes(EntityTypesInit.WANDERING_TRADER_REGISTRY_OBJECT, E2JWanderingTraderEntity.createWanderingTraderAttributes());
    }

    private static void registerBaseChickenAttributes(EntityType<? extends LivingEntity> entityType) {
        RegisterHelper.registerEntityAttributes(entityType, E2JBaseChickenEntity.createChickenAttributes());
    }

    private static void registerBaseCowAttributes(EntityType<? extends LivingEntity> entityType) {
        RegisterHelper.registerEntityAttributes(entityType, E2JBaseCowEntity.createCowAttributes());
    }

    private static void registerBasePigAttributes(EntityType<? extends LivingEntity> entityType) {
        RegisterHelper.registerEntityAttributes(entityType, E2JBasePigEntity.createPigAttributes());
    }

    private static void registerBaseOneColorSheepAttributes(EntityType<? extends LivingEntity> entityType) {
        RegisterHelper.registerEntityAttributes(entityType, E2JOneColorSheepEntity.createOneColorSheepAttributes());
    }

    private static void registerBaseRabbitAttributes(EntityType<? extends LivingEntity> entityType) {
        RegisterHelper.registerEntityAttributes(entityType, E2JBaseRabbitEntity.createRabbitAttributes());
    }

}
