package slexom.earthtojava.mobs.client.renderer.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import slexom.earthtojava.mobs.client.renderer.entity.feature.RainbowSheepWoolFeatureRenderer;
import slexom.earthtojava.mobs.client.renderer.entity.model.RainbowSheepModel;
import slexom.earthtojava.mobs.entity.passive.RainbowSheepEntity;
import slexom.earthtojava.mobs.init.EntityModeLayersInit;

@Environment(EnvType.CLIENT)
public class RainbowSheepRenderer extends MobEntityRenderer<RainbowSheepEntity, RainbowSheepModel<RainbowSheepEntity>> {
    public RainbowSheepRenderer(EntityRendererFactory.Context context) {
        super(context, new RainbowSheepModel<>(context.getPart(EntityModeLayersInit.RAINBOW_SHEEP_ENTITY_MODEL_LAYER)), 0.7F);
        this.addFeature(new RainbowSheepWoolFeatureRenderer(this, context.getModelLoader()));
    }

    @Override
    public Identifier getTexture(RainbowSheepEntity entity) {
        Identifier texture = new Identifier("earthtojavamobs:textures/mobs/sheep/rainbow_sheep/rainbow_sheep.png");
        Identifier textureBlink = new Identifier("earthtojavamobs:textures/mobs/sheep/rainbow_sheep/rainbow_sheep_blink.png");
        return entity.getBlinkRemainingTicks() > 0 ? textureBlink : texture;
    }
}
