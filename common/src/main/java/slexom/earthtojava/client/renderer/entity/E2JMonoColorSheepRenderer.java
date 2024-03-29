package slexom.earthtojava.client.renderer.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory.Context;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.SheepEntityModel;
import net.minecraft.util.Identifier;
import slexom.earthtojava.client.renderer.entity.feature.E2JMonoColorSheepWoolFeatureRenderer;
import slexom.earthtojava.entity.base.E2JBaseMonoColorSheepEntity;

import java.text.MessageFormat;

@Environment(EnvType.CLIENT)
public class E2JMonoColorSheepRenderer extends MobEntityRenderer<E2JBaseMonoColorSheepEntity, SheepEntityModel<E2JBaseMonoColorSheepEntity>> {

	private final String registryName;

	public E2JMonoColorSheepRenderer(Context context, String registryName) {
		super(context, new SheepEntityModel<>(context.getPart(EntityModelLayers.SHEEP)), 0.7F);
		this.registryName = registryName;
		String woolTexture = MessageFormat.format("earthtojavamobs:textures/mobs/sheep/{0}/{0}_fur.png", registryName);
		addFeature(new E2JMonoColorSheepWoolFeatureRenderer<>(this, context.getModelLoader(), woolTexture));
	}

	public Identifier getTexture(E2JBaseMonoColorSheepEntity entity) {
		Identifier texture = TextureUtils.getTextureIdentifier("sheep", registryName);
		Identifier textureBlink = TextureUtils.getTextureIdentifier("sheep", registryName, "blink");
		return entity.blinkManager.getBlinkRemainingTicks() > 0 ? textureBlink : texture;
	}
}