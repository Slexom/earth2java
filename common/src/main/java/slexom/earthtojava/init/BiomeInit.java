package slexom.earthtojava.init;

import dev.architectury.registry.level.biome.BiomeModifications;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.tag.BiomeTags;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import slexom.earthtojava.config.ModConfig;
import slexom.earthtojava.init.features.PlacedFeatureInit;

import java.util.Objects;
import java.util.function.Predicate;

public final class BiomeInit {
    private static final ModConfig config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();

    private BiomeInit() {
        throw new IllegalStateException("Utility class");
    }

    public static void init() {
        Predicate<BiomeModifications.BiomeContext> plainsPredicate = ctx -> Objects.equals(ctx.getKey().get(), BiomeKeys.PLAINS.getValue());
        BiomeModifications.addProperties(plainsPredicate, (biomeContext, mutable) -> mutable.getGenerationProperties().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, RegistryEntry.of(PlacedFeatureInit.E2J_FLOWERS_PLACED_FEATURES.get())));
    }

}
