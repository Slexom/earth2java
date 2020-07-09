package slexom.earthtojava.mobs.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import slexom.earthtojava.mobs.client.renderer.entity.layers.RockySheepWoolLayer;
import slexom.earthtojava.mobs.client.renderer.entity.model.RockySheepModel;
import slexom.earthtojava.mobs.entity.passive.RockySheepEntity;

@OnlyIn(Dist.CLIENT)
public class RockySheepRenderer extends MobRenderer<RockySheepEntity, RockySheepModel<RockySheepEntity>> {

    public RockySheepRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new RockySheepModel<>(), 0.7F);
        this.addLayer(new RockySheepWoolLayer(this));
    }

    public ResourceLocation getEntityTexture(RockySheepEntity entity) {
        ResourceLocation texture = new ResourceLocation("earthtojavamobs:textures/mobs/sheep/rocky_sheep/rocky_sheep.png");
        ResourceLocation textureBlink = new ResourceLocation("earthtojavamobs:textures/mobs/sheep/rocky_sheep/rocky_sheep_blink.png");
        return entity.getBlinkRemainingTicks() > 0 ? textureBlink : texture;
    }
}