package slexom.earthtojava.client.renderer.entity.feature;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.IronGolemEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import slexom.earthtojava.entity.passive.FurnaceGolemEntity;

import java.text.MessageFormat;

@Environment(EnvType.CLIENT)
public class FurnaceGolemFlamesFeatureRenderer extends FeatureRenderer<FurnaceGolemEntity, IronGolemEntityModel<FurnaceGolemEntity>> {

    private static final int ANIMATION_FRAMES = 6;
    private static final float ANIMATION_TIME = 6.0F;
    private int currentFrame = 0;

    public FurnaceGolemFlamesFeatureRenderer(FeatureRendererContext<FurnaceGolemEntity, IronGolemEntityModel<FurnaceGolemEntity>> featureRendererContext) {
        super(featureRendererContext);
    }

    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, FurnaceGolemEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        if (!entity.isInvisible() && entity.isAngry()) {
            this.currentFrame = (int) (Math.floor(entity.age / ANIMATION_TIME) % ANIMATION_FRAMES);
            String frameLocation = MessageFormat.format("earthtojavamobs:textures/mobs/iron_golem/furnace_golem/furnace_golem_flames_layer_anim_{0}.png", (this.currentFrame + 1));
            Identifier resourcelocation = new Identifier(frameLocation);
            renderModel(this.getContextModel(), resourcelocation, matrices, vertexConsumers, light, entity, 1.0F, 1.0F, 1.0F);
        }
    }
}