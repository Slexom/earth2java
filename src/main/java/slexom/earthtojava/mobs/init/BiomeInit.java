package slexom.earthtojava.mobs.init;

import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;

public class BiomeInit {

    public static void init() {
        Registry.BIOME.forEach(BiomeInit::handleBiome);
        RegistryEntryAddedCallback.event(Registry.BIOME).register((i, identifier, biome) -> handleBiome(biome));
    }

    private static void handleBiome(Biome biome) {
        addOres(biome);
    }

    private static void addOres(Biome biome) {
        addRubyOre(biome);
    }

    private static void addRubyOre(Biome biome) {
        if (biome.getCategory() != Biome.Category.NETHER && biome.getCategory() != Biome.Category.THEEND) {
            biome.addFeature(
                    GenerationStep.Feature.UNDERGROUND_ORES,
                    Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Target.NATURAL_STONE, BlockInit.RUBY_ORE.getDefaultState(), 8)).createDecoratedFeature(Decorator.COUNT_RANGE.configure(new RangeDecoratorConfig(2, 0, 0, 16))));
        }
    }


}
