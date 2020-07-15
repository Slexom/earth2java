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

    protected float handleRotationFloat(CluckshroomEntity livingBase, float partialTicks) {
        float f = MathHelper.lerp(partialTicks, livingBase.oFlap, livingBase.wingRotation);
        float f1 = MathHelper.lerp(partialTicks, livingBase.oFlapSpeed, livingBase.destPos);
        return (MathHelper.sin(f) + 1.0F) * f1;
    }

    @Override
    public Identifier getTexture(CluckshroomEntity entity) {
        Identifier texture = new Identifier("earthtojavamobs:textures/mobs/chicken/cluck_shroom/cluck_shroom.png");
        Identifier textureBlink = new Identifier("earthtojavamobs:textures/mobs/chicken/cluck_shroom/cluck_shroom_blink.png");
        return entity.getBlinkRemainingTicks() > 0 ? textureBlink : texture;
    }
}
