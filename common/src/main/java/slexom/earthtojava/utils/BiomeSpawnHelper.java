package slexom.earthtojava.utils;

import dev.architectury.registry.level.biome.BiomeModifications;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.WaterCreatureEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.tag.BiomeTags;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.SpawnSettings;

import java.util.Objects;
import java.util.function.Predicate;


public final class BiomeSpawnHelper {

    public static final Predicate<BiomeModifications.BiomeContext> ALBINO_COW_SPAWN_BIOMES_PREDICATE = ctx -> ctx.hasTag(BiomeTags.IS_MOUNTAIN) || Objects.equals(ctx.getKey().get(), BiomeKeys.PLAINS.getValue());
    public static final Predicate<BiomeModifications.BiomeContext> AMBER_CHICKEN_SPAWN_BIOMES_PREDICATE = ctx -> ctx.hasTag(BiomeTags.IS_SAVANNA) || Objects.equals(ctx.getKey().get(), BiomeKeys.DESERT.getValue());
    public static final Predicate<BiomeModifications.BiomeContext> ASHEN_COW_SPAWN_BIOMES_PREDICATE = ctx -> ctx.hasTag(BiomeTags.IS_MOUNTAIN);
    public static final Predicate<BiomeModifications.BiomeContext> BOLD_STRIPED_RABBIT_SPAWN_BIOMES_PREDICATE = ctx -> ctx.hasTag(BiomeTags.IS_MOUNTAIN) || ctx.hasTag(BiomeTags.IS_FOREST) || Objects.equals(ctx.getKey().get(), BiomeKeys.PLAINS.getValue());
    public static final Predicate<BiomeModifications.BiomeContext> BONE_SPIDER_SPAWN_BIOMES_PREDICATE = ctx -> ctx.hasTag(BiomeTags.IS_BADLANDS);
    public static final Predicate<BiomeModifications.BiomeContext> BOULDERING_ZOMBIE_SPAWN_BIOMES_PREDICATE = ctx -> ctx.hasTag(BiomeTags.IS_MOUNTAIN);
    public static final Predicate<BiomeModifications.BiomeContext> BRONZED_CHICKEN_SPAWN_BIOMES_PREDICATE = ctx -> Objects.equals(ctx.getKey().get(), BiomeKeys.PLAINS.getValue());
    public static final Predicate<BiomeModifications.BiomeContext> CLUCKSHROOM_SPAWN_BIOMES_PREDICATE = ctx -> Objects.equals(ctx.getKey().get(), BiomeKeys.MUSHROOM_FIELDS.getValue());
    public static final Predicate<BiomeModifications.BiomeContext> COOKIE_COW_SPAWN_BIOMES_PREDICATE = ctx -> Objects.equals(ctx.getKey().get(), BiomeKeys.PLAINS.getValue()) || ctx.hasTag(BiomeTags.IS_TAIGA) || ctx.hasTag(BiomeTags.IS_MOUNTAIN);
    public static final Predicate<BiomeModifications.BiomeContext> CREAM_COW_SPAWN_BIOMES_PREDICATE = ctx -> Objects.equals(ctx.getKey().get(), BiomeKeys.PLAINS.getValue()) || ctx.hasTag(BiomeTags.IS_FOREST) || ctx.hasTag(BiomeTags.IS_JUNGLE) || ctx.hasTag(BiomeTags.IS_SAVANNA);
    public static final Predicate<BiomeModifications.BiomeContext> DAIRY_COW_SPAWN_BIOMES_PREDICATE = ctx -> Objects.equals(ctx.getKey().get(), BiomeKeys.PLAINS.getValue()) || ctx.hasTag(BiomeTags.IS_FOREST) || ctx.hasTag(BiomeTags.IS_JUNGLE) || ctx.hasTag(BiomeTags.IS_MOUNTAIN);
    public static final Predicate<BiomeModifications.BiomeContext> FANCY_CHICKEN_SPAWN_BIOMES_PREDICATE = ctx -> Objects.equals(ctx.getKey().get(), BiomeKeys.PLAINS.getValue()) || ctx.hasTag(BiomeTags.IS_FOREST);//
    public static final Predicate<BiomeModifications.BiomeContext> FLECKED_SHEEP_SPAWN_BIOMES_PREDICATE = ctx -> Objects.equals(ctx.getKey().get(), BiomeKeys.PLAINS.getValue()) || ctx.hasTag(BiomeTags.IS_FOREST) || ctx.hasTag(BiomeTags.IS_MOUNTAIN) || ctx.hasTag(BiomeTags.IS_TAIGA);
    public static final Predicate<BiomeModifications.BiomeContext> FRECKLED_RABBIT_SPAWN_BIOMES_PREDICATE = ctx -> Objects.equals(ctx.getKey().get(), BiomeKeys.PLAINS.getValue()) || ctx.hasTag(BiomeTags.IS_FOREST) || ctx.hasTag(BiomeTags.IS_JUNGLE) || ctx.hasTag(BiomeTags.IS_SAVANNA);
    public static final Predicate<BiomeModifications.BiomeContext> FUZZY_SHEEP_SPAWN_BIOMES_PREDICATE = ctx -> Objects.equals(ctx.getKey().get(), BiomeKeys.PLAINS.getValue()) || ctx.hasTag(BiomeTags.IS_FOREST) || ctx.hasTag(BiomeTags.IS_JUNGLE) || ctx.hasTag(BiomeTags.IS_SAVANNA);
    public static final Predicate<BiomeModifications.BiomeContext> GOLD_CRESTED_CHICKEN_SPAWN_BIOMES_PREDICATE = ctx -> Objects.equals(ctx.getKey().get(), BiomeKeys.PLAINS.getValue()) || ctx.hasTag(BiomeTags.IS_FOREST) || ctx.hasTag(BiomeTags.IS_MOUNTAIN) || ctx.hasTag(BiomeTags.IS_SAVANNA);
    public static final Predicate<BiomeModifications.BiomeContext> HARELEQUIN_RABBIT_SPAWN_BIOMES_PREDICATE = ctx -> Objects.equals(ctx.getKey().get(), BiomeKeys.PLAINS.getValue());
    public static final Predicate<BiomeModifications.BiomeContext> HORNED_SHEEP_SPAWN_BIOMES_PREDICATE = ctx -> ctx.hasTag(BiomeTags.IS_MOUNTAIN) || Objects.equals(ctx.getKey().get(), BiomeKeys.PLAINS.getValue());
    public static final Predicate<BiomeModifications.BiomeContext> INKY_SHEEP_SPAWN_BIOMES_PREDICATE = ctx -> ctx.hasTag(BiomeTags.IS_MOUNTAIN) || Objects.equals(ctx.getKey().get(), BiomeKeys.PLAINS.getValue());
    public static final Predicate<BiomeModifications.BiomeContext> JOLLY_LLAMA_SPAWN_BIOMES_PREDICATE = ctx -> ctx.hasTag(BiomeTags.IS_TAIGA) || ctx.hasTag(BiomeTags.VILLAGE_SNOWY_HAS_STRUCTURE);
    public static final Predicate<BiomeModifications.BiomeContext> JUMBO_RABBIT_SPAWN_BIOMES_PREDICATE = ctx -> Objects.equals(ctx.getKey().get(), BiomeKeys.PLAINS.getValue());
    public static final Predicate<BiomeModifications.BiomeContext> LOBBER_ZOMBIE_SPAWN_BIOMES_PREDICATE = ctx -> ctx.hasTag(BiomeTags.SWAMP_HUT_HAS_STRUCTURE);
    public static final Predicate<BiomeModifications.BiomeContext> LONG_NOSED_SHEEP_SPAWN_BIOMES_PREDICATE = ctx -> ctx.hasTag(BiomeTags.IS_SAVANNA) || Objects.equals(ctx.getKey().get(), BiomeKeys.DESERT.getValue());
    public static final Predicate<BiomeModifications.BiomeContext> MIDNIGHT_CHICKEN_SPAWN_BIOMES_PREDICATE = ctx -> ctx.hasTag(BiomeTags.IS_FOREST) || ctx.hasTag(BiomeTags.IS_JUNGLE);
    public static final Predicate<BiomeModifications.BiomeContext> MOOBLOOM_SPAWN_BIOMES_PREDICATE = ctx -> Objects.equals(ctx.getKey().get(), BiomeKeys.FLOWER_FOREST.getValue()) || Objects.equals(ctx.getKey().get(), BiomeKeys.MEADOW.getValue());
    public static final Predicate<BiomeModifications.BiomeContext> MOOLIP_SPAWN_BIOMES_PREDICATE = ctx -> Objects.equals(ctx.getKey().get(), BiomeKeys.FLOWER_FOREST.getValue()) || Objects.equals(ctx.getKey().get(), BiomeKeys.MEADOW.getValue());
    public static final Predicate<BiomeModifications.BiomeContext> MOTTLED_PIG_SPAWN_BIOMES_PREDICATE = ctx -> Objects.equals(ctx.getKey().get(), BiomeKeys.PLAINS.getValue()) || ctx.hasTag(BiomeTags.IS_FOREST) || ctx.hasTag(BiomeTags.IS_MOUNTAIN);
    public static final Predicate<BiomeModifications.BiomeContext> MUDDY_FOOT_RABBIT_SPAWN_BIOMES_PREDICATE = ctx -> Objects.equals(ctx.getKey().get(), BiomeKeys.PLAINS.getValue());
    public static final Predicate<BiomeModifications.BiomeContext> MUDDY_PIG_SPAWN_BIOMES_PREDICATE = ctx -> Objects.equals(ctx.getKey().get(), BiomeKeys.PLAINS.getValue()) || ctx.hasTag(BiomeTags.IS_RIVER) || ctx.hasTag(BiomeTags.IS_MOUNTAIN);
    public static final Predicate<BiomeModifications.BiomeContext> PALE_PIG_SPAWN_BIOMES_PREDICATE = ctx -> ctx.hasTag(BiomeTags.IS_TAIGA) || ctx.hasTag(BiomeTags.VILLAGE_SNOWY_HAS_STRUCTURE);
    public static final Predicate<BiomeModifications.BiomeContext> PATCHED_SHEEP_SPAWN_BIOMES_PREDICATE = ctx -> Objects.equals(ctx.getKey().get(), BiomeKeys.PLAINS.getValue()) || ctx.hasTag(BiomeTags.IS_MOUNTAIN);
    public static final Predicate<BiomeModifications.BiomeContext> PIEBALD_PIG_SPAWN_BIOMES_PREDICATE = ctx -> Objects.equals(ctx.getKey().get(), BiomeKeys.PLAINS.getValue()) || ctx.hasTag(BiomeTags.IS_FOREST) || ctx.hasTag(BiomeTags.IS_MOUNTAIN) || ctx.hasTag(BiomeTags.IS_SAVANNA) || ctx.hasTag(BiomeTags.IS_TAIGA);
    public static final Predicate<BiomeModifications.BiomeContext> PINK_FOOTED_PIG_SPAWN_BIOMES_PREDICATE = ctx -> Objects.equals(ctx.getKey().get(), BiomeKeys.PLAINS.getValue()) || ctx.hasTag(BiomeTags.IS_FOREST);
    public static final Predicate<BiomeModifications.BiomeContext> PINTO_COW_SPAWN_BIOMES_PREDICATE = ctx -> Objects.equals(ctx.getKey().get(), BiomeKeys.PLAINS.getValue()) || ctx.hasTag(BiomeTags.IS_MOUNTAIN) || ctx.hasTag(BiomeTags.IS_TAIGA);
    public static final Predicate<BiomeModifications.BiomeContext> RAINBOW_SHEEP_SPAWN_BIOMES_PREDICATE = ctx -> Objects.equals(ctx.getKey().get(), BiomeKeys.PLAINS.getValue()) || ctx.hasTag(BiomeTags.IS_FOREST);
    public static final Predicate<BiomeModifications.BiomeContext> ROCKY_SHEEP_SPAWN_BIOMES_PREDICATE = ctx -> Objects.equals(ctx.getKey().get(), BiomeKeys.PLAINS.getValue()) || ctx.hasTag(BiomeTags.IS_MOUNTAIN);
    public static final Predicate<BiomeModifications.BiomeContext> SKELETON_WOLF_SPAWN_BIOMES_PREDICATE = ctx -> ctx.hasTag(BiomeTags.IS_FOREST) || ctx.hasTag(BiomeTags.IS_TAIGA) || ctx.hasTag(BiomeTags.IS_BADLANDS);
    public static final Predicate<BiomeModifications.BiomeContext> SKEWBALD_CHICKEN_SPAWN_BIOMES_PREDICATE = ctx -> Objects.equals(ctx.getKey().get(), BiomeKeys.PLAINS.getValue()) || ctx.hasTag(BiomeTags.IS_FOREST) || ctx.hasTag(BiomeTags.IS_JUNGLE);
    public static final Predicate<BiomeModifications.BiomeContext> SOOTY_PIG_SPAWN_BIOMES_PREDICATE = ctx -> Objects.equals(ctx.getKey().get(), BiomeKeys.PLAINS.getValue()) || ctx.hasTag(BiomeTags.IS_SAVANNA) || ctx.hasTag(BiomeTags.IS_JUNGLE);
    public static final Predicate<BiomeModifications.BiomeContext> SPOTTED_PIG_SPAWN_BIOMES_PREDICATE = ctx -> ctx.hasTag(BiomeTags.SWAMP_HUT_HAS_STRUCTURE);
    public static final Predicate<BiomeModifications.BiomeContext> STORMY_CHICKEN_SPAWN_BIOMES_PREDICATE = ctx -> Objects.equals(ctx.getKey().get(), BiomeKeys.PLAINS.getValue()) || ctx.hasTag(BiomeTags.IS_MOUNTAIN) || ctx.hasTag(BiomeTags.IS_TAIGA);
    public static final Predicate<BiomeModifications.BiomeContext> SUNSET_COW_SPAWN_BIOMES_PREDICATE = ctx -> ctx.hasTag(BiomeTags.IS_SAVANNA);
    public static final Predicate<BiomeModifications.BiomeContext> TROPICAL_SLIME_SPAWN_BIOMES_PREDICATE = ctx -> ctx.hasTag(BiomeTags.IS_BEACH);
    public static final Predicate<BiomeModifications.BiomeContext> UMBRA_COW_SPAWN_BIOMES_PREDICATE = ctx -> ctx.hasTag(BiomeTags.IS_TAIGA) || ctx.hasTag(BiomeTags.IS_MOUNTAIN) || ctx.hasTag(BiomeTags.VILLAGE_SNOWY_HAS_STRUCTURE);
    public static final Predicate<BiomeModifications.BiomeContext> VESTED_RABBIT_SPAWN_BIOMES_PREDICATE = ctx -> Objects.equals(ctx.getKey().get(), BiomeKeys.PLAINS.getValue());
    public static final Predicate<BiomeModifications.BiomeContext> VILER_WITCH_SPAWN_BIOMES_PREDICATE = ctx -> ctx.hasTag(BiomeTags.SWAMP_HUT_HAS_STRUCTURE);
    public static final Predicate<BiomeModifications.BiomeContext> WOOLY_COW_SPAWN_BIOMES_PREDICATE = ctx -> ctx.hasTag(BiomeTags.IS_TAIGA);

