package slexom.earthtojava.mobs.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.SpiderModel;
import net.minecraft.entity.monster.SpiderEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import slexom.earthtojava.mobs.entity.base.E2JBaseSpiderEntity;

import java.text.MessageFormat;

@OnlyIn(Dist.CLIENT)
public class E2JSpiderRenderer extends MobRenderer<E2JBaseSpiderEntity<? extends SpiderEntity>, SpiderModel<E2JBaseSpiderEntity<? extends SpiderEntity>>> {

    private final String registryName;

    public E2JSpiderRenderer(EntityRendererManager renderManagerIn, String registryName) {
        super(renderManagerIn, new SpiderModel<>(), 0.8F);
        this.registryName = registryName;
    }

    protected float getDeathMaxRotation(E2JBaseSpiderEntity entityLivingBaseIn) {
        return 180.0F;
    }

    @Override
    public ResourceLocation getEntityTexture(E2JBaseSpiderEntity entity) {
        String resourceTexture = MessageFormat.format("earthtojavamobs:textures/mobs/spider/{0}/{0}.png", this.registryName);
        String resourceTextureBlink = MessageFormat.format("earthtojavamobs:textures/mobs/spider/{0}/{0}_blink.png", this.registryName);
        ResourceLocation texture = new ResourceLocation(resourceTexture);
        ResourceLocation textureBlink = new ResourceLocation(resourceTextureBlink);
        return entity.getBlinkRemainingTicks() > 0 ? textureBlink : texture;
    }
}
