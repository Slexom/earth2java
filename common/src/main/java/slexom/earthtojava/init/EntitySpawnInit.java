package slexom.earthtojava.init;

import dev.architectury.registry.level.biome.BiomeModifications;
import dev.architectury.registry.registries.RegistrySupplier;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.SpawnSettings;
import slexom.earthtojava.config.ModConfig;
import slexom.earthtojava.mixins.SpawnRestrictionAccessor;
import slexom.earthtojava.utils.BiomeSpawnHelper;
import slexom.earthtojava.utils.EntitySpawnConfigHolder;

import java.util.Objects;
import java.util.function.Predicate;

public final class EntitySpawnInit {

    private static final ModConfig config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();

    public static void init() {
        manageAnimalEntities();
        manageMonsterEntities();
    }

    public static void initSpawnRestriction() {
        registerCreatureSpawnRestriction(EntityTypesInit.ALBINO_COW_REGISTRY_OBJECT);
        registerCreatureSpawnRestriction(EntityTypesInit.AMBER_CHICKEN_REGISTRY_OBJECT);
        registerCreatureSpawnRestriction(EntityTypesInit.ASHEN_COW_REGISTRY_OBJECT);
        registerCreatureSpawnRestriction(EntityTypesInit.BRONZED_CHICKEN_REGISTRY_OBJECT);
        registerCreatureSpawnRestriction(EntityTypesInit.BOLD_STRIPED_RABBIT_REGISTRY_OBJECT);
        registerCreatureSpawnRestriction(EntityTypesInit.COOKIE_COW_REGISTRY_OBJECT);
        registerCreatureSpawnRestriction(EntityTypesInit.CREAM_COW_REGISTRY_OBJECT);
        registerCreatureSpawnRestriction(EntityTypesInit.DAIRY_COW_REGISTRY_OBJECT);
        registerCreatureSpawnRestriction(EntityTypesInit.PINTO_COW_REGISTRY_OBJECT);
        registerCreatureSpawnRestriction(EntityTypesInit.CLUCKSHROOM_REGISTRY_OBJECT);
        registerCreatureSpawnRestriction(EntityTypesInit.FANCY_CHICKEN_REGISTRY_OBJECT);
        registerCreatureSpawnRestriction(EntityTypesInit.FLECKED_SHEEP_REGISTRY_OBJECT);
        registerCreatureSpawnRestriction(EntityTypesInit.FRECKLED_RABBIT_REGISTRY_OBJECT);
        registerCreatureSpawnRestriction(EntityTypesInit.FUZZY_SHEEP_REGISTRY_OBJECT);
        registerCreatureSpawnRestriction(EntityTypesInit.GOLD_CRESTED_CHICKEN_REGISTRY_OBJECT);
        registerCreatureSpawnRestriction(EntityTypesInit.HARELEQUIN_RABBIT_REGISTRY_OBJECT);
        registerCreatureSpawnRestriction(EntityTypesInit.HORNED_SHEEP_REGISTRY_OBJECT);
        registerCreatureSpawnRestriction(EntityTypesInit.INKY_SHEEP_REGISTRY_OBJECT);
        registerCreatureSpawnRestriction(EntityTypesInit.JOLLY_LLAMA_REGISTRY_OBJECT);
        registerCreatureSpawnRestriction(EntityTypesInit.JUMBO_RABBIT_REGISTRY_OBJECT);
        registerCreatureSpawnRestriction(EntityTypesInit.LONG_NOSED_SHEEP_REGISTRY_OBJECT);
        registerCreatureSpawnRestriction(EntityTypesInit.MIDNIGHT_CHICKEN_REGISTRY_OBJECT);
        registerCreatureSpawnRestriction(EntityTypesInit.MOOBLOOM_REGISTRY_OBJECT);
        registerCreatureSpawnRestriction(EntityTypesInit.MOOLIP_REGISTRY_OBJECT);
        registerCreatureSpawnRestriction(EntityTypesInit.MOTTLED_PIG_REGISTRY_OBJECT);
        registerCreatureSpawnRestriction(EntityTypesInit.MUDDY_PIG_REGISTRY_OBJECT);
        registerCreatureSpawnRestriction(EntityTypesInit.MUDDY_FOOT_RABBIT_REGISTRY_OBJECT);
        registerCreatureSpawnRestriction(EntityTypesInit.PALE_PIG_REGISTRY_OBJECT);
        registerCreatureSpawnRestriction(EntityTypesInit.PATCHED_SHEEP_REGISTRY_OBJECT);
        registerCreatureSpawnRestriction(EntityTypesInit.PIEBALD_PIG_REGISTRY_OBJECT);
        registerCreatureSpawnRestriction(EntityTypesInit.PINK_FOOTED_PIG_REGISTRY_OBJECT);
        registerCreatureSpawnRestriction(EntityTypesInit.RAINBOW_SHEEP_REGISTRY_OBJECT);
        registerCreatureSpawnRestriction(EntityTypesInit.ROCKY_SHEEP_REGISTRY_OBJECT);
        registerCreatureSpawnRestriction(EntityTypesInit.SKEWBALD_CHICKEN_REGISTRY_OBJECT);
        registerCreatureSpawnRestriction(EntityTypesInit.SOOTY_PIG_REGISTRY_OBJECT);
        registerCreatureSpawnRestriction(EntityTypesInit.SPOTTED_PIG_REGISTRY_OBJECT);
        registerCreatureSpawnRestriction(EntityTypesInit.STORMY_CHICKEN_REGISTRY_OBJECT);
        registerCreatureSpawnRestriction(EntityTypesInit.SUNSET_COW_REGISTRY_OBJECT);
        registerCreatureSpawnRestriction(EntityTypesInit.UMBRA_COW_REGISTRY_OBJECT);
        registerCreatureSpawnRestriction(EntityTypesInit.VESTED_RABBIT_REGISTRY_OBJECT);
        registerCreatureSpawnRestriction(EntityTypesInit.WOOLY_COW_REGISTRY_OBJECT);

        registerMonsterSpawnRestriction(EntityTypesInit.BONE_SPIDER_REGISTRY_OBJECT);
        registerMonsterSpawnRestriction(EntityTypesInit.BOULDERING_ZOMBIE_REGISTRY_OBJECT);
        registerMonsterSpawnRestriction(EntityTypesInit.LOBBER_ZOMBIE_REGISTRY_OBJECT);
        registerMonsterSpawnRestriction(EntityTypesInit.SKELETON_WOLF_REGISTRY_OBJECT);
        registerMonsterSpawnRestriction(EntityTypesInit.TROPICAL_SLIME_REGISTRY_OBJECT);
        registerMonsterSpawnRestriction(EntityTypesInit.VILER_WITCH_REGISTRY_OBJECT);
    }

