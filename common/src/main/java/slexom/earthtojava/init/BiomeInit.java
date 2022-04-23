package slexom.earthtojava.init;

import dev.architectury.registry.level.biome.BiomeModifications;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import slexom.earthtojava.Earth2JavaMod;
import slexom.earthtojava.config.ModConfig;
import slexom.earthtojava.init.features.PlacedFeatureInit;

import java.util.Objects;
import java.util.function.Predicate;

public class BiomeInit {
    private static final ModConfig config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();


    public static void init() {
        Predicate<BiomeModifications.BiomeContext> PLAINS = (ctx) -> Objects.equals(ctx.getProperties().getCategory(), Biome.Category.PLAINS);
        BiomeModifications.addProperties(PLAINS, (biomeContext, mutable) -> mutable.getGenerationProperties().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedFeatureInit.E2J_FLOWERS_PLACED_FEATURES.get()));

        if (config.mudLakeConfig.canGenerate && config.mudLakeConfig.mudLakeFrequency > 0) {
            Predicate<BiomeModifications.BiomeContext> FOREST = (ctx) -> Objects.equals(ctx.getProperties().getCategory(), Biome.Category.FOREST);
            BiomeModifications.addProperties(PLAINS, (biomeContext, mutable) -> mutable.getGenerationProperties().addFeature(GenerationStep.Feature.LAKES, PlacedFeatureInit.MUD_LAKE_PLACED_FEATURES.get()));
        }
    }

}
