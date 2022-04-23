package slexom.earthtojava.utils;

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
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import org.jetbrains.annotations.ApiStatus;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
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
        return Stream.of(types).map(Biome.Category::getName).toArray(String[]::new);
    }

    private static void setSpawnBiomes(EntityType<?> entity, String[] spawnBiomes, int weight, int minGroupSize, int maxGroupSize, SpawnGroup classification) {
        List<String> biomeTagKeysIdentifiers = Arrays.stream(spawnBiomes).filter(BiomeSpawnHelper::isBiomeTag).collect(Collectors.toList());
        List<String> biomeCategoryIdentifiers = Arrays.stream(spawnBiomes).filter(BiomeSpawnHelper::isBiomeCategory).collect(Collectors.toList());
        List<String> biomeIdentifierString = Arrays.stream(spawnBiomes).filter(id -> !isBiomeTag(id) && !isBiomeCategory(id)).collect(Collectors.toList());

        List<TagKey<Biome>> biomeTagKeys = getBiomeTagKeys(biomeTagKeysIdentifiers);
        List<Biome.Category> biomeCategories = getBiomeCategories(biomeCategoryIdentifiers);
        List<Identifier> biomeIdentifiers = getBiomeRegistryKeys(biomeIdentifierString);

        addEntityToBiomeCategories(entity, biomeCategories, minGroupSize, maxGroupSize, classification, weight);
        addEntityToBiomeTagKeys(entity, biomeTagKeys, minGroupSize, maxGroupSize, classification, weight);
        addEntityToBiomes(entity, biomeIdentifiers, minGroupSize, maxGroupSize, classification, weight);
    }

    private static List<Biome.Category> getBiomeCategories(List<String> categories) {
        return categories.stream().map(Biome.Category::byName).toList();
    }

    private static List<Identifier> getBiomeRegistryKeys(List<String> categories) {
        return categories.stream().map(Identifier::new).toList();
    }

    private static List<TagKey<Biome>> getBiomeTagKeys(List<String> tagKeys) {
        return tagKeys.stream().map(identifier -> TagKey.of(Registry.BIOME_KEY, new Identifier(identifier))).toList();
    }

    private static boolean isBiomeCategory(String identifier) {
        return identifier.split(":").length == 1;
    }

    private static boolean isBiomeTag(String identifier) {
        return identifier.contains("is_") || identifier.contains("has_");
    }

    private static void addEntityToBiomeTagKeys(EntityType<?> entity, List<TagKey<Biome>> biomeTagKeys, int minGroupSize, int maxGroupSize, SpawnGroup classification, int weight) {
        for (TagKey<Biome> tagKey : biomeTagKeys) {
            Predicate<BiomeSelectionContext> predicate = BiomeSelectors.tag(tagKey);
            BiomeModifications.addSpawn(predicate, classification, entity, weight, minGroupSize, maxGroupSize);
        }
    }

    private static void addEntityToBiomeCategories(EntityType<?> entity, List<Biome.Category> biomeCategories, int minGroupSize, int maxGroupSize, SpawnGroup classification, int weight) {
        for (Biome.Category category : biomeCategories) {
            Predicate<BiomeSelectionContext> predicate = BiomeSelectors.categories(category);
            BiomeModifications.addSpawn(predicate, classification, entity, weight, minGroupSize, maxGroupSize);
        }
    }

    private static void addEntityToBiomes(EntityType<?> entity, List<Identifier> biomeIdentifiers, int minGroupSize, int maxGroupSize, SpawnGroup classification, int weight) {
        for (Identifier biomeIdentifier : biomeIdentifiers) {
            Predicate<BiomeSelectionContext> predicate = biomeSelectionContext -> Objects.equals(biomeSelectionContext.getBiomeKey().getValue(), biomeIdentifier);
            BiomeModifications.addSpawn(predicate, classification, entity, weight, minGroupSize, maxGroupSize);
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

    @ApiStatus.Experimental
    public static void autoSpawn(EntityType<? extends Entity> entity, EntityType<? extends Entity> baseEntity) {

        BuiltinRegistries.BIOME.stream().forEach(biome -> {
            biome
                    .getSpawnSettings()
                    .getSpawnEntries(SpawnGroup.MONSTER)
                    .getEntries()
                    .stream()
                    .filter(spawnEntry -> spawnEntry.type.equals(baseEntity))
                    .findFirst()
                    .ifPresent(spawnEntry -> {
                        Predicate<BiomeSelectionContext> predicate = BiomeSelectors.includeByKey(BuiltinRegistries.BIOME.getKey(biome).get());
                        BiomeModifications.addSpawn(predicate, SpawnGroup.MONSTER, entity, 10, spawnEntry.minGroupSize, spawnEntry.maxGroupSize);
                    });
        });
        RegistryEntryAddedCallback.event(BuiltinRegistries.BIOME).register((i, registryName, biome) -> {
            System.out.println(registryName);
            System.out.println(biome.getSpawnSettings().getSpawnDensity(baseEntity));

        });
    }

}
