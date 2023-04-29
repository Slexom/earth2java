package slexom.earthtojava.client.renderer.entity.feature;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.feature.VillagerHeldItemFeatureRenderer;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import net.minecraft.util.math.RotationAxis;
import slexom.earthtojava.client.renderer.entity.model.VilerWitchModel;
import slexom.earthtojava.entity.monster.VilerWitchEntity;

@Environment(EnvType.CLIENT)
public class VilerWitchHeldItemFeatureRenderer<T extends VilerWitchEntity> extends VillagerHeldItemFeatureRenderer<T, VilerWitchModel<T>> {
    public VilerWitchHeldItemFeatureRenderer(FeatureRendererContext<T, VilerWitchModel<T>> featureRendererContext, HeldItemRenderer heldItemRenderer) {
        super(featureRendererContext, heldItemRenderer);
    }

    @Override
    public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, T livingEntity, float f, float g, float h, float j, float k, float l) {
        ItemStack itemStack = livingEntity.getMainHandStack();
        matrixStack.push();
        if (itemStack.isOf(Items.POTION)) {
            this.getContextModel().getHead().rotate(matrixStack);
            this.getContextModel().getNose().rotate(matrixStack);
            matrixStack.translate(0.0625f, 0.25f, 0.0f);
            matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(180.0f));
            matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(140.0f));
            matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(10.0f));
            matrixStack.translate(0.0f, -0.4f, 0.4f);
        }
        super.render(matrixStack, vertexConsumerProvider, i, livingEntity, f, g, h, j, k, l);
        matrixStack.pop();
    }
}