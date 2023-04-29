package slexom.earthtojava.client.renderer.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.IronGolemEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;
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

    protected void setupTransforms(FurnaceGolemEntity furnaceGolemEntity, MatrixStack matrices, float animationProgress, float bodyYaw, float tickDelta) {
        super.setupTransforms(furnaceGolemEntity, matrices, animationProgress, bodyYaw, tickDelta);
        if ((double) furnaceGolemEntity.limbAnimator.getSpeed() < 0.01) {
            return;
        }

        float f1 = furnaceGolemEntity.limbAnimator.getPos(tickDelta) + 6.0F;
        float f2 = (Math.abs(f1 % 13.0F - 6.5F) - 3.25F) / 3.25F;
        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(6.5F * f2));
    }

    public Identifier getTexture(FurnaceGolemEntity entity) {
        Identifier texture = new Identifier("earthtojavamobs:textures/mobs/iron_golem/furnace_golem/furnace_golem.png");
        Identifier textureBlink = new Identifier("earthtojavamobs:textures/mobs/iron_golem/furnace_golem/furnace_golem_blink.png");
        Identifier textureAngry = new Identifier("earthtojavamobs:textures/mobs/iron_golem/furnace_golem/furnace_golem_angry.png");
        if (entity.isAngry()) {
            return textureAngry;
        } else {
            if (entity.blinkManager.getBlinkRemainingTicks() > 0) return textureBlink;
            return texture;
        }
    }

}
