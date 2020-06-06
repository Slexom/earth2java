package net.slexom.earthtojavamobs.client.renderer.entity;

import net.slexom.earthtojavamobs.entity.WoolyCowEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.CowModel;
import net.minecraft.util.ResourceLocation;

public class WoolyCowRenderer extends MobRenderer<WoolyCowEntity.CustomEntity, CowModel<WoolyCowEntity.CustomEntity>> {
    private static final ResourceLocation texture = new ResourceLocation("earthtojavamobs:textures/mobs/cow/wooly_cow/wooly_cow.png");
    private static final ResourceLocation textureBlink = new ResourceLocation("earthtojavamobs:textures/mobs/cow/wooly_cow/wooly_cow_blink.png");
    private static final ResourceLocation textureSheared = new ResourceLocation("earthtojavamobs:textures/mobs/cow/wooly_cow/wooly_cow_sheared.png");
    private static final ResourceLocation textureShearedBlink = new ResourceLocation("earthtojavamobs:textures/mobs/cow/wooly_cow/wooly_cow_sheared_blink.png");
    private static final int blinkTime = 100;

    public WoolyCowRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new CowModel<>(), 0.7F);
    }

    public ResourceLocation getEntityTexture(WoolyCowEntity.CustomEntity entity) {
        boolean blink = (entity.ticksExisted % blinkTime) == 0 || (entity.ticksExisted % blinkTime) == 1 || (entity.ticksExisted % blinkTime) == 2 || (entity.ticksExisted % blinkTime) == 3;
        return entity.getSheared() ? blink ? textureShearedBlink : textureSheared : blink ? textureBlink : texture;
    }
}