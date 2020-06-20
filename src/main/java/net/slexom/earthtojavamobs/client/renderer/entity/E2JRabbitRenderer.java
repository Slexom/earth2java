package net.slexom.earthtojavamobs.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.RabbitModel;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class E2JRabbitRenderer extends E2JRenderer<RabbitEntity> {

    public E2JRabbitRenderer(EntityRendererManager renderManagerIn, String registryName) {
        super(renderManagerIn, new RabbitModel<>(), 0.3F, "rabbit", registryName);
    }

}
