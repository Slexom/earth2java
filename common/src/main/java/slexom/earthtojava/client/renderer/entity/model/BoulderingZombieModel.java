package slexom.earthtojava.client.renderer.entity.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.client.render.entity.model.ZombieEntityModel;
import net.minecraft.util.math.MathHelper;
import slexom.earthtojava.entity.monster.BoulderingZombieEntity;

@Environment(EnvType.CLIENT)
public class BoulderingZombieModel<T extends BoulderingZombieEntity> extends ZombieEntityModel<T> {
	public BoulderingZombieModel(ModelPart modelPart) {
		super(modelPart);
	}

	public static ModelData getModelData(Dilation dilation, float pivotOffsetY) {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		modelPartData.addChild(EntityModelPartNames.HEAD, ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, dilation), ModelTransform.pivot(0.0F, 0.0F + pivotOffsetY, 0.0F));
		modelPartData.addChild(EntityModelPartNames.HAT, ModelPartBuilder.create().uv(32, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, dilation.add(0.5F)), ModelTransform.pivot(0.0F, 0.0F + pivotOffsetY, 0.0F));
		modelPartData.addChild(EntityModelPartNames.BODY, ModelPartBuilder.create().uv(16, 16).cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, dilation), ModelTransform.pivot(0.0F, 0.0F + pivotOffsetY, 0.0F));
		modelPartData.addChild(EntityModelPartNames.RIGHT_ARM, ModelPartBuilder.create().uv(16, 32).cuboid(-3.0F, -2.0F, -2.0F, 5.0F, 14.0F, 4.0F, dilation), ModelTransform.pivot(-5.0F, 2.0F + pivotOffsetY, 0.0F));
		modelPartData.addChild(EntityModelPartNames.LEFT_ARM, ModelPartBuilder.create().uv(34, 32).mirrored().cuboid(-1.0F, -2.0F, -2.0F, 5.0F, 14.0F, 4.0F, dilation), ModelTransform.pivot(5.0F, 2.0F + pivotOffsetY, 0.0F));
		modelPartData.addChild(EntityModelPartNames.RIGHT_LEG, ModelPartBuilder.create().uv(0, 16).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, dilation), ModelTransform.pivot(-1.9F, 12.0F + pivotOffsetY, 0.0F));
		modelPartData.addChild(EntityModelPartNames.LEFT_LEG, ModelPartBuilder.create().uv(0, 34).mirrored().cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, dilation), ModelTransform.pivot(1.9F, 12.0F + pivotOffsetY, 0.0F));
		return modelData;
	}

	public static TexturedModelData getTexturedModelData() {
		return TexturedModelData.of(getModelData(Dilation.NONE, 0.0F), 64, 64);
	}

	@Override
	public void animateModel(T livingEntity, float f, float g, float h) {
		rightArmPose = ArmPose.EMPTY;
		leftArmPose = ArmPose.EMPTY;
		super.animateModel(livingEntity, f, g, h);
	}

	@Override
	public void setAngles(T livingEntity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
		super.setAngles(livingEntity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
		if (livingEntity.isClimbingWall()) {
			rightArm.yaw = -0.4F;
			rightArm.pitch = (float) Math.PI + MathHelper.cos(animationProgress * 0.65F + (float) Math.PI) * 0.33F;
			leftArm.yaw = 0.4F;
			leftArm.pitch = (float) Math.PI + MathHelper.cos(animationProgress * 0.65F) * 0.33F;
		}
	}

}
