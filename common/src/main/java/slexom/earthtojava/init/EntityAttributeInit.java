package slexom.earthtojava.init;

import dev.architectury.registry.level.entity.EntityAttributeRegistry;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.WitchEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import slexom.earthtojava.entity.base.*;
import slexom.earthtojava.entity.monster.BoneSpiderEntity;
import slexom.earthtojava.entity.monster.SkeletonWolfEntity;
import slexom.earthtojava.entity.passive.HornedSheepEntity;
import slexom.earthtojava.entity.passive.MelonGolemEntity;

public final class EntityAttributeInit {

    public static void init() {

        registerBaseChickenAttributes(EntityTypesInit.AMBER_CHICKEN_REGISTRY_OBJECT);
        registerBaseChickenAttributes(EntityTypesInit.BRONZED_CHICKEN_REGISTRY_OBJECT);
        registerBaseChickenAttributes(EntityTypesInit.GOLD_CRESTED_CHICKEN_REGISTRY_OBJECT);
        registerBaseChickenAttributes(EntityTypesInit.MIDNIGHT_CHICKEN_REGISTRY_OBJECT);
        registerBaseChickenAttributes(EntityTypesInit.SKEWBALD_CHICKEN_REGISTRY_OBJECT);
        registerBaseChickenAttributes(EntityTypesInit.STORMY_CHICKEN_REGISTRY_OBJECT);

        registerBaseCowAttributes(EntityTypesInit.ALBINO_COW_REGISTRY_OBJECT);
        registerBaseCowAttributes(EntityTypesInit.ASHEN_COW_REGISTRY_OBJECT);
        registerBaseCowAttributes(EntityTypesInit.COOKIE_COW_REGISTRY_OBJECT);
        registerBaseCowAttributes(EntityTypesInit.CREAM_COW_REGISTRY_OBJECT);
        registerBaseCowAttributes(EntityTypesInit.DAIRY_COW_REGISTRY_OBJECT);
        registerBaseCowAttributes(EntityTypesInit.PINTO_COW_REGISTRY_OBJECT);
        registerBaseCowAttributes(EntityTypesInit.SUNSET_COW_REGISTRY_OBJECT);

        registerBasePigAttributes(EntityTypesInit.MOTTLED_PIG_REGISTRY_OBJECT);
        registerBasePigAttributes(EntityTypesInit.PALE_PIG_REGISTRY_OBJECT);
        registerBasePigAttributes(EntityTypesInit.PIEBALD_PIG_REGISTRY_OBJECT);
        registerBasePigAttributes(EntityTypesInit.PINK_FOOTED_PIG_REGISTRY_OBJECT);
        registerBasePigAttributes(EntityTypesInit.SOOTY_PIG_REGISTRY_OBJECT);
        registerBasePigAttributes(EntityTypesInit.SPOTTED_PIG_REGISTRY_OBJECT);

        registerBaseRabbitAttributes(EntityTypesInit.BOLD_STRIPED_RABBIT_REGISTRY_OBJECT);
        registerBaseRabbitAttributes(EntityTypesInit.FRECKLED_RABBIT_REGISTRY_OBJECT);
        registerBaseRabbitAttributes(EntityTypesInit.HARELEQUIN_RABBIT_REGISTRY_OBJECT);
        registerBaseRabbitAttributes(EntityTypesInit.MUDDY_FOOT_RABBIT_REGISTRY_OBJECT);
        registerBaseRabbitAttributes(EntityTypesInit.VESTED_RABBIT_REGISTRY_OBJECT);

        registerBaseOneColorSheepAttributes(EntityTypesInit.INKY_SHEEP_REGISTRY_OBJECT);
        registerBaseOneColorSheepAttributes(EntityTypesInit.FLECKED_SHEEP_REGISTRY_OBJECT);
        registerBaseOneColorSheepAttributes(EntityTypesInit.FUZZY_SHEEP_REGISTRY_OBJECT);
        registerBaseOneColorSheepAttributes(EntityTypesInit.LONG_NOSED_SHEEP_REGISTRY_OBJECT);
        registerBaseOneColorSheepAttributes(EntityTypesInit.PATCHED_SHEEP_REGISTRY_OBJECT);
        registerBaseOneColorSheepAttributes(EntityTypesInit.ROCKY_SHEEP_REGISTRY_OBJECT);

        EntityAttributeRegistry.register(EntityTypesInit.HORNED_SHEEP_REGISTRY_OBJECT, HornedSheepEntity::createHornedSheepAttributes);
        EntityAttributeRegistry.register(EntityTypesInit.RAINBOW_SHEEP_REGISTRY_OBJECT, E2JBaseSheepEntity::createSheepAttributes);
        EntityAttributeRegistry.register(EntityTypesInit.CLUCKSHROOM_REGISTRY_OBJECT, E2JBaseChickenEntity::createChickenAttributes);
        EntityAttributeRegistry.register(EntityTypesInit.FANCY_CHICKEN_REGISTRY_OBJECT, E2JBaseChickenEntity::createChickenAttributes);
        EntityAttributeRegistry.register(EntityTypesInit.MUDDY_PIG_REGISTRY_OBJECT, E2JBasePigEntity::createPigAttributes);
        EntityAttributeRegistry.register(EntityTypesInit.MOOBLOOM_REGISTRY_OBJECT, E2JBaseCowEntity::createCowAttributes);
        EntityAttributeRegistry.register(EntityTypesInit.MOOLIP_REGISTRY_OBJECT, E2JBaseCowEntity::createCowAttributes);
        EntityAttributeRegistry.register(EntityTypesInit.UMBRA_COW_REGISTRY_OBJECT, E2JBaseCowEntity::createCowAttributes);
        EntityAttributeRegistry.register(EntityTypesInit.WOOLY_COW_REGISTRY_OBJECT, E2JBaseCowEntity::createCowAttributes);
        EntityAttributeRegistry.register(EntityTypesInit.JUMBO_RABBIT_REGISTRY_OBJECT, E2JBaseRabbitEntity::createRabbitAttributes);
        EntityAttributeRegistry.register(EntityTypesInit.FURNACE_GOLEM_REGISTRY_OBJECT, IronGolemEntity::createIronGolemAttributes);
        EntityAttributeRegistry.register(EntityTypesInit.MELON_GOLEM_REGISTRY_OBJECT, MelonGolemEntity::createMelonGolemAttributes);
        EntityAttributeRegistry.register(EntityTypesInit.TROPICAL_SLIME_REGISTRY_OBJECT, HostileEntity::createHostileAttributes);
        EntityAttributeRegistry.register(EntityTypesInit.BONE_SPIDER_REGISTRY_OBJECT, BoneSpiderEntity::createBoneSpiderAttributes);
        EntityAttributeRegistry.register(EntityTypesInit.SKELETON_WOLF_REGISTRY_OBJECT, SkeletonWolfEntity::createSkeletonWolfAttributes);
        EntityAttributeRegistry.register(EntityTypesInit.JOLLY_LLAMA_REGISTRY_OBJECT, E2JBaseLlamaEntity::createJollyLlamaAttributes);
        EntityAttributeRegistry.register(EntityTypesInit.BOULDERING_ZOMBIE_REGISTRY_OBJECT, E2JBaseZombieEntity::createZombieAttributes);
        EntityAttributeRegistry.register(EntityTypesInit.LOBBER_ZOMBIE_REGISTRY_OBJECT, E2JBaseZombieEntity::createZombieAttributes);
        EntityAttributeRegistry.register(EntityTypesInit.VILER_WITCH_REGISTRY_OBJECT, WitchEntity::createWitchAttributes);
    }

    private static void registerBaseChickenAttributes(RegistrySupplier<EntityType<E2JBaseChickenEntity>> entityType) {
        EntityAttributeRegistry.register(entityType, E2JBaseChickenEntity::createChickenAttributes);
    }

    private static void registerBaseCowAttributes(RegistrySupplier<EntityType<E2JBaseCowEntity>> entityType) {
        EntityAttributeRegistry.register(entityType, E2JBaseCowEntity::createCowAttributes);
    }

    private static void registerBasePigAttributes(RegistrySupplier<EntityType<E2JBasePigEntity>> entityType) {
        EntityAttributeRegistry.register(entityType, E2JBasePigEntity::createPigAttributes);
    }

    private static void registerBaseOneColorSheepAttributes(RegistrySupplier<EntityType<E2JBaseMonoColorSheepEntity>> entityType) {
        EntityAttributeRegistry.register(entityType, E2JBaseSheepEntity::createSheepAttributes);
    }

    private static void registerBaseRabbitAttributes(RegistrySupplier<EntityType<E2JBaseRabbitEntity>> entityType) {
        EntityAttributeRegistry.register(entityType, E2JBaseRabbitEntity::createRabbitAttributes);
    }

}
