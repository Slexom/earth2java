package slexom.earthtojava.mobs.client.renderer.entity.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.render.entity.model.SegmentedModel;
import net.minecraft.client.model.ModelPart;
import net.minecraft.entity.passive.IronGolemEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class FurnaceGolemModel<T extends IronGolemEntity> extends SegmentedModel<T> {
    private final ModelRenderer ironGolemHead;
    private final ModelRenderer ironGolemBody;
    private final ModelRenderer ironGolemRightArm;
    private final ModelRenderer ironGolemLeftArm;
    private final ModelRenderer ironGolemLeftLeg;
    private final ModelRenderer ironGolemRightLeg;

    public FurnaceGolemModel() {
        this.ironGolemHead = (new ModelPart(this)).setTextureSize(128, 128);
        this.ironGolemHead.setPivot(0.0F, -7.0F, -2.0F);
        this.ironGolemHead.setTextureOffset(0, 0).addCuboid(-4.0F, -12.0F, -5.5F, 8.0F, 10.0F, 8.0F, 0.0F);
        this.ironGolemHead.setTextureOffset(24, 0).addCuboid(-1.0F, -5.0F, -7.5F, 2.0F, 4.0F, 2.0F, 0.0F);
        this.ironGolemBody = (new ModelPart(this)).setTextureSize(128, 128);
        this.ironGolemBody.setPivot(0.0F, -7.0F, 0.0F);
        this.ironGolemBody.setTextureOffset(0, 40).addCuboid(-9.0F, -2.0F, -6.0F, 18.0F, 12.0F, 11.0F, 0.0F);
        this.ironGolemBody.setTextureOffset(0, 70).addCuboid(-4.5F, 10.0F, -3.0F, 9.0F, 5.0F, 6.0F, 0.5F);
        this.ironGolemRightArm = (new ModelPart(this)).setTextureSize(128, 128);
        this.ironGolemRightArm.setPivot(0.0F, -7.0F, 0.0F);
        this.ironGolemRightArm.setTextureOffset(60, 21).addCuboid(-13.0F, -2.5F, -3.0F, 4.0F, 30.0F, 6.0F, 0.0F);
        this.ironGolemLeftArm = (new ModelPart(this)).setTextureSize(128, 128);
        this.ironGolemLeftArm.setPivot(0.0F, -7.0F, 0.0F);
        this.ironGolemLeftArm.setTextureOffset(60, 58).addCuboid(9.0F, -2.5F, -3.0F, 4.0F, 30.0F, 6.0F, 0.0F);
        this.ironGolemLeftLeg = (new ModelPart(this, 0, 22)).setTextureSize(128, 128);
        this.ironGolemLeftLeg.setPivot(-4.0F, 11.0F, 0.0F);
        this.ironGolemLeftLeg.setTextureOffset(37, 0).addCuboid(-3.5F, -3.0F, -3.0F, 6.0F, 16.0F, 5.0F, 0.0F);
        this.ironGolemRightLeg = (new ModelPart(this, 0, 22)).setTextureSize(128, 128);
        this.ironGolemRightLeg.mirror = true;
        this.ironGolemRightLeg.setTextureOffset(60, 0).setPivot(5.0F, 11.0F, 0.0F);
        this.ironGolemRightLeg.addCuboid(-3.5F, -3.0F, -3.0F, 6.0F, 16.0F, 5.0F, 0.0F);
    }

    public Iterable<ModelRenderer> getParts() {
        return ImmutableList.of(this.ironGolemHead, this.ironGolemBody, this.ironGolemLeftLeg, this.ironGolemRightLeg, this.ironGolemRightArm, this.ironGolemLeftArm);
    }

    public void setAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.ironGolemHead.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
        this.ironGolemHead.pitch = headPitch * ((float)Math.PI / 180F);
        this.ironGolemLeftLeg.pitch = -1.5F * this.triangleWave(limbSwing, 13.0F) * limbSwingAmount;
        this.ironGolemRightLeg.pitch = 1.5F * this.triangleWave(limbSwing, 13.0F) * limbSwingAmount;
        this.ironGolemLeftLeg.rotateAngleY = 0.0F;
        this.ironGolemRightLeg.rotateAngleY = 0.0F;
    }

    public void animateModel(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
        int i = entityIn.getAttackTimer();
        if (i > 0) {
            this.ironGolemRightArm.pitch = -2.0F + 1.5F * this.triangleWave((float)i - partialTick, 10.0F);
            this.ironGolemLeftArm.pitch = -2.0F + 1.5F * this.triangleWave((float)i - partialTick, 10.0F);
        } else {
            int j = entityIn.getHoldRoseTick();
            if (j > 0) {
                this.ironGolemRightArm.pitch = -0.8F + 0.025F * this.triangleWave((float)j, 70.0F);
                this.ironGolemLeftArm.pitch = 0.0F;
            } else {
                this.ironGolemRightArm.pitch = (-0.2F + 1.5F * this.triangleWave(limbSwing, 13.0F)) * limbSwingAmount;
                this.ironGolemLeftArm.pitch = (-0.2F - 1.5F * this.triangleWave(limbSwing, 13.0F)) * limbSwingAmount;
            }
        }

    }

    private float triangleWave(float p_78172_1_, float p_78172_2_) {
        return (Math.abs(p_78172_1_ % p_78172_2_ - p_78172_2_ * 0.5F) - p_78172_2_ * 0.25F) / (p_78172_2_ * 0.25F);
    }

    public ModelRenderer getArmHoldingRose() {
        return this.ironGolemRightArm;
    }
}