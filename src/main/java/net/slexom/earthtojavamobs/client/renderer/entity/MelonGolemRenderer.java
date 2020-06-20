package net.slexom.earthtojavamobs.client.renderer.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.slexom.earthtojavamobs.client.renderer.entity.model.MelonGolemModel;
import net.slexom.earthtojavamobs.entity.passive.MelonGolemEntity;

import java.util.Random;

@OnlyIn(Dist.CLIENT)
public class MelonGolemRenderer extends MobRenderer<MelonGolemEntity, MelonGolemModel<MelonGolemEntity>> {
    private static final ResourceLocation SNOW_MAN_TEXTURES = new ResourceLocation("textures/entity/snow_golem.png");

    private int lastBlink = 0;
    private int nextBlinkInterval = new Random().nextInt(760) + 60;
    private int remainingTick = 0;
    private int internalBlinkTick = 0;

    public MelonGolemRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new MelonGolemModel<>(), 0.5F);
    }

    @Override
    public void render(MelonGolemEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
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

    public ResourceLocation getEntityTexture(MelonGolemEntity entity) {
        ResourceLocation texture = new ResourceLocation("earthtojavamobs:textures/mobs/snow_golem/melon_golem/melon_golem.png");
        ResourceLocation textureBlink = new ResourceLocation("earthtojavamobs:textures/mobs/snow_golem/melon_golem/melon_golem_blink.png");
        // private static final ResourceLocation textureShoot = new ResourceLocation("earthtojavamobs:textures/mobs/snow_golem/melon_golem/melon_golem_blink.png");
        return remainingTick > 0 ? textureBlink : texture;
    }
}