package slexom.earthtojava.client.renderer.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory.Context;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import slexom.earthtojava.client.renderer.entity.feature.CluckshroomMushroomFeatureRenderer;
import slexom.earthtojava.client.renderer.entity.model.CluckshroomModel;
import slexom.earthtojava.entity.passive.CluckshroomEntity;
import slexom.earthtojava.init.RegistryNames;

@Environment(EnvType.CLIENT)
public class CluckshroomRenderer extends MobEntityRenderer<CluckshroomEntity, CluckshroomModel<CluckshroomEntity>> {

	public CluckshroomRenderer(Context context) {
		super(context, new CluckshroomModel<>(context.getPart(EntityModelLayers.CHICKEN)), 0.3F);
		addFeature(new CluckshroomMushroomFeatureRenderer<>(this));
	}

	protected float getAnimationProgress(CluckshroomEntity chickenEntity, float f) {
		float g = MathHelper.lerp(f, chickenEntity.prevFlapProgress, chickenEntity.flapProgress);
		float h = MathHelper.lerp(f, chickenEntity.prevMaxWingDeviation, chickenEntity.maxWingDeviation);
		return (MathHelper.sin(g) + 1.0F) * h;
	}

	@Override
	public Identifier getTexture(CluckshroomEntity entity) {
		Identifier texture = TextureUtils.getTextureIdentifier("chicken", RegistryNames.CLUCKSHROOM_REGISTRY_NAME);
		Identifier textureBlink = TextureUtils.getTextureIdentifier("chicken", RegistryNames.CLUCKSHROOM_REGISTRY_NAME, "blink");
		return entity.blinkManager.getBlinkRemainingTicks() > 0 ? textureBlink : texture;
	}
}
