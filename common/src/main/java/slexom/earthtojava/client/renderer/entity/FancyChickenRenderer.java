package slexom.earthtojava.client.renderer.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import slexom.earthtojava.client.renderer.entity.model.FancyChickenModel;
import slexom.earthtojava.entity.passive.FancyChickenEntity;
import slexom.earthtojava.init.EntityModelLayersInit;
import slexom.earthtojava.init.EntityTypesInit;

import java.text.MessageFormat;

@Environment(EnvType.CLIENT)
public class FancyChickenRenderer extends MobEntityRenderer<FancyChickenEntity, FancyChickenModel<FancyChickenEntity>> {
    private final String registryName = EntityTypesInit.FANCY_CHICKEN_REGISTRY_NAME;

    public FancyChickenRenderer(EntityRendererFactory.Context context) {
        super(context, new FancyChickenModel<>(context.getPart(EntityModelLayersInit.FANCY_CHICKEN_ENTITY_MODEL_LAYER)), 0.3F);
    }

    protected float getAnimationProgress(FancyChickenEntity chickenEntity, float f) {
        float g = MathHelper.lerp(f, chickenEntity.prevFlapProgress, chickenEntity.flapProgress);
        float h = MathHelper.lerp(f, chickenEntity.prevMaxWingDeviation, chickenEntity.maxWingDeviation);
        return (MathHelper.sin(g) + 1.0F) * h;
    }

    public Identifier getTexture(FancyChickenEntity entity) {
        String resourceTexture = MessageFormat.format("earthtojavamobs:textures/mobs/chicken/{0}/{0}.png", this.registryName);
        String resourceTextureBlink = MessageFormat.format("earthtojavamobs:textures/mobs/chicken/{0}/{0}_blink.png", this.registryName);
        Identifier texture = new Identifier(resourceTexture);
        Identifier textureBlink = new Identifier(resourceTextureBlink);
        return entity.blinkManager.getBlinkRemainingTicks() > 0 ? textureBlink : texture;
    }


}
