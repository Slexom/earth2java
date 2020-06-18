package net.slexom.earthtojavamobs.client.renderer.entity.layers;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.slexom.earthtojavamobs.client.renderer.entity.model.CluckshroomModel;
import net.slexom.earthtojavamobs.entity.passive.CluckshroomEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CluckshroomLayer<T extends CluckshroomEntity> extends LayerRenderer<T, CluckshroomModel<T>> {
    public CluckshroomLayer(IEntityRenderer<T, CluckshroomModel<T>> rendererIn) {
        super(rendererIn);
    }

    public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (!entitylivingbaseIn.isChild() && !entitylivingbaseIn.isInvisible()) {
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
            this.getEntityModel().getHead().translateRotate(matrixStackIn);
            matrixStackIn.translate((double) 0.05F, (double) -0.6F, 0.0D);
            matrixStackIn.rotate(Vector3f.YP.rotationDegrees(-78.0F));
            matrixStackIn.scale(-0.5F, -0.5F, 0.5F);
            matrixStackIn.translate(-0.5D, -0.5D, -0.5D);
            blockrendererdispatcher.renderBlock(blockstate, matrixStackIn, bufferIn, packedLightIn, i);
            matrixStackIn.pop();
        }
    }
}