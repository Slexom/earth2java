package net.slexom.earthtojavamobs.client.renderer.entity;

import net.minecraft.client.renderer.entity.CowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.util.ResourceLocation;

import java.text.MessageFormat;

public class E2JCowRenderer extends CowRenderer {

    private final String registryName;

    public E2JCowRenderer(EntityRendererManager renderManagerIn, String registryName) {
        super(renderManagerIn);
        this.registryName = registryName;
    }

    @Override
    public ResourceLocation getEntityTexture(CowEntity entity) {
        String resourceTexture = MessageFormat.format("earthtojavamobs:textures/mobs/cow/{0}/{0}.png", this.registryName);
        String resourceTextureBlink = MessageFormat.format("earthtojavamobs:textures/mobs/cow/{0}/{0}_blink.png", this.registryName);
        ResourceLocation texture = new ResourceLocation(resourceTexture);
        ResourceLocation textureBlink = new ResourceLocation(resourceTextureBlink);
        int blinkTime = 175;
        return (entity.ticksExisted % blinkTime) == 0 || (entity.ticksExisted % blinkTime) == 1 ? textureBlink : texture;
    }
}
