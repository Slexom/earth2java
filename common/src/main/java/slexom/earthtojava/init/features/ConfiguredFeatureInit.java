package slexom.earthtojava.init.features;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.collection.DataPool;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;
import slexom.earthtojava.Earth2JavaMod;
import slexom.earthtojava.init.BlockInit;

public final class ConfiguredFeatureInit {

    public static final RegistrySupplier<ConfiguredFeature<RandomPatchFeatureConfig, Feature<RandomPatchFeatureConfig>>> E2J_FLOWERS_CONFIGURED_FEATURE;
    public static final RegistrySupplier<ConfiguredFeature<LakeFeature.Config, Feature<LakeFeature.Config>>> MUD_LAKE_CONFIGURED_FEATURE;

    static {
        E2J_FLOWERS_CONFIGURED_FEATURE = Earth2JavaMod.CONFIGURED_FEATURE_REGISTRAR.register(
                Earth2JavaMod.toIdentifier("e2j_flowers"),
                () -> new ConfiguredFeature<>(
                        Feature.FLOWER,
                        ConfiguredFeatureInit.createRandomPatchFeatureConfig(new WeightedBlockStateProvider(new DataPool.Builder<BlockState>().add(BlockInit.PINK_DAISY.get().getDefaultState(), 1).add(BlockInit.BUTTERCUP.get().getDefaultState(), 1)), 64)
                ));
        MUD_LAKE_CONFIGURED_FEATURE = Earth2JavaMod.CONFIGURED_FEATURE_REGISTRAR.register(
                Earth2JavaMod.toIdentifier("lake_mud"),
                () -> new ConfiguredFeature<>(
                        Feature.LAKE,
                        new LakeFeature.Config(BlockStateProvider.of(BlockInit.MUD_BLOCK.get().getDefaultState()), BlockStateProvider.of(Blocks.DIRT.getDefaultState()))
                ));
    }

    public static void init() {
    }

    private static RandomPatchFeatureConfig createRandomPatchFeatureConfig(BlockStateProvider block, int tries) {
        return ConfiguredFeatures.createRandomPatchFeatureConfig(tries, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(block)));
    }
}

