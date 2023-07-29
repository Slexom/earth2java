package slexom.earthtojava.entity.ai.control;

import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.attribute.EntityAttributes;
import slexom.earthtojava.entity.passive.TropicalSlimeEntity;

public class TropicalSlimeMoveControl extends MoveControl {
	private final TropicalSlimeEntity slime;
	private float targetYaw;
	private int ticksUntilJump;
	private boolean jumpOften;

	public TropicalSlimeMoveControl(TropicalSlimeEntity slime) {
		super(slime);
		this.slime = slime;
		targetYaw = 180.0F * slime.getYaw() / (float) Math.PI;
	}

	public void look(float targetYaw, boolean jumpOften) {
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
			return;
		}
		state = State.WAIT;
		if (entity.isOnGround()) {
			entity.setMovementSpeed((float) (speed * entity.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED)));
			if (ticksUntilJump-- <= 0) {
				ticksUntilJump = slime.getTicksUntilNextJump();
				if (jumpOften) {
					ticksUntilJump /= 3;
				}
				slime.getJumpControl().setActive();
				if (slime.makesJumpSound()) {
					slime.playSound(slime.getJumpSound(), 1.0F, slime.getJumpSoundPitch());
				}
			} else {
				slime.sidewaysSpeed = 0.0F;
				slime.forwardSpeed = 0.0F;
				entity.setMovementSpeed(0.0F);
			}
		} else {
			entity.setMovementSpeed((float) (speed * entity.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED)));
		}

	}

}
