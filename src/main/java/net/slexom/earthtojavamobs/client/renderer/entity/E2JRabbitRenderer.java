package net.slexom.earthtojavamobs.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.PigRenderer;
import net.minecraft.client.renderer.entity.RabbitRenderer;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.text.MessageFormat;

@OnlyIn(Dist.CLIENT)
public class E2JRabbitRenderer extends RabbitRenderer {

    private final String registryName;

    public E2JRabbitRenderer(EntityRendererManager renderManagerIn, String registryName) {
        super(renderManagerIn);
        this.registryName = registryName;
    }

    @Override
    public ResourceLocation getEntityTexture(RabbitEntity entity) {
        String resourceTexture = MessageFormat.format("earthtojavamobs:textures/mobs/rabbit/{0}/{0}.png", this.registryName);
        String resourceTextureBlink = MessageFormat.format("earthtojavamobs:textures/mobs/rabbit/{0}/{0}_blink.png", this.registryName);
        ResourceLocation texture = new ResourceLocation(resourceTexture);
        ResourceLocation textureBlink = new ResourceLocation(resourceTextureBlink);
        int blinkTime = 250;
        return (entity.ticksExisted % blinkTime) == 0 || (entity.ticksExisted % blinkTime) == 1  || (entity.ticksExisted % blinkTime) == 2 ? textureBlink : texture;
    }
}