    private static <T extends AnimalEntity> void registerCreatureSpawnRestriction(RegistrySupplier<EntityType<T>> entity) {
        SpawnRestrictionAccessor.callRegister(entity.get(), SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::isValidNaturalSpawn);
    }

    private static <T extends HostileEntity> void registerMonsterSpawnRestriction(RegistrySupplier<EntityType<T>> entity) {
        SpawnRestrictionAccessor.callRegister(entity.get(), SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, HostileEntity::canSpawnInDark);
    }

    private static void manageAnimalEntities() {
        // Chickens
        registerAnimalEntitySpawn(EntityTypesInit.AMBER_CHICKEN_REGISTRY_OBJECT, new EntitySpawnConfigHolder(BiomeSpawnHelper.AMBER_CHICKEN_SPAWN_BIOMES, config.amberChicken));
        registerAnimalEntitySpawn(EntityTypesInit.BRONZED_CHICKEN_REGISTRY_OBJECT, new EntitySpawnConfigHolder(BiomeSpawnHelper.BRONZED_CHICKEN_SPAWN_BIOMES, config.bronzedChicken));
        registerAnimalEntitySpawn(EntityTypesInit.FANCY_CHICKEN_REGISTRY_OBJECT, new EntitySpawnConfigHolder(BiomeSpawnHelper.FANCY_CHICKEN_SPAWN_BIOMES, config.fancyChicken));
        registerAnimalEntitySpawn(EntityTypesInit.GOLD_CRESTED_CHICKEN_REGISTRY_OBJECT, new EntitySpawnConfigHolder(BiomeSpawnHelper.GOLD_CRESTED_CHICKEN_SPAWN_BIOMES, config.goldCrestedChicken));
        registerAnimalEntitySpawn(EntityTypesInit.MIDNIGHT_CHICKEN_REGISTRY_OBJECT, new EntitySpawnConfigHolder(BiomeSpawnHelper.MIDNIGHT_CHICKEN_SPAWN_BIOMES, config.midnightChicken));
        registerAnimalEntitySpawn(EntityTypesInit.SKEWBALD_CHICKEN_REGISTRY_OBJECT, new EntitySpawnConfigHolder(BiomeSpawnHelper.SKEWBALD_CHICKEN_SPAWN_BIOMES, config.skewbaldChicken));
        registerAnimalEntitySpawn(EntityTypesInit.STORMY_CHICKEN_REGISTRY_OBJECT, new EntitySpawnConfigHolder(BiomeSpawnHelper.STORMY_CHICKEN_SPAWN_BIOMES, config.stormyChicken));

        // Cows
        registerAnimalEntitySpawn(EntityTypesInit.ALBINO_COW_REGISTRY_OBJECT, new EntitySpawnConfigHolder(BiomeSpawnHelper.ALBINO_COW_SPAWN_BIOMES, config.albinoCow));
        registerAnimalEntitySpawn(EntityTypesInit.ASHEN_COW_REGISTRY_OBJECT, new EntitySpawnConfigHolder(BiomeSpawnHelper.ASHEN_COW_SPAWN_BIOMES, config.ashenCow));
        registerAnimalEntitySpawn(EntityTypesInit.COOKIE_COW_REGISTRY_OBJECT, new EntitySpawnConfigHolder(BiomeSpawnHelper.COOKIE_COW_SPAWN_BIOMES, config.cookieCow));
        registerAnimalEntitySpawn(EntityTypesInit.CREAM_COW_REGISTRY_OBJECT, new EntitySpawnConfigHolder(BiomeSpawnHelper.CREAM_COW_SPAWN_BIOMES, config.creamCow));
        registerAnimalEntitySpawn(EntityTypesInit.DAIRY_COW_REGISTRY_OBJECT, new EntitySpawnConfigHolder(BiomeSpawnHelper.DAIRY_COW_SPAWN_BIOMES, config.dairyCow));
        registerAnimalEntitySpawn(EntityTypesInit.PINTO_COW_REGISTRY_OBJECT, new EntitySpawnConfigHolder(BiomeSpawnHelper.PINTO_COW_SPAWN_BIOMES, config.pintoCow));
        registerAnimalEntitySpawn(EntityTypesInit.SUNSET_COW_REGISTRY_OBJECT, new EntitySpawnConfigHolder(BiomeSpawnHelper.SUNSET_COW_SPAWN_BIOMES, config.sunsetCow));
        registerAnimalEntitySpawn(EntityTypesInit.UMBRA_COW_REGISTRY_OBJECT, new EntitySpawnConfigHolder(BiomeSpawnHelper.UMBRA_COW_SPAWN_BIOMES, config.umbraCow));
        registerAnimalEntitySpawn(EntityTypesInit.WOOLY_COW_REGISTRY_OBJECT, new EntitySpawnConfigHolder(BiomeSpawnHelper.WOOLY_COW_SPAWN_BIOMES, config.woolyCow));
        registerAnimalEntitySpawn(EntityTypesInit.CLUCKSHROOM_REGISTRY_OBJECT, new EntitySpawnConfigHolder(BiomeSpawnHelper.CLUCKSHROOM_SPAWN_BIOMES, config.cluckshroom));

        // Pigs
        registerAnimalEntitySpawn(EntityTypesInit.MOTTLED_PIG_REGISTRY_OBJECT, new EntitySpawnConfigHolder(BiomeSpawnHelper.MOTTLED_PIG_SPAWN_BIOMES, config.mottledPig));
        registerAnimalEntitySpawn(EntityTypesInit.MUDDY_PIG_REGISTRY_OBJECT, new EntitySpawnConfigHolder(BiomeSpawnHelper.MUDDY_PIG_SPAWN_BIOMES, config.muddyPig));
        registerAnimalEntitySpawn(EntityTypesInit.PALE_PIG_REGISTRY_OBJECT, new EntitySpawnConfigHolder(BiomeSpawnHelper.PALE_PIG_SPAWN_BIOMES, config.palePig));
        registerAnimalEntitySpawn(EntityTypesInit.PIEBALD_PIG_REGISTRY_OBJECT, new EntitySpawnConfigHolder(BiomeSpawnHelper.PIEBALD_PIG_SPAWN_BIOMES, config.piebaldPig));
        registerAnimalEntitySpawn(EntityTypesInit.PINK_FOOTED_PIG_REGISTRY_OBJECT, new EntitySpawnConfigHolder(BiomeSpawnHelper.PINK_FOOTED_PIG_SPAWN_BIOMES, config.pinkFootedPig));
        registerAnimalEntitySpawn(EntityTypesInit.SOOTY_PIG_REGISTRY_OBJECT, new EntitySpawnConfigHolder(BiomeSpawnHelper.SOOTY_PIG_SPAWN_BIOMES, config.sootyPig));
        registerAnimalEntitySpawn(EntityTypesInit.SPOTTED_PIG_REGISTRY_OBJECT, new EntitySpawnConfigHolder(BiomeSpawnHelper.SPOTTED_PIG_SPAWN_BIOMES, config.spottedPig));

        // Rabbits
        registerAnimalEntitySpawn(EntityTypesInit.BOLD_STRIPED_RABBIT_REGISTRY_OBJECT, new EntitySpawnConfigHolder(BiomeSpawnHelper.BOLD_STRIPED_RABBIT_SPAWN_BIOMES, config.boldStripedRabbit));
        registerAnimalEntitySpawn(EntityTypesInit.HARELEQUIN_RABBIT_REGISTRY_OBJECT, new EntitySpawnConfigHolder(BiomeSpawnHelper.HARELEQUIN_RABBIT_SPAWN_BIOMES, config.harelequinRabbit));
        registerAnimalEntitySpawn(EntityTypesInit.FRECKLED_RABBIT_REGISTRY_OBJECT, new EntitySpawnConfigHolder(BiomeSpawnHelper.FRECKLED_RABBIT_SPAWN_BIOMES, config.freckledRabbit));
        registerAnimalEntitySpawn(EntityTypesInit.JUMBO_RABBIT_REGISTRY_OBJECT, new EntitySpawnConfigHolder(BiomeSpawnHelper.JUMBO_RABBIT_SPAWN_BIOMES, config.jumboRabbit));
        registerAnimalEntitySpawn(EntityTypesInit.MUDDY_FOOT_RABBIT_REGISTRY_OBJECT, new EntitySpawnConfigHolder(BiomeSpawnHelper.MUDDY_FOOT_RABBIT_SPAWN_BIOMES, config.muddyFootRabbit));
        registerAnimalEntitySpawn(EntityTypesInit.VESTED_RABBIT_REGISTRY_OBJECT, new EntitySpawnConfigHolder(BiomeSpawnHelper.VESTED_RABBIT_SPAWN_BIOMES, config.vestedRabbit));

        // Sheeps
        registerAnimalEntitySpawn(EntityTypesInit.FLECKED_SHEEP_REGISTRY_OBJECT, new EntitySpawnConfigHolder(BiomeSpawnHelper.FLECKED_SHEEP_SPAWN_BIOMES, config.fleckedSheep));
        registerAnimalEntitySpawn(EntityTypesInit.PATCHED_SHEEP_REGISTRY_OBJECT, new EntitySpawnConfigHolder(BiomeSpawnHelper.PATCHED_SHEEP_SPAWN_BIOMES, config.patchedSheep));
        registerAnimalEntitySpawn(EntityTypesInit.RAINBOW_SHEEP_REGISTRY_OBJECT, new EntitySpawnConfigHolder(BiomeSpawnHelper.RAINBOW_SHEEP_SPAWN_BIOMES, config.rainbowSheep));
        registerAnimalEntitySpawn(EntityTypesInit.ROCKY_SHEEP_REGISTRY_OBJECT, new EntitySpawnConfigHolder(BiomeSpawnHelper.ROCKY_SHEEP_SPAWN_BIOMES, config.rockySheep));
        registerAnimalEntitySpawn(EntityTypesInit.FUZZY_SHEEP_REGISTRY_OBJECT, new EntitySpawnConfigHolder(BiomeSpawnHelper.FUZZY_SHEEP_SPAWN_BIOMES, config.fuzzySheep));
        registerAnimalEntitySpawn(EntityTypesInit.HORNED_SHEEP_REGISTRY_OBJECT, new EntitySpawnConfigHolder(BiomeSpawnHelper.HORNED_SHEEP_SPAWN_BIOMES, config.hornedSheep));
        registerAnimalEntitySpawn(EntityTypesInit.INKY_SHEEP_REGISTRY_OBJECT, new EntitySpawnConfigHolder(BiomeSpawnHelper.INKY_SHEEP_SPAWN_BIOMES, config.inkySheep));
        registerAnimalEntitySpawn(EntityTypesInit.LONG_NOSED_SHEEP_REGISTRY_OBJECT, new EntitySpawnConfigHolder(BiomeSpawnHelper.LONG_NOSED_SHEEP_SPAWN_BIOMES, config.longNosedSheep));

        // Other
        registerAnimalEntitySpawn(EntityTypesInit.JOLLY_LLAMA_REGISTRY_OBJECT, new EntitySpawnConfigHolder(BiomeSpawnHelper.JOLLY_LLAMA_SPAWN_BIOMES, config.jollyLlama));
        addFlowerCowsToBiomes();
    }

