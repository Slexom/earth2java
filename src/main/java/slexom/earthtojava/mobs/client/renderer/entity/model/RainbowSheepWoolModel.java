package slexom.earthtojava.mobs.client.renderer.entity.model;

import net.minecraft.client.renderer.entity.model.QuadrupedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import slexom.earthtojava.mobs.entity.passive.RainbowSheepEntity;

public class RainbowSheepWoolModel<T extends RainbowSheepEntity> extends QuadrupedModel<T> {
    private float headRotationAngleX;

    public RainbowSheepWoolModel() {
        super(12, 0.0F, false, 8.0F, 4.0F, 2.0F, 2.0F, 24);
        this.headModel = new ModelRenderer(this, 0, 0).setTextureSize(64,64);
        this.headModel.addBox(-4.0F, -5.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.6F);
        this.headModel.setRotationPoint(0.0F, 6.0F, -8.0F);

        this.body = new ModelRenderer(this, 22, 36).setTextureSize(64,64);
        this.body.addBox(-6.0F, -11.5F, -8.5F, 12.0F, 19.0F, 9.0F, 1.75F);
        this.body.setRotationPoint(0.0F, 5.0F, 2.0F);
//        this.legFrontRight.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.5F);
        this.legBackRight = new ModelRenderer(this, 0, 29).setTextureSize(64,64);
        this.legBackRight.addBox(-2.5F, -0.5F, -3.0F, 5.0F, 7.0F, 6.0F, 0.5F);
        this.legBackRight.setRotationPoint(-3.0F, 12.0F, 7.0F);
        this.legBackLeft = new ModelRenderer(this, 0, 29).setTextureSize(64,64);
        this.legBackLeft.addBox(-2.5F, -0.5F, -3.0F, 5.0F, 7.0F, 6.0F, 0.5F);
        this.legBackLeft.setRotationPoint(3.0F, 12.0F, 7.0F);
        this.legFrontRight = new ModelRenderer(this, 0, 16).setTextureSize(64,64);
        this.legFrontRight.addBox(-2.5F, -0.5F, -3.0F, 5.0F, 7.0F, 6.0F, 0.5F);
        this.legFrontRight.setRotationPoint(-3.0F, 12.0F, -5.0F);
        this.legFrontLeft = new ModelRenderer(this, 0, 16).setTextureSize(64,64);
        this.legFrontLeft.addBox(-2.5F, -0.5F, -3.0F, 5.0F, 7.0F, 6.0F, 0.5F);
        this.legFrontLeft.setRotationPoint(3.0F, 12.0F, -5.0F);
    }

    public void setLivingAnimations(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
        super.setLivingAnimations(entityIn, limbSwing, limbSwingAmount, partialTick);
        this.headModel.rotationPointY = 6.0F + entityIn.getHeadRotationPointY(partialTick) * 9.0F;
        this.headRotationAngleX = entityIn.getHeadRotationAngleX(partialTick);
    }

    /**
     * Sets this entity's model rotation angles
     */
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        super.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        this.headModel.rotateAngleX = this.headRotationAngleX;
    }
}