    private BiomeSpawnHelper() {
    }

    private static <T extends Entity> void addEntityToBiomes(RegistrySupplier<EntityType<T>> entity, Predicate<BiomeModifications.BiomeContext> predicate, int weight, int minGroupSize, int maxGroupSize, SpawnGroup classification) {
         BiomeModifications.addProperties(predicate, (biomeContext, mutable) -> {
            SpawnSettings.SpawnEntry spawnEntry = new SpawnSettings.SpawnEntry(entity.get(), weight, minGroupSize, maxGroupSize);
            mutable.getSpawnProperties().addSpawn(classification, spawnEntry);
        });
    }

    public static <T extends AnimalEntity> void setCreatureSpawnBiomes(RegistrySupplier<EntityType<T>> entity, Predicate<BiomeModifications.BiomeContext> predicate, int weight, int minGroupCountIn, int maxGroupCountIn) {
        addEntityToBiomes(entity, predicate, weight, minGroupCountIn, maxGroupCountIn, SpawnGroup.CREATURE);
    }

    public static <T extends WaterCreatureEntity> void setWaterCreatureSpawnBiomes(RegistrySupplier<EntityType<T>> entity, Predicate<BiomeModifications.BiomeContext> predicate, int weight, int minGroupCountIn, int maxGroupCountIn) {
        addEntityToBiomes(entity, predicate, weight, minGroupCountIn, maxGroupCountIn, SpawnGroup.WATER_CREATURE);
    }

    public static <T extends HostileEntity> void setMonsterSpawnBiomes(RegistrySupplier<EntityType<T>> entity, Predicate<BiomeModifications.BiomeContext> predicate, int weight, int minGroupCountIn, int maxGroupCountIn) {
        addEntityToBiomes(entity, predicate, weight, minGroupCountIn, maxGroupCountIn, SpawnGroup.MONSTER);
    }

    public static <T extends MobEntity> void setMobSpawnBiomes(RegistrySupplier<EntityType<T>> entity, Predicate<BiomeModifications.BiomeContext> predicate, int weight, int minGroupCountIn, int maxGroupCountIn) {
        addEntityToBiomes(entity, predicate, weight, minGroupCountIn, maxGroupCountIn, SpawnGroup.MISC);
    }

}
