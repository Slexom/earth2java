package slexom.earthtojava.mobs.mixins;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.SpawnSettings;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import slexom.earthtojava.mobs.world.biome.ExtendedBiome;

@Mixin(Biome.class)
public class MixinBiome implements ExtendedBiome {

    @Mutable
    @Shadow
    @Final
    private SpawnSettings spawnSettings;

    @Override
    public void e2jSetSpawnSettings(SpawnSettings spawnSettings) {
        this.spawnSettings = spawnSettings;
    }
}
