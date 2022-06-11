package slexom.earthtojava.utils;

import com.google.common.collect.ImmutableList;
import dev.architectury.hooks.level.biome.BiomeProperties;
import dev.architectury.registry.level.biome.BiomeModifications;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.WaterCreatureEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.SpawnSettings;
import slexom.earthtojava.mixins.SpawnRestrictionAccessor;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;


public final class BiomeSpawnHelper {

    public static final List<Biome.Category> ALBINO_COW_SPAWN_BIOMES = ImmutableList.of(Biome.Category.PLAINS, Biome.Category.EXTREME_HILLS);
    public static final List<Biome.Category> AMBER_CHICKEN_SPAWN_BIOMES = ImmutableList.of(Biome.Category.DESERT, Biome.Category.SAVANNA);
    public static final List<Biome.Category> ASHEN_COW_SPAWN_BIOMES = ImmutableList.of(Biome.Category.EXTREME_HILLS);
    public static final List<Biome.Category> BOLD_STRIPED_RABBIT_SPAWN_BIOMES = ImmutableList.of(Biome.Category.PLAINS, Biome.Category.FOREST, Biome.Category.EXTREME_HILLS);
    public static final List<Biome.Category> BONE_SPIDER_SPAWN_BIOMES = ImmutableList.of(Biome.Category.MESA);
    public static final List<Biome.Category> BOULDERING_ZOMBIE_SPAWN_BIOMES = ImmutableList.of(Biome.Category.EXTREME_HILLS);
    public static final List<Biome.Category> BRONZED_CHICKEN_SPAWN_BIOMES = ImmutableList.of(Biome.Category.PLAINS);
    public static final List<Biome.Category> CLUCKSHROOM_SPAWN_BIOMES = ImmutableList.of(Biome.Category.MUSHROOM);
    public static final List<Biome.Category> COOKIE_COW_SPAWN_BIOMES = ImmutableList.of(Biome.Category.PLAINS, Biome.Category.TAIGA, Biome.Category.EXTREME_HILLS);
    public static final List<Biome.Category> CREAM_COW_SPAWN_BIOMES = ImmutableList.of(Biome.Category.PLAINS, Biome.Category.FOREST, Biome.Category.JUNGLE, Biome.Category.SAVANNA);
    public static final List<Biome.Category> DAIRY_COW_SPAWN_BIOMES = ImmutableList.of(Biome.Category.PLAINS, Biome.Category.FOREST, Biome.Category.JUNGLE, Biome.Category.EXTREME_HILLS);
    public static final List<Biome.Category> FANCY_CHICKEN_SPAWN_BIOMES = ImmutableList.of(Biome.Category.PLAINS, Biome.Category.FOREST);
    public static final List<Biome.Category> FLECKED_SHEEP_SPAWN_BIOMES = ImmutableList.of(Biome.Category.PLAINS, Biome.Category.EXTREME_HILLS, Biome.Category.TAIGA, Biome.Category.FOREST);
    public static final List<Biome.Category> FRECKLED_RABBIT_SPAWN_BIOMES = ImmutableList.of(Biome.Category.SAVANNA, Biome.Category.PLAINS, Biome.Category.FOREST, Biome.Category.JUNGLE);
    public static final List<Biome.Category> FUZZY_SHEEP_SPAWN_BIOMES = ImmutableList.of(Biome.Category.SAVANNA, Biome.Category.PLAINS, Biome.Category.FOREST, Biome.Category.JUNGLE, Biome.Category.FOREST);
    public static final List<Biome.Category> GOLD_CRESTED_CHICKEN_SPAWN_BIOMES = ImmutableList.of(Biome.Category.PLAINS, Biome.Category.EXTREME_HILLS, Biome.Category.FOREST, Biome.Category.SAVANNA);
    public static final List<Biome.Category> HARELEQUIN_RABBIT_SPAWN_BIOMES = ImmutableList.of(Biome.Category.PLAINS);
    public static final List<Biome.Category> HORNED_SHEEP_SPAWN_BIOMES = ImmutableList.of(Biome.Category.PLAINS, Biome.Category.EXTREME_HILLS);
    public static final List<Biome.Category> INKY_SHEEP_SPAWN_BIOMES = ImmutableList.of(Biome.Category.PLAINS, Biome.Category.EXTREME_HILLS);
    public static final List<Biome.Category> JOLLY_LLAMA_SPAWN_BIOMES = ImmutableList.of(Biome.Category.TAIGA, Biome.Category.ICY);
    public static final List<Biome.Category> JUMBO_RABBIT_SPAWN_BIOMES = ImmutableList.of(Biome.Category.PLAINS);
    public static final List<Biome.Category> LOBBER_ZOMBIE_SPAWN_BIOMES = ImmutableList.of(Biome.Category.SWAMP);
    public static final List<Biome.Category> LONG_NOSED_SHEEP_SPAWN_BIOMES = ImmutableList.of(Biome.Category.SAVANNA, Biome.Category.DESERT);
    public static final List<Biome.Category> MIDNIGHT_CHICKEN_SPAWN_BIOMES = ImmutableList.of(Biome.Category.FOREST, Biome.Category.JUNGLE);
    // public static final  List<Biome.Category> MOOBLOOM_SPAWN_BIOMES = getBiomesListFromBiomes(new String[]{"minecraft:flower_forest"});
    // public static final  List<Biome.Category> MOOLIP_SPAWN_BIOMES = getBiomesListFromBiomes(new String[]{"minecraft:flower_forest"});
    public static final List<Biome.Category> MOTTLED_PIG_SPAWN_BIOMES = ImmutableList.of(Biome.Category.PLAINS, Biome.Category.EXTREME_HILLS, Biome.Category.FOREST);
    public static final List<Biome.Category> MUDDY_FOOT_RABBIT_SPAWN_BIOMES = ImmutableList.of(Biome.Category.PLAINS);
    public static final List<Biome.Category> MUDDY_PIG_SPAWN_BIOMES = ImmutableList.of(Biome.Category.PLAINS, Biome.Category.EXTREME_HILLS, Biome.Category.RIVER);
    public static final List<Biome.Category> PALE_PIG_SPAWN_BIOMES = ImmutableList.of(Biome.Category.TAIGA, Biome.Category.ICY);
    public static final List<Biome.Category> PATCHED_SHEEP_SPAWN_BIOMES = ImmutableList.of(Biome.Category.PLAINS, Biome.Category.EXTREME_HILLS);
    public static final List<Biome.Category> PIEBALD_PIG_SPAWN_BIOMES = ImmutableList.of(Biome.Category.FOREST, Biome.Category.PLAINS, Biome.Category.EXTREME_HILLS, Biome.Category.TAIGA, Biome.Category.SAVANNA);
    public static final List<Biome.Category> PINK_FOOTED_PIG_SPAWN_BIOMES = ImmutableList.of(Biome.Category.PLAINS, Biome.Category.FOREST);
    public static final List<Biome.Category> PINTO_COW_SPAWN_BIOMES = ImmutableList.of(Biome.Category.PLAINS, Biome.Category.TAIGA, Biome.Category.EXTREME_HILLS);
    public static final List<Biome.Category> RAINBOW_SHEEP_SPAWN_BIOMES = ImmutableList.of(Biome.Category.PLAINS, Biome.Category.FOREST);
    public static final List<Biome.Category> ROCKY_SHEEP_SPAWN_BIOMES = ImmutableList.of(Biome.Category.PLAINS, Biome.Category.EXTREME_HILLS);
    public static final List<Biome.Category> SKELETON_WOLF_SPAWN_BIOMES = ImmutableList.of(Biome.Category.FOREST, Biome.Category.TAIGA, Biome.Category.MESA);
    public static final List<Biome.Category> SKEWBALD_CHICKEN_SPAWN_BIOMES = ImmutableList.of(Biome.Category.PLAINS, Biome.Category.FOREST, Biome.Category.JUNGLE);
    public static final List<Biome.Category> SOOTY_PIG_SPAWN_BIOMES = ImmutableList.of(Biome.Category.PLAINS, Biome.Category.JUNGLE, Biome.Category.SAVANNA);
    public static final List<Biome.Category> SPOTTED_PIG_SPAWN_BIOMES = ImmutableList.of(Biome.Category.SWAMP);
    public static final List<Biome.Category> STORMY_CHICKEN_SPAWN_BIOMES = ImmutableList.of(Biome.Category.PLAINS, Biome.Category.EXTREME_HILLS, Biome.Category.TAIGA);
    public static final List<Biome.Category> SUNSET_COW_SPAWN_BIOMES = ImmutableList.of(Biome.Category.SAVANNA);
    public static final List<Biome.Category> TROPICAL_SLIME_SPAWN_BIOMES = ImmutableList.of(Biome.Category.BEACH);
    public static final List<Biome.Category> UMBRA_COW_SPAWN_BIOMES = ImmutableList.of(Biome.Category.TAIGA, Biome.Category.ICY, Biome.Category.EXTREME_HILLS);
    public static final List<Biome.Category> VESTED_RABBIT_SPAWN_BIOMES = ImmutableList.of(Biome.Category.PLAINS);
    public static final List<Biome.Category> VILER_WITCH_SPAWN_BIOMES = ImmutableList.of(Biome.Category.SWAMP);
    public static final List<Biome.Category> WOOLY_COW_SPAWN_BIOMES = ImmutableList.of(Biome.Category.TAIGA);
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private BiomeSpawnHelper() {
    }


