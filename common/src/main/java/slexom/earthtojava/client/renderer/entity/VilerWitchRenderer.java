package slexom.earthtojava.client.renderer.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import slexom.earthtojava.client.renderer.entity.feature.VilerWitchHeldItemFeatureRenderer;
import slexom.earthtojava.client.renderer.entity.model.VilerWitchModel;
import slexom.earthtojava.entity.monster.VilerWitchEntity;
import slexom.earthtojava.init.EntityModelLayersInit;
import slexom.earthtojava.init.EntityTypesInit;

import java.text.MessageFormat;

@Environment(EnvType.CLIENT)
public class VilerWitchRenderer extends MobEntityRenderer<VilerWitchEntity, VilerWitchModel<VilerWitchEntity>> {

    private final String registryName;

    public VilerWitchRenderer(EntityRendererFactory.Context context) {
        super(context, new VilerWitchModel<>(context.getPart(EntityModelLayersInit.VILER_WITCH_ENTITY_MODEL_LAYER)), 0.5F);
        this.addFeature(new VilerWitchHeldItemFeatureRenderer<>(this));
        this.registryName = EntityTypesInit.VILER_WITCH_REGISTRY_NAME;
    }

    public void render(VilerWitchEntity witchEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        this.model.setLiftingNose(!witchEntity.getMainHandStack().isEmpty());
        super.render(witchEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    public Identifier getTexture(VilerWitchEntity entity) {
        String resourceTexture = MessageFormat.format("earthtojavamobs:textures/mobs/witch/{0}/{0}.png", this.registryName);
        String resourceTextureBlink = MessageFormat.format("earthtojavamobs:textures/mobs/witch/{0}/{0}_blink.png", this.registryName);
        Identifier texture = new Identifier(resourceTexture);
        Identifier textureBlink = new Identifier(resourceTextureBlink);
        return entity.blinkManager.getBlinkRemainingTicks() > 0 ? textureBlink : texture;
    }

    protected void scale(VilerWitchEntity witchEntity, MatrixStack matrixStack, float f) {
        matrixStack.scale(0.9375F, 0.9375F, 0.9375F);
    }
}
