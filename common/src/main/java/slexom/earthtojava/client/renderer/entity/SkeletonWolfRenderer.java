package slexom.earthtojava.client.renderer.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.util.Identifier;
import slexom.earthtojava.client.renderer.entity.model.SkeletonWolfModel;
import slexom.earthtojava.entity.monster.SkeletonWolfEntity;
import slexom.earthtojava.init.EntityTypesInit;

@Environment(EnvType.CLIENT)
public class SkeletonWolfRenderer extends MobEntityRenderer<SkeletonWolfEntity, SkeletonWolfModel<SkeletonWolfEntity>> {

	public SkeletonWolfRenderer(EntityRendererFactory.Context context) {
		super(context, new SkeletonWolfModel<>(context.getPart(EntityModelLayers.WOLF)), 0.5F);
	}

	protected float getAnimationProgress(SkeletonWolfEntity wolfEntity, float f) {
		return wolfEntity.getTailAngle();
	}

	public Identifier getTexture(SkeletonWolfEntity entity) {
		Identifier texture = TextureUtils.getTextureIdentifier("wolf", EntityTypesInit.SKELETON_WOLF_REGISTRY_NAME);
		Identifier textureAngry = TextureUtils.getTextureIdentifier("wolf", EntityTypesInit.SKELETON_WOLF_REGISTRY_NAME, "angry");
		return entity.isAngry() ? textureAngry : texture;
	}

}
