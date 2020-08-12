package slexom.earthtojava.mobs.mixins;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.biome.SpawnSettings;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import slexom.earthtojava.mobs.world.biome.ExtendedSpawnSettings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mixin(SpawnSettings.class)
public class MixinSpawnSettings implements ExtendedSpawnSettings {

    @Mutable
    @Shadow
    @Final
    private Map<SpawnGroup, List<SpawnSettings.SpawnEntry>> spawners;

    @Override
    public void addToSpawner(SpawnGroup spawnGroup, SpawnSettings.SpawnEntry spawnEntry) {
        List<SpawnSettings.SpawnEntry> spawnEntries = new ArrayList<>(this.spawners.get(spawnGroup));
        Map<SpawnGroup, List<SpawnSettings.SpawnEntry>> spawners = new HashMap<>(this.spawners);
        spawnEntries.add(spawnEntry);
        spawners.put(spawnGroup, spawnEntries);
        this.spawners = spawners;
    }

}
