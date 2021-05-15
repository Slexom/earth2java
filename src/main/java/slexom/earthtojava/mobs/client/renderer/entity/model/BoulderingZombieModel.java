package slexom.earthtojava.mobs.client.renderer.entity.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.ZombieEntityModel;
import net.minecraft.util.math.MathHelper;
import slexom.earthtojava.mobs.entity.monster.BoulderingZombieEntity;

@Environment(EnvType.CLIENT)
public class BoulderingZombieModel<T extends BoulderingZombieEntity> extends ZombieEntityModel<T> {
    public BoulderingZombieModel(float scale, boolean bl) {
        super(scale, 0.0F, 64, bl ? 32 : 64);
    }

    public BoulderingZombieModel(float scale, float pivotY, int textureWidth, int textureHeight) {
        super(scale, pivotY, textureWidth, textureHeight);
        this.rightArm = new ModelPart(this, 16, 32);
        this.rightArm.addCuboid(-3.0F, -2.0F, -2.0F, 5.0F, 14.0F, 4.0F, scale);
        this.rightArm.setPivot(-5.0F, 2.0F + pivotY, 0.0F);
        this.leftArm = new ModelPart(this, 34, 32);
        this.leftArm.mirror = true;
        this.leftArm.addCuboid(-1.0F, -2.0F, -2.0F, 5.0F, 14.0F, 4.0F, scale);
        this.leftArm.setPivot(5.0F, 2.0F + pivotY, 0.0F);
        this.rightLeg = new ModelPart(this, 0, 16);
        this.rightLeg.addCuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, scale);
        this.rightLeg.setPivot(-1.9F, 12.0F + pivotY, 0.0F);
        this.leftLeg = new ModelPart(this, 0, 34);
        this.leftLeg.mirror = true;
        this.leftLeg.addCuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, scale);
        this.leftLeg.setPivot(1.9F, 12.0F + pivotY, 0.0F);
    }

    @Override
    public void animateModel(T livingEntity, float f, float g, float h) {
        this.rightArmPose = BipedEntityModel.ArmPose.EMPTY;
        this.leftArmPose = BipedEntityModel.ArmPose.EMPTY;
        super.animateModel(livingEntity, f, g, h);
    }

    @Override
    public void setAngles(T livingEntity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        super.setAngles(livingEntity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
        if (livingEntity.isClimbingWall()) {
            this.rightArm.yaw = -0.4F;
            this.rightArm.pitch = (float) Math.PI + MathHelper.cos(animationProgress * 0.65F + (float) Math.PI) * 0.33F;
            this.leftArm.yaw = 0.4F;
            this.leftArm.pitch = (float) Math.PI + MathHelper.cos(animationProgress * 0.65F) * 0.33F;
        }
    }

}
