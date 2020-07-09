package slexom.earthtojava.mobs.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import slexom.earthtojava.mobs.client.renderer.entity.layers.InkySheepWoolLayer;
import slexom.earthtojava.mobs.client.renderer.entity.model.InkySheepModel;
import slexom.earthtojava.mobs.entity.passive.InkySheepEntity;

@OnlyIn(Dist.CLIENT)
public class InkySheepRenderer extends MobRenderer<InkySheepEntity, InkySheepModel<InkySheepEntity>> {

    public InkySheepRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new InkySheepModel<>(), 0.7F);
        this.addLayer(new InkySheepWoolLayer(this));
    }

    public ResourceLocation getEntityTexture(InkySheepEntity entity) {
        ResourceLocation texture = new ResourceLocation("earthtojavamobs:textures/mobs/sheep/inky_sheep/inky_sheep.png");
        ResourceLocation textureBlink = new ResourceLocation("earthtojavamobs:textures/mobs/sheep/inky_sheep/inky_sheep_blink.png");
        return entity.getBlinkRemainingTicks() > 0 ? textureBlink : texture;
    }

}