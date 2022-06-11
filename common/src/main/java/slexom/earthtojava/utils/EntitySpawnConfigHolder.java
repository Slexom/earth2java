package slexom.earthtojava.utils;

import net.minecraft.world.biome.Biome;
import slexom.earthtojava.config.ModConfig;

import java.util.List;

public record EntitySpawnConfigHolder(List<Biome.Category> biomeCategories, ModConfig.EntityConfig config) {
}
