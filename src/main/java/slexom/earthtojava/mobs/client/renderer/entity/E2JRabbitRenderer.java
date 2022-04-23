package slexom.earthtojava.client.renderer.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory.Context;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.RabbitEntityModel;
import net.minecraft.util.Identifier;
import slexom.earthtojava.entity.base.E2JBaseRabbitEntity;

import java.text.MessageFormat;

@Environment(EnvType.CLIENT)
public class E2JRabbitRenderer extends MobEntityRenderer<E2JBaseRabbitEntity, RabbitEntityModel<E2JBaseRabbitEntity>> {

    private final String registryName;

    public E2JRabbitRenderer(Context context, String registryName) {
        super(context, new RabbitEntityModel<>(context.getPart(EntityModelLayers.RABBIT)), 0.3F);
        this.registryName = registryName;
    }

    @Override
    public Identifier getTexture(E2JBaseRabbitEntity entity) {
        String resourceTexture = MessageFormat.format("earthtojavamobs:textures/mobs/rabbit/{0}/{0}.png", this.registryName);
        String resourceTextureBlink = MessageFormat.format("earthtojavamobs:textures/mobs/rabbit/{0}/{0}_blink.png", this.registryName);
        Identifier texture = new Identifier(resourceTexture);
        Identifier textureBlink = new Identifier(resourceTextureBlink);
        return entity.blinkManager.getBlinkRemainingTicks() > 0 ? textureBlink : texture;
    }

}
