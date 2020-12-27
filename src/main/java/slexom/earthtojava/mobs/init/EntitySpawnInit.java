package slexom.earthtojava.mobs.init;

import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import net.fabricmc.fabric.mixin.object.builder.SpawnRestrictionAccessor;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.world.Heightmap;
import slexom.earthtojava.mobs.config.ModConfig;
import slexom.earthtojava.mobs.entity.passive.GlowSquidEntity;
import slexom.earthtojava.mobs.utils.BiomeSpawnHelper;

public class EntitySpawnInit {

    private static final ModConfig config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();

    public static void init() {
        manageAnimalEntities();
        manageMobEntities();
        manageMonsterEntities();
        manageWaterEntities();
    }

    private static void manageAnimalEntities() {
        registerAnimalEntitySpawn(EntityTypesInit.ALBINO_COW_REGISTRY_OBJECT, config.albinoCow.spawnBiomes.toArray(new String[0]), config.albinoCow);
        registerAnimalEntitySpawn(EntityTypesInit.AMBER_CHICKEN_REGISTRY_OBJECT, config.amberChicken.spawnBiomes.toArray(new String[0]), config.amberChicken);
        registerAnimalEntitySpawn(EntityTypesInit.ASHEN_COW_REGISTRY_OBJECT, config.ashenCow.spawnBiomes.toArray(new String[0]), config.ashenCow);
        registerAnimalEntitySpawn(EntityTypesInit.BRONZED_CHICKEN_REGISTRY_OBJECT, config.bronzedChicken);
        registerAnimalEntitySpawn(EntityTypesInit.BOLD_STRIPED_RABBIT_REGISTRY_OBJECT, config.boldStripedRabbit);
        registerAnimalEntitySpawn(EntityTypesInit.COOKIE_COW_REGISTRY_OBJECT, config.cookieCow);
        registerAnimalEntitySpawn(EntityTypesInit.PINTO_COW_REGISTRY_OBJECT, config.pintoCow);
        // registerAnimalEntitySpawn(EntityTypesInit.CLUCKSHROOM_REGISTRY_OBJECT, config.cluckshroom.spawnBiomes.toArray(new String[0]), config.cluckshroom);
        // registerAnimalEntitySpawn(EntityTypesInit.FANCY_CHICKEN_REGISTRY_OBJECT, config.fancyChicken);
        registerAnimalEntitySpawn(EntityTypesInit.FLECKED_SHEEP_REGISTRY_OBJECT, config.fleckedSheep.spawnBiomes.toArray(new String[0]), config.fleckedSheep);
        registerAnimalEntitySpawn(EntityTypesInit.HARELEQUIN_RABBIT_REGISTRY_OBJECT, config.harelequinRabbit.spawnBiomes.toArray(new String[0]), config.harelequinRabbit);
//        registerAnimalEntitySpawn(EntityTypesInit.HORNED_SHEEP_REGISTRY_OBJECT, config.hornedSheep.spawnBiomes.toArray(new String[0]), config.hornedSheep);
        registerAnimalEntitySpawn(EntityTypesInit.INKY_SHEEP_REGISTRY_OBJECT, config.inkySheep.spawnBiomes.toArray(new String[0]), config.inkySheep);
//        registerAnimalEntitySpawn(EntityTypesInit.JOLLY_LLAMA_REGISTRY_OBJECT, config.jollyLlama.spawnBiomes.toArray(new String[0]), config.jollyLlama);
//        registerAnimalEntitySpawn(EntityTypesInit.JUMBO_RABBIT_REGISTRY_OBJECT, config.jumboRabbit.spawnBiomes.toArray(new String[0]), config.jumboRabbit);
        registerAnimalEntitySpawn(EntityTypesInit.MIDNIGHT_CHICKEN_REGISTRY_OBJECT, config.midnightChicken.spawnBiomes.toArray(new String[0]), config.midnightChicken);
//        registerAnimalEntitySpawn(EntityTypesInit.MOOBLOOM_REGISTRY_OBJECT, config.moobloom.spawnBiomes.toArray(new String[0]), config.moobloom);
//        registerAnimalEntitySpawn(EntityTypesInit.MOOLIP_REGISTRY_OBJECT, config.moolip);
//        registerAnimalEntitySpawn(EntityTypesInit.MUDDY_PIG_REGISTRY_OBJECT, config.muddyPig.spawnBiomes.toArray(new String[0]), config.muddyPig);
        registerAnimalEntitySpawn(EntityTypesInit.MUDDY_FOOT_RABBIT_REGISTRY_OBJECT, config.muddyFootRabbit.spawnBiomes.toArray(new String[0]), config.muddyFootRabbit);
        registerAnimalEntitySpawn(EntityTypesInit.PALE_PIG_REGISTRY_OBJECT, config.palePig.spawnBiomes.toArray(new String[0]), config.palePig);
        registerAnimalEntitySpawn(EntityTypesInit.PATCHED_SHEEP_REGISTRY_OBJECT, config.patchedSheep);
        registerAnimalEntitySpawn(EntityTypesInit.PIEBALD_PIG_REGISTRY_OBJECT, config.piebaldPig.spawnBiomes.toArray(new String[0]), config.piebaldPig);
        registerAnimalEntitySpawn(EntityTypesInit.PINK_FOOTED_PIG_REGISTRY_OBJECT, config.pinkFootedPig.spawnBiomes.toArray(new String[0]), config.pinkFootedPig);
       //  registerAnimalEntitySpawn(EntityTypesInit.RAINBOW_SHEEP_REGISTRY_OBJECT, config.rainbowSheep.spawnBiomes.toArray(new String[0]), config.rainbowSheep);
        registerAnimalEntitySpawn(EntityTypesInit.ROCKY_SHEEP_REGISTRY_OBJECT, config.rockySheep.spawnBiomes.toArray(new String[0]), config.rockySheep);
        registerAnimalEntitySpawn(EntityTypesInit.SKEWBALD_CHICKEN_REGISTRY_OBJECT, config.skewbaldChicken);
        registerAnimalEntitySpawn(EntityTypesInit.SPOTTED_PIG_REGISTRY_OBJECT, config.spottedPig.spawnBiomes.toArray(new String[0]), config.spottedPig);
        registerAnimalEntitySpawn(EntityTypesInit.STORMY_CHICKEN_REGISTRY_OBJECT, config.stormyChicken.spawnBiomes.toArray(new String[0]), config.stormyChicken);
        registerAnimalEntitySpawn(EntityTypesInit.SUNSET_COW_REGISTRY_OBJECT, config.sunsetCow.spawnBiomes.toArray(new String[0]), config.sunsetCow);
        registerAnimalEntitySpawn(EntityTypesInit.VESTED_RABBIT_REGISTRY_OBJECT, config.vestedRabbit.spawnBiomes.toArray(new String[0]), config.vestedRabbit);
//        registerAnimalEntitySpawn(EntityTypesInit.WOOLY_COW_REGISTRY_OBJECT, config.woolyCow.spawnBiomes.toArray(new String[0]), config.woolyCow);
    }

