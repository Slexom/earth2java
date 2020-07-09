package slexom.earthtojava.mobs.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import slexom.earthtojava.mobs.client.renderer.entity.layers.CluckshroomLayer;
import slexom.earthtojava.mobs.client.renderer.entity.model.CluckshroomModel;
import slexom.earthtojava.mobs.entity.passive.CluckshroomEntity;

@OnlyIn(Dist.CLIENT)
public class CluckshroomRenderer extends MobRenderer<CluckshroomEntity, CluckshroomModel<CluckshroomEntity>> {

    public CluckshroomRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new CluckshroomModel<>(), 0.3F);
        this.addLayer(new CluckshroomLayer<>(this));
    }

    protected float handleRotationFloat(CluckshroomEntity livingBase, float partialTicks) {
        float f = MathHelper.lerp(partialTicks, livingBase.oFlap, livingBase.wingRotation);
        float f1 = MathHelper.lerp(partialTicks, livingBase.oFlapSpeed, livingBase.destPos);
        return (MathHelper.sin(f) + 1.0F) * f1;
    }

    @Override
    public ResourceLocation getEntityTexture(CluckshroomEntity entity) {
        ResourceLocation texture = new ResourceLocation("earthtojavamobs:textures/mobs/chicken/cluck_shroom/cluck_shroom.png");
        ResourceLocation textureBlink = new ResourceLocation("earthtojavamobs:textures/mobs/chicken/cluck_shroom/cluck_shroom_blink.png");
        return entity.getBlinkRemainingTicks() > 0 ? textureBlink : texture;
    }
}
