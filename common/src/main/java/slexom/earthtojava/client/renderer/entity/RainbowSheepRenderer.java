package slexom.earthtojava.client.renderer.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import slexom.earthtojava.client.renderer.entity.feature.RainbowSheepWoolFeatureRenderer;
import slexom.earthtojava.client.renderer.entity.model.RainbowSheepModel;
import slexom.earthtojava.entity.passive.RainbowSheepEntity;
import slexom.earthtojava.init.EntityModelLayersInit;

@Environment(EnvType.CLIENT)
public class RainbowSheepRenderer extends MobEntityRenderer<RainbowSheepEntity, RainbowSheepModel<RainbowSheepEntity>> {
    public RainbowSheepRenderer(EntityRendererFactory.Context context) {
        super(context, new RainbowSheepModel<>(context.getPart(EntityModelLayersInit.RAINBOW_SHEEP_ENTITY_MODEL_LAYER)), 0.7F);
        this.addFeature(new RainbowSheepWoolFeatureRenderer(this, context.getModelLoader()));
    }

    @Override
    public Identifier getTexture(RainbowSheepEntity entity) {
        Identifier texture = new Identifier("earthtojavamobs:textures/mobs/sheep/rainbow_sheep/rainbow_sheep.png");
        Identifier textureBlink = new Identifier("earthtojavamobs:textures/mobs/sheep/rainbow_sheep/rainbow_sheep_blink.png");
        return entity.blinkManager.getBlinkRemainingTicks() > 0 ? textureBlink : texture;
    }
}
