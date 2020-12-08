package slexom.earthtojava.mobs.client.renderer.entity.feature;


import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.CowEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import slexom.earthtojava.mobs.entity.passive.MoolipEntity;
import slexom.earthtojava.mobs.init.BlockInit;

@Environment(EnvType.CLIENT)
public class MoolipLayer<T extends MoolipEntity> extends FeatureRenderer<T, CowEntityModel<T>> {

    public MoolipLayer(FeatureRendererContext<T, CowEntityModel<T>> rendererIn) {
        super(rendererIn);
    }

    public void render(MatrixStack matrixStackIn, VertexConsumerProvider bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (!entitylivingbaseIn.isBaby() && !entitylivingbaseIn.isInvisible()) {
            BlockRenderManager blockRenderManager = MinecraftClient.getInstance().getBlockRenderManager();
            BlockState blockstate = BlockInit.PINK_DAISY.getDefaultState();
            int i = LivingEntityRenderer.getOverlay(entitylivingbaseIn, 0.0F);
            matrixStackIn.push();
            matrixStackIn.translate(0.2D, -0.2D, 0.5D);
            matrixStackIn.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(-48.0F));
            matrixStackIn.scale(-0.66F, -0.66F, 0.66F);
            matrixStackIn.translate(-0.5D, -0.5D, -0.5D);
            blockRenderManager.renderBlockAsEntity(blockstate, matrixStackIn, bufferIn, packedLightIn, i);
            matrixStackIn.pop();
            matrixStackIn.push();
            matrixStackIn.translate(-0.2D, -0.2D, 0.4D);
            matrixStackIn.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(-48.0F));
            matrixStackIn.scale(-0.66F, -0.66F, 0.66F);
            matrixStackIn.translate(-0.5D, -0.5D, -0.5D);
            blockRenderManager.renderBlockAsEntity(blockstate, matrixStackIn, bufferIn, packedLightIn, i);
            matrixStackIn.pop();
            matrixStackIn.push();
            matrixStackIn.translate(-0.15D, -0.2D, -0.3D);
            matrixStackIn.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(-48.0F));
            matrixStackIn.scale(-0.66F, -0.66F, 0.66F);
            matrixStackIn.translate(-0.5D, -0.5D, -0.5D);
            blockRenderManager.renderBlockAsEntity(blockstate, matrixStackIn, bufferIn, packedLightIn, i);
            matrixStackIn.pop();
            matrixStackIn.push();
            matrixStackIn.translate(0.15D, -0.2D, -0.2D);
            matrixStackIn.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(-48.0F));
            matrixStackIn.scale(-0.66F, -0.66F, 0.66F);
            matrixStackIn.translate(-0.5D, -0.5D, -0.5D);
            blockRenderManager.renderBlockAsEntity(blockstate, matrixStackIn, bufferIn, packedLightIn, i);
            matrixStackIn.pop();
            matrixStackIn.push();
            this.getContextModel().getHead().rotate(matrixStackIn);
            matrixStackIn.translate(0.1D, -0.5D, -0.2D);
            matrixStackIn.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(-78.0F));
            matrixStackIn.scale(-0.66F, -0.66F, 0.66F);
            matrixStackIn.translate(-0.5D, -0.5D, -0.5D);
            blockRenderManager.renderBlockAsEntity(blockstate, matrixStackIn, bufferIn, packedLightIn, i);
            matrixStackIn.pop();
        }
    }


}