package slexom.earthtojava.mobs.client.renderer.entity.feature;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.SheepEntityModel;
import net.minecraft.client.render.entity.model.SheepWoolEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import slexom.earthtojava.mobs.entity.base.E2JBaseMonoColorSheepEntity;

@Environment(EnvType.CLIENT)
public class E2JMonoColorSheepWoolFeature extends FeatureRenderer<E2JBaseMonoColorSheepEntity, SheepEntityModel<E2JBaseMonoColorSheepEntity>> {
    private final SheepWoolEntityModel sheepModel = new SheepWoolEntityModel();

    private Identifier texture;

    public E2JMonoColorSheepWoolFeature(FeatureRendererContext<E2JBaseMonoColorSheepEntity, SheepEntityModel<E2JBaseMonoColorSheepEntity>> rendererIn, String texture) {
        super(rendererIn);
        this.texture = new Identifier(texture);
    }

    public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int packedLightIn, E2JBaseMonoColorSheepEntity entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (!entitylivingbaseIn.isSheared() && !entitylivingbaseIn.isInvisible()) {
            float red = 0.9019608F;
            float green = 0.9019608F;
            float blue = 0.9019608F;
            render(this.getContextModel(), this.sheepModel, texture, matrixStack, vertexConsumerProvider, packedLightIn, entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, partialTicks, red, green, blue);
        }
    }
}
