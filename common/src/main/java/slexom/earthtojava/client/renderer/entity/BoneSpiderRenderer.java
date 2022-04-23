package slexom.earthtojava.client.renderer.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.feature.EyesFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.SpiderEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.SpiderEntity;
import net.minecraft.util.Identifier;
import slexom.earthtojava.entity.base.E2JBaseSpiderEntity;

@Environment(EnvType.CLIENT)
public class BoneSpiderRenderer extends E2JSpiderRenderer {
    public BoneSpiderRenderer(EntityRendererFactory.Context context) {
        super(context, "bone_spider");
        this.addFeature(new BoneSpiderEyesLayer<>(this));
    }

    @Environment(EnvType.CLIENT)
    static class BoneSpiderEyesLayer<T extends E2JBaseSpiderEntity<? extends SpiderEntity>, M extends SpiderEntityModel<T>> extends EyesFeatureRenderer<T, M> {
        private static final RenderLayer RENDER_TYPE = RenderLayer.getEyes(new Identifier("earthtojavamobs:textures/mobs/spider/bone_spider/bone_spider_eyes.png"));

        public BoneSpiderEyesLayer(FeatureRendererContext<T, M> featureRendererContext) {
            super(featureRendererContext);
        }

        public void render(MatrixStack matrixStackIn, VertexConsumerProvider bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
            float alpha = entitylivingbaseIn.blinkManager.getBlinkRemainingTicks() == 0 ? 1.0F : 0.0F;
            VertexConsumer ivertexbuilder = bufferIn.getBuffer(this.getEyesTexture());
            this.getContextModel().render(matrixStackIn, ivertexbuilder, 15728640, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, alpha);
        }

        public RenderLayer getEyesTexture() {
            return RENDER_TYPE;
        }

    }

}
