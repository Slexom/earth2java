package slexom.earthtojava.client.renderer.entity;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import slexom.earthtojava.client.renderer.entity.model.JumboRabbitModel;
import slexom.earthtojava.entity.passive.JumboRabbitEntity;
import slexom.earthtojava.init.EntityModelLayersInit;
import slexom.earthtojava.init.EntityTypesInit;

import java.text.MessageFormat;

public class JumboRabbitRenderer extends MobEntityRenderer<JumboRabbitEntity, JumboRabbitModel<JumboRabbitEntity>> {

    private final String registryName = EntityTypesInit.JUMBO_RABBIT_REGISTRY_NAME;

    public JumboRabbitRenderer(EntityRendererFactory.Context context) {
        super(context, new JumboRabbitModel<>(context.getPart(EntityModelLayersInit.JUMBO_RABBIT_ENTITY_MODEL_LAYER)), 0.6F);
    }

    @Override
    public Identifier getTexture(JumboRabbitEntity entity) {
        String resourceTexture = MessageFormat.format("earthtojavamobs:textures/mobs/rabbit/{0}/{0}.png", this.registryName);
        String resourceTextureBlink = MessageFormat.format("earthtojavamobs:textures/mobs/rabbit/{0}/{0}_blink.png", this.registryName);
        Identifier texture = new Identifier(resourceTexture);
        Identifier textureBlink = new Identifier(resourceTextureBlink);
        return entity.blinkManager.getBlinkRemainingTicks() > 0 ? textureBlink : texture;
    }

}