package slexom.earthtojava.mobs.init;

import com.google.common.collect.Lists;
import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.fabric.api.biome.v1.BiomeModificationContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import slexom.earthtojava.mobs.Earth2JavaMod;
import slexom.earthtojava.mobs.config.ModConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class BiomeInit {
    private static final ModConfig config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();


    public static void init() {
        BuiltinRegistries.BIOME.forEach(BiomeInit::handleBiome);

        BiomeModifications.addFeature(BiomeSelectors.categories(Biome.Category.PLAINS), GenerationStep.Feature.VEGETAL_DECORATION, RegistryKey.of(BuiltinRegistries.PLACED_FEATURE.getKey(),  new Identifier(Earth2JavaMod.MOD_ID, "e2j_flowers")) );



        RegistryEntryAddedCallback.event(BuiltinRegistries.BIOME).register((i, identifier, biome) -> handleBiome(biome));
    }

    private static void handleBiome(Biome biome) {
        addMudLake(biome);

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







}
