package slexom.earthtojava.client.renderer.entity.feature;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class GlowingSquidGlowLayer<T extends Entity, M extends EntityModel<T>> extends FeatureRenderer<T, M> {
    public GlowingSquidGlowLayer(FeatureRendererContext<T, M> featureRendererContext) {
        super(featureRendererContext);
    }

    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int packedLightIn, T entitylivingbaseIn, float limbSwing,
                       float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        VertexConsumer ivertexbuilder = vertexConsumers
                .getBuffer(RenderLayer.getEyes(new Identifier("earthtojavamobs:textures/mobs/squid/glow_squid/glow_squid_glow_layer.png")));
        this.getContextModel().render(matrices, ivertexbuilder, 15728640, OverlayTexture.DEFAULT_UV, 1, 1, 1, 1);
    }
}