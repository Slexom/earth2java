package net.slexom.earthtojavamobs.client.renderer.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.SpiderModel;
import net.minecraft.entity.monster.SpiderEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.text.MessageFormat;
import java.util.Random;

@OnlyIn(Dist.CLIENT)
public class E2JSpiderRenderer<T extends SpiderEntity> extends MobRenderer<T, SpiderModel<T>> {

    private final String registryName;
    private int lastBlink = 0;
    private int nextBlinkInterval = new Random().nextInt(760) + 60;
    private int remainingTick = 0;
    private int internalBlinkTick = 0;

    public E2JSpiderRenderer(EntityRendererManager renderManagerIn, String registryName) {
        super(renderManagerIn, new SpiderModel<>(), 0.8F);
        this.registryName = registryName;
    }

    protected float getDeathMaxRotation(T entityLivingBaseIn) {
        return 180.0F;
    }

    @Override
    public void render(T entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
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

    @Override
    public ResourceLocation getEntityTexture(SpiderEntity entity) {
        String resourceTexture = MessageFormat.format("earthtojavamobs:textures/mobs/spider/{0}/{0}.png", this.registryName);
        String resourceTextureBlink = MessageFormat.format("earthtojavamobs:textures/mobs/spider/{0}/{0}_blink.png", this.registryName);
        ResourceLocation texture = new ResourceLocation(resourceTexture);
        ResourceLocation textureBlink = new ResourceLocation(resourceTextureBlink);
        return remainingTick > 0 ? textureBlink : texture;
    }
}
