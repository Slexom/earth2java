package slexom.earthtojava.mobs.client.renderer.entity.layers;

import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.render.BlockRendererDispatcher;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.Vector3f;
import net.minecraft.client.render.entity.FeatureRendererContext;
import net.minecraft.client.render.entity.LivingRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import slexom.earthtojava.mobs.client.renderer.entity.model.CluckshroomModel;
import slexom.earthtojava.mobs.entity.passive.CluckshroomEntity;

@Environment(EnvType.CLIENT)
public class CluckshroomLayer<T extends CluckshroomEntity> extends FeatureRenderer<T, CluckshroomModel<T>> {
    public CluckshroomLayer(FeatureRendererContext<T, CluckshroomModel<T>> rendererIn) {
        super(rendererIn);
    }

    public void render(MatrixStack matrixStackIn, VertexConsumerProvider bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (!entitylivingbaseIn.isBaby() && !entitylivingbaseIn.isInvisible()) {
            BlockRendererDispatcher blockrendererdispatcher = Minecraft.getInstance().getBlockRendererDispatcher();
            BlockState blockstate = Blocks.RED_MUSHROOM.getDefaultState();
            int i = LivingRenderer.getPackedOverlay(entitylivingbaseIn, 0.0F);
            matrixStackIn.push();
            matrixStackIn.translate((double) -0.1F, (double) 0.6F, 0.05D);
            matrixStackIn.rotate(Vector3f.YP.rotationDegrees(-48.0F));
            matrixStackIn.scale(-0.5F, -0.5F, 0.5F);
            matrixStackIn.translate(-0.5D, -0.5D, -0.5D);
            blockrendererdispatcher.renderBlock(blockstate, matrixStackIn, bufferIn, packedLightIn, i);
            matrixStackIn.pop();
            matrixStackIn.push();
            this.getContextModel().getHead().translateRotate(matrixStackIn);
            matrixStackIn.translate((double) 0.05F, (double) -0.6F, 0.0D);
            matrixStackIn.rotate(Vector3f.YP.rotationDegrees(-78.0F));
            matrixStackIn.scale(-0.5F, -0.5F, 0.5F);
            matrixStackIn.translate(-0.5D, -0.5D, -0.5D);
            blockrendererdispatcher.renderBlock(blockstate, matrixStackIn, bufferIn, packedLightIn, i);
            matrixStackIn.pop();
        }
    }
}