package slexom.earthtojava.client.renderer.entity.feature;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import slexom.earthtojava.client.renderer.entity.model.RainbowSheepModel;
import slexom.earthtojava.client.renderer.entity.model.RainbowSheepWoolModel;
import slexom.earthtojava.entity.passive.RainbowSheepEntity;
import slexom.earthtojava.init.EntityModelLayersInit;

public class RainbowSheepWoolFeatureRenderer extends FeatureRenderer<RainbowSheepEntity, RainbowSheepModel<RainbowSheepEntity>> {
    private static final Identifier TEXTURE = new Identifier("earthtojavamobs:textures/mobs/sheep/rainbow_sheep/rainbow_sheep_fur.png");
    private final RainbowSheepWoolModel<RainbowSheepEntity> sheepModel;

    public RainbowSheepWoolFeatureRenderer(FeatureRendererContext<RainbowSheepEntity, RainbowSheepModel<RainbowSheepEntity>> featureRendererContext, EntityModelLoader entityModelLoader) {
        super(featureRendererContext);
        this.sheepModel = new RainbowSheepWoolModel<>(entityModelLoader.getModelPart(EntityModelLayersInit.RAINBOW_SHEEP_FUR_ENTITY_MODEL_LAYER));
    }

    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, RainbowSheepEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        if (!entity.isSheared() && !entity.isInvisible()) {
            float f = 0.9019608F;
            float f1 = 0.9019608F;
            float f2 = 0.9019608F;
            render(this.getContextModel(), this.sheepModel, TEXTURE, matrices, vertexConsumers, light, entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch, tickDelta, f, f1, f2);
        }
    }

}