package slexom.earthtojava.client.renderer.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.feature.SaddleFeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.PigEntityModel;
import net.minecraft.util.Identifier;
import slexom.earthtojava.client.renderer.entity.model.MuddyPigModel;
import slexom.earthtojava.entity.passive.MuddyPigEntity;
import slexom.earthtojava.init.EntityModelLayersInit;

@Environment(EnvType.CLIENT)
public class MuddyPigRenderer extends MobEntityRenderer<MuddyPigEntity, MuddyPigModel<MuddyPigEntity>> {

    public MuddyPigRenderer(EntityRendererFactory.Context context) {
        super(context, new MuddyPigModel<>(context.getPart(EntityModelLayersInit.MUDDY_PIG_ENTITY_MODEL_LAYER)), 0.7F);
        this.addFeature(new SaddleFeatureRenderer(this, new PigEntityModel(context.getPart(EntityModelLayers.PIG_SADDLE)), new Identifier("textures/entity/pig/pig_saddle.png")));
    }

    public Identifier getTexture(MuddyPigEntity entity) {
        Identifier texture = new Identifier("earthtojavamobs:textures/mobs/pig/muddy_pig/muddy_pig.png");
        Identifier textureBlink = new Identifier("earthtojavamobs:textures/mobs/pig/muddy_pig/muddy_pig_blink.png");
        Identifier textureDried = new Identifier("earthtojavamobs:textures/mobs/pig/muddy_pig/muddy_pig_dried.png");
        Identifier textureDriedBlink = new Identifier("earthtojavamobs:textures/mobs/pig/muddy_pig/muddy_pig_dried_blink.png");
        boolean blink = entity.blinkManager.getBlinkRemainingTicks() > 0;
        return entity.isInMuddyState() ?
                blink ?
                        textureBlink :
                        texture :
                blink ?
                        textureDriedBlink :
                        textureDried;
    }

//    public static class SaddleLayer extends FeatureRenderer<MuddyPigEntity, MuddyPigModel<MuddyPigEntity>> {
//        private final Identifier TEXTURE = new Identifier("textures/entity/pig/pig_saddle.png");
//        private final PigEntityModel<MuddyPigEntity> pigModel = new PigEntityModel<>(0.5F);
//
//        public SaddleLayer(FeatureRendererContext<MuddyPigEntity, MuddyPigModel<MuddyPigEntity>> p_i50927_1_) {
//            super(p_i50927_1_);
//        }
//
//        public void render(MatrixStack matrixStackIn, VertexConsumerProvider bufferIn, int packedLightIn, MuddyPigEntity entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
//            if (entitylivingbaseIn.isSaddled()) {
//                this.getContextModel().copyStateTo(this.pigModel);
//                this.pigModel.animateModel(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks);
//                this.pigModel.setAngles(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
//                VertexConsumer ivertexbuilder = bufferIn.getBuffer(RenderLayer.getEntityCutoutNoCull(TEXTURE));
//                this.pigModel.render(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
//            }
//        }
//    }

}
