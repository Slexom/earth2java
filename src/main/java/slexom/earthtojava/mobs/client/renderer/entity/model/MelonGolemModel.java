package slexom.earthtojava.mobs.client.renderer.entity.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.render.entity.model.SegmentedModel;
import net.minecraft.client.model.ModelPart;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class MelonGolemModel <T extends Entity> extends SegmentedModel<T> {
    private final ModelRenderer body;
    private final ModelRenderer bottomBody;
    private final ModelRenderer head;
    private final ModelRenderer rightHand;
    private final ModelRenderer leftHand;

    public MelonGolemModel() {
        this.head = (new ModelPart(this, 0, 0)).setTextureSize(128, 64);
        this.head.addCuboid(-8.0F, -16.0F, -8.0F, 16.0F, 16.0F, 16.0F, -0.5F);
        this.head.setPivot(0.0F, 4.0F, 0.0F);
        this.rightHand = (new ModelPart(this, 64, 0)).setTextureSize(128, 64);
        this.rightHand.addCuboid(-1.0F, 0.0F, -1.0F, 12.0F, 2.0F, 2.0F, -0.5F);
        this.rightHand.setPivot(0.0F, 6.0F, 0.0F);
        this.leftHand = (new ModelPart(this, 64, 0)).setTextureSize(128, 64);
        this.leftHand.addCuboid(-1.0F, 0.0F, -1.0F, 12.0F, 2.0F, 2.0F, -0.5F);
        this.leftHand.setPivot(0.0F, 6.0F, 0.0F);
        this.torso = (new ModelPart(this, 0, 32)).setTextureSize(128, 64);
        this.torso.addCuboid(-5.0F, -10.0F, -5.0F, 10.0F, 10.0F, 10.0F, -0.5F);
        this.torso.setPivot(0.0F, 13.0F, 0.0F);
        this.bottomBody = (new ModelPart(this, 64, 32)).setTextureSize(128, 64);
        this.bottomBody.addCuboid(-6.0F, -12.0F, -6.0F, 12.0F, 12.0F, 12.0F, -0.5F);
        this.bottomBody.setPivot(0.0F, 24.0F, 0.0F);
    }

    /**
     * Sets this entity's model rotation angles
     */
    public void setAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
        this.head.pitch = headPitch * ((float)Math.PI / 180F);
        this.torso.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F) * 0.25F;
        float f = MathHelper.sin(this.torso.rotateAngleY);
        float f1 = MathHelper.cos(this.torso.rotateAngleY);
        this.rightHand.rotateAngleZ = 1.0F;
        this.leftHand.rotateAngleZ = -1.0F;
        this.rightHand.rotateAngleY = 0.0F + this.torso.rotateAngleY;
        this.leftHand.rotateAngleY = (float)Math.PI + this.torso.rotateAngleY;
        this.rightHand.rotationPointX = f1 * 5.0F;
        this.rightHand.rotationPointZ = -f * 5.0F;
        this.leftHand.rotationPointX = -f1 * 5.0F;
        this.leftHand.rotationPointZ = f * 5.0F;
    }

    public Iterable<ModelRenderer> getParts() {
        return ImmutableList.of(this.torso, this.bottomBody, this.head, this.rightHand, this.leftHand);
    }

    public ModelRenderer func_205070_a() {
        return this.head;
    }
}