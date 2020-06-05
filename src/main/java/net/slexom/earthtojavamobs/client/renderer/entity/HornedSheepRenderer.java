package net.slexom.earthtojavamobs.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.slexom.earthtojavamobs.client.renderer.entity.layers.HornedSheepWoolLayer;
import net.slexom.earthtojavamobs.client.renderer.entity.model.HornedSheepModel;
import net.slexom.earthtojavamobs.entity.HornedSheepEntity;

public class HornedSheepRenderer extends MobRenderer<HornedSheepEntity.CustomEntity, HornedSheepModel<HornedSheepEntity.CustomEntity>> {
    private static final ResourceLocation SHEARED_SHEEP_TEXTURES = new ResourceLocation("earthtojavamobs:textures/mobs/sheep/horned_sheep/horned_sheep.png");

    public HornedSheepRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new HornedSheepModel<>(), 0.7F);
        this.addLayer(new HornedSheepWoolLayer(this));
    }

    /**
     * Returns the location of an entity's texture.
     */
    public ResourceLocation getEntityTexture(HornedSheepEntity.CustomEntity entity) {
        return SHEARED_SHEEP_TEXTURES;
    }
}