package slexom.earthtojava.client.renderer.entity.model;

import com.google.common.collect.ImmutableList;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;
import slexom.earthtojava.entity.passive.JumboRabbitEntity;

@Environment(EnvType.CLIENT)
public class JumboRabbitModel<T extends JumboRabbitEntity> extends EntityModel<T> {

    private final ModelPart leftHindLeg;
    private final ModelPart rightHindLeg;
    private final ModelPart leftHaunch;
    private final ModelPart rightHaunch;
    private final ModelPart body;
    private final ModelPart leftFrontLeg;
    private final ModelPart rightFrontLeg;
    private final ModelPart head;
    private final ModelPart rightEar;
    private final ModelPart leftEar;
    private final ModelPart tail;
    private final ModelPart nose;
    private float jumpProgress;

    public JumboRabbitModel(ModelPart root) {
        this.leftHindLeg = root.getChild("left_hind_foot");
        this.rightHindLeg = root.getChild("right_hind_foot");
        this.leftHaunch = root.getChild("left_haunch");
        this.rightHaunch = root.getChild("right_haunch");
        this.body = root.getChild("body");
        this.leftFrontLeg = root.getChild("left_front_leg");
        this.rightFrontLeg = root.getChild("right_front_leg");
        this.head = root.getChild("head");
        this.rightEar = root.getChild("right_ear");
        this.leftEar = root.getChild("left_ear");
        this.tail = root.getChild("tail");
        this.nose = root.getChild("nose");
//        float bodyX = -3.5F;
//        float bodyY = -6.0F;
//        float bodyZ = -11.5F;
//        float headX = bodyX + 1.5F;
//        float headY = -8.0F;
//        float headZ = -5.0F;
//
//        this.leftHindLeg = new ModelPart(this, 24, 47).setTextureSize(64, 64);
//        this.leftHindLeg.addCuboid(-0.0F, 5.5F, -2.7F, 3.0F, 2.0F, 9.0F);
//        this.leftHindLeg.setPivot(3.0F, 17.5F, 3.7F);
//        this.leftHindLeg.mirror = true;
//        this.setRotationOffset(this.leftHindLeg, 0.0F, 0.0F, 0.0F);
//
//        this.rightHindLeg = new ModelPart(this, 0, 47).setTextureSize(64, 64);
//        this.rightHindLeg.addCuboid(-2.0F, 5.5F, -2.7F, 3.0F, 2.0F, 9.0F);
//        this.rightHindLeg.setPivot(-3.0F, 17.5F, 3.7F);
//        this.rightHindLeg.mirror = true;
//        this.setRotationOffset(this.rightHindLeg, 0.0F, 0.0F, 0.0F);
//
//        this.leftHaunch = new ModelPart(this, 42, 14).setTextureSize(64, 64);
//        this.leftHaunch.addCuboid(-0.0F, -4.0F, 0.0F, 3.0F, 7.0F, 8.0F);
//        this.leftHaunch.setPivot(3.0F, 17.5F, 3.7F);
//        this.leftHaunch.mirror = true;
//        this.setRotationOffset(this.leftHaunch, -0.34906584F, 0.0F, 0.0F);
//
//        this.rightHaunch = new ModelPart(this, 42, 29).setTextureSize(64, 64);
//        this.rightHaunch.addCuboid(-2.0F, -4.0F, 0.0F, 3.0F, 7.0F, 8.0F);
//        this.rightHaunch.setPivot(-3.0F, 17.5F, 3.7F);
//        this.rightHaunch.mirror = true;
//        this.setRotationOffset(this.rightHaunch, -0.34906584F, 0.0F, 0.0F);
//
//        this.body = new ModelPart(this, 0, 0).setTextureSize(64, 64);
//        this.body.addCuboid(bodyX, bodyY, bodyZ, 8.0F, 8.0F, 14.0F);
//        this.body.setPivot(0.0F, 19.0F, 8.0F);
//        this.body.mirror = true;
//        this.setRotationOffset(this.body, -0.34906584F, 0.0F, 0.0F);
//
//        this.leftFrontLeg = new ModelPart(this, 8, 33).setTextureSize(64, 64);
//        this.leftFrontLeg.addCuboid(0.0F, -3.0F, -2.7F, 2.0F, 11.0F, 3.0F);
//        this.leftFrontLeg.setPivot(3.0F, 17.0F, -1.0F);
//        this.leftFrontLeg.mirror = true;
//        this.setRotationOffset(this.leftFrontLeg, -0.17453292F, 0.0F, 0.0F);
//
//        this.rightFrontLeg = new ModelPart(this, 0, 33).setTextureSize(64, 64);
//        this.rightFrontLeg.addCuboid(-1.0F, -3.0F, -2.7F, 2.0F, 11.0F, 3.0F);
//        this.rightFrontLeg.setPivot(-3.0F, 17.0F, -1.0F);
//        this.rightFrontLeg.mirror = true;
//        this.setRotationOffset(this.rightFrontLeg, -0.17453292F, 0.0F, 0.0F);
//
//        this.head = new ModelPart(this, 0, 22).setTextureSize(64, 64);
//        this.head.addCuboid(headX, headY, headZ, 5.0F, 5.0F, 6.0F);
//        this.head.setPivot(0.0F, 16.0F, -1.0F);
//        this.head.mirror = true;
//        this.setRotationOffset(this.head, 0.0F, 0.0F, 0.0F);
//
//        this.rightEar = new ModelPart(this, 48, 0).setTextureSize(64, 64);
//        this.rightEar.addCuboid(headX - 1.0F, headY - 12.0F, headZ + 4.0F, 3.0F, 10.0F, 1.0F)
//                .setTextureOffset(48, 11).addCuboid(headX, headY - 2.0F, headZ + 4.0F, 2.0F, 2.0F, 1.0F);
//        this.rightEar.setPivot(0.0F, 16.0F, -1.0F);
//        this.rightEar.mirror = true;
//        this.setRotationOffset(this.rightEar, 0.0F, -0.2617994F, 0.0F);
//
//        this.leftEar = new ModelPart(this, 56, 0).setTextureSize(64, 64);
//        this.leftEar.addCuboid(headX + 3.0F, headY - 12.0F, headZ + 4.0F, 3.0F, 10.0F, 1.0F)
//                .setTextureOffset(54, 11).addCuboid(headX + 3, headY - 2.0F, headZ + 4.0F, 2.0F, 2.0F, 1.0F);
//        this.leftEar.setPivot(0.0F, 16.0F, -1.0F);
//        this.leftEar.mirror = true;
//        this.setRotationOffset(this.leftEar, 0.0F, 0.2617994F, 0.0F);
//
//        this.tail = new ModelPart(this, 22, 22).setTextureSize(64, 64);
//        this.tail.addCuboid(bodyX + 2.5F, bodyY + 1.0F, bodyZ + 14.0F, 3.0F, 3.0F, 2.0F);
//        this.tail.setPivot(0.0F, 20.0F, 7.0F);
//        this.tail.mirror = true;
//        this.setRotationOffset(this.tail, -0.3490659F, 0.0F, 0.0F);
//
//        this.nose = new ModelPart(this, 22, 27).setTextureSize(64, 64);
//        this.nose.addCuboid(headX + 2.0F, headY + 2.0F, headZ - 1.0F, 1.0F, 1.0F, 1.0F);
//        this.nose.setPivot(0.0F, 16.0F, -1.0F);
//        this.nose.mirror = true;
        //this.setRotationOffset(this.nose, 0.0F, 0.0F, 0.0F);
    }


