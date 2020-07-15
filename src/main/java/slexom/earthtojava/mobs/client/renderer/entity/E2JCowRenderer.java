package slexom.earthtojava.mobs.client.renderer.entity;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.CowEntityModel;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.util.Identifier;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import slexom.earthtojava.mobs.entity.base.E2JBaseCowEntity;

import java.text.MessageFormat;

@Environment(EnvType.CLIENT)
public class E2JCowRenderer extends MobEntityRenderer<E2JBaseCowEntity<? extends CowEntity>, CowEntityModel<E2JBaseCowEntity<? extends CowEntity>>> {

    private final String registryName;

    public E2JCowRenderer(EntityRenderDispatcher renderManagerIn, String registryName) {
        super(renderManagerIn, new CowEntityModel<>(), 0.7F);
        this.registryName = registryName;
    }

    @Override
    public Identifier getTexture(E2JBaseCowEntity<? extends CowEntity> entity) {
        String resourceTexture = MessageFormat.format("earthtojavamobs:textures/mobs/cow/{0}/{0}.png", this.registryName);
        String resourceTextureBlink = MessageFormat.format("earthtojavamobs:textures/mobs/cow/{0}/{0}_blink.png", this.registryName);
        Identifier texture = new Identifier(resourceTexture);
        Identifier textureBlink = new Identifier(resourceTextureBlink);
        return entity.getBlinkRemainingTicks() > 0 ? textureBlink : texture;
    }
}
