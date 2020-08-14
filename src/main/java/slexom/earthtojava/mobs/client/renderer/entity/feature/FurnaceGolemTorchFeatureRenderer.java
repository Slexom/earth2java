package slexom.earthtojava.mobs.client.renderer.entity.feature;


import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import slexom.earthtojava.mobs.client.renderer.entity.model.FurnaceGolemModel;
import slexom.earthtojava.mobs.entity.passive.FurnaceGolemEntity;

@Environment(EnvType.CLIENT)
public class FurnaceGolemTorchFeatureRenderer extends FeatureRenderer<FurnaceGolemEntity, FurnaceGolemModel<FurnaceGolemEntity>> {

    public FurnaceGolemTorchFeatureRenderer(FeatureRendererContext<FurnaceGolemEntity, FurnaceGolemModel<FurnaceGolemEntity>> featureRendererContext) {
        super(featureRendererContext);
    }

    public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, FurnaceGolemEntity furnaceGolemEntity, float f, float g, float h, float j, float k, float l) {
        if (furnaceGolemEntity.getLookingAtVillagerTicks() != 0) {
            matrixStack.push();
            ModelPart modelPart = this.getContextModel().getRightArm();
            modelPart.rotate(matrixStack);
            matrixStack.translate(-1.1875D, 1.0625D, -0.9375D);
            matrixStack.translate(0.5D, 0.5D, 0.5D);
            float m = 0.5F;
            matrixStack.scale(0.5F, 0.5F, 0.5F);
            matrixStack.multiply(Vector3f.POSITIVE_X.getDegreesQuaternion(-90.0F));
            matrixStack.translate(-0.5D, -0.5D, -0.5D);
            MinecraftClient.getInstance().getBlockRenderManager().renderBlockAsEntity(Blocks.TORCH.getDefaultState(), matrixStack, vertexConsumerProvider, i, OverlayTexture.DEFAULT_UV);
            matrixStack.pop();
        }
    }

}
