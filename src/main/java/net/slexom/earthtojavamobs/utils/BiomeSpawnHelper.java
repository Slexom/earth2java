package net.slexom.earthtojavamobs.utils;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Arrays;
import java.util.stream.Stream;


public final class BiomeSpawnHelper {

    private BiomeSpawnHelper() {
    }

    public static final String[] SNOWY_TUNDRA = new String[]{"minecraft:snowy_tundra", "minecraft:snowy_mountains"};
    public static final String[] ICE_SPIKE = new String[]{"minecraft:ice_spikes"};
    public static final String[] SNOWY_TAIGA = new String[]{"minecraft:snowy_taiga", "minecraft:snowy_taiga_hills", "minecraft:snowy_taiga_mountains"};
    public static final String[] FROZEN_RIVER = new String[]{"minecraft:frozen_river"};
    public static final String[] SNOWY_BEACH = new String[]{"minecraft:snowy_beach"};

    public static final String[] MOUNTAINS = new String[]{"minecraft:mountains", "minecraft:wooded_mountains", "minecraft:mountain_edge"};
    public static final String[] GRAVELLY_MOUNTAINS = new String[]{"minecraft:gravelly_mountains", "minecraft:modified_gravelly_mountains"};
    public static final String[] TAIGA = new String[]{"minecraft:taiga", "minecraft:taiga_hills", "minecraft:taiga_mountains"};
    public static final String[] GIANT_TAIGA = new String[]{"minecraft:giant_tree_taiga", "minecraft:giant_tree_taiga_hills", "minecraft:giant_spruce_taiga", "minecraft:giant_spruce_taiga_hills"};

    public static final String[] PLAINS = new String[]{"minecraft:plains", "minecraft:sunflower_plains"};
    public static final String[] FOREST = new String[]{"minecraft:forest", "minecraft:wooded_hills", "minecraft:flower_forest"};
    public static final String[] BIRCH_FOREST = new String[]{"minecraft:birch_forest", "minecraft:birch_forest_hills", "minecraft:tall_birch_forest", "minecraft:tall_birch_hills"};
    public static final String[] DARK_FOREST = new String[]{"minecraft:dark_forest", "minecraft:dark_forest_hills"};
    public static final String[] SWAMP = new String[]{"minecraft:swamp", "minecraft:swamp_hills"};
    public static final String[] JUNGLE = new String[]{"minecraft:jungle", "minecraft:jungle_hills", "minecraft:modified_jungle", "minecraft:jungle_edge", "minecraft:modified_jungle_edge"};
    public static final String[] BAMBOO_JUNGLE = new String[]{"minecraft:bamboo_jungle", "minecraft:bamboo_jungle_hills"};
    public static final String[] RIVER = new String[]{"minecraft:river"};
    public static final String[] BEACH = new String[]{"minecraft:beach"};
    public static final String[] MUSHROOM_FIELDS = new String[]{"minecraft:mushroom_fields", "minecraft:mushroom_fields"};

    public static final String[] DESERT = new String[]{"minecraft:desert", "minecraft:desert_hills", "minecraft:desert_lakes"};
    public static final String[] SAVANNA = new String[]{"minecraft:savanna", "minecraft:savanna_plateau", "minecraft:shattered_savanna", "minecraft:shattered_savanna_plateau"};

    public static final String[] OCEAN = new String[]{"minecraft:ocean", "minecraft:deep_ocean", "minecraft:frozen_ocean", "minecraft:deep_frozen_ocean", "minecraft:cold_ocean", "minecraft:deep_cold_ocean", "minecraft:lukewarm_ocean", "minecraft:deep_lukewarm_ocean", "minecraft:warm_ocean", "minecraft:deep_warm_ocean"};

    /**
     * @param biomes
     * @return
     */
    public static String[] getBiomesListFromBiomes(String[]... biomes) {
        return Stream.of(biomes).flatMap(Stream::of).toArray(String[]::new);
    }

    private static void setSpawnBiomes(EntityType entity, String[] spawnBiomes, int weight, int minGroupCountIn, int maxGroupCountIn, EntityClassification classification) {
        for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
            boolean biomeCriteria = Arrays.asList(spawnBiomes).contains(ForgeRegistries.BIOMES.getKey(biome).toString());
            if (!biomeCriteria)
                continue;
            biome.getSpawns(classification).add(new Biome.SpawnListEntry(entity, weight, minGroupCountIn, maxGroupCountIn));
        }
    }

    public static void setCreatureSpawnBiomes(EntityType entity, String[] spawnBiomes, int weight, int minGroupCountIn, int maxGroupCountIn) {
        setSpawnBiomes(entity, spawnBiomes, weight, minGroupCountIn, maxGroupCountIn, EntityClassification.CREATURE);
    }

    public static void setWaterCreatureSpawnBiomes(EntityType entity, String[] spawnBiomes, int weight, int minGroupCountIn, int maxGroupCountIn) {
        setSpawnBiomes(entity, spawnBiomes, weight, minGroupCountIn, maxGroupCountIn, EntityClassification.WATER_CREATURE);
    }

    public static void setMonsterSpawnBiomes(EntityType entity, String[] spawnBiomes, int weight, int minGroupCountIn, int maxGroupCountIn) {
        setSpawnBiomes(entity, spawnBiomes, weight, minGroupCountIn, maxGroupCountIn, EntityClassification.MONSTER);
    }
}
