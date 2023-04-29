package slexom.earthtojava.client.renderer.entity.feature;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.RotationAxis;
import slexom.earthtojava.client.renderer.entity.model.CluckshroomModel;
import slexom.earthtojava.entity.passive.CluckshroomEntity;

@Environment(EnvType.CLIENT)
public class CluckshroomMushroomFeatureRenderer<T extends CluckshroomEntity> extends FeatureRenderer<T, CluckshroomModel<T>> {
    public CluckshroomMushroomFeatureRenderer(FeatureRendererContext<T, CluckshroomModel<T>> rendererIn) {
        super(rendererIn);
    }

    public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int light, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (!entity.isBaby() && !entity.isInvisible()) {
            BlockRenderManager blockRenderManager = MinecraftClient.getInstance().getBlockRenderManager();
            BlockState blockstate = Blocks.RED_MUSHROOM.getDefaultState();
            int i = LivingEntityRenderer.getOverlay(entity, 0.0F);
            matrixStack.push();
            matrixStack.translate((double) -0.1F, (double) 0.6F, 0.05D);
            matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-48.0F));
            matrixStack.scale(-0.5F, -0.5F, 0.5F);
            matrixStack.translate(-0.5D, -0.5D, -0.5D);
            blockRenderManager.renderBlockAsEntity(blockstate, matrixStack, vertexConsumerProvider, light, i);
            matrixStack.pop();
            matrixStack.push();
            this.getContextModel().getHead().rotate(matrixStack);
            matrixStack.translate(0.05F, -0.6F, 0.0D);
            matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-78.0F));
            matrixStack.scale(-0.5F, -0.5F, 0.5F);
            matrixStack.translate(-0.5D, -0.5D, -0.5D);
            blockRenderManager.renderBlockAsEntity(blockstate, matrixStack, vertexConsumerProvider, light, i);
            matrixStack.pop();
        }
    }
}