    private static void manageMonsterEntities() {
        registerMonsterEntitySpawn(EntityTypesInit.BONE_SPIDER_REGISTRY_OBJECT, new EntitySpawnConfigHolder(BiomeSpawnHelper.BONE_SPIDER_SPAWN_BIOMES, config.boneSpider));
        registerMonsterEntitySpawn(EntityTypesInit.BOULDERING_ZOMBIE_REGISTRY_OBJECT, new EntitySpawnConfigHolder(BiomeSpawnHelper.BOULDERING_ZOMBIE_SPAWN_BIOMES, config.boulderingZombie));
        registerMonsterEntitySpawn(EntityTypesInit.LOBBER_ZOMBIE_REGISTRY_OBJECT, new EntitySpawnConfigHolder(BiomeSpawnHelper.LOBBER_ZOMBIE_SPAWN_BIOMES, config.lobberZombie));
        registerMonsterEntitySpawn(EntityTypesInit.SKELETON_WOLF_REGISTRY_OBJECT, new EntitySpawnConfigHolder(BiomeSpawnHelper.SKELETON_WOLF_SPAWN_BIOMES, config.skeletonWolf));
        registerMonsterEntitySpawn(EntityTypesInit.TROPICAL_SLIME_REGISTRY_OBJECT, new EntitySpawnConfigHolder(BiomeSpawnHelper.TROPICAL_SLIME_SPAWN_BIOMES, config.tropicalSlime));
        registerMonsterEntitySpawn(EntityTypesInit.VILER_WITCH_REGISTRY_OBJECT, new EntitySpawnConfigHolder(BiomeSpawnHelper.VILER_WITCH_SPAWN_BIOMES, config.vilerWitch));
    }

