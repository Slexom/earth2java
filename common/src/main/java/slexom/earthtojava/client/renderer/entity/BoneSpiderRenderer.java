package slexom.earthtojava.client.renderer.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.SpiderEntityModel;
import net.minecraft.util.Identifier;
import slexom.earthtojava.client.renderer.entity.feature.BoneSpiderEyesFeatureRenderer;
import slexom.earthtojava.entity.monster.BoneSpiderEntity;

import java.text.MessageFormat;

@Environment(EnvType.CLIENT)
public class BoneSpiderRenderer extends MobEntityRenderer<BoneSpiderEntity, SpiderEntityModel<BoneSpiderEntity>> {

    public BoneSpiderRenderer(EntityRendererFactory.Context context) {
        super(context, new SpiderEntityModel<>(context.getPart(EntityModelLayers.SPIDER)), 0.8F);
        this.addFeature(new BoneSpiderEyesFeatureRenderer<>(this));
    }

    protected float getLyingAngle(BoneSpiderEntity spiderEntity) {
        return 180.0F;
    }

    @Override
    public Identifier getTexture(BoneSpiderEntity entity) {
        String resourceTexture = MessageFormat.format("earthtojavamobs:textures/mobs/spider/{0}/{0}.png", "bone_spider");
        String resourceTextureBlink = MessageFormat.format("earthtojavamobs:textures/mobs/spider/{0}/{0}_blink.png", "bone_spider");
        Identifier texture = new Identifier(resourceTexture);
        Identifier textureBlink = new Identifier(resourceTextureBlink);
        return entity.blinkManager.getBlinkRemainingTicks() > 0 ? textureBlink : texture;
    }

}
