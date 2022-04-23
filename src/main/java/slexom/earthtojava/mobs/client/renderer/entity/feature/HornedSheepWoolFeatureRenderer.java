package slexom.earthtojava.client.renderer.entity.feature;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.render.entity.model.SheepWoolEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import slexom.earthtojava.client.renderer.entity.model.HornedSheepModel;
import slexom.earthtojava.entity.passive.HornedSheepEntity;

@Environment(EnvType.CLIENT)
public class HornedSheepWoolFeatureRenderer extends FeatureRenderer<HornedSheepEntity, HornedSheepModel<HornedSheepEntity>> {
    private static final Identifier TEXTURE = new Identifier("textures/entity/sheep/sheep_fur.png");
    private final SheepWoolEntityModel<HornedSheepEntity> woolEntityModel;

    public HornedSheepWoolFeatureRenderer(FeatureRendererContext<HornedSheepEntity, HornedSheepModel<HornedSheepEntity>> featureRendererContext, EntityModelLoader entityModelLoader) {
        super(featureRendererContext);
        this.woolEntityModel = new SheepWoolEntityModel(entityModelLoader.getModelPart(EntityModelLayers.SHEEP_FUR));
    }

    public void render(MatrixStack matrixStackIn, VertexConsumerProvider bufferIn, int packedLightIn, HornedSheepEntity entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (!entitylivingbaseIn.isSheared() && !entitylivingbaseIn.isInvisible()) {
            float f;
            float f1;
            float f2;
            if (entitylivingbaseIn.hasCustomName() && "jeb_".equals(entitylivingbaseIn.getName().asString())) {
                int i = entitylivingbaseIn.age / 25 + entitylivingbaseIn.getId();
                int j = DyeColor.values().length;
                int k = i % j;
                int l = (i + 1) % j;
                float f3 = ((float) (entitylivingbaseIn.age % 25) + partialTicks) / 25.0F;
                float[] afloat1 = HornedSheepEntity.getRgbColor(DyeColor.byId(k));
                float[] afloat2 = HornedSheepEntity.getRgbColor(DyeColor.byId(l));
                f = afloat1[0] * (1.0F - f3) + afloat2[0] * f3;
                f1 = afloat1[1] * (1.0F - f3) + afloat2[1] * f3;
                f2 = afloat1[2] * (1.0F - f3) + afloat2[2] * f3;
            } else {
                float[] afloat = HornedSheepEntity.getRgbColor(entitylivingbaseIn.getColor());
                f = afloat[0];
                f1 = afloat[1];
                f2 = afloat[2];
            }

            render(this.getContextModel(), this.woolEntityModel, TEXTURE, matrixStackIn, bufferIn, packedLightIn, entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, partialTicks, f, f1, f2);
        }
    }
}