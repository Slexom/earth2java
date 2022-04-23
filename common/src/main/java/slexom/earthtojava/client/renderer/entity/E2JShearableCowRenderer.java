package slexom.earthtojava.client.renderer.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.util.Identifier;
import slexom.earthtojava.client.renderer.entity.model.E2JShearableCowModel;
import slexom.earthtojava.entity.base.E2JBaseShearableCowEntity;

import java.text.MessageFormat;

@Environment(EnvType.CLIENT)
public class E2JShearableCowRenderer extends MobEntityRenderer<E2JBaseShearableCowEntity, E2JShearableCowModel<E2JBaseShearableCowEntity>> {

    private final String registryName;

    public E2JShearableCowRenderer(EntityRendererFactory.Context context, String registryName) {
        super(context, new E2JShearableCowModel<>(context.getPart(EntityModelLayers.COW)), 0.7F);
        this.registryName = registryName;
    }

    public Identifier getTexture(E2JBaseShearableCowEntity entity) {
        String resourceTexture = MessageFormat.format("earthtojavamobs:textures/mobs/cow/{0}/{0}.png", this.registryName);
        String resourceTextureBlink = MessageFormat.format("earthtojavamobs:textures/mobs/cow/{0}/{0}_blink.png", this.registryName);
        String resourceTextureSheared = MessageFormat.format("earthtojavamobs:textures/mobs/cow/{0}/{0}_sheared.png", this.registryName);
        String resourceTextureShearedBlink = MessageFormat.format("earthtojavamobs:textures/mobs/cow/{0}/{0}_sheared_blink.png", this.registryName);
        Identifier texture = new Identifier(resourceTexture);
        Identifier textureBlink = new Identifier(resourceTextureBlink);
        Identifier textureSheared = new Identifier(resourceTextureSheared);
        Identifier textureShearedBlink = new Identifier(resourceTextureShearedBlink);
        boolean blink = entity.blinkManager.getBlinkRemainingTicks() > 0;
        return entity.isSheared() ? blink ? textureShearedBlink : textureSheared : blink ? textureBlink : texture;
    }
}