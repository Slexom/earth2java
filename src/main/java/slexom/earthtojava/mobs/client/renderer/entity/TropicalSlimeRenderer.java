package slexom.earthtojava.mobs.client.renderer.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.feature.SlimeOverlayFeatureRenderer;
import net.minecraft.client.render.entity.model.SlimeEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import slexom.earthtojava.mobs.entity.passive.TropicalSlimeEntity;

import java.text.MessageFormat;

@Environment(EnvType.CLIENT)
public class TropicalSlimeRenderer extends MobEntityRenderer<TropicalSlimeEntity, SlimeEntityModel<TropicalSlimeEntity>> {
    private static final int ANIMATION_FRAMES = 48;
    private static final float ANIMATION_TIME = 12.0F;
    private int currentFrame = 0;

    public TropicalSlimeRenderer(EntityRenderDispatcher renderManagerIn) {
        super(renderManagerIn, new SlimeEntityModel<>(16), 0.25F);
        this.addFeature(new SlimeOverlayFeatureRenderer<>(this));
    }

    public void render(TropicalSlimeEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, VertexConsumerProvider bufferIn, int packedLightIn) {
        this.shadowRadius = 1.0F;
        this.currentFrame = (int) (Math.floor(entityIn.age / ANIMATION_TIME) % ANIMATION_FRAMES);
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    protected void scale(TropicalSlimeEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        matrixStackIn.scale(0.999F, 0.999F, 0.999F);
        matrixStackIn.translate(0.0D, (double) 0.001F, 0.0D);
        float f1 = 4.0F;
        float f2 = MathHelper.lerp(partialTickTime, entitylivingbaseIn.prevSquishFactor, entitylivingbaseIn.squishFactor) / (f1 * 0.5F + 1.0F);
        float f3 = 1.0F / (f2 + 1.0F);
        matrixStackIn.scale(f3 * f1, 1.0F / f3 * f1, f3 * f1);
    }

    public Identifier getTexture(TropicalSlimeEntity entity) {
        String frameLocation = MessageFormat.format("earthtojavamobs:textures/mobs/slime/tropical_slime/tropical_slime_anim_{0}.png", (currentFrame + 1));
        return new Identifier(frameLocation);
    }
}