    private static <T extends Entity> void modifier(BiomeModifications.BiomeContext context, BiomeProperties.Mutable mutable, RegistrySupplier<EntityType<T>> entity, int minGroupSize, int maxGroupSize, SpawnGroup classification, int weight) {
        SpawnSettings.SpawnEntry spawnEntry = new SpawnSettings.SpawnEntry(entity.get(), weight, minGroupSize, maxGroupSize);
        mutable.getSpawnProperties().addSpawn(classification, spawnEntry);
        if (classification == SpawnGroup.CREATURE) {
            SpawnRestrictionAccessor.callRegister((EntityType<AnimalEntity>) entity.get(), SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::isValidNaturalSpawn);
        }
        if (classification == SpawnGroup.MONSTER) {
            SpawnRestrictionAccessor.callRegister((EntityType<HostileEntity>) entity.get(), SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, HostileEntity::canSpawnInDark);
        }
    }

    private static <T extends Entity> void addEntityToBiomeCategories(RegistrySupplier<EntityType<T>> entity, List<Biome.Category> biomeCategories, int weight, int minGroupSize, int maxGroupSize, SpawnGroup classification) {
        for (Biome.Category category : biomeCategories) {
            Predicate<BiomeModifications.BiomeContext> predicate = (ctx) -> Objects.equals(ctx.getProperties()
                    .getCategory(), category);
            BiomeModifications.addProperties(predicate, (biomeContext, mutable) -> {
                modifier(biomeContext, mutable, entity, minGroupSize, maxGroupSize, classification, weight);
            });

        }
    }

