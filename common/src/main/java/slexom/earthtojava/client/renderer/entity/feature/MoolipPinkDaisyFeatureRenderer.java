package slexom.earthtojava.client.renderer.entity.feature;


import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.CowEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Vec3f;
import slexom.earthtojava.entity.passive.MoolipEntity;
import slexom.earthtojava.init.BlockInit;

@Environment(EnvType.CLIENT)
public class MoolipPinkDaisyFeatureRenderer<T extends MoolipEntity> extends FeatureRenderer<T, CowEntityModel<T>> {

    public MoolipPinkDaisyFeatureRenderer(FeatureRendererContext<T, CowEntityModel<T>> featureRendererContext) {
        super(featureRendererContext);
    }

    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, T entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        if (!entity.isBaby() && !entity.isInvisible()) {
            BlockRenderManager blockRenderManager = MinecraftClient.getInstance().getBlockRenderManager();
            BlockState blockstate = BlockInit.PINK_DAISY.get().getDefaultState();
            int i = LivingEntityRenderer.getOverlay(entity, 0.0F);
            matrices.push();
            matrices.translate(0.2D, -0.2D, 0.5D);
            matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(-48.0F));
            matrices.scale(-0.66F, -0.66F, 0.66F);
            matrices.translate(-0.5D, -0.5D, -0.5D);
            blockRenderManager.renderBlockAsEntity(blockstate, matrices, vertexConsumers, light, i);
            matrices.pop();
            matrices.push();
            matrices.translate(-0.2D, -0.2D, 0.4D);
            matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(-48.0F));
            matrices.scale(-0.66F, -0.66F, 0.66F);
            matrices.translate(-0.5D, -0.5D, -0.5D);
            blockRenderManager.renderBlockAsEntity(blockstate, matrices, vertexConsumers, light, i);
            matrices.pop();
            matrices.push();
            matrices.translate(-0.15D, -0.2D, -0.3D);
            matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(-48.0F));
            matrices.scale(-0.66F, -0.66F, 0.66F);
            matrices.translate(-0.5D, -0.5D, -0.5D);
            blockRenderManager.renderBlockAsEntity(blockstate, matrices, vertexConsumers, light, i);
            matrices.pop();
            matrices.push();
            matrices.translate(0.15D, -0.2D, -0.2D);
            matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(-48.0F));
            matrices.scale(-0.66F, -0.66F, 0.66F);
            matrices.translate(-0.5D, -0.5D, -0.5D);
            blockRenderManager.renderBlockAsEntity(blockstate, matrices, vertexConsumers, light, i);
            matrices.pop();
            matrices.push();
            this.getContextModel().getHead().rotate(matrices);
            matrices.translate(0.1D, -0.5D, -0.2D);
            matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(-78.0F));
            matrices.scale(-0.66F, -0.66F, 0.66F);
            matrices.translate(-0.5D, -0.5D, -0.5D);
            blockRenderManager.renderBlockAsEntity(blockstate, matrices, vertexConsumers, light, i);
            matrices.pop();
        }
    }


}