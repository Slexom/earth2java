package slexom.earthtojava.init;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.ItemStack;
import slexom.earthtojava.Earth2JavaMod;
import slexom.earthtojava.entity.base.*;
import slexom.earthtojava.entity.monster.*;
import slexom.earthtojava.entity.passive.*;
import slexom.earthtojava.entity.projectile.BoneShardEntity;
import slexom.earthtojava.entity.projectile.MelonSeedProjectileEntity;
import slexom.earthtojava.entity.projectile.RottenFleshProjectileEntity;

public final class EntityTypesInit {

    public static final String ALBINO_COW_REGISTRY_NAME = "albino_cow";
    public static final String AMBER_CHICKEN_REGISTRY_NAME = "amber_chicken";
    public static final String ASHEN_COW_REGISTRY_NAME = "ashen_cow";
    public static final String BOLD_STRIPED_RABBIT_REGISTRY_NAME = "bold_striped_rabbit";
    public static final String BONE_SHARD_REGISTRY_NAME = "bone_shard";
    public static final String BONE_SPIDER_REGISTRY_NAME = "bone_spider";
    public static final String BOULDERING_ZOMBIE_REGISTRY_NAME = "bouldering_zombie";
    public static final String BRONZED_CHICKEN_REGISTRY_NAME = "bronzed_chicken";
    public static final String CLUCKSHROOM_REGISTRY_NAME = "cluckshroom";
    public static final String COOKIE_COW_REGISTRY_NAME = "cookie_cow";
    public static final String CREAM_COW_REGISTRY_NAME = "cream_cow";
    public static final String DAIRY_COW_REGISTRY_NAME = "dairy_cow";
    public static final String FANCY_CHICKEN_REGISTRY_NAME = "fancy_chicken";
    public static final String FLECKED_SHEEP_REGISTRY_NAME = "flecked_sheep";
    public static final String FRECKLED_RABBIT_REGISTRY_NAME = "freckled_rabbit";
    public static final String FURNACE_GOLEM_REGISTRY_NAME = "furnace_golem";
    public static final String FUZZY_SHEEP_REGISTRY_NAME = "fuzzy_sheep";
    public static final String GOLD_CRESTED_CHICKEN_REGISTRY_NAME = "gold_crested_chicken";
    public static final String HARELEQUIN_RABBIT_REGISTRY_NAME = "harelequin_rabbit";
    public static final String HORNED_SHEEP_REGISTRY_NAME = "horned_sheep";
    public static final String INKY_SHEEP_REGISTRY_NAME = "inky_sheep";
    public static final String JOLLY_LLAMA_REGISTRY_NAME = "jolly_llama";
    public static final String JUMBO_RABBIT_REGISTRY_NAME = "jumbo_rabbit";
    public static final String LOBBER_ZOMBIE_REGISTRY_NAME = "lobber_zombie";
    public static final String LONG_NOSED_SHEEP_REGISTRY_NAME = "long_nosed_sheep";
    public static final String MELON_GOLEM_REGISTRY_NAME = "melon_golem";
    public static final String MELON_SEED_PROJECTILE_REGISTRY_NAME = "melon_seed_projectile";
    public static final String MIDNIGHT_CHICKEN_REGISTRY_NAME = "midnight_chicken";
    public static final String MOOBLOOM_REGISTRY_NAME = "moobloom";
    public static final String MOOLIP_REGISTRY_NAME = "moolip";
    public static final String MOTTLED_PIG_REGISTRY_NAME = "mottled_pig";
    public static final String MUDDY_FOOT_RABBIT_REGISTRY_NAME = "muddy_foot_rabbit";
    public static final String MUDDY_PIG_REGISTRY_NAME = "muddy_pig";
    public static final String PALE_PIG_REGISTRY_NAME = "pale_pig";
    public static final String PATCHED_SHEEP_REGISTRY_NAME = "patched_sheep";
    public static final String PIEBALD_PIG_REGISTRY_NAME = "piebald_pig";
    public static final String PINK_FOOTED_PIG_REGISTRY_NAME = "pink_footed_pig";
    public static final String PINTO_COW_REGISTRY_NAME = "pinto_cow";
    public static final String RAINBOW_SHEEP_REGISTRY_NAME = "rainbow_sheep";
    public static final String ROCKY_SHEEP_REGISTRY_NAME = "rocky_sheep";
    public static final String ROTTEN_FLESH_PROJECTILE_REGISTRY_NAME = "rotten_flesh_projectile";
    public static final String SKELETON_WOLF_REGISTRY_NAME = "skeleton_wolf";
    public static final String SKEWBALD_CHICKEN_REGISTRY_NAME = "skewbald_chicken";
    public static final String SOOTY_PIG_REGISTRY_NAME = "sooty_pig";
    public static final String SPOTTED_PIG_REGISTRY_NAME = "spotted_pig";
    public static final String STORMY_CHICKEN_REGISTRY_NAME = "stormy_chicken";
    public static final String SUNSET_COW_REGISTRY_NAME = "sunset_cow";
    public static final String TROPICAL_SLIME_REGISTRY_NAME = "tropical_slime";
    public static final String UMBRA_COW_REGISTRY_NAME = "umbra_cow";
    public static final String VESTED_RABBIT_REGISTRY_NAME = "vested_rabbit";
    public static final String VILER_WITCH_REGISTRY_NAME = "viler_witch";
    public static final String WOOLY_COW_REGISTRY_NAME = "wooly_cow";
    public static final RegistrySupplier<EntityType<E2JBaseCowEntity>> ALBINO_COW_REGISTRY_OBJECT;
    public static final RegistrySupplier<EntityType<E2JBaseChickenEntity>> AMBER_CHICKEN_REGISTRY_OBJECT;
    public static final RegistrySupplier<EntityType<E2JBaseCowEntity>> ASHEN_COW_REGISTRY_OBJECT;
    public static final RegistrySupplier<EntityType<E2JBaseRabbitEntity>> BOLD_STRIPED_RABBIT_REGISTRY_OBJECT;
    public static final RegistrySupplier<EntityType<BoneShardEntity>> BONE_SHARD_REGISTRY_OBJECT;
    public static final RegistrySupplier<EntityType<BoneSpiderEntity>> BONE_SPIDER_REGISTRY_OBJECT;
    public static final RegistrySupplier<EntityType<BoulderingZombieEntity>> BOULDERING_ZOMBIE_REGISTRY_OBJECT;
    public static final RegistrySupplier<EntityType<E2JBaseChickenEntity>> BRONZED_CHICKEN_REGISTRY_OBJECT;
    public static final RegistrySupplier<EntityType<CluckshroomEntity>> CLUCKSHROOM_REGISTRY_OBJECT;
    public static final RegistrySupplier<EntityType<E2JBaseCowEntity>> COOKIE_COW_REGISTRY_OBJECT;
    public static final RegistrySupplier<EntityType<E2JBaseCowEntity>> CREAM_COW_REGISTRY_OBJECT;
    public static final RegistrySupplier<EntityType<E2JBaseCowEntity>> DAIRY_COW_REGISTRY_OBJECT;
    public static final RegistrySupplier<EntityType<FancyChickenEntity>> FANCY_CHICKEN_REGISTRY_OBJECT;
    public static final RegistrySupplier<EntityType<E2JBaseMonoColorSheepEntity>> FLECKED_SHEEP_REGISTRY_OBJECT;
    public static final RegistrySupplier<EntityType<E2JBaseRabbitEntity>> FRECKLED_RABBIT_REGISTRY_OBJECT;
    public static final RegistrySupplier<EntityType<FurnaceGolemEntity>> FURNACE_GOLEM_REGISTRY_OBJECT;
    public static final RegistrySupplier<EntityType<E2JBaseMonoColorSheepEntity>> FUZZY_SHEEP_REGISTRY_OBJECT;
    public static final RegistrySupplier<EntityType<E2JBaseChickenEntity>> GOLD_CRESTED_CHICKEN_REGISTRY_OBJECT;
    public static final RegistrySupplier<EntityType<E2JBaseRabbitEntity>> HARELEQUIN_RABBIT_REGISTRY_OBJECT;
    public static final RegistrySupplier<EntityType<HornedSheepEntity>> HORNED_SHEEP_REGISTRY_OBJECT;
    public static final RegistrySupplier<EntityType<E2JBaseMonoColorSheepEntity>> INKY_SHEEP_REGISTRY_OBJECT;
    public static final RegistrySupplier<EntityType<JollyLlamaEntity>> JOLLY_LLAMA_REGISTRY_OBJECT;
    public static final RegistrySupplier<EntityType<JumboRabbitEntity>> JUMBO_RABBIT_REGISTRY_OBJECT;
    public static final RegistrySupplier<EntityType<LobberZombieEntity>> LOBBER_ZOMBIE_REGISTRY_OBJECT;
    public static final RegistrySupplier<EntityType<E2JBaseMonoColorSheepEntity>> LONG_NOSED_SHEEP_REGISTRY_OBJECT;
    public static final RegistrySupplier<EntityType<MelonGolemEntity>> MELON_GOLEM_REGISTRY_OBJECT;
    public static final RegistrySupplier<EntityType<MelonSeedProjectileEntity>> MELON_SEED_PROJECTILE_REGISTRY_OBJECT;
    public static final RegistrySupplier<EntityType<E2JBaseChickenEntity>> MIDNIGHT_CHICKEN_REGISTRY_OBJECT;
    public static final RegistrySupplier<EntityType<MoobloomEntity>> MOOBLOOM_REGISTRY_OBJECT;
    public static final RegistrySupplier<EntityType<MoolipEntity>> MOOLIP_REGISTRY_OBJECT;
    public static final RegistrySupplier<EntityType<E2JBasePigEntity>> MOTTLED_PIG_REGISTRY_OBJECT;
    public static final RegistrySupplier<EntityType<E2JBaseRabbitEntity>> MUDDY_FOOT_RABBIT_REGISTRY_OBJECT;
    public static final RegistrySupplier<EntityType<MuddyPigEntity>> MUDDY_PIG_REGISTRY_OBJECT;
    public static final RegistrySupplier<EntityType<E2JBasePigEntity>> PALE_PIG_REGISTRY_OBJECT;
    public static final RegistrySupplier<EntityType<E2JBaseMonoColorSheepEntity>> PATCHED_SHEEP_REGISTRY_OBJECT;
    public static final RegistrySupplier<EntityType<E2JBasePigEntity>> PIEBALD_PIG_REGISTRY_OBJECT;
    public static final RegistrySupplier<EntityType<E2JBasePigEntity>> PINK_FOOTED_PIG_REGISTRY_OBJECT;
    public static final RegistrySupplier<EntityType<E2JBaseCowEntity>> PINTO_COW_REGISTRY_OBJECT;
    public static final RegistrySupplier<EntityType<RainbowSheepEntity>> RAINBOW_SHEEP_REGISTRY_OBJECT;
    public static final RegistrySupplier<EntityType<E2JBaseMonoColorSheepEntity>> ROCKY_SHEEP_REGISTRY_OBJECT;
    public static final RegistrySupplier<EntityType<RottenFleshProjectileEntity>> ROTTEN_FLESH_PROJECTILE_REGISTRY_OBJECT;
    public static final RegistrySupplier<EntityType<SkeletonWolfEntity>> SKELETON_WOLF_REGISTRY_OBJECT;
    public static final RegistrySupplier<EntityType<E2JBaseChickenEntity>> SKEWBALD_CHICKEN_REGISTRY_OBJECT;
    public static final RegistrySupplier<EntityType<E2JBasePigEntity>> SOOTY_PIG_REGISTRY_OBJECT;
    public static final RegistrySupplier<EntityType<E2JBasePigEntity>> SPOTTED_PIG_REGISTRY_OBJECT;
    public static final RegistrySupplier<EntityType<E2JBaseChickenEntity>> STORMY_CHICKEN_REGISTRY_OBJECT;
    public static final RegistrySupplier<EntityType<E2JBaseCowEntity>> SUNSET_COW_REGISTRY_OBJECT;
    public static final RegistrySupplier<EntityType<TropicalSlimeEntity>> TROPICAL_SLIME_REGISTRY_OBJECT;
    public static final RegistrySupplier<EntityType<UmbraCowEntity>> UMBRA_COW_REGISTRY_OBJECT;
    public static final RegistrySupplier<EntityType<E2JBaseRabbitEntity>> VESTED_RABBIT_REGISTRY_OBJECT;
    public static final RegistrySupplier<EntityType<VilerWitchEntity>> VILER_WITCH_REGISTRY_OBJECT;
    public static final RegistrySupplier<EntityType<WoolyCowEntity>> WOOLY_COW_REGISTRY_OBJECT;

