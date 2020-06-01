package net.slexom.earthtojavamobs.client.renderer.entity;

import net.slexom.earthtojavamobs.entity.WoolyCowEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.CowModel;
import net.minecraft.util.ResourceLocation;

public class WoolyCowRenderer extends MobRenderer<WoolyCowEntity.CustomEntity, CowModel<WoolyCowEntity.CustomEntity>> {

    public WoolyCowRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new CowModel<>(), 0.7F);
        //todo: fix wool model
//        this.addLayer(new WoolyCowLayer(this));
    }

    public ResourceLocation getEntityTexture(WoolyCowEntity.CustomEntity entity) {
        ResourceLocation texture = new ResourceLocation("earthtojavamobs:textures/mobs/cow/wooly_cow/wooly_cow.png");
        ResourceLocation textureSheared = new ResourceLocation("earthtojavamobs:textures/mobs/cow/wooly_cow/wooly_cow_sheared.png");
        return entity.getSheared() ? textureSheared : texture;
//
//        if (entity.getSheared()) {
//            return new ResourceLocation("earthtojavamobs:textures/mobs/cow/wooly_cow/wooly_cow_sheared.png");
//        } else {
//            return new ResourceLocation("earthtojavamobs:textures/mobs/cow/wooly_cow/wooly_cow.png");
//        }
    }
}