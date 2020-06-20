package net.slexom.earthtojavamobs.client.renderer.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.CowModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.slexom.earthtojavamobs.client.renderer.entity.layers.MoobloomLayer;
import net.slexom.earthtojavamobs.entity.passive.MoobloomEntity;

import java.util.Random;

@OnlyIn(Dist.CLIENT)
public class MoobloomRenderer extends MobRenderer<MoobloomEntity, CowModel<MoobloomEntity>> {
    private int lastBlink = 0;
    private int nextBlinkInterval = new Random().nextInt(760) + 60;
    private int remainingTick = 0;
    private int internalBlinkTick = 0;

    public MoobloomRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new CowModel<>(), 0.7F);
        this.addLayer(new MoobloomLayer<>(this));
    }

    @Override
    public void render(MoobloomEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
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

    public ResourceLocation getEntityTexture(MoobloomEntity entity) {
        ResourceLocation texture = new ResourceLocation("earthtojavamobs:textures/mobs/cow/moobloom/moobloom.png");
        ResourceLocation textureBlink = new ResourceLocation("earthtojavamobs:textures/mobs/cow/moobloom/moobloom_blink.png");
        return remainingTick > 0 ? textureBlink : texture;
    }
}