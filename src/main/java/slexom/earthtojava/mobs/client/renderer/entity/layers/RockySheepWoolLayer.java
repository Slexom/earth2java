package slexom.earthtojava.mobs.client.renderer.entity.layers;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import slexom.earthtojava.mobs.client.renderer.entity.model.RockySheepModel;
import slexom.earthtojava.mobs.client.renderer.entity.model.RockySheepWoolModel;
import slexom.earthtojava.mobs.entity.passive.RockySheepEntity;

@OnlyIn(Dist.CLIENT)
public class RockySheepWoolLayer extends LayerRenderer<RockySheepEntity, RockySheepModel<RockySheepEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("earthtojavamobs:textures/mobs/sheep/rocky_sheep/rocky_sheep_fur.png");
    private final RockySheepWoolModel<RockySheepEntity> sheepModel = new RockySheepWoolModel<>();

    public RockySheepWoolLayer(IEntityRenderer<RockySheepEntity, RockySheepModel<RockySheepEntity>> rendererIn) {
        super(rendererIn);
    }

    public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, RockySheepEntity entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (!entitylivingbaseIn.getSheared() && !entitylivingbaseIn.isInvisible()) {
            float f = 0.9019608F;
            float f1 = 0.9019608F;
            float f2 = 0.9019608F;
            renderCopyCutoutModel(this.getEntityModel(), this.sheepModel, TEXTURE, matrixStackIn, bufferIn, packedLightIn, entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, partialTicks, f, f1, f2);
        }
    }
}
