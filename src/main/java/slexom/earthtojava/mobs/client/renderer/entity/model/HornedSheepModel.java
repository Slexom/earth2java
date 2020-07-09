package slexom.earthtojava.mobs.client.renderer.entity.model;

import net.minecraft.client.renderer.entity.model.QuadrupedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import slexom.earthtojava.mobs.entity.passive.HornedSheepEntity;

@OnlyIn(Dist.CLIENT)
public class HornedSheepModel<T extends HornedSheepEntity> extends QuadrupedModel<T> {
    private float headRotationAngleX;


    public HornedSheepModel() {
        super(12, 0.0F, false, 8.0F, 4.0F, 2.0F, 2.0F, 24);
//        this.textureWidth = 64;
//        this.textureHeight = 64;


        this.headModel = new ModelRenderer(this, 0, 0).setTextureSize(64, 64);
        this.headModel.addBox(-3.0F, -4.0F, -6.0F, 6.0F, 6.0F, 8.0F, 0.0F);
        this.headModel.setRotationPoint(0.0F, 6.0F, -8.0F);
        ///////

        //this.headModel.setTextureOffset(0, 32).addBox(6.0F, 0.0F, 0.0F, 4.0F, 8.0F, 6.0F);
        float hornX = -7.0F;
        float hornY = -5.0F;
        float hornZ = -4.0F;
        this.headModel
                .setTextureOffset(0, 32)
                .addBox(hornX, hornY, hornZ, 4.0F, 7.0F, 6.0F)
                .setTextureOffset(20, 32)
                .addBox(hornX, hornY + 4.0F, hornZ - 3.0F, 4.0F, 3.0F, 3.0F);

        this.headModel
                .setTextureOffset(0, 32)
                .addBox(hornX + 10.0F, hornY, hornZ, 4.0F, 7.0F, 6.0F, true)
                .setTextureOffset(20, 32)
                .addBox(hornX + 10.0F, hornY + 4.0F, hornZ - 3.0F, 4.0F, 3.0F, 3.0F, true);

        ///////
        this.body = new ModelRenderer(this, 28, 8).setTextureSize(64, 64);
        this.body.addBox(-4.0F, -10.0F, -7.0F, 8.0F, 16.0F, 6.0F, 0.0F);
        this.body.setRotationPoint(0.0F, 5.0F, 2.0F);


        this.legBackRight = new ModelRenderer(this, 0, 16).setTextureSize(64, 64);
        this.legBackRight.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F);
        this.legBackRight.setRotationPoint(-3.0F, 24 - 12.0F, 7.0F);
        this.legBackLeft = new ModelRenderer(this, 0, 16).setTextureSize(64, 64);
        this.legBackLeft.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F);
        this.legBackLeft.setRotationPoint(3.0F, (24 - 12.0F), 7.0F);
        this.legFrontRight = new ModelRenderer(this, 0, 16).setTextureSize(64, 64);
        this.legFrontRight.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F);
        this.legFrontRight.setRotationPoint(-3.0F, (24 - 12.0F), -5.0F);
        this.legFrontLeft = new ModelRenderer(this, 0, 16).setTextureSize(64, 64);
        this.legFrontLeft.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F);
        this.legFrontLeft.setRotationPoint(3.0F, (24 - 12.0F), -5.0F);


    }

    public void setLivingAnimations(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
        super.setLivingAnimations(entityIn, limbSwing, limbSwingAmount, partialTick);
        this.headModel.rotationPointY = 6.0F + entityIn.getHeadRotationPointY(partialTick) * 9.0F;
        this.headRotationAngleX = entityIn.getHeadRotationAngleX(partialTick);
    }

    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        super.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        this.headModel.rotateAngleX = this.headRotationAngleX;
    }


}