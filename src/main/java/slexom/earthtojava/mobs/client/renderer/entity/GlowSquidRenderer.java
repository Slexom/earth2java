package slexom.earthtojava.mobs.client.renderer.entity;

import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.Vector3f;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.SquidEntityModel;
import net.minecraft.client.render.entity.model.SquidModel;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import slexom.earthtojava.mobs.client.renderer.entity.layers.GlowingSquidGlowLayer;
import slexom.earthtojava.mobs.entity.passive.GlowSquidEntity;

@Environment(EnvType.CLIENT)
public class GlowSquidRenderer extends MobEntityRenderer<GlowSquidEntity, SquidEntityModel<GlowSquidEntity>> {
    private static final int ANIMATION_FRAMES = 22;
    private static final float ANIMATION_TIME = 12.0F;
    private int currentFrame = 0;

    public GlowSquidRenderer(EntityRenderDispatcher renderManagerIn) {
        super(renderManagerIn, new SquidEntityModel<>(), 0.7F);
        this.addFeature(new GlowingSquidGlowLayer<>(this));

    }

    public void render(GlowSquidEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, VertexConsumerProvider bufferIn, int packedLightIn) {
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        this.currentFrame = (int) (Math.floor(entityIn.ticksExisted / ANIMATION_TIME) % ANIMATION_FRAMES);
    }

    public Identifier getTexture(GlowSquidEntity entity) {
//        String frameLocation = MessageFormat.format("earthtojavamobs:textures/mobs/squid/glow_squid/glow_squid_anim_{0}.png", (currentFrame + 1));
        Identifier textureFrame = new Identifier("earthtojavamobs:textures/mobs/squid/glow_squid/glow_squid_anim_1.png");
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