    private static void manageMonsterEntities() {
//        registerMonsterEntitySpawn(EntityTypesInit.BONE_SPIDER_REGISTRY_OBJECT, config.boneSpider.spawnBiomes.toArray(new String[0]), config.boneSpider);
//        registerMonsterEntitySpawn(EntityTypesInit.SKELETON_WOLF_REGISTRY_OBJECT, config.skeletonWolf.spawnBiomes.toArray(new String[0]), config.skeletonWolf);
//        registerMonsterEntitySpawn(EntityTypesInit.TROPICAL_SLIME_REGISTRY_OBJECT, config.tropicalSlime.spawnBiomes.toArray(new String[0]), config.tropicalSlime);
//        registerMonsterEntitySpawn(EntityTypesInit.BOULDERING_ZOMBIE_REGISTRY_OBJECT, config.boulderingZombie);
//        registerMonsterEntitySpawn(EntityTypesInit.LOBBER_ZOMBIE_REGISTRY_OBJECT, config.lobberZombie);

        // EXPERIMENTAL
        // BiomeSpawnHelper.autoSpawn(EntityTypesInit.BOULDERING_ZOMBIE_REGISTRY_OBJECT, EntityType.ZOMBIE);
    }

    private static void manageMobEntities() {
        // registerMobEntitySpawn(EntityTypesInit.FURNACE_GOLEM_REGISTRY_OBJECT, config.furnaceGolem.spawnBiomes.toArray(new String[0]), config.furnaceGolem);
        // registerMobEntitySpawn(EntityTypesInit.MELON_GOLEM_REGISTRY_OBJECT, config.melonGolem.spawnBiomes.toArray(new String[0]), config.melonGolem);
    }

    private static void manageWaterEntities() {
        registerGlowingSquidSpawn();
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

    private static <T extends MobEntity> void registerMobEntitySpawn(EntityType<T> entity, String[] spawnBiomes, ModConfig.EntityConfig config) {
        if (config.spawn) {
            BiomeSpawnHelper.setMobSpawnBiomes(entity, spawnBiomes, config.weight, config.groupMin, config.groupMax);
            SpawnRestrictionAccessor.callRegister(entity, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MobEntity::canMobSpawn);
        }
    }

    private static <T extends MobEntity> void registerMobEntitySpawn(EntityType<T> entity, ModConfig.EntityConfig entityConfig) {
        registerMobEntitySpawn(entity, entityConfig.spawnBiomes.toArray(new String[0]), entityConfig);
    }

    private static void registerGlowingSquidSpawn() {
//        BiomeSpawnHelper.setWaterCreatureSpawnBiomes(EntityTypesInit.GLOW_SQUID_REGISTRY_OBJECT, config.glowSquid.spawnBiomes.toArray(new String[0]), config.glowSquid.weight, config.glowSquid.groupMin, config.glowSquid.groupMax);
//        SpawnRestrictionAccessor.callRegister(EntityTypesInit.GLOW_SQUID_REGISTRY_OBJECT, SpawnRestriction.Location.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, GlowSquidEntity::canGlowingSquidSpawn);

    }
}
