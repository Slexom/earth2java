package net.slexom.earthtojavamobs.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.PigRenderer;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.util.ResourceLocation;

import java.text.MessageFormat;

public class E2JPigRenderer extends PigRenderer {

    private final String registryName;

    public E2JPigRenderer(EntityRendererManager renderManagerIn, String registryName) {
        super(renderManagerIn);
        this.registryName = registryName;
    }

    @Override
    public ResourceLocation getEntityTexture(PigEntity entity) {
        String resourceTexture = MessageFormat.format("earthtojavamobs:textures/mobs/pig/{0}/{0}.png", this.registryName);
        String resourceTextureBlink = MessageFormat.format("earthtojavamobs:textures/mobs/pig/{0}/{0}_blink.png", this.registryName);
        ResourceLocation texture = new ResourceLocation(resourceTexture);
        ResourceLocation textureBlink = new ResourceLocation(resourceTextureBlink);
        int blinkTime = 175;
        return (entity.ticksExisted % blinkTime) == 0 || (entity.ticksExisted % blinkTime) == 1  || (entity.ticksExisted % blinkTime) == 2 ? textureBlink : texture;
    }
}
