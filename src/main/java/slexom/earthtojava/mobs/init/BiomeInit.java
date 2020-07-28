package slexom.earthtojava.mobs.init;

import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.ChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.NoiseHeightmapDecoratorConfig;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.RandomPatchFeatureConfig;
import net.minecraft.world.gen.feature.SingleStateFeatureConfig;
import net.minecraft.world.gen.placer.SimpleBlockPlacer;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import slexom.earthtojava.mobs.config.ModConfig;

public class BiomeInit {
    private static final ModConfig config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();

    public static void init() {
        Registry.BIOME.forEach(BiomeInit::handleBiome);
        RegistryEntryAddedCallback.event(Registry.BIOME).register((i, identifier, biome) -> handleBiome(biome));
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
        if(config.rubyOre.canGenerate) {
            if (isInOverworld(biome) && !isMushroom(biome)) {
                biome.addFeature(
                        GenerationStep.Feature.UNDERGROUND_ORES,
                        Feature.ORE
                                .configure(new OreFeatureConfig(OreFeatureConfig.Target.NATURAL_STONE, BlockInit.RUBY_ORE.getDefaultState(), 8))
                                .createDecoratedFeature(Decorator.COUNT_RANGE.configure(new RangeDecoratorConfig(config.rubyOre.count, config.rubyOre.bottomOffset, config.rubyOre.topOffset, config.rubyOre.maximum))));
            }
        }
    }

    private static void addMudLake(Biome biome) {
        if (isInOverworld(biome) && !isMushroom(biome)) {
            biome.addFeature(
                    GenerationStep.Feature.LOCAL_MODIFICATIONS,
                    FeatureInit.MUD_LAKE
                            .configure(new SingleStateFeatureConfig(BlockInit.MUD_BLOCK.getDefaultState()))
                            .createDecoratedFeature(Decorator.WATER_LAKE.configure(new ChanceDecoratorConfig(config.mudLakeFrequency)))
            );
        }
    }

    private static void addButtercup(Biome biome) {
        if (isInOverworld(biome) && biome.getCategory() == Biome.Category.PLAINS) {
            biome.addFeature(
                    GenerationStep.Feature.VEGETAL_DECORATION,
                    Feature.FLOWER
                            .configure((new RandomPatchFeatureConfig.Builder(new SimpleBlockStateProvider(BlockInit.BUTTERCUP.getDefaultState()), new SimpleBlockPlacer())).tries(64).build())
                            .createDecoratedFeature(Decorator.NOISE_HEIGHTMAP_32.configure(new NoiseHeightmapDecoratorConfig(-0.8D, 15, 4)))
            );
        }
    }

}
