package net.slexom.earthtojavamobs.client.renderer.entity.layers;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.slexom.earthtojavamobs.client.renderer.entity.model.FleckedSheepModel;
import net.slexom.earthtojavamobs.client.renderer.entity.model.FleckedSheepWoolModel;
import net.slexom.earthtojavamobs.entity.FleckedSheepEntity;

public class FleckedSheepWoolLayer extends LayerRenderer<FleckedSheepEntity.CustomEntity, FleckedSheepModel<FleckedSheepEntity.CustomEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("earthtojavamobs:textures/mobs/sheep/flecked_sheep/flecked_sheep_fur.png");
    private final FleckedSheepWoolModel<FleckedSheepEntity.CustomEntity> sheepModel = new FleckedSheepWoolModel<>();

    public FleckedSheepWoolLayer(IEntityRenderer<FleckedSheepEntity.CustomEntity, FleckedSheepModel<FleckedSheepEntity.CustomEntity>> rendererIn) {
        super(rendererIn);
    }

    public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, FleckedSheepEntity.CustomEntity entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (!entitylivingbaseIn.getSheared() && !entitylivingbaseIn.isInvisible()) {
            float f = 0.9019608F;
            float f1 = 0.9019608F;
            float f2 = 0.9019608F;
            renderCopyCutoutModel(this.getEntityModel(), this.sheepModel, TEXTURE, matrixStackIn, bufferIn, packedLightIn, entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, partialTicks, f, f1, f2);
        }
    }
}