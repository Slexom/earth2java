package net.slexom.earthtojavamobs.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.slexom.earthtojavamobs.client.renderer.entity.layers.CluckshroomLayer;
import net.slexom.earthtojavamobs.client.renderer.entity.model.CluckshroomModel;
import net.slexom.earthtojavamobs.entity.passive.CluckshroomEntity;

@OnlyIn(Dist.CLIENT)
public class CluckshroomRenderer extends MobRenderer<CluckshroomEntity, CluckshroomModel<CluckshroomEntity>> {
    private static final ResourceLocation texture = new ResourceLocation("earthtojavamobs:textures/mobs/chicken/cluck_shroom/cluck_shroom.png");
    private static final ResourceLocation textureBlink = new ResourceLocation("earthtojavamobs:textures/mobs/chicken/cluck_shroom/cluck_shroom_blink.png");
    private static final int blinkTime = 100;

    public CluckshroomRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new CluckshroomModel<>(), 0.3F);
        this.addLayer(new CluckshroomLayer<>(this));
    }

    public ResourceLocation getEntityTexture(CluckshroomEntity entity) {
        return (entity.ticksExisted % blinkTime) == 0 || (entity.ticksExisted % blinkTime) == 1 ? textureBlink : texture;
    }

    protected float handleRotationFloat(CluckshroomEntity livingBase, float partialTicks) {
        float f = MathHelper.lerp(partialTicks, livingBase.oFlap, livingBase.wingRotation);
        float f1 = MathHelper.lerp(partialTicks, livingBase.oFlapSpeed, livingBase.destPos);
        return (MathHelper.sin(f) + 1.0F) * f1;
    }
}
