package slexom.earthtojava.mobs.client.renderer.entity.layers;

import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.FeatureRendererContext;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.util.Identifier;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import slexom.earthtojava.mobs.client.renderer.entity.model.FurnaceGolemModel;
import slexom.earthtojava.mobs.entity.passive.FurnaceGolemEntity;

import java.text.MessageFormat;

@Environment(EnvType.CLIENT)
public class FurnaceGolemFlamesLayer extends FeatureRenderer<FurnaceGolemEntity, FurnaceGolemModel<FurnaceGolemEntity>> {

    private static final int ANIMATION_FRAMES = 6;
    private static final float ANIMATION_TIME = 6.0F;
    private int currentFrame = 0;
    private final FurnaceGolemModel<FurnaceGolemEntity> furnaceGolemModel = new FurnaceGolemModel<>();

    public FurnaceGolemFlamesLayer(FeatureRendererContext<FurnaceGolemEntity, FurnaceGolemModel<FurnaceGolemEntity>> entityRenderer) {
        super(entityRenderer);
    }

    public void render(MatrixStack matrixStackIn, VertexConsumerProvider bufferIn, int packedLightIn, FurnaceGolemEntity entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (!entitylivingbaseIn.isInvisible() && entitylivingbaseIn.isAngry()) {
            this.currentFrame = (int) (Math.floor(entitylivingbaseIn.ticksExisted / ANIMATION_TIME) % ANIMATION_FRAMES);
            String frameLocation = MessageFormat.format("earthtojavamobs:textures/mobs/iron_golem/furnace_golem/furnace_golem_flames_layer_anim_{0}.png", (this.currentFrame + 1));
            Identifier resourcelocation = new Identifier(frameLocation);
            renderCutoutModel(this.getContextModel(), resourcelocation, matrixStackIn, bufferIn, packedLightIn, entitylivingbaseIn, 1.0F, 1.0F, 1.0F);

            // renderCopyCutoutModel(this.getContextModel(), this.furnaceGolemModel, resourcelocation, matrixStackIn, bufferIn, packedLightIn, entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, partialTicks, 1.0F, 1.0F, 1.0F);
        }
    }
}