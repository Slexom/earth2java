package slexom.earthtojava.mobs.client.renderer.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.util.Identifier;
import slexom.earthtojava.mobs.client.renderer.entity.feature.FurnaceGolemFlamesLayer;
import slexom.earthtojava.mobs.client.renderer.entity.feature.FurnaceGolemTorchFeatureRenderer;
import slexom.earthtojava.mobs.client.renderer.entity.model.FurnaceGolemModel;
import slexom.earthtojava.mobs.entity.passive.FurnaceGolemEntity;

@Environment(EnvType.CLIENT)
public class FurnaceGolemRenderer extends MobEntityRenderer<FurnaceGolemEntity, FurnaceGolemModel<FurnaceGolemEntity>> {

    public FurnaceGolemRenderer(EntityRenderDispatcher renderManagerIn) {
        super(renderManagerIn, new FurnaceGolemModel<>(), 0.7F);
        this.addFeature(new FurnaceGolemFlamesLayer(this));
        this.addFeature(new FurnaceGolemTorchFeatureRenderer(this));
    }

    protected void setupTransforms(FurnaceGolemEntity entityLiving, MatrixStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        super.setupTransforms(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
        if (!((double) entityLiving.limbDistance < 0.01D)) {
            float f = 13.0F;
            float f1 = entityLiving.limbAngle - entityLiving.limbDistance * (1.0F - partialTicks) + 6.0F;
            float f2 = (Math.abs(f1 % 13.0F - 6.5F) - 3.25F) / 3.25F;
            matrixStackIn.multiply(Vector3f.POSITIVE_Z.getDegreesQuaternion(6.5F * f2));
        }
    }

    public Identifier getTexture(FurnaceGolemEntity entity) {
        Identifier texture = new Identifier("earthtojavamobs:textures/mobs/iron_golem/furnace_golem/furnace_golem.png");
        Identifier textureBlink = new Identifier("earthtojavamobs:textures/mobs/iron_golem/furnace_golem/furnace_golem_blink.png");
        Identifier textureAngry = new Identifier("earthtojavamobs:textures/mobs/iron_golem/furnace_golem/furnace_golem_angry.png");
        return entity.isAngry() ? textureAngry : entity.getBlinkRemainingTicks() > 0 ? textureBlink : texture;
    }
}
