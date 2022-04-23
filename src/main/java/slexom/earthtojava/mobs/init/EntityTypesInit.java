package slexom.earthtojava.init;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.ItemStack;
import slexom.earthtojava.entity.base.*;
import slexom.earthtojava.entity.monster.*;
import slexom.earthtojava.entity.passive.*;
import slexom.earthtojava.entity.projectile.BoneShardEntity;
import slexom.earthtojava.entity.projectile.MelonSeedProjectileEntity;
import slexom.earthtojava.entity.projectile.RottenFleshProjectileEntity;

public class EntityTypesInit {

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
    public static final String GLOW_SQUID_REGISTRY_NAME = "glow_squid";
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
    public static EntityType<E2JBaseCowEntity> ALBINO_COW_REGISTRY_OBJECT;
    public static EntityType<E2JBaseChickenEntity> AMBER_CHICKEN_REGISTRY_OBJECT;
    public static EntityType<E2JBaseCowEntity> ASHEN_COW_REGISTRY_OBJECT;
    public static EntityType<E2JBaseRabbitEntity> BOLD_STRIPED_RABBIT_REGISTRY_OBJECT;
    public static EntityType<BoneShardEntity> BONE_SHARD_REGISTRY_OBJECT;
    public static EntityType<BoneSpiderEntity> BONE_SPIDER_REGISTRY_OBJECT;
    public static EntityType<BoulderingZombieEntity> BOULDERING_ZOMBIE_REGISTRY_OBJECT;
    public static EntityType<E2JBaseChickenEntity> BRONZED_CHICKEN_REGISTRY_OBJECT;
    public static EntityType<CluckshroomEntity> CLUCKSHROOM_REGISTRY_OBJECT;
    public static EntityType<E2JBaseCowEntity> COOKIE_COW_REGISTRY_OBJECT;
    public static EntityType<E2JBaseCowEntity> CREAM_COW_REGISTRY_OBJECT;
    public static EntityType<E2JBaseCowEntity> DAIRY_COW_REGISTRY_OBJECT;
    public static EntityType<FancyChickenEntity> FANCY_CHICKEN_REGISTRY_OBJECT;
    public static EntityType<E2JBaseMonoColorSheepEntity> FLECKED_SHEEP_REGISTRY_OBJECT;
    public static EntityType<E2JBaseRabbitEntity> FRECKLED_RABBIT_REGISTRY_OBJECT;
    public static EntityType<FurnaceGolemEntity> FURNACE_GOLEM_REGISTRY_OBJECT;
    public static EntityType<E2JBaseMonoColorSheepEntity> FUZZY_SHEEP_REGISTRY_OBJECT;
    public static EntityType<E2JBaseChickenEntity> GOLD_CRESTED_CHICKEN_REGISTRY_OBJECT;
    public static EntityType<E2JBaseRabbitEntity> HARELEQUIN_RABBIT_REGISTRY_OBJECT;
    public static EntityType<HornedSheepEntity> HORNED_SHEEP_REGISTRY_OBJECT;
    public static EntityType<E2JBaseMonoColorSheepEntity> INKY_SHEEP_REGISTRY_OBJECT;
    public static EntityType<JollyLlamaEntity> JOLLY_LLAMA_REGISTRY_OBJECT;
    public static EntityType<JumboRabbitEntity> JUMBO_RABBIT_REGISTRY_OBJECT;
    public static EntityType<LobberZombieEntity> LOBBER_ZOMBIE_REGISTRY_OBJECT;
    public static EntityType<E2JBaseMonoColorSheepEntity> LONG_NOSED_SHEEP_REGISTRY_OBJECT;
    public static EntityType<MelonGolemEntity> MELON_GOLEM_REGISTRY_OBJECT;
    public static EntityType<MelonSeedProjectileEntity> MELON_SEED_PROJECTILE_REGISTRY_OBJECT;
    public static EntityType<E2JBaseChickenEntity> MIDNIGHT_CHICKEN_REGISTRY_OBJECT;
    public static EntityType<MoobloomEntity> MOOBLOOM_REGISTRY_OBJECT;
    public static EntityType<MoolipEntity> MOOLIP_REGISTRY_OBJECT;
    public static EntityType<E2JBasePigEntity> MOTTLED_PIG_REGISTRY_OBJECT;
    public static EntityType<E2JBaseRabbitEntity> MUDDY_FOOT_RABBIT_REGISTRY_OBJECT;
    public static EntityType<MuddyPigEntity> MUDDY_PIG_REGISTRY_OBJECT;
    public static EntityType<E2JBasePigEntity> PALE_PIG_REGISTRY_OBJECT;
    public static EntityType<E2JBaseMonoColorSheepEntity> PATCHED_SHEEP_REGISTRY_OBJECT;
    public static EntityType<E2JBasePigEntity> PIEBALD_PIG_REGISTRY_OBJECT;
    public static EntityType<E2JBasePigEntity> PINK_FOOTED_PIG_REGISTRY_OBJECT;
    public static EntityType<E2JBaseCowEntity> PINTO_COW_REGISTRY_OBJECT;
    public static EntityType<RainbowSheepEntity> RAINBOW_SHEEP_REGISTRY_OBJECT;
    public static EntityType<E2JBaseMonoColorSheepEntity> ROCKY_SHEEP_REGISTRY_OBJECT;
    public static EntityType<RottenFleshProjectileEntity> ROTTEN_FLESH_PROJECTILE_REGISTRY_OBJECT;
    public static EntityType<SkeletonWolfEntity> SKELETON_WOLF_REGISTRY_OBJECT;
    public static EntityType<E2JBaseChickenEntity> SKEWBALD_CHICKEN_REGISTRY_OBJECT;
    public static EntityType<E2JBasePigEntity> SOOTY_PIG_REGISTRY_OBJECT;
    public static EntityType<E2JBasePigEntity> SPOTTED_PIG_REGISTRY_OBJECT;
    public static EntityType<E2JBaseChickenEntity> STORMY_CHICKEN_REGISTRY_OBJECT;
    public static EntityType<E2JBaseCowEntity> SUNSET_COW_REGISTRY_OBJECT;
    public static EntityType<TropicalSlimeEntity> TROPICAL_SLIME_REGISTRY_OBJECT;
    public static EntityType<UmbraCowEntity> UMBRA_COW_REGISTRY_OBJECT;
    public static EntityType<E2JBaseRabbitEntity> VESTED_RABBIT_REGISTRY_OBJECT;
    public static EntityType<VilerWitchEntity> VILER_WITCH_REGISTRY_OBJECT;
    public static EntityType<WoolyCowEntity> WOOLY_COW_REGISTRY_OBJECT;

