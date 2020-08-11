package slexom.earthtojava.mobs.init;

import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import slexom.earthtojava.mobs.config.ModConfig;
import slexom.earthtojava.mobs.world.gen.feature.ModifiableGeneration;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class BiomeInit {
    private static final ModConfig config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();


    public static void init() {
        BuiltinRegistries.BIOME.forEach(BiomeInit::handleBiome);
        RegistryEntryAddedCallback.event(BuiltinRegistries.BIOME).register((i, identifier, biome) -> handleBiome(biome));
    }

    private static void handleBiome(Biome biome) {
        addOres(biome);
        addMudLake(biome);
        addButtercup(biome);
    }

    private static void addOres(Biome biome) {
        addRubyOre(biome);
    }

    private static boolean isInOverworld(Biome biome) {
        return biome.getCategory() != Biome.Category.NETHER && biome.getCategory() != Biome.Category.THEEND;
    }

    private static boolean isMushroom(Biome biome) {
        return biome.getCategory() == Biome.Category.MUSHROOM;
    }

    private static void addRubyOre(Biome biome) {
        if (config.rubyOre.canGenerate) {
            if (isInOverworld(biome) && !isMushroom(biome)) {
                addFeature(biome, GenerationStep.Feature.UNDERGROUND_DECORATION, FeatureInit.RUBY_ORE);

//                biome.getGenerationSettings().getFeatures().add(GenerationStep.Feature.UNDERGROUND_DECORATION.ordinal(), (List<Supplier<ConfiguredFeature<?, ?>>>) FeatureInit.RUBY_ORE);


//                biome.addFeature(
//                        GenerationStep.Feature.UNDERGROUND_ORES,
//                        Feature.ORE
//                                .configure(new OreFeatureConfig(OreFeatureConfig.Target.NATURAL_STONE, BlockInit.RUBY_ORE.getDefaultState(), 8))
//                                .createDecoratedFeature(Decorator.COUNT_RANGE.configure(new RangeDecoratorConfig(config.rubyOre.count, config.rubyOre.bottomOffset, config.rubyOre.topOffset, config.rubyOre.maximum))));
            }
        }
    }

    private static void addMudLake(Biome biome) {
        if (isInOverworld(biome) && !isMushroom(biome)) {
//            biome.addFeature(
//                    GenerationStep.Feature.LOCAL_MODIFICATIONS,
//                    FeatureInit.MUD_LAKE
//                            .configure(new SingleStateFeatureConfig(BlockInit.MUD_BLOCK.getDefaultState()))
//                            .createDecoratedFeature(Decorator.WATER_LAKE.configure(new ChanceDecoratorConfig(config.mudLakeFrequency)))
//            );
        }
    }

    private static void addButtercup(Biome biome) {
        if (isInOverworld(biome) && biome.getCategory() == Biome.Category.PLAINS) {
//            biome.addFeature(
//                    GenerationStep.Feature.VEGETAL_DECORATION,
//                    Feature.FLOWER
//                            .configure((new RandomPatchFeatureConfig.Builder(new SimpleBlockStateProvider(BlockInit.BUTTERCUP.getDefaultState()), new SimpleBlockPlacer())).tries(64).build())
//                            .createDecoratedFeature(Decorator.NOISE_HEIGHTMAP_32.configure(new NoiseHeightmapDecoratorConfig(-0.8D, 15, 4)))
//            );
        }
    }


    private static void addFeature(Biome biome, GenerationStep.Feature step, ConfiguredFeature<?, ?> feature) {
        List<List<Supplier<ConfiguredFeature<?, ?>>>> features = biome.getGenerationSettings().getFeatures();
        if (!(features instanceof ArrayList)) features = new ArrayList<>(features);
        int index = step.ordinal();
        for (int i = 0; i <= index; i++) {
            if (features.size() <= i) features.add(new ArrayList<>());
            else if (!(features.get(i) instanceof ArrayList)) features.set(i, new ArrayList<>(features.get(i)));
        }
        features.get(index).add(() -> feature);
        ((ModifiableGeneration) biome.getGenerationSettings()).setFeatures(features);
    }

}
