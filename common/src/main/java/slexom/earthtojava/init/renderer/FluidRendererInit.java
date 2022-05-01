package slexom.earthtojava.init.renderer;

import dev.architectury.registry.ReloadListenerRegistry;
import dev.architectury.registry.client.rendering.RenderTypeRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.fluid.Fluid;
import net.minecraft.resource.ResourceType;
import net.minecraft.resource.SynchronousResourceReloader;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import slexom.earthtojava.Earth2JavaMod;
import slexom.earthtojava.init.FluidInit;

import java.util.function.Function;

public class FluidRendererInit {

    public static void init() {
        RenderTypeRegistry.register(RenderLayer.getSolid(), FluidInit.MUD_FLUID_FLOWING.get(), FluidInit.MUD_FLUID_STILL.get());
      //  setupFluidRendering(FluidInit.MUD_FLUID_STILL.get(), FluidInit.MUD_FLUID_FLOWING.get(), 0x472804);
    }

    public static void setupFluidRendering(final Fluid still, final Fluid flowing, final int color) {
    //   final Identifier stillSpriteId = new Identifier(Earth2JavaMod.MOD_ID, "fluids/mud_still");
    //   final Identifier flowingSpriteId = new Identifier(Earth2JavaMod.MOD_ID, "fluids/mud_flow");
    /*    ClientSpriteRegistryCallback.event(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE).register((atlasTexture, registry) -> {
            registry.register(stillSpriteId);
            registry.register(flowingSpriteId);
        });*/
     //   final Identifier fluidId = Registry.FLUID.getId(still);
     //   final Identifier listenerId = new Identifier(fluidId.getNamespace(), fluidId.getPath() + "_reload_listener");
     //   final Sprite[] fluidSprites = {null, null};

     //  ReloadListenerRegistry.register(ResourceType.CLIENT_RESOURCES, (SynchronousResourceReloader) manager -> {
     //      final Function<Identifier, Sprite> atlas = MinecraftClient.getInstance().getSpriteAtlas(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE);
     //      fluidSprites[0] = atlas.apply(stillSpriteId);
     //      fluidSprites[1] = atlas.apply(flowingSpriteId);
     //  });
      /*
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

       */
    }

}