    static {
        ASHEN_COW_REGISTRY_OBJECT = registerBaseCowEntity(ASHEN_COW_REGISTRY_NAME);
        ALBINO_COW_REGISTRY_OBJECT = registerBaseCowEntity(ALBINO_COW_REGISTRY_NAME);
        COOKIE_COW_REGISTRY_OBJECT = registerBaseCowEntity(COOKIE_COW_REGISTRY_NAME);
        CREAM_COW_REGISTRY_OBJECT = registerBaseCowEntity(CREAM_COW_REGISTRY_NAME);
        DAIRY_COW_REGISTRY_OBJECT = registerBaseCowEntity(DAIRY_COW_REGISTRY_NAME);
        PINTO_COW_REGISTRY_OBJECT = registerBaseCowEntity(PINTO_COW_REGISTRY_NAME);
        SUNSET_COW_REGISTRY_OBJECT = registerBaseCowEntity(SUNSET_COW_REGISTRY_NAME);

        AMBER_CHICKEN_REGISTRY_OBJECT = registerBaseChickenEntity(AMBER_CHICKEN_REGISTRY_NAME);
        BRONZED_CHICKEN_REGISTRY_OBJECT = registerBaseChickenEntity(BRONZED_CHICKEN_REGISTRY_NAME);
        GOLD_CRESTED_CHICKEN_REGISTRY_OBJECT = registerBaseChickenEntity(GOLD_CRESTED_CHICKEN_REGISTRY_NAME);
        MIDNIGHT_CHICKEN_REGISTRY_OBJECT = registerBaseChickenEntity(MIDNIGHT_CHICKEN_REGISTRY_NAME);
        SKEWBALD_CHICKEN_REGISTRY_OBJECT = registerBaseChickenEntity(SKEWBALD_CHICKEN_REGISTRY_NAME);
        STORMY_CHICKEN_REGISTRY_OBJECT = registerBaseChickenEntity(STORMY_CHICKEN_REGISTRY_NAME);

        MOTTLED_PIG_REGISTRY_OBJECT = registerBasePigEntity(MOTTLED_PIG_REGISTRY_NAME);
        PALE_PIG_REGISTRY_OBJECT = registerBasePigEntity(PALE_PIG_REGISTRY_NAME);
        PIEBALD_PIG_REGISTRY_OBJECT = registerBasePigEntity(PIEBALD_PIG_REGISTRY_NAME);
        PINK_FOOTED_PIG_REGISTRY_OBJECT = registerBasePigEntity(PINK_FOOTED_PIG_REGISTRY_NAME);
        SOOTY_PIG_REGISTRY_OBJECT = registerBasePigEntity(SOOTY_PIG_REGISTRY_NAME);
        SPOTTED_PIG_REGISTRY_OBJECT = registerBasePigEntity(SPOTTED_PIG_REGISTRY_NAME);

        BOLD_STRIPED_RABBIT_REGISTRY_OBJECT = registerBaseRabbitEntity(BOLD_STRIPED_RABBIT_REGISTRY_NAME);
        FRECKLED_RABBIT_REGISTRY_OBJECT = registerBaseRabbitEntity(FRECKLED_RABBIT_REGISTRY_NAME);
        HARELEQUIN_RABBIT_REGISTRY_OBJECT = registerBaseRabbitEntity(HARELEQUIN_RABBIT_REGISTRY_NAME);
        MUDDY_FOOT_RABBIT_REGISTRY_OBJECT = registerBaseRabbitEntity(MUDDY_FOOT_RABBIT_REGISTRY_NAME);
        VESTED_RABBIT_REGISTRY_OBJECT = registerBaseRabbitEntity(VESTED_RABBIT_REGISTRY_NAME);

        FLECKED_SHEEP_REGISTRY_OBJECT = registerBaseMonoColorSheepEntity(FLECKED_SHEEP_REGISTRY_NAME, new ItemStack(Blocks.BROWN_WOOL));
        FUZZY_SHEEP_REGISTRY_OBJECT = registerBaseMonoColorSheepEntity(FUZZY_SHEEP_REGISTRY_NAME, new ItemStack(Blocks.WHITE_WOOL));
        INKY_SHEEP_REGISTRY_OBJECT = registerBaseMonoColorSheepEntity(INKY_SHEEP_REGISTRY_NAME, new ItemStack(Blocks.BLACK_WOOL));
        LONG_NOSED_SHEEP_REGISTRY_OBJECT = registerBaseMonoColorSheepEntity(LONG_NOSED_SHEEP_REGISTRY_NAME, new ItemStack(Blocks.BROWN_WOOL));
        PATCHED_SHEEP_REGISTRY_OBJECT = registerBaseMonoColorSheepEntity(PATCHED_SHEEP_REGISTRY_NAME, new ItemStack(Blocks.WHITE_WOOL));
        ROCKY_SHEEP_REGISTRY_OBJECT = registerBaseMonoColorSheepEntity(ROCKY_SHEEP_REGISTRY_NAME, new ItemStack(Blocks.GRAY_WOOL));

        BONE_SHARD_REGISTRY_OBJECT = Earth2JavaMod.ENTITY_TYPE_REGISTRAR.register(Earth2JavaMod.toIdentifier(BONE_SHARD_REGISTRY_NAME), () -> EntityType.Builder.<BoneShardEntity>create(BoneShardEntity::new, SpawnGroup.MISC)
                .setDimensions(0.25F, 0.25F)
                .maxTrackingRange(4)
                .trackingTickInterval(10)
                .build(BONE_SHARD_REGISTRY_NAME));
        BONE_SPIDER_REGISTRY_OBJECT = Earth2JavaMod.ENTITY_TYPE_REGISTRAR.register(Earth2JavaMod.toIdentifier(BONE_SPIDER_REGISTRY_NAME), () -> EntityType.Builder.create(BoneSpiderEntity::new, SpawnGroup.MONSTER)
                .setDimensions(0.6F, 0.7F)
                .maxTrackingRange(8)
                .build(BONE_SPIDER_REGISTRY_NAME));
        BOULDERING_ZOMBIE_REGISTRY_OBJECT = Earth2JavaMod.ENTITY_TYPE_REGISTRAR.register(Earth2JavaMod.toIdentifier(BOULDERING_ZOMBIE_REGISTRY_NAME), () -> EntityType.Builder.create(BoulderingZombieEntity::new, SpawnGroup.MONSTER)
                .setDimensions(EntityType.ZOMBIE.getWidth(), EntityType.ZOMBIE.getHeight())
                .maxTrackingRange(8)
                .build(BOULDERING_ZOMBIE_REGISTRY_NAME));
        CLUCKSHROOM_REGISTRY_OBJECT = Earth2JavaMod.ENTITY_TYPE_REGISTRAR.register(Earth2JavaMod.toIdentifier(CLUCKSHROOM_REGISTRY_NAME), () -> EntityType.Builder.create(CluckshroomEntity::new, SpawnGroup.CREATURE)
                .setDimensions(EntityType.CHICKEN.getWidth(), EntityType.CHICKEN.getHeight())
                .maxTrackingRange(10)
                .build(CLUCKSHROOM_REGISTRY_NAME));
        FANCY_CHICKEN_REGISTRY_OBJECT = Earth2JavaMod.ENTITY_TYPE_REGISTRAR.register(Earth2JavaMod.toIdentifier(FANCY_CHICKEN_REGISTRY_NAME), () -> EntityType.Builder.create(FancyChickenEntity::new, SpawnGroup.CREATURE)
                .setDimensions(EntityType.CHICKEN.getWidth(), EntityType.CHICKEN.getHeight())
                .maxTrackingRange(10)
                .build(FANCY_CHICKEN_REGISTRY_NAME));
        FURNACE_GOLEM_REGISTRY_OBJECT = Earth2JavaMod.ENTITY_TYPE_REGISTRAR.register(Earth2JavaMod.toIdentifier(FURNACE_GOLEM_REGISTRY_NAME), () -> EntityType.Builder.create(FurnaceGolemEntity::new, SpawnGroup.MISC)
                .setDimensions(EntityType.IRON_GOLEM.getWidth(), EntityType.IRON_GOLEM.getHeight())
                .makeFireImmune()
                .maxTrackingRange(10)
                .build(FURNACE_GOLEM_REGISTRY_NAME));
        HORNED_SHEEP_REGISTRY_OBJECT = Earth2JavaMod.ENTITY_TYPE_REGISTRAR.register(Earth2JavaMod.toIdentifier(HORNED_SHEEP_REGISTRY_NAME), () -> EntityType.Builder.create(HornedSheepEntity::new, SpawnGroup.CREATURE)
                .setDimensions(EntityType.SHEEP.getWidth(), EntityType.SHEEP.getHeight())
                .maxTrackingRange(10)
                .build(HORNED_SHEEP_REGISTRY_NAME));
        JOLLY_LLAMA_REGISTRY_OBJECT = Earth2JavaMod.ENTITY_TYPE_REGISTRAR.register(Earth2JavaMod.toIdentifier(JOLLY_LLAMA_REGISTRY_NAME), () -> EntityType.Builder.create(JollyLlamaEntity::new, SpawnGroup.CREATURE)
                .setDimensions(EntityType.LLAMA.getWidth(), EntityType.LLAMA.getHeight())
                .maxTrackingRange(10)
                .build(JOLLY_LLAMA_REGISTRY_NAME));
        JUMBO_RABBIT_REGISTRY_OBJECT = Earth2JavaMod.ENTITY_TYPE_REGISTRAR.register(Earth2JavaMod.toIdentifier(JUMBO_RABBIT_REGISTRY_NAME), () -> EntityType.Builder.create(JumboRabbitEntity::new, SpawnGroup.CREATURE)
                .setDimensions(0.8F, 1.0F)
                .maxTrackingRange(8)
                .build(JUMBO_RABBIT_REGISTRY_NAME));
        LOBBER_ZOMBIE_REGISTRY_OBJECT = Earth2JavaMod.ENTITY_TYPE_REGISTRAR.register(Earth2JavaMod.toIdentifier(LOBBER_ZOMBIE_REGISTRY_NAME), () -> EntityType.Builder.create(LobberZombieEntity::new, SpawnGroup.MONSTER)
                .setDimensions(EntityType.ZOMBIE.getWidth(), EntityType.ZOMBIE.getHeight())
                .maxTrackingRange(8)
                .build(LOBBER_ZOMBIE_REGISTRY_NAME));
        MELON_GOLEM_REGISTRY_OBJECT = Earth2JavaMod.ENTITY_TYPE_REGISTRAR.register(Earth2JavaMod.toIdentifier(MELON_GOLEM_REGISTRY_NAME), () -> EntityType.Builder.create(MelonGolemEntity::new, SpawnGroup.MISC)
                .setDimensions(EntityType.SNOW_GOLEM.getWidth(), EntityType.SNOW_GOLEM.getHeight())
                .allowSpawningInside(Blocks.POWDER_SNOW)
                .maxTrackingRange(8)
                .build(MELON_GOLEM_REGISTRY_NAME));
        MELON_SEED_PROJECTILE_REGISTRY_OBJECT = Earth2JavaMod.ENTITY_TYPE_REGISTRAR.register(Earth2JavaMod.toIdentifier(MELON_SEED_PROJECTILE_REGISTRY_NAME), () -> EntityType.Builder.<MelonSeedProjectileEntity>create(MelonSeedProjectileEntity::new, SpawnGroup.MISC)
                .setDimensions(0.25F, 0.25F)
                .maxTrackingRange(4)
                .trackingTickInterval(10)
                .build(MELON_SEED_PROJECTILE_REGISTRY_NAME));
        MOOBLOOM_REGISTRY_OBJECT = Earth2JavaMod.ENTITY_TYPE_REGISTRAR.register(Earth2JavaMod.toIdentifier(MOOBLOOM_REGISTRY_NAME), () -> EntityType.Builder.create(MoobloomEntity::new, SpawnGroup.CREATURE)
                .setDimensions(EntityType.COW.getWidth(), EntityType.COW.getHeight())
                .maxTrackingRange(10)
                .build(MOOBLOOM_REGISTRY_NAME));
        MOOLIP_REGISTRY_OBJECT = Earth2JavaMod.ENTITY_TYPE_REGISTRAR.register(Earth2JavaMod.toIdentifier(MOOLIP_REGISTRY_NAME), () -> EntityType.Builder.create(MoolipEntity::new, SpawnGroup.CREATURE)
                .setDimensions(EntityType.COW.getWidth(), EntityType.COW.getHeight())
                .maxTrackingRange(10)
                .build(MOOLIP_REGISTRY_NAME));
        MUDDY_PIG_REGISTRY_OBJECT = Earth2JavaMod.ENTITY_TYPE_REGISTRAR.register(Earth2JavaMod.toIdentifier(MUDDY_PIG_REGISTRY_NAME), () -> EntityType.Builder.create(MuddyPigEntity::new, SpawnGroup.CREATURE)
                .setDimensions(EntityType.PIG.getWidth(), EntityType.PIG.getHeight())
                .maxTrackingRange(10)
                .build(MUDDY_PIG_REGISTRY_NAME));
        RAINBOW_SHEEP_REGISTRY_OBJECT = Earth2JavaMod.ENTITY_TYPE_REGISTRAR.register(Earth2JavaMod.toIdentifier(RAINBOW_SHEEP_REGISTRY_NAME), () -> EntityType.Builder.create(RainbowSheepEntity::new, SpawnGroup.CREATURE)
                .setDimensions(EntityType.SHEEP.getWidth(), EntityType.SHEEP.getHeight())
                .maxTrackingRange(10)
                .build(RAINBOW_SHEEP_REGISTRY_NAME));
        ROTTEN_FLESH_PROJECTILE_REGISTRY_OBJECT = Earth2JavaMod.ENTITY_TYPE_REGISTRAR.register(Earth2JavaMod.toIdentifier(ROTTEN_FLESH_PROJECTILE_REGISTRY_NAME), () -> EntityType.Builder.<RottenFleshProjectileEntity>create(RottenFleshProjectileEntity::new, SpawnGroup.MISC)
                .setDimensions(0.25F, 0.25F)
                .maxTrackingRange(4)
                .trackingTickInterval(10)
                .build(ROTTEN_FLESH_PROJECTILE_REGISTRY_NAME));
        SKELETON_WOLF_REGISTRY_OBJECT = Earth2JavaMod.ENTITY_TYPE_REGISTRAR.register(Earth2JavaMod.toIdentifier(SKELETON_WOLF_REGISTRY_NAME), () -> EntityType.Builder.create(SkeletonWolfEntity::new, SpawnGroup.MONSTER)
                .setDimensions(EntityType.WOLF.getWidth(), EntityType.WOLF.getHeight())
                .maxTrackingRange(10)
                .build(SKELETON_WOLF_REGISTRY_NAME));
        TROPICAL_SLIME_REGISTRY_OBJECT = Earth2JavaMod.ENTITY_TYPE_REGISTRAR.register(Earth2JavaMod.toIdentifier(TROPICAL_SLIME_REGISTRY_NAME), () -> EntityType.Builder.create(TropicalSlimeEntity::new, SpawnGroup.CREATURE)
                .setDimensions(2.04F, 2.04F)
                .makeFireImmune()
                .maxTrackingRange(10)
                .build(TROPICAL_SLIME_REGISTRY_NAME));
        UMBRA_COW_REGISTRY_OBJECT = Earth2JavaMod.ENTITY_TYPE_REGISTRAR.register(Earth2JavaMod.toIdentifier(UMBRA_COW_REGISTRY_NAME), () -> EntityType.Builder.create(UmbraCowEntity::new, SpawnGroup.CREATURE)
                .setDimensions(EntityType.COW.getWidth(), EntityType.COW.getHeight())
                .maxTrackingRange(10)
                .build(UMBRA_COW_REGISTRY_NAME));
        VILER_WITCH_REGISTRY_OBJECT = Earth2JavaMod.ENTITY_TYPE_REGISTRAR.register(Earth2JavaMod.toIdentifier(VILER_WITCH_REGISTRY_NAME), () -> EntityType.Builder.create(VilerWitchEntity::new, SpawnGroup.MONSTER)
                .setDimensions(EntityType.WITCH.getWidth(), EntityType.WITCH.getHeight())
                .maxTrackingRange(8)
                .build(VILER_WITCH_REGISTRY_NAME));
        WOOLY_COW_REGISTRY_OBJECT = Earth2JavaMod.ENTITY_TYPE_REGISTRAR.register(Earth2JavaMod.toIdentifier(WOOLY_COW_REGISTRY_NAME), () -> EntityType.Builder.create(WoolyCowEntity::new, SpawnGroup.CREATURE)
                .setDimensions(EntityType.COW.getWidth(), EntityType.COW.getHeight())
                .maxTrackingRange(10)
                .build(WOOLY_COW_REGISTRY_NAME));
    }

