package slexom.earthtojava.mobs.client.renderer.entity;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import slexom.earthtojava.mobs.client.renderer.entity.model.JumboRabbitEntityModel;
import slexom.earthtojava.mobs.entity.passive.JumboRabbitEntity;

import java.text.MessageFormat;

public class JumboRabbitRenderer extends MobEntityRenderer<JumboRabbitEntity, JumboRabbitEntityModel<JumboRabbitEntity>> {

    private final String registryName;

    public JumboRabbitRenderer(EntityRenderDispatcher renderManagerIn, String registryName) {
        super(renderManagerIn, new JumboRabbitEntityModel<>(), 0.6F);
        this.registryName = registryName;
    }

    @Override
    public Identifier getTexture(JumboRabbitEntity entity) {
        String resourceTexture = MessageFormat.format("earthtojavamobs:textures/mobs/rabbit/jumbo_rabbit/jumbo_rabbit.png", this.registryName);
        String resourceTextureBlink = MessageFormat.format("earthtojavamobs:textures/mobs/rabbit/jumbo_rabbit/jumbo_rabbit_blink.png", this.registryName);
        Identifier texture = new Identifier(resourceTexture);
        Identifier textureBlink = new Identifier(resourceTextureBlink);
        return entity.getBlinkRemainingTicks() > 0 ? textureBlink : texture;
    }


}