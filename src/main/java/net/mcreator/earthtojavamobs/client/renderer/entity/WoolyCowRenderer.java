package net.mcreator.earthtojavamobs.client.renderer.entity;

import net.mcreator.earthtojavamobs.entity.WoolyCowEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.CowModel;
import net.minecraft.util.ResourceLocation;

public class WoolyCowRenderer extends MobRenderer<WoolyCowEntity.CustomEntity, CowModel<WoolyCowEntity.CustomEntity>> {

    public WoolyCowRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new CowModel<>(), 0.7F);
        //todo: fix wool mode
//        this.addLayer(new WoolyCowLayer(this));
    }

    /**
     * Returns the location of an entity's texture.
     */
    public ResourceLocation getEntityTexture(WoolyCowEntity.CustomEntity entity) {
        if (entity.getSheared()) {
            return new ResourceLocation("earthtojavamobs:textures/texture_wooly_cow_sheared.png");
        } else {

            return new ResourceLocation("earthtojavamobs:textures/texture_wooly_cow.png");
        }
    }
}