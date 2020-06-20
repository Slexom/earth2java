package net.slexom.earthtojavamobs.client.renderer.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.slexom.earthtojavamobs.client.renderer.entity.layers.InkySheepWoolLayer;
import net.slexom.earthtojavamobs.client.renderer.entity.model.InkySheepModel;
import net.slexom.earthtojavamobs.entity.passive.InkySheepEntity;

import java.util.Random;

@OnlyIn(Dist.CLIENT)
public class InkySheepRenderer extends MobRenderer<InkySheepEntity, InkySheepModel<InkySheepEntity>> {

    private int lastBlink = 0;
    private int nextBlinkInterval = new Random().nextInt(760) + 60;
    private int remainingTick = 0;
    private int internalBlinkTick = 0;

    public InkySheepRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new InkySheepModel<>(), 0.7F);
        this.addLayer(new InkySheepWoolLayer(this));
    }

    @Override
    public void render(InkySheepEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
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

    public ResourceLocation getEntityTexture(InkySheepEntity entity) {
        ResourceLocation texture = new ResourceLocation("earthtojavamobs:textures/mobs/sheep/inky_sheep/inky_sheep.png");
        ResourceLocation textureBlink = new ResourceLocation("earthtojavamobs:textures/mobs/sheep/inky_sheep/inky_sheep_blink.png");
        return remainingTick > 0 ? textureBlink : texture;
    }

}