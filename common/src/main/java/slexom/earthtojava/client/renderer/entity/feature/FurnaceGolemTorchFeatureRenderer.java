package slexom.earthtojava.client.renderer.entity.feature;


import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.IronGolemEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Vec3f;
import slexom.earthtojava.entity.passive.FurnaceGolemEntity;

@Environment(EnvType.CLIENT)
public class FurnaceGolemTorchFeatureRenderer extends FeatureRenderer<FurnaceGolemEntity, IronGolemEntityModel<FurnaceGolemEntity>> {

    public FurnaceGolemTorchFeatureRenderer(FeatureRendererContext<FurnaceGolemEntity, IronGolemEntityModel<FurnaceGolemEntity>> featureRendererContext) {
        super(featureRendererContext);
    }

    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, FurnaceGolemEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        if (entity.getLookingAtVillagerTicks() != 0) {
            matrices.push();
            ModelPart modelPart = this.getContextModel().getRightArm();
            modelPart.rotate(matrices);
            matrices.translate(-1.1875D, 1.0625D, -0.9375D);
            matrices.translate(0.5D, 0.5D, 0.5D);
            matrices.scale(0.5F, 0.5F, 0.5F);
            matrices.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(-90.0F));
            matrices.translate(-0.5D, -0.5D, -0.5D);
            MinecraftClient.getInstance().getBlockRenderManager().renderBlockAsEntity(Blocks.TORCH.getDefaultState(), matrices, vertexConsumers, light, OverlayTexture.DEFAULT_UV);
            matrices.pop();
        }
    }

}
