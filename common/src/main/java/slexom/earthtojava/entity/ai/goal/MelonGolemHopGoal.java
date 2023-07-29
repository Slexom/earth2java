package slexom.earthtojava.entity.ai.goal;

import net.minecraft.entity.ai.goal.Goal;
import slexom.earthtojava.entity.ai.control.MelonGolemMoveControl;
import slexom.earthtojava.entity.passive.MelonGolemEntity;

import java.util.EnumSet;

public class MelonGolemHopGoal extends Goal {
	private final MelonGolemEntity melonGolem;

	public MelonGolemHopGoal(MelonGolemEntity entity) {
		melonGolem = entity;
		setControls(EnumSet.of(Control.JUMP, Control.MOVE));
	}

	public boolean canStart() {
		return !melonGolem.hasVehicle();
	}

	@Override
	public void tick() {
		((MelonGolemMoveControl) melonGolem.getMoveControl()).move(1.0D);
	}
}
