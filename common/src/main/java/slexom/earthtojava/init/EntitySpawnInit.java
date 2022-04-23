package slexom.earthtojava.init;

import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.world.Heightmap;
import slexom.earthtojava.config.ModConfig;
import slexom.earthtojava.mixins.SpawnRestrictionAccessor;
import slexom.earthtojava.utils.BiomeSpawnHelper;

public class EntitySpawnInit {

    private static final ModConfig config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();

    public static void init() {
        manageAnimalEntities();
        manageMonsterEntities();
    }

    private static void manageAnimalEntities() {
        registerAnimalEntitySpawn(EntityTypesInit.ALBINO_COW_REGISTRY_OBJECT.get(), config.albinoCow);
        registerAnimalEntitySpawn(EntityTypesInit.AMBER_CHICKEN_REGISTRY_OBJECT.get(), config.amberChicken);
        registerAnimalEntitySpawn(EntityTypesInit.ASHEN_COW_REGISTRY_OBJECT.get(), config.ashenCow);
        registerAnimalEntitySpawn(EntityTypesInit.BRONZED_CHICKEN_REGISTRY_OBJECT.get(), config.bronzedChicken);
        registerAnimalEntitySpawn(EntityTypesInit.BOLD_STRIPED_RABBIT_REGISTRY_OBJECT.get(), config.boldStripedRabbit);
        registerAnimalEntitySpawn(EntityTypesInit.COOKIE_COW_REGISTRY_OBJECT.get(), config.cookieCow);
        registerAnimalEntitySpawn(EntityTypesInit.CREAM_COW_REGISTRY_OBJECT.get(), config.creamCow);
        registerAnimalEntitySpawn(EntityTypesInit.DAIRY_COW_REGISTRY_OBJECT.get(), config.dairyCow);
        registerAnimalEntitySpawn(EntityTypesInit.PINTO_COW_REGISTRY_OBJECT.get(), config.pintoCow);
        registerAnimalEntitySpawn(EntityTypesInit.CLUCKSHROOM_REGISTRY_OBJECT.get(), config.cluckshroom);
        registerAnimalEntitySpawn(EntityTypesInit.FANCY_CHICKEN_REGISTRY_OBJECT.get(), config.fancyChicken);
        registerAnimalEntitySpawn(EntityTypesInit.FLECKED_SHEEP_REGISTRY_OBJECT.get(), config.fleckedSheep);
        registerAnimalEntitySpawn(EntityTypesInit.FRECKLED_RABBIT_REGISTRY_OBJECT.get(), config.freckledRabbit);
        registerAnimalEntitySpawn(EntityTypesInit.FUZZY_SHEEP_REGISTRY_OBJECT.get(), config.fuzzySheep);
        registerAnimalEntitySpawn(EntityTypesInit.GOLD_CRESTED_CHICKEN_REGISTRY_OBJECT.get(), config.goldCrestedChicken);
        registerAnimalEntitySpawn(EntityTypesInit.HARELEQUIN_RABBIT_REGISTRY_OBJECT.get(), config.harelequinRabbit);
        registerAnimalEntitySpawn(EntityTypesInit.HORNED_SHEEP_REGISTRY_OBJECT.get(), config.hornedSheep);
        registerAnimalEntitySpawn(EntityTypesInit.INKY_SHEEP_REGISTRY_OBJECT.get(), config.inkySheep);
        registerAnimalEntitySpawn(EntityTypesInit.JOLLY_LLAMA_REGISTRY_OBJECT.get(), config.jollyLlama);
        registerAnimalEntitySpawn(EntityTypesInit.JUMBO_RABBIT_REGISTRY_OBJECT.get(), config.jumboRabbit);
        registerAnimalEntitySpawn(EntityTypesInit.LONG_NOSED_SHEEP_REGISTRY_OBJECT.get(), config.longNosedSheep);
        registerAnimalEntitySpawn(EntityTypesInit.MIDNIGHT_CHICKEN_REGISTRY_OBJECT.get(), config.midnightChicken);
        registerAnimalEntitySpawn(EntityTypesInit.MOOBLOOM_REGISTRY_OBJECT.get(), config.moobloom);
        registerAnimalEntitySpawn(EntityTypesInit.MOOLIP_REGISTRY_OBJECT.get(), config.moolip);
        registerAnimalEntitySpawn(EntityTypesInit.MOTTLED_PIG_REGISTRY_OBJECT.get(), config.mottledPig);
        registerAnimalEntitySpawn(EntityTypesInit.MUDDY_PIG_REGISTRY_OBJECT.get(), config.muddyPig);
        registerAnimalEntitySpawn(EntityTypesInit.MUDDY_FOOT_RABBIT_REGISTRY_OBJECT.get(), config.muddyFootRabbit);
        registerAnimalEntitySpawn(EntityTypesInit.PALE_PIG_REGISTRY_OBJECT.get(), config.palePig);
        registerAnimalEntitySpawn(EntityTypesInit.PATCHED_SHEEP_REGISTRY_OBJECT.get(), config.patchedSheep);
        registerAnimalEntitySpawn(EntityTypesInit.PIEBALD_PIG_REGISTRY_OBJECT.get(), config.piebaldPig);
        registerAnimalEntitySpawn(EntityTypesInit.PINK_FOOTED_PIG_REGISTRY_OBJECT.get(), config.pinkFootedPig);
        registerAnimalEntitySpawn(EntityTypesInit.RAINBOW_SHEEP_REGISTRY_OBJECT.get(), config.rainbowSheep);
        registerAnimalEntitySpawn(EntityTypesInit.ROCKY_SHEEP_REGISTRY_OBJECT.get(), config.rockySheep);
        registerAnimalEntitySpawn(EntityTypesInit.SKEWBALD_CHICKEN_REGISTRY_OBJECT.get(), config.skewbaldChicken);
        registerAnimalEntitySpawn(EntityTypesInit.SOOTY_PIG_REGISTRY_OBJECT.get(), config.sootyPig);
        registerAnimalEntitySpawn(EntityTypesInit.SPOTTED_PIG_REGISTRY_OBJECT.get(), config.spottedPig);
        registerAnimalEntitySpawn(EntityTypesInit.STORMY_CHICKEN_REGISTRY_OBJECT.get(), config.stormyChicken);
        registerAnimalEntitySpawn(EntityTypesInit.SUNSET_COW_REGISTRY_OBJECT.get(), config.sunsetCow);
        registerAnimalEntitySpawn(EntityTypesInit.UMBRA_COW_REGISTRY_OBJECT.get(), config.umbraCow);
        registerAnimalEntitySpawn(EntityTypesInit.VESTED_RABBIT_REGISTRY_OBJECT.get(), config.vestedRabbit);
        registerAnimalEntitySpawn(EntityTypesInit.WOOLY_COW_REGISTRY_OBJECT.get(), config.woolyCow);
    }

