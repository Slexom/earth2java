package slexom.earthtojava.mobs.client.renderer.entity;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.SpiderEntityModel;
import net.minecraft.client.render.entity.model.SpiderModel;
import net.minecraft.entity.mob.SpiderEntity;
import net.minecraft.entity.monster.SpiderEntity;
import net.minecraft.util.Identifier;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import slexom.earthtojava.mobs.entity.base.E2JBaseSpiderEntity;

import java.text.MessageFormat;

@Environment(EnvType.CLIENT)
public class E2JSpiderRenderer extends MobEntityRenderer<E2JBaseSpiderEntity<? extends SpiderEntity>, SpiderEntityModel<E2JBaseSpiderEntity<? extends SpiderEntity>>> {

    private final String registryName;

    public E2JSpiderRenderer(EntityRenderDispatcher renderManagerIn, String registryName) {
        super(renderManagerIn, new SpiderEntityModel<>(), 0.8F);
        this.registryName = registryName;
    }

    protected float getDeathMaxRotation(E2JBaseSpiderEntity entityLivingBaseIn) {
        return 180.0F;
    }

    @Override
    public Identifier getTexture(E2JBaseSpiderEntity entity) {
        String resourceTexture = MessageFormat.format("earthtojavamobs:textures/mobs/spider/{0}/{0}.png", this.registryName);
        String resourceTextureBlink = MessageFormat.format("earthtojavamobs:textures/mobs/spider/{0}/{0}_blink.png", this.registryName);
        Identifier texture = new Identifier(resourceTexture);
        Identifier textureBlink = new Identifier(resourceTextureBlink);
        return entity.getBlinkRemainingTicks() > 0 ? textureBlink : texture;
    }
}
