package slexom.earthtojava.mobs.client.renderer.entity.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.CompositeEntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class MelonGolemModel<T extends Entity> extends CompositeEntityModel<T> {
    private final ModelPart torso;
    private final ModelPart bottomBody;
    private final ModelPart head;
    private final ModelPart rightHand;
    private final ModelPart leftHand;

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
        this.head.yaw = netHeadYaw * ((float) Math.PI / 180F);
        this.head.pitch = headPitch * ((float) Math.PI / 180F);
        this.torso.yaw = netHeadYaw * ((float) Math.PI / 180F) * 0.25F;
        float f = MathHelper.sin(this.torso.yaw);
        float f1 = MathHelper.cos(this.torso.yaw);
        this.rightHand.roll = 1.0F;
        this.leftHand.roll = -1.0F;
        this.rightHand.yaw = 0.0F + this.torso.yaw;
        this.leftHand.yaw = (float) Math.PI + this.torso.yaw;
        this.rightHand.pivotX = f1 * 5.0F;
        this.rightHand.pivotZ = -f * 5.0F;
        this.leftHand.pivotX = -f1 * 5.0F;
        this.leftHand.pivotZ = f * 5.0F;
    }

    public Iterable<ModelPart> getParts() {
        return ImmutableList.of(this.torso, this.bottomBody, this.head, this.rightHand, this.leftHand);
    }

    public ModelPart func_205070_a() {
        return this.head;
    }
}