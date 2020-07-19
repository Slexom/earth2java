package slexom.earthtojava.mobs.client.renderer.tileentity;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.item.BuiltinModelItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import slexom.earthtojava.mobs.init.BlockEntityTypeInit;

public class RainbowBedItemStackTileEntityRenderer extends BuiltinModelItemRenderer {
    @Override
    public void render(ItemStack stack, ModelTransformation.Mode mode, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, int j) {
        BlockEntityRenderDispatcher.INSTANCE.renderEntity(BlockEntityTypeInit.RAINBOW_BED.instantiate(), matrixStack, vertexConsumerProvider, i, j);
    }

}
