//package slexom.earthtojava.mobs.client.renderer.entity;
//
//import net.fabricmc.api.EnvType;
//import net.fabricmc.api.Environment;
//import net.minecraft.client.render.entity.EntityRenderDispatcher;
//import net.minecraft.client.render.entity.MobEntityRenderer;
//import net.minecraft.client.render.entity.ZombieBaseEntityRenderer;
//import net.minecraft.client.render.entity.model.CowEntityModel;
//import net.minecraft.client.render.entity.model.ZombieEntityModel;
//import net.minecraft.entity.mob.ZombieEntity;
//import net.minecraft.util.Identifier;
//import slexom.earthtojava.mobs.client.renderer.entity.feature.MoobloomLayer;
//import slexom.earthtojava.mobs.client.renderer.entity.model.BoulderingZombieModel;
//import slexom.earthtojava.mobs.entity.monster.BoulderingZombieEntity;
//import slexom.earthtojava.mobs.entity.passive.MoobloomEntity;
//
//@Environment(EnvType.CLIENT)
//public class BoulderingZombieRenderer extends ZombieBaseEntityRenderer<BoulderingZombieEntity, BoulderingZombieModel<BoulderingZombieEntity>> {
//
//    public BoulderingZombieRenderer(EntityRenderDispatcher entityRenderDispatcher) {
//        super(entityRenderDispatcher, new BoulderingZombieModel<>(0.0F, 0.0F, 64, 64), new BoulderingZombieModel<>(0.5F, true), new BoulderingZombieModel<>(1.0F, true));
//    }
//
//    public Identifier getTexture(BoulderingZombieEntity entity) {
//        Identifier texture = new Identifier("earthtojavamobs:textures/mobs/zombie/bouldering_zombie/bouldering_zombie.png");
//        Identifier textureBlink = new Identifier("earthtojavamobs:textures/mobs/zombie/bouldering_zombie/bouldering_zombie_blink.png");
//        return entity.getBlinkRemainingTicks() > 0 ? textureBlink : texture;
//    }
//}