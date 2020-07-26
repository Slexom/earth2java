package slexom.earthtojava.mobs.init.renderer;

import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import slexom.earthtojava.mobs.init.BlockEntityTypeInit;
import slexom.earthtojava.mobs.init.ItemInit;

public class RendererInit {
    public static void init() {
        BlockRendererInit.init();
        FluidRendererInit.init();
        EntityRendererInit.init();
        BuiltinItemRendererRegistry.INSTANCE.register(ItemInit.RAINBOW_BED, (stack, matrices, vertexConsumers, light, overlay) -> BlockEntityRenderDispatcher.INSTANCE.renderEntity(BlockEntityTypeInit.RAINBOW_BED.instantiate(), matrices, vertexConsumers, light, overlay));
    }
}
