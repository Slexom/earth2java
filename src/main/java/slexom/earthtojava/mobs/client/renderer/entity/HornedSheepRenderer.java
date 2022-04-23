package slexom.earthtojava.client.renderer.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import slexom.earthtojava.client.renderer.entity.feature.HornedSheepWoolFeatureRenderer;
import slexom.earthtojava.client.renderer.entity.model.HornedSheepModel;
import slexom.earthtojava.entity.passive.HornedSheepEntity;
import slexom.earthtojava.init.EntityModelLayersInit;

@Environment(EnvType.CLIENT)
public class HornedSheepRenderer extends MobEntityRenderer<HornedSheepEntity, HornedSheepModel<HornedSheepEntity>> {

    public HornedSheepRenderer(EntityRendererFactory.Context context) {
        super(context, new HornedSheepModel<>(context.getPart(EntityModelLayersInit.HORNED_SHEEP_ENTITY_MODEL_LAYER)), 0.7F);
        this.addFeature(new HornedSheepWoolFeatureRenderer(this, context.getModelLoader()));
    }

    public Identifier getTexture(HornedSheepEntity entity) {
        Identifier texture = new Identifier("earthtojavamobs:textures/mobs/sheep/horned_sheep/horned_sheep.png");
        Identifier textureBlink = new Identifier("earthtojavamobs:textures/mobs/sheep/horned_sheep/horned_sheep_blink.png");
        return entity.blinkManager.getBlinkRemainingTicks() > 0 ? textureBlink : texture;
    }
}