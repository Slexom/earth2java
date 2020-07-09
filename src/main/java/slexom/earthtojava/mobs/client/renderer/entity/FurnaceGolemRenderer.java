package slexom.earthtojava.mobs.client.renderer.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import slexom.earthtojava.mobs.client.renderer.entity.layers.FurnaceGolemFlamesLayer;
import slexom.earthtojava.mobs.client.renderer.entity.model.FurnaceGolemModel;
import slexom.earthtojava.mobs.entity.passive.FurnaceGolemEntity;

@OnlyIn(Dist.CLIENT)
public class FurnaceGolemRenderer extends MobRenderer<FurnaceGolemEntity, FurnaceGolemModel<FurnaceGolemEntity>> {

    public FurnaceGolemRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new FurnaceGolemModel<>(), 0.7F);
        this.addLayer(new FurnaceGolemFlamesLayer(this));
    }

    protected void applyRotations(FurnaceGolemEntity entityLiving, MatrixStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
        if (!((double) entityLiving.limbSwingAmount < 0.01D)) {
            float f = 13.0F;
            float f1 = entityLiving.limbSwing - entityLiving.limbSwingAmount * (1.0F - partialTicks) + 6.0F;
            float f2 = (Math.abs(f1 % 13.0F - 6.5F) - 3.25F) / 3.25F;
            matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(6.5F * f2));
        }
    }

    public ResourceLocation getEntityTexture(FurnaceGolemEntity entity) {
        ResourceLocation texture = new ResourceLocation("earthtojavamobs:textures/mobs/iron_golem/furnace_golem/furnace_golem.png");
        ResourceLocation textureBlink = new ResourceLocation("earthtojavamobs:textures/mobs/iron_golem/furnace_golem/furnace_golem_blink.png");
        ResourceLocation textureAngry = new ResourceLocation("earthtojavamobs:textures/mobs/iron_golem/furnace_golem/furnace_golem_angry.png");
        return entity.isAngry() ? textureAngry : entity.getBlinkRemainingTicks() > 0 ? textureBlink : texture;
    }
}
