package slexom.earthtojava.client.renderer.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory.Context;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.feature.SaddleFeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.PigEntityModel;
import net.minecraft.util.Identifier;
import slexom.earthtojava.entity.base.E2JBasePigEntity;

@Environment(EnvType.CLIENT)
public class E2JPigRenderer extends MobEntityRenderer<E2JBasePigEntity, PigEntityModel<E2JBasePigEntity>> {

	private final String registryName;

	public E2JPigRenderer(Context context, String registryName) {
		super(context, new PigEntityModel<>(context.getPart(EntityModelLayers.PIG)), 0.7F);
		addFeature(new SaddleFeatureRenderer<>(this, new PigEntityModel<>(context.getPart(EntityModelLayers.PIG_SADDLE)), new Identifier("textures/entity/pig/pig_saddle.png")));
		this.registryName = registryName;
	}

	@Override
	public Identifier getTexture(E2JBasePigEntity entity) {
		Identifier texture = TextureUtils.getTextureIdentifier("pig", registryName);
		Identifier textureBlink = TextureUtils.getTextureIdentifier("pig", registryName, "blink");
		return entity.blinkManager.getBlinkRemainingTicks() > 0 ? textureBlink : texture;
	}

}
