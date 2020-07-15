package slexom.earthtojava.mobs.client.renderer.entity.model;

import net.minecraft.client.render.entity.model.QuadrupedEntityModel;
import net.minecraft.client.model.ModelPart;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import slexom.earthtojava.mobs.entity.base.E2JOneColorSheepEntity;

@Environment(EnvType.CLIENT)
public class E2JOneColorSheepModel<T extends E2JOneColorSheepEntity<T>> extends QuadrupedEntityModel<T> {
    private float headRotationAngleX;

    public E2JOneColorSheepModel() {
        super(12, 0.0F, false, 8.0F, 4.0F, 2.0F, 2.0F, 24);
        this.head = new ModelPart(this, 0, 0);
        this.head.addCuboid(-3.0F, -4.0F, -6.0F, 6.0F, 6.0F, 8.0F, 0.0F);
        this.head.setPivot(0.0F, 6.0F, -8.0F);
        this.torso = new ModelPart(this, 28, 8);
        this.torso.addCuboid(-4.0F, -10.0F, -7.0F, 8.0F, 16.0F, 6.0F, 0.0F);
        this.torso.setPivot(0.0F, 5.0F, 2.0F);
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