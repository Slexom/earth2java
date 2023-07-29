package slexom.earthtojava.client.renderer.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import slexom.earthtojava.client.renderer.entity.model.FancyChickenModel;
import slexom.earthtojava.entity.passive.FancyChickenEntity;
import slexom.earthtojava.init.EntityModelLayersInit;
import slexom.earthtojava.init.RegistryNames;

@Environment(EnvType.CLIENT)
public class FancyChickenRenderer extends MobEntityRenderer<FancyChickenEntity, FancyChickenModel<FancyChickenEntity>> {

	public FancyChickenRenderer(EntityRendererFactory.Context context) {
		super(context, new FancyChickenModel<>(context.getPart(EntityModelLayersInit.FANCY_CHICKEN_ENTITY_MODEL_LAYER)), 0.3F);
	}

	protected float getAnimationProgress(FancyChickenEntity chickenEntity, float f) {
		float g = MathHelper.lerp(f, chickenEntity.prevFlapProgress, chickenEntity.flapProgress);
		float h = MathHelper.lerp(f, chickenEntity.prevMaxWingDeviation, chickenEntity.maxWingDeviation);
		return (MathHelper.sin(g) + 1.0F) * h;
	}

	public Identifier getTexture(FancyChickenEntity entity) {
		Identifier texture = TextureUtils.getTextureIdentifier("chicken", RegistryNames.FANCY_CHICKEN_REGISTRY_NAME);
		Identifier textureBlink = TextureUtils.getTextureIdentifier("chicken", RegistryNames.FANCY_CHICKEN_REGISTRY_NAME, "blink");
		return entity.blinkManager.getBlinkRemainingTicks() > 0 ? textureBlink : texture;
	}


}
