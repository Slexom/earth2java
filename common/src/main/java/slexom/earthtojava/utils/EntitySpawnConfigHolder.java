package slexom.earthtojava.utils;

import dev.architectury.registry.level.biome.BiomeModifications;
import slexom.earthtojava.config.ModConfig;

import java.util.function.Predicate;

public record EntitySpawnConfigHolder(
        Predicate<BiomeModifications.BiomeContext> predicate,
        boolean canSpawn,
        int weight,
        int groupMin,
        int groupMax) {

    public EntitySpawnConfigHolder(Predicate<BiomeModifications.BiomeContext> predicate, ModConfig.EntityConfig config) {
        this(predicate, config.spawn, config.weight, config.groupMin, config.groupMax);
    }
}
