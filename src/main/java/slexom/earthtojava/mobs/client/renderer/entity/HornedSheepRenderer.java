package slexom.earthtojava.mobs.client.renderer.entity;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import slexom.earthtojava.mobs.client.renderer.entity.layers.HornedSheepWoolLayer;
import slexom.earthtojava.mobs.client.renderer.entity.model.HornedSheepModel;
import slexom.earthtojava.mobs.entity.passive.HornedSheepEntity;

@Environment(EnvType.CLIENT)
public class HornedSheepRenderer extends MobEntityRenderer<HornedSheepEntity, HornedSheepModel<HornedSheepEntity>> {

    public HornedSheepRenderer(EntityRenderDispatcher renderManagerIn) {
        super(renderManagerIn, new HornedSheepModel<>(), 0.7F);
        this.addFeature(new HornedSheepWoolLayer(this));
    }

    public Identifier getTexture(HornedSheepEntity entity) {
        Identifier texture = new Identifier("earthtojavamobs:textures/mobs/sheep/horned_sheep/horned_sheep.png");
        Identifier textureBlink = new Identifier("earthtojavamobs:textures/mobs/sheep/horned_sheep/horned_sheep_blink.png");
        return entity.getBlinkRemainingTicks() > 0 ? textureBlink : texture;
    }
}