package net.slexom.earthtojavamobs.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.slexom.earthtojavamobs.client.renderer.entity.layers.HornedSheepWoolLayer;
import net.slexom.earthtojavamobs.client.renderer.entity.model.HornedSheepModel;
import net.slexom.earthtojavamobs.entity.passive.HornedSheepEntity;

@OnlyIn(Dist.CLIENT)
public class HornedSheepRenderer extends MobRenderer<HornedSheepEntity, HornedSheepModel<HornedSheepEntity>> {
    private static final ResourceLocation SHEARED_SHEEP_TEXTURES = new ResourceLocation("earthtojavamobs:textures/mobs/sheep/horned_sheep/horned_sheep.png");
    private static final ResourceLocation SHEARED_SHEEP_BLINK_TEXTURES = new ResourceLocation("earthtojavamobs:textures/mobs/sheep/horned_sheep/horned_sheep_blink.png");
    private static final int blinkTime = 100;

    public HornedSheepRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new HornedSheepModel<>(), 0.7F);
        this.addLayer(new HornedSheepWoolLayer(this));
    }

    public ResourceLocation getEntityTexture(HornedSheepEntity entity) {
        return (entity.ticksExisted % blinkTime) == 0 || (entity.ticksExisted % blinkTime) == 1 || (entity.ticksExisted % blinkTime) == 2 || (entity.ticksExisted % blinkTime) == 3 ? SHEARED_SHEEP_BLINK_TEXTURES : SHEARED_SHEEP_TEXTURES;
    }
}