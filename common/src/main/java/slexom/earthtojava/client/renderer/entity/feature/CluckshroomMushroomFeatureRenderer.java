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
import net.minecraft.util.math.Vec3f;
import slexom.earthtojava.client.renderer.entity.model.CluckshroomModel;
import slexom.earthtojava.entity.passive.CluckshroomEntity;

@Environment(EnvType.CLIENT)
public class CluckshroomMushroomFeatureRenderer<T extends CluckshroomEntity> extends FeatureRenderer<T, CluckshroomModel<T>> {
    public CluckshroomMushroomFeatureRenderer(FeatureRendererContext<T, CluckshroomModel<T>> rendererIn) {
        super(rendererIn);
    }

    public void render(MatrixStack matrixStackIn, VertexConsumerProvider bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (!entitylivingbaseIn.isBaby() && !entitylivingbaseIn.isInvisible()) {
            BlockRenderManager blockRenderManager = MinecraftClient.getInstance().getBlockRenderManager();
            BlockState blockstate = Blocks.RED_MUSHROOM.getDefaultState();
            int i = LivingEntityRenderer.getOverlay(entitylivingbaseIn, 0.0F);
            matrixStackIn.push();
            matrixStackIn.translate((double) -0.1F, (double) 0.6F, 0.05D);
            matrixStackIn.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(-48.0F));
            matrixStackIn.scale(-0.5F, -0.5F, 0.5F);
            matrixStackIn.translate(-0.5D, -0.5D, -0.5D);
            blockRenderManager.renderBlockAsEntity(blockstate, matrixStackIn, bufferIn, packedLightIn, i);
            matrixStackIn.pop();
            matrixStackIn.push();
            this.getContextModel().getHead().rotate(matrixStackIn);
            matrixStackIn.translate((double) 0.05F, (double) -0.6F, 0.0D);
            matrixStackIn.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(-78.0F));
            matrixStackIn.scale(-0.5F, -0.5F, 0.5F);
            matrixStackIn.translate(-0.5D, -0.5D, -0.5D);
            blockRenderManager.renderBlockAsEntity(blockstate, matrixStackIn, bufferIn, packedLightIn, i);
            matrixStackIn.pop();
        }
    }
}