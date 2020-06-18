package net.slexom.earthtojavamobs.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.slexom.earthtojavamobs.client.renderer.entity.layers.InkySheepWoolLayer;
import net.slexom.earthtojavamobs.client.renderer.entity.model.InkySheepModel;
import net.slexom.earthtojavamobs.entity.passive.InkySheepEntity;

@OnlyIn(Dist.CLIENT)
public class InkySheepRenderer extends MobRenderer<InkySheepEntity, InkySheepModel<InkySheepEntity>> {

    public InkySheepRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new InkySheepModel<>(), 0.7F);
        this.addLayer(new InkySheepWoolLayer(this));

    }

    public ResourceLocation getEntityTexture(InkySheepEntity entity) {
        ResourceLocation texture = new ResourceLocation("earthtojavamobs:textures/mobs/sheep/inky_sheep/inky_sheep.png");
        ResourceLocation textureBlink = new ResourceLocation("earthtojavamobs:textures/mobs/sheep/inky_sheep/inky_sheep_blink.png");
        int blinkTime = 150;
        return (entity.ticksExisted % blinkTime) == 0 || (entity.ticksExisted % blinkTime) == 1 || (entity.ticksExisted % blinkTime) == 2 || (entity.ticksExisted % blinkTime) == 3 ? textureBlink : texture;
    }
}