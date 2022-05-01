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

    private static void setupFluidRendering(final Fluid still, final Fluid flowing, final int color) {
        final Identifier stillSpriteId = new Identifier(Earth2JavaMod.MOD_ID, "fluids/mud_still");
        final Identifier flowingSpriteId = new Identifier(Earth2JavaMod.MOD_ID, "fluids/mud_flow");
        ClientSpriteRegistryCallback.event(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE).register((atlasTexture, registry) -> {
            registry.register(stillSpriteId);
            registry.register(flowingSpriteId);
        });
        final Identifier fluidId = Registry.FLUID.getId(still);
        final Identifier listenerId = new Identifier(fluidId.getNamespace(), fluidId.getPath() + "_reload_listener");
        final Sprite[] fluidSprites = {null, null};
        ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(new SimpleSynchronousResourceReloadListener() {
            @Override
            public void reload(ResourceManager manager) {
                final Function<Identifier, Sprite> atlas = MinecraftClient.getInstance().getSpriteAtlas(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE);
                fluidSprites[0] = atlas.apply(stillSpriteId);
                fluidSprites[1] = atlas.apply(flowingSpriteId);
            }

            @Override
            public Identifier getFabricId() {
                return listenerId;
            }
        });

        final FluidRenderHandler renderHandler = new FluidRenderHandler() {
            @Override
            public Sprite[] getFluidSprites(BlockRenderView view, BlockPos pos, FluidState state) {
                return fluidSprites;
            }

            @Override
            public int getFluidColor(BlockRenderView view, BlockPos pos, FluidState state) {
                return color;
            }
        };
        FluidRenderHandlerRegistry.INSTANCE.register(still, renderHandler);
        FluidRenderHandlerRegistry.INSTANCE.register(flowing, renderHandler);
    }

    @Override
    public void onInitialize() {
        Earth2JavaMod.initialize();

        FeatureInit.init();
        setupFluidRendering(FluidInit.MUD_FLUID_STILL.get(), FluidInit.MUD_FLUID_FLOWING.get(), 0x472804);
        setupBiomes();
    }

}
