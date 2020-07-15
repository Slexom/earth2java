package slexom.earthtojava.mobs.client.renderer.entity;

import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.layers.CrossedArmsItemLayer;
import net.minecraft.client.render.entity.layers.HeadLayer;
import net.minecraft.client.render.entity.model.VillagerModel;
import net.minecraft.util.Identifier;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import slexom.earthtojava.mobs.entity.merchant.villager.E2JWanderingTraderEntity;

@Environment(EnvType.CLIENT)
public class E2JWanderingTraderRenderer extends MobEntityRenderer<E2JWanderingTraderEntity, VillagerModel<E2JWanderingTraderEntity>> {
    private static final Identifier field_217780_a = new Identifier("earthtojavamobs:textures/entity/villager/wandering_trader/wandering_trader.png");

    public E2JWanderingTraderRenderer(EntityRenderDispatcher renderManagerIn) {
        super(renderManagerIn, new VillagerModel<>(0.0F), 0.5F);
        this.addFeature(new HeadLayer<>(this));
        this.addFeature(new CrossedArmsItemLayer<>(this));
    }

    public Identifier getTexture(E2JWanderingTraderEntity entity) {
        return field_217780_a;
    }

    protected void preRenderCallback(E2JWanderingTraderEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        matrixStackIn.scale(0.9375F, 0.9375F, 0.9375F);
    }
}