package slexom.earthtojava.entity.ai.goal;

import net.minecraft.entity.ai.goal.Goal;
import slexom.earthtojava.entity.ai.control.TropicalSlimeMoveControl;
import slexom.earthtojava.entity.passive.TropicalSlimeEntity;

import java.util.EnumSet;

public class TropicalSlimeHopGoal extends Goal {
	private final TropicalSlimeEntity slime;

	public TropicalSlimeHopGoal(TropicalSlimeEntity slime) {
		this.slime = slime;
		setControls(EnumSet.of(Control.JUMP, Control.MOVE));
	}

	public boolean canStart() {
		return !slime.hasVehicle();
	}

	@Override
	public void tick() {
		((TropicalSlimeMoveControl) slime.getMoveControl()).move(1.0D);
	}
}
