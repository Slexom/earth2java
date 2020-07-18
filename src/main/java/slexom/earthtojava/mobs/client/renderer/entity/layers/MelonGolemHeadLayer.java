package slexom.earthtojava.mobs.client.renderer.entity.layers;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.SnowmanEntityModel;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.item.ItemStack;
import slexom.earthtojava.mobs.entity.passive.MelonGolemEntity;
import slexom.earthtojava.mobs.init.BlockInit;

@Environment(EnvType.CLIENT)
public class MelonGolemHeadLayer extends FeatureRenderer<MelonGolemEntity, SnowmanEntityModel<MelonGolemEntity>> {
    public MelonGolemHeadLayer(FeatureRendererContext<MelonGolemEntity, SnowmanEntityModel<MelonGolemEntity>> p_i50922_1_) {
        super(p_i50922_1_);
    }

    public void render(MatrixStack matrixStackIn, VertexConsumerProvider bufferIn, int packedLightIn, MelonGolemEntity entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (!entitylivingbaseIn.isInvisible() && entitylivingbaseIn.isMelonEquipped()) {
            matrixStackIn.push();
            this.getContextModel().getTopSnowball().rotate(matrixStackIn);
            matrixStackIn.translate(0.0D, -0.34375D, 0.0D);
            matrixStackIn.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(180.0F));
            matrixStackIn.scale(0.625F, -0.625F, -0.625F);
            ItemStack head = new ItemStack(BlockInit.CARVED_MELON);
            ItemStack headBlink = new ItemStack(BlockInit.MELON_GOLEM_HEAD_BLINK);
            ItemStack headShoot = new ItemStack(BlockInit.MELON_GOLEM_HEAD_SHOOT);
            ItemStack itemstack = entitylivingbaseIn.isShooting() ? headShoot : entitylivingbaseIn.getBlinkRemainingTicks() > 0 ? headBlink : head;
            MinecraftClient.getInstance().getItemRenderer().renderItem(entitylivingbaseIn, itemstack, ModelTransformation.Mode.HEAD, false, matrixStackIn, bufferIn, entitylivingbaseIn.world, packedLightIn, LivingEntityRenderer.getOverlay(entitylivingbaseIn, 0.0F));
            matrixStackIn.pop();
        }
    }
}