package slexom.earthtojava.client.renderer.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory.Context;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.SpiderEntityModel;
import net.minecraft.entity.mob.SpiderEntity;
import net.minecraft.util.Identifier;
import slexom.earthtojava.entity.base.E2JBaseSpiderEntity;

import java.text.MessageFormat;

@Environment(EnvType.CLIENT)
public class E2JSpiderRenderer extends MobEntityRenderer<E2JBaseSpiderEntity<? extends SpiderEntity>, SpiderEntityModel<E2JBaseSpiderEntity<? extends SpiderEntity>>> {

    private final String registryName;

    public E2JSpiderRenderer(Context context, String registryName) {
        super(context, new SpiderEntityModel<>(context.getPart(EntityModelLayers.SPIDER)), 0.8F);
        this.registryName = registryName;
    }

    protected float getDeathMaxRotation(E2JBaseSpiderEntity entityLivingBaseIn) {
        return 180.0F;
    }

    @Override
    public Identifier getTexture(E2JBaseSpiderEntity entity) {
        String resourceTexture = MessageFormat.format("earthtojavamobs:textures/mobs/spider/{0}/{0}.png", this.registryName);
        String resourceTextureBlink = MessageFormat.format("earthtojavamobs:textures/mobs/spider/{0}/{0}_blink.png", this.registryName);
        Identifier texture = new Identifier(resourceTexture);
        Identifier textureBlink = new Identifier(resourceTextureBlink);
        return entity.blinkManager.getBlinkRemainingTicks() > 0 ? textureBlink : texture;
    }
}
