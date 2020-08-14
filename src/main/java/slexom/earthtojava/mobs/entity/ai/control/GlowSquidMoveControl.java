package slexom.earthtojava.mobs.entity.ai.control;

import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.MathHelper;
import slexom.earthtojava.mobs.entity.passive.GlowSquidEntity;

public class GlowSquidMoveControl extends MoveControl {
    private final GlowSquidEntity glowSquidEntity;

    public GlowSquidMoveControl(GlowSquidEntity glowSquidEntity) {
        super(glowSquidEntity);
        this.glowSquidEntity = glowSquidEntity;
    }

    @Override
    public void tick() {
        if (glowSquidEntity.isSubmergedIn(FluidTags.WATER))
            glowSquidEntity.setVelocity(glowSquidEntity.getVelocity().add(0, 0.005, 0));
        if (this.state == State.MOVE_TO && !glowSquidEntity.getNavigation().isIdle()) {
            double dx = this.targetX - glowSquidEntity.getX();
            double dy = this.targetY - glowSquidEntity.getY();
            double dz = this.targetZ - glowSquidEntity.getZ();
            dy = dy / (double) MathHelper.sqrt(dx * dx + dy * dy + dz * dz);
            glowSquidEntity.yaw = this.changeAngle(glowSquidEntity.yaw, (float) (MathHelper.atan2(dz, dx) * (double) (180 / (float) Math.PI)) - 90, 90);
            glowSquidEntity.bodyYaw = glowSquidEntity.yaw;
            glowSquidEntity.setMovementSpeed(MathHelper.lerp(0.125f, glowSquidEntity.getMovementSpeed(),
                    (float) (this.speed * glowSquidEntity.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED).getValue())));
            glowSquidEntity.setVelocity(glowSquidEntity.getVelocity().add(0, glowSquidEntity.getMovementSpeed() * dy * 0.1, 0));
        } else {
            glowSquidEntity.setMovementSpeed(0);
        }
    }
}
