package slexom.earthtojava.client.renderer.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.feature.SlimeOverlayFeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.SlimeEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import slexom.earthtojava.entity.passive.TropicalSlimeEntity;

import java.text.MessageFormat;

@Environment(EnvType.CLIENT)
public class TropicalSlimeRenderer extends MobEntityRenderer<TropicalSlimeEntity, SlimeEntityModel<TropicalSlimeEntity>> {
    private static final int ANIMATION_FRAMES = 48;
    private static final float ANIMATION_TIME = 12.0F;
    private int currentFrame = 0;

    public TropicalSlimeRenderer(EntityRendererFactory.Context context) {
        super(context, new SlimeEntityModel<>(context.getPart(EntityModelLayers.SLIME)), 0.25F);
        this.addFeature(new SlimeOverlayFeatureRenderer<>(this, context.getModelLoader()));
    }

    public void render(TropicalSlimeEntity slimeEntity, float entityYaw, float partialTicks, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int light) {
        this.shadowRadius = 1.0F;
        this.currentFrame = (int) (Math.floor(slimeEntity.age / ANIMATION_TIME) % ANIMATION_FRAMES);
        super.render(slimeEntity, entityYaw, partialTicks, matrixStack, vertexConsumerProvider, light);
    }

    protected void scale(TropicalSlimeEntity slimeEntity, MatrixStack matrices, float amount) {
        matrices.scale(0.999F, 0.999F, 0.999F);
        matrices.translate(0.0D, 0.001D, 0.0D);
        float f1 = 4.0F;
        float f2 = MathHelper.lerp(amount, slimeEntity.prevSquishFactor, slimeEntity.squishFactor) / (f1 * 0.5F + 1.0F);
        float f3 = 1.0F / (f2 + 1.0F);
        matrices.scale(f3 * f1, 1.0F / f3 * f1, f3 * f1);
    }

    public Identifier getTexture(TropicalSlimeEntity entity) {
        String frameLocation = MessageFormat.format("earthtojavamobs:textures/mobs/slime/tropical_slime/tropical_slime_anim_{0}.png", (currentFrame + 1));
        return new Identifier(frameLocation);
    }
}