    public static <T extends AnimalEntity> void setCreatureSpawnBiomes(RegistrySupplier<EntityType<T>> entity, List<Biome.Category> biomeCategories, int weight, int minGroupCountIn, int maxGroupCountIn) {
        addEntityToBiomeCategories(entity, biomeCategories, weight, minGroupCountIn, maxGroupCountIn, SpawnGroup.CREATURE);
    }

    public static <T extends WaterCreatureEntity> void setWaterCreatureSpawnBiomes(RegistrySupplier<EntityType<T>> entity, List<Biome.Category> biomeCategories, int weight, int minGroupCountIn, int maxGroupCountIn) {
        addEntityToBiomeCategories(entity, biomeCategories, weight, minGroupCountIn, maxGroupCountIn, SpawnGroup.WATER_CREATURE);
    }

    public static <T extends HostileEntity> void setMonsterSpawnBiomes(RegistrySupplier<EntityType<T>> entity, List<Biome.Category> biomeCategories, int weight, int minGroupCountIn, int maxGroupCountIn) {
        addEntityToBiomeCategories(entity, biomeCategories, weight, minGroupCountIn, maxGroupCountIn, SpawnGroup.MONSTER);
    }

    public static <T extends MobEntity> void setMobSpawnBiomes(RegistrySupplier<EntityType<T>> entity, List<Biome.Category> biomeCategories, int weight, int minGroupCountIn, int maxGroupCountIn) {
        addEntityToBiomeCategories(entity, biomeCategories, weight, minGroupCountIn, maxGroupCountIn, SpawnGroup.MISC);
    }

    public static List<Biome.Category> getBiomeCategoriesFromConfig(List<String> categories) {
        return categories.stream()
                .map(Biome.Category::byName)
                .toList();
    }

    public static List<String> convertForConfig(List<Biome.Category> categories) {
        return categories.stream().map(Biome.Category::getName).toList();
    }

}
