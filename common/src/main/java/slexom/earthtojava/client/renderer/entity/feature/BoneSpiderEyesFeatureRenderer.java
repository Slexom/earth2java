package slexom.earthtojava.client.renderer.entity.feature;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.EyesFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.SpiderEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import slexom.earthtojava.entity.monster.BoneSpiderEntity;

@Environment(EnvType.CLIENT)
public class BoneSpiderEyesFeatureRenderer<T extends BoneSpiderEntity, M extends SpiderEntityModel<T>> extends EyesFeatureRenderer<T, M> {

	public BoneSpiderEyesFeatureRenderer(FeatureRendererContext<T, M> featureRendererContext) {
		super(featureRendererContext);
	}

	public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, BoneSpiderEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
		float alpha = entity.blinkManager.getBlinkRemainingTicks() == 0 ? 1.0F : 0.0F;
		VertexConsumer vertexConsumer = vertexConsumers.getBuffer(getEyesTexture());
		getContextModel().render(matrices, vertexConsumer, 15728640, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, alpha);
	}

	public RenderLayer getEyesTexture() {
		Identifier identifier = new Identifier("earthtojavamobs:textures/mobs/spider/bone_spider/bone_spider_eyes.png");
		return RenderLayer.getEyes(identifier);
	}
}
