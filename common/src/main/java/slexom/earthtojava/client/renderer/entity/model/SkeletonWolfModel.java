package slexom.earthtojava.client.renderer.entity.model;

import com.google.common.collect.ImmutableList;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.TintableAnimalModel;
import net.minecraft.util.math.MathHelper;
import slexom.earthtojava.entity.monster.SkeletonWolfEntity;

@Environment(EnvType.CLIENT)
public class SkeletonWolfModel<T extends SkeletonWolfEntity> extends TintableAnimalModel<T> {
	private final ModelPart head;
	private final ModelPart realHead;
	private final ModelPart torso;
	private final ModelPart rightHindLeg;
	private final ModelPart leftHindLeg;
	private final ModelPart rightFrontLeg;
	private final ModelPart leftFrontLeg;
	private final ModelPart tail;
	private final ModelPart realTail;
	private final ModelPart neck;

	public SkeletonWolfModel(ModelPart root) {
		head = root.getChild("head");
		realHead = head.getChild("real_head");
		torso = root.getChild("body");
		neck = root.getChild("upper_body");
		rightHindLeg = root.getChild("right_hind_leg");
		leftHindLeg = root.getChild("left_hind_leg");
		rightFrontLeg = root.getChild("right_front_leg");
		leftFrontLeg = root.getChild("left_front_leg");
		tail = root.getChild("tail");
		realTail = tail.getChild("real_tail");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData modelPartData2 = modelPartData.addChild("head", ModelPartBuilder.create(), ModelTransform.pivot(-1.0F, 13.5F, -7.0F));
		modelPartData2.addChild("real_head", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -3.0F, -2.0F, 6.0F, 6.0F, 4.0F).uv(16, 14).cuboid(-2.0F, -5.0F, 0.0F, 2.0F, 2.0F, 1.0F).uv(16, 14).cuboid(2.0F, -5.0F, 0.0F, 2.0F, 2.0F, 1.0F).uv(0, 10).cuboid(-0.5F, 0.0F, -5.0F, 3.0F, 3.0F, 4.0F), ModelTransform.NONE);
		modelPartData.addChild("body", ModelPartBuilder.create().uv(18, 14).cuboid(-3.0F, -2.0F, -3.0F, 6.0F, 9.0F, 6.0F), ModelTransform.of(0.0F, 14.0F, 2.0F, 1.5707964F, 0.0F, 0.0F));
		modelPartData.addChild("upper_body", ModelPartBuilder.create().uv(21, 0).cuboid(-3.0F, -3.0F, -3.0F, 8.0F, 6.0F, 7.0F), ModelTransform.of(-1.0F, 14.0F, -3.0F, 1.5707964F, 0.0F, 0.0F));
		ModelPartBuilder modelPartBuilder = ModelPartBuilder.create().uv(0, 18).cuboid(0.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F);
		modelPartData.addChild("right_hind_leg", modelPartBuilder, ModelTransform.pivot(-2.5F, 16.0F, 7.0F));
		modelPartData.addChild("left_hind_leg", modelPartBuilder, ModelTransform.pivot(0.5F, 16.0F, 7.0F));
		modelPartData.addChild("right_front_leg", modelPartBuilder, ModelTransform.pivot(-2.5F, 16.0F, -4.0F));
		modelPartData.addChild("left_front_leg", modelPartBuilder, ModelTransform.pivot(0.5F, 16.0F, -4.0F));
		ModelPartData modelPartData3 = modelPartData.addChild("tail", ModelPartBuilder.create(), ModelTransform.of(-1.0F, 12.0F, 8.0F, 0.62831855F, 0.0F, 0.0F));
		modelPartData3.addChild("real_tail", ModelPartBuilder.create().uv(9, 18).cuboid(0.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F), ModelTransform.NONE);
		return TexturedModelData.of(modelData, 64, 32);
	}

	protected Iterable<ModelPart> getHeadParts() {
		return ImmutableList.of(head);
	}

	protected Iterable<ModelPart> getBodyParts() {
		return ImmutableList.of(torso, rightHindLeg, leftHindLeg, rightFrontLeg, leftFrontLeg, tail, neck);
	}

	@Override
	public void animateModel(T entity, float limbAngle, float limbDistance, float tickDelta) {
		if (entity.isAngry()) {
			tail.yaw = 0.0F;
		} else {
			tail.yaw = MathHelper.cos(limbAngle * 0.6662F) * 1.4F * limbDistance;
		}
		torso.setPivot(0.0F, 14.0F, 2.0F);
		torso.pitch = 1.5707964F;
		neck.setPivot(-1.0F, 14.0F, -3.0F);
		neck.pitch = torso.pitch;
		tail.setPivot(-1.0F, 12.0F, 8.0F);
		rightHindLeg.setPivot(-2.5F, 16.0F, 7.0F);
		leftHindLeg.setPivot(0.5F, 16.0F, 7.0F);
		rightFrontLeg.setPivot(-2.5F, 16.0F, -4.0F);
		leftFrontLeg.setPivot(0.5F, 16.0F, -4.0F);
		rightHindLeg.pitch = MathHelper.cos(limbAngle * 0.6662F) * 1.4F * limbDistance;
		leftHindLeg.pitch = MathHelper.cos(limbAngle * 0.6662F + 3.1415927F) * 1.4F * limbDistance;
		rightFrontLeg.pitch = MathHelper.cos(limbAngle * 0.6662F + 3.1415927F) * 1.4F * limbDistance;
		leftFrontLeg.pitch = MathHelper.cos(limbAngle * 0.6662F) * 1.4F * limbDistance;
	}

	public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
		head.pitch = headPitch * ((float) Math.PI / 180F);
		head.yaw = headYaw * ((float) Math.PI / 180F);
		tail.pitch = animationProgress;
	}

}