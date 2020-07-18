package slexom.earthtojava.mobs.client.renderer.entity.layers;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import slexom.earthtojava.mobs.client.renderer.entity.model.E2JOneColorSheepModel;
import slexom.earthtojava.mobs.client.renderer.entity.model.E2JOneColorSheepWoolModel;
import slexom.earthtojava.mobs.entity.base.E2JOneColorSheepEntity;

@OnlyIn(Dist.CLIENT)
public class E2JOneColorSheepWoolLayer<T extends E2JOneColorSheepEntity<T>> extends LayerRenderer<T, E2JOneColorSheepModel<T>> {
    private final E2JOneColorSheepWoolModel<T> sheepModel = new E2JOneColorSheepWoolModel<>();

    private ResourceLocation texture;

    public E2JOneColorSheepWoolLayer(IEntityRenderer<T, E2JOneColorSheepModel<T>> rendererIn, String texture) {
        super(rendererIn);
        this.texture = new ResourceLocation(texture);
    }

    public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (!entitylivingbaseIn.getSheared() && !entitylivingbaseIn.isInvisible()) {
            float f = 0.9019608F;
            float f1 = 0.9019608F;
            float f2 = 0.9019608F;
            renderCopyCutoutModel(this.getEntityModel(), this.sheepModel, texture, matrixStackIn, bufferIn, packedLightIn, entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, partialTicks, f, f1, f2);
        }
    }
}
