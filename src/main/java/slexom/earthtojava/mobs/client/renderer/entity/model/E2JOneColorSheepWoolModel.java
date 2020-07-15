package slexom.earthtojava.mobs.client.renderer.entity.model;

import net.minecraft.client.render.entity.model.QuadrupedEntityModel;
import net.minecraft.client.model.ModelPart;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import slexom.earthtojava.mobs.entity.base.E2JOneColorSheepEntity;

@Environment(EnvType.CLIENT)
public class E2JOneColorSheepWoolModel<T extends E2JOneColorSheepEntity<T>> extends QuadrupedEntityModel<T> {
    private float headRotationAngleX;

    public E2JOneColorSheepWoolModel() {
        super(12, 0.0F, false, 8.0F, 4.0F, 2.0F, 2.0F, 24);
        this.head = new ModelPart(this, 0, 0);
        this.head.addCuboid(-3.0F, -4.0F, -4.0F, 6.0F, 6.0F, 6.0F, 0.6F);
        this.head.setPivot(0.0F, 6.0F, -8.0F);
        this.torso = new ModelPart(this, 28, 8);
        this.torso.addCuboid(-4.0F, -10.0F, -7.0F, 8.0F, 16.0F, 6.0F, 1.75F);
        this.torso.setPivot(0.0F, 5.0F, 2.0F);
        float f = 0.5F;
        this.backRightLeg = new ModelPart(this, 0, 16);
        this.backRightLeg.addCuboid(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.5F);
        this.backRightLeg.setPivot(-3.0F, 12.0F, 7.0F);
        this.backLeftLeg = new ModelPart(this, 0, 16);
        this.backLeftLeg.addCuboid(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.5F);
        this.backLeftLeg.setPivot(3.0F, 12.0F, 7.0F);
        this.frontRightLeg = new ModelPart(this, 0, 16);
        this.frontRightLeg.addCuboid(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.5F);
        this.frontRightLeg.setPivot(-3.0F, 12.0F, -5.0F);
        this.frontLeftLeg = new ModelPart(this, 0, 16);
        this.frontLeftLeg.addCuboid(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.5F);
        this.frontLeftLeg.setPivot(3.0F, 12.0F, -5.0F);
    }

    public void animateModel(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
        super.animateModel(entityIn, limbSwing, limbSwingAmount, partialTick);
        this.head.pivotY= 6.0F + entityIn.getHeadRotationPointY(partialTick) * 9.0F;
        this.headRotationAngleX = entityIn.getHeadRotationAngleX(partialTick);
    }

    /**
     * Sets this entity's model rotation angles
     */
    public void setAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        super.setAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        this.head.pitch = this.headRotationAngleX;
    }
}