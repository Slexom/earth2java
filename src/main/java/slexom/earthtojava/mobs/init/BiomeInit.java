package slexom.earthtojava.mobs.init;

import com.google.common.collect.Lists;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.fabricmc.fabric.mixin.biome.modification.GenerationSettingsAccessor;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import slexom.earthtojava.mobs.config.ModConfig;

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
        addMudLake(biome);
        addButtercup(biome);
        addPinkDaisy(biome);
    }

    private static boolean isInOverworld(Biome biome) {
        return biome.getCategory() != Biome.Category.NETHER && biome.getCategory() != Biome.Category.THEEND;
    }

    private static boolean isMushroom(Biome biome) {
        return biome.getCategory() == Biome.Category.MUSHROOM;
    }

    private static void addMudLake(Biome biome) {
        if (isInOverworld(biome) && !isMushroom(biome) && config.mudLakeConfig.canGenerate) {
            addFeature(biome, GenerationStep.Feature.LAKES, FeatureInit.MUD_LAKE_CONFIGURED_FEATURE);
        }
    }

    private static void addButtercup(Biome biome) {
        if (isInOverworld(biome) && biome.getCategory() == Biome.Category.PLAINS) {
            addFeature(biome, GenerationStep.Feature.VEGETAL_DECORATION, FeatureInit.FLOWER_BUTTERCUP_CONFIGURED_FEATURE);
        }
    }

    private static void addPinkDaisy(Biome biome) {
        if (isInOverworld(biome) && biome.getCategory() == Biome.Category.PLAINS) {
            addFeature(biome, GenerationStep.Feature.VEGETAL_DECORATION, FeatureInit.FLOWER_PINK_DAISY_CONFIGURED_FEATURE);
        }
    }

    public static void addFeature(Biome biome, GenerationStep.Feature step, ConfiguredFeature<?, ?> feature) {
        GenerationSettingsAccessor generationSettingsAccessor = (GenerationSettingsAccessor) biome.getGenerationSettings();
        int stepIndex = step.ordinal();
        List<List<Supplier<ConfiguredFeature<?, ?>>>> featuresByStep = new ArrayList<>(generationSettingsAccessor.fabric_getFeatures());
        while (featuresByStep.size() <= stepIndex) {
            featuresByStep.add(Lists.newArrayList());
        }
        List<Supplier<ConfiguredFeature<?, ?>>> features = new ArrayList<>(featuresByStep.get(stepIndex));
        features.add(() -> feature);
        featuresByStep.set(stepIndex, features);
        generationSettingsAccessor.fabric_setFeatures(featuresByStep);
    }

}
