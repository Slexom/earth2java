package slexom.earthtojava.mobs.init;

import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.intprovider.ClampedIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.decorator.BiomePlacementModifier;
import net.minecraft.world.gen.decorator.CountPlacementModifier;
import net.minecraft.world.gen.decorator.RarityFilterPlacementModifier;
import net.minecraft.world.gen.decorator.SquarePlacementModifier;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;
import slexom.earthtojava.mobs.Earth2JavaMod;
import slexom.earthtojava.mobs.config.ModConfig;

public class FeatureInit {

    private static final ModConfig config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();
    public static ConfiguredFeature<RandomPatchFeatureConfig, ?> E2J_FLOWERS_CONFIGURED_FEATURE;
    public static PlacedFeature E2J_FLOWERS_PLACED_FEATURES;
    public static ConfiguredFeature<?, ?> FLOWER_BUTTERCUP_CONFIGURED_FEATURE;
    public static ConfiguredFeature<?, ?> FLOWER_PINK_DAISY_CONFIGURED_FEATURE;
    public static ConfiguredFeature<?, ?> MUD_LAKE_CONFIGURED_FEATURE;


    public static void init() {
        DataPool.Builder<BlockState> E2J_FLOWERS_DATA_POOL = new DataPool.Builder<BlockState>().add(BlockInit.PINK_DAISY.getDefaultState(), 1).add(BlockInit.BUTTERCUP.getDefaultState(), 1);
        E2J_FLOWERS_CONFIGURED_FEATURE = Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(Earth2JavaMod.MOD_ID, "e2j_flowers"), (Feature.FLOWER.configure(FeatureInit.createRandomPatchFeatureConfig(new WeightedBlockStateProvider(E2J_FLOWERS_DATA_POOL), 64))));

        E2J_FLOWERS_PLACED_FEATURES = Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(Earth2JavaMod.MOD_ID, "e2j_flowers"), E2J_FLOWERS_CONFIGURED_FEATURE.withPlacement(RarityFilterPlacementModifier.of(7), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, CountPlacementModifier.of(ClampedIntProvider.create(UniformIntProvider.create(-3, 1), 0, 1)), BiomePlacementModifier.of()));
        // FLOWER_BUTTERCUP_CONFIGURED_FEATURE = Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(Earth2JavaMod.MOD_ID, "flower_buttercup"), (Feature.FLOWER.configure((new RandomPatchFeatureConfig.Builder(new SimpleBlockStateProvider(BlockInit.BUTTERCUP.getDefaultState()), SimpleBlockPlacer.INSTANCE)).tries(64).build()).decorate(ConfiguredFeatures.Decorators.SPREAD_32_ABOVE).decorate(ConfiguredFeatures.Decorators.HEIGHTMAP).spreadHorizontally()).decorate(Decorator.COUNT_NOISE.configure(new CountNoiseDecoratorConfig(-0.8D, 15, 4))));
        // FLOWER_PINK_DAISY_CONFIGURED_FEATURE = Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(Earth2JavaMod.MOD_ID, "flower_pink_daisy"), (Feature.FLOWER.configure((new RandomPatchFeatureConfig.Builder(new SimpleBlockStateProvider(BlockInit.PINK_DAISY.getDefaultState()), SimpleBlockPlacer.INSTANCE)).tries(64).build()).decorate(ConfiguredFeatures.Decorators.SPREAD_32_ABOVE).decorate(ConfiguredFeatures.Decorators.HEIGHTMAP).spreadHorizontally()).decorate(Decorator.COUNT_NOISE.configure(new CountNoiseDecoratorConfig(-0.8D, 15, 4))));
        // MUD_LAKE_CONFIGURED_FEATURE = Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(Earth2JavaMod.MOD_ID, "lake_mud"), ((Feature.LAKE.configure(new SingleStateFeatureConfig(BlockInit.MUD_BLOCK.getDefaultState())).range(ConfiguredFeatures.Decorators.BOTTOM_TO_TOP)).spreadHorizontally()).applyChance(config.mudLakeConfig.mudLakeFrequency));
    }

    private static RandomPatchFeatureConfig createRandomPatchFeatureConfig(BlockStateProvider block, int tries) {
        return ConfiguredFeatures.createRandomPatchFeatureConfig(tries, Feature.SIMPLE_BLOCK.configure(new SimpleBlockFeatureConfig(block)).withInAirFilter());
    }
}

