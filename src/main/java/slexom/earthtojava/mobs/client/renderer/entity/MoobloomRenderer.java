//package slexom.earthtojava.mobs.client.renderer.entity;
//
//import net.minecraft.client.render.entity.EntityRenderDispatcher;
//import net.minecraft.client.render.entity.MobEntityRenderer;
//import net.minecraft.client.render.entity.model.CowEntityModel;
//import net.minecraft.util.Identifier;
//import net.fabricmc.api.EnvType;
//import net.fabricmc.api.Environment;
//import slexom.earthtojava.mobs.client.renderer.entity.feature.MoobloomLayer;
//import slexom.earthtojava.mobs.entity.passive.MoobloomEntity;
//
//@Environment(EnvType.CLIENT)
//public class MoobloomRenderer extends MobEntityRenderer<MoobloomEntity, CowEntityModel<MoobloomEntity>> {
//
//    public MoobloomRenderer(EntityRenderDispatcher renderManagerIn) {
//        super(renderManagerIn, new CowEntityModel<>(), 0.7F);
//        this.addFeature(new MoobloomLayer<>(this));
//    }
//
//    public Identifier getTexture(MoobloomEntity entity) {
//        Identifier texture = new Identifier("earthtojavamobs:textures/mobs/cow/moobloom/moobloom.png");
//        Identifier textureBlink = new Identifier("earthtojavamobs:textures/mobs/cow/moobloom/moobloom_blink.png");
//        return entity.getBlinkRemainingTicks() > 0 ? textureBlink : texture;
//    }
//}