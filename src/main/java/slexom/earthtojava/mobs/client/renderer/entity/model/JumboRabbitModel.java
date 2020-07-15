package slexom.earthtojava.mobs.client.renderer.entity.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.model.ModelPart;
import net.minecraft.util.math.MathHelper;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import slexom.earthtojava.mobs.entity.passive.JumboRabbitEntity;

@Environment(EnvType.CLIENT)
public class JumboRabbitEntityModel<T extends JumboRabbitEntity> extends EntityModel<T> {
    /**
     * The Rabbit's Left Foot
     */
    private final ModelRenderer rabbitLeftFoot;
    /**
     * The Rabbit's Right Foot
     */
    private final ModelRenderer rabbitRightFoot;
    /**
     * The Rabbit's Left Thigh
     */
    private final ModelRenderer rabbitLeftThigh;
    /**
     * The Rabbit's Right Thigh
     */
    private final ModelRenderer rabbitRightThigh;
    /**
     * The Rabbit's Body
     */
    private final ModelRenderer rabbitBody;
    /**
     * The Rabbit's Left Arm
     */
    private final ModelRenderer rabbitLeftArm;
    /**
     * The Rabbit's Right Arm
     */
    private final ModelRenderer rabbitRightArm;
    /**
     * The Rabbit's Head
     */
    private final ModelRenderer rabbitHead;
    /**
     * The Rabbit's Right Ear
     */
    private final ModelRenderer rabbitRightEar;
    /**
     * The Rabbit's Left Ear
     */
    private final ModelRenderer rabbitLeftEar;
    /**
     * The Rabbit's Tail
     */
    private final ModelRenderer rabbitTail;
    /**
     * The Rabbit's Nose
     */
    private final ModelRenderer rabbitNose;
    private float jumpRotation;

