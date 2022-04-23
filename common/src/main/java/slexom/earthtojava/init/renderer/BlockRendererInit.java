package slexom.earthtojava.init.renderer;

import dev.architectury.registry.client.rendering.RenderTypeRegistry;
import net.minecraft.client.render.RenderLayer;
import slexom.earthtojava.init.BlockInit;

public class BlockRendererInit {
    public static void init() {
        RenderTypeRegistry.register(RenderLayer.getCutout(), BlockInit.BUTTERCUP.get());
        RenderTypeRegistry.register(RenderLayer.getCutout(), BlockInit.POTTED_BUTTERCUP.get());
        RenderTypeRegistry.register(RenderLayer.getCutout(), BlockInit.PINK_DAISY.get());
        RenderTypeRegistry.register(RenderLayer.getCutout(), BlockInit.POTTED_PINK_DAISY.get());
    }
}
