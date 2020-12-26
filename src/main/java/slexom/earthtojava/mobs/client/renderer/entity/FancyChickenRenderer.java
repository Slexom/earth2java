//package slexom.earthtojava.mobs.client.renderer.entity;
//
//import net.fabricmc.api.EnvType;
//import net.fabricmc.api.Environment;
//import net.minecraft.client.render.entity.EntityRenderDispatcher;
//import net.minecraft.client.render.entity.MobEntityRenderer;
//import net.minecraft.client.render.entity.model.ChickenEntityModel;
//import net.minecraft.entity.passive.ChickenEntity;
//import net.minecraft.util.Identifier;
//import net.minecraft.util.math.MathHelper;
//import slexom.earthtojava.mobs.client.renderer.entity.model.FancyChickenModel;
//import slexom.earthtojava.mobs.entity.base.E2JBaseChickenEntity;
//import slexom.earthtojava.mobs.init.EntityTypesInit;
//
//import java.text.MessageFormat;
//
//@Environment(EnvType.CLIENT)
//public class FancyChickenRenderer extends MobEntityRenderer<E2JBaseChickenEntity<? extends ChickenEntity>, FancyChickenModel<E2JBaseChickenEntity<? extends ChickenEntity>>> {
//    private final String registryName = EntityTypesInit.FANCY_CHICKEN_REGISTRY_NAME;
//
//    public FancyChickenRenderer(EntityRenderDispatcher entityRenderDispatcher) {
//        super(entityRenderDispatcher, new FancyChickenModel<>(), 0.3F);
//
//    }
//
//    protected float getAnimationProgress(E2JBaseChickenEntity<? extends ChickenEntity> chickenEntity, float f) {
//        float g = MathHelper.lerp(f, chickenEntity.prevFlapProgress, chickenEntity.flapProgress);
//        float h = MathHelper.lerp(f, chickenEntity.prevMaxWingDeviation, chickenEntity.maxWingDeviation);
//        return (MathHelper.sin(g) + 1.0F) * h;
//    }
//
//    public Identifier getTexture(E2JBaseChickenEntity<? extends ChickenEntity> entity) {
//        String resourceTexture = MessageFormat.format("earthtojavamobs:textures/mobs/chicken/{0}/{0}.png", this.registryName);
//        String resourceTextureBlink = MessageFormat.format("earthtojavamobs:textures/mobs/chicken/{0}/{0}_blink.png", this.registryName);
//        Identifier texture = new Identifier(resourceTexture);
//        Identifier textureBlink = new Identifier(resourceTextureBlink);
//        return entity.getBlinkRemainingTicks() > 0 ? textureBlink : texture;
//    }
//
//
//}
