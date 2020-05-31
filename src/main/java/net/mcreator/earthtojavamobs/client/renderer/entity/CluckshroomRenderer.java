package net.mcreator.earthtojavamobs.client.renderer.entity;

import net.mcreator.earthtojavamobs.client.renderer.entity.model.CluckshroomModel;
import net.mcreator.earthtojavamobs.entity.CluckshroomEntity;
import net.mcreator.earthtojavamobs.client.renderer.entity.layers.CluckshroomLayer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class CluckshroomRenderer extends MobRenderer<CluckshroomEntity.CustomEntity, CluckshroomModel<CluckshroomEntity.CustomEntity>> {

    public CluckshroomRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new CluckshroomModel<>(), 0.3F);
        this.addLayer(new CluckshroomLayer<>(this));
    }

    public ResourceLocation getEntityTexture(CluckshroomEntity.CustomEntity entity) {
        return new ResourceLocation("earthtojavamobs:textures/texture_cluckshroom.png");
    }

    protected float handleRotationFloat(CluckshroomEntity.CustomEntity livingBase, float partialTicks) {
        float f = MathHelper.lerp(partialTicks, livingBase.oFlap, livingBase.wingRotation);
        float f1 = MathHelper.lerp(partialTicks, livingBase.oFlapSpeed, livingBase.destPos);
        return (MathHelper.sin(f) + 1.0F) * f1;
    }
}
