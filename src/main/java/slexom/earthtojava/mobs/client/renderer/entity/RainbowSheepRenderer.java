//package slexom.earthtojava.mobs.client.renderer.entity;
//
//import net.minecraft.client.render.entity.EntityRenderDispatcher;
//import net.minecraft.client.render.entity.MobEntityRenderer;
//import net.minecraft.util.Identifier;
//import slexom.earthtojava.mobs.client.renderer.entity.feature.RainbowSheepWoolLayer;
//import slexom.earthtojava.mobs.client.renderer.entity.model.RainbowSheepModel;
//import slexom.earthtojava.mobs.entity.passive.RainbowSheepEntity;
//
//public class RainbowSheepRenderer extends MobEntityRenderer<RainbowSheepEntity, RainbowSheepModel<RainbowSheepEntity>> {
//    public RainbowSheepRenderer(EntityRenderDispatcher renderManagerIn) {
//        super(renderManagerIn, new RainbowSheepModel<>(), 0.7F);
//        this.addFeature(new RainbowSheepWoolLayer(this));
//    }
//
//    @Override
//    public Identifier getTexture(RainbowSheepEntity entity) {
//        Identifier texture = new Identifier("earthtojavamobs:textures/mobs/sheep/rainbow_sheep/rainbow_sheep.png");
//        Identifier textureBlink = new Identifier("earthtojavamobs:textures/mobs/sheep/rainbow_sheep/rainbow_sheep_blink.png");
//        return entity.getBlinkRemainingTicks() > 0 ? textureBlink : texture;
//    }
//}
