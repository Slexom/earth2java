package net.slexom.earthtojavamobs.client.renderer.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.SquidModel;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.slexom.earthtojavamobs.client.renderer.entity.layers.GlowingSquidGlowLayer;
import net.slexom.earthtojavamobs.entity.passive.GlowSquidEntity;

@OnlyIn(Dist.CLIENT)
public class GlowSquidRenderer extends MobRenderer<GlowSquidEntity, SquidModel<GlowSquidEntity>> {
    private static final int ANIMATION_FRAMES = 22;
    private static final float ANIMATION_TIME = 12.0F;
    private int currentFrame = 0;

    public GlowSquidRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new SquidModel<>(), 0.7F);
        this.addLayer(new GlowingSquidGlowLayer<>(this));

    }

    public void render(GlowSquidEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        this.currentFrame = (int) (Math.floor(entityIn.ticksExisted / ANIMATION_TIME) % ANIMATION_FRAMES);
    }

    public ResourceLocation getEntityTexture(GlowSquidEntity entity) {
//        String frameLocation = MessageFormat.format("earthtojavamobs:textures/mobs/squid/glow_squid/glow_squid_anim_{0}.png", (currentFrame + 1));
        ResourceLocation textureFrame = new ResourceLocation("earthtojavamobs:textures/mobs/squid/glow_squid/glow_squid_anim_1.png");
        return textureFrame;
    }

    protected void applyRotations(GlowSquidEntity entityLiving, MatrixStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        float f = MathHelper.lerp(partialTicks, entityLiving.prevSquidPitch, entityLiving.squidPitch);
        float f1 = MathHelper.lerp(partialTicks, entityLiving.prevSquidYaw, entityLiving.squidYaw);
        matrixStackIn.translate(0.0D, 0.5D, 0.0D);
        matrixStackIn.rotate(Vector3f.YP.rotationDegrees(180.0F - rotationYaw));
        matrixStackIn.rotate(Vector3f.XP.rotationDegrees(f));
        matrixStackIn.rotate(Vector3f.YP.rotationDegrees(f1));
        matrixStackIn.translate(0.0D, (double) -1.2F, 0.0D);
    }

    protected float handleRotationFloat(GlowSquidEntity livingBase, float partialTicks) {
        return MathHelper.lerp(partialTicks, livingBase.lastTentacleAngle, livingBase.tentacleAngle);
    }
}