package slexom.earthtojava.client.renderer.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.util.Identifier;
import slexom.earthtojava.client.renderer.entity.model.E2JShearableCowModel;
import slexom.earthtojava.entity.base.E2JBaseShearableCowEntity;

@Environment(EnvType.CLIENT)
public class E2JShearableCowRenderer extends MobEntityRenderer<E2JBaseShearableCowEntity, E2JShearableCowModel<E2JBaseShearableCowEntity>> {

	private final String registryName;

	public E2JShearableCowRenderer(EntityRendererFactory.Context context, String registryName) {
		super(context, new E2JShearableCowModel<>(context.getPart(EntityModelLayers.COW)), 0.7F);
		this.registryName = registryName;
	}

	public Identifier getTexture(E2JBaseShearableCowEntity entity) {
		Identifier texture = TextureUtils.getTextureIdentifier("cow", registryName);
		Identifier textureBlink = TextureUtils.getTextureIdentifier("cow", registryName, "blink");
		Identifier textureSheared = TextureUtils.getTextureIdentifier("cow", registryName, "sheared");
		Identifier textureShearedBlink = TextureUtils.getTextureIdentifier("cow", registryName, "sheared_blink");
		boolean blink = entity.blinkManager.getBlinkRemainingTicks() > 0;
		if (entity.isSheared()) {
			if (blink) return textureShearedBlink;
			return textureSheared;
		}
		return blink ? textureBlink : texture;
	}
}