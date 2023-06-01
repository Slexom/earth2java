package slexom.earthtojava.entity.ai.control;

import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.attribute.EntityAttributes;
import slexom.earthtojava.entity.passive.MelonGolemEntity;

public class MelonGolemMoveControl extends MoveControl {
	private final MelonGolemEntity melonGolem;
	private float targetYaw;
	private int jumpDelay;
	private boolean jumpOften;

	public MelonGolemMoveControl(MelonGolemEntity entity) {
		super(entity);
		melonGolem = entity;
		targetYaw = 180.0F * entity.getYaw() / (float) Math.PI;
	}

	public void setDirection(float targetYaw, boolean jumpOften) {
		this.targetYaw = targetYaw;
		this.jumpOften = jumpOften;
	}

	public void move(double speed) {
		this.speed = speed;
		state = State.MOVE_TO;
	}

	@Override
	public void tick() {
		entity.setYaw(wrapDegrees(entity.getYaw(), targetYaw, 90.0F));
		entity.headYaw = entity.getYaw();
		entity.bodyYaw = entity.getYaw();
		if (state != State.MOVE_TO) {
			entity.setForwardSpeed(0.0F);
		} else {
			state = State.WAIT;
			if (entity.isOnGround()) {
				entity.setMovementSpeed((float) (entity.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED)));
				if (jumpDelay-- <= 0) {
					jumpDelay = melonGolem.getJumpDelay();
					if (jumpOften) {
						jumpDelay /= 3;
					}
					melonGolem.getJumpControl().setActive();

				} else {
					melonGolem.sidewaysSpeed = 0.0F;
					melonGolem.forwardSpeed = 0.0F;
					entity.setMovementSpeed(0.0F);
				}
			} else {
				entity.setMovementSpeed((float) (entity.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED)));
			}

		}
	}
}
