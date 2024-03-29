package slexom.earthtojava.client.renderer.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.CowEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.util.Identifier;
import slexom.earthtojava.client.renderer.entity.feature.MoolipPinkDaisyFeatureRenderer;
import slexom.earthtojava.entity.passive.MoolipEntity;
import slexom.earthtojava.init.RegistryNames;

@Environment(EnvType.CLIENT)
public class MoolipRenderer extends MobEntityRenderer<MoolipEntity, CowEntityModel<MoolipEntity>> {

	public MoolipRenderer(EntityRendererFactory.Context context) {
		super(context, new CowEntityModel<>(context.getPart(EntityModelLayers.MOOSHROOM)), 0.7F);
		addFeature(new MoolipPinkDaisyFeatureRenderer<>(this));
	}

	public Identifier getTexture(MoolipEntity entity) {
		Identifier texture = TextureUtils.getTextureIdentifier("cow", RegistryNames.MOOLIP_REGISTRY_NAME);
		Identifier textureBlink = TextureUtils.getTextureIdentifier("cow", RegistryNames.MOOLIP_REGISTRY_NAME, "blink");
		return entity.blinkManager.getBlinkRemainingTicks() > 0 ? textureBlink : texture;
	}
}