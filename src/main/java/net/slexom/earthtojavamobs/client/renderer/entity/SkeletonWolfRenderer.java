package net.slexom.earthtojavamobs.client.renderer.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.WolfRenderer;
import net.minecraft.client.renderer.entity.layers.WolfCollarLayer;
import net.minecraft.client.renderer.entity.model.WolfModel;
import net.minecraft.entity.monster.SkeletonEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.util.ResourceLocation;
import net.slexom.earthtojavamobs.client.renderer.entity.model.SkeletonWolfModel;
import net.slexom.earthtojavamobs.entity.SkeletonWolfEntity;

import java.text.MessageFormat;

public class SkeletonWolfRenderer extends MobRenderer<SkeletonWolfEntity.CustomEntity, SkeletonWolfModel<SkeletonWolfEntity.CustomEntity>> {

    public SkeletonWolfRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new SkeletonWolfModel<>(), 0.5F);

    }

    /**
     * Defines what float the third param in setRotationAngles of ModelBase is
     */
    protected float handleRotationFloat(SkeletonWolfEntity.CustomEntity livingBase, float partialTicks) {
        return livingBase.getTailRotation();
    }

    public ResourceLocation getEntityTexture(SkeletonWolfEntity.CustomEntity entity) {
        ResourceLocation texture = new ResourceLocation("earthtojavamobs:textures/mobs/wolf/skeleton_wolf/skeleton_wolf.png");
        ResourceLocation textureAngry = new ResourceLocation("earthtojavamobs:textures/mobs/wolf/skeleton_wolf/skeleton_wolf_angry.png");
        return entity.isAngry() ? textureAngry : texture;
    }

}
