package slexom.earthtojava.client.renderer.entity.model;

import com.google.common.collect.ImmutableList;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.ChickenEntityModel;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import slexom.earthtojava.entity.passive.FancyChickenEntity;

@Environment(EnvType.CLIENT)
public class FancyChickenModel<T extends FancyChickenEntity> extends ChickenEntityModel<T> {

	private static final String CREST = "crest";
	private final ModelPart tail;
	private final ModelPart crest;

	public FancyChickenModel(ModelPart root) {
		super(root);
		tail = root.getChild(EntityModelPartNames.TAIL);
		crest = root.getChild(CREST);
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		modelPartData.addChild(EntityModelPartNames.HEAD, ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -8.0F, -2.0F, 4.0F, 6.0F, 3.0F), ModelTransform.pivot(0.0F, 15.0F, -4.0F));
		modelPartData.addChild(EntityModelPartNames.BEAK, ModelPartBuilder.create().uv(14, 0).cuboid(-2.0F, -6.0F, -4.0F, 4.0F, 2.0F, 2.0F), ModelTransform.pivot(0.0F, 15.0F, -4.0F));
		modelPartData.addChild(RED_THING, ModelPartBuilder.create().uv(14, 4).cuboid(-0.5F, -4.0F, -3.0F, 1.0F, 2.0F, 2.0F), ModelTransform.pivot(0.0F, 15.0F, -4.0F));
		modelPartData.addChild(EntityModelPartNames.BODY, ModelPartBuilder.create().uv(0, 9).cuboid(-3.0F, -4.0F, -1.0F, 6.0F, 8.0F, 6.0F), ModelTransform.of(0.0F, 16.0F, 0.0F, 1.5707964F, 0.0F, 0.0F));
		ModelPartBuilder modelPartBuilder = ModelPartBuilder.create().uv(26, 0).cuboid(-1.0F, -2.0F, -3.0F, 3.0F, 7.0F, 3.0F);
		modelPartData.addChild(EntityModelPartNames.RIGHT_LEG, modelPartBuilder, ModelTransform.pivot(-2.0F, 19.0F, 1.0F));
		modelPartData.addChild(EntityModelPartNames.LEFT_LEG, modelPartBuilder, ModelTransform.pivot(1.0F, 19.0F, 1.0F));
		modelPartData.addChild(EntityModelPartNames.RIGHT_WING, ModelPartBuilder.create().uv(24, 13).cuboid(0.0F, 0.0F, -3.0F, 1.0F, 4.0F, 6.0F), ModelTransform.pivot(-4.0F, 11.0F, 0.0F));
		modelPartData.addChild(EntityModelPartNames.LEFT_WING, ModelPartBuilder.create().uv(24, 13).cuboid(-1.0F, 0.0F, -3.0F, 1.0F, 4.0F, 6.0F), ModelTransform.pivot(4.0F, 11.0F, 0.0F));
		modelPartData.addChild(EntityModelPartNames.TAIL, ModelPartBuilder.create().uv(48, 15).cuboid(-1.0F, -12.0F, 8.0F, 1.0F, 10.0F, 7.0F), ModelTransform.pivot(0.0F, 15.0F, -4.0F));
		modelPartData.addChild(CREST, ModelPartBuilder.create().uv(48, 0).cuboid(-1.0F, -12.0F, -3.0F, 1.0F, 5.0F, 5.0F), ModelTransform.pivot(0.0F, 15.0F, -4.0F));
		return TexturedModelData.of(modelData, 64, 32);

	}

	@Override
	protected Iterable<ModelPart> getHeadParts() {
		return new ImmutableList.Builder<ModelPart>().addAll(super.getHeadParts()).add(crest).build();
	}

	@Override
	protected Iterable<ModelPart> getBodyParts() {
		return new ImmutableList.Builder<ModelPart>().addAll(super.getBodyParts()).add(tail).build();
	}

	@Override
	public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
		super.setAngles(entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
		crest.pitch = head.pitch;
		crest.yaw = head.yaw;
	}
}
