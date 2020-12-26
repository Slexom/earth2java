//package slexom.earthtojava.mobs.client.renderer.entity.model;
//
//import net.minecraft.client.render.entity.model.QuadrupedEntityModel;
//import net.minecraft.client.model.ModelPart;
//import net.fabricmc.api.EnvType;
//import net.fabricmc.api.Environment;
//import slexom.earthtojava.mobs.entity.passive.HornedSheepEntity;
//
//@Environment(EnvType.CLIENT)
//public class HornedSheepModel<T extends HornedSheepEntity> extends QuadrupedEntityModel<T> {
//    private float headRotationAngleX;
//
//
//    public HornedSheepModel() {
//        super(12, 0.0F, false, 8.0F, 4.0F, 2.0F, 2.0F, 24);
////        this.textureWidth = 64;
////        this.textureHeight = 64;
//
//
//        this.head = new ModelPart(this, 0, 0).setTextureSize(64, 64);
//        this.head.addCuboid(-3.0F, -4.0F, -6.0F, 6.0F, 6.0F, 8.0F, 0.0F);
//        this.head.setPivot(0.0F, 6.0F, -8.0F);
//        ///////
//
//        //this.head.setTextureOffset(0, 32).addCuboid(6.0F, 0.0F, 0.0F, 4.0F, 8.0F, 6.0F);
//        float hornX = -7.0F;
//        float hornY = -5.0F;
//        float hornZ = -4.0F;
//        this.head
//                .setTextureOffset(0, 32)
//                .addCuboid(hornX, hornY, hornZ, 4.0F, 7.0F, 6.0F)
//                .setTextureOffset(20, 32)
//                .addCuboid(hornX, hornY + 4.0F, hornZ - 3.0F, 4.0F, 3.0F, 3.0F);
//
//        this.head
//                .setTextureOffset(0, 32)
//                .addCuboid(hornX + 10.0F, hornY, hornZ, 4.0F, 7.0F, 6.0F, true)
//                .setTextureOffset(20, 32)
//                .addCuboid(hornX + 10.0F, hornY + 4.0F, hornZ - 3.0F, 4.0F, 3.0F, 3.0F, true);
//
//        ///////
//        this.torso = new ModelPart(this, 28, 8).setTextureSize(64, 64);
//        this.torso.addCuboid(-4.0F, -10.0F, -7.0F, 8.0F, 16.0F, 6.0F, 0.0F);
//        this.torso.setPivot(0.0F, 5.0F, 2.0F);
//
//
//        this.backRightLeg = new ModelPart(this, 0, 16).setTextureSize(64, 64);
//        this.backRightLeg.addCuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F);
//        this.backRightLeg.setPivot(-3.0F, 24 - 12.0F, 7.0F);
//        this.backLeftLeg = new ModelPart(this, 0, 16).setTextureSize(64, 64);
//        this.backLeftLeg.addCuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F);
//        this.backLeftLeg.setPivot(3.0F, (24 - 12.0F), 7.0F);
//        this.frontRightLeg = new ModelPart(this, 0, 16).setTextureSize(64, 64);
//        this.frontRightLeg.addCuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F);
//        this.frontRightLeg.setPivot(-3.0F, (24 - 12.0F), -5.0F);
//        this.frontLeftLeg = new ModelPart(this, 0, 16).setTextureSize(64, 64);
//        this.frontLeftLeg.addCuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F);
//        this.frontLeftLeg.setPivot(3.0F, (24 - 12.0F), -5.0F);
//
//
//    }
//
//    public void animateModel(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
//        super.animateModel(entityIn, limbSwing, limbSwingAmount, partialTick);
//        this.head.pivotY= 6.0F + entityIn.getNeckAngle(partialTick) * 9.0F;
//        this.headRotationAngleX = entityIn.getHeadAngle(partialTick);
//    }
//
//    public void setAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
//        super.setAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
//        this.head.pitch = this.headRotationAngleX;
//    }
//
//
//}