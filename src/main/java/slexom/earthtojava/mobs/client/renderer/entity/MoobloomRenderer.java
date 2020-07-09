package slexom.earthtojava.mobs.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.CowModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import slexom.earthtojava.mobs.client.renderer.entity.layers.MoobloomLayer;
import slexom.earthtojava.mobs.entity.passive.MoobloomEntity;

@OnlyIn(Dist.CLIENT)
public class MoobloomRenderer extends MobRenderer<MoobloomEntity, CowModel<MoobloomEntity>> {

    public MoobloomRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new CowModel<>(), 0.7F);
        this.addLayer(new MoobloomLayer<>(this));
    }

    public ResourceLocation getEntityTexture(MoobloomEntity entity) {
        ResourceLocation texture = new ResourceLocation("earthtojavamobs:textures/mobs/cow/moobloom/moobloom.png");
        ResourceLocation textureBlink = new ResourceLocation("earthtojavamobs:textures/mobs/cow/moobloom/moobloom_blink.png");
        return entity.getBlinkRemainingTicks() > 0 ? textureBlink : texture;
    }
}