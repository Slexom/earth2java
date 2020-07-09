package slexom.earthtojava.mobs.client.renderer.entity.layers;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.PigModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.util.ResourceLocation;
import slexom.earthtojava.mobs.entity.base.E2JBasePigEntity;

public class E2JBasePigSaddleLayer extends LayerRenderer<E2JBasePigEntity<? extends PigEntity>, PigModel<E2JBasePigEntity<? extends PigEntity>>> {
    private final ResourceLocation TEXTURE = new ResourceLocation("textures/entity/pig/pig_saddle.png");
    private final PigModel<E2JBasePigEntity<? extends PigEntity>> pigModel = new PigModel<>(0.5F);

    public E2JBasePigSaddleLayer(IEntityRenderer<E2JBasePigEntity<? extends PigEntity>, PigModel<E2JBasePigEntity<? extends PigEntity>>> p_i50927_1_) {
        super(p_i50927_1_);
    }

    public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, E2JBasePigEntity<? extends PigEntity> entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (entitylivingbaseIn.isHorseSaddled()) {
            this.getEntityModel().copyModelAttributesTo(this.pigModel);
            this.pigModel.setLivingAnimations(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks);
            this.pigModel.setRotationAngles(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            IVertexBuilder ivertexbuilder = bufferIn.getBuffer(RenderType.getEntityCutoutNoCull(TEXTURE));
            this.pigModel.render(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        }
    }
}
