package slexom.earthtojava.client.renderer.entity.model;

import com.google.common.collect.ImmutableList;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;
import slexom.earthtojava.entity.passive.JumboRabbitEntity;

@Environment(EnvType.CLIENT)
public class JumboRabbitModel<T extends JumboRabbitEntity> extends EntityModel<T> {

	private static final String LEFT_HAUNCH = "left_haunch";
	private static final String RIGHT_HAUNCH = "right_haunch";
	private final ModelPart leftHindLeg;
	private final ModelPart rightHindLeg;
	private final ModelPart leftHaunch;
	private final ModelPart rightHaunch;
	private final ModelPart body;
	private final ModelPart leftFrontLeg;
	private final ModelPart rightFrontLeg;
	private final ModelPart head;
	private final ModelPart rightEar;
	private final ModelPart leftEar;
	private final ModelPart tail;
	private final ModelPart nose;
	private float jumpProgress;

	public JumboRabbitModel(ModelPart root) {
		leftHindLeg = root.getChild(EntityModelPartNames.LEFT_HIND_FOOT);
		rightHindLeg = root.getChild(EntityModelPartNames.RIGHT_HIND_FOOT);
		leftHaunch = root.getChild(LEFT_HAUNCH);
		rightHaunch = root.getChild(RIGHT_HAUNCH);
		body = root.getChild(EntityModelPartNames.BODY);
		leftFrontLeg = root.getChild(EntityModelPartNames.LEFT_FRONT_LEG);
		rightFrontLeg = root.getChild(EntityModelPartNames.RIGHT_FRONT_LEG);
		head = root.getChild(EntityModelPartNames.HEAD);
		rightEar = root.getChild(EntityModelPartNames.RIGHT_EAR);
		leftEar = root.getChild(EntityModelPartNames.LEFT_EAR);
		tail = root.getChild(EntityModelPartNames.TAIL);
		nose = root.getChild(EntityModelPartNames.NOSE);
	}

	public static TexturedModelData getTexturedModelData() {
		float bodyX = -3.5F;
		float bodyY = -6.0F;
		float bodyZ = -11.5F;
		float headX = bodyX + 1.5F;
		float headY = -8.0F;
		float headZ = -5.0F;
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		modelPartData.addChild(EntityModelPartNames.LEFT_HIND_FOOT, ModelPartBuilder.create().uv(24, 47).cuboid(0.0F, 5.5F, -2.7F, 3.0F, 2.0F, 9.0F).mirrored(), ModelTransform.pivot(3.0F, 17.5F, 3.7F));
		modelPartData.addChild(EntityModelPartNames.RIGHT_HIND_FOOT, ModelPartBuilder.create().uv(0, 47).cuboid(-2.0F, 5.5F, -2.7F, 3.0F, 2.0F, 9.0F), ModelTransform.pivot(-3.0F, 17.5F, 3.7F));
		modelPartData.addChild(LEFT_HAUNCH, ModelPartBuilder.create().uv(42, 14).cuboid(0.0F, -4.0F, 0.0F, 3.0F, 7.0F, 8.0F).mirrored(), ModelTransform.of(3.0F, 17.5F, 3.7F, -0.34906584F, 0.0F, 0.0F));
		modelPartData.addChild(RIGHT_HAUNCH, ModelPartBuilder.create().uv(42, 29).cuboid(-2.0F, -4.0F, 0.0F, 3.0F, 7.0F, 8.0F), ModelTransform.of(-3.0F, 17.5F, 3.7F, -0.34906584F, 0.0F, 0.0F));
		modelPartData.addChild(EntityModelPartNames.BODY, ModelPartBuilder.create().uv(0, 0).cuboid(bodyX, bodyY, bodyZ, 8.0F, 8.0F, 14.0F), ModelTransform.of(0.0F, 19.0F, 8.0F, -0.34906584F, 0.0F, 0.0F));
		modelPartData.addChild(EntityModelPartNames.LEFT_FRONT_LEG, ModelPartBuilder.create().uv(8, 33).cuboid(0.0F, -3.0F, -2.7F, 2.0F, 11.0F, 3.0F), ModelTransform.of(3.0F, 17.0F, -1.0F, -0.17453292F, 0.0F, 0.0F));
		modelPartData.addChild(EntityModelPartNames.RIGHT_FRONT_LEG, ModelPartBuilder.create().uv(0, 33).cuboid(-1.0F, -3.0F, -2.7F, 2.0F, 11.0F, 3.0F), ModelTransform.of(-3.0F, 17.0F, -1.0F, -0.17453292F, 0.0F, 0.0F));
		modelPartData.addChild(EntityModelPartNames.HEAD, ModelPartBuilder.create().uv(0, 22).cuboid(headX, headY, headZ, 5.0F, 5.0F, 6.0F), ModelTransform.pivot(0.0F, 16.0F, -1.0F));
		modelPartData.addChild(EntityModelPartNames.RIGHT_EAR, ModelPartBuilder.create().uv(48, 0).cuboid(headX - 1.0F, headY - 12.0F, headZ + 4.0F, 3.0F, 10.0F, 1.0F).uv(48, 11).cuboid(headX, headY - 2.0F, headZ + 4.0F, 2.0F, 2.0F, 1.0F), ModelTransform.of(0.0F, 16.0F, -1.0F, 0.0F, -0.2617994F, 0.0F));
		modelPartData.addChild(EntityModelPartNames.LEFT_EAR, ModelPartBuilder.create().uv(56, 0).cuboid(headX + 3.0F, headY - 12.0F, headZ + 4.0F, 3.0F, 10.0F, 1.0F).uv(54, 11).cuboid(headX + 3, headY - 2.0F, headZ + 4.0F, 2.0F, 2.0F, 1.0F), ModelTransform.of(0.0F, 16.0F, -1.0F, 0.0F, 0.2617994F, 0.0F));
		modelPartData.addChild(EntityModelPartNames.TAIL, ModelPartBuilder.create().uv(22, 22).cuboid(bodyX + 2.5F, bodyY + 1.0F, bodyZ + 14.0F, 3.0F, 3.0F, 2.0F), ModelTransform.of(0.0F, 20.0F, 7.0F, -0.3490659F, 0.0F, 0.0F));
		modelPartData.addChild(EntityModelPartNames.NOSE, ModelPartBuilder.create().uv(22, 27).cuboid(headX + 2.0F, headY + 2.0F, headZ - 1.0F, 1.0F, 1.0F, 1.0F), ModelTransform.pivot(0.0F, 16.0F, -1.0F));
		return TexturedModelData.of(modelData, 64, 64);
	}

