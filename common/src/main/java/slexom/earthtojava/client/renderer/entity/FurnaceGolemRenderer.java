package slexom.earthtojava.client.renderer.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.IronGolemEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3f;
import slexom.earthtojava.client.renderer.entity.feature.FurnaceGolemFlamesFeatureRenderer;
import slexom.earthtojava.client.renderer.entity.feature.FurnaceGolemTorchFeatureRenderer;
import slexom.earthtojava.entity.passive.FurnaceGolemEntity;

@Environment(EnvType.CLIENT)
public class FurnaceGolemRenderer extends MobEntityRenderer<FurnaceGolemEntity, IronGolemEntityModel<FurnaceGolemEntity>> {

    public FurnaceGolemRenderer(EntityRendererFactory.Context context) {
        super(context, new IronGolemEntityModel<>(context.getPart(EntityModelLayers.IRON_GOLEM)), 0.7F);
        this.addFeature(new FurnaceGolemFlamesFeatureRenderer(this));
        this.addFeature(new FurnaceGolemTorchFeatureRenderer(this));
    }

    protected void setupTransforms(FurnaceGolemEntity entityLiving, MatrixStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        super.setupTransforms(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
        if (!((double) entityLiving.limbDistance < 0.01D)) {
            float f1 = entityLiving.limbAngle - entityLiving.limbDistance * (1.0F - partialTicks) + 6.0F;
            float f2 = (Math.abs(f1 % 13.0F - 6.5F) - 3.25F) / 3.25F;
            matrixStackIn.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(6.5F * f2));
        }
    }

    public Identifier getTexture(FurnaceGolemEntity entity) {
        Identifier texture = new Identifier("earthtojavamobs:textures/mobs/iron_golem/furnace_golem/furnace_golem.png");
        Identifier textureBlink = new Identifier("earthtojavamobs:textures/mobs/iron_golem/furnace_golem/furnace_golem_blink.png");
        Identifier textureAngry = new Identifier("earthtojavamobs:textures/mobs/iron_golem/furnace_golem/furnace_golem_angry.png");
        return entity.isAngry() ? textureAngry : entity.blinkManager.getBlinkRemainingTicks() > 0 ? textureBlink : texture;
    }

}
