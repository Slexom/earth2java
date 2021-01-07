package slexom.earthtojava.mobs.client.renderer.entity.model;

import net.minecraft.client.render.entity.model.CowEntityModel;
import slexom.earthtojava.mobs.entity.base.E2JBaseShearableCowEntity;

public class E2JShearableCowModel<T extends E2JBaseShearableCowEntity> extends CowEntityModel<T> {
    private float headPitchModifier;

    public void animateModel(T sheepEntity, float limbAngle, float limbDistance, float tickDelta) {
        super.animateModel(sheepEntity, limbAngle, limbDistance, tickDelta);
        this.head.pivotY = 4.0F + sheepEntity.getNeckAngle(tickDelta) * 7.0F;
        this.headPitchModifier = sheepEntity.getHeadAngle(tickDelta);
    }

    public void setAngles(T sheepEntity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        super.setAngles(sheepEntity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
        this.head.pitch = this.headPitchModifier;
    }
}