    public static void init() {
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

        BONE_SHARD_REGISTRY_OBJECT = RegisterHelper.registerEntity(BONE_SHARD_REGISTRY_NAME, FabricEntityTypeBuilder.<BoneShardEntity>create(SpawnGroup.MISC, BoneShardEntity::new).dimensions(EntityDimensions.fixed(0.25F, 0.25F)).trackRangeBlocks(64).trackedUpdateRate(3).build());
        BONE_SPIDER_REGISTRY_OBJECT = RegisterHelper.registerEntity(BONE_SPIDER_REGISTRY_NAME, FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, BoneSpiderEntity::new).dimensions(EntityDimensions.fixed(0.6F, 0.7F)).trackRangeBlocks(64).trackedUpdateRate(3).build());
        BOULDERING_ZOMBIE_REGISTRY_OBJECT = RegisterHelper.registerEntity(BOULDERING_ZOMBIE_REGISTRY_NAME, FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, BoulderingZombieEntity::new).dimensions(EntityDimensions.fixed(EntityType.ZOMBIE.getWidth(), EntityType.ZOMBIE.getHeight())).trackRangeBlocks(64).trackedUpdateRate(3).build());
        CLUCKSHROOM_REGISTRY_OBJECT = RegisterHelper.registerEntity(CLUCKSHROOM_REGISTRY_NAME, FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, CluckshroomEntity::new).dimensions(chickenDimensions()).trackRangeBlocks(64).trackedUpdateRate(3).build());
        FANCY_CHICKEN_REGISTRY_OBJECT = RegisterHelper.registerEntity(FANCY_CHICKEN_REGISTRY_NAME, FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, FancyChickenEntity::new).dimensions(chickenDimensions()).trackRangeBlocks(64).trackedUpdateRate(3).build());
        FURNACE_GOLEM_REGISTRY_OBJECT = RegisterHelper.registerEntity(FURNACE_GOLEM_REGISTRY_NAME, FabricEntityTypeBuilder.create(SpawnGroup.MISC, FurnaceGolemEntity::new).dimensions(EntityDimensions.fixed(EntityType.IRON_GOLEM.getWidth(), EntityType.IRON_GOLEM.getHeight())).fireImmune().trackRangeBlocks(64).trackedUpdateRate(3).build());
        HORNED_SHEEP_REGISTRY_OBJECT = RegisterHelper.registerEntity(HORNED_SHEEP_REGISTRY_NAME, FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, HornedSheepEntity::new).dimensions(sheepDimensions()).trackRangeBlocks(64).trackedUpdateRate(3).build());
        JOLLY_LLAMA_REGISTRY_OBJECT = RegisterHelper.registerEntity(JOLLY_LLAMA_REGISTRY_NAME, FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, JollyLlamaEntity::new).dimensions(EntityDimensions.fixed(EntityType.LLAMA.getWidth(), EntityType.LLAMA.getHeight())).trackRangeBlocks(64).trackedUpdateRate(3).build());
        JUMBO_RABBIT_REGISTRY_OBJECT = RegisterHelper.registerEntity(JUMBO_RABBIT_REGISTRY_NAME, FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, JumboRabbitEntity::new).dimensions(EntityDimensions.fixed(0.8F, 1.0F)).trackRangeBlocks(64).trackedUpdateRate(3).build());
        LOBBER_ZOMBIE_REGISTRY_OBJECT = RegisterHelper.registerEntity(LOBBER_ZOMBIE_REGISTRY_NAME, FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, LobberZombieEntity::new).dimensions(EntityDimensions.fixed(EntityType.ZOMBIE.getWidth(), EntityType.ZOMBIE.getHeight())).trackRangeBlocks(64).trackedUpdateRate(3).build());
        MELON_GOLEM_REGISTRY_OBJECT = RegisterHelper.registerEntity(MELON_GOLEM_REGISTRY_NAME, FabricEntityTypeBuilder.create(SpawnGroup.MISC, MelonGolemEntity::new).dimensions(EntityDimensions.fixed(EntityType.SNOW_GOLEM.getWidth(), EntityType.SNOW_GOLEM.getHeight())).trackRangeBlocks(64).trackedUpdateRate(3).build());
        MELON_SEED_PROJECTILE_REGISTRY_OBJECT = RegisterHelper.registerEntity(MELON_SEED_PROJECTILE_REGISTRY_NAME, FabricEntityTypeBuilder.<MelonSeedProjectileEntity>create(SpawnGroup.MISC, MelonSeedProjectileEntity::new).dimensions(EntityDimensions.fixed(0.25F, 0.25F)).trackRangeBlocks(64).trackedUpdateRate(3).build());
        MOOBLOOM_REGISTRY_OBJECT = RegisterHelper.registerEntity(MOOBLOOM_REGISTRY_NAME, FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, MoobloomEntity::new).dimensions(cowDimensions()).trackRangeBlocks(64).trackedUpdateRate(3).build());
        MOOLIP_REGISTRY_OBJECT = RegisterHelper.registerEntity(MOOLIP_REGISTRY_NAME, FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, MoolipEntity::new).dimensions(cowDimensions()).trackRangeBlocks(64).trackedUpdateRate(3).build());
        MUDDY_PIG_REGISTRY_OBJECT = RegisterHelper.registerEntity(MUDDY_PIG_REGISTRY_NAME, FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, MuddyPigEntity::new).dimensions(pigDimensions()).trackRangeBlocks(64).trackedUpdateRate(3).build());
        RAINBOW_SHEEP_REGISTRY_OBJECT = RegisterHelper.registerEntity(RAINBOW_SHEEP_REGISTRY_NAME, FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, RainbowSheepEntity::new).dimensions(sheepDimensions()).trackRangeBlocks(64).trackedUpdateRate(3).build());
        ROTTEN_FLESH_PROJECTILE_REGISTRY_OBJECT = RegisterHelper.registerEntity(ROTTEN_FLESH_PROJECTILE_REGISTRY_NAME, FabricEntityTypeBuilder.<RottenFleshProjectileEntity>create(SpawnGroup.MISC, RottenFleshProjectileEntity::new).dimensions(EntityDimensions.fixed(0.25F, 0.25F)).trackRangeBlocks(64).trackedUpdateRate(3).build());
        SKELETON_WOLF_REGISTRY_OBJECT = RegisterHelper.registerEntity(SKELETON_WOLF_REGISTRY_NAME, FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, SkeletonWolfEntity::new).dimensions(EntityDimensions.fixed(EntityType.WOLF.getWidth(), EntityType.WOLF.getHeight())).trackRangeBlocks(64).trackedUpdateRate(3).build());
        TROPICAL_SLIME_REGISTRY_OBJECT = RegisterHelper.registerEntity(TROPICAL_SLIME_REGISTRY_NAME, FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, TropicalSlimeEntity::new).dimensions(EntityDimensions.fixed(2.04F, 2.04F)).fireImmune().trackRangeBlocks(64).trackedUpdateRate(3).build());
        UMBRA_COW_REGISTRY_OBJECT = RegisterHelper.registerEntity(UMBRA_COW_REGISTRY_NAME, FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, UmbraCowEntity::new).dimensions(cowDimensions()).trackRangeBlocks(64).trackedUpdateRate(3).build());
        VILER_WITCH_REGISTRY_OBJECT = RegisterHelper.registerEntity(VILER_WITCH_REGISTRY_NAME, FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, VilerWitchEntity::new).dimensions(EntityDimensions.fixed(EntityType.WITCH.getWidth(), EntityType.WITCH.getHeight())).trackRangeBlocks(64).trackedUpdateRate(3).build());
        WOOLY_COW_REGISTRY_OBJECT = RegisterHelper.registerEntity(WOOLY_COW_REGISTRY_NAME, FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, WoolyCowEntity::new).dimensions(cowDimensions()).trackRangeBlocks(64).trackedUpdateRate(3).build());
    }

    private static EntityType<E2JBaseChickenEntity> registerBaseChickenEntity(String registryName) {
        EntityType<E2JBaseChickenEntity> entityType = FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, E2JBaseChickenEntity::new).dimensions(chickenDimensions()).trackRangeBlocks(64).trackedUpdateRate(3).build();
        return RegisterHelper.registerEntity(registryName, entityType);
    }

    private static EntityType<E2JBaseCowEntity> registerBaseCowEntity(String registryName) {
        EntityType<E2JBaseCowEntity> entityType = FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, E2JBaseCowEntity::new).dimensions(cowDimensions()).trackRangeBlocks(64).trackedUpdateRate(3).build();
        return RegisterHelper.registerEntity(registryName, entityType);
    }

    private static EntityType<E2JBasePigEntity> registerBasePigEntity(String registryName) {
        EntityType<E2JBasePigEntity> entityType = FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, E2JBasePigEntity::new).dimensions(pigDimensions()).trackRangeBlocks(64).trackedUpdateRate(3).build();
        return RegisterHelper.registerEntity(registryName, entityType);
    }

    private static EntityType<E2JBaseRabbitEntity> registerBaseRabbitEntity(String registryName) {
        EntityType<E2JBaseRabbitEntity> entityType = FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, E2JBaseRabbitEntity::new).dimensions(rabbitDimensions()).trackRangeBlocks(64).trackedUpdateRate(3).build();
        return RegisterHelper.registerEntity(registryName, entityType);
    }

    private static EntityType<E2JBaseMonoColorSheepEntity> registerBaseMonoColorSheepEntity(String registryName, ItemStack wool) {
        EntityType<E2JBaseMonoColorSheepEntity> entityType = FabricEntityTypeBuilder.<E2JBaseMonoColorSheepEntity>create(SpawnGroup.CREATURE, (type, world) -> new E2JBaseMonoColorSheepEntity(type, world, wool)).dimensions(sheepDimensions()).trackRangeBlocks(64).trackedUpdateRate(3).build();
        return RegisterHelper.registerEntity(registryName, entityType);
    }


    private static EntityDimensions chickenDimensions() {
        return EntityDimensions.fixed(EntityType.CHICKEN.getWidth(), EntityType.CHICKEN.getHeight());
    }

    private static EntityDimensions cowDimensions() {
        return EntityDimensions.fixed(EntityType.COW.getWidth(), EntityType.COW.getHeight());
    }

    private static EntityDimensions pigDimensions() {
        return EntityDimensions.fixed(EntityType.PIG.getWidth(), EntityType.PIG.getHeight());
    }

    private static EntityDimensions rabbitDimensions() {
        return EntityDimensions.fixed(EntityType.RABBIT.getWidth(), EntityType.RABBIT.getHeight());
    }

    private static EntityDimensions sheepDimensions() {
        return EntityDimensions.fixed(EntityType.SHEEP.getWidth(), EntityType.SHEEP.getHeight());
    }

}


