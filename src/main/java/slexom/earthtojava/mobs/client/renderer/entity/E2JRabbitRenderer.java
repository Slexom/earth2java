package slexom.earthtojava.mobs.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.RabbitModel;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import slexom.earthtojava.mobs.entity.base.E2JBaseRabbitEntity;

import java.text.MessageFormat;

@OnlyIn(Dist.CLIENT)
public class E2JRabbitRenderer extends MobRenderer<E2JBaseRabbitEntity<? extends RabbitEntity>, RabbitModel<E2JBaseRabbitEntity<? extends RabbitEntity>>> {

    private final String registryName;

    public E2JRabbitRenderer(EntityRendererManager renderManagerIn, String registryName) {
        super(renderManagerIn, new RabbitModel<>(), 0.3F);
        this.registryName = registryName;
    }

    @Override
    public ResourceLocation getEntityTexture(E2JBaseRabbitEntity<? extends RabbitEntity> entity) {
        String resourceTexture = MessageFormat.format("earthtojavamobs:textures/mobs/rabbit/{0}/{0}.png", this.registryName);
        String resourceTextureBlink = MessageFormat.format("earthtojavamobs:textures/mobs/rabbit/{0}/{0}_blink.png", this.registryName);
        ResourceLocation texture = new ResourceLocation(resourceTexture);
        ResourceLocation textureBlink = new ResourceLocation(resourceTextureBlink);
        return entity.getBlinkRemainingTicks() > 0 ? textureBlink : texture;
    }

}