    public JumboRabbitEntityModel() {

        float bodyX = -3.5F;
        float bodyY = -6.0F;
        float bodyZ = -11.5F;
        float headX = bodyX + 1.5F;
        float headY = -8.0F;
        float headZ = -5.0F;

        this.rabbitLeftFoot = new ModelPart(this, 24, 47).setTextureSize(64, 64);
        this.rabbitLeftFoot.addCuboid(-0.0F, 5.5F, -2.7F, 3.0F, 2.0F, 9.0F);
        this.rabbitLeftFoot.setPivot(3.0F, 17.5F, 3.7F);
        this.rabbitLeftFoot.mirror = true;
        this.setRotationOffset(this.rabbitLeftFoot, 0.0F, 0.0F, 0.0F);

        this.rabbitRightFoot = new ModelPart(this, 0, 47).setTextureSize(64, 64);
        this.rabbitRightFoot.addCuboid(-2.0F, 5.5F, -2.7F, 3.0F, 2.0F, 9.0F);
        this.rabbitRightFoot.setPivot(-3.0F, 17.5F, 3.7F);
        this.rabbitRightFoot.mirror = true;
        this.setRotationOffset(this.rabbitRightFoot, 0.0F, 0.0F, 0.0F);

        this.rabbitLeftThigh = new ModelPart(this, 42, 14).setTextureSize(64, 64);
        this.rabbitLeftThigh.addCuboid(-0.0F, -4.0F, 0.0F, 3.0F, 7.0F, 8.0F);
        this.rabbitLeftThigh.setPivot(3.0F, 17.5F, 3.7F);
        this.rabbitLeftThigh.mirror = true;
        this.setRotationOffset(this.rabbitLeftThigh, -0.34906584F, 0.0F, 0.0F);

        this.rabbitRightThigh = new ModelPart(this, 42, 29).setTextureSize(64, 64);
        this.rabbitRightThigh.addCuboid(-2.0F, -4.0F, 0.0F, 3.0F, 7.0F, 8.0F);
        this.rabbitRightThigh.setPivot(-3.0F, 17.5F, 3.7F);
        this.rabbitRightThigh.mirror = true;
        this.setRotationOffset(this.rabbitRightThigh, -0.34906584F, 0.0F, 0.0F);

        this.rabbitBody = new ModelPart(this, 0, 0).setTextureSize(64, 64);
        this.rabbitBody.addCuboid(bodyX, bodyY, bodyZ, 8.0F, 8.0F, 14.0F);
        this.rabbitBody.setPivot(0.0F, 19.0F, 8.0F);
        this.rabbitBody.mirror = true;
        this.setRotationOffset(this.rabbitBody, -0.34906584F, 0.0F, 0.0F);

        this.rabbitLeftArm = new ModelPart(this, 8, 33).setTextureSize(64, 64);
        this.rabbitLeftArm.addCuboid(0.0F, -3.0F, -2.7F, 2.0F, 11.0F, 3.0F);
        this.rabbitLeftArm.setPivot(3.0F, 17.0F, -1.0F);
        this.rabbitLeftArm.mirror = true;
        this.setRotationOffset(this.rabbitLeftArm, -0.17453292F, 0.0F, 0.0F);

        this.rabbitRightArm = new ModelPart(this, 0, 33).setTextureSize(64, 64);
        this.rabbitRightArm.addCuboid(-1.0F, -3.0F, -2.7F, 2.0F, 11.0F, 3.0F);
        this.rabbitRightArm.setPivot(-3.0F, 17.0F, -1.0F);
        this.rabbitRightArm.mirror = true;
        this.setRotationOffset(this.rabbitRightArm, -0.17453292F, 0.0F, 0.0F);

        this.rabbitHead = new ModelPart(this, 0, 22).setTextureSize(64, 64);
        this.rabbitHead.addCuboid(headX, headY, headZ, 5.0F, 5.0F, 6.0F);
        this.rabbitHead.setPivot(0.0F, 16.0F, -1.0F);
        this.rabbitHead.mirror = true;
        this.setRotationOffset(this.rabbitHead, 0.0F, 0.0F, 0.0F);

        this.rabbitRightEar = new ModelPart(this, 48, 0).setTextureSize(64, 64);
        this.rabbitRightEar.addCuboid(headX - 1.0F, headY - 12.0F, headZ + 4.0F, 3.0F, 10.0F, 1.0F)
                .setTextureOffset(48, 11).addCuboid(headX, headY - 2.0F, headZ + 4.0F, 2.0F, 2.0F, 1.0F);
        this.rabbitRightEar.setPivot(0.0F, 16.0F, -1.0F);
        this.rabbitRightEar.mirror = true;
        this.setRotationOffset(this.rabbitRightEar, 0.0F, -0.2617994F, 0.0F);

        this.rabbitLeftEar = new ModelPart(this, 56, 0).setTextureSize(64, 64);
        this.rabbitLeftEar.addCuboid(headX + 3.0F, headY - 12.0F, headZ + 4.0F, 3.0F, 10.0F, 1.0F)
                .setTextureOffset(54, 11).addCuboid(headX + 3, headY - 2.0F, headZ + 4.0F, 2.0F, 2.0F, 1.0F);
        this.rabbitLeftEar.setPivot(0.0F, 16.0F, -1.0F);
        this.rabbitLeftEar.mirror = true;
        this.setRotationOffset(this.rabbitLeftEar, 0.0F, 0.2617994F, 0.0F);

        this.rabbitTail = new ModelPart(this, 22, 22).setTextureSize(64, 64);
        this.rabbitTail.addCuboid(bodyX + 2.5F, bodyY + 1.0F, bodyZ + 14.0F, 3.0F, 3.0F, 2.0F);
        this.rabbitTail.setPivot(0.0F, 20.0F, 7.0F);
        this.rabbitTail.mirror = true;
        this.setRotationOffset(this.rabbitTail, -0.3490659F, 0.0F, 0.0F);

        this.rabbitNose = new ModelPart(this, 22, 27).setTextureSize(64, 64);
        this.rabbitNose.addCuboid(headX + 2.0F, headY + 2.0F, headZ - 1.0F, 1.0F, 1.0F, 1.0F);
        this.rabbitNose.setPivot(0.0F, 16.0F, -1.0F);
        this.rabbitNose.mirror = true;
        this.setRotationOffset(this.rabbitNose, 0.0F, 0.0F, 0.0F);
    }

