package slexom.earthtojava.mobs.client.renderer.entity.layers;

import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.RenderType;
import net.minecraft.client.render.entity.FeatureRendererContext;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.model.PigEntityModel;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.util.Identifier;
import slexom.earthtojava.mobs.entity.base.E2JBasePigEntity;

public class E2JBasePigSaddleLayer extends FeatureRenderer<E2JBasePigEntity<? extends PigEntity>, PigEntityModel<E2JBasePigEntity<? extends PigEntity>>> {
    private final Identifier TEXTURE = new Identifier("textures/entity/pig/pig_saddle.png");
    private final PigEntityModel<E2JBasePigEntity<? extends PigEntity>> pigModel = new PigEntityModel<>(0.5F);

    public E2JBasePigSaddleLayer(FeatureRendererContext<E2JBasePigEntity<? extends PigEntity>, PigEntityModel<E2JBasePigEntity<? extends PigEntity>>> p_i50927_1_) {
        super(p_i50927_1_);
    }

    public void render(MatrixStack matrixStackIn, VertexConsumerProvider bufferIn, int packedLightIn, E2JBasePigEntity<? extends PigEntity> entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (entitylivingbaseIn.getSaddled()) {
            this.getContextModel().copyModelAttributesTo(this.pigModel);
            this.pigModel.animateModel(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks);
            this.pigModel.setAngles(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            VertexConsumer ivertexbuilder = bufferIn.getBuffer(RenderType.getEntityCutoutNoCull(TEXTURE));
            this.pigModel.render(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        }
    }
}