    private static void manageMonsterEntities() {
        registerMonsterEntitySpawn(EntityTypesInit.BONE_SPIDER_REGISTRY_OBJECT.get(), config.boneSpider);
        registerMonsterEntitySpawn(EntityTypesInit.BOULDERING_ZOMBIE_REGISTRY_OBJECT.get(), config.boulderingZombie);
        registerMonsterEntitySpawn(EntityTypesInit.LOBBER_ZOMBIE_REGISTRY_OBJECT.get(), config.lobberZombie);
        registerMonsterEntitySpawn(EntityTypesInit.SKELETON_WOLF_REGISTRY_OBJECT.get(), config.skeletonWolf);
        registerMonsterEntitySpawn(EntityTypesInit.TROPICAL_SLIME_REGISTRY_OBJECT.get(), config.tropicalSlime);
        registerMonsterEntitySpawn(EntityTypesInit.VILER_WITCH_REGISTRY_OBJECT.get(), config.vilerWitch);

        // EXPERIMENTAL
        // BiomeSpawnHelper.autoSpawn(EntityTypesInit.BOULDERING_ZOMBIE_REGISTRY_OBJECT.get(), EntityType.ZOMBIE);
    }

    private static <T extends AnimalEntity> void registerAnimalEntitySpawn(EntityType<T> entity, String[] spawnBiomes, ModConfig.EntityConfig config) {
        if (config.spawn) {
            BiomeSpawnHelper.setCreatureSpawnBiomes(entity, spawnBiomes, config.weight, config.groupMin, config.groupMax);
            SpawnRestrictionAccessor.callRegister(entity, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::isValidNaturalSpawn);
        }
    }

    private static <T extends AnimalEntity> void registerAnimalEntitySpawn(EntityType<T> entity, ModConfig.EntityConfig entityConfig) {
        registerAnimalEntitySpawn(entity, entityConfig.spawnBiomes.toArray(new String[0]), entityConfig);
    }

    private static <T extends HostileEntity> void registerMonsterEntitySpawn(EntityType<T> entity, String[] spawnBiomes, ModConfig.EntityConfig config) {
        if (config.spawn) {
            BiomeSpawnHelper.setMonsterSpawnBiomes(entity, spawnBiomes, config.weight, config.groupMin, config.groupMax);
            SpawnRestrictionAccessor.callRegister(entity, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, HostileEntity::canSpawnInDark);
        }
    }

    private static <T extends HostileEntity> void registerMonsterEntitySpawn(EntityType<T> entity, ModConfig.EntityConfig entityConfig) {
        registerMonsterEntitySpawn(entity, entityConfig.spawnBiomes.toArray(new String[0]), entityConfig);
    }

}
