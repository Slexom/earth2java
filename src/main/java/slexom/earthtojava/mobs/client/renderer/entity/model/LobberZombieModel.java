//package slexom.earthtojava.mobs.client.renderer.entity.model;
//
//import net.minecraft.client.model.ModelPart;
//import net.minecraft.client.render.entity.model.ZombieEntityModel;
//import slexom.earthtojava.mobs.entity.monster.LobberZombieEntity;
//
//public class LobberZombieModel<T extends LobberZombieEntity> extends ZombieEntityModel<T> {
//
//    public LobberZombieModel(float scale, boolean bl) {
//        super(scale, 0.0F, 64, bl ? 32 : 64);
//    }
//
//    public LobberZombieModel(float scale, float pivotY, int textureWidth, int textureHeight) {
//        super(scale, pivotY, textureWidth, textureHeight);
//        this.rightArm = new ModelPart(this, 16, 32);
//        this.rightArm.addCuboid(-3.0F, -2.0F, -2.0F, 4.0F, 11.0F, 4.0F, scale);
//        this.rightArm.setPivot(-5.0F, 2.0F + pivotY, 0.0F);
//        this.leftArm = new ModelPart(this, 32, 32);
//        this.leftArm.mirror = true;
//        this.leftArm.addCuboid(-1.0F, -2.0F, -2.0F, 5.0F, 14.0F, 4.0F, scale);
//        this.leftArm.setPivot(5.0F, 2.0F + pivotY, 0.0F);
//        this.rightLeg = new ModelPart(this, 0, 16);
//        this.rightLeg.addCuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, scale);
//        this.rightLeg.setPivot(-1.9F, 12.0F + pivotY, 0.0F);
//        this.leftLeg = new ModelPart(this, 0, 34);
//        this.leftLeg.mirror = true;
//        this.leftLeg.addCuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, scale);
//        this.leftLeg.setPivot(1.9F, 12.0F + pivotY, 0.0F);
//    }
//
//    @Override
//    public void animateModel(T livingEntity, float f, float g, float h) {
//        this.rightArmPose = ArmPose.EMPTY;
//        this.leftArmPose = ArmPose.EMPTY;
//        super.animateModel(livingEntity, f, g, h);
//    }
//
//}
