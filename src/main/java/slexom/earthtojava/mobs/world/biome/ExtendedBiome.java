package slexom.earthtojava.mobs.world.biome;

import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import java.util.List;
import java.util.function.Supplier;

public interface ExtendedBiome {
    void e2jSetSpawnSettings(SpawnSettings spawnSettings);
}
