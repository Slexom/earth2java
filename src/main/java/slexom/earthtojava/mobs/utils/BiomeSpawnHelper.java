package slexom.earthtojava.mobs.utils;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
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

    public static final String[] BOLD_STRIPED_RABBIT_SPAWN_BIOMES = getBiomesListFromBiomeCategories(Biome.Category.PLAINS, Biome.Category.FOREST, Biome.Category.EXTREME_HILLS);
    public static final String[] COOKIE_COW_SPAWN_BIOMES = getBiomesListFromBiomeCategories(Biome.Category.PLAINS, Biome.Category.TAIGA, Biome.Category.EXTREME_HILLS);
    public static final String[] PINTO_COW_SPAWN_BIOMES = getBiomesListFromBiomeCategories(Biome.Category.PLAINS, Biome.Category.TAIGA, Biome.Category.EXTREME_HILLS);
    public static final String[] MOOBLOOM_SPAWN_BIOMES = getBiomesListFromBiomes(new String[]{"minecraft:flower_forest"});
    public static final String[] MOOLIP_SPAWN_BIOMES = getBiomesListFromBiomes(new String[]{"minecraft:flower_forest"});
    public static final String[] PINK_FOOTED_PIG_SPAWN_BIOMES = getBiomesListFromBiomeCategories(Biome.Category.PLAINS);
    private static final String[] GRAVELLY_MOUNTAINS = new String[]{"minecraft:gravelly_mountains", "minecraft:modified_gravelly_mountains"};
    private static final String[] BADLANDS = new String[]{"minecraft:badlands", "minecraft:badlands_plateau", "minecraft:modified_badlands_plateau", "minecraft:wooded_badlands_plateau", "minecraft:modified_wooded_badlands_plateau", "minecraft:eroded_badlands"};
    public static final String[] BONE_SPIDER_SPAWN_BIOMES = getBiomesListFromBiomes(BADLANDS);
    private static final String[] BAMBOO_JUNGLE = new String[]{"minecraft:bamboo_jungle", "minecraft:bamboo_jungle_hills"};
    private static final String[] BEACH = new String[]{"minecraft:beach"};
    public static final String[] TROPICAL_SLIME_SPAWN_BIOMES = getBiomesListFromBiomes(BEACH);
    private static final String[] BIRCH_FOREST = new String[]{"minecraft:birch_forest", "minecraft:birch_forest_hills", "minecraft:tall_birch_forest", "minecraft:tall_birch_hills"};
    private static final String[] DARK_FOREST = new String[]{"minecraft:dark_forest", "minecraft:dark_forest_hills"};
    private static final String[] DESERT = new String[]{"minecraft:desert", "minecraft:desert_hills", "minecraft:desert_lakes"};
    public static final String[] FURNACE_GOLEM_SPAWN_BIOMES = getBiomesListFromBiomes(DESERT);
    private static final String[] FOREST = new String[]{"minecraft:forest", "minecraft:wooded_hills", "minecraft:flower_forest"};
    private static final String[] FROZEN_RIVER = new String[]{"minecraft:frozen_river"};
    private static final String[] GIANT_TAIGA = new String[]{"minecraft:giant_tree_taiga", "minecraft:giant_tree_taiga_hills", "minecraft:giant_spruce_taiga", "minecraft:giant_spruce_taiga_hills"};
    private static final String[] ICE_SPIKE = new String[]{"minecraft:ice_spikes"};
    private static final String[] JUNGLE = new String[]{"minecraft:jungle", "minecraft:jungle_hills", "minecraft:modified_jungle", "minecraft:jungle_edge", "minecraft:modified_jungle_edge"};
    public static final String[] MIDNIGHT_CHICKEN_SPAWN_BIOMES = getBiomesListFromBiomes(FOREST, DARK_FOREST, JUNGLE, BIRCH_FOREST);
    private static final String[] MOUNTAINS = new String[]{"minecraft:mountains", "minecraft:wooded_mountains", "minecraft:mountain_edge"};
    public static final String[] ASHEN_COW_SPAWN_BIOMES = getBiomesListFromBiomes(MOUNTAINS, GRAVELLY_MOUNTAINS);
    private static final String[] MUSHROOM_FIELDS = new String[]{"minecraft:mushroom_fields", "minecraft:mushroom_fields"};
    public static final String[] CLUCKSHROOM_SPAWN_BIOMES = getBiomesListFromBiomes(MUSHROOM_FIELDS);
    private static final String[] OCEAN = new String[]{"minecraft:ocean", "minecraft:deep_ocean", "minecraft:frozen_ocean", "minecraft:deep_frozen_ocean", "minecraft:cold_ocean", "minecraft:deep_cold_ocean", "minecraft:lukewarm_ocean", "minecraft:deep_lukewarm_ocean", "minecraft:warm_ocean", "minecraft:deep_warm_ocean"};
    private static final String[] PLAINS = new String[]{"minecraft:plains", "minecraft:sunflower_plains"};
    public static final String[] BRONZED_CHICKEN_SPAWN_BIOMES = getBiomesListFromBiomes(PLAINS);
    public static final String[] ALBINO_COW_SPAWN_BIOMES = getBiomesListFromBiomes(PLAINS, MOUNTAINS, GRAVELLY_MOUNTAINS);
    public static final String[] MUDDY_FOOT_RABBIT_SPAWN_BIOMES = getBiomesListFromBiomes(PLAINS);
    public static final String[] HARELEQUIN_RABBIT_SPAWN_BIOMES = getBiomesListFromBiomes(PLAINS);
    public static final String[] JUMBO_RABBIT_SPAWN_BIOMES = getBiomesListFromBiomes(PLAINS);
    public static final String[] INKY_SHEEP_SPAWN_BIOMES = getBiomesListFromBiomes(PLAINS, MOUNTAINS, GRAVELLY_MOUNTAINS);
    public static final String[] HORNED_SHEEP_SPAWN_BIOMES = getBiomesListFromBiomes(PLAINS, MOUNTAINS, GRAVELLY_MOUNTAINS);
    public static final String[] RAINBOW_SHEEP_SPAWN_BIOMES = getBiomesListFromBiomes(PLAINS, FOREST);
    public static final String[] ROCKY_SHEEP_SPAWN_BIOMES = getBiomesListFromBiomes(PLAINS, MOUNTAINS, GRAVELLY_MOUNTAINS);
    public static final String[] VESTED_RABBIT_SPAWN_BIOMES = getBiomesListFromBiomes(PLAINS);
    private static final String[] RIVER = new String[]{"minecraft:river"};
    public static final String[] MUDDY_PIG_SPAWN_BIOMES = getBiomesListFromBiomes(PLAINS, MOUNTAINS, RIVER);
    private static final String[] SAVANNA = new String[]{"minecraft:savanna", "minecraft:savanna_plateau", "minecraft:shattered_savanna", "minecraft:shattered_savanna_plateau"};
    public static final String[] AMBER_CHICKEN_SPAWN_BIOMES = getBiomesListFromBiomes(DESERT, SAVANNA);
    public static final String[] SUNSET_COW_SPAWN_BIOMES = getBiomesListFromBiomes(SAVANNA);
    private static final String[] SNOWY_BEACH = new String[]{"minecraft:snowy_beach"};
    private static final String[] SNOWY_TAIGA = new String[]{"minecraft:snowy_taiga", "minecraft:snowy_taiga_hills", "minecraft:snowy_taiga_mountains"};
    private static final String[] SNOWY_TUNDRA = new String[]{"minecraft:snowy_tundra", "minecraft:snowy_mountains"};
    public static final String[] PALE_PIG_SPAWN_BIOMES = getBiomesListFromBiomes(SNOWY_TUNDRA, SNOWY_TAIGA);
    public static final String[] MELON_GOLEM_SPAWN_BIOMES = getBiomesListFromBiomes(SNOWY_TAIGA, SNOWY_TUNDRA, SNOWY_BEACH, ICE_SPIKE);
    public static final String[] JOLLY_LLAMA_SPAWN_BIOMES = getBiomesListFromBiomes(SNOWY_TUNDRA, ICE_SPIKE, SNOWY_TAIGA, FROZEN_RIVER, SNOWY_BEACH);
    private static final String[] SWAMP = new String[]{"minecraft:swamp", "minecraft:swamp_hills"};
    public static final String[] SPOTTED_PIG_SPAWN_BIOMES = getBiomesListFromBiomes(SWAMP);
    public static final String[] GLOW_SQUID_SPAWN_BIOMES = getBiomesListFromBiomes(OCEAN, RIVER, SWAMP);
    private static final String[] TAIGA = new String[]{"minecraft:taiga", "minecraft:taiga_hills", "minecraft:taiga_mountains"};
    public static final String[] STORMY_CHICKEN_SPAWN_BIOMES = getBiomesListFromBiomes(PLAINS, MOUNTAINS, GRAVELLY_MOUNTAINS, TAIGA);
    public static final String[] FLECKED_SHEEP_SPAWN_BIOMES = getBiomesListFromBiomes(PLAINS, MOUNTAINS, TAIGA, GRAVELLY_MOUNTAINS, FOREST);
    public static final String[] PIEBALD_PIG_SPAWN_BIOMES = getBiomesListFromBiomes(FOREST, BIRCH_FOREST, PLAINS, MOUNTAINS, TAIGA, SAVANNA);
    public static final String[] SKELETON_WOLF_SPAWN_BIOMES = getBiomesListFromBiomes(FOREST, TAIGA, SNOWY_TAIGA, GIANT_TAIGA, BADLANDS);
    public static final String[] WOOLY_COW_SPAWN_BIOMES = getBiomesListFromBiomes(TAIGA, SNOWY_TAIGA, GIANT_TAIGA);
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
                if (biome.getCategory().toString().toUpperCase().equals(biomeCategory.toUpperCase())) {
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

}
