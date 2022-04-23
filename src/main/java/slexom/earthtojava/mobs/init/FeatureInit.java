package slexom.earthtojava.init;

import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.intprovider.ClampedIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.BiomePlacementModifier;
import net.minecraft.world.gen.placementmodifier.CountPlacementModifier;
import net.minecraft.world.gen.placementmodifier.RarityFilterPlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;
import slexom.earthtojava.config.ModConfig;

public class FeatureInit {

    private static final ModConfig config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();
    public static ConfiguredFeature<RandomPatchFeatureConfig, ?> E2J_FLOWERS_CONFIGURED_FEATURE;
    public static PlacedFeature E2J_FLOWERS_PLACED_FEATURES;
    public static ConfiguredFeature<?, ?> MUD_LAKE_CONFIGURED_FEATURE;
    public static PlacedFeature MUD_LAKE_PLACED_FEATURES;

    public static void init() {
        E2J_FLOWERS_CONFIGURED_FEATURE = RegisterHelper.registerConfiguredFeature("e2j_flowers", Feature.FLOWER, FeatureInit.createRandomPatchFeatureConfig(new WeightedBlockStateProvider(new DataPool.Builder<BlockState>().add(BlockInit.PINK_DAISY.get().getDefaultState(), 1).add(BlockInit.BUTTERCUP.get().getDefaultState(), 1)), 64));
        E2J_FLOWERS_PLACED_FEATURES = RegisterHelper.registerPlacedFeature("e2j_flowers", E2J_FLOWERS_CONFIGURED_FEATURE, RarityFilterPlacementModifier.of(7), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, CountPlacementModifier.of(ClampedIntProvider.create(UniformIntProvider.create(-3, 1), 0, 1)), BiomePlacementModifier.of());
        MUD_LAKE_CONFIGURED_FEATURE = RegisterHelper.registerConfiguredFeature("lake_mud", Feature.LAKE, new LakeFeature.Config(BlockStateProvider.of(BlockInit.MUD_BLOCK.getDefaultState()), BlockStateProvider.of(Blocks.DIRT.getDefaultState())));
        MUD_LAKE_PLACED_FEATURES = RegisterHelper.registerPlacedFeature("lake_mud", MUD_LAKE_CONFIGURED_FEATURE, RarityFilterPlacementModifier.of(config.mudLakeConfig.mudLakeFrequency), SquarePlacementModifier.of(), PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP, BiomePlacementModifier.of());
    }

    private static RandomPatchFeatureConfig createRandomPatchFeatureConfig(BlockStateProvider block, int tries) {
        return ConfiguredFeatures.createRandomPatchFeatureConfig(tries, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(block)));
    }
}

