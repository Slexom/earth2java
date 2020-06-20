package net.slexom.earthtojavamobs.client.renderer.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.slexom.earthtojavamobs.client.renderer.entity.layers.HornedSheepWoolLayer;
import net.slexom.earthtojavamobs.client.renderer.entity.model.HornedSheepModel;
import net.slexom.earthtojavamobs.entity.passive.HornedSheepEntity;

import java.util.Random;

@OnlyIn(Dist.CLIENT)
public class HornedSheepRenderer extends MobRenderer<HornedSheepEntity, HornedSheepModel<HornedSheepEntity>> {

    private int lastBlink = 0;
    private int nextBlinkInterval = new Random().nextInt(760) + 60;
    private int remainingTick = 0;
    private int internalBlinkTick = 0;

    @Override
    public void render(HornedSheepEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
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

    public HornedSheepRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new HornedSheepModel<>(), 0.7F);
        this.addLayer(new HornedSheepWoolLayer(this));
    }

    public ResourceLocation getEntityTexture(HornedSheepEntity entity) {
        ResourceLocation texture = new ResourceLocation("earthtojavamobs:textures/mobs/sheep/horned_sheep/horned_sheep.png");
        ResourceLocation textureBlink = new ResourceLocation("earthtojavamobs:textures/mobs/sheep/horned_sheep/horned_sheep_blink.png");
        return remainingTick > 0 ? textureBlink : texture;
    }
}