    public static void init() {
    }

    private static RegistrySupplier<EntityType<E2JBaseChickenEntity>> registerBaseChickenEntity(String registryName) {
        EntityType.Builder<E2JBaseChickenEntity> entityType = EntityType.Builder.create(E2JBaseChickenEntity::new, SpawnGroup.CREATURE)
                .setDimensions(EntityType.CHICKEN.getWidth(), EntityType.CHICKEN.getHeight())
                .maxTrackingRange(10);
        return Earth2JavaMod.ENTITY_TYPE_REGISTRAR.register(Earth2JavaMod.toIdentifier(registryName), () -> entityType.build(registryName));
    }

    private static RegistrySupplier<EntityType<E2JBaseCowEntity>> registerBaseCowEntity(String registryName) {
        EntityType.Builder<E2JBaseCowEntity> entityType = EntityType.Builder.create(E2JBaseCowEntity::new, SpawnGroup.CREATURE)
                .setDimensions(EntityType.COW.getWidth(), EntityType.COW.getHeight())
                .maxTrackingRange(10);
        return Earth2JavaMod.ENTITY_TYPE_REGISTRAR.register(Earth2JavaMod.toIdentifier(registryName), () -> entityType.build(registryName));
    }

    private static RegistrySupplier<EntityType<E2JBasePigEntity>> registerBasePigEntity(String registryName) {
        EntityType.Builder<E2JBasePigEntity> entityType = EntityType.Builder.create(E2JBasePigEntity::new, SpawnGroup.CREATURE)
                .setDimensions(EntityType.PIG.getWidth(), EntityType.PIG.getHeight())
                .maxTrackingRange(10);
        return Earth2JavaMod.ENTITY_TYPE_REGISTRAR.register(Earth2JavaMod.toIdentifier(registryName), () -> entityType.build(registryName));
    }

