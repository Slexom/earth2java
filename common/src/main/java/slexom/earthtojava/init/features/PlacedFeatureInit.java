package slexom.earthtojava.init.features;

import dev.architectury.registry.registries.RegistrySupplier;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.util.math.intprovider.ClampedIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.placementmodifier.*;
import slexom.earthtojava.Earth2JavaMod;
import slexom.earthtojava.config.ModConfig;
import slexom.earthtojava.utils.Utils;

import java.util.List;

public final class PlacedFeatureInit {

    public static final RegistrySupplier<PlacedFeature> E2J_FLOWERS_PLACED_FEATURES;
    private static final ModConfig config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();

    static {
        E2J_FLOWERS_PLACED_FEATURES = registerPlacedFeature("e2j_flowers", ConfiguredFeatureInit.E2J_FLOWERS_CONFIGURED_FEATURE.get(),
                RarityFilterPlacementModifier.of(7),
                SquarePlacementModifier.of(),
                PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
                CountPlacementModifier.of(ClampedIntProvider.create(UniformIntProvider.create(-3, 1), 0, 1)),
                BiomePlacementModifier.of());
    }

    private PlacedFeatureInit() {
        throw new IllegalStateException("Utility class");
    }

    public static void init() {
    }


    public static RegistrySupplier<PlacedFeature> registerPlacedFeature(String registryName, ConfiguredFeature<?, ?> configuredFeature, PlacementModifier... placementModifiers) {
        return Earth2JavaMod.PLACED_FEATURE_REGISTRAR.register(Utils.modIdentifierOf(registryName), () -> new PlacedFeature(RegistryEntry.of(configuredFeature), List.of(placementModifiers)));
    }


}

