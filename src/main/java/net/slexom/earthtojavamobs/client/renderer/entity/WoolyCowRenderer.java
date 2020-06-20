package net.slexom.earthtojavamobs.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.slexom.earthtojavamobs.entity.passive.WoolyCowEntity;

@OnlyIn(Dist.CLIENT)
public class WoolyCowRenderer extends E2JCowRenderer {

    private int remainingTick = 0;

    public WoolyCowRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, "wooly_cow");
    }

    public ResourceLocation getEntityTexture(WoolyCowEntity entity) {
        ResourceLocation texture = new ResourceLocation("earthtojavamobs:textures/mobs/cow/wooly_cow/wooly_cow.png");
        ResourceLocation textureBlink = new ResourceLocation("earthtojavamobs:textures/mobs/cow/wooly_cow/wooly_cow_blink.png");
        ResourceLocation textureSheared = new ResourceLocation("earthtojavamobs:textures/mobs/cow/wooly_cow/wooly_cow_sheared.png");
        ResourceLocation textureShearedBlink = new ResourceLocation("earthtojavamobs:textures/mobs/cow/wooly_cow/wooly_cow_sheared_blink.png");
        boolean blink = remainingTick > 0;
        return entity.getSheared() ? blink ? textureShearedBlink : textureSheared : blink ? textureBlink : texture;
    }
}