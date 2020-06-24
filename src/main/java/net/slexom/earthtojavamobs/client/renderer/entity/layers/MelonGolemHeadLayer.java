package net.slexom.earthtojavamobs.client.renderer.entity.layers;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.SnowManModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.slexom.earthtojavamobs.entity.passive.MelonGolemEntity;
import net.slexom.earthtojavamobs.init.BlockInit;

@OnlyIn(Dist.CLIENT)
public class MelonGolemHeadLayer extends LayerRenderer<MelonGolemEntity, SnowManModel<MelonGolemEntity>> {
    public MelonGolemHeadLayer(IEntityRenderer<MelonGolemEntity, SnowManModel<MelonGolemEntity>> p_i50922_1_) {
        super(p_i50922_1_);
    }

    public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, MelonGolemEntity entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (!entitylivingbaseIn.isInvisible() && entitylivingbaseIn.isMelonEquipped()) {
            matrixStackIn.push();
            this.getEntityModel().func_205070_a().translateRotate(matrixStackIn);
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