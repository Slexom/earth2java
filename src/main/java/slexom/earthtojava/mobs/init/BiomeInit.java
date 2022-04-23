package slexom.earthtojava.init;

import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import slexom.earthtojava.Earth2JavaMod;
import slexom.earthtojava.config.ModConfig;

public class BiomeInit {
    private static final ModConfig config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();


    public static void init() {
        BiomeModifications.addFeature(BiomeSelectors.categories(Biome.Category.PLAINS), GenerationStep.Feature.VEGETAL_DECORATION, RegistryKey.of(BuiltinRegistries.PLACED_FEATURE.getKey(), new Identifier(Earth2JavaMod.MOD_ID, "e2j_flowers")));

        if (config.mudLakeConfig.canGenerate && config.mudLakeConfig.mudLakeFrequency > 0) {
            BiomeModifications.addFeature(BiomeSelectors.categories(Biome.Category.FOREST), GenerationStep.Feature.LAKES, RegistryKey.of(BuiltinRegistries.PLACED_FEATURE.getKey(), new Identifier(Earth2JavaMod.MOD_ID, "lake_mud")));
        }
    }

}
