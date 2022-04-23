package slexom.earthtojava.init.renderer;

public class RendererInit {
    public static void init() {
        BlockRendererInit.init();
        FluidRendererInit.init();
        EntityRendererInit.init();
//        BuiltinItemRendererRegistry.INSTANCE.register(ItemInit.RAINBOW_BED, (stack, matrices, vertexConsumers, light, overlay) -> BlockEntityRenderDispatcher.INSTANCE.renderEntity(BlockEntityTypeInit.RAINBOW_BED.instantiate(), matrices, vertexConsumers, light, overlay));
    }
}
