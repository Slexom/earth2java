package slexom.earthtojava.mobs.init.renderer;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
import slexom.earthtojava.mobs.init.BlockInit;

public class BlockRendererInit {
    public static void init() {
        BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.BUTTERCUP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.POTTED_BUTTERCUP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.PINK_DAISY, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.POTTED_PINK_DAISY, RenderLayer.getCutout());
    }
}
