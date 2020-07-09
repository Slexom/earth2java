package slexom.earthtojava.mobs.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import slexom.earthtojava.mobs.client.renderer.entity.model.JumboRabbitModel;
import slexom.earthtojava.mobs.entity.passive.JumboRabbitEntity;

import java.text.MessageFormat;

public class JumboRabbitRenderer extends MobRenderer<JumboRabbitEntity, JumboRabbitModel<JumboRabbitEntity>> {

    private final String registryName;

    public JumboRabbitRenderer(EntityRendererManager renderManagerIn, String registryName) {
        super(renderManagerIn, new JumboRabbitModel<>(), 0.6F);
        this.registryName = registryName;
    }

    @Override
    public ResourceLocation getEntityTexture(JumboRabbitEntity entity) {
        String resourceTexture = MessageFormat.format("earthtojavamobs:textures/mobs/rabbit/jumbo_rabbit/jumbo_rabbit.png", this.registryName);
        String resourceTextureBlink = MessageFormat.format("earthtojavamobs:textures/mobs/rabbit/jumbo_rabbit/jumbo_rabbit_blink.png", this.registryName);
        ResourceLocation texture = new ResourceLocation(resourceTexture);
        ResourceLocation textureBlink = new ResourceLocation(resourceTextureBlink);
        return entity.getBlinkRemainingTicks() > 0 ? textureBlink : texture;
    }


}