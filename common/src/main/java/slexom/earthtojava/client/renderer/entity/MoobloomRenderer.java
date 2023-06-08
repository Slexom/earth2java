package slexom.earthtojava.client.renderer.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.CowEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.util.Identifier;
import slexom.earthtojava.client.renderer.entity.feature.MoobloomButtercupFeatureRenderer;
import slexom.earthtojava.entity.passive.MoobloomEntity;
import slexom.earthtojava.init.RegistryNames;

@Environment(EnvType.CLIENT)
public class MoobloomRenderer extends MobEntityRenderer<MoobloomEntity, CowEntityModel<MoobloomEntity>> {

	public MoobloomRenderer(EntityRendererFactory.Context context) {
		super(context, new CowEntityModel<>(context.getPart(EntityModelLayers.MOOSHROOM)), 0.7F);
		addFeature(new MoobloomButtercupFeatureRenderer<>(this));
	}

	public Identifier getTexture(MoobloomEntity entity) {
		Identifier texture = TextureUtils.getTextureIdentifier("cow", RegistryNames.MOOBLOOM_REGISTRY_NAME);
		Identifier textureBlink = TextureUtils.getTextureIdentifier("cow", RegistryNames.MOOBLOOM_REGISTRY_NAME, "blink");
		return entity.blinkManager.getBlinkRemainingTicks() > 0 ? textureBlink : texture;
	}

}