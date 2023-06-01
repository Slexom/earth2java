package slexom.earthtojava.client.renderer.entity.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.client.render.entity.model.QuadrupedEntityModel;
import slexom.earthtojava.entity.passive.RainbowSheepEntity;

@Environment(EnvType.CLIENT)
public class RainbowSheepWoolModel<T extends RainbowSheepEntity> extends QuadrupedEntityModel<T> {
	private float headRotationAngleX;

	public RainbowSheepWoolModel(ModelPart root) {
		super(root, false, 8.0F, 4.0F, 2.0F, 2.0F, 24);
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		modelPartData.addChild(EntityModelPartNames.HEAD, ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -5.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.6F)), ModelTransform.pivot(0.0F, 6.0F, -8.0F));
		modelPartData.addChild(EntityModelPartNames.BODY, ModelPartBuilder.create().uv(22, 36).cuboid(-6.0F, -11.5F, -8.5F, 12.0F, 19.0F, 9.0F, new Dilation(1.75F)), ModelTransform.of(0.0F, 5.0F, 2.0F, 1.5707964F, 0.0F, 0.0F));
		ModelPartBuilder hindLegModelPartBuilder = ModelPartBuilder.create().uv(0, 29).cuboid(-2.5F, -0.5F, -3.0F, 5.0F, 7.0F, 6.0F, new Dilation(0.5F));
		modelPartData.addChild(EntityModelPartNames.RIGHT_HIND_LEG, hindLegModelPartBuilder, ModelTransform.pivot(-3.0F, 12.0F, 7.0F));
		modelPartData.addChild(EntityModelPartNames.LEFT_HIND_LEG, hindLegModelPartBuilder, ModelTransform.pivot(3.0F, 12.0F, 7.0F));
		ModelPartBuilder frontLegModelPartBuilder = ModelPartBuilder.create().uv(0, 16).cuboid(-2.5F, -0.5F, -3.0F, 5.0F, 7.0F, 6.0F, new Dilation(0.5F));
		modelPartData.addChild(EntityModelPartNames.RIGHT_FRONT_LEG, frontLegModelPartBuilder, ModelTransform.pivot(-3.0F, 12.0F, -5.0F));
		modelPartData.addChild(EntityModelPartNames.LEFT_FRONT_LEG, frontLegModelPartBuilder, ModelTransform.pivot(3.0F, 12.0F, -5.0F));
		return TexturedModelData.of(modelData, 64, 64);
	}

	@Override
	public void animateModel(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
		super.animateModel(entityIn, limbSwing, limbSwingAmount, partialTick);
		head.pivotY = 6.0F + entityIn.getNeckAngle(partialTick) * 9.0F;
		headRotationAngleX = entityIn.getHeadAngle(partialTick);
	}

	@Override
	public void setAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		head.pitch = headRotationAngleX;
	}
}