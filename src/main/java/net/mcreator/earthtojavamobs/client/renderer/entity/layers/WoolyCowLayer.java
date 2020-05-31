package net.mcreator.earthtojavamobs.client.renderer.entity.layers;


import com.mojang.blaze3d.matrix.MatrixStack;
import net.mcreator.earthtojavamobs.entity.WoolyCowEntity;
import net.mcreator.earthtojavamobs.client.renderer.entity.model.WoolyCowWoolModel;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.CowModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WoolyCowLayer extends LayerRenderer<WoolyCowEntity.CustomEntity, CowModel<WoolyCowEntity.CustomEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("earthtojavamobs:textures/texture_wooly_cow.png");
    private final WoolyCowWoolModel<WoolyCowEntity.CustomEntity> woolyCowModel = new WoolyCowWoolModel<>();

    public WoolyCowLayer(IEntityRenderer<WoolyCowEntity.CustomEntity, CowModel<WoolyCowEntity.CustomEntity>> rendererIn) {
        super(rendererIn);
    }

    public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, WoolyCowEntity.CustomEntity entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (!entitylivingbaseIn.getSheared() && !entitylivingbaseIn.isInvisible()) {
            float f = 0.9019608F;
            float f1 = 0.9019608F;
            float f2 = 0.9019608F;
            renderCopyCutoutModel(this.getEntityModel(), this.woolyCowModel, TEXTURE, matrixStackIn, bufferIn, packedLightIn, entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, partialTicks, f, f1, f2);
        }
    }
}