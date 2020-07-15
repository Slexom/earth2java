package slexom.earthtojava.mobs.client.renderer.entity;

import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.RenderType;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.FeatureRendererContext;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.model.PigEntityModel;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.util.Identifier;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import slexom.earthtojava.mobs.client.renderer.entity.model.MuddyPigEntityModel;
import slexom.earthtojava.mobs.entity.passive.MuddyPigEntity;

@Environment(EnvType.CLIENT)
public class MuddyPigRenderer extends MobEntityRenderer<MuddyPigEntity, MuddyPigEntityModel<MuddyPigEntity>> {

    public MuddyPigRenderer(EntityRenderDispatcher renderManagerIn) {
        super(renderManagerIn, new MuddyPigEntityModel<>(), 0.7F);
        this.addFeature(new SaddleLayer(this));
    }

    public Identifier getTexture(MuddyPigEntity entity) {
        Identifier texture = new Identifier("earthtojavamobs:textures/mobs/pig/muddy_pig/muddy_pig.png");
        Identifier textureBlink = new Identifier("earthtojavamobs:textures/mobs/pig/muddy_pig/muddy_pig_blink.png");
        Identifier textureDried = new Identifier("earthtojavamobs:textures/mobs/pig/muddy_pig/muddy_pig_dried.png");
        Identifier textureDriedBlink = new Identifier("earthtojavamobs:textures/mobs/pig/muddy_pig/muddy_pig_dried_blink.png");
        boolean blink = entity.getBlinkRemainingTicks() > 0;
        return entity.isInMuddyState() ?
                blink ?
                        textureBlink :
                        texture :
                blink ?
                        textureDriedBlink :
                        textureDried;
    }

    public static class SaddleLayer extends FeatureRenderer<MuddyPigEntity, MuddyPigEntityModel<MuddyPigEntity>> {
        private final Identifier TEXTURE = new Identifier("textures/entity/pig/pig_saddle.png");
        private final PigEntityModel<MuddyPigEntity> pigModel = new PigEntityModel<>(0.5F);

        public SaddleLayer(FeatureRendererContext<MuddyPigEntity, MuddyPigEntityModel<MuddyPigEntity>> p_i50927_1_) {
            super(p_i50927_1_);
        }

        public void render(MatrixStack matrixStackIn, VertexConsumerProvider bufferIn, int packedLightIn, MuddyPigEntity entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
            if (entitylivingbaseIn.getSaddled()) {
                this.getContextModel().copyModelAttributesTo(this.pigModel);
                this.pigModel.animateModel(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks);
                this.pigModel.setAngles(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
                VertexConsumer ivertexbuilder = bufferIn.getBuffer(RenderType.getEntityCutoutNoCull(TEXTURE));
                this.pigModel.render(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
            }
        }
    }

}
