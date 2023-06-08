package slexom.earthtojava.client.renderer.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.feature.SaddleFeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.util.Identifier;
import slexom.earthtojava.client.renderer.entity.model.MuddyPigModel;
import slexom.earthtojava.entity.passive.MuddyPigEntity;
import slexom.earthtojava.init.EntityModelLayersInit;
import slexom.earthtojava.init.RegistryNames;

@Environment(EnvType.CLIENT)
public class MuddyPigRenderer extends MobEntityRenderer<MuddyPigEntity, MuddyPigModel<MuddyPigEntity>> {

	public MuddyPigRenderer(EntityRendererFactory.Context context) {
		super(context, new MuddyPigModel<>(context.getPart(EntityModelLayersInit.MUDDY_PIG_ENTITY_MODEL_LAYER)), 0.7F);
		addFeature(new SaddleFeatureRenderer<>(this, new MuddyPigModel<>(context.getPart(EntityModelLayers.PIG_SADDLE)), new Identifier("textures/entity/pig/pig_saddle.png")));
	}

	public Identifier getTexture(MuddyPigEntity entity) {
		Identifier texture = TextureUtils.getTextureIdentifier("pig", RegistryNames.MUDDY_PIG_REGISTRY_NAME);
		Identifier textureBlink = TextureUtils.getTextureIdentifier("pig", RegistryNames.MUDDY_PIG_REGISTRY_NAME, "blink");
		Identifier textureDried = TextureUtils.getTextureIdentifier("pig", RegistryNames.MUDDY_PIG_REGISTRY_NAME, "dried");
		Identifier textureDriedBlink = TextureUtils.getTextureIdentifier("pig", RegistryNames.MUDDY_PIG_REGISTRY_NAME, "dried_blink");
		boolean blink = entity.blinkManager.getBlinkRemainingTicks() > 0;
		if (entity.isInMuddyState()) {
			return blink ? textureBlink : texture;
		}

		return blink ? textureDriedBlink : textureDried;
	}


}
