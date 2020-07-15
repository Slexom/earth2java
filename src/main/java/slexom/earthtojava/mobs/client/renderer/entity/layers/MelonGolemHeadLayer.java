package slexom.earthtojava.mobs.client.renderer.entity.layers;

import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.Vector3f;
import net.minecraft.client.render.entity.FeatureRendererContext;
import net.minecraft.client.render.entity.LivingRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.model.SnowManModel;
import net.minecraft.client.render.model.ItemCameraTransforms;
import net.minecraft.item.ItemStack;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import slexom.earthtojava.mobs.entity.passive.MelonGolemEntity;
import slexom.earthtojava.mobs.init.BlockInit;

@Environment(EnvType.CLIENT)
public class MelonGolemHeadLayer extends FeatureRenderer<MelonGolemEntity, SnowManModel<MelonGolemEntity>> {
    public MelonGolemHeadLayer(FeatureRendererContext<MelonGolemEntity, SnowManModel<MelonGolemEntity>> p_i50922_1_) {
        super(p_i50922_1_);
    }

    public void render(MatrixStack matrixStackIn, VertexConsumerProvider bufferIn, int packedLightIn, MelonGolemEntity entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (!entitylivingbaseIn.isInvisible() && entitylivingbaseIn.isMelonEquipped()) {
            matrixStackIn.push();
            this.getContextModel().func_205070_a().translateRotate(matrixStackIn);
            matrixStackIn.translate(0.0D, -0.34375D, 0.0D);
            matrixStackIn.rotate(Vector3f.YP.rotationDegrees(180.0F));
            matrixStackIn.scale(0.625F, -0.625F, -0.625F);
            ItemStack head = new ItemStack(BlockInit.CARVED_MELON.get());
            ItemStack headBlink = new ItemStack(BlockInit.MELON_GOLEM_HEAD_BLINK.get());
            ItemStack headShoot = new ItemStack(BlockInit.MELON_GOLEM_HEAD_SHOOT.get());
            ItemStack itemstack = entitylivingbaseIn.isShooting() ? headShoot : entitylivingbaseIn.getBlinkRemainingTicks() > 0 ? headBlink : head;
            Minecraft.getInstance().getItemRenderer().renderItem(entitylivingbaseIn, itemstack, ItemCameraTransforms.TransformType.HEAD, false, matrixStackIn, bufferIn, entitylivingbaseIn.world, packedLightIn, LivingRenderer.getPackedOverlay(entitylivingbaseIn, 0.0F));
            matrixStackIn.pop();
        }
    }
}