    private static RegistrySupplier<EntityType<E2JBaseRabbitEntity>> registerBaseRabbitEntity(String registryName) {
        EntityType.Builder<E2JBaseRabbitEntity> entityType = EntityType.Builder.create(E2JBaseRabbitEntity::new, SpawnGroup.CREATURE)
                .setDimensions(EntityType.RABBIT.getWidth(), EntityType.RABBIT.getHeight())
                .maxTrackingRange(8);
        return Earth2JavaMod.ENTITY_TYPE_REGISTRAR.register(Earth2JavaMod.toIdentifier(registryName), () -> entityType.build(registryName));
    }

    private static RegistrySupplier<EntityType<E2JBaseMonoColorSheepEntity>> registerBaseMonoColorSheepEntity(String registryName, ItemStack wool) {
        EntityType.Builder<E2JBaseMonoColorSheepEntity> entityType = EntityType.Builder.<E2JBaseMonoColorSheepEntity>create((type, world) -> new E2JBaseMonoColorSheepEntity(type, world, wool), SpawnGroup.CREATURE)
                .setDimensions(EntityType.SHEEP.getWidth(), EntityType.SHEEP.getHeight())
                .maxTrackingRange(10);
        return Earth2JavaMod.ENTITY_TYPE_REGISTRAR.register(Earth2JavaMod.toIdentifier(registryName), () -> entityType.build(registryName));
    }


}


