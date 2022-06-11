package slexom.earthtojava.fabric;

import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandler;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.BlockRenderView;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import slexom.earthtojava.Earth2JavaMod;
import slexom.earthtojava.config.ModConfig;
import slexom.earthtojava.fabric.init.FeatureInit;
import slexom.earthtojava.init.FluidInit;

import java.util.function.Function;

public class Earth2JavaModFabric implements ModInitializer {

    private static void setupBiomes() {
        final ModConfig config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();
        BiomeModifications.addFeature(BiomeSelectors.categories(Biome.Category.PLAINS), GenerationStep.Feature.VEGETAL_DECORATION, RegistryKey.of(BuiltinRegistries.PLACED_FEATURE.getKey(), new Identifier(Earth2JavaMod.MOD_ID, "e2j_flowers")));

        if (config.mudLakeConfig.canGenerate && config.mudLakeConfig.mudLakeFrequency > 0) {
            BiomeModifications.addFeature(BiomeSelectors.categories(Biome.Category.FOREST), GenerationStep.Feature.LAKES, RegistryKey.of(BuiltinRegistries.PLACED_FEATURE.getKey(), new Identifier(Earth2JavaMod.MOD_ID, "lake_mud")));
        }
    }

    @Override
    public void onInitialize() {
        Earth2JavaMod.initialize();

     //   FeatureInit.init();
     //
     //   setupBiomes();
    }

}
