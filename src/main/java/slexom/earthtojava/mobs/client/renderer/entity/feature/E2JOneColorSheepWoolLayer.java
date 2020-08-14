package slexom.earthtojava.mobs.client.renderer.entity.feature;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import slexom.earthtojava.mobs.client.renderer.entity.model.E2JOneColorSheepModel;
import slexom.earthtojava.mobs.client.renderer.entity.model.E2JOneColorSheepWoolModel;
import slexom.earthtojava.mobs.entity.base.E2JOneColorSheepEntity;

@Environment(EnvType.CLIENT)
public class E2JOneColorSheepWoolLayer<T extends E2JOneColorSheepEntity<T>> extends FeatureRenderer<T, E2JOneColorSheepModel<T>> {
    private final E2JOneColorSheepWoolModel<T> sheepModel = new E2JOneColorSheepWoolModel<>();

    private Identifier texture;

    public E2JOneColorSheepWoolLayer(FeatureRendererContext<T, E2JOneColorSheepModel<T>> rendererIn, String texture) {
        super(rendererIn);
        this.texture = new Identifier(texture);
    }

    public void render(MatrixStack matrixStackIn, VertexConsumerProvider bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (!entitylivingbaseIn.isSheared() && !entitylivingbaseIn.isInvisible()) {
            float f = 0.9019608F;
            float f1 = 0.9019608F;
            float f2 = 0.9019608F;
            render(this.getContextModel(), this.sheepModel, texture, matrixStackIn, bufferIn, packedLightIn, entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, partialTicks, f, f1, f2);
        }
    }
}
