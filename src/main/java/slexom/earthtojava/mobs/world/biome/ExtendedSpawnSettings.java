package slexom.earthtojava.mobs.world.biome;

import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.biome.SpawnSettings;

public interface ExtendedSpawnSettings {
    void e2jAddToSpawner(SpawnGroup spawnGroup, SpawnSettings.SpawnEntry spawnEntry);
}
