package slexom.earthtojava.mobs.client.renderer.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.AbstractEyesLayer;
import net.minecraft.client.renderer.entity.model.SpiderModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.monster.SpiderEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import slexom.earthtojava.mobs.entity.base.E2JBaseSpiderEntity;

@OnlyIn(Dist.CLIENT)
public class BoneSpiderRenderer extends E2JSpiderRenderer {
    public BoneSpiderRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, "bone_spider");
        this.addLayer(new BoneSpiderEyesLayer<>(this));
    }

    @OnlyIn(Dist.CLIENT)
    static class BoneSpiderEyesLayer<T extends E2JBaseSpiderEntity<? extends SpiderEntity>, M extends SpiderModel<T>> extends AbstractEyesLayer<T, M> {
        private static final RenderType RENDER_TYPE = RenderType.getEyes(new ResourceLocation("earthtojavamobs:textures/mobs/spider/bone_spider/bone_spider_eyes.png"));

        public BoneSpiderEyesLayer(IEntityRenderer<T, M> rendererIn) {
            super(rendererIn);
        }

        public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
            float alpha = entitylivingbaseIn.getBlinkRemainingTicks() == 0 ? 1.0F : 0.0F;
            IVertexBuilder ivertexbuilder = bufferIn.getBuffer(this.getRenderType());
            this.getEntityModel().render(matrixStackIn, ivertexbuilder, 15728640, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, alpha);

        }


        public RenderType getRenderType() {
            return RENDER_TYPE;
        }
    }
}
