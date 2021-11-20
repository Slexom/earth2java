package slexom.earthtojava.mobs.client.renderer.entity.feature;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.SnowGolemEntityModel;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3f;
import slexom.earthtojava.mobs.entity.passive.MelonGolemEntity;
import slexom.earthtojava.mobs.init.BlockInit;

@Environment(EnvType.CLIENT)
public class MelonGolemCarvedMelonFeatureRenderer extends FeatureRenderer<MelonGolemEntity, SnowGolemEntityModel<MelonGolemEntity>> {
    public MelonGolemCarvedMelonFeatureRenderer(FeatureRendererContext<MelonGolemEntity, SnowGolemEntityModel<MelonGolemEntity>> featureRendererContext) {
        super(featureRendererContext);
    }

    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, MelonGolemEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        if (!entity.isInvisible() && entity.isMelonEquipped()) {
            matrices.push();
            this.getContextModel().getHead().rotate(matrices);
            matrices.translate(0.0D, -0.34375D, 0.0D);
            matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(180.0F));
            matrices.scale(0.625F, -0.625F, -0.625F);
            ItemStack head = new ItemStack(BlockInit.CARVED_MELON);
            ItemStack headBlink = new ItemStack(BlockInit.MELON_GOLEM_HEAD_BLINK);
            ItemStack headShoot = new ItemStack(BlockInit.MELON_GOLEM_HEAD_SHOOT);
            ItemStack itemstack = entity.isShooting() ? headShoot : entity.blinkManager.getBlinkRemainingTicks() > 0 ? headBlink : head;
            MinecraftClient.getInstance().getItemRenderer().renderItem(entity, itemstack, ModelTransformation.Mode.HEAD, false, matrices, vertexConsumers, entity.world, light, LivingEntityRenderer.getOverlay(entity, 0.0F), entity.getId());
            matrices.pop();
        }
    }
}