package slexom.earthtojava.entity.ai.goal;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.effect.StatusEffects;
import slexom.earthtojava.entity.ai.control.TropicalSlimeMoveControl;
import slexom.earthtojava.entity.passive.TropicalSlimeEntity;

import java.util.EnumSet;

public class TropicalSlimeFaceRandomGoal extends Goal {
	private final TropicalSlimeEntity slime;
	private float chosenDegrees;
	private int nextRandomizeTime;

	public TropicalSlimeFaceRandomGoal(TropicalSlimeEntity slime) {
		this.slime = slime;
		setControls(EnumSet.of(Control.LOOK));
	}

	public boolean canStart() {
		return slime.getTarget() == null && (slime.isOnGround() || slime.isTouchingWater() || slime.isInLava() || slime.hasStatusEffect(StatusEffects.LEVITATION)) && slime.getMoveControl() instanceof TropicalSlimeMoveControl;
	}

	@Override
	public void tick() {
		if (--nextRandomizeTime <= 0) {
			nextRandomizeTime = 40 + slime.getRandom().nextInt(60);
			chosenDegrees = (float) slime.getRandom().nextInt(360);
		}
		((TropicalSlimeMoveControl) slime.getMoveControl()).look(chosenDegrees, false);
	}

}
