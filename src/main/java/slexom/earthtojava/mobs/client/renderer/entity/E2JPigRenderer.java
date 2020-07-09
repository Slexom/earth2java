package slexom.earthtojava.mobs.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.PigModel;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import slexom.earthtojava.mobs.client.renderer.entity.layers.E2JBasePigSaddleLayer;
import slexom.earthtojava.mobs.entity.base.E2JBasePigEntity;

import java.text.MessageFormat;

@OnlyIn(Dist.CLIENT)
public class E2JPigRenderer extends MobRenderer<E2JBasePigEntity<? extends PigEntity>, PigModel<E2JBasePigEntity<? extends PigEntity>>> {

    private final String registryName;

    public E2JPigRenderer(EntityRendererManager renderManagerIn, String registryName) {
        super(renderManagerIn, new PigModel<>(), 0.7F);
        this.addLayer(new E2JBasePigSaddleLayer(this));
        this.registryName = registryName;
    }

    @Override
    public ResourceLocation getEntityTexture(E2JBasePigEntity<? extends PigEntity> entity) {
        String resourceTexture = MessageFormat.format("earthtojavamobs:textures/mobs/pig/{0}/{0}.png", this.registryName);
        String resourceTextureBlink = MessageFormat.format("earthtojavamobs:textures/mobs/pig/{0}/{0}_blink.png", this.registryName);
        ResourceLocation texture = new ResourceLocation(resourceTexture);
        ResourceLocation textureBlink = new ResourceLocation(resourceTextureBlink);
        return entity.getBlinkRemainingTicks() > 0 ? textureBlink : texture;
    }

}
