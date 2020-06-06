package net.slexom.earthtojavamobs.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.slexom.earthtojavamobs.client.renderer.entity.layers.RockySheepRockLayer;
import net.slexom.earthtojavamobs.client.renderer.entity.model.RockySheepModel;
import net.slexom.earthtojavamobs.entity.RockySheepEntity;

public class RockySheepRenderer extends MobRenderer<RockySheepEntity.CustomEntity, RockySheepModel<RockySheepEntity.CustomEntity>> {
    private static final ResourceLocation SHEARED_SHEEP_TEXTURES = new ResourceLocation("earthtojavamobs:textures/mobs/sheep/rocky_sheep/rocky_sheep.png");
    private static final ResourceLocation texture = new ResourceLocation("earthtojavamobs:textures/mobs/sheep/rocky_sheep/rocky_sheep.png");
    private static final ResourceLocation textureBlink = new ResourceLocation("earthtojavamobs:textures/mobs/sheep/rocky_sheep/rocky_sheep_blink.png");
    private static final int blinkTime = 150;

    public RockySheepRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new RockySheepModel<>(), 0.7F);
        this.addLayer(new RockySheepRockLayer(this));
    }

    public ResourceLocation getEntityTexture(RockySheepEntity.CustomEntity entity) {
        return (entity.ticksExisted % blinkTime) == 0 || (entity.ticksExisted % blinkTime) == 1 || (entity.ticksExisted % blinkTime) == 2 || (entity.ticksExisted % blinkTime) == 3 ? textureBlink : texture;
    }
}