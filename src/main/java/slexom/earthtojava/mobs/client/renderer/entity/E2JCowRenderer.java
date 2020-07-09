package slexom.earthtojava.mobs.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.CowModel;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import slexom.earthtojava.mobs.entity.base.E2JBaseCowEntity;

import java.text.MessageFormat;

@OnlyIn(Dist.CLIENT)
public class E2JCowRenderer extends MobRenderer<E2JBaseCowEntity<? extends CowEntity>, CowModel<E2JBaseCowEntity<? extends CowEntity>>> {

    private final String registryName;

    public E2JCowRenderer(EntityRendererManager renderManagerIn, String registryName) {
        super(renderManagerIn, new CowModel<>(), 0.7F);
        this.registryName = registryName;
    }

    @Override
    public ResourceLocation getEntityTexture(E2JBaseCowEntity<? extends CowEntity> entity) {
        String resourceTexture = MessageFormat.format("earthtojavamobs:textures/mobs/cow/{0}/{0}.png", this.registryName);
        String resourceTextureBlink = MessageFormat.format("earthtojavamobs:textures/mobs/cow/{0}/{0}_blink.png", this.registryName);
        ResourceLocation texture = new ResourceLocation(resourceTexture);
        ResourceLocation textureBlink = new ResourceLocation(resourceTextureBlink);
        return entity.getBlinkRemainingTicks() > 0 ? textureBlink : texture;
    }
}
