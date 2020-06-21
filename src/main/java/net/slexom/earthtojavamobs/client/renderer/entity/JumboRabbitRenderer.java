package net.slexom.earthtojavamobs.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.slexom.earthtojavamobs.client.renderer.entity.model.JumboRabbitModel;
import net.slexom.earthtojavamobs.entity.passive.JumboRabbitEntity;

public class JumboRabbitRenderer extends E2JRenderer<JumboRabbitEntity> {

    public JumboRabbitRenderer(EntityRendererManager renderManagerIn, String registryName) {
        super(renderManagerIn, new JumboRabbitModel<>(), 0.3F, "rabbit", registryName);
    }

}