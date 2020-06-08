package net.slexom.earthtojavamobs.client.renderer.entity;

import net.minecraft.client.renderer.entity.ChickenRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.util.ResourceLocation;

import java.text.MessageFormat;

public class E2JChickenRenderer extends ChickenRenderer {

    private final String registryName;

    public E2JChickenRenderer(EntityRendererManager renderManagerIn, String registryName) {
        super(renderManagerIn);
        this.registryName = registryName;
    }

    @Override
    public ResourceLocation getEntityTexture(ChickenEntity entity) {
        String resourceTexture = MessageFormat.format("earthtojavamobs:textures/mobs/chicken/{0}/{0}.png", this.registryName);
        String resourceTextureBlink = MessageFormat.format("earthtojavamobs:textures/mobs/chicken/{0}/{0}_blink.png", this.registryName);
        ResourceLocation texture = new ResourceLocation(resourceTexture);
        ResourceLocation textureBlink = new ResourceLocation(resourceTextureBlink);
        int blinkTime = 100;
        return (entity.ticksExisted % blinkTime) == 0 || (entity.ticksExisted % blinkTime) == 1 ? textureBlink : texture;
    }
}
