package slexom.earthtojava.mobs.client.renderer.entity.layers;

import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.RenderType;
import net.minecraft.client.render.entity.FeatureRendererContext;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class GlowingSquidGlowLayer<T extends Entity, M extends EntityModel<T>> extends FeatureRenderer<T, M> {
    public GlowingSquidGlowLayer(FeatureRendererContext<T, M> er) {
        super(er);
    }

    public void render(MatrixStack matrixStackIn, VertexConsumerProvider bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing,
                       float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        VertexConsumer ivertexbuilder = bufferIn
                .getBuffer(RenderType.getEyes(new Identifier("earthtojavamobs:textures/mobs/squid/glow_squid/glow_squid_glow_layer.png")));
        this.getContextModel().render(matrixStackIn, ivertexbuilder, 15728640, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
    }
}