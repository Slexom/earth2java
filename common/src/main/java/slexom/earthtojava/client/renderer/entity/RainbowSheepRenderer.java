package slexom.earthtojava.client.renderer.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import slexom.earthtojava.client.renderer.entity.feature.RainbowSheepWoolFeatureRenderer;
import slexom.earthtojava.client.renderer.entity.model.RainbowSheepModel;
import slexom.earthtojava.entity.passive.RainbowSheepEntity;
import slexom.earthtojava.init.EntityModelLayersInit;
import slexom.earthtojava.init.RegistryNames;

@Environment(EnvType.CLIENT)
public class RainbowSheepRenderer extends MobEntityRenderer<RainbowSheepEntity, RainbowSheepModel<RainbowSheepEntity>> {
	public RainbowSheepRenderer(EntityRendererFactory.Context context) {
		super(context, new RainbowSheepModel<>(context.getPart(EntityModelLayersInit.RAINBOW_SHEEP_ENTITY_MODEL_LAYER)), 0.7F);
		addFeature(new RainbowSheepWoolFeatureRenderer(this, context.getModelLoader()));
	}

	@Override
	public Identifier getTexture(RainbowSheepEntity entity) {
		Identifier texture = TextureUtils.getTextureIdentifier("sheep", RegistryNames.RAINBOW_SHEEP_REGISTRY_NAME);
		Identifier textureBlink = TextureUtils.getTextureIdentifier("sheep", RegistryNames.RAINBOW_SHEEP_REGISTRY_NAME, "blink");
		return entity.blinkManager.getBlinkRemainingTicks() > 0 ? textureBlink : texture;
	}
}
