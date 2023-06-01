package slexom.earthtojava.client.renderer.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.SpiderEntityModel;
import net.minecraft.util.Identifier;
import slexom.earthtojava.client.renderer.entity.feature.BoneSpiderEyesFeatureRenderer;
import slexom.earthtojava.entity.monster.BoneSpiderEntity;
import slexom.earthtojava.init.EntityTypesInit;

@Environment(EnvType.CLIENT)
public class BoneSpiderRenderer extends MobEntityRenderer<BoneSpiderEntity, SpiderEntityModel<BoneSpiderEntity>> {

	public BoneSpiderRenderer(EntityRendererFactory.Context context) {
		super(context, new SpiderEntityModel<>(context.getPart(EntityModelLayers.SPIDER)), 0.8F);
		addFeature(new BoneSpiderEyesFeatureRenderer<>(this));
	}

	protected float getLyingAngle(BoneSpiderEntity spiderEntity) {
		return 180.0F;
	}

	@Override
	public Identifier getTexture(BoneSpiderEntity entity) {
		Identifier texture = TextureUtils.getTextureIdentifier("spider", EntityTypesInit.BONE_SPIDER_REGISTRY_NAME);
		Identifier textureBlink = TextureUtils.getTextureIdentifier("spider", EntityTypesInit.BONE_SPIDER_REGISTRY_NAME, "blink");
		return entity.blinkManager.getBlinkRemainingTicks() > 0 ? textureBlink : texture;
	}

}
