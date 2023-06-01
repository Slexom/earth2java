package slexom.earthtojava.client.renderer.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory.Context;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.CowEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.util.Identifier;
import slexom.earthtojava.entity.base.E2JBaseCowEntity;

@Environment(EnvType.CLIENT)
public class E2JCowRenderer extends MobEntityRenderer<E2JBaseCowEntity, CowEntityModel<E2JBaseCowEntity>> {

	private final String registryName;

	public E2JCowRenderer(Context context, String registryName) {
		super(context, new CowEntityModel<>(context.getPart(EntityModelLayers.COW)), 0.7F);
		this.registryName = registryName;
	}

	@Override
	public Identifier getTexture(E2JBaseCowEntity entity) {
		Identifier texture = TextureUtils.getTextureIdentifier("cow", registryName);
		Identifier textureBlink = TextureUtils.getTextureIdentifier("cow", registryName, "blink");
		return entity.blinkManager.getBlinkRemainingTicks() > 0 ? textureBlink : texture;
	}
}
