package slexom.earthtojava.mobs.client.renderer.entity.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.QuadrupedEntityModel;
import slexom.earthtojava.mobs.entity.passive.RainbowSheepEntity;

@Environment(EnvType.CLIENT)
public class RainbowSheepWoolModel<T extends RainbowSheepEntity> extends QuadrupedEntityModel<T> {
    private float headRotationAngleX;

    public RainbowSheepWoolModel() {
        super(12, 0.0F, false, 8.0F, 4.0F, 2.0F, 2.0F, 24);
        this.head = new ModelPart(this, 0, 0).setTextureSize(64, 64);
        this.head.addCuboid(-4.0F, -5.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.6F);
        this.head.setPivot(0.0F, 6.0F, -8.0F);
        this.torso = new ModelPart(this, 22, 36).setTextureSize(64, 64);
        this.torso.addCuboid(-6.0F, -11.5F, -8.5F, 12.0F, 19.0F, 9.0F, 1.75F);
        this.torso.setPivot(0.0F, 5.0F, 2.0F);
        this.backRightLeg = new ModelPart(this, 0, 29).setTextureSize(64, 64);
        this.backRightLeg.addCuboid(-2.5F, -0.5F, -3.0F, 5.0F, 7.0F, 6.0F, 0.5F);
        this.backRightLeg.setPivot(-3.0F, 12.0F, 7.0F);
        this.backLeftLeg = new ModelPart(this, 0, 29).setTextureSize(64, 64);
        this.backLeftLeg.addCuboid(-2.5F, -0.5F, -3.0F, 5.0F, 7.0F, 6.0F, 0.5F);
        this.backLeftLeg.setPivot(3.0F, 12.0F, 7.0F);
        this.frontRightLeg = new ModelPart(this, 0, 16).setTextureSize(64, 64);
        this.frontRightLeg.addCuboid(-2.5F, -0.5F, -3.0F, 5.0F, 7.0F, 6.0F, 0.5F);
        this.frontRightLeg.setPivot(-3.0F, 12.0F, -5.0F);
        this.frontLeftLeg = new ModelPart(this, 0, 16).setTextureSize(64, 64);
        this.frontLeftLeg.addCuboid(-2.5F, -0.5F, -3.0F, 5.0F, 7.0F, 6.0F, 0.5F);
        this.frontLeftLeg.setPivot(3.0F, 12.0F, -5.0F);
    }

    public void animateModel(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
        super.animateModel(entityIn, limbSwing, limbSwingAmount, partialTick);
        this.head.pivotY = 6.0F + entityIn.getNeckAngle(partialTick) * 9.0F;
        this.headRotationAngleX = entityIn.getHeadAngle(partialTick);
    }

    public void setAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        super.setAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        this.head.pitch = this.headRotationAngleX;
    }
}