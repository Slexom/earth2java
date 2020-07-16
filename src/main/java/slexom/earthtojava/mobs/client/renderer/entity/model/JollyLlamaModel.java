package slexom.earthtojava.mobs.client.renderer.entity.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;
import slexom.earthtojava.mobs.entity.passive.JollyLlamaEntity;

public class JollyLlamaModel extends EntityModel<JollyLlamaEntity> {
    private final ModelPart head;
    private final ModelPart torso;
    private final ModelPart backRightLeg;
    private final ModelPart backLeftLeg;
    private final ModelPart frontRightLeg;
    private final ModelPart frontLeftLeg;

    public JollyLlamaModel(float p_i47226_1_) {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.head = new ModelPart(this, 0, 0);
        this.head.addCuboid(-2.0F, -14.0F, -10.0F, 4.0F, 4.0F, 9.0F, p_i47226_1_);
        this.head.setTextureOffset(96, 0).addCuboid(-2.5F, -14.05F, -10.5F, 5.0F, 5.0F, 10.0F, p_i47226_1_); // berries
        this.head.setTextureOffset(0, 47).addCuboid(4.5F, -30.0F, -2.0F, 8.0F, 16.0F, 1.0F, p_i47226_1_); // horn
        this.head.setTextureOffset(0, 47).addCuboid(-12.5F, -30.0F, -2.0F, 8.0F, 16.0F, 1.0F, p_i47226_1_, true); // horn
        this.head.setPivot(0.0F, 7.0F, -6.0F);
        this.head.setTextureOffset(0, 14).addCuboid(-4.0F, -16.0F, -6.0F, 8.0F, 18.0F, 6.0F, p_i47226_1_);
        this.head.setTextureOffset(96, 16).addCuboid(-4.5F, -16.0F, -6.5F, 9.0F, 18.0F, 7.0F, p_i47226_1_); //bells
        this.head.setTextureOffset(17, 0).addCuboid(-4.0F, -19.0F, -4.0F, 3.0F, 3.0F, 2.0F, p_i47226_1_);
        this.head.setTextureOffset(17, 0).addCuboid(1.0F, -19.0F, -4.0F, 3.0F, 3.0F, 2.0F, p_i47226_1_);
        this.torso = new ModelPart(this, 29, 0);
        this.torso.addCuboid(-6.0F, -10.0F, -7.0F, 12.0F, 18.0F, 10.0F, p_i47226_1_);
        this.torso.setPivot(0.0F, 5.0F, 2.0F);
        this.backRightLeg = new ModelPart(this, 29, 29);
        this.backRightLeg.addCuboid(-2.0F, 0.0F, -2.0F, 4.0F, 14.0F, 4.0F, p_i47226_1_);
        this.backRightLeg.setPivot(-2.5F, 10.0F, 6.0F);
        this.backLeftLeg = new ModelPart(this, 45, 29);
        this.backLeftLeg.addCuboid(-2.0F, 0.0F, -2.0F, 4.0F, 14.0F, 4.0F, p_i47226_1_);
        this.backLeftLeg.setPivot(2.5F, 10.0F, 6.0F);
        this.frontRightLeg = new ModelPart(this, 61, 29);
        this.frontRightLeg.addCuboid(-2.0F, 0.0F, -2.0F, 4.0F, 14.0F, 4.0F, p_i47226_1_);
        this.frontRightLeg.setPivot(-2.5F, 10.0F, -4.0F);
        this.frontLeftLeg = new ModelPart(this, 77, 29);
        this.frontLeftLeg.addCuboid(-2.0F, 0.0F, -2.0F, 4.0F, 14.0F, 4.0F, p_i47226_1_);
        this.frontLeftLeg.setPivot(2.5F, 10.0F, -4.0F);
        --this.backRightLeg.pivotX;
        ++this.backLeftLeg.pivotX;
        this.backRightLeg.pivotZ += 0.0F;
        this.backLeftLeg.pivotZ += 0.0F;
        --this.frontRightLeg.pivotX;
        ++this.frontLeftLeg.pivotX;
        --this.frontRightLeg.pivotZ;
        --this.frontLeftLeg.pivotZ;
    }

    /**
     * Sets this entity's model rotation angles
     */
    public void setAngles(JollyLlamaEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.pitch = headPitch * ((float) Math.PI / 180F);
        this.head.yaw = netHeadYaw * ((float) Math.PI / 180F);
        this.torso.pitch = ((float) Math.PI / 2F);
        this.backRightLeg.pitch = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.backLeftLeg.pitch = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
        this.frontRightLeg.pitch = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
        this.frontLeftLeg.pitch = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
    }

    public void render(MatrixStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        if (this.child) {
            matrixStackIn.push();
            matrixStackIn.scale(0.71428573F, 0.64935064F, 0.7936508F);
            matrixStackIn.translate(0.0D, 1.3125D, (double) 0.22F);
            this.head.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
            matrixStackIn.pop();
            matrixStackIn.push();
            matrixStackIn.scale(0.625F, 0.45454544F, 0.45454544F);
            matrixStackIn.translate(0.0D, 2.0625D, 0.0D);
            this.torso.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
            matrixStackIn.pop();
            matrixStackIn.push();
            matrixStackIn.scale(0.45454544F, 0.41322312F, 0.45454544F);
            matrixStackIn.translate(0.0D, 2.0625D, 0.0D);
            ImmutableList.of(this.backRightLeg, this.backLeftLeg, this.frontRightLeg, this.frontLeftLeg).forEach((p_228280_8_) -> {
                p_228280_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
            });
            matrixStackIn.pop();
        } else {
            ImmutableList.of(this.head, this.torso, this.backRightLeg, this.backLeftLeg, this.frontRightLeg, this.frontLeftLeg).forEach((p_228279_8_) -> {
                p_228279_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
            });
        }

    }
}