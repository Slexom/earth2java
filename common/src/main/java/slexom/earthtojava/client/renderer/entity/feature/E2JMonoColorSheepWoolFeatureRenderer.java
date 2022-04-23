package slexom.earthtojava.client.renderer.entity.feature;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.render.entity.model.SheepEntityModel;
import net.minecraft.client.render.entity.model.SheepWoolEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import slexom.earthtojava.entity.base.E2JBaseMonoColorSheepEntity;

@Environment(EnvType.CLIENT)
public class E2JMonoColorSheepWoolFeatureRenderer extends FeatureRenderer<E2JBaseMonoColorSheepEntity, SheepEntityModel<E2JBaseMonoColorSheepEntity>> {
    private final SheepWoolEntityModel sheepModel;

    private Identifier texture;

    public E2JMonoColorSheepWoolFeatureRenderer(FeatureRendererContext<E2JBaseMonoColorSheepEntity, SheepEntityModel<E2JBaseMonoColorSheepEntity>> featureRendererContext, EntityModelLoader entityModelLoader, String texture) {
        super(featureRendererContext);
        this.texture = new Identifier(texture);
        this.sheepModel = new SheepWoolEntityModel(entityModelLoader.getModelPart(EntityModelLayers.SHEEP_FUR));

    }

    public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int packedLightIn, E2JBaseMonoColorSheepEntity livingEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (!livingEntity.isSheared() && !livingEntity.isInvisible()) {
            float red = 0.9019608F;
            float green = 0.9019608F;
            float blue = 0.9019608F;
            render(this.getContextModel(), this.sheepModel, texture, matrixStack, vertexConsumerProvider, packedLightIn, livingEntity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, partialTicks, red, green, blue);
        }
    }
}
