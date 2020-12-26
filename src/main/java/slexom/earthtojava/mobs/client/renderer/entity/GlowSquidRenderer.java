//package slexom.earthtojava.mobs.client.renderer.entity;
//
//import net.fabricmc.api.EnvType;
//import net.fabricmc.api.Environment;
//import net.minecraft.client.render.VertexConsumerProvider;
//import net.minecraft.client.render.entity.EntityRenderDispatcher;
//import net.minecraft.client.render.entity.MobEntityRenderer;
//import net.minecraft.client.render.entity.model.SquidEntityModel;
//import net.minecraft.client.util.math.MatrixStack;
//import net.minecraft.client.util.math.Vector3f;
//import net.minecraft.util.Identifier;
//import net.minecraft.util.math.MathHelper;
//import slexom.earthtojava.mobs.client.renderer.entity.feature.GlowingSquidGlowLayer;
//import slexom.earthtojava.mobs.entity.passive.GlowSquidEntity;
//
//@Environment(EnvType.CLIENT)
//public class GlowSquidRenderer extends MobEntityRenderer<GlowSquidEntity, SquidEntityModel<GlowSquidEntity>> {
//    private static final int ANIMATION_FRAMES = 22;
//    private static final float ANIMATION_TIME = 12.0F;
//    private int currentFrame = 0;
//
//    public GlowSquidRenderer(EntityRenderDispatcher renderManagerIn) {
//        super(renderManagerIn, new SquidEntityModel<>(), 0.7F);
//        this.addFeature(new GlowingSquidGlowLayer<>(this));
//
//    }
//
//    public void render(GlowSquidEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, VertexConsumerProvider bufferIn, int packedLightIn) {
//        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
//        this.currentFrame = (int) (Math.floor(entityIn.age / ANIMATION_TIME) % ANIMATION_FRAMES);
//    }
//
//    public Identifier getTexture(GlowSquidEntity entity) {
////        String frameLocation = MessageFormat.format("earthtojavamobs:textures/mobs/squid/glow_squid/glow_squid_anim_{0}.png", (currentFrame + 1));
//        Identifier textureFrame = new Identifier("earthtojavamobs:textures/mobs/squid/glow_squid/glow_squid_anim_1.png");
//        return textureFrame;
//    }
//
//    protected void setupTransforms(GlowSquidEntity squidEntity, MatrixStack matrixStack, float f, float g, float h) {
//        float i = MathHelper.lerp(h, squidEntity.prevTiltAngle, squidEntity.tiltAngle);
//        float j = MathHelper.lerp(h, squidEntity.prevRollAngle, squidEntity.rollAngle);
//        matrixStack.translate(0.0D, 0.5D, 0.0D);
//        matrixStack.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(180.0F - g));
//        matrixStack.multiply(Vector3f.POSITIVE_X.getDegreesQuaternion(i));
//        matrixStack.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(j));
//        matrixStack.translate(0.0D, -1.2000000476837158D, 0.0D);
//    }
//
//    protected float getAnimationProgress(GlowSquidEntity squidEntity, float f) {
//        return MathHelper.lerp(f, squidEntity.prevTentacleAngle, squidEntity.tentacleAngle);
//    }
//}