    public static TexturedModelData getTexturedModelData() {
        float bodyX = -3.5F;
        float bodyY = -6.0F;
        float bodyZ = -11.5F;
        float headX = bodyX + 1.5F;
        float headY = -8.0F;
        float headZ = -5.0F;
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        modelPartData.addChild("left_hind_foot", ModelPartBuilder.create().uv(24, 47).cuboid(0.0F, 5.5F, -2.7F, 3.0F, 2.0F, 9.0F).mirrored(), ModelTransform.pivot(3.0F, 17.5F, 3.7F));
        modelPartData.addChild("right_hind_foot", ModelPartBuilder.create().uv(0, 47).cuboid(-2.0F, 5.5F, -2.7F, 3.0F, 2.0F, 9.0F), ModelTransform.pivot(-3.0F, 17.5F, 3.7F));
        modelPartData.addChild("left_haunch", ModelPartBuilder.create().uv(42, 14).cuboid(0.0F, -4.0F, 0.0F, 3.0F, 7.0F, 8.0F).mirrored(), ModelTransform.of(3.0F, 17.5F, 3.7F, -0.34906584F, 0.0F, 0.0F));
        modelPartData.addChild("right_haunch", ModelPartBuilder.create().uv(42, 29).cuboid(-2.0F, -4.0F, 0.0F, 3.0F, 7.0F, 8.0F), ModelTransform.of(-3.0F, 17.5F, 3.7F, -0.34906584F, 0.0F, 0.0F));
        modelPartData.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(bodyX, bodyY, bodyZ, 8.0F, 8.0F, 14.0F), ModelTransform.of(0.0F, 19.0F, 8.0F, -0.34906584F, 0.0F, 0.0F));
        modelPartData.addChild("left_front_leg", ModelPartBuilder.create().uv(8, 33).cuboid(0.0F, -3.0F, -2.7F, 2.0F, 11.0F, 3.0F), ModelTransform.of(3.0F, 17.0F, -1.0F, -0.17453292F, 0.0F, 0.0F));
        modelPartData.addChild("right_front_leg", ModelPartBuilder.create().uv(0, 33).cuboid(-1.0F, -3.0F, -2.7F, 2.0F, 11.0F, 3.0F), ModelTransform.of(-3.0F, 17.0F, -1.0F, -0.17453292F, 0.0F, 0.0F));
        modelPartData.addChild("head", ModelPartBuilder.create().uv(0, 22).cuboid(headX, headY, headZ, 5.0F, 5.0F, 6.0F), ModelTransform.pivot(0.0F, 16.0F, -1.0F));
        modelPartData.addChild("right_ear", ModelPartBuilder.create().uv(48, 0).cuboid(headX - 1.0F, headY - 12.0F, headZ + 4.0F, 3.0F, 10.0F, 1.0F).uv(48, 11).cuboid(headX, headY - 2.0F, headZ + 4.0F, 2.0F, 2.0F, 1.0F), ModelTransform.of(0.0F, 16.0F, -1.0F, 0.0F, -0.2617994F, 0.0F));
        modelPartData.addChild("left_ear", ModelPartBuilder.create().uv(56, 0).cuboid(headX + 3.0F, headY - 12.0F, headZ + 4.0F, 3.0F, 10.0F, 1.0F).uv(54, 11).cuboid(headX + 3, headY - 2.0F, headZ + 4.0F, 2.0F, 2.0F, 1.0F), ModelTransform.of(0.0F, 16.0F, -1.0F, 0.0F, 0.2617994F, 0.0F));
        modelPartData.addChild("tail", ModelPartBuilder.create().uv(22, 22).cuboid(bodyX + 2.5F, bodyY + 1.0F, bodyZ + 14.0F, 3.0F, 3.0F, 2.0F), ModelTransform.of(0.0F, 20.0F, 7.0F, -0.3490659F, 0.0F, 0.0F));
        modelPartData.addChild("nose", ModelPartBuilder.create().uv(22, 27).cuboid(headX + 2.0F, headY + 2.0F, headZ - 1.0F, 1.0F, 1.0F, 1.0F), ModelTransform.pivot(0.0F, 16.0F, -1.0F));
        return TexturedModelData.of(modelData, 64, 64);
    }

    public void render(MatrixStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        if (this.child) {
            matrixStackIn.push();
            matrixStackIn.scale(0.56666666F, 0.56666666F, 0.56666666F);
            matrixStackIn.translate(0.0D, 1.375D, 0.125D);
            ImmutableList.of(this.head, this.leftEar, this.rightEar, this.nose).forEach((p_228292_8_) -> {
                p_228292_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
            });
            matrixStackIn.pop();
            matrixStackIn.push();
            matrixStackIn.scale(0.4F, 0.4F, 0.4F);
            matrixStackIn.translate(0.0D, 2.25D, 0.0D);
            ImmutableList.of(this.leftHindLeg, this.rightHindLeg, this.leftHaunch, this.rightHaunch, this.body, this.leftFrontLeg, this.rightFrontLeg, this.tail).forEach((p_228291_8_) -> {
                p_228291_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
            });
            matrixStackIn.pop();
        } else {
            matrixStackIn.push();
            matrixStackIn.scale(0.6F, 0.6F, 0.6F);
            matrixStackIn.translate(0.0D, 1.0D, 0.0D);
            ImmutableList.of(this.leftHindLeg, this.rightHindLeg, this.leftHaunch, this.rightHaunch, this.body, this.leftFrontLeg, this.rightFrontLeg, this.head, this.rightEar, this.leftEar, this.tail, this.nose).forEach((p_228290_8_) -> {
                p_228290_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
            });
            matrixStackIn.pop();
        }

    }

    public void setAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = ageInTicks - (float) entity.age;
        this.nose.pitch = headPitch * ((float) Math.PI / 180F);
        this.head.pitch = headPitch * ((float) Math.PI / 180F);
        this.rightEar.pitch = headPitch * ((float) Math.PI / 180F);
        this.leftEar.pitch = headPitch * ((float) Math.PI / 180F);
        this.nose.yaw = netHeadYaw * ((float) Math.PI / 180F);
        this.head.yaw = netHeadYaw * ((float) Math.PI / 180F);
        this.rightEar.yaw = this.nose.yaw - 0.2617994F;
        this.leftEar.yaw = this.nose.yaw + 0.2617994F;
        this.jumpProgress = MathHelper.sin(entity.getJumpProgress(f) * (float) Math.PI);
        this.leftHaunch.pitch = (this.jumpProgress * 50.0F - 21.0F) * ((float) Math.PI / 180F);
        this.rightHaunch.pitch = (this.jumpProgress * 50.0F - 21.0F) * ((float) Math.PI / 180F);
        this.leftHindLeg.pitch = this.jumpProgress * 50.0F * ((float) Math.PI / 180F);
        this.rightHindLeg.pitch = this.jumpProgress * 50.0F * ((float) Math.PI / 180F);
        this.leftFrontLeg.pitch = (this.jumpProgress * -40.0F - 11.0F) * ((float) Math.PI / 180F);
        this.rightFrontLeg.pitch = (this.jumpProgress * -40.0F - 11.0F) * ((float) Math.PI / 180F);
    }

    public void animateModel(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
        super.animateModel(entityIn, limbSwing, limbSwingAmount, partialTick);
        this.jumpProgress = MathHelper.sin(entityIn.getJumpProgress(partialTick) * (float) Math.PI);
    }

}