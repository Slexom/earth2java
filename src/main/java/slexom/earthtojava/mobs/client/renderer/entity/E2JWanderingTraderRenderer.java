//package slexom.earthtojava.mobs.client.renderer.entity;
//
//import net.fabricmc.api.EnvType;
//import net.fabricmc.api.Environment;
//import net.minecraft.client.render.entity.EntityRenderDispatcher;
//import net.minecraft.client.render.entity.MobEntityRenderer;
//import net.minecraft.client.render.entity.feature.HeadFeatureRenderer;
//import net.minecraft.client.render.entity.feature.VillagerHeldItemFeatureRenderer;
//import net.minecraft.client.render.entity.model.VillagerResemblingModel;
//import net.minecraft.client.util.math.MatrixStack;
//import net.minecraft.util.Identifier;
//import slexom.earthtojava.mobs.entity.merchant.villager.E2JWanderingTraderEntity;
//
//@Environment(EnvType.CLIENT)
//public class E2JWanderingTraderRenderer extends MobEntityRenderer<E2JWanderingTraderEntity, VillagerResemblingModel<E2JWanderingTraderEntity>> {
//    private static final Identifier texture = new Identifier("earthtojavamobs:textures/entity/villager/wandering_trader/wandering_trader.png");
//
//    public E2JWanderingTraderRenderer(EntityRenderDispatcher renderManagerIn) {
//        super(renderManagerIn, new VillagerResemblingModel<>(0.0F), 0.5F);
//        this.addFeature(new HeadFeatureRenderer<>(this));
//        this.addFeature(new VillagerHeldItemFeatureRenderer<>(this));
//    }
//
//    public Identifier getTexture(E2JWanderingTraderEntity entity) {
//        return texture;
//    }
//
//    protected void scale(E2JWanderingTraderEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
//        matrixStackIn.scale(0.9375F, 0.9375F, 0.9375F);
//    }
//}