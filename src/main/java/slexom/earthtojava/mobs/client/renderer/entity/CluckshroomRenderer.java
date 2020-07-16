package slexom.earthtojava.mobs.client.renderer.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import slexom.earthtojava.mobs.client.renderer.entity.layers.CluckshroomLayer;
import slexom.earthtojava.mobs.client.renderer.entity.model.CluckshroomModel;
import slexom.earthtojava.mobs.entity.passive.CluckshroomEntity;

@Environment(EnvType.CLIENT)
public class CluckshroomRenderer extends MobEntityRenderer<CluckshroomEntity, CluckshroomModel<CluckshroomEntity>> {

    public CluckshroomRenderer(EntityRenderDispatcher renderManagerIn) {
        super(renderManagerIn, new CluckshroomModel<>(), 0.3F);
        this.addFeature(new CluckshroomLayer<>(this));
    }

    protected float getAnimationProgress(CluckshroomEntity chickenEntity, float f) {
        float g = MathHelper.lerp(f, chickenEntity.prevFlapProgress, chickenEntity.flapProgress);
        float h = MathHelper.lerp(f, chickenEntity.prevMaxWingDeviation, chickenEntity.maxWingDeviation);
        return (MathHelper.sin(g) + 1.0F) * h;
    }

    @Override
    public Identifier getTexture(CluckshroomEntity entity) {
        Identifier texture = new Identifier("earthtojavamobs:textures/mobs/chicken/cluck_shroom/cluck_shroom.png");
        Identifier textureBlink = new Identifier("earthtojavamobs:textures/mobs/chicken/cluck_shroom/cluck_shroom_blink.png");
        return entity.getBlinkRemainingTicks() > 0 ? textureBlink : texture;
    }
}
