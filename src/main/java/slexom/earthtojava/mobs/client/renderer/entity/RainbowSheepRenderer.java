package slexom.earthtojava.mobs.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import slexom.earthtojava.mobs.client.renderer.entity.layers.RainbowSheepWoolLayer;
import slexom.earthtojava.mobs.client.renderer.entity.model.RainbowSheepModel;
import slexom.earthtojava.mobs.entity.passive.RainbowSheepEntity;

public class RainbowSheepRenderer extends MobRenderer<RainbowSheepEntity, RainbowSheepModel<RainbowSheepEntity>> {
    public RainbowSheepRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new RainbowSheepModel<>(), 0.7F);
        this.addLayer(new RainbowSheepWoolLayer(this));
    }

    @Override
    public ResourceLocation getEntityTexture(RainbowSheepEntity entity) {
        ResourceLocation texture = new ResourceLocation("earthtojavamobs:textures/mobs/sheep/rainbow_sheep/rainbow_sheep.png");
        ResourceLocation textureBlink = new ResourceLocation("earthtojavamobs:textures/mobs/sheep/rainbow_sheep/rainbow_sheep_blink.png");
        return entity.getBlinkRemainingTicks() > 0 ? textureBlink : texture;
    }
}
