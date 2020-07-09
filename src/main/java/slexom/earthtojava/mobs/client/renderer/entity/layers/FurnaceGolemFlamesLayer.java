package slexom.earthtojava.mobs.client.renderer.entity.layers;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import slexom.earthtojava.mobs.client.renderer.entity.model.FurnaceGolemModel;
import slexom.earthtojava.mobs.entity.passive.FurnaceGolemEntity;

import java.text.MessageFormat;

@OnlyIn(Dist.CLIENT)
public class FurnaceGolemFlamesLayer extends LayerRenderer<FurnaceGolemEntity, FurnaceGolemModel<FurnaceGolemEntity>> {

    private static final int ANIMATION_FRAMES = 6;
    private static final float ANIMATION_TIME = 6.0F;
    private int currentFrame = 0;
    private final FurnaceGolemModel<FurnaceGolemEntity> furnaceGolemModel = new FurnaceGolemModel<>();

    public FurnaceGolemFlamesLayer(IEntityRenderer<FurnaceGolemEntity, FurnaceGolemModel<FurnaceGolemEntity>> entityRenderer) {
        super(entityRenderer);
    }

    public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, FurnaceGolemEntity entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (!entitylivingbaseIn.isInvisible() && entitylivingbaseIn.isAngry()) {
            this.currentFrame = (int) (Math.floor(entitylivingbaseIn.ticksExisted / ANIMATION_TIME) % ANIMATION_FRAMES);
            String frameLocation = MessageFormat.format("earthtojavamobs:textures/mobs/iron_golem/furnace_golem/furnace_golem_flames_layer_anim_{0}.png", (this.currentFrame + 1));
            ResourceLocation resourcelocation = new ResourceLocation(frameLocation);
            renderCutoutModel(this.getEntityModel(), resourcelocation, matrixStackIn, bufferIn, packedLightIn, entitylivingbaseIn, 1.0F, 1.0F, 1.0F);

            // renderCopyCutoutModel(this.getEntityModel(), this.furnaceGolemModel, resourcelocation, matrixStackIn, bufferIn, packedLightIn, entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, partialTicks, 1.0F, 1.0F, 1.0F);
        }
    }
}