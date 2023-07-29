package slexom.earthtojava.client.renderer.entity.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.CowEntityModel;
import slexom.earthtojava.entity.base.E2JBaseShearableCowEntity;

@Environment(EnvType.CLIENT)
public class E2JShearableCowModel<T extends E2JBaseShearableCowEntity> extends CowEntityModel<T> {
	private float headPitchModifier;

	public E2JShearableCowModel(ModelPart root) {
		super(root);
	}

	@Override
	public void animateModel(T sheepEntity, float limbAngle, float limbDistance, float tickDelta) {
		super.animateModel(sheepEntity, limbAngle, limbDistance, tickDelta);
		head.pivotY = 4.0F + sheepEntity.getNeckAngle(tickDelta) * 7.0F;
		headPitchModifier = sheepEntity.getHeadAngle(tickDelta);
	}

	@Override
	public void setAngles(T sheepEntity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
		super.setAngles(sheepEntity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
		head.pitch = headPitchModifier;
	}
}
