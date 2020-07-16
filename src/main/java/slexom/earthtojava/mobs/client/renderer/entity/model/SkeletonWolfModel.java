package slexom.earthtojava.mobs.client.renderer.entity.model;

import com.google.common.collect.ImmutableList;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.TintableAnimalModel;
import net.minecraft.util.math.MathHelper;
import slexom.earthtojava.mobs.entity.monster.SkeletonWolfEntity;

@Environment(EnvType.CLIENT)
public class SkeletonWolfModel<T extends SkeletonWolfEntity> extends TintableAnimalModel<T> {
    private final ModelPart head;
    private final ModelPart headChild;
    private final ModelPart torso;
    private final ModelPart backRightLeg;
    private final ModelPart backLeftLeg;
    private final ModelPart frontRightLeg;
    private final ModelPart frontLeftLeg;
    private final ModelPart tail;
    private final ModelPart tailChild;
    private final ModelPart mane;

    public SkeletonWolfModel() {
        this.head = new ModelPart(this, 0, 0);
        this.head.setPivot(-1.0F, 13.5F, -7.0F);
        this.headChild = new ModelPart(this, 0, 0);
        this.headChild.addCuboid(-2.0F, -3.0F, -2.0F, 6.0F, 6.0F, 4.0F, 0.0F);
        this.head.addChild(this.headChild);
        this.torso = new ModelPart(this, 18, 14);
        this.torso.addCuboid(-3.0F, -2.0F, -3.0F, 6.0F, 9.0F, 6.0F, 0.0F);
        this.torso.setPivot(0.0F, 14.0F, 2.0F);
        this.mane = new ModelPart(this, 21, 0);
        this.mane.addCuboid(-3.0F, -3.0F, -3.0F, 8.0F, 6.0F, 7.0F, 0.0F);
        this.mane.setPivot(-1.0F, 14.0F, 2.0F);
        this.backRightLeg = new ModelPart(this, 0, 18);
        this.backRightLeg.addCuboid(0.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F);
        this.backRightLeg.setPivot(-2.5F, 16.0F, 7.0F);
        this.backLeftLeg = new ModelPart(this, 0, 18);
        this.backLeftLeg.addCuboid(0.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F);
        this.backLeftLeg.setPivot(0.5F, 16.0F, 7.0F);
        this.frontRightLeg = new ModelPart(this, 0, 18);
        this.frontRightLeg.addCuboid(0.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F);
        this.frontRightLeg.setPivot(-2.5F, 16.0F, -4.0F);
        this.frontLeftLeg = new ModelPart(this, 0, 18);
        this.frontLeftLeg.addCuboid(0.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F);
        this.frontLeftLeg.setPivot(0.5F, 16.0F, -4.0F);
        this.tail = new ModelPart(this, 9, 18);
        this.tail.setPivot(-1.0F, 12.0F, 8.0F);
        this.tailChild = new ModelPart(this, 9, 18);
        this.tailChild.addCuboid(0.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F);
        this.tail.addChild(this.tailChild);
        this.headChild.setTextureOffset(16, 14).addCuboid(-2.0F, -5.0F, 0.0F, 2.0F, 2.0F, 1.0F, 0.0F);
        this.headChild.setTextureOffset(16, 14).addCuboid(2.0F, -5.0F, 0.0F, 2.0F, 2.0F, 1.0F, 0.0F);
        this.headChild.setTextureOffset(0, 10).addCuboid(-0.5F, 0.0F, -5.0F, 3.0F, 3.0F, 4.0F, 0.0F);
    }

    protected Iterable<ModelPart> getHeadParts() {
        return ImmutableList.of(this.head);
    }

    protected Iterable<ModelPart> getBodyParts() {
        return ImmutableList.of(this.torso, this.backRightLeg, this.backLeftLeg, this.frontRightLeg, this.frontLeftLeg, this.tail, this.mane);
    }

    public void animateModel(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
        if (entityIn.isAngry()) {
            this.tail.yaw = 0.0F;
        } else {
            this.tail.yaw = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        }
        this.torso.setPivot(0.0F, 14.0F, 2.0F);
        this.torso.pitch = ((float) Math.PI / 2F);
        this.mane.setPivot(-1.0F, 14.0F, -3.0F);
        this.mane.pitch = this.torso.pitch;
        this.tail.setPivot(-1.0F, 12.0F, 8.0F);
        this.backRightLeg.setPivot(-2.5F, 16.0F, 7.0F);
        this.backLeftLeg.setPivot(0.5F, 16.0F, 7.0F);
        this.frontRightLeg.setPivot(-2.5F, 16.0F, -4.0F);
        this.frontLeftLeg.setPivot(0.5F, 16.0F, -4.0F);
        this.backRightLeg.pitch = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.backLeftLeg.pitch = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
        this.frontRightLeg.pitch = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
        this.frontLeftLeg.pitch = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.headChild.roll = entityIn.getInterestedAngle(partialTick);
    }

    /**
     * Sets this entity's model rotation angles
     */
    public void setAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.pitch = headPitch * ((float) Math.PI / 180F);
        this.head.yaw = netHeadYaw * ((float) Math.PI / 180F);
        this.tail.pitch = ageInTicks;
    }
}