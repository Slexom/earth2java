package net.slexom.earthtojavamobs.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.slexom.earthtojavamobs.client.renderer.entity.model.MelonGolemModel;
import net.slexom.earthtojavamobs.entity.passive.MelonGolemEntity;

@OnlyIn(Dist.CLIENT)
public class MelonGolemRenderer extends MobRenderer<MelonGolemEntity, MelonGolemModel<MelonGolemEntity>> {
    private static final ResourceLocation SNOW_MAN_TEXTURES = new ResourceLocation("textures/entity/snow_golem.png");

    private static final ResourceLocation texture = new ResourceLocation("earthtojavamobs:textures/mobs/snow_golem/melon_golem/melon_golem.png");
    private static final ResourceLocation textureBlink = new ResourceLocation("earthtojavamobs:textures/mobs/snow_golem/melon_golem/melon_golem_blink.png");
    // private static final ResourceLocation textureShoot = new ResourceLocation("earthtojavamobs:textures/mobs/snow_golem/melon_golem/melon_golem_blink.png");
    private static final int blinkTime = 250;

    public MelonGolemRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new MelonGolemModel<>(), 0.5F);
    }

    public ResourceLocation getEntityTexture(MelonGolemEntity entity) {
        return (entity.ticksExisted % blinkTime) == 0 || (entity.ticksExisted % blinkTime) == 1 || (entity.ticksExisted % blinkTime) == 2 || (entity.ticksExisted % blinkTime) == 3 ? textureBlink : texture;
    }
}