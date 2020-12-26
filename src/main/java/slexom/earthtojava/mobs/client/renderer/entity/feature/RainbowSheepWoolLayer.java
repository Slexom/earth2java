//package slexom.earthtojava.mobs.client.renderer.entity.feature;
//
//import net.minecraft.client.render.VertexConsumerProvider;
//import net.minecraft.client.render.entity.feature.FeatureRenderer;
//import net.minecraft.client.render.entity.feature.FeatureRendererContext;
//import net.minecraft.client.util.math.MatrixStack;
//import net.minecraft.util.Identifier;
//import slexom.earthtojava.mobs.client.renderer.entity.model.RainbowSheepModel;
//import slexom.earthtojava.mobs.client.renderer.entity.model.RainbowSheepWoolModel;
//import slexom.earthtojava.mobs.entity.passive.RainbowSheepEntity;
//
//public class RainbowSheepWoolLayer extends FeatureRenderer<RainbowSheepEntity, RainbowSheepModel<RainbowSheepEntity>> {
//    private static final Identifier TEXTURE = new Identifier("earthtojavamobs:textures/mobs/sheep/rainbow_sheep/rainbow_sheep_fur.png");
//    private final RainbowSheepWoolModel<RainbowSheepEntity> sheepModel = new RainbowSheepWoolModel<>();
//
//    public RainbowSheepWoolLayer(FeatureRendererContext<RainbowSheepEntity, RainbowSheepModel<RainbowSheepEntity>> rendererIn) {
//        super(rendererIn);
//    }
//
//    public void render(MatrixStack matrixStackIn, VertexConsumerProvider bufferIn, int packedLightIn, RainbowSheepEntity entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
//        if (!entitylivingbaseIn.isSheared() && !entitylivingbaseIn.isInvisible()) {
//            float f = 0.9019608F;
//            float f1 = 0.9019608F;
//            float f2 = 0.9019608F;
//            render(this.getContextModel(), this.sheepModel, TEXTURE, matrixStackIn, bufferIn, packedLightIn, entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, partialTicks, f, f1, f2);
//        }
//    }
//}