package slexom.earthtojava.fabric.init;

import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.intprovider.ClampedIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.*;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;
import slexom.earthtojava.Earth2JavaMod;
import slexom.earthtojava.config.ModConfig;
import slexom.earthtojava.init.BlockInit;
import slexom.earthtojava.init.FluidInit;

import java.util.List;

public class FeatureInit {

    private static final ModConfig config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();
    public static ConfiguredFeature<RandomPatchFeatureConfig, ?> E2J_FLOWERS_CONFIGURED_FEATURE;
    public static PlacedFeature E2J_FLOWERS_PLACED_FEATURES;
    public static ConfiguredFeature<?, ?> MUD_LAKE_CONFIGURED_FEATURE;
    public static PlacedFeature MUD_LAKE_PLACED_FEATURES;

    public static void init() {
        E2J_FLOWERS_CONFIGURED_FEATURE = registerConfiguredFeature("e2j_flowers", Feature.FLOWER, FeatureInit.createRandomPatchFeatureConfig(new WeightedBlockStateProvider(new DataPool.Builder<BlockState>().add(BlockInit.PINK_DAISY.get().getDefaultState(), 1).add(BlockInit.BUTTERCUP.get().getDefaultState(), 1)), 64));
        E2J_FLOWERS_PLACED_FEATURES = registerPlacedFeature("e2j_flowers", E2J_FLOWERS_CONFIGURED_FEATURE, RarityFilterPlacementModifier.of(7), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, CountPlacementModifier.of(ClampedIntProvider.create(UniformIntProvider.create(-3, 1), 0, 1)), BiomePlacementModifier.of());
        MUD_LAKE_CONFIGURED_FEATURE = registerConfiguredFeature("lake_mud", Feature.LAKE, new LakeFeature.Config(BlockStateProvider.of(BlockInit.MUD_BLOCK.get().getDefaultState()), BlockStateProvider.of(Blocks.DIRT.getDefaultState())));
        MUD_LAKE_PLACED_FEATURES = registerPlacedFeature("lake_mud", MUD_LAKE_CONFIGURED_FEATURE, RarityFilterPlacementModifier.of(config.mudLakeConfig.mudLakeFrequency), SquarePlacementModifier.of(), PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP, BiomePlacementModifier.of());
    }

    private static RandomPatchFeatureConfig createRandomPatchFeatureConfig(BlockStateProvider block, int tries) {
        return ConfiguredFeatures.createRandomPatchFeatureConfig(tries, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(block)));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> ConfiguredFeature<FC, F> registerConfiguredFeature(String registryName, F feature, FC config) {
        final Identifier identifier = new Identifier(Earth2JavaMod.MOD_ID, registryName);
        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, identifier, new ConfiguredFeature<>(feature, config));
    }


    private static PlacedFeature registerPlacedFeature(String registryName, ConfiguredFeature<?, ?> configuredFeature, PlacementModifier... placementModifiers) {
        return Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(Earth2JavaMod.MOD_ID, registryName), new PlacedFeature(RegistryEntry.of(configuredFeature), List.of(placementModifiers)));
    }
}

