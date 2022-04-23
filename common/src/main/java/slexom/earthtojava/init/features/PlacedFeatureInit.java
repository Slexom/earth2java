package slexom.earthtojava.init.features;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.intprovider.ClampedIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.*;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;
import slexom.earthtojava.Earth2JavaMod;
import slexom.earthtojava.config.ModConfig;
import slexom.earthtojava.init.BlockInit;

import java.util.List;

public class PlacedFeatureInit {
     private static final DeferredRegister<PlacedFeature> PLACED_FEATURE_DEFERRED_REGISTER = DeferredRegister.create(Earth2JavaMod.MOD_ID, Registry.PLACED_FEATURE_KEY);

    private static final ModConfig config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();
     public static RegistrySupplier<PlacedFeature> E2J_FLOWERS_PLACED_FEATURES;
     public static RegistrySupplier<PlacedFeature> MUD_LAKE_PLACED_FEATURES;

    static {
        E2J_FLOWERS_PLACED_FEATURES = registerPlacedFeature("e2j_flowers", ConfiguredFeatureInit.E2J_FLOWERS_CONFIGURED_FEATURE.get(), RarityFilterPlacementModifier.of(7), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, CountPlacementModifier.of(ClampedIntProvider.create(UniformIntProvider.create(-3, 1), 0, 1)), BiomePlacementModifier.of());
        MUD_LAKE_PLACED_FEATURES = registerPlacedFeature("lake_mud", ConfiguredFeatureInit.MUD_LAKE_CONFIGURED_FEATURE.get(), RarityFilterPlacementModifier.of(config.mudLakeConfig.mudLakeFrequency), SquarePlacementModifier.of(), PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP, BiomePlacementModifier.of());
    }

    public static void init() {

        PLACED_FEATURE_DEFERRED_REGISTER.register();
    }


    public static RegistrySupplier<PlacedFeature> registerPlacedFeature(String registryName, ConfiguredFeature<?, ?> configuredFeature, PlacementModifier... placementModifiers) {
        return PLACED_FEATURE_DEFERRED_REGISTER.register(registryName, () -> new PlacedFeature(RegistryEntry.of(configuredFeature), List.of(placementModifiers)));
    }


}

