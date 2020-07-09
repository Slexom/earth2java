package slexom.earthtojava.mobs.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import slexom.earthtojava.mobs.client.renderer.entity.layers.FleckedSheepWoolLayer;
import slexom.earthtojava.mobs.client.renderer.entity.model.FleckedSheepModel;
import slexom.earthtojava.mobs.entity.passive.FleckedSheepEntity;

@OnlyIn(Dist.CLIENT)
public class FleckedSheepRenderer extends MobRenderer<FleckedSheepEntity, FleckedSheepModel<FleckedSheepEntity>> {

    public FleckedSheepRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new FleckedSheepModel<>(), 0.7F);
        this.addLayer(new FleckedSheepWoolLayer(this));
    }

    public ResourceLocation getEntityTexture(FleckedSheepEntity entity) {
        ResourceLocation texture = new ResourceLocation("earthtojavamobs:textures/mobs/sheep/flecked_sheep/flecked_sheep.png");
        ResourceLocation textureBlink = new ResourceLocation("earthtojavamobs:textures/mobs/sheep/flecked_sheep/flecked_sheep_blink.png");
        return entity.getBlinkRemainingTicks() > 0 ? textureBlink : texture;
    }
}