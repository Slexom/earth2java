package net.slexom.earthtojavamobs.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.slexom.earthtojavamobs.client.renderer.entity.model.SkeletonWolfModel;
import net.slexom.earthtojavamobs.entity.SkeletonWolfEntity;

@OnlyIn(Dist.CLIENT)
public class SkeletonWolfRenderer extends MobRenderer<SkeletonWolfEntity, SkeletonWolfModel<SkeletonWolfEntity>> {

    public SkeletonWolfRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new SkeletonWolfModel<>(), 0.5F);

    }

    /**
     * Defines what float the third param in setRotationAngles of ModelBase is
     */
    protected float handleRotationFloat(SkeletonWolfEntity livingBase, float partialTicks) {
        return livingBase.getTailRotation();
    }

    public ResourceLocation getEntityTexture(SkeletonWolfEntity entity) {
        ResourceLocation texture = new ResourceLocation("earthtojavamobs:textures/mobs/wolf/skeleton_wolf/skeleton_wolf.png");
        ResourceLocation textureAngry = new ResourceLocation("earthtojavamobs:textures/mobs/wolf/skeleton_wolf/skeleton_wolf_angry.png");
        return entity.isAngry() ? textureAngry : texture;
    }

}
