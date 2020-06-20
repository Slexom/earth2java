package net.slexom.earthtojavamobs.client.renderer.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.slexom.earthtojavamobs.client.renderer.entity.layers.FurnaceGolemFlamesLayer;
import net.slexom.earthtojavamobs.client.renderer.entity.model.FurnaceGolemModel;
import net.slexom.earthtojavamobs.entity.passive.FurnaceGolemEntity;

import java.util.Random;

@OnlyIn(Dist.CLIENT)
public class FurnaceGolemRenderer extends MobRenderer<FurnaceGolemEntity, FurnaceGolemModel<FurnaceGolemEntity>> {

    private int lastBlink = 0;
    private int nextBlinkInterval = new Random().nextInt(760) + 60;
    private int remainingTick = 0;
    private int internalBlinkTick = 0;

    public FurnaceGolemRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new FurnaceGolemModel<>(), 0.7F);
        this.addLayer(new FurnaceGolemFlamesLayer(this));
    }

    @Override
    public void render(FurnaceGolemEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        if (remainingTick > 0) {
            --remainingTick;
        }
        if (internalBlinkTick == (lastBlink + nextBlinkInterval)) {
            lastBlink = internalBlinkTick;
            nextBlinkInterval = new Random().nextInt(740) + 60;
            remainingTick = 4;
        }
        ++internalBlinkTick;
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
        int blinkTime = 150;
        return entity.isAngry() ? textureAngry : remainingTick > 0 ? textureBlink : texture;
    }
}