    private static <T extends AnimalEntity> void registerAnimalEntitySpawn(RegistrySupplier<EntityType<T>> entity, EntitySpawnConfigHolder configHolder) {
        ModConfig.EntityConfig modConfig = configHolder.config();
        if (modConfig.spawn) {
            BiomeSpawnHelper.setCreatureSpawnBiomes(entity, configHolder.biomeCategories(), modConfig.weight, modConfig.groupMin, modConfig.groupMax);
        }
    }

    private static <T extends HostileEntity> void registerMonsterEntitySpawn(RegistrySupplier<EntityType<T>> entity, EntitySpawnConfigHolder configHolder) {
        ModConfig.EntityConfig modConfig = configHolder.config();
        if (modConfig.spawn) {
            BiomeSpawnHelper.setMonsterSpawnBiomes(entity, configHolder.biomeCategories(), modConfig.weight, modConfig.groupMin, modConfig.groupMax);
        }
    }

    private static <T extends Entity> void addFlowerCowsToBiomes() {
        Predicate<BiomeModifications.BiomeContext> flowerForestPredicate = (ctx) -> Objects.equals(ctx.getKey(), BiomeKeys.FLOWER_FOREST.getValue());
        Predicate<BiomeModifications.BiomeContext> meadowPredicate = (ctx) -> Objects.equals(ctx.getKey(), BiomeKeys.MEADOW.getValue());
        if (config.moobloom.spawn) {
            BiomeModifications.addProperties(flowerForestPredicate, (biomeContext, mutable) -> {
                SpawnSettings.SpawnEntry mooblomSpawnEntry = new SpawnSettings.SpawnEntry(EntityTypesInit.MOOBLOOM_REGISTRY_OBJECT.get(), config.moobloom.weight, config.moobloom.groupMin, config.moobloom.groupMax);
                SpawnSettings.SpawnEntry moolipSpawnEntry = new SpawnSettings.SpawnEntry(EntityTypesInit.MOOLIP_REGISTRY_OBJECT.get(), config.moobloom.weight, config.moobloom.groupMin, config.moobloom.groupMax);
                mutable.getSpawnProperties().addSpawn(SpawnGroup.CREATURE, mooblomSpawnEntry);
                mutable.getSpawnProperties().addSpawn(SpawnGroup.CREATURE, moolipSpawnEntry);
            });
        }
        if (config.moolip.spawn) {
            BiomeModifications.addProperties(meadowPredicate, (biomeContext, mutable) -> {
                SpawnSettings.SpawnEntry mooblomSpawnEntry = new SpawnSettings.SpawnEntry(EntityTypesInit.MOOBLOOM_REGISTRY_OBJECT.get(), config.moolip.weight, config.moolip.groupMin, config.moolip.groupMax);
                SpawnSettings.SpawnEntry moolipSpawnEntry = new SpawnSettings.SpawnEntry(EntityTypesInit.MOOLIP_REGISTRY_OBJECT.get(), config.moolip.weight, config.moolip.groupMin, config.moolip.groupMax);
                mutable.getSpawnProperties().addSpawn(SpawnGroup.CREATURE, mooblomSpawnEntry);
                mutable.getSpawnProperties().addSpawn(SpawnGroup.CREATURE, moolipSpawnEntry);
            });
        }
    }
}
