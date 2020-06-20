package net.slexom.earthtojavamobs.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.CowModel;
import net.minecraft.entity.passive.CowEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class E2JCowRenderer extends E2JRenderer<CowEntity> {

    public E2JCowRenderer(EntityRendererManager renderManagerIn, String registryName) {
        super(renderManagerIn, new CowModel<>(), 0.7F, "cow", registryName);
    }

}
