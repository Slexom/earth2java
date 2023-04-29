package slexom.earthtojava.init;

import dev.architectury.registry.level.biome.BiomeModifications;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import slexom.earthtojava.Earth2JavaMod;

import java.util.Objects;
import java.util.function.Predicate;

public final class BiomeInit {

    private BiomeInit() {
        throw new IllegalStateException("Utility class");
    }

    public static void init() {
        Predicate<BiomeModifications.BiomeContext> plainsPredicate = ctx -> Objects.equals(ctx.getKey().get(), BiomeKeys.PLAINS.getValue());
        BiomeModifications.addProperties(plainsPredicate, (biomeContext, mutable) -> mutable.getGenerationProperties().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(Earth2JavaMod.MOD_ID + ":earth_flowers"))));
    }

}
