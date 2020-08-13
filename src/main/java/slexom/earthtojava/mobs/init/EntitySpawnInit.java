package slexom.earthtojava.mobs.init;

import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.world.Heightmap;
import slexom.earthtojava.mobs.config.ModConfig;
import slexom.earthtojava.mobs.entity.passive.GlowSquidEntity;
import slexom.earthtojava.mobs.mixins.SpawnRestrictionAccessor;
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
        registerAnimalEntitySpawn(EntityTypesInit.ALBINO_COW_REGISTRY_OBJECT, config.albinoCow.spawnBiomes.toArray(new String[0]), config.albinoCow.weight, config.albinoCow.groupMin, config.albinoCow.groupMax);
        registerAnimalEntitySpawn(EntityTypesInit.AMBER_CHICKEN_REGISTRY_OBJECT, config.amberChicken.spawnBiomes.toArray(new String[0]), config.amberChicken.weight, config.amberChicken.groupMin, config.amberChicken.groupMax);
        registerAnimalEntitySpawn(EntityTypesInit.ASHEN_COW_REGISTRY_OBJECT, config.ashenCow.spawnBiomes.toArray(new String[0]), config.ashenCow.weight, config.ashenCow.groupMin, config.ashenCow.groupMax);
        registerAnimalEntitySpawn(EntityTypesInit.BRONZED_CHICKEN_REGISTRY_OBJECT, config.bronzedChicken);
        registerAnimalEntitySpawn(EntityTypesInit.CLUCKSHROOM_REGISTRY_OBJECT, config.cluckshroom.spawnBiomes.toArray(new String[0]), config.cluckshroom.weight, config.cluckshroom.groupMin, config.cluckshroom.groupMax);
        registerAnimalEntitySpawn(EntityTypesInit.FLECKED_SHEEP_REGISTRY_OBJECT, config.fleckedSheep.spawnBiomes.toArray(new String[0]), config.fleckedSheep.weight, config.fleckedSheep.groupMin, config.fleckedSheep.groupMax);
        registerAnimalEntitySpawn(EntityTypesInit.HARELEQUIN_RABBIT_REGISTRY_OBJECT, config.harelequinRabbit.spawnBiomes.toArray(new String[0]), config.harelequinRabbit.weight, config.harelequinRabbit.groupMin, config.harelequinRabbit.groupMax);
        registerAnimalEntitySpawn(EntityTypesInit.HORNED_SHEEP_REGISTRY_OBJECT, config.hornedSheep.spawnBiomes.toArray(new String[0]), config.hornedSheep.weight, config.hornedSheep.groupMin, config.hornedSheep.groupMax);
        registerAnimalEntitySpawn(EntityTypesInit.INKY_SHEEP_REGISTRY_OBJECT, config.inkySheep.spawnBiomes.toArray(new String[0]), config.inkySheep.weight, config.inkySheep.groupMin, config.inkySheep.groupMax);
        registerAnimalEntitySpawn(EntityTypesInit.JOLLY_LLAMA_REGISTRY_OBJECT, config.jollyLlama.spawnBiomes.toArray(new String[0]), config.jollyLlama.weight, config.jollyLlama.groupMin, config.jollyLlama.groupMax);
        registerAnimalEntitySpawn(EntityTypesInit.JUMBO_RABBIT_REGISTRY_OBJECT, config.jumboRabbit.spawnBiomes.toArray(new String[0]), config.jumboRabbit.weight, config.jumboRabbit.groupMin, config.jumboRabbit.groupMax);
        registerAnimalEntitySpawn(EntityTypesInit.MIDNIGHT_CHICKEN_REGISTRY_OBJECT, config.midnightChicken.spawnBiomes.toArray(new String[0]), config.midnightChicken.weight, config.midnightChicken.groupMin, config.midnightChicken.groupMax);
        registerAnimalEntitySpawn(EntityTypesInit.MOOBLOOM_REGISTRY_OBJECT, config.moobloom.spawnBiomes.toArray(new String[0]), config.moobloom.weight, config.moobloom.groupMin, config.moobloom.groupMax);
        registerAnimalEntitySpawn(EntityTypesInit.MUDDY_PIG_REGISTRY_OBJECT, config.muddyPig.spawnBiomes.toArray(new String[0]), config.muddyPig.weight, config.muddyPig.groupMin, config.muddyPig.groupMax);
        registerAnimalEntitySpawn(EntityTypesInit.MUDDY_FOOT_RABBIT_REGISTRY_OBJECT, config.muddyFootRabbit.spawnBiomes.toArray(new String[0]), config.muddyFootRabbit.weight, config.muddyFootRabbit.groupMin, config.muddyFootRabbit.groupMax);
        registerAnimalEntitySpawn(EntityTypesInit.PALE_PIG_REGISTRY_OBJECT, config.palePig.spawnBiomes.toArray(new String[0]), config.palePig.weight, config.palePig.groupMin, config.palePig.groupMax);
        registerAnimalEntitySpawn(EntityTypesInit.PIEBALD_PIG_REGISTRY_OBJECT, config.piebaldPig.spawnBiomes.toArray(new String[0]), config.piebaldPig.weight, config.piebaldPig.groupMin, config.piebaldPig.groupMax);
        registerAnimalEntitySpawn(EntityTypesInit.PINK_FOOTED_PIG_REGISTRY_OBJECT, config.pinkFootedPig.spawnBiomes.toArray(new String[0]), config.pinkFootedPig.weight, config.pinkFootedPig.groupMin, config.pinkFootedPig.groupMax);
        registerAnimalEntitySpawn(EntityTypesInit.RAINBOW_SHEEP_REGISTRY_OBJECT, config.rainbowSheep.spawnBiomes.toArray(new String[0]), config.rainbowSheep.weight, config.rainbowSheep.groupMin, config.rainbowSheep.groupMax);
        registerAnimalEntitySpawn(EntityTypesInit.ROCKY_SHEEP_REGISTRY_OBJECT, config.rockySheep.spawnBiomes.toArray(new String[0]), config.rockySheep.weight, config.rockySheep.groupMin, config.rockySheep.groupMax);
        registerAnimalEntitySpawn(EntityTypesInit.SPOTTED_PIG_REGISTRY_OBJECT, config.spottedPig.spawnBiomes.toArray(new String[0]), config.spottedPig.weight, config.spottedPig.groupMin, config.spottedPig.groupMax);
        registerAnimalEntitySpawn(EntityTypesInit.STORMY_CHICKEN_REGISTRY_OBJECT, config.stormyChicken.spawnBiomes.toArray(new String[0]), config.stormyChicken.weight, config.stormyChicken.groupMin, config.stormyChicken.groupMax);
        registerAnimalEntitySpawn(EntityTypesInit.SUNSET_COW_REGISTRY_OBJECT, config.sunsetCow.spawnBiomes.toArray(new String[0]), config.sunsetCow.weight, config.sunsetCow.groupMin, config.sunsetCow.groupMax);
        registerAnimalEntitySpawn(EntityTypesInit.VESTED_RABBIT_REGISTRY_OBJECT, config.vestedRabbit.spawnBiomes.toArray(new String[0]), config.vestedRabbit.weight, config.vestedRabbit.groupMin, config.vestedRabbit.groupMax);
        registerAnimalEntitySpawn(EntityTypesInit.WOOLY_COW_REGISTRY_OBJECT, config.woolyCow.spawnBiomes.toArray(new String[0]), config.woolyCow.weight, config.woolyCow.groupMin, config.woolyCow.groupMax);
    }

    private static void manageMonsterEntities() {
        registerMonsterEntitySpawn(EntityTypesInit.BONE_SPIDER_REGISTRY_OBJECT, config.boneSpider.spawnBiomes.toArray(new String[0]), config.boneSpider.weight, config.boneSpider.groupMin, config.boneSpider.groupMax);
        registerMonsterEntitySpawn(EntityTypesInit.SKELETON_WOLF_REGISTRY_OBJECT, config.skeletonWolf.spawnBiomes.toArray(new String[0]), config.skeletonWolf.weight, config.skeletonWolf.groupMin, config.skeletonWolf.groupMax);
    }

    private static void manageMobEntities() {
        registerMobEntitySpawn(EntityTypesInit.FURNACE_GOLEM_REGISTRY_OBJECT, config.furnaceGolem.spawnBiomes.toArray(new String[0]), config.furnaceGolem.weight, config.furnaceGolem.groupMin, config.furnaceGolem.groupMax);
        registerMobEntitySpawn(EntityTypesInit.MELON_GOLEM_REGISTRY_OBJECT, config.melonGolem.spawnBiomes.toArray(new String[0]), config.melonGolem.weight, config.melonGolem.groupMin, config.melonGolem.groupMax);
        registerMobEntitySpawn(EntityTypesInit.TROPICAL_SLIME_REGISTRY_OBJECT, config.tropicalSlime.spawnBiomes.toArray(new String[0]), config.tropicalSlime.weight, config.tropicalSlime.groupMin, config.tropicalSlime.groupMax);
    }

    private static void manageWaterEntities() {
        registerGlowingSquidSpawn();
    }


    private static <T extends AnimalEntity> void registerAnimalEntitySpawn(EntityType<T> entity, String[] spawnBiomes, int weight, int minGroupCountIn, int maxGroupCountIn) {
        BiomeSpawnHelper.setCreatureSpawnBiomes(entity, spawnBiomes, weight, minGroupCountIn, maxGroupCountIn);
        SpawnRestrictionAccessor.callRegister(entity, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::isValidNaturalSpawn);
    }

    private static <T extends AnimalEntity> void registerAnimalEntitySpawn(EntityType<T> entity, ModConfig.EntityConfig entityConfig) {
        registerAnimalEntitySpawn(entity, entityConfig.spawnBiomes.toArray(new String[0]), entityConfig.weight, entityConfig.groupMin, entityConfig.groupMax);
    }

    private static <T extends HostileEntity> void registerMonsterEntitySpawn(EntityType<T> entity, String[] spawnBiomes, int weight, int minGroupCountIn, int maxGroupCountIn) {
        BiomeSpawnHelper.setMonsterSpawnBiomes(entity, spawnBiomes, weight, minGroupCountIn, maxGroupCountIn);
        SpawnRestrictionAccessor.callRegister(entity, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, HostileEntity::canSpawnInDark);
    }

    private static <T extends HostileEntity> void registerMonsterEntitySpawn(EntityType<T> entity, ModConfig.EntityConfig entityConfig) {
        registerMonsterEntitySpawn(entity, entityConfig.spawnBiomes.toArray(new String[0]), entityConfig.weight, entityConfig.groupMin, entityConfig.groupMax);
    }

    private static <T extends MobEntity> void registerMobEntitySpawn(EntityType<T> entity, String[] spawnBiomes, int weight, int minGroupCountIn, int maxGroupCountIn) {
        BiomeSpawnHelper.setMobSpawnBiomes(entity, spawnBiomes, weight, minGroupCountIn, maxGroupCountIn);
        SpawnRestrictionAccessor.callRegister(entity, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MobEntity::canMobSpawn);
    }

    private static <T extends MobEntity> void registerMobEntitySpawn(EntityType<T> entity, ModConfig.EntityConfig entityConfig) {
        registerMobEntitySpawn(entity, entityConfig.spawnBiomes.toArray(new String[0]), entityConfig.weight, entityConfig.groupMin, entityConfig.groupMax);
    }

    private static void registerGlowingSquidSpawn() {
        BiomeSpawnHelper.setWaterCreatureSpawnBiomes(EntityTypesInit.GLOW_SQUID_REGISTRY_OBJECT, BiomeSpawnHelper.GLOW_SQUID_SPAWN_BIOMES, 6, 2, 4);
        SpawnRestrictionAccessor.callRegister(EntityTypesInit.GLOW_SQUID_REGISTRY_OBJECT, SpawnRestriction.Location.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, GlowSquidEntity::canGlowingSquidSpawn);
    }
}