    private void setRotationOffset(ModelRenderer renderer, float x, float y, float z) {
        renderer.pitch = x;
        renderer.rotateAngleY = y;
        renderer.rotateAngleZ = z;
    }

    public void render(MatrixStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        if (this.isChild) {
            float f = 1.5F;
            matrixStackIn.push();
            matrixStackIn.scale(0.56666666F, 0.56666666F, 0.56666666F);
            matrixStackIn.translate(0.0D, 1.375D, 0.125D);
            ImmutableList.of(this.rabbitHead, this.rabbitLeftEar, this.rabbitRightEar, this.rabbitNose).forEach((p_228292_8_) -> {
                p_228292_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
            });
            matrixStackIn.pop();
            matrixStackIn.push();
            matrixStackIn.scale(0.4F, 0.4F, 0.4F);
            matrixStackIn.translate(0.0D, 2.25D, 0.0D);
            ImmutableList.of(this.rabbitLeftFoot, this.rabbitRightFoot, this.rabbitLeftThigh, this.rabbitRightThigh, this.rabbitBody, this.rabbitLeftArm, this.rabbitRightArm, this.rabbitTail).forEach((p_228291_8_) -> {
                p_228291_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
            });
            matrixStackIn.pop();
        } else {
            matrixStackIn.push();
            matrixStackIn.scale(0.6F, 0.6F, 0.6F);
            matrixStackIn.translate(0.0D, 1.0D, 0.0D);
            ImmutableList.of(this.rabbitLeftFoot, this.rabbitRightFoot, this.rabbitLeftThigh, this.rabbitRightThigh, this.rabbitBody, this.rabbitLeftArm, this.rabbitRightArm, this.rabbitHead, this.rabbitRightEar, this.rabbitLeftEar, this.rabbitTail, this.rabbitNose).forEach((p_228290_8_) -> {
                p_228290_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
            });
            matrixStackIn.pop();
        }

    }

    /**
     * Sets this entity's model rotation angles
     */
    public void setAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = ageInTicks - (float) entityIn.ticksExisted;
        this.rabbitNose.pitch = headPitch * ((float) Math.PI / 180F);
        this.rabbitHead.pitch = headPitch * ((float) Math.PI / 180F);
        this.rabbitRightEar.pitch = headPitch * ((float) Math.PI / 180F);
        this.rabbitLeftEar.pitch = headPitch * ((float) Math.PI / 180F);
        this.rabbitNose.rotateAngleY = netHeadYaw * ((float) Math.PI / 180F);
        this.rabbitHead.rotateAngleY = netHeadYaw * ((float) Math.PI / 180F);
        this.rabbitRightEar.rotateAngleY = this.rabbitNose.rotateAngleY - 0.2617994F;
        this.rabbitLeftEar.rotateAngleY = this.rabbitNose.rotateAngleY + 0.2617994F;
        this.jumpRotation = MathHelper.sin(entityIn.getJumpCompletion(f) * (float) Math.PI);
        this.rabbitLeftThigh.pitch = (this.jumpRotation * 50.0F - 21.0F) * ((float) Math.PI / 180F);
        this.rabbitRightThigh.pitch = (this.jumpRotation * 50.0F - 21.0F) * ((float) Math.PI / 180F);
        this.rabbitLeftFoot.pitch = this.jumpRotation * 50.0F * ((float) Math.PI / 180F);
        this.rabbitRightFoot.pitch = this.jumpRotation * 50.0F * ((float) Math.PI / 180F);
        this.rabbitLeftArm.pitch = (this.jumpRotation * -40.0F - 11.0F) * ((float) Math.PI / 180F);
        this.rabbitRightArm.pitch = (this.jumpRotation * -40.0F - 11.0F) * ((float) Math.PI / 180F);
    }

    public void animateModel(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
        super.animateModel(entityIn, limbSwing, limbSwingAmount, partialTick);
        this.jumpRotation = MathHelper.sin(entityIn.getJumpCompletion(partialTick) * (float) Math.PI);
    }
}