package slexom.earthtojava.mobs.client.renderer.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.text.MessageFormat;
import java.util.Random;

@OnlyIn(Dist.CLIENT)
public class E2JRenderer<T extends MobEntity> extends MobRenderer<T, EntityModel<T>> {
    private final String entityType;
    private final String registryName;

    private int lastBlink = 0;
    private int nextBlinkInterval = new Random().nextInt(760) + 60;
    private int remainingTick = 0;
    private int internalBlinkTick = 0;

    public E2JRenderer(EntityRendererManager renderManagerIn, EntityModel<T> entityModelIn, float shadowSizeIn, String entityType, String registryName) {
        super(renderManagerIn, entityModelIn, shadowSizeIn);
        this.entityType = entityType;
        this.registryName = registryName;
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
    public ResourceLocation getEntityTexture(T entity) {
        String resourceTexture = MessageFormat.format("earthtojavamobs:textures/mobs/{0}/{1}/{1}.png", this.entityType, this.registryName);
        String resourceTextureBlink = MessageFormat.format("earthtojavamobs:textures/mobs/{0}/{1}/{1}_blink.png", this.entityType, this.registryName);
        ResourceLocation texture = new ResourceLocation(resourceTexture);
        ResourceLocation textureBlink = new ResourceLocation(resourceTextureBlink);
        return remainingTick > 0 ? textureBlink : texture;
    }
}
