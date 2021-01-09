package slexom.earthtojava.mobs.utils;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.WaterCreatureEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.world.biome.Biome;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public final class BiomeSpawnHelper {

    public static final String[] ALBINO_COW_SPAWN_BIOMES = getBiomesListFromBiomeCategories(Biome.Category.PLAINS, Biome.Category.EXTREME_HILLS);
    public static final String[] AMBER_CHICKEN_SPAWN_BIOMES = getBiomesListFromBiomeCategories(Biome.Category.DESERT, Biome.Category.SAVANNA);
    public static final String[] ASHEN_COW_SPAWN_BIOMES = getBiomesListFromBiomeCategories(Biome.Category.EXTREME_HILLS);
    public static final String[] BOLD_STRIPED_RABBIT_SPAWN_BIOMES = getBiomesListFromBiomeCategories(Biome.Category.PLAINS, Biome.Category.FOREST, Biome.Category.EXTREME_HILLS);
    public static final String[] BONE_SPIDER_SPAWN_BIOMES = getBiomesListFromBiomeCategories(Biome.Category.MESA);
    public static final String[] BOULDERING_ZOMBIE_SPAWN_BIOMES = getBiomesListFromBiomeCategories(Biome.Category.EXTREME_HILLS);
    public static final String[] BRONZED_CHICKEN_SPAWN_BIOMES = getBiomesListFromBiomeCategories(Biome.Category.PLAINS);
    public static final String[] CLUCKSHROOM_SPAWN_BIOMES = getBiomesListFromBiomeCategories(Biome.Category.MUSHROOM);
    public static final String[] COOKIE_COW_SPAWN_BIOMES = getBiomesListFromBiomeCategories(Biome.Category.PLAINS, Biome.Category.TAIGA, Biome.Category.EXTREME_HILLS);
    public static final String[] CREAM_COW_SPAWN_BIOMES = getBiomesListFromBiomeCategories(Biome.Category.PLAINS, Biome.Category.FOREST, Biome.Category.JUNGLE, Biome.Category.SAVANNA);
    public static final String[] DAIRY_COW_SPAWN_BIOMES = getBiomesListFromBiomeCategories(Biome.Category.PLAINS, Biome.Category.FOREST, Biome.Category.JUNGLE, Biome.Category.EXTREME_HILLS);
    public static final String[] FANCY_CHICKEN_SPAWN_BIOMES = getBiomesListFromBiomeCategories(Biome.Category.PLAINS, Biome.Category.FOREST);
    public static final String[] FLECKED_SHEEP_SPAWN_BIOMES = getBiomesListFromBiomeCategories(Biome.Category.PLAINS, Biome.Category.EXTREME_HILLS, Biome.Category.TAIGA, Biome.Category.FOREST);
    public static final String[] FRECKLED_RABBIT_SPAWN_BIOMES = getBiomesListFromBiomeCategories(Biome.Category.SAVANNA, Biome.Category.PLAINS, Biome.Category.FOREST, Biome.Category.JUNGLE);
    public static final String[] FUZZY_SHEEP_SPAWN_BIOMES = getBiomesListFromBiomeCategories(Biome.Category.SAVANNA, Biome.Category.PLAINS, Biome.Category.FOREST, Biome.Category.JUNGLE, Biome.Category.FOREST);
    public static final String[] GLOW_SQUID_SPAWN_BIOMES = getBiomesListFromBiomeCategories(Biome.Category.OCEAN, Biome.Category.RIVER, Biome.Category.SWAMP);
    public static final String[] GOLD_CRESTED_CHICKEN_SPAWN_BIOMES = getBiomesListFromBiomeCategories(Biome.Category.PLAINS, Biome.Category.EXTREME_HILLS, Biome.Category.FOREST, Biome.Category.SAVANNA);
    public static final String[] HARELEQUIN_RABBIT_SPAWN_BIOMES = getBiomesListFromBiomeCategories(Biome.Category.PLAINS);
    public static final String[] HORNED_SHEEP_SPAWN_BIOMES = getBiomesListFromBiomeCategories(Biome.Category.PLAINS, Biome.Category.EXTREME_HILLS);
    public static final String[] INKY_SHEEP_SPAWN_BIOMES = getBiomesListFromBiomeCategories(Biome.Category.PLAINS, Biome.Category.EXTREME_HILLS);
    public static final String[] JOLLY_LLAMA_SPAWN_BIOMES = getBiomesListFromBiomeCategories(Biome.Category.TAIGA, Biome.Category.ICY);
    public static final String[] JUMBO_RABBIT_SPAWN_BIOMES = getBiomesListFromBiomeCategories(Biome.Category.PLAINS);
    public static final String[] LOBBER_ZOMBIE_SPAWN_BIOMES = getBiomesListFromBiomeCategories(Biome.Category.SWAMP);
    public static final String[] LONG_NOSED_SHEEP_SPAWN_BIOMES = getBiomesListFromBiomeCategories(Biome.Category.SAVANNA, Biome.Category.DESERT);
    public static final String[] MIDNIGHT_CHICKEN_SPAWN_BIOMES = getBiomesListFromBiomeCategories(Biome.Category.FOREST, Biome.Category.JUNGLE);
    public static final String[] MOOBLOOM_SPAWN_BIOMES = getBiomesListFromBiomes(new String[]{"minecraft:flower_forest"});
    public static final String[] MOOLIP_SPAWN_BIOMES = getBiomesListFromBiomes(new String[]{"minecraft:flower_forest"});
    public static final String[] MOTTLED_PIG_SPAWN_BIOMES = getBiomesListFromBiomeCategories(Biome.Category.PLAINS, Biome.Category.EXTREME_HILLS, Biome.Category.FOREST);
    public static final String[] MUDDY_FOOT_RABBIT_SPAWN_BIOMES = getBiomesListFromBiomeCategories(Biome.Category.PLAINS);
    public static final String[] MUDDY_PIG_SPAWN_BIOMES = getBiomesListFromBiomeCategories(Biome.Category.PLAINS, Biome.Category.EXTREME_HILLS, Biome.Category.RIVER);
    public static final String[] PALE_PIG_SPAWN_BIOMES = getBiomesListFromBiomeCategories(Biome.Category.TAIGA, Biome.Category.ICY);
    public static final String[] PATCHED_SHEEP_SPAWN_BIOMES = getBiomesListFromBiomeCategories(Biome.Category.PLAINS, Biome.Category.EXTREME_HILLS);
    public static final String[] PIEBALD_PIG_SPAWN_BIOMES = getBiomesListFromBiomeCategories(Biome.Category.FOREST, Biome.Category.PLAINS, Biome.Category.EXTREME_HILLS, Biome.Category.TAIGA, Biome.Category.SAVANNA);
    public static final String[] PINK_FOOTED_PIG_SPAWN_BIOMES = getBiomesListFromBiomeCategories(Biome.Category.PLAINS, Biome.Category.FOREST);
    public static final String[] PINTO_COW_SPAWN_BIOMES = getBiomesListFromBiomeCategories(Biome.Category.PLAINS, Biome.Category.TAIGA, Biome.Category.EXTREME_HILLS);
    public static final String[] RAINBOW_SHEEP_SPAWN_BIOMES = getBiomesListFromBiomeCategories(Biome.Category.PLAINS, Biome.Category.FOREST);
    public static final String[] ROCKY_SHEEP_SPAWN_BIOMES = getBiomesListFromBiomeCategories(Biome.Category.PLAINS, Biome.Category.EXTREME_HILLS);
    public static final String[] SKELETON_WOLF_SPAWN_BIOMES = getBiomesListFromBiomeCategories(Biome.Category.FOREST, Biome.Category.TAIGA, Biome.Category.MESA);
    public static final String[] SKEWBALD_CHICKEN_SPAWN_BIOMES = getBiomesListFromBiomeCategories(Biome.Category.PLAINS, Biome.Category.FOREST, Biome.Category.JUNGLE);
    public static final String[] SOOTY_PIG_SPAWN_BIOMES = getBiomesListFromBiomeCategories(Biome.Category.PLAINS, Biome.Category.JUNGLE, Biome.Category.SAVANNA);
    public static final String[] SPOTTED_PIG_SPAWN_BIOMES = getBiomesListFromBiomeCategories(Biome.Category.SWAMP);
    public static final String[] STORMY_CHICKEN_SPAWN_BIOMES = getBiomesListFromBiomeCategories(Biome.Category.PLAINS, Biome.Category.EXTREME_HILLS, Biome.Category.TAIGA);
    public static final String[] SUNSET_COW_SPAWN_BIOMES = getBiomesListFromBiomeCategories(Biome.Category.SAVANNA);
    public static final String[] TROPICAL_SLIME_SPAWN_BIOMES = getBiomesListFromBiomeCategories(Biome.Category.BEACH);
    public static final String[] UMBRA_COW_SPAWN_BIOMES = getBiomesListFromBiomeCategories(Biome.Category.TAIGA, Biome.Category.ICY, Biome.Category.EXTREME_HILLS);
    public static final String[] VESTED_RABBIT_SPAWN_BIOMES = getBiomesListFromBiomeCategories(Biome.Category.PLAINS);
    public static final String[] VILER_WITCH_SPAWN_BIOMES = getBiomesListFromBiomeCategories(Biome.Category.SWAMP);
    public static final String[] WOOLY_COW_SPAWN_BIOMES = getBiomesListFromBiomeCategories(Biome.Category.TAIGA);

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private BiomeSpawnHelper() {
    }

    public static String[] getBiomesListFromBiomes(String[]... biomes) {
        return Stream.of(biomes).flatMap(Stream::of).toArray(String[]::new);
    }

    public static String[] getBiomesListFromBiomeCategories(Biome.Category... types) {
        return Stream.of(types).flatMap(Stream::of).map(Biome.Category::getName).toArray(String[]::new);
    }

    private static void setSpawnBiomes(EntityType<?> entity, String[] spawnBiomes, int weight, int minGroupSize, int maxGroupSize, SpawnGroup classification) {
        List<String> blackList = Arrays.stream(spawnBiomes).filter(id -> id.contains("!")).collect(Collectors.toList());
        List<String> spawnList = expandSpawnList(Arrays.stream(spawnBiomes).filter(id -> !id.contains("!")).collect(Collectors.toList()));
        blackList.replaceAll(s -> s.replace("!", ""));
        spawnList.removeAll(blackList);
        addEntityToBiomes(entity, spawnList, minGroupSize, maxGroupSize, classification, weight);
    }

    private static List<String> expandSpawnList(List<String> spawnList) {
        List<String> biomes = new ArrayList<>(Collections.emptyList());
        List<String> biomeCategories = new ArrayList<>(Collections.emptyList());
        List<String> biomesFromCategories = new ArrayList<>(Collections.emptyList());
        spawnList.forEach(identifier -> {
            if (isBiomeCategory(identifier)) {
                biomeCategories.add(identifier);
            } else {
                biomes.add(identifier);
            }
        });
        for (String biomeCategory : biomeCategories) {
            BuiltinRegistries.BIOME.forEach(biome -> {
                if (biome.getCategory().toString().equalsIgnoreCase(biomeCategory)) {
                    biomesFromCategories.add(BuiltinRegistries.BIOME.getId(biome).toString());
                }
            });
        }
        return Stream.concat(biomes.stream(), biomesFromCategories.stream()).collect(Collectors.toList());
    }

    private static boolean isBiomeCategory(String identifier) {
        return identifier.split(":").length == 1;
    }

    private static void addEntityToBiomes(EntityType<?> entity, List<String> spawnList, int minGroupSize, int maxGroupSize, SpawnGroup classification, int weight) {
        for (String identifier : spawnList) {
            BuiltinRegistries.BIOME.stream()
                    .filter(biome -> BuiltinRegistries.BIOME.getId(biome).toString().equals(identifier))
                    .findFirst()
                    .ifPresent(biome -> {
                        Predicate<BiomeSelectionContext> predicate = BiomeSelectors.includeByKey(BuiltinRegistries.BIOME.getKey(biome).get());
                        BiomeModifications.addSpawn(predicate, classification, entity, weight, minGroupSize, maxGroupSize);
                    });
            RegistryEntryAddedCallback.event(BuiltinRegistries.BIOME).register((i, registryName, biome) -> {
                if (registryName.toString().equals(identifier)) {
                    Predicate<BiomeSelectionContext> predicate = BiomeSelectors.includeByKey(BuiltinRegistries.BIOME.getKey(biome).get());
                    BiomeModifications.addSpawn(predicate, classification, entity, weight, minGroupSize, maxGroupSize);
                }
            });

        }
    }

    public static <T extends AnimalEntity> void setCreatureSpawnBiomes(EntityType<T> entity, String[] spawnBiomes, int weight, int minGroupCountIn, int maxGroupCountIn) {
        setSpawnBiomes(entity, spawnBiomes, weight, minGroupCountIn, maxGroupCountIn, SpawnGroup.CREATURE);
    }

    public static <T extends WaterCreatureEntity> void setWaterCreatureSpawnBiomes(EntityType<T> entity, String[] spawnBiomes, int weight, int minGroupCountIn, int maxGroupCountIn) {
        setSpawnBiomes(entity, spawnBiomes, weight, minGroupCountIn, maxGroupCountIn, SpawnGroup.WATER_CREATURE);
    }

    public static <T extends HostileEntity> void setMonsterSpawnBiomes(EntityType<T> entity, String[] spawnBiomes, int weight, int minGroupCountIn, int maxGroupCountIn) {
        setSpawnBiomes(entity, spawnBiomes, weight, minGroupCountIn, maxGroupCountIn, SpawnGroup.MONSTER);
    }

    public static <T extends MobEntity> void setMobSpawnBiomes(EntityType<T> entity, String[] spawnBiomes, int weight, int minGroupCountIn, int maxGroupCountIn) {
        setSpawnBiomes(entity, spawnBiomes, weight, minGroupCountIn, maxGroupCountIn, SpawnGroup.MISC);
    }

    public static List<String> convertForConfig(String[] ary) {
        return Arrays.stream(ary).collect(Collectors.toList());
    }

    // Experimental (Should add this for passive mobs only?)
    public static void autoSpawn(EntityType<? extends Entity> entity, EntityType<? extends Entity> entityType) {
        BuiltinRegistries.BIOME.stream().forEach(biome -> {
            autoSpawnInBiome(biome, entity, entityType, entityType.getSpawnGroup());
        });
        RegistryEntryAddedCallback.event(BuiltinRegistries.BIOME).register((i, registryName, biome) -> {
            autoSpawnInBiome(biome, entity, entityType, entityType.getSpawnGroup());
        });
    }

    private static void autoSpawnInBiome(Biome biome, EntityType<? extends Entity> entity, EntityType<? extends Entity> entityType, SpawnGroup spawnGroup) {
        biome
                .getSpawnSettings()
                .getSpawnEntry(spawnGroup)
                .stream()
                .filter(spawnEntry -> spawnEntry.type.equals(entityType))
                .findFirst()
                .ifPresent(spawnEntry -> {
                    Predicate<BiomeSelectionContext> predicate = BiomeSelectors.includeByKey(BuiltinRegistries.BIOME.getKey(biome).get());
                    BiomeModifications.addSpawn(predicate, spawnGroup, entity, spawnEntry.weight, spawnEntry.minGroupSize, spawnEntry.maxGroupSize);
                });
    }
}