	public void render(MatrixStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
		if (child) {
			matrixStackIn.push();
			matrixStackIn.scale(0.56666666F, 0.56666666F, 0.56666666F);
			matrixStackIn.translate(0.0D, 1.375D, 0.125D);
			ImmutableList.of(head, leftEar, rightEar, nose).forEach((modelPart) -> modelPart.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha));
			matrixStackIn.pop();
			matrixStackIn.push();
			matrixStackIn.scale(0.4F, 0.4F, 0.4F);
			matrixStackIn.translate(0.0D, 2.25D, 0.0D);
			ImmutableList.of(leftHindLeg, rightHindLeg, leftHaunch, rightHaunch, body, leftFrontLeg, rightFrontLeg, tail).forEach(modelPart -> modelPart.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha));
			matrixStackIn.pop();
		} else {
			matrixStackIn.push();
			matrixStackIn.scale(0.6F, 0.6F, 0.6F);
			matrixStackIn.translate(0.0D, 1.0D, 0.0D);
			ImmutableList.of(leftHindLeg, rightHindLeg, leftHaunch, rightHaunch, body, leftFrontLeg, rightFrontLeg, head, rightEar, leftEar, tail, nose).forEach(modelPart -> modelPart.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha));
			matrixStackIn.pop();
		}
	}

	public void setAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		float f = ageInTicks - (float) entity.age;
		nose.pitch = headPitch * ((float) Math.PI / 180F);
		head.pitch = headPitch * ((float) Math.PI / 180F);
		rightEar.pitch = headPitch * ((float) Math.PI / 180F);
		leftEar.pitch = headPitch * ((float) Math.PI / 180F);
		nose.yaw = netHeadYaw * ((float) Math.PI / 180F);
		head.yaw = netHeadYaw * ((float) Math.PI / 180F);
		rightEar.yaw = nose.yaw - 0.2617994F;
		leftEar.yaw = nose.yaw + 0.2617994F;
		jumpProgress = MathHelper.sin(entity.getJumpProgress(f) * (float) Math.PI);
		leftHaunch.pitch = (jumpProgress * 50.0F - 21.0F) * ((float) Math.PI / 180F);
		rightHaunch.pitch = (jumpProgress * 50.0F - 21.0F) * ((float) Math.PI / 180F);
		leftHindLeg.pitch = jumpProgress * 50.0F * ((float) Math.PI / 180F);
		rightHindLeg.pitch = jumpProgress * 50.0F * ((float) Math.PI / 180F);
		leftFrontLeg.pitch = (jumpProgress * -40.0F - 11.0F) * ((float) Math.PI / 180F);
		rightFrontLeg.pitch = (jumpProgress * -40.0F - 11.0F) * ((float) Math.PI / 180F);
	}

	@Override
	public void animateModel(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
		super.animateModel(entityIn, limbSwing, limbSwingAmount, partialTick);
		jumpProgress = MathHelper.sin(entityIn.getJumpProgress(partialTick) * (float) Math.PI);
	}
}
