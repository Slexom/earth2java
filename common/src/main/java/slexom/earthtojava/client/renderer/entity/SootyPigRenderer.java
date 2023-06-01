package slexom.earthtojava.client.renderer.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory.Context;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.feature.SaddleFeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.PigEntityModel;
import net.minecraft.util.Identifier;
import slexom.earthtojava.client.renderer.entity.model.SootyPigModel;
import slexom.earthtojava.entity.base.E2JBasePigEntity;
import slexom.earthtojava.init.EntityModelLayersInit;
import slexom.earthtojava.init.EntityTypesInit;

@Environment(EnvType.CLIENT)
public class SootyPigRenderer extends MobEntityRenderer<E2JBasePigEntity, SootyPigModel> {

	public SootyPigRenderer(Context context) {
		super(context, new SootyPigModel(context.getPart(EntityModelLayersInit.SOOTY_PIG_ENTITY_MODEL_LAYER)), 0.7F);
		addFeature(new SaddleFeatureRenderer(this, new PigEntityModel<>(context.getPart(EntityModelLayers.PIG_SADDLE)), new Identifier("textures/entity/pig/pig_saddle.png")));
	}

	@Override
	public Identifier getTexture(E2JBasePigEntity entity) {
		Identifier texture = TextureUtils.getTextureIdentifier("pig", EntityTypesInit.SOOTY_PIG_REGISTRY_NAME);
		Identifier textureBlink = TextureUtils.getTextureIdentifier("pig", EntityTypesInit.SOOTY_PIG_REGISTRY_NAME, "blink");
		return entity.blinkManager.getBlinkRemainingTicks() > 0 ? textureBlink : texture;
	}

}
