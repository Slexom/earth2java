package slexom.earthtojava.init;

import dev.architectury.registry.level.biome.BiomeModifications;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import slexom.earthtojava.Earth2JavaMod;
import slexom.earthtojava.config.ModConfig;

import java.util.Objects;
import java.util.function.Predicate;

public class BiomeInit {
    private static final ModConfig config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();


    public static void init() {
        Predicate<BiomeModifications.BiomeContext> PLAINS = (ctx) -> Objects.equals(ctx.getProperties().getCategory(), Biome.Category.PLAINS);

        BiomeModifications.addFeature(BiomeSelectors.categories(Biome.Category.PLAINS), GenerationStep.Feature.VEGETAL_DECORATION, RegistryKey.of(BuiltinRegistries.PLACED_FEATURE.getKey(), new Identifier(Earth2JavaMod.MOD_ID, "e2j_flowers")));
BiomeModifications.addProperties(PLAINS, (biomeContext, mutable) -> biomeContext);



        if (config.mudLakeConfig.canGenerate && config.mudLakeConfig.mudLakeFrequency > 0) {
            BiomeModifications.addFeature(BiomeSelectors.categories(Biome.Category.FOREST), GenerationStep.Feature.LAKES, RegistryKey.of(BuiltinRegistries.PLACED_FEATURE.getKey(), new Identifier(Earth2JavaMod.MOD_ID, "lake_mud")));
        }
    }

}
