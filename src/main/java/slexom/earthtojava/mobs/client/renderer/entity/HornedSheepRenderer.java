package slexom.earthtojava.mobs.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import slexom.earthtojava.mobs.client.renderer.entity.layers.HornedSheepWoolLayer;
import slexom.earthtojava.mobs.client.renderer.entity.model.HornedSheepModel;
import slexom.earthtojava.mobs.entity.passive.HornedSheepEntity;

@OnlyIn(Dist.CLIENT)
public class HornedSheepRenderer extends MobRenderer<HornedSheepEntity, HornedSheepModel<HornedSheepEntity>> {

    public HornedSheepRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new HornedSheepModel<>(), 0.7F);
        this.addLayer(new HornedSheepWoolLayer(this));
    }

    public ResourceLocation getEntityTexture(HornedSheepEntity entity) {
        ResourceLocation texture = new ResourceLocation("earthtojavamobs:textures/mobs/sheep/horned_sheep/horned_sheep.png");
        ResourceLocation textureBlink = new ResourceLocation("earthtojavamobs:textures/mobs/sheep/horned_sheep/horned_sheep_blink.png");
        return entity.getBlinkRemainingTicks() > 0 ? textureBlink : texture;
    }
}