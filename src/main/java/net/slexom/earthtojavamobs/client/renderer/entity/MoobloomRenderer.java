package net.slexom.earthtojavamobs.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.CowModel;
import net.minecraft.util.ResourceLocation;
import net.slexom.earthtojavamobs.client.renderer.entity.layers.MoobloomLayer;
import net.slexom.earthtojavamobs.entity.MoobloomEntity;

public class MoobloomRenderer extends MobRenderer<MoobloomEntity.CustomEntity, CowModel<MoobloomEntity.CustomEntity>> {

    public MoobloomRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new CowModel<>(), 0.7F);
        this.addLayer(new MoobloomLayer<>(this));
    }

    /**
     * Returns the location of an entity's texture.
     */
    public ResourceLocation getEntityTexture(MoobloomEntity.CustomEntity entity) {
        return new ResourceLocation("earthtojavamobs:textures/mobs/cow/moobloom/moobloom.png");

    }
}