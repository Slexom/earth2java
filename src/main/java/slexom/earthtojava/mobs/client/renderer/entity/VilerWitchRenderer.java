package slexom.earthtojava.mobs.client.renderer.entity;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.feature.WitchHeldItemFeatureRenderer;
import net.minecraft.client.render.entity.model.WitchEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.WitchEntity;
import net.minecraft.util.Identifier;
import slexom.earthtojava.mobs.client.renderer.entity.feature.VilerWitchHeldItemFeatureRenderer;
import slexom.earthtojava.mobs.client.renderer.entity.model.VilerWitchModel;
import slexom.earthtojava.mobs.entity.monster.VilerWitchEntity;
import slexom.earthtojava.mobs.init.EntityTypesInit;

import java.text.MessageFormat;

public class VilerWitchRenderer extends MobEntityRenderer<VilerWitchEntity, VilerWitchModel<VilerWitchEntity>> {

    private final String registryName;

    public VilerWitchRenderer(EntityRenderDispatcher entityRenderDispatcher) {
        super(entityRenderDispatcher, new VilerWitchModel<>(0.0F), 0.5F);
        this.addFeature(new VilerWitchHeldItemFeatureRenderer<>(this));
        this.registryName = EntityTypesInit.VILER_WITCH_REGISTRY_NAME;
    }

    public void render(VilerWitchEntity witchEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        this.model.setLiftingNose(!witchEntity.getMainHandStack().isEmpty());
        super.render( witchEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    public Identifier getTexture(VilerWitchEntity entity) {
        String resourceTexture = MessageFormat.format("earthtojavamobs:textures/mobs/witch/{0}/{0}.png", this.registryName);
        String resourceTextureBlink = MessageFormat.format("earthtojavamobs:textures/mobs/witch/{0}/{0}_blink.png", this.registryName);
        Identifier texture = new Identifier(resourceTexture);
        Identifier textureBlink = new Identifier(resourceTextureBlink);
        return entity.getBlinkRemainingTicks() > 0 ? textureBlink : texture;
    }

    protected void scale(VilerWitchEntity witchEntity, MatrixStack matrixStack, float f) {
        float g = 0.9375F;
        matrixStack.scale(0.9375F, 0.9375F, 0.9375